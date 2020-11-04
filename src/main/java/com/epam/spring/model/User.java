package com.epam.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private Set<News> news;
}
