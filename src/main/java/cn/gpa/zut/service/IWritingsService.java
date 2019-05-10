package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Writings;

public interface IWritingsService {
	List<Writings> findAll() throws Exception;
	void save(Writings writings)throws Exception;
	List<Writings> findAllById(String id);
	Writings   findById(String paperId);
	void update(Writings wrtings);
}
