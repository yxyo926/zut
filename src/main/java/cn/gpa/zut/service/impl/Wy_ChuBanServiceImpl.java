package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.Wy_ProjectDao;
import cn.gpa.zut.domain.WY_BiaoYan;
import cn.gpa.zut.domain.WY_ChuBan;
import cn.gpa.zut.domain.WY_ProjectLev;
import cn.gpa.zut.domain.WY_Record;
import cn.gpa.zut.service.Wy_ChuBanService;

@Service
@Transactional
public class Wy_ChuBanServiceImpl implements Wy_ChuBanService {

	
	@Autowired Wy_ProjectDao wy_projectdao;
	@Override
	public void Add_WyChuBan(WY_ChuBan wy_chuban) throws Exception {
		// TODO Auto-generated method stub
		wy_projectdao.Add_WyChuBan(wy_chuban);
	}
	
	@Override
	public List<WY_BiaoYan> findBiaoYanById(String id) {
		// TODO Auto-generated method stub
		return wy_projectdao.findbiaoyanById(id);
	}
	
	@Override
	public List<WY_ChuBan> findChuBanById(String id) {
		// TODO Auto-generated method stub
		return wy_projectdao.findChuBanById(id);
	}
	
	
	@Override
	public List<WY_ProjectLev> findLevByCategoryID(String id) throws Exception{
		// TODO Auto-generated method stub
		return wy_projectdao.findWYLevById(id);
	}
	@Override
	public Double findgpaByprojectId(String project_id)throws Exception {
		// TODO Auto-generated method stub
		return wy_projectdao.findgpaByprojectId(project_id);
	}
	@Override
	public Double findKhgpaByprojectId(String id) throws Exception {
		// TODO Auto-generated method stub
		return wy_projectdao.findKhgpaByprojectId(id);
	}
	@Override
	public void Add_Record(WY_Record wy_record) throws Exception {
		// TODO Auto-generated method stub
		wy_projectdao.Add_Record(wy_record);
		
	}
	@Override
	public void Add_WyBiaoYan(WY_BiaoYan wy_biaoyan) throws Exception {
		// TODO Auto-generated method stub
		wy_projectdao.Add_WYBiaoYan(wy_biaoyan);
	}

	@Override
	public String findNameByprojectId(String project_id) throws Exception {
		// TODO Auto-generated method stub
		return wy_projectdao.findNameByprojectId(project_id);
	}
	

}
