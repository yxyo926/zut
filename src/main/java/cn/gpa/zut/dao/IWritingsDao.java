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
import cn.gpa.zut.domain.Reward;
import cn.gpa.zut.domain.Writings;

public interface IWritingsDao {
	// 查询所有信息
	@Select("select * from sci_writingsinfo")
	List<Writings> findAll() throws Exception;

	// 保存信息
	@Insert("insert into sci_writingsinfo(writingsinfo_Id,writingsinfo_Editor,writingsinfo_Name,"
			+ "writingsinfo_Press,writingsinfo_ISBN,writingsinfo_time,"
			+ "writingsinfo_wordsnum,writinginfo_org,writinginfo_lev,writinginfo_getGpa)"
			+ " values(#{writingsinfo_Id},#{writingsinfo_Editor},#{writingsinfo_Name},"
			+ "#{writingsinfo_Press},#{writingsinfo_ISBN},#{writingsinfo_time},"
			+ "#{writingsinfo_wordsnum},#{writinginfo_org},#{writinginfo_lev},#{writinginfo_getGpa})")
	void save(Writings writings);

	// 查询名下信息
	@Select("select writingsinfo_Id,writingsinfo_Editor,writingsinfo_Name,"
			+ "writingsinfo_Press,writingsinfo_ISBN,writingsinfo_time,"
			+ "writingsinfo_wordsnum,writinginfo_org,writinginfo_lev,writinginfo_getGpa,userteam_getGpa,record_status "
			+ "from sci_writingsinfo inner join sci_record on"
			+ " sci_writingsinfo.writingsinfo_Id=sci_record.recordinfo_id"
			+ " INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id "
			+ "and sci_gpadistr.user_Id=#{id}")
	@Results({ @Result(id = true, property = "writingsinfo_Id", column = "writingsinfo_Id"),
			@Result(property = "writingsinfo_Editor", column = "writingsinfo_Editor"),
			@Result(property = "writingsinfo_Name", column = "writingsinfo_Name"),
			@Result(property = "writingsinfo_Press", column = "writingsinfo_Press"),
			@Result(property = "writingsinfo_ISBN", column = "writingsinfo_ISBN"),
			@Result(property = "writingsinfo_time", column = "writingsinfo_time"),
			@Result(property = "writingsinfo_wordsnum", column = "writingsinfo_wordsnum"),
			@Result(property = "writinginfo_org", column = "writinginfo_org"),
			@Result(property = "writinginfo_lev", column = "writinginfo_lev"),
			@Result(property = "writinginfo_getGpa", column = "writinginfo_getGpa"),
			@Result(property = "gpaDistr", column = "userteam_getGpa"),
			@Result(property = "record_status", column = "record_status"),
			@Result(property = "user", column = "writingsinfo_Editor", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
			@Result(property = "sys_Dict",column="writinginfo_lev",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Ratio",column="writinginfo_org",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)), })
	List<Writings> findAllById(String id);

	// 更新
	@Update("update sci_writingsinfo set writingsinfo_Editor=#{writingsinfo_Editor}," + "writingsinfo_Name=#{writingsinfo_Name},"
			+ "writingsinfo_Press=#{writingsinfo_Press}," + "writingsinfo_ISBN=#{writingsinfo_ISBN},writingsinfo_time=#{writingsinfo_time},"
			+ "writingsinfo_wordsnum=#{writingsinfo_wordsnum},writinginfo_org=#{writinginfo_org},"
			+ "writinginfo_lev=#{writinginfo_lev},writinginfo_lev=#{writinginfo_lev} "
			+ "where writingsinfo_Id=#{writingsinfo_Id};")
	void update(Writings writings);

	// 查找单个信息
	@Select("select * from sci_writingsinfo where writingsinfo_Id=#{id}")	
	Writings findById(String id);
}
