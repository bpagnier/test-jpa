package bpagnier.jpatest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Session session = entityManager.unwrap(Session.class);

        Father father = new Father();
        father.setName("father name");

        Child child = new Child();
        child.setFather(father);
        child.setName("child name");

        father.getChilds().add(child);

        logger.info("### save father : {}", session.save(father));
        logger.info("### father from : {}", father.getName());

        Query query = session.createQuery("update Father set name = 'father name updated'");
        query.executeUpdate();

        session.flush();
        session.clear();

        logger.info("### Refresh father");
        session.refresh(father);

        Father reloadedfather = session.get(Father.class, father.getId());

        logger.info("### father name : {}", father.getName());
        logger.info("### Reloaded father name : {}", reloadedfather.getName());
    }

}
