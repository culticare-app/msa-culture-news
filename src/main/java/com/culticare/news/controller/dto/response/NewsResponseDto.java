package com.culticare.news.controller.dto.response;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String newsType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
