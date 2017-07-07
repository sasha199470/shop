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
@Table(name = "users")
public class User {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter @Setter
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name="first_name")
    private String firstName;


    @Getter @Setter
    @Column(name="last_name")
    private String lastName;

    @Getter @Setter
    @Column(name = "phone")
    private String phone;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_sreet")
    private Street street;

    @Getter @Setter
    @Column(name = "house")
    private long house;

    @Getter @Setter
    @Column(name = "apartment")
    private long apartment;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public List<Role> roles;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Buy> buys;

    @Getter @Setter
    @OneToMany (mappedBy = "user")
    private List<Basket> baskets;

    public User() { }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
