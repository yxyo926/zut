package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.Performance_Type;

public interface IPerformance_typeDao {
	// 根据类别查询所有三级分类信息
	@Select("SELECT * FROM `performance_type` WHERE parent_id=(SELECT id FROM `performance_type` WHERE name=#{sortname});")
	List<Performance_Type> getThirdSort(String sortname) throws Exception;
	// 根据名称对应的id
	@Select("SELECT id FROM `performance_type` WHERE name=#{sortname};")
	Integer getIdByName(String sortname) throws Exception;
	// 根据id查对象
	@Select("select * from performance_type where id=#{id}")
	public Performance_Type findById(Integer id);
	//根据id和名字查id
	@Select("SELECT id FROM `performance_type` WHERE parent_id=#{arg0} and name=#{arg1};")
	public Integer getIdByParent(int id, String name);
	//根据id查询父id
	@Select("SELECT parent_id FROM `performance_type` WHERE id=#{parseInt};")
	int getpIdByid(int parseInt);
	
}
