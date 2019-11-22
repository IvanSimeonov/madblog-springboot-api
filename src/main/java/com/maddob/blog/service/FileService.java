package com.maddob.blog.service;

import com.maddob.blog.domain.File;

/**
 * 
 * FileService interface
 * 
 * Defines the basic file storage contract
 * Feel free to implement different file storage strategies :)
 * 
 * @author Martin Dobrev <martin@dobrev.eu.com>
 *
 */
public interface FileService {

	File getFileById(long id);	
}
