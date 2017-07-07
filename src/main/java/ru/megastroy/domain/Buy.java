package ru.megastroy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by pccp on 15.04.2017.
 */

@Entity
@Table(name = "buys")
public class Buy {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name="date")
    private Date date;

    @Getter @Setter
    @Column(name = "price", nullable = false)
    private int price;

    @Getter @Setter
    @Column(name = "house")
    private long house;

    @Getter @Setter
    @Column(name = "apartment")
    private  long apartment;

    @Getter @Setter
    @ManyToOne()
    @JoinColumn(name="id_street")
    private Street street;

    @Getter @Setter
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "buy_product",
            joinColumns = @JoinColumn(name = "id_buy", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"))
    private List<Product> products;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;


    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="id_status")
    private StatusBuy status;
}
