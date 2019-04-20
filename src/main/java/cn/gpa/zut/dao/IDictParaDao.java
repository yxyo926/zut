package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.DictPara;

public interface IDictParaDao {

	//根据条件查询参数信息
	@Select("select * from sci_dictpara where dictpara_sort_id=#{id}")
	List<DictPara> getSort(String id) throws Exception;
	//查询所有参数信息
	@Select("select * from sci_dictpara")
	List<DictPara> findAll() throws Exception;
	//根据id查对象
	@Select("select * from sci_dictpara where dictpara_id=#{id}")
	public DictPara findById(String id);
	

}
