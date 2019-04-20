package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Paper;

public interface IGpaDistrService {

	void save(GpaDistr gpaDistr);
	List<GpaDistr> findAllById(String paperId) throws Exception;

	
	

	

}
