package bpagnier.jpatest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(Child.ChildPk.class)
public class Child {

    @Id
    @JoinColumn(name = "father_id")
    @ManyToOne(optional = false)
    private Father father;

    @Id
    @Column(name = "name")
    private String name;

    public Child() {

    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class ChildPk implements Serializable {

        private Father father;
        private String name;

        public Father getFather() {
            return father;
        }

        public void setFather(Father father) {
            this.father = father;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ChildPk childPk = (ChildPk) o;
            return Objects.equals(father, childPk.father) &&
                    Objects.equals(name, childPk.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(father, name);
        }
    }
}
