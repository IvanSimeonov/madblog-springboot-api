package com.maddob.blog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.domain.Article;
import com.maddob.blog.helpers.TestPage;
import com.maddob.blog.mappers.ArticleConstants;
import com.maddob.blog.mappers.ArticleMapper;
import com.maddob.blog.mappers.ArticleMapperImpl;
import com.maddob.blog.repositories.ArticleRepository;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

	@Mock
	private ArticleRepository articleRepository;
	
	@Spy
	private ArticleMapper articleMapper = new ArticleMapperImpl();
	
	@InjectMocks
	private ArticleServiceImpl serviceUnderTest;
	
	
	@Test
	public void testGetArticlePageDoesNotReturnNull() {
		// given
		given(articleRepository.findByPublishedTrue(any())).willReturn(null);
		
		// when
		ArticlePageDTO articlePage = serviceUnderTest.getArticlePage(0);
		
		// then
		assertNotNull(articlePage);
	}
	
	@Test
	public void testGetArticlePageReturnsCorrectObject() {
		// given
		given(articleRepository.findByPublishedTrue(any())).willReturn(
				new TestPage<Article>(1, 1l, 1, 1, 1, Collections.singletonList(ArticleConstants.DOMAIN))
			);
		
		// when
		ArticlePageDTO articlePage = serviceUnderTest.getArticlePage(0);
		
		// then
		assertNotNull(articlePage);
		assertEquals(1, articlePage.getPageNumber());
		assertEquals(1, articlePage.getTotalArticles());
		assertEquals(1, articlePage.getTotalPages());
		assertEquals(ArticleConstants.DTO, articlePage.getArticles().get(0));
		
	}
}
