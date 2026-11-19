package com.qicms.core.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CubeQueryMapper {

	/*按报表条件查询数据*/
	List<Map<String, Object>> flexQuery(@Param("table") String table, @Param("cols") String cols, @Param("where") String where, @Param("order") String order);
}