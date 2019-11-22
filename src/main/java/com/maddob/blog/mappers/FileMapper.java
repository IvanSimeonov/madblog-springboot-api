package com.maddob.blog.mappers;

import com.maddob.blog.api.FileInfoDTO;
import com.maddob.blog.domain.File;

public interface FileMapper {
	FileInfoDTO domain2dto(File file);
}
