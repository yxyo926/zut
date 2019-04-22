package cn.gpa.zut.dao;

import org.apache.ibatis.annotations.Insert;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Record;

public interface IRecordDao {
	@Insert("insert into sci_record(record_Id,recordinfo_id,category_Id,record_time,record_proof,record_status)"
			+ " values(#{record_Id},#{recordinfo_id},#{category_Id},#{record_time},#{record_proof},#{record_status})")
	void save(Record record);

}
