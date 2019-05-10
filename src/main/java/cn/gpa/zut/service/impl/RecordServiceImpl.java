package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IRecordDao;
import cn.gpa.zut.dao.JY_ProjectDao;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.Record;
import cn.gpa.zut.service.IRecordService;
@Service
@Transactional
public class RecordServiceImpl implements IRecordService {
	@Autowired
	private IRecordDao recordDao;
	@Autowired
	private JY_ProjectDao projectdao;

	@Override
	public void save(Record record) {
		// TODO Auto-generated method stub
		recordDao.save(record);
		
	}

	@Override
	public Record findByInfoId(String id) {
		// TODO Auto-generated method stub
		return recordDao.findByinfoID(id);
	}

	@Override
	public void update(Record record1) {
		// TODO Auto-generated method stub
		recordDao.update(record1);
	}


	@Override
	public void tch_save(JY_Record record) throws Exception {
		// TODO Auto-generated method stub
		projectdao.Add_Record(record);
	}

	@Override
	public List<Record> findAllByUserId(String id) {
		// TODO Auto-generated method stub
		return recordDao.findAllByUserId(id);
	}

}
