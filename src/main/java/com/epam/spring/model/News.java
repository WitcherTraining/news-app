package com.epam.spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"author"})
@Entity
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String briefContent;
    private String content;

    @ManyToOne
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "NEWS_CATEGORY", joinColumns = @JoinColumn(name = "NEWS_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private Set<Category> categories = new HashSet<>();
}
