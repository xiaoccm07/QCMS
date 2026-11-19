package com.qicms.core.model.dao.processdao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.Bursement;
import com.qicms.core.model.entity.process.DetailsBurse;

public interface DetailsBurseDao extends PagingAndSortingRepository<DetailsBurse, Long>{

	List<DetailsBurse> findByBurs(Bursement bu);
}
