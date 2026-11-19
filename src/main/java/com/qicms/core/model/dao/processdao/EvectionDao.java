package com.qicms.core.model.dao.processdao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.Evection;
import com.qicms.core.model.entity.process.ProcessList;

public interface EvectionDao extends PagingAndSortingRepository<Evection, Long> {

	Evection findByProId(ProcessList process);

}
