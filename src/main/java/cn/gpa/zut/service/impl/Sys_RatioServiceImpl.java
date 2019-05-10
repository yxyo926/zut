package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.ISys_RatioDao;
import cn.gpa.zut.domain.Sys_Ratio;
import cn.gpa.zut.service.ISys_RatioService;

@Service
@Transactional
public class Sys_RatioServiceImpl implements ISys_RatioService {
    @Autowired
	private ISys_RatioDao dictRatioDao;
	@Override
	public List<Sys_Ratio> findAll() throws Exception {
		
		return dictRatioDao.findAll();
	}
	//根据id查询
	@Override
	public Sys_Ratio findByid(String checklev) {
		// TODO Auto-generated method stub
		return dictRatioDao.findById(checklev);
	}
	
	//根据三级父类查询系数subject适用
	@Override
	public List<Sys_Ratio> findAllLevbyId(Integer ratioid) throws Exception {
		// TODO Auto-generated method stub
		return dictRatioDao.findAllLevbyId(ratioid);
	}
	
	//根据id查数据
	@Override
	public Sys_Ratio getByID(int pid) {
		// TODO Auto-generated method stub
		return dictRatioDao.getById(pid);
	}
	//根据父类id查数据
	@Override
	public Sys_Ratio getByPId(int pid, String name) {
		// TODO Auto-generated method stub
		return dictRatioDao.getByPId(pid, name);
	}

}
