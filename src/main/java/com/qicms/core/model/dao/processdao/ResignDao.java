package com.qicms.core.model.dao.processdao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.ProcessList;
import com.qicms.core.model.entity.process.Resign;

public interface ResignDao extends PagingAndSortingRepository<Resign, Long>{

	Resign findByProId(ProcessList process);

}
