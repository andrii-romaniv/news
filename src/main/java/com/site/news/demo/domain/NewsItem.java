package com.site.news.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="news_item")
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title",unique = true,nullable = false)
    @NotBlank(message = "News title cannot be empty")
    @Length(max = 255, message = "Title too long")
    private String title;
    @Column(name="tag",nullable = false)
    @NotBlank(message = "News tag cannot be empty")
    @Length(max = 255, message = "Tag too long")
    private String tag;
    @Column(name="content",nullable = false,length = 2550)
    @NotBlank(message = "Content for news cannot be empty")
    @Length(max = 2550, message = "Text content too long")
    private String content;
    @Column(name="image",nullable = false)
    private String image;
    @Column(name="date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
}
