package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.JY_ProjectDao;
import cn.gpa.zut.domain.JY_Category;
import cn.gpa.zut.domain.JY_HuoJiang;
import cn.gpa.zut.domain.JY_KeCheng;
import cn.gpa.zut.domain.JY_Lunwen;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_ZhuanYe;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.service.Jy_ProjectService;


@Service
@Transactional
public class Jy_ProjectServiceImpl implements Jy_ProjectService {
	
	@Autowired
	private JY_ProjectDao jy_projectDao;

	public List<JY_Category> findAll_Category() throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.findAll_Category();
	}

	public List<JY_ProjectLev> findLevByCategoryID(String category_id) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.findLevBycategory(category_id);
	}

	@Override
	public Boolean Add_Jiaogai(JY_jiaogai jy_jiaogai) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.Add_JiaoGai(jy_jiaogai);
	}

	@Override
	public Boolean Add_ZhuanYe(JY_ZhuanYe zhuanye) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.Add_ZhuanYe(zhuanye);
	}

	@Override
	public Boolean Add_KeCheng(JY_KeCheng kecheng) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.Add_KeCheng(kecheng);
	}

	@Override
	public Boolean Add_HuoJiang(JY_HuoJiang huojiang) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.Add_HuoJiang(huojiang);
	}

	@Override
	public Boolean Add_Record(JY_Record jy_record) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.Add_Record(jy_record);
	}

	@Override
	public double findgpaByprojectId(String project_id) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.findgpaByprojectId(project_id);
	}

	@Override
	public double findKhgpaByprojectId(String project_id) throws Exception {
		// TODO Auto-generated method stub
		return jy_projectDao.findKhgpaByprojectId(project_id);
	}

	@Override
	public List<JY_jiaogai> findJiaogaiById(String userId) {
		// TODO Auto-generated method stub
		return jy_projectDao.findJiaogaiById(userId);
	}

	@Override
	public List<JY_ZhuanYe> findZhuanyeById(String userId) {
		// TODO Auto-generated method stub
		return jy_projectDao.findZhuanyeById(userId);
	}

	@Override
	public List<JY_KeCheng> findKechengById(String userId) {
		// TODO Auto-generated method stub
		return jy_projectDao.findKechengById(userId);
	}

	@Override
	public List<JY_HuoJiang> findHuojiangById(String userId) {
		// TODO Auto-generated method stub
		return jy_projectDao.findHuojiangById(userId);
	}

	@Override
	public List<JY_Lunwen> findLunwenById(String userId) {
		// TODO Auto-generated method stub
		return jy_projectDao.findLunwenById(userId);
	}

	

}
