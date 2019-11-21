package com.maddob.blog.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.maddob.blog.controllers.ArticleController;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * SpringFox configuration
 * 
 * Generates Swagger definitions for the Madblog API!
 * 
 * The definitions will be used to create frontend and mobile clients...
 * Probably someday different server implementations. This will provide a good basis for
 * comparing different frameworks 
 * 
 * @author Martin Dobrev <martin@dobrev.eu.com>
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {
		ArticleController.class
})
public class SpringFoxConfig {
	
	@Autowired
	BuildProperties buildProperties;
	
	@Bean
	public Docket maddobDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Madblog REST API",
			description,
			buildProperties.getVersion(),
			"TERMS_OF_SERVICE",
			new Contact("Martin Dobrev", "https://maddob.com", "martin@dobrev.eu.com"),
			"MIT",
			"http://licenceurl.here",
			Collections.emptyList()
		);
	}

	private final static String description = "This is a simple webpage and blog API. "
			+ "It is a part of the madblog development tutorial. "
			+ "The tutorial series will show how to build and test a simple website with an integrated blog. "
			+ "TODO: Add details as the tutorial is being built"
			+ "For more information checkout https://maddob.com/tutorial";
	
	
}
