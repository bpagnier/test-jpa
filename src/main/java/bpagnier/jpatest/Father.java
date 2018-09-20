package bpagnier.jpatest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Father {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "father", cascade = CascadeType.ALL)
    private Set<Child> childs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Child> getChilds() {
        return childs;
    }

    public void setChilds(Set<Child> childs) {
        this.childs = childs;
    }

}
