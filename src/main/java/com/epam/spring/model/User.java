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
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    //Initialize Set to avoid null pointer at startup
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<News> news = new HashSet<>();
}
