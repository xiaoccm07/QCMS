package com.qicms.core.model.dao.taskdao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.qicms.core.model.entity.task.Tasklogger;

public interface TaskloggerDao extends PagingAndSortingRepository<Tasklogger, Long>{
	
	@Query("select tl from Tasklogger tl where tl.taskId.taskId=:id")
	List<Tasklogger> findByTaskId(@Param("id")Long id);
}
