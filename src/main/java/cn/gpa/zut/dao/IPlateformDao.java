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
import cn.gpa.zut.domain.Project;

public interface IPlateformDao {
	//查询所有论文信息
		@Select("select * from sci_plateforminfo")
		List<Plateform> findAll() throws Exception;
		
		//保存论文信息
		@Insert("insert into sci_plateforminfo(plateforminfo_id,plateforminfo_name,"
				+ "plateforminfo_organize,plateforminfo_lev,plateforminfo_starttime,"  
			+"plateforminfo_finishtime,plateforninfo_checklev,plateforminfo_getGpa)"
				+ " values(#{plateforminfo_id},#{plateforminfo_name},#{plateforminfo_organize},"
				+ "#{plateforminfo_lev},#{plateforminfo_starttime},#{plateforminfo_finishtime},"
				+ "#{plateforninfo_checklev},#{plateforminfo_getGpa})")
		void save(Plateform plateform);
		//查询用户名下信息
				@Select("select plateforminfo_id,plateforminfo_name,plateforminfo_organize,plateforminfo_lev,plateforminfo_starttime,"
						+ "plateforminfo_finishtime,plateforninfo_checklev,plateforminfo_getGpa,"
						+ "userteam_getGpa from sci_plateforminfo inner join sci_record on "
						+ "sci_plateforminfo.plateforminfo_id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
			    @Results({
			    	@Result(id = true, property = "plateforminfo_id", column = "plateforminfo_id"),
			        @Result(property = "plateforminfo_name", column = "plateforminfo_name"),
			        @Result(property = "plateforminfo_organize", column = "plateforminfo_organize"),
			        @Result(property = "plateforminfo_lev", column = "plateforminfo_lev"),
			        @Result(property = "plateforminfo_starttime", column = "plateforminfo_starttime"),
			        @Result(property = "plateforminfo_finishtime", column = "plateforminfo_finishtime"),
			        @Result(property = "plateforninfo_checklev", column = "plateforninfo_checklev"),
			        @Result(property = "plateforminfo_getGpa", column = "plateforminfo_getGpa"),
			    	@Result(property = "gpaDistr",column="userteam_getGpa")
			    })
				List<Plateform> findAllById(String id);
	    //查找论文详情
		 public  Paper findAllByPaper(String paperId);
}
