package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Plateform;

public interface IPlateformService {
	List<Plateform> findAll() throws Exception;
	void save(Plateform plateform)throws Exception;
	List<Plateform> findAllById(String id);
	Plateform   findAllByPaper(String paperId);
}
