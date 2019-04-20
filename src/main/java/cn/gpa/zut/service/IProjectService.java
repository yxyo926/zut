package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Project;

public interface IProjectService {
	List<Project> findAll() throws Exception;
	void save(Project paper)throws Exception;
	List<Project> findAllById(String id);
	Project   findAllByPaper(String paperId);
}
