package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.Record;

public interface IRecordDao {
	@Insert("insert into sci_record(record_Id,recordinfo_id,category_Id,record_time,record_proof,record_status)"
			+ " values(#{record_Id},#{recordinfo_id},#{category_Id},#{record_time},#{record_proof},#{record_status})")
	void save(Record record);
	//根据记录编号查询记录数据
    @Select("select * from  sci_record where recordinfo_id=#{id}")
	Record findByinfoID(String id);
    @Update("update sci_record set record_time=#{record_time},"
    		+ "record_proof=#{record_proof},"
    		+ "record_status=#{record_status}"
    		+ " where record_Id=#{record_Id};")
	void update(Record record1);
    @Select("select * from  sci_record INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id where sci_gpadistr.user_Id=#{id}")
    @Results({ @Result(id = true, property = "record_Id", column = "record_id"),
		@Result(property = "user", column = "user_Id", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
		 })
	List<Record> findAllByUserId(String id);
    
    @Select("select * from  tch_record INNER JOIN sci_gpadistr on tch_record.record_Id=tch_gpadistr.record_id where tch_gpadistr.user_Id=#{id}")
    @Results({ @Result(id = true, property = "record_Id", column = "record_id"),
		@Result(property = "user", column = "user_Id", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
		 })
	List<JY_Record> findTchAllByUserId(String id);
    

}
