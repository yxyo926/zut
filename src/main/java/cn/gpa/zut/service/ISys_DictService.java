package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Performance_Type;
import cn.gpa.zut.domain.Sys_Dict;

public interface ISys_DictService {
	//获取三级分类
		public List<Sys_Dict> getDicts(Integer id) throws Exception;
		//获取分类对象
		public Sys_Dict findByid(String string);
		public Integer getParentId(String subjectinfo_lev);
}
