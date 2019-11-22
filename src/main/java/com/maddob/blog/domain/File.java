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
 * 
 * File entity
 * 
 * Files will be stored in the database. This entity will
 * be responsible for saving both the metadata and the actual bytes 
 * of each file.
 * 
 * @author Martin Dobrev <martin@dobrev.eu.com>
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
  private String name;

  @Lob
  private byte[] data;

  private long size;
  private String type;
  private LocalDateTime created;
}
