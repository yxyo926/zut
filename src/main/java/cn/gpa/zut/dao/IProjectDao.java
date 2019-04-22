package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Project;

public interface IProjectDao {
	//查询所有信息
		@Select("select * from sci_projectinfo")
		List<Project> findAll() throws Exception;
		
		//保存信息
		@Insert("insert into sci_projectinfo(projectinfo_Id,projectinfo_origin,"
				+ "projectinfo_Name,projectinfo_Leader,projectinfo_StartTime,"
				+ "projectinfo_FinishTime,projectinfo_StartMoney,projectinfo_getGpa)"
				+ " values(#{projectinfo_Id},#{projectinfo_origin},#{projectinfo_Name},"
				+ "#{projectinfo_Leader},#{projectinfo_StartTime},#{projectinfo_FinishTime},"
				+ "#{projectinfo_StartMoney},#{projectinfo_getGpa})")
		void save(Project project);
		//查询用户名下信息
		@Select("select projectinfo_Id,projectinfo_origin,projectinfo_Name,projectinfo_Leader,projectinfo_StartTime,projectinfo_FinishTime,projectinfo_StartMoney,projectinfo_getGpa,userteam_getGpa from sci_projectinfo inner join sci_record on sci_projectinfo.projectinfo_Id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "projectinfo_Id", column = "projectinfo_Id"),
	        @Result(property = "projectinfo_origin", column = "projectinfo_origin"),
	        @Result(property = "projectinfo_Name", column = "projectinfo_Name"),
	        @Result(property = "projectinfo_Leader", column = "projectinfo_Leader"),
	        @Result(property = "projectinfo_StartTime", column = "projectinfo_StartTime"),
	        @Result(property = "projectinfo_FinishTime", column = "projectinfo_FinishTime"),
	        @Result(property = "projectinfo_StartMoney", column = "projectinfo_StartMoney"),
	        @Result(property = "projectinfo_getGpa", column = "projectinfo_getGpa"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
		List<Project> findAllById(String id);
		
		//
		 public  Paper findAllByPaper(String paperId);

}
