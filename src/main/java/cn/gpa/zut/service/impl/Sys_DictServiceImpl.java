package cn.gpa.zut.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gpa.zut.dao.ISys_DictDao;
import cn.gpa.zut.domain.Sys_Dict;
import cn.gpa.zut.service.ISys_DictService;
@Service
@Transactional
public class Sys_DictServiceImpl implements ISys_DictService {
@Autowired
private ISys_DictDao sys_dictDao;
	@Override
	public List<Sys_Dict> getDicts(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return sys_dictDao.getDicts(id);
	}
	@Override
	public Sys_Dict findByid(String string) {
		// TODO Auto-generated method stub
		return sys_dictDao.findById(Integer.parseInt(string));
	}
	@Override
	public Integer getParentId(String subjectinfo_lev) {
		// TODO Auto-generated method stub
		return sys_dictDao.findParentId(Integer.parseInt(subjectinfo_lev));
	}

}
