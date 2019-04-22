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
import cn.gpa.zut.domain.Patent;

public interface IPatentDao {
	//查询所有论文信息
		@Select("select * from sci_patentinfo")
		List<Patent> findAll() throws Exception;
		
		//保存论文信息
		@Insert("insert into sci_patentinfo(patentinfo_Id,patentinfo_Inventor,patentinfo_name," 
				+ "patentinfo_sort,patentinfo_status,patentinfo_authorization,"  
				+ "patentinfo_num,patentinfoinfo_getGpa)"
				+ " values(#{patentinfo_Id},#{patentinfo_Inventor},#{patentinfo_name},"
				+ "#{patentinfo_sort},#{patentinfo_status},#{patentinfo_authorization},"
				+ "#{patentinfo_num},#{patentinfoinfo_getGpa})")
		void save(Patent patent);
		@Select("select patentinfo_Id,patentinfo_Inventor,patentinfo_name,"
				+ "patentinfo_sort,patentinfo_status,patentinfo_authorization,"
				+ "patentinfo_num,patentinfoinfo_getGpa,userteam_getGpa "
				+ "from sci_patentinfo inner join sci_record on"
				+ " sci_patentinfo.patentinfo_Id=sci_record.recordinfo_id"
				+ " INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id "
				+ "and sci_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "patentinfo_Id", column = "patentinfo_Id"),
	        @Result(property = "patentinfo_Inventor", column = "patentinfo_Inventor"),
	        @Result(property = "patentinfo_name", column = "patentinfo_name"),
	        @Result(property = "patentinfo_sort", column = "patentinfo_sort"),
	        @Result(property = "patentinfo_status", column = "patentinfo_status"),
	        @Result(property = "patentinfo_authorization", column = "patentinfo_authorization"),
	        @Result(property = "patentinfo_num", column = "patentinfo_num"),
	        @Result(property = "patentinfoinfo_getGpa", column = "patentinfoinfo_getGpa"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
		List<Patent> findAllById(String id);
	    //查找论文详情
		 public  Paper findAllByPaper(String paperId);
}
