package com.epam.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String login;
    private String password;

    //Initialize Set to avoid null pointer at startup
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<News> news = new HashSet<>();
}
