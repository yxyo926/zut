package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.domain.Subject;

public interface IAssessDao {
	// 查询所有论文信息
	@Select("select * from sci_assessinfo")
	List<Assess> findAll() throws Exception;

	// 保存信息
	@Insert("insert into sci_assessinfo(assessinfo_id,assessinfo_aname,"
			+ "assessinfo_rname,assessinfo_person,assessinfo_getGpa," + "assessinfo_time)"
			+ " values(#{assessinfo_id},#{assessinfo_aname},"
			+ "#{assessinfo_rname},#{assessinfo_person},#{assessinfo_getGpa}," + "#{assessinfo_time})")
	void save(Assess assess);
	
	//更新
	@Update("update sci_assessinfo set assessinfo_aname=#{assessinfo_aname},"
     		+ "assessinfo_rname=#{assessinfo_rname},"
     		+ "assessinfo_person=#{assessinfo_person},"
     		+ "assessinfo_getGpa=#{assessinfo_getGpa},assessinfo_time=#{assessinfo_time}"
     		+ "where assessinfo_id=#{assessinfo_id};")
	void update(Assess assess);

	// 查询用户名下信息
	@Select("select assessinfo_id,assessinfo_aname,assessinfo_rname,assessinfo_person,"
			+ "assessinfo_getGpa,assessinfo_time,userteam_getGpa,record_status  from sci_assessinfo inner join sci_record on"
			+ " sci_assessinfo.assessinfo_id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on "
			+ "sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
	@Results({ @Result(id = true, property = "assessinfo_id", column = "assessinfo_id"),
			@Result(property = "assessinfo_aname", column = "assessinfo_aname"),
			@Result(property = "assessinfo_rname", column = "assessinfo_rname"),
			@Result(property = "assessinfo_person", column = "assessinfo_person"),
			@Result(property = "assessinfo_getGpa", column = "assessinfo_getGpa"),
			@Result(property = "gpaDistr", column = "userteam_getGpa"),
			@Result(property = "record_status", column = "record_status"),
			@Result(property = "user", column = "assessinfo_person", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
			//@Result(property = "performance_Type",column="subjectinfo_sort",one=@One(select = "cn.gpa.zut.dao.IPerformance_typeDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Dict",column="assessinfo_aname",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
	    	//@Result(property = "sys_Ratio",column="patentinfo_num",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "project",column="assessinfo_rname",one=@One(select = "cn.gpa.zut.dao.IProjectDao.findById",fetchType = FetchType.EAGER)),

	})
	List<Assess> findAllById(String id);

	// 查找单个信息
	@Select("select * from sci_assessinfo where assessinfo_id=#{id}")
	Assess findById(String id);
}
