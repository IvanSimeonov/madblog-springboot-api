package com.maddob.blog.service;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;

public interface ArticleService {
	ArticlePageDTO getArticlePage(int pageNumber);
	ArticlePageDTO getArticlePage(int pageNumber, int pageSize);
	ArticleDTO getArticle(Long id);
    ArticleDTO createArticle(ArticleDTO article);
}
