package com.culticare.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberNewsScrap {

    @Id
    @GeneratedValue
    @Column(name = "member_news_scrap")
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    @Builder
    public MemberNewsScrap(Long memberId, News news) {
        this.memberId = memberId;
        this.news = news;
    }
}
