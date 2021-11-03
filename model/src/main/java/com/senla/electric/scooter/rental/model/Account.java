package com.senla.electric.scooter.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "default_gen", sequenceName = "account_seq", allocationSize = 1)
public class Account extends AbstractEntity {

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToOne
    @JoinColumn(name="user_id", unique = true, nullable = false)
    private User user;

    public Account() {
    }
}
