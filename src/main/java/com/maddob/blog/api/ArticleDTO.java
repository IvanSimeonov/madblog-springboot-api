package com.maddob.blog.api;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
	
	private Long id;
	private String title;
	private String subtitle;
	private String content;
	private boolean published;
	private boolean featured;
	private LocalDateTime created;
}
