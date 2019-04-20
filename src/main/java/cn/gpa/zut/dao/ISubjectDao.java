package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Plateform;
import cn.gpa.zut.domain.Subject;

public interface ISubjectDao {
	//查询所有论文信息
		@Select("select * from sci_paperinfo")
		List<Subject> findAll() throws Exception;
		
		//保存论文信息
		@Insert("insert into sci_paperinfo(paperinfo_Id,paperinfo_Author,paperinfo_Name,paperinfo_CN,paperinfo_ISSN,paperinfo_Time,paperinfo_Lev,paperinfo_orglev,paperinfo_getGpa)"
				+ " values(#{paperinfo_Id},#{paperinfo_Author},#{paperinfo_Name},#{paperinfo_CN},#{paperinfo_ISSN},#{paperinfo_Time},#{paperinfo_Lev},#{paperinfo_orglev},#{paperinfo_getGpa})")
		void save(Paper paper);
		//查询用户名下论文信息
	    //@Select("SELECT * from sci_paperinfo where paperinfo_id IN(SELECT recordinfo_id from sci_record where record_Id in( SELECT record_id FROM `sci_gpadistr`where user_Id=#{id}));")
		//查询用户名下信息
		@Select("select subjectinfo_id,subjectinfo_name,subjectinfo_uname,subjectinfo_starttime,"
				+ "subjectinfo_finishtime,subjectinfo_checklev,subjectinfo_getGpa,"
				+ "userteam_getGpa from sci_subjectinfo inner join sci_record on "
				+ "sci_subjectinfo.subjectinfo_id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "subjectinfo_id", column = "subjectinfo_id"),
	        @Result(property = "subjectinfo_name", column = "subjectinfo_name"),
	        @Result(property = "subjectinfo_uname", column = "subjectinfo_uname"),
	        @Result(property = "subjectinfo_starttime", column = "subjectinfo_starttime"),
	        @Result(property = "subjectinfo_finishtime", column = "subjectinfo_finishtime"),
	        @Result(property = "subjectinfo_checklev", column = "subjectinfo_checklev"),
	        @Result(property = "subjectinfo_getGpa", column = "subjectinfo_getGpa"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
		List<Subject> findAllById(String id);
	    //查找论文详情
		 public  Paper findAllByPaper(String paperId);
}
