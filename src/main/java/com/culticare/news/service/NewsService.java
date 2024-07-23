package com.culticare.news.service;

import com.culticare.common.exception.CustomException;
import com.culticare.common.exception.ErrorCode;
import com.culticare.news.controller.dto.request.NewsCreateRequestDto;
import com.culticare.news.controller.dto.response.NewsListResponseDto;
import com.culticare.news.controller.dto.response.NewsResponseDto;
import com.culticare.news.entity.MemberNewsScrap;
import com.culticare.news.entity.News;
import com.culticare.news.entity.NewsType;
import com.culticare.news.repository.MemberNewsScrapRepository;
import com.culticare.news.repository.NewsRepository;
import com.culticare.news.repository.NewsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsTypeRepository newsTypeRepository;
    private final MemberNewsScrapRepository memberNewsScrapRepository;

    @Transactional
    public void saveNews(NewsCreateRequestDto dto) {

        News news = dto.toEntity();

        NewsType newsType = newsTypeRepository.findByName(dto.getNewsType())
                .orElseGet(() -> {
                    NewsType newNewsType = NewsType.builder()
                            .name(dto.getTitle())
                            .build();
                    return newsTypeRepository.save(newNewsType);
                });

        news.setNewsType(newsType);

        newsRepository.save(news);
    }

    public List<NewsListResponseDto> getNewsListByType(String newsType) {

        NewsType findNewsType = newsTypeRepository.findByName(newsType).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));
        List<News> findNewsList = newsRepository.findByNewsType(findNewsType);

        List<NewsListResponseDto> newsListResponseDtoList = new ArrayList<>();

        for (News news : findNewsList) {

            NewsListResponseDto dto = NewsListResponseDto.builder()
                    .id(news.getId())
                    .title(news.getTitle())
                    .content(news.getContent())
                    .newsType(findNewsType.getName())
                    .createdAt(news.getCreatedAt())
                    .modifiedAt(news.getModifiedAt())
                    .build();

            newsListResponseDtoList.add(dto);
        }

        return newsListResponseDtoList;
    }

    public NewsResponseDto getNewsById(Long loginMemberId, Long newsId) {

        News findNews = newsRepository.findById(newsId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        boolean isScrapped = false;

        // 스크랩 여부 확인 추가


        return NewsResponseDto.builder()
                .id(findNews.getId())
                .title(findNews.getTitle())
                .content(findNews.getContent())
                .newsType(findNews.getNewsType().getName())
                .createdAt(findNews.getCreatedAt())
                .modifiedAt(findNews.getModifiedAt())
                .build();

    }


    @Transactional
    public Long scrapNews(Long loginMemberId, Long newsId) {

        News findNews = newsRepository.findById(newsId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));


        if (memberNewsScrapRepository.existsByMemberIdAndNews(loginMemberId, findNews)) {
            throw new CustomException(ErrorCode.EXIST_MEMBER_NEWS_SCRAP);
        }

        MemberNewsScrap memberNewsScrap = MemberNewsScrap.builder()
                .memberId(loginMemberId)
                .news(findNews)
                .build();

        return memberNewsScrapRepository.save(memberNewsScrap).getId();
    }

    @Transactional
    public void deleteScrap(Long loginMemberId, Long newsId) {

        News findNews = newsRepository.findById(newsId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));


        if (!memberNewsScrapRepository.existsByMemberIdAndNews(loginMemberId, findNews)) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER_NEWS_SCRAP);
        }

        MemberNewsScrap findMemberNewsScrap = memberNewsScrapRepository.findByMemberIdAndNews(loginMemberId, findNews)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER_NEWS_SCRAP));

        memberNewsScrapRepository.delete(findMemberNewsScrap);
    }


}
