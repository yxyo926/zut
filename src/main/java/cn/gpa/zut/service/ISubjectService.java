package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Subject;

public interface ISubjectService {
	List<Subject> findAll() throws Exception;
	void save(Subject subject)throws Exception;
	List<Subject> findAllById(String id);
	void update(Subject subject);
	Subject findById(String id);
}
