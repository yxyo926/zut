package cn.gpa.zut.dao;

import org.apache.ibatis.annotations.Insert;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Record;

public interface IRecordDao {
	@Insert("insert into sci_record(gpadistr_id,userteam_id,record_id,user_Id,userteam_profession,userteam_getGpa)"
			+ " values(#{gpadistr_id},#{userteam_id},#{record_id},#{user_Id},#{userteam_profession},#{userteam_getGpa})")
	void save(Record record);

}
