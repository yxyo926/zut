package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.Sys_Dict;

public interface ISys_DictDao {
	       //根据查询参数信息
			@Select("SELECT * FROM `sys_dict` WHERE three_type=#{id};")
			List<Sys_Dict> getDicts(Integer id) throws Exception;
			//查询所有参数信息
			@Select("select * from sci_dictpara")
			List<Sys_Dict> findAll() throws Exception;
			//根据id查对象
			@Select("select * from sys_dict where id=#{id}")
			public Sys_Dict findById(Integer id);
			@Select("select * from sci_dictpara where dictpara_kind=#{subject}")
			List<Sys_Dict> getSubject(String subject);
			@Select("select three_type from sys_dict where id=#{parseInt}")
			Integer findParentId(int parseInt);
}
