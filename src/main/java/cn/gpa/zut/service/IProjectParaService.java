package cn.gpa.zut.service;

import java.util.List;


import cn.gpa.zut.domain.ProjectPara;

public interface IProjectParaService {
	
	public List<ProjectPara> findAll() throws Exception;
}
