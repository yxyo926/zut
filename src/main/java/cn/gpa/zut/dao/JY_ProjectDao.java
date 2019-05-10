package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.JY_Category;
import cn.gpa.zut.domain.JY_HuoJiang;
import cn.gpa.zut.domain.JY_KeCheng;
import cn.gpa.zut.domain.JY_Lunwen;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_ZhuanYe;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.domain.Paper;



public interface JY_ProjectDao {
	
	
	//查询所有的教研项目分类：教改、专业等
		@Select("select * from tch_category")
		List<JY_Category> findAll_Category() throws Exception;
		
		 
		@Select("select * from tch_projectlev where tch_category_id='${category_id}' ")
		public List<JY_ProjectLev> findLevBycategory(@Param("category_id") String category_id) throws Exception;
		
		@Select("select projectlev_GPA from tch_projectlev where project_id='${project_id}' ")
		public double findgpaByprojectId(@Param("project_id")String project_id)throws Exception;
		
		@Select("select projectlev_khgpa from tch_projectlev where project_id='${project_id}' ")
		public double findKhgpaByprojectId(@Param("project_id")String project_id)throws Exception;

		@Insert("insert into tch_record(record_id,record_sort,record_project_id,record_sbtime,record_state,record_local,record_piont) "
				+ "value(#{record_id},#{record_sort},#{record_project_id},#{record_sbtime},#{state},#{Record_local},#{record_piont})")
		Boolean Add_Record(JY_Record jy_record)throws Exception;
		
		
		
		
		@Insert("insert into tch_reforminfo(project_id,projectlev_id,reforminfo_name,reforminfo_sort,reforminfo_starttime,reforminfo_finishtime,reforminfo_place) "
				+ "value(#{reforminfo_id},#{projectlev_id},#{reforminfo_name},#{reforminfo_sort},#{reforminfo_starttime},#{reforminfo_finishtime},#{reforminfo_place})")
		Boolean Add_JiaoGai(JY_jiaogai jiaogai)throws Exception;
		
		
		@Insert("insert into tch_majorinfo(project_id,projectlev_id,majorinfo_name,majorinfo_mname,majorinfo_college,majorinfo_starttime,majorinfo_endtime,majorinfo_place) "
				+ "value(#{project_id},#{projectlev_id},#{majorinfo_name},#{majorinfo_mname},#{majorinfo_college},#{majorinfo_starttime},#{majorinfo_endtime},#{majorinfo_place})")
		Boolean Add_ZhuanYe(JY_ZhuanYe zhuanye)throws Exception;
		
		
		    
		@Insert("insert into tch_classinfo(classinfo_id,project_lev,classinfo_name,classinfo_majorname,classinfo_belongs,classinfo_starttime,classinfo_endtime,classinfo_place) "
				+ "value(#{classinfo_id},#{classinfo_lev},#{classinfo_name},#{classinfo_majorname},#{classinfo_belongs},#{classinfo_starttime},#{classinfo_endtime},#{classinfo_place})")
		Boolean Add_KeCheng(JY_KeCheng kecheng)throws Exception;
		
		@Insert("insert into tch_rewardinfo(rewardinfo_id,projectlev_id,rewardinfo_name,rewardinfo_organizename,rewardinfo_gettime,rewardinfo_place) "
				+ "value(#{rewardinfo_id},#{projectlev_id},#{rewardinfo_name},#{rewardinfo_organizename},#{rewardinfo_gettime},#{rewardinfo_place})")
		Boolean Add_HuoJiang(JY_HuoJiang huojiang)throws Exception;
		
