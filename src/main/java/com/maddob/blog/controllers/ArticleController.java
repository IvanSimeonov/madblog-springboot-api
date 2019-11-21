package com.maddob.blog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.service.ArticleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/")
public class ArticleController {
	
	private final ArticleService articleService;
	
	public ArticleController(final ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@ApiOperation(value = "Gets last articles", notes = "Returns articles in pages. The default page consists of 5 elements.")
	@GetMapping("/articles")
	public ArticlePageDTO getArticlesByPage(@RequestParam(required = false) Long page, @RequestParam(required = false) Long pageSize) {
		int pageNumber = (page == null || page < 0)? 0: page.intValue();
		
		if (pageSize == null || pageSize < 0) {
			return this.articleService.getArticlePage(pageNumber);
		}
		
		return this.articleService.getArticlePage(pageNumber, pageSize.intValue());
	}
	
	@ApiOperation(value = "Retrieves a single article by its id")
	@GetMapping("/articles/{articleId}")
	public ArticleDTO getArticleById(@PathVariable Long articleId) {
		return this.articleService.getArticle(articleId);
	}

}
