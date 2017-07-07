package ru.megastroy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by pccp on 12.06.2017.
 */

@Entity
@Table(name="baskets")
public class Basket {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_product")
    private  Product product;

    @Getter @Setter
    @Column(name = "namber")
    private long namber;
}
