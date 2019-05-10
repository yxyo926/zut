package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Patent;

public interface IConcludeService {
	List<Conclude> findAll() throws Exception;
	void save(Conclude conclude)throws Exception;
	List<Conclude> findAllById(String id);
	Conclude   findById(String paperId);
	void update(Conclude conclude);

}
