package com.maddob.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maddob.blog.domain.File;
import com.maddob.blog.repositories.FileRepository;

/**
 * 
 * File service implementation that stores files using the file repository
 * 
 * The first implementation will be a JPA repository, but other repos can be 
 * used in the future.
 * 
 * @author Martin Dobrev <martin@dobrev.eu.com>
 *
 */
@Service
public class DBFileService implements FileService {

	private final FileRepository fileRepository;

	@Autowired
	public DBFileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	@Override
	public File getFileById(long id) {
		if (!this.fileRepository.existsById(id)) {
			return null;
		}
		
		return this.fileRepository.findById(id).get();
	}

}
