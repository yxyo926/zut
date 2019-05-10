package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.domain.Paper;

public interface IGpaDistrService {

	void save(GpaDistr gpaDistr);
	List<GpaDistr> findAllById(String infoId) throws Exception;
	void update(GpaDistr gpaDistr);
	void deletebyID(GpaDistr gpaDistr);
	List<GpaDistr> findAllByRecordId(String record_Id);
    void tch_save(GpaDistr gpaDistr);
	void wy_save(GpaDistr gpaDistr);
	
	

	

}
