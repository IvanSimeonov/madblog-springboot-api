package com.maddob.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maddob.blog.domain.File;
import com.maddob.blog.service.FileService;

@RestController
@RequestMapping("/api/v1/")
public class FileController {

	private final FileService fileService;

	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping(value = "/files/{id}")
	public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable Long id) {		
		File file = this.fileService.getFileById(id);
		if (file == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", file.getType());
		return new ResponseEntity<>(file.getData(), httpHeaders, HttpStatus.OK);
	}
}
