package com.maddob.blog.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.maddob.blog.domain.File;
import com.maddob.blog.service.FileService;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {

	@Mock
	FileService fileService;
	
	@InjectMocks
	FileController controllerUnterTest;
	
	@Test
	public void testThatControllerReturnsErrorWhenFileNotFound() {
		// given
		given(fileService.getFileById(3l)).willReturn(null);
		
		// when
		ResponseEntity<byte[]> entity = this.controllerUnterTest.getFile(3l);
		
		// then
		assertNotNull(entity);
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
	}
	
	@Test
	public void testThatControllerReturnsFileWhenFileFound() {
		// given
		File file = File.builder()
				.id(3l)
				.name("TEST_FILE.txt")
				.type("text/plain")
				.data("THIS IS A TEST".getBytes())
				.build();
		given(fileService.getFileById(3l)).willReturn(file);
		
		// when
		ResponseEntity<byte[]> entity = this.controllerUnterTest.getFile(3l);
		
		// then
		assertNotNull(entity);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue(entity.getHeaders().get("Content-Type").contains("text/plain"));
		assertEquals(file.getData(), entity.getBody());
	}
}
