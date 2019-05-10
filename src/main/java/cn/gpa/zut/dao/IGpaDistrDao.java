package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.GpaDistr;

public interface IGpaDistrDao {
	@Insert("insert into sci_gpadistr(gpadistr_id,userteam_id,record_id,user_Id,userteam_profession,userteam_getGpa)"
			+ " values(#{gpadistr_id},#{userteam_id},#{record_id},#{user_Id},#{userteam_profession},#{userteam_getGpa})")
	void save(GpaDistr gpaDistr);

	// 根据条件查询参数信息
	// 缺少限定条件user_id保证唯一性
	@Select("select * from sci_gpadistr where record_id in(SELECT record_id FROM sci_record WHERE recordinfo_id=#{id})")
	public GpaDistr findById(String id) throws Exception;

	// 根据信息编号查询所有分配详情
	@Select("select * from sci_gpadistr where record_id in(SELECT record_id FROM sci_record WHERE recordinfo_id=#{id})")
	@Results({ @Result(id = true, property = "record_id", column = "record_id"),
			@Result(property = "user", column = "user_Id", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
			@Result(property = "userteam", column = "userteam_id", one = @One(select = "cn.gpa.zut.dao.IUserteamDao.findById", fetchType = FetchType.EAGER)), })
	List<GpaDistr> findAllById(String id) throws Exception;

	@Update("update sci_gpadistr set userteam_id=#{userteam_id}," + "record_id=#{record_id}," + "user_Id=#{user_Id},"
			+ "userteam_profession=#{userteam_profession},userteam_getGpa=#{userteam_getGpa}"
			+ " where gpadistr_id=#{gpadistr_id};")
	void update(GpaDistr gpaDistr);

	@Delete("delete from sci_gpadistr where gpadistr_id=#{gpadistr_id}")
	void delete(GpaDistr gpaDistr);

	@Select("select * from sci_gpadistr where record_id=#{record_Id}")
	List<GpaDistr> findAllByRecordId(String record_Id);

	// 教研
	@Select("select * from tch_gpadistr where record_id in(SELECT record_id FROM tch_record WHERE recordinfo_id=#{id})")
	GpaDistr tch_findById(String id) throws Exception;

	// 根据信息编号查询所有分配详情
	@Select("select * from tch_gpadistr where record_id in(SELECT record_id FROM tch_record WHERE recordinfo_id=#{id})")
	List<GpaDistr> tch_findAllById(String id) throws Exception;

    //教研业绩分配
	@Insert("insert into tch_gpadistr(gpadistr_id,userteam_id,record_id,user_Id,userteam_profession,userteam_getGpa)"
			+ " values(#{gpadistr_id},#{userteam_id},#{record_id},#{user_Id},#{userteam_profession},#{userteam_getGpa})")
	void tch_save(GpaDistr gpaDistr);

    //文艺业绩分配
	@Insert("insert into wy_gpadistr(gpadistr_id,userteam_id,record_id,user_Id,userteam_profession,userteam_getGpa)"
			+ " values(#{gpadistr_id},#{userteam_id},#{record_id},#{user_Id},#{userteam_profession},#{userteam_getGpa})")
	void wy_save(GpaDistr gpaDistr);
}
