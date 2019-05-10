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
import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Paper;

public interface IConcludeDao {
	// 查询所有论文信息
	@Select("select * from sci_concludeinfo")
	List<Conclude> findAll() throws Exception;
	
	// 保存论文信息
	@Insert("insert into sci_concludeinfo(concludeinfo_id,concludeinfo_name,concludeinfo_person"
			+ ",concludeinfo_olev,concludeinfo_lev,concludetinfo_getGpa)"
			+ " values(#{concludeinfo_id},#{concludeinfo_name},#{concludeinfo_person},#{concludeinfo_olev},#{concludeinfo_lev},"
			+ "#{concludetinfo_getGpa})")
	void save(Conclude conclude);
	//更新
			@Update("update sci_concludeinfo set concludeinfo_name=#{concludeinfo_name},"
		     		+ "concludeinfo_person=#{concludeinfo_person},"
		     		+ "concludeinfo_olev=#{concludeinfo_olev},"
		     		+ "concludeinfo_lev=#{concludeinfo_lev},concludetinfo_getGpa=#{concludetinfo_getGpa}"
		     		+ "where concludeinfo_id=#{concludeinfo_id};")
			void update(Conclude conclude);

	@Select("select concludeinfo_id,concludeinfo_name,concludeinfo_person,"
			+ "concludeinfo_olev,concludeinfo_lev,concludetinfo_getGpa,userteam_getGpa,record_status"
			+ " from sci_concludeinfo inner join sci_record on"
			+ " sci_concludeinfo.concludeinfo_id=sci_record.recordinfo_id"
			+ " INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id "
			+ "and sci_gpadistr.user_Id=#{id}")
	@Results({ @Result(id = true, property = "concludeinfo_id", column = "concludeinfo_id"),
			@Result(property = "concludeinfo_name", column = "concludeinfo_name"),
			@Result(property = "concludeinfo_person", column = "concludeinfo_person"),
			@Result(property = "concludeinfo_olev", column = "concludeinfo_olev"),
			@Result(property = "concludeinfo_lev", column = "concludeinfo_lev"),
			@Result(property = "concludetinfo_getGpa", column = "concludetinfo_getGpa"),
			@Result(property = "gpaDistr", column = "userteam_getGpa"),
			@Result(property = "record_status", column = "record_status"),
			@Result(property = "user", column = "concludeinfo_person", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
			//@Result(property = "performance_Type",column="subjectinfo_sort",one=@One(select = "cn.gpa.zut.dao.IPerformance_typeDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Dict",column="concludeinfo_lev",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Ratio",column="concludeinfo_olev",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "project",column="concludeinfo_name",one=@One(select = "cn.gpa.zut.dao.IProjectDao.findById",fetchType = FetchType.EAGER)),
	    	})
	List<Conclude> findAllById(String id);

	// 查找单个信息
		@Select("select * from sci_concludeinfo where concludeinfo_id=#{id}")
	Conclude findById(String id);
}
