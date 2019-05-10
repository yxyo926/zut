package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Patent;
import cn.gpa.zut.domain.Plateform;
import cn.gpa.zut.domain.Subject;

public interface ISubjectDao {
	//查询所有信息
		@Select("select * from sci_subjectinfo")
		List<Subject> findAll() throws Exception;
		
		//保存信息
		@Insert("insert into sci_subjectinfo(subjectinfo_id,subjectinfo_name,"
				+ "subjectinfo_uname,subjectinfo_sort,subjectinfo_lev,"
				+ "subjectinfo_starttime,subjectinfo_finishtime,"
				+ "subjectinfo_checklev,subjectinfo_getGpa)"
				+ " values(#{subjectinfo_id},#{subjectinfo_name},"
				+ "#{subjectinfo_uname},#{subjectinfo_sort},#{subjectinfo_lev},"
				+ "#{subjectinfo_starttime},#{subjectinfo_finishtime},#{subjectinfo_checklev},#{subjectinfo_getGpa})")
		void save(Subject subject);
		//查询用户名下论文信息
	    //@Select("SELECT * from sci_paperinfo where paperinfo_id IN(SELECT recordinfo_id from sci_record where record_Id in( SELECT record_id FROM `sci_gpadistr`where user_Id=#{id}));")
		//查询用户名下信息
		@Select("select subjectinfo_id,subjectinfo_name,subjectinfo_uname,subjectinfo_sort,subjectinfo_lev,subjectinfo_starttime,"
				+ "subjectinfo_finishtime,subjectinfo_checklev,subjectinfo_getGpa,"
				+ "userteam_getGpa,record_status from sci_subjectinfo inner join sci_record on "
				+ "sci_subjectinfo.subjectinfo_id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
	    @Results({
	    	@Result(id = true, property = "subjectinfo_id", column = "subjectinfo_id"),
	        @Result(property = "subjectinfo_name", column = "subjectinfo_name"),
	        @Result(property = "subjectinfo_uname", column = "subjectinfo_uname"),
	        @Result(property = "subjectinfo_sort", column = "subjectinfo_sort"),
	        @Result(property = "subjectinfo_lev", column = "subjectinfo_lev"),
	        @Result(property = "subjectinfo_starttime", column = "subjectinfo_starttime"),
	        @Result(property = "subjectinfo_finishtime", column = "subjectinfo_finishtime"),
	        @Result(property = "subjectinfo_checklev", column = "subjectinfo_checklev"),
	        @Result(property = "subjectinfo_getGpa", column = "subjectinfo_getGpa"),
	    	@Result(property = "gpaDistr",column="userteam_getGpa"),
	    	@Result(property = "record_status",column="record_status"),
	    	@Result(property = "user",column="subjectinfo_uname",one=@One(select = "cn.gpa.zut.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "performance_Type",column="subjectinfo_sort",one=@One(select = "cn.gpa.zut.dao.IPerformance_typeDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Dict",column="subjectinfo_lev",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Ratio",column="subjectinfo_checklev",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)),
	    })
		List<Subject> findAllById(String id);
	    //查找学科建设详情
		@Select("select * from sci_subjectinfo where subjectinfo_id=#{id}")
		Subject findById(String id);
        @Update("update sci_subjectinfo set subjectinfo_name=#{subjectinfo_name},"
        		+ "subjectinfo_sort=#{subjectinfo_sort},"
        		+ "subjectinfo_lev=#{subjectinfo_lev},"
        		+ "subjectinfo_starttime=#{subjectinfo_starttime},subjectinfo_finishtime=#{subjectinfo_finishtime},"
        		+ "subjectinfo_checklev=#{subjectinfo_checklev},subjectinfo_getGpa=#{subjectinfo_getGpa} where subjectinfo_id=#{subjectinfo_id};")
		void update(Subject subject);
}
