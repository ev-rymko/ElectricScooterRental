package com.senla.finalProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
@SequenceGenerator(name = "default_gen", sequenceName = "role_seq", allocationSize = 1)
public class Role extends AbstractEntity{

    @Column(name = "role_value", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<Account> accountList;

    public Role() {
    }
}
