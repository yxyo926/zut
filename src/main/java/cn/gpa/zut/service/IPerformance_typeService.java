package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Performance_Type;

public interface IPerformance_typeService {
	public List<Performance_Type> findAll() throws Exception;
	//获取三级分类
	public List<Performance_Type> getThirdSort(String sortname) throws Exception;
	//
	public Integer getIdByName(String string) throws Exception;
	public Integer getIdByParent(Integer parentid, String name);
	public int getpIdByid(String id);
}
