package com.epam.spring.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseEntity {

    private String name;

    //Initialize Set to avoid null pointer at startup
    @ManyToMany(mappedBy = "categories")
    private Set<News> news = new HashSet<>();
}
