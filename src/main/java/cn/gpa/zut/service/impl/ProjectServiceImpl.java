package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IProjectDao;
import cn.gpa.zut.domain.Project;
import cn.gpa.zut.service.IProjectService;

@Service
@Transactional
public class ProjectServiceImpl  implements IProjectService{
@Autowired
public IProjectDao projectDao;
	@Override
	public List<Project> findAll() throws Exception {
		// TODO Auto-generated method stub
		return projectDao.findAll();
	}

	@Override
	public void save(Project paper) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Project> findAllById(String id) {
		// TODO Auto-generated method stub
		return projectDao.findAllById(id);
	}

	@Override
	public Project findAllByPaper(String paperId) {
		// TODO Auto-generated method stub
		return null;
	}

}
