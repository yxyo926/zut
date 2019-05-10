package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IPatentDao;
import cn.gpa.zut.domain.Patent;
import cn.gpa.zut.service.IPatentService;

@Service
@Transactional
public class PatentServiceImpl implements IPatentService{
@Autowired
public IPatentDao patentDao;
	@Override
	public List<Patent> findAll() throws Exception {
		// TODO Auto-generated method stub
		return patentDao.findAll();
	}

	@Override
	public void save(Patent patent) throws Exception {
		// TODO Auto-generated method stub
		patentDao.save(patent);
	}

	@Override
	public List<Patent> findAllById(String id) {
		// TODO Auto-generated method stub
		return patentDao.findAllById(id);
	}

	@Override
	public Patent findById(String paperId) {
		// TODO Auto-generated method stub
		return patentDao.findById(paperId);
	}

	@Override
	public void update(Patent patent) {
		// TODO Auto-generated method stub
		patentDao.update(patent);
	}

}
