package com.epam.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class News {

    private Long id;
    private String title;
    private String briefContent;
    private String content;
    private User author;
    private Set<Category> categories;
}
