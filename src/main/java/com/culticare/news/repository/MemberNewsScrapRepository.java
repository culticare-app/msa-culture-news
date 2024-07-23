package com.culticare.news.repository;

import com.culticare.news.entity.MemberNewsScrap;
import com.culticare.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberNewsScrapRepository extends JpaRepository<MemberNewsScrap, Long> {

    boolean existsByMemberIdAndNews(Long memberId, News news);

    List<MemberNewsScrap> findByMemberId(Long memberId);

    Optional<MemberNewsScrap> findByMemberIdAndNews(Long memberId, News news);
}
