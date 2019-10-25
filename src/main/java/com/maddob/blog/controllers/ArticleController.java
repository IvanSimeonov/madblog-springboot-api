package com.maddob.blog.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.service.ArticleService;

@RestController
@RequestMapping("/api/v1/")
public class ArticleController {
	
	private final ArticleService articleService;
	
	public ArticleController(final ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("/articles")
	public ArticlePageDTO getArticlesByPage(@RequestParam(required = false) Long page) {
		int pageNumber = (page == null || page < 0)? 0: page.intValue();
		return this.articleService.getArticlePage(pageNumber);
	}
	
	@GetMapping("/articles/{articleId}")
	public ArticleDTO getArticleById(@PathVariable Long articleId) {
		return this.articleService.getArticle(articleId);
	}

}
