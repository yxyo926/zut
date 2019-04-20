package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Writings;

public interface IWritingsService {
	List<Writings> findAll() throws Exception;
	void save(Writings paper)throws Exception;
	List<Writings> findAllById(String id);
	Writings   findAllByPaper(String paperId);
}
