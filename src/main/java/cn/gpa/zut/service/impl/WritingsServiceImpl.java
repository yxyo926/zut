package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IWritingsDao;
import cn.gpa.zut.domain.Writings;
import cn.gpa.zut.service.IWritingsService;

@Service
@Transactional
public class WritingsServiceImpl implements IWritingsService{
	@Autowired
	public IWritingsDao writingsDao;

	@Override
	public List<Writings> findAll() throws Exception {
		// TODO Auto-generated method stub
		return writingsDao.findAll();
	}

	@Override
	public void save(Writings writings) throws Exception {
		// TODO Auto-generated method stub
		writingsDao.save(writings);
	}

	@Override
	public List<Writings> findAllById(String id) {
		// TODO Auto-generated method stub
		return writingsDao.findAllById(id);
	}

	@Override
	public Writings findAllByPaper(String paperId) {
		// TODO Auto-generated method stub
		return null;
	}

}
