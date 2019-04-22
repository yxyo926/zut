package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Patent;

public interface IPatentService {
	List<Patent> findAll() throws Exception;
	void save(Patent patent)throws Exception;
	List<Patent> findAllById(String id);
	Patent   findAllByPaper(String paperId);
}

