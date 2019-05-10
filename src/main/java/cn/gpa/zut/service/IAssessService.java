package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.Patent;

public interface IAssessService {
	List<Assess> findAll() throws Exception;
	void save(Assess assess)throws Exception;
	List<Assess> findAllById(String id);
	Assess   findById(String paperId);
	void update(Assess assess);

}
