package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IPaperDao;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.service.IPaperService;


@Service
@Transactional
public class PaperServiceImpl implements IPaperService {
	@Autowired
    private IPaperDao paperDao;
	
	@Override
	public void save(Paper paper) throws Exception {
		paperDao.save(paper);
		
	}
	@Override
	public List<Paper> findAll() throws Exception {
	return paperDao.findAll();
	}
	@Override
	public List<Paper> findAllById(String id) {
		// TODO Auto-generated method stub
		return paperDao.findAllById(id);
	}
	@Override
	public Paper findByPaperId(String paperId) {
		// TODO Auto-generated method stub
		return paperDao.findByPaperId(paperId);
	}
	@Override
	public void update(Paper paper) {
		// TODO Auto-generated method stub
		paperDao.update(paper);
	}
	
}
