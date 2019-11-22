package com.maddob.blog.mappers;

import com.maddob.blog.api.FileInfoDTO;
import com.maddob.blog.domain.File;

public class FileMapperImpl implements FileMapper {

	@Override
	public FileInfoDTO domain2dto(File file) {
		if (file == null) {
			return null;
		}
			
		return FileInfoDTO.builder()
				.id(file.getId())
				.name(file.getName())
				.type(file.getType())
				.size(file.getSize())
				.created(file.getCreated())
				.build();
	}

}
