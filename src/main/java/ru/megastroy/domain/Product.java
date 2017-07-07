package ru.megastroy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pccp on 15.04.2017.
 */

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "description", nullable = false, length = 50000)
    private String description;

    @Getter @Setter
    @Column(name = "price", nullable = false)
    private int price;

    @Getter @Setter
    @Column(name = "photo")
    private String photo;

//    @Getter @Setter
//    @OneToMany(mappedBy = "product")
//    private List<Basket> baskets;

    @Getter @Setter
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Buy> buys;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="id_firm")
    private Firm firm;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_subdirectory")
    private Subcategory subcategory;

    @Getter @Setter
    @OneToMany(mappedBy = "product")
    private List<Value> values;


}
