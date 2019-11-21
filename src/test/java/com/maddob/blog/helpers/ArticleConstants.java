package com.maddob.blog.helpers;

import java.time.LocalDateTime;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.domain.Article;

public class ArticleConstants {
	public final static String CONTENT = "TEST CONTENT";
	public final static String TITLE = "TEST TITLE";
	public final static String SUBTITLE = "TEST SUBTITLE";
	public final static LocalDateTime DATE_TIME = LocalDateTime.of(2019, 1, 1, 10, 0);
	public final static Long ID = 57485438L;
	
	public final static Article DOMAIN = Article.builder()
			.content(CONTENT)
			.created(DATE_TIME)
			.id(ID)
			.published(false)
			.featured(true)
			.subtitle(SUBTITLE)
			.title(TITLE)
			.build();
	
	public final static ArticleDTO DTO = ArticleDTO.builder()
			.content(CONTENT)
			.created(DATE_TIME)
			.id(ID)
			.published(false)
			.featured(true)
			.subtitle(SUBTITLE)
			.title(TITLE)
			.build();
}
