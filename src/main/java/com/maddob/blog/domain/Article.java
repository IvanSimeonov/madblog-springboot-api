package com.maddob.blog.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a basic Madblog article
 * 
 * Content will be stored in Markdown
 * 
 * @author Martin Dobrev
 *
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String title;
	
	@NotNull
	private String subtitle;
	
	@Lob
	@NotNull
	private String content;
	
	private Long coverImage;
	
	private boolean published;
	private boolean featured;
	private LocalDateTime created;
	
}
