package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IDictRatioDao;
import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.service.IDictRatioService;

@Service
@Transactional
public class DictRatioServiceImpl implements IDictRatioService {
    @Autowired
	private IDictRatioDao dictRatioDao;
	@Override
	public List<DictRatio> findAll() throws Exception {
		
		return dictRatioDao.findAll();
	}
	@Override
	public List<DictRatio> getLev(String id) throws Exception {
		// TODO Auto-generated method stub
		return dictRatioDao.getLev(id);
	}

}
