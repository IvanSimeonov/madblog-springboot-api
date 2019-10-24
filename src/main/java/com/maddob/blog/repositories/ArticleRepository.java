package com.maddob.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.maddob.blog.domain.Article;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	Page<Article> findByPublishedTrue(Pageable pageable);
	Page<Article> findAll(Pageable pageable);
}
