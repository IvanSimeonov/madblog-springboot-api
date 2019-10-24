package com.maddob.blog.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maddob.blog.api.ArticlePageDTO;
import com.maddob.blog.service.ArticleService;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

	@Mock
	ArticleService articleService;
	
	@InjectMocks
	ArticleController controllerUnderTest;
	
	@Captor
	ArgumentCaptor<Integer> pageNumberCaptor;
	
	@Test
	public void testControllerCallsPageZeroWhenNoParameterSet() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(null);
		
		// then
		assertEquals(0, pageNumberCaptor.getValue());
	}
	
	@Test
	public void testControllerCallsCorrectPageWhenPositiveInt() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(123l);
		
		// then
		assertEquals(123, pageNumberCaptor.getValue());
	}
	
	@Test
	public void testControllerCallsZeroWhenNegativeInt() {
		// given
		given(articleService.getArticlePage(pageNumberCaptor.capture())).willReturn(ArticlePageDTO.builder().build());
		
		// when
		controllerUnderTest.getArticlesByPage(-123l);
		
		// then
		assertEquals(0, pageNumberCaptor.getValue());
	}
}
