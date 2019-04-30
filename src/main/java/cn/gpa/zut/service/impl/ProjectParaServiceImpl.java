package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IProjectParaDao;
import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.domain.ProjectPara;
import cn.gpa.zut.service.IProjectParaService;
@Service
@Transactional
public class ProjectParaServiceImpl implements IProjectParaService {
@Autowired
private IProjectParaDao projectDao;
	@Override
	public List<ProjectPara> findAll() throws Exception {
		// TODO Auto-generated method stub
		return projectDao.findAll();
	}

}
