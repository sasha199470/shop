package ru.megastroy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pccp on 15.04.2017.
 */

@Entity
@Table(name = "categories")
public class Category {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @OneToMany(mappedBy = "category")
    private List<Subcategory> subdirectories;
}
