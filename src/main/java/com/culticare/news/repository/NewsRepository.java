package com.culticare.news.repository;

import com.culticare.news.entity.News;
import com.culticare.news.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByNewsType(NewsType newsType);
}
