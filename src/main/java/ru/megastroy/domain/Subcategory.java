package ru.megastroy.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pccp on 15.04.2017.
 */

@Entity
@Table(name = "subcategories")
public class Subcategory implements Serializable {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "subcategory")
    private List<Product> products;

    @Getter @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    public Subcategory() { }

    @JsonCreator
    public Subcategory(@JsonProperty("id") long id,
                       @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
