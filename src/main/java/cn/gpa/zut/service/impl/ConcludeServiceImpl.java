package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IConcludeDao;
import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.service.IConcludeService;

@Service
@Transactional
public class ConcludeServiceImpl implements IConcludeService {
	@Autowired 
	public IConcludeDao concludeDao;

	@Override
	public List<Conclude> findAll() throws Exception {
		// TODO Auto-generated method stub
		return concludeDao.findAll();
	}

	@Override
	public void save(Conclude conclude) throws Exception {
		// TODO Auto-generated method stub
		concludeDao.save(conclude);
	}

	@Override
	public List<Conclude> findAllById(String id) {
		// TODO Auto-generated method stub
		return concludeDao.findAllById(id);
	}

	@Override
	public Conclude findAllByPaper(String paperId) {
		// TODO Auto-generated method stub
		return null;
	}

}
