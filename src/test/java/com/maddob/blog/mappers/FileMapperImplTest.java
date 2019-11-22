package com.maddob.blog.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.maddob.blog.api.FileInfoDTO;
import com.maddob.blog.domain.File;

public class FileMapperImplTest {

	private FileMapper fileMapperUnderTest = new FileMapperImpl();
	
	@Test
	public void testDomain2DtoReturnsNullWhenNullPassed() {
		// when
		FileInfoDTO dto = fileMapperUnderTest.domain2dto(null);
		
		// then
		assertNull(dto);
	}
	
	@Test
	public void testDomain2DtoReturnsCorrectObject() {
		// given
		LocalDateTime testTimestamp = LocalDateTime.now();
		File f = File.builder()
				.id(1l)
				.created(testTimestamp)
				.type("TEST_FILE")
				.name("test.txt")
				.size(100l)
				.data("TEST".getBytes())
				.build();
		
		// when
		FileInfoDTO dto = fileMapperUnderTest.domain2dto(f);
		
		// then
		assertNotNull(dto);
		assertEquals(1l, dto.getId());
		assertEquals(testTimestamp, dto.getCreated());
		assertEquals("TEST_FILE", dto.getType());
		assertEquals(100l, dto.getSize());
	}
}