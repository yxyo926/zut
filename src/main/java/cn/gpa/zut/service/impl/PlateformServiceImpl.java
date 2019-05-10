package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.gpa.zut.dao.IPlateformDao;

import cn.gpa.zut.domain.Plateform;

import cn.gpa.zut.service.IPlateformService;

@Service
@Transactional
public class PlateformServiceImpl  implements IPlateformService{
@Autowired 
public IPlateformDao plateformDao;

	@Override
	public void save(Plateform plateform) throws Exception {
		// TODO Auto-generated method stub
		plateformDao.save(plateform);
	}

	@Override
	public Plateform findById(String id) {
		// TODO Auto-generated method stub
		return plateformDao.findById(id);
	}

	@Override
	public List<Plateform> findAll() throws Exception {
		// TODO Auto-generated method stub
		return plateformDao.findAll();
	}

	@Override
	public List<Plateform> findAllById(String id) {
		// TODO Auto-generated method stub
		return plateformDao.findAllById(id);
	}

	@Override
	public void update(Plateform plateform) {
		// TODO Auto-generated method stub
		plateformDao.update(plateform);
	}

}
