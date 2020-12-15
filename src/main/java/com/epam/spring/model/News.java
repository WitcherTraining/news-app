package com.epam.spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "NEWS")
public class News extends BaseEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BRIEF_CONTENT")
    private String briefContent;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NEWS_DATE")
    private Date date;
}
