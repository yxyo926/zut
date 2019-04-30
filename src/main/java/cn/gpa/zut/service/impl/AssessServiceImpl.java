package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IAssessDao;
import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.service.IAssessService;
@Service
@Transactional
public class AssessServiceImpl implements IAssessService {
	@Autowired
	public IAssessDao assessDao;

	@Override
	public List<Assess> findAll() throws Exception {
		// TODO Auto-generated method stub
		return assessDao.findAll();
	}

	@Override
	public void save(Assess assess) throws Exception {
		// TODO Auto-generated method stub
		assessDao.save(assess);
	}

	@Override
	public List<Assess> findAllById(String id) {
		// TODO Auto-generated method stub
		return assessDao.findAllById(id);
	}

	@Override
	public Assess findAllByPaper(String paperId) {
		// TODO Auto-generated method stub
		return null;
	}

}
