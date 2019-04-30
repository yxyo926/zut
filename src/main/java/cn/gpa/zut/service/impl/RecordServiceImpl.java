package cn.gpa.zut.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IRecordDao;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.service.IRecordService;
@Service
@Transactional
public class RecordServiceImpl implements IRecordService {
	@Autowired
	private IRecordDao recordDao;

	@Override
	public void save(Record record) {
		// TODO Auto-generated method stub
		recordDao.save(record);
		
	}

}
