package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.domain.Subject;

public interface IAssessDao {
	//查询所有论文信息
		@Select("select * from sci_assessinfo")
		List<Assess> findAll() throws Exception;
		
		//保存信息
				@Insert("insert into sci_subjectinfo(subjectinfo_id,subjectinfo_name,"
						+ "subjectinfo_uname,subjectinfo_sort,subjectinfo_lev,"
						+ "subjectinfo_starttime,subjectinfo_finishtime,"
						+ "subjectinfo_checklev,subjectinfo_getGpa)"
						+ " values(#{subjectinfo_id},#{subjectinfo_name},"
						+ "#{subjectinfo_uname},#{subjectinfo_sort},#{subjectinfo_lev},"
						+ "#{subjectinfo_starttime},#{subjectinfo_finishtime},#{subjectinfo_getGpa})")
				void save(Assess assess);
		//查询用户名下信息
				@Select("select assessinfo_id,assessinfo_aname,assessinfo_rname,assessinfo_person,assessinfo_getGpa,userteam_getGpa  from sci_assessinfo inner join sci_record on" + 
						" sci_assessinfo.assessinfo_id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
			    @Results({
			    	@Result(id = true, property = "assessinfo_id", column = "assessinfo_id"),
			        @Result(property = "assessinfo_aname", column = "assessinfo_aname"),
			        @Result(property = "assessinfo_rname", column = "assessinfo_rname"),
			        @Result(property = "assessinfo_person", column = "assessinfo_person"),
			        @Result(property = "assessinfo_getGpa", column = "assessinfo_getGpa"),
			    	@Result(property = "gpaDistr",column="userteam_getGpa")
			    })
				List<Assess> findAllById(String id);
	    //查找论文详情
		 public  Assess findAllGpa(String paperId);
}
