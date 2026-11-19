package com.qicms.core.model.dao.address;


import org.springframework.data.jpa.repository.JpaRepository;

import com.qicms.core.model.entity.note.Director;

public interface AddressDao extends JpaRepository<Director, Long> {

	//根据姓名首拼模糊查询
	
}
