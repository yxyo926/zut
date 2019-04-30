package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.DictPara;

public interface IDictParaService {
	
	public List<DictPara> findAll() throws Exception;
	//获取类别
	public List<DictPara> getSort(String id) throws Exception;
	//获取类别编号
}
