package com.maddob.blog.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maddob.blog.api.ArticleDTO;
import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.helpers.ArticleConstants;
import com.maddob.blog.service.ArticleService;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

	@Mock
	ArticleService articleService;
	
	@InjectMocks
	ArticleController controllerUnderTest;
	
	@Captor
	ArgumentCaptor<Integer> pageNumberCaptor;
	
	@Captor
	ArgumentCaptor<Integer> pageSizeCaptor;
	
	@Test
	public void testControllerCallsPageZeroWhenNoParameterSet() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(null, null);
		
		// then
		assertEquals(0, pageNumberCaptor.getValue());
	}
	
	@Test
	public void testControllerCallsCorrectPageWhenPositiveInt() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(123l, null);
		
		// then
		assertEquals(123, pageNumberCaptor.getValue());
	}
	
	@Test
	public void testControllerCallsZeroWhenNegativeInt() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(-123l, null);
		
		// then
		assertEquals(0, pageNumberCaptor.getValue());
	}
	
	@Test
	public void testControllerCallsPageAndSize() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture(), pageSizeCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(2l, 4l);
		
		// then
		assertEquals(2, pageNumberCaptor.getValue());
		assertEquals(4, pageSizeCaptor.getValue());
	}
	
	@Test
	public void testControllerCallsPageAndInvalidSize() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(2l, -4l);
		
		// then
		assertEquals(2, pageNumberCaptor.getValue());
	}
	
	
	
	@Test
	public void testControllerReturnsDtoFromService() {
		// given
		given(articleService.getArticle(any())).willReturn(ArticleConstants.DTO);
		
		// when
		ArticleDTO dto = controllerUnderTest.getArticleById(123l);
		
		// then
		assertEquals(dto, ArticleConstants.DTO);
	}
}
