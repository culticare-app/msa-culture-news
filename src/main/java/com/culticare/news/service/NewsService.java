package com.culticare.news.service;

import com.culticare.news.controller.dto.request.NewsCreateRequestDto;
import com.culticare.news.controller.dto.response.NewsListResponseDto;
import com.culticare.news.entity.News;
import com.culticare.news.entity.NewsType;
import com.culticare.news.repository.NewsRepository;
import com.culticare.news.repository.NewsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsTypeRepository newsTypeRepository;

    @Transactional
    public void saveNews(NewsCreateRequestDto dto) {

    }

    public void getNewsByType(String type) {

    }

    public NewsListResponseDto getNewsListByType(String newsType) {
        
    }
}
