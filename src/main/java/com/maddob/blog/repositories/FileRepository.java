package com.maddob.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maddob.blog.domain.File;
import com.maddob.blog.domain.FileInfoView;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {

	@Query("SELECT new com.maddob.blog.domain.FileInfoView(f.id, f.name, f.size, f.type, f.created) FROM File f")
	List<FileInfoView> getFilesInfoView();
}

