package cn.gpa.zut.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.JY_Category;
import cn.gpa.zut.domain.JY_HuoJiang;
import cn.gpa.zut.domain.JY_KeCheng;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_ZhuanYe;
import cn.gpa.zut.domain.JY_jiaogai;



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

		@Insert("insert into tch_record(record_id,record_sort,record_project_id,record_sbtime,record_state,team_id,Record_local) "
				+ "value(#{Record_id},#{record_sort},#{record_project_id},#{record_sbtime},#{state},#{team_id},#{Record_local})")
		Boolean Add_Record(JY_Record jy_record)throws Exception;
		
		
		
		@Insert("insert into tch_reforminfo(project_id,projectlev_id,reforminfo_name,reforminfo_sort,reforminfo_starttime,reforminfo_finishtime,reforminfo_place) "
				+ "value(#{reforminfo_id},#{projectlev_id},#{reforminfo_name},#{reforminfo_sort},#{reforminfo_starttime},#{reforminfo_finishtime},#{reforminfo_place})")
		Boolean Add_JiaoGai(JY_jiaogai jiaogai)throws Exception;
		
		
		@Insert("insert into tch_majorinfo(project_id,projectlev_id,majorinfo_name,majorinfo_mname,majorinfo_college,majorinfo_starttime,majorinfo_endtime,majorinfo_place) "
				+ "value(#{majorinfo_id},#{projectlev_id},#{majorinfo_name},#{majorinfo_mname},#{majorinfo_college},#{majorinfo_starttime},#{majorinfo_endtime},#{majorinfo_place})")
		Boolean Add_ZhuanYe(JY_ZhuanYe zhuanye)throws Exception;
		
		
		    
		@Insert("insert into tch_classinfo(classinfo_id,project_lev,classinfo_name,classinfo_majorname,classinfo_belongs,classinfo_starttime,classinfo_endtime,classinfo_place) "
				+ "value(#{classinfo_id},#{classinfo_lev},#{classinfo_name},#{classinfo_majorname},#{classinfo_belongs},#{classinfo_starttime},#{classinfo_endtime},#{classinfo_place})")
		Boolean Add_KeCheng(JY_KeCheng kecheng)throws Exception;
		
		@Insert("insert into tch_rewardinfo(rewardinfo_id,projectlev_id,rewardinfo_name,rewardinfo_organizename,rewardinfo_gettime,rewardinfo_place) "
				+ "value(#{rewardinfo_id},#{projectlev_id},#{rewardinfo_name},#{rewardinfo_organizename},#{rewardinfo_gettime},#{rewardinfo_place})")
		Boolean Add_HuoJiang(JY_HuoJiang huojiang)throws Exception;
		
                
}
