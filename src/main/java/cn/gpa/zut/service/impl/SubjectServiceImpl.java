package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.ISubjectDao;
import cn.gpa.zut.domain.Subject;
import cn.gpa.zut.service.IProductService;
import cn.gpa.zut.service.ISubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements ISubjectService {
@Autowired
public ISubjectDao subjectDao; 
	@Override
	public List<Subject> findAll() throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.findAll();
	}

	@Override
	public void save(Subject paper) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Subject> findAllById(String id) {
		// TODO Auto-generated method stub
		return subjectDao.findAllById(id);
	}

	@Override
	public Subject findAllByPaper(String paperId) {
		// TODO Auto-generated method stub
		return null;
	}

}
