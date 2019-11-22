package com.maddob.blog.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maddob.blog.domain.File;
import com.maddob.blog.repositories.FileRepository;

@ExtendWith(MockitoExtension.class)
public class DBFileServiceTest {

	
	@Mock
	private FileRepository fileRepository;
	
	@InjectMocks
	private DBFileService serviceUnderTest;
	
	@Test
	public void testThatWhenFileDoesNotExistNullIsReturned() {
		// given
		when(fileRepository.existsById(any())).thenReturn(false);
		
		// when
		File file = serviceUnderTest.getFileById(2l);
		
		// then
		assertNull(file);
		verify(fileRepository, never()).findById(2l);
		verify(fileRepository, times(1)).existsById(2l);
	}
	
	@Test
	public void testThatWhenFileExistsCorrectRepositoryCallIsMade() {
			// given
			when(fileRepository.existsById(any())).thenReturn(true);
			when(fileRepository.findById(eq(2l))).thenReturn(
					Optional.of(File.builder().id(2l).build()));
			
			// when
			File file = serviceUnderTest.getFileById(2l);
			
			// then
			assertNotNull(file);
			verify(fileRepository, times(1)).findById(2l);
			verify(fileRepository, times(1)).existsById(2l);
	}
	
}