		@Insert("insert into tch_paperinfo(paperinfo_Id,paperinfo_Author,paperinfo_Name,paperinfo_ISSN,paperinfo_Time,paperinfo_Lev,paperinfo_orglev)"
				+ " values(#{paperinfo_id},#{paperinfo_author},#{paperinfo_name},#{paperinfo_ISSN},#{paperinfo_time},#{project_id},#{rewardinfo_organizename})")
		Boolean Add_LunWen(JY_Lunwen lunwen)throws Exception;
		
		
		
		
		//查询名下信息
		@Select("select paperinfo_Id,paperinfo_Author,paperinfo_Name,"
				+ "paperinfo_ISSN,paperinfo_Time,paperinfo_Lev,paperinfo_orglev, record_piont,userteam_getGpa from tch_paperinfo inner join tch_record "
				+ "on tch_paperinfo.paperinfo_Id=tch_record.record_project_id INNER JOIN "
				+ "tch_gpadistr on tch_record.record_id=tch_gpadistr.record_id and tch_gpadistr.user_Id=#{id}")
		@Results({
	    	@Result(id = true, property = "paperinfo_id", column = "paperinfo_id"),
//	        @Result(property = "project_id", column = "project_id"),
	        @Result(property = "paperinfo_name", column = "paperinfo_name"),
	        @Result(property = "paperinfo_author", column = "paperinfo_author"),
	        @Result(property = "paperinfo_ISSN", column = "paperinfo_ISSN"),
	        @Result(property = "record_piont", column = "record_piont"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
		List<JY_Lunwen> findLunwenById(String id); 
		
		@Select("select classinfo_id,project_lev,classinfo_name,classinfo_majorname,"			
				+ "classinfo_belongs,classinfo_starttime,classinfo_endtime,classinfo_place,record_piont,userteam_getGpa"
				+ " from tch_classinfo inner join tch_record on " + 
				"tch_classinfo.classinfo_id=tch_record.record_project_id INNER JOIN tch_gpadistr on "
				+ "tch_record.record_id=tch_gpadistr.record_id and tch_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "classinfo_id", column = "classinfo_id"),
	        @Result(property = "classinfo_lev", column = "project_lev"),
	        @Result(property = "classinfo_name", column = "classinfo_name"),
	        @Result(property = "classinfo_majorname", column = "classinfo_majorname"),
	        @Result(property = "classinfo_belongs", column = "classinfo_belongs"),
	        @Result(property = "classinfo_starttime", column = "classinfo_starttime"),
	        @Result(property = "classinfo_endtime", column = "classinfo_endtime"),
	        @Result(property = "classinfo_place", column = "classinfo_place"),
	        @Result(property = "record_piont", column = "record_piont"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
		List<JY_KeCheng> findKechengById(String id); 
		
		@Select("select rewardinfo_id,rewardinfo_name,projectlev_id,rewardinfo_gettime,"		
			+ "rewardinfo_organizename ,rewardinfo_place,record_piont,userteam_getGpa"
		        + " from tch_rewardinfo inner join tch_record on" + 
			" tch_rewardinfo.rewardinfo_id=tch_record.record_project_id INNER JOIN tch_gpadistr on "
			+ "tch_record.record_id=tch_gpadistr.record_id and tch_gpadistr.user_Id=#{id}")
       @Results({
    	@Result(id = true, property = "rewardinfo_id", column = "rewardinfo_id"),
        @Result(property = "rewardinfo_name", column = "rewardinfo_name"),
        @Result(property = "projectlev_id", column ="projectlev_id"),
        @Result(property = "rewardinfo_gettime", column = "rewardinfo_gettime"),
        @Result(property = "rewardinfo_organizename", column = "rewardinfo_organizename"),
        @Result(property = "rewardinfo_place", column = "rewardinfo_place"),
        @Result(property = "record_piont", column = "record_piont"),
    	@Result(property = "gpaDistr",column="userteam_getGpa")
        })
		List<JY_HuoJiang> findHuojiangById(String id); 
		
		@Select("select project_id,projectlev_id,reforminfo_person,reforminfo_name,"		
			+ "reforminfo_sort, reforminfo_starttime,reforminfo_finishtime,"
			+"reforminfo_place,reforminfo_getGpa,record_piont,userteam_getGpa"
		        + " from tch_reforminfo inner join tch_record on " + 
			"tch_reforminfo.project_id=tch_record.record_project_id INNER JOIN tch_gpadistr on "
			+ "tch_record.record_id=tch_gpadistr.record_id and tch_gpadistr.user_Id=#{id}")
    @Results({
    	@Result(id = true, property = "reforminfo_id", column = "project_id"),
        @Result(property = "projectlev_id", column = "projectlev_id"),
        @Result(property = "userteam_id", column ="reforminfo_person"),
        @Result(property = "reforminfo_name", column = "reforminfo_name"),
        @Result(property = "reforminfo_starttime", column = "reforminfo_starttime"),
        @Result(property = "reforminfo_finishtime", column = "reforminfo_finishtime"),
        @Result(property = "reforminfo_place", column = "reforminfo_place"),
        @Result(property = "record_piont", column = "record_piont"),
    	@Result(property = "gpaDistr",column="userteam_getGpa")
    })
		List<JY_jiaogai> findJiaogaiById(String id); 
		
		@Select("select project_id,projectlev_id,majorinfo_name,majorinfo_person,"		
				+ "majorinfo_mname,majorinfo_college,majorinfo_starttime,"
				+"majorinfo_endtime,majorinfo_place,record_piont,userteam_getGpa"
			        + " from tch_majorinfo inner join tch_record on " + 
				"tch_majorinfo.project_id=tch_record.record_project_id INNER JOIN tch_gpadistr on "
				+ "tch_record.record_id=tch_gpadistr.record_id and tch_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "project_id", column = "project_id"),
	        @Result(property = "projectlev_id", column = "projectlev_id"),
	        @Result(property = "majorinfo_name", column ="majorinfo_name"),
	        @Result(property = "majorinfo_person", column = "majorinfo_person"),
	        @Result(property = "majorinfo _college", column = "majorinfo _college "),
	        @Result(property = "majorinfo _starttime", column = "majorinfo _starttime"),
	        @Result(property = "majorinfo _endtime", column = "majorinfo _endtime"),
	        @Result(property = "majorinfo _place", column = "majorinfo _place"),
	        @Result(property = "record_piont", column = "record_piont"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa")
	    })
			List<JY_ZhuanYe> findZhuanyeById(String id); 


		
}
