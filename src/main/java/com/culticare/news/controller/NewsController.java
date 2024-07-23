package com.culticare.news.controller;

import com.culticare.news.controller.dto.request.NewsCreateRequestDto;
import com.culticare.news.controller.dto.response.NewsListResponseDto;
import com.culticare.news.controller.dto.response.NewsResponseDto;
import com.culticare.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/news")
@RestController
public class NewsController {

    private final NewsService newsService;

    // 뉴스 생성 - 관리자 페이지
    @PostMapping("/create")
    public ResponseEntity<Void> saveNews(NewsCreateRequestDto newsCreateRequestDto) {

        newsService.saveNews(newsCreateRequestDto);

        return ResponseEntity.ok().build();
    }

    // 뉴스 목록 타입(카테고리)별 조회
    @GetMapping("/list")
    public ResponseEntity<List<NewsListResponseDto>> findByType(@RequestParam(name = "newsType") String newsType) {

        List<NewsListResponseDto> newsListResponseDto = newsService.getNewsListByType(newsType);

        return ResponseEntity.status(HttpStatus.OK).body(newsListResponseDto);
    }

    // 뉴스 개별 조회
    @GetMapping("/{newsId}")
    public ResponseEntity<NewsResponseDto> findById(@RequestHeader("memberId") Long loginMemberId, @PathVariable("newsId") Long newsId) {

        NewsResponseDto result = newsService.getNewsById(loginMemberId, newsId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 뉴스 스크랩
    @PostMapping("/scrap/{newsId}")
    public ResponseEntity<Long> scrapNews(@RequestHeader("memberId") Long loginMemberId, @PathVariable("newsId") Long newsId) {

        Long result = newsService.scrapNews(loginMemberId, newsId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 뉴스 스크랩 취소
    @DeleteMapping("/scrap/{newsId}")
    public ResponseEntity<Void> deleteScrapNews(@RequestHeader("memberId") Long loginMemberId, @PathVariable("newsId") Long newsId) {

        newsService.deleteScrap(loginMemberId, newsId);

        return ResponseEntity.ok().build();
    }
}
