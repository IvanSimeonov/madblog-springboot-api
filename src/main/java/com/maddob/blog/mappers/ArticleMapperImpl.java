package com.maddob.blog.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.domain.Article;

@Component
public class ArticleMapperImpl implements ArticleMapper {

	@Override
	public ArticleDTO domain2dto(Article article) {
		if (article == null) {
			return null;
		}
		return ArticleDTO.builder()
				.id(article.getId())
				.content(article.getContent())
				.created(article.getCreated())
				.featured(article.isFeatured())
				.published(article.isPublished())
				.coverImage(article.getCoverImage())
				.subtitle(article.getSubtitle())
				.title(article.getTitle())
				.build();
	}

	@Override
	public Article dto2domain(ArticleDTO articleDto) {
		if (articleDto == null) {
			return null;
		}
		
		return Article.builder()
				.id(articleDto.getId())
				.content(articleDto.getContent())
				.title(articleDto.getTitle())
				.subtitle(articleDto.getSubtitle())
				.coverImage(articleDto.getCoverImage())
				.featured(articleDto.isFeatured())
				.published(articleDto.isPublished())
				.created(articleDto.getCreated())
				.build();
		
	}

	@Override
	public ArticlePageDTO domainPage2dto(Page<Article> articlePage) {
		
		int totalArticles = 0;
		int totalPages = 0;
		int pageNumber = 0;
		List<ArticleDTO> articles = new ArrayList<>();
		
		if (articlePage != null) {
			totalArticles = (int) articlePage.getTotalElements();
			totalPages = articlePage.getTotalPages();
			pageNumber = articlePage.getNumber();
			if (articlePage.getContent() != null && !articlePage.getContent().isEmpty()) {				
				articles = articlePage.getContent()
						.stream().map(this::domain2dto)
						.collect(Collectors.toList());
			}
		}
		return ArticlePageDTO.builder()
				.pageNumber(pageNumber)
				.totalArticles(totalArticles)
				.totalPages(totalPages)
				.articles(articles)
				.build();
	}

}
