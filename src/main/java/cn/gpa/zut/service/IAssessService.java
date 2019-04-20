package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Assess;

public interface IAssessService {
	List<Assess> findAll() throws Exception;
	void save(Assess paper)throws Exception;
	List<Assess> findAllById(String id);
	Assess   findAllByPaper(String paperId);
}
