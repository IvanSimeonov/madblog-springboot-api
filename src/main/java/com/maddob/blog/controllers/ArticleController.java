package com.maddob.blog.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Article Controller", tags = {"article"})
@RestController
@RequestMapping("/api/v1/")
public class ArticleController {
	
	private final ArticleService articleService;
	
	public ArticleController(final ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@ApiOperation(value = "Gets article page", notes = "Returns articles in pages. The default page consists of 5 elements.")
	@GetMapping("/articles")
	public ArticlePageDTO getArticlesByPage(@RequestParam(required = false) Long page, @RequestParam(required = false) Long pageSize) {
		int pageNumber = (page == null || page < 0)? 0: page.intValue();
		
		if (pageSize == null || pageSize < 0) {
			return this.articleService.getArticlePage(pageNumber);
		}
		
		return this.articleService.getArticlePage(pageNumber, pageSize.intValue());
	}
	
	@ApiOperation(value = "Retrieves a single article by its id", nickname = "getArticleById", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/articles/{articleId}")
	public ArticleDTO getArticleById(@PathVariable Long articleId) {
		return this.articleService.getArticle(articleId);
	}


	@PostMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ArticleDTO createArticle(@RequestBody ArticleDTO article) {
		return this.articleService.createArticle(article);
	}

	@DeleteMapping("/article/table/{articleId}")
	public void deleteArticleById(@PathVariable Long articleId) {
		articleService.deleteArticle(articleId);
	}

//	@PutMapping("article/edit/{articleId}")
//	public ArticleDTO editArticle(@RequestBody ArticleDTO article, @PathVariable Long articleId){
//		return this.articleService.editArticle(article, articleId);
//	}


}
