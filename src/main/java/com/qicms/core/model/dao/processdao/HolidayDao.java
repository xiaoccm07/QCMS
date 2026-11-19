package com.qicms.core.model.dao.processdao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.Holiday;
import com.qicms.core.model.entity.process.ProcessList;

public interface HolidayDao extends PagingAndSortingRepository<Holiday, Long>{

	Holiday findByProId(ProcessList pro);

}
