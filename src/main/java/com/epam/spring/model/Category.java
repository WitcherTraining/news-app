package com.epam.spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    //Initialize Set to avoid null pointer at startup
    @ManyToMany(mappedBy = "categories")
    private Set<News> news = new HashSet<>();
}
