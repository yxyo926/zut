package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IDictParaDao;
import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.service.IDictParaService;
@Service
@Transactional
public class DictParaServiceImpl implements IDictParaService {
    @Autowired
	private  IDictParaDao dictParaDao;
    @Override
    public List<DictPara> findAll() throws Exception{
        return dictParaDao.findAll();
    }
    @Override
    public List<DictPara> getSort(String id) throws Exception{
        return dictParaDao.getSort(id);
    }
	
}
