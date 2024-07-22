package com.culticare.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NewsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_type_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "newsType", cascade = CascadeType.ALL)
    private List<News> newsList = new ArrayList<>();

    @Builder
    public NewsType(String name) {
        this.name = name;
    }
}
