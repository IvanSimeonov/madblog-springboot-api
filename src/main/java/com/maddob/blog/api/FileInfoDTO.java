package com.maddob.blog.api;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileInfoDTO {
	private long id;
	private String name;
	private long size;
  private String type;
  private LocalDateTime created;
}
