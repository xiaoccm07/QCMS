package com.qicms.core.model.dao.plandao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.qicms.core.model.entity.process.EvectionMoney;

import com.qicms.core.model.entity.process.Traffic;

public interface TrafficDao extends PagingAndSortingRepository<Traffic, Long>{

	List<Traffic> findByEvection(EvectionMoney money);
}
