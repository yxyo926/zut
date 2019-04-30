package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.domain.JY_pingtai;


public interface Jy_PingTaiService {
	
	List<JY_ProjectLev> findLevByCategoryID() throws Exception;
	
	void Add_pingtai(JY_pingtai jy_pt)throws Exception;

	List<JY_pingtai> findpingtaiById(String userId);

}
