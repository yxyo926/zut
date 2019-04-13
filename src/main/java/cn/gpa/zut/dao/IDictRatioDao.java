package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.DictRatio;

public interface IDictRatioDao {

	//根据条件查询参数信息
		@Select("select * from sci_dictratio where dictratio_type_code=#{id}")
		List<DictRatio> getLev(String id) throws Exception;
		//查询所有参数信息
		@Select("select * from sci_dictratio")
		List<DictRatio> findAll() throws Exception;
}
