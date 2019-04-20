package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.Conclude;

public interface IConcludeService {
	List<Conclude> findAll() throws Exception;
	void save(Conclude paper)throws Exception;
	List<Conclude> findAllById(String id);
	Conclude   findAllByPaper(String paperId);
}
