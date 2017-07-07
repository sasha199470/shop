package ru.megastroy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
@Entity
@Table(name = "status_buys")
public class StatusBuy {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter @Setter
    @OneToMany(mappedBy = "status")
    private List<Buy> buys;
}