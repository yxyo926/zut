package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.Jy_PingTaiDao;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_pingtai;
import cn.gpa.zut.service.Jy_PingTaiService;

@Service
@Transactional
public class Jy_PingTaiServiceImpl implements Jy_PingTaiService {
	@Autowired
	private Jy_PingTaiDao jy_pingtai;

	public List<JY_ProjectLev> findLevByCategoryID() throws Exception {
		// TODO Auto-generated method stub
		return jy_pingtai.findLevBycategory();
	}

	@Override
	public void Add_pingtai(JY_pingtai jy_pt) throws Exception {
		// TODO Auto-generated method stub
		jy_pingtai.Add_pt(jy_pt);
	}

}
