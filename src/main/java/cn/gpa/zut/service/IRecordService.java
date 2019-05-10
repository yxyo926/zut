package cn.gpa.zut.service;


import java.util.List;

import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.Record;

public interface IRecordService {

	void save(Record record);

	Record findByInfoId(String id);

	void update(Record record1);
	void tch_save(JY_Record record)throws Exception;

	List<Record> findAllByUserId(String id);
}
