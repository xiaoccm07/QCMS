package com.qicms.core.model.dao.processdao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.Overtime;
import com.qicms.core.model.entity.process.ProcessList;

public interface OvertimeDao extends PagingAndSortingRepository<Overtime, Long>{

	Overtime findByProId(ProcessList pro);

}
