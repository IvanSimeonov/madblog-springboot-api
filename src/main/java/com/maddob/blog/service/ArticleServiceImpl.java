package com.maddob.blog.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.mappers.ArticleMapper;
import com.maddob.blog.repositories.ArticleRepository;

/**
 * Article service implementation
 * 
 * Gives access to the article information
 * 
 * @author Martin Dobrev
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	public final static int DEFAULT_ARTICLE_PAGE_SIZE = 5;
	
	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;
	
	public ArticleServiceImpl(final ArticleRepository articleRepository, final ArticleMapper articleMapper) {
		this.articleRepository = articleRepository;
		this.articleMapper = articleMapper;
	}

	@Override
	public ArticlePageDTO getArticlePage(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize); 
		return articleMapper.domainPage2dto(articleRepository.findByPublishedTrue(pageable));
	}
	
	@Override
	public ArticlePageDTO getArticlePage(int pageNumber) {
		return this.getArticlePage(pageNumber, DEFAULT_ARTICLE_PAGE_SIZE);
	}

	@Override
	public ArticleDTO getArticle(Long id) {
		if (!articleRepository.existsById(id)) {
			return null;
		}
		
		return articleMapper.domain2dto(articleRepository.findById(id).get());
	}
}
