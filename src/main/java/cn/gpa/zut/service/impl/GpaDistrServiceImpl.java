package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IGpaDistrDao;
import cn.gpa.zut.domain.GpaDistr;
import cn.gpa.zut.service.IGpaDistrService;

@Service
@Transactional
public class GpaDistrServiceImpl implements IGpaDistrService {
	@Autowired
	private IGpaDistrDao gpaDistrDao;

	@Override
	public void save(GpaDistr gpaDistr) {
		// TODO Auto-generated method stub
      gpaDistrDao.save(gpaDistr);
	}

	@Override
	public List<GpaDistr> findAllById(String Id) throws Exception {
		// TODO Auto-generated method stub
		return gpaDistrDao.findAllById(Id);
	}

	@Override
	public void update(GpaDistr gpaDistr) {
		// TODO Auto-generated method stub
		gpaDistrDao.update(gpaDistr);
	}

	@Override
	public void deletebyID(GpaDistr gpaDistr) {
		// TODO Auto-generated method stub
		gpaDistrDao.delete(gpaDistr);
	}

	@Override
	public List<GpaDistr> findAllByRecordId(String record_Id) {
		// TODO Auto-generated method stub
		return gpaDistrDao.findAllByRecordId(record_Id);
	}
	@Override
	public void wy_save(GpaDistr gpaDistr) {
		// TODO Auto-generated method stub
		gpaDistrDao.wy_save(gpaDistr);
	}
	@Override
	public void tch_save(GpaDistr gpaDistr) {
		// TODO Auto-generated method stub
		gpaDistrDao.tch_save(gpaDistr);
		
	}

}
