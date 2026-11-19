package com.qicms.core.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InjectLabMapper {

	/*公告检索，按标题关键字与排序字段查询*/
	List<Map<String, Object>> noticeSearch(@Param("kw") String kw, @Param("sort") String sort);

	/*外部联系人筛选，按拼音前缀与分类过滤*/
	List<Map<String, Object>> directorFilter(@Param("pinyin") String pinyin, @Param("catalog") String catalog);

	/*人员模糊查询，按姓名与部门匹配*/
	List<Map<String, Object>> userLike(@Param("name") String name, @Param("dept") String dept);

	/*文件排序，按类型筛选并指定排序字段*/
	List<Map<String, Object>> fileOrder(@Param("type") String type, @Param("order") String order);

	/*任务条件查询，条件由页面组装*/
	List<Map<String, Object>> taskWhere(@Param("cond") String cond);

	/*分组统计，按指定字段聚合并过滤*/
	List<Map<String, Object>> statGroup(@Param("col") String col, @Param("having") String having);
}