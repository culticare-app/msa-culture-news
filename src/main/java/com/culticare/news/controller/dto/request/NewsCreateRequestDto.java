package com.culticare.news.controller.dto.request;

import com.culticare.news.entity.News;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsCreateRequestDto {

    private String title;
    private String content;
    private String newsType;

    public News toEntity() {
        return News.builder()
                .title(title)
                .content(content)
                .build();
    }
}
