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
@Table(name = "firms")
public class Firm implements Serializable
{
    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "name")
    private  String name;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "firm")
    private List<Product> products;

    public Firm() { }

    @JsonCreator
    public Firm(@JsonProperty("id") long id,
                @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
