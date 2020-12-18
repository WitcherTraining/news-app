package com.epam.spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_generator")
    @SequenceGenerator(name = "news_id_generator", sequenceName = "NEWS_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BRIEF_CONTENT")
    private String briefContent;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NEWS_DATE")
    private Date date;
}
