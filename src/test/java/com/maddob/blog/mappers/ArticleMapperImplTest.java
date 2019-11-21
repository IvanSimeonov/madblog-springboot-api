package com.maddob.blog.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.domain.Article;
import com.maddob.blog.helpers.ArticleConstants;
import com.maddob.blog.helpers.TestPage;

public class ArticleMapperImplTest {
	
	private final ArticleMapperImpl mapperUnderTest = new ArticleMapperImpl();
	
	@Nested
	@DisplayName("ARTICLE: Domain -> DTO")
	class Domain2dtoTests {
		
		@Test
		public void testDomain2DtoReturnsNullWhenNullPassed() {
			// when
			ArticleDTO dto = mapperUnderTest.domain2dto(null);
			
			// then
			assertNull(dto);
		}
		
		@Test
		public void testDomain2DtoReturnsCorrectObject() {
			// when
			ArticleDTO dto = mapperUnderTest.domain2dto(ArticleConstants.DOMAIN);
			
			// then
			assertEquals(ArticleConstants.DTO, dto);
		}
	}
	
	@Nested
	@DisplayName("ARTICLE: Domain Page -> DTO Page")
	class DomainPage2dtoPageTests {
		
		@Test
		public void testDomainPage2dtoReturnsEmptyObjectWhenNullPassed() {
			
			// when
			ArticlePageDTO dtoPage = mapperUnderTest.domainPage2dto(null);
			
			// then
			assertNotNull(dtoPage);
			assertEquals(0, dtoPage.getTotalPages());
			assertEquals(0, dtoPage.getPageNumber());
			assertEquals(0, dtoPage.getTotalArticles());
			assertNotNull(dtoPage.getArticles());
			assertTrue(dtoPage.getArticles().isEmpty());
		}
		
		@Test
		public void testDomainPage2dtoReturnsCorrectObject() {
			// given
			List<Article> articles = new ArrayList<>();
			articles.add(ArticleConstants.DOMAIN);
			Page<Article> page = new TestPage<Article>(1, 5l, 1, 1, 1, articles);
			
			// when
			ArticlePageDTO dtoPage = mapperUnderTest.domainPage2dto(page);
			
			// then
			assertNotNull(dtoPage);
			assertEquals(5, dtoPage.getTotalArticles());
			assertEquals(1, dtoPage.getPageNumber());
			assertEquals(1, dtoPage.getTotalPages());
			assertNotNull(dtoPage.getArticles());
			assertFalse(dtoPage.getArticles().isEmpty());
			assertEquals(1, dtoPage.getArticles().size());
			assertEquals(ArticleConstants.DTO, dtoPage.getArticles().get(0));
		}
	}
	
	@Nested
	@DisplayName("ARTICLE: DTO -> Domain")
	class Dto2domainTests {
		
		@Test
		@DisplayName("Expect null when DTO is null")
		public void testDto2domainReturnsNullWhenNullPassed() {
			// when
			Article article = mapperUnderTest.dto2domain(null);
			
			// then
			assertNull(article);
		}
		
		@Test
		@DisplayName("Correct domain object is returned from DTO")
		public void testDto2domainReturnsCorrectObject() {
			// when
			Article article = mapperUnderTest.dto2domain(ArticleConstants.DTO);
			
			// then
			assertNotNull(article);
			assertEquals(ArticleConstants.DOMAIN, article);
		}
	}
	
}
