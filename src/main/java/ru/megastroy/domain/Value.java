package ru.megastroy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pccp on 19.05.2017.
 */
@Entity
@Table(name = "value")
public class Value {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "price")
    private long price;

    @Getter @Setter
    @Column(name ="number_rec")
    private long numberRec;

    @Getter @Setter
    @Column (name ="number_rem")
    private long numbeRem;

    @Getter @Setter
    @Column(name ="date")
    private Date date;

    @Getter @Setter
    @ManyToOne
    private Product product;

}
