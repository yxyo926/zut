package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.Sys_Ratio;

public interface ISys_RatioDao {

	    //根据三级父类条件查询参数信息
		@Select("select * from sys_ratio where ratio_three_type=#{id}")
		List<Sys_Ratio> getLev(Integer id) throws Exception;
		//查询所有参数信息
		@Select("select * from sys_ratio")
		List<Sys_Ratio> findAll() throws Exception;
		//根据id查对象
		@Select("select * from sys_ratio where ratio_id=#{id}")
		public Sys_Ratio findById(String id);
		//根据三级父类查询sysratio
		@Select("select * from sys_ratio  where ratio_parentid=#{ratioid}")
		List<Sys_Ratio> findAllLevbyId(Integer ratioid);
		//根据id查系数
		@Select("select * from sys_ratio where ratio_id=#{pid}")
		Sys_Ratio getById(int pid);
		//根据parent_id查系数
		@Select("select * from sys_ratio where ratio_parentid=#{arg0} and ratio_name=#{arg1}")
		Sys_Ratio getByPId(int pid,String name);
}
