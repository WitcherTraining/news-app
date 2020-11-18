package com.epam.spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"author"}, callSuper = true)
@Entity
@Table(name = "NEWS")
public class News extends BaseEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BRIEF_CONTENT")
    private String briefContent;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "NEWS_CATEGORY", joinColumns = @JoinColumn(name = "NEWS_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private Set<Category> categories = new HashSet<>();
}
