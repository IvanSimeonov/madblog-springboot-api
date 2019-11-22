package com.maddob.blog.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * File information view
 * 
 * When loading only the file metadata, this class can be used.
 * 
 * @author Martin Dobrev <martin@dobrev.eu.com>
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoView {
	private Long id;
	private String name;
	private long size;
  private String type;
  private LocalDateTime created;
}
