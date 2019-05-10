package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Paper;



public interface IPaperService {
	
	List<Paper> findAll() throws Exception;
	void save(Paper paper)throws Exception;
	List<Paper> findAllById(String id);
	Paper   findByPaperId(String paperId);
	void update(Paper paper);


}
