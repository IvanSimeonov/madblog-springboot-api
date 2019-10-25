package com.maddob.blog.service;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;

public interface ArticleService {
	ArticlePageDTO getArticlePage(int pageNumber);
	ArticleDTO getArticle(Long id);
}
