package com.qicms.core.model.dao.notedao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.note.Director;
import com.qicms.core.model.entity.note.Note;

public interface DirectorDao  extends PagingAndSortingRepository<Director, Long>{

}
