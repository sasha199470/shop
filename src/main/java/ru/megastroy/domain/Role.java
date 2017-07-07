package ru.megastroy.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pccp on 15.04.2017.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    public List<User> users;

}