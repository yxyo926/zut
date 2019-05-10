package cn.gpa.zut.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gpa.zut.dao.IPerformance_typeDao;
import cn.gpa.zut.domain.Performance_Type;
import cn.gpa.zut.service.IPerformance_typeService;
@Service
@Transactional
public class Performance_typeServiceImpl implements IPerformance_typeService {
@Autowired
private IPerformance_typeDao performanceDao;
	@Override
	public List<Performance_Type> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Performance_Type> getThirdSort(String sortname) throws Exception {
		// TODO Auto-generated method stub
		return performanceDao.getThirdSort(sortname);
	}
   //根据名称获取对应id
	@Override
	public Integer getIdByName(String string) throws Exception {
		// TODO Auto-generated method stub
		return performanceDao.getIdByName(string);
	}

	@Override
	public Integer getIdByParent(Integer parentid, String name) {
		// TODO Auto-generated method stub
		return performanceDao.getIdByParent(parentid,name);
	}

	@Override
	public int getpIdByid(String id) {
		// TODO Auto-generated method stub
		return performanceDao.getpIdByid(Integer.parseInt(id));
	}

}
