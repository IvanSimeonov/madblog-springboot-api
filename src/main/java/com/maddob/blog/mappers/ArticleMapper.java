package com.maddob.blog.mappers;

import org.springframework.data.domain.Page;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.domain.Article;

public interface ArticleMapper {
	ArticleDTO domain2dto(Article article);
	Article dto2domain(ArticleDTO articleDto);
	ArticlePageDTO domainPage2dto(Page<Article> articlePage);
}
