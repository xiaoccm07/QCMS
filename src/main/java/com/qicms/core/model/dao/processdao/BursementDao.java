package com.qicms.core.model.dao.processdao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.Bursement;
import com.qicms.core.model.entity.process.ProcessList;

public interface BursementDao extends PagingAndSortingRepository<Bursement, Long>{

	Bursement findByProId(ProcessList process);
	
	

}
