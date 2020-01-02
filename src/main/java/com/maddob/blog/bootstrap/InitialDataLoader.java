package com.maddob.blog.bootstrap;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maddob.blog.domain.Article;
import com.maddob.blog.domain.File;
import com.maddob.blog.repositories.ArticleRepository;
import com.maddob.blog.repositories.FileRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Bootstrap some data for development
 * 
 * If there are is no data in the current repositories, this component will
 * add some for testing purposes
 * 
 * 
 * @author Martin Dobrev
 */
@Component
@Slf4j
@Profile("dev")
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {
	
	private final ArticleRepository articleRepository;
	private final FileRepository fileRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public InitialDataLoader(ArticleRepository articleRepository, FileRepository fileRepository) {
		this.articleRepository = articleRepository;
		this.fileRepository = fileRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		File welcomeImage = null;
		if (fileRepository.count() == 0) {
			
			try {
				byte[] bytes = this.getClass().getClassLoader().getResourceAsStream("bootstrap/images/welcome.jpg").readAllBytes();
				welcomeImage = File.builder()
						.data(bytes)
						.name("welcome.jpg")
						.created(LocalDateTime.now())
						.size(bytes.length)
						.type(MediaType.IMAGE_JPEG_VALUE)
								.build();
				
				welcomeImage = fileRepository.save(welcomeImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (articleRepository.count() == 0) {
			try {
				Article article = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream("bootstrap/articles/welcome.json"), Article.class);
				article.setCoverImage(welcomeImage.getId());
				article = articleRepository.save(article);
				
				log.info("Saved ARTICLE ID: {}, title: {}", article.getId(), article.getTitle());
			} catch (IOException e) {
				e.printStackTrace();
				log.error("LOADING ARTICLE FAILED!!!");
			}
		}
	}
}
