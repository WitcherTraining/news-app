package com.epam.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User implements java.io.Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private Set<News> news;
}
