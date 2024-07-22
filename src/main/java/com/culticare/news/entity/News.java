package com.culticare.news.entity;

import com.culticare.BaseTimeEntity;
import com.culticare.news.repository.NewsTypeRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_type_id")
    private NewsType newsType;

    @Builder
    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
        newsType.getNewsList().add(this);
    }
}
