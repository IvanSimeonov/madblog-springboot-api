package com.maddob.blog.helpers;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Test implementation of the Page interface
 * 
 * To be used in tests which need page objects 
 * 
 * @author Martin Dobrev
 *
 * @param <T>
 */
public class TestPage<T> implements Page<T> {

	private final int number;
	private final int numberOfElements;
	private final int totalPages;
	private final long totalElements;
	private final int size;
	private final List<T> content;
	
	public TestPage(final int totalPages, final long totalElements, final int number, final int size, final int numberOfElements, final List<T> content) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.size = size;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.content = content;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public int getNumberOfElements() {
		return this.numberOfElements;
	}

	@Override
	public List<T> getContent() {
		return this.content;
	}

	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFirst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalPages() {
		return this.totalPages;
	}

	@Override
	public long getTotalElements() {
		return this.totalElements;
	}

	@Override
	public <U> Page<U> map(Function<? super T, ? extends U> converter) {
		// TODO Auto-generated method stub
		return null;
	}

}
