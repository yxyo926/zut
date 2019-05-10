package cn.gpa.zut.service.impl;

import java.time.Year;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.TongjiDao;
import cn.gpa.zut.domain.TongJi;
import cn.gpa.zut.service.TongJiService;

@Service
@Transactional
public class TongJiServiceImpl implements TongJiService {
	@Autowired 
	private TongjiDao tongjidao;
	

	
	@Override
	public List<TongJi> findtchGpaByUserId(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return tongjidao.findTchGpaByUserId(user_id);
	}


	@Override
	public List<TongJi> findsciGpaByUserId(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return tongjidao.findKYGpaByUserId(user_id);
	}


	@Override
	public List<TongJi> findwyGpaByUserId(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return tongjidao.findWYGpaByUserId(user_id);
	}


	@Override
	public List<String> findSciYear(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return tongjidao.findSciYear(user_id);
	}


	@Override
	public List<String> findTchYear(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return tongjidao.findTchYear(user_id);
	}


	@Override
	public List<String> findWyYear(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return tongjidao.findWyYear(user_id);
	}


	@Override
	public Double sci_findSumGpa(String user_id, String year) throws Exception {
		// TODO Auto-generated method stub
		Double sumgpa=tongjidao.sci_findSumGpa(user_id, year);
		if(sumgpa==null){
			sumgpa=(double) 0;
		}
		
		return sumgpa;
	}


	@Override
	public Double tch_findSumGpa(String user_id, String year) throws Exception {
		// TODO Auto-generated method stub

		Double sumgpa=tongjidao.tch_findSumGpa(user_id, year);
		if(sumgpa==null){
			sumgpa=(double) 0;
		}
		
		return sumgpa;
	}


	@Override
	public Double wy_findSumGpa(String user_id, String year) throws Exception {
		// TODO Auto-generated method stub
		
		Double sumgpa=tongjidao.wy_findSumGpa(user_id, year);
		if(sumgpa==null){
			sumgpa=(double) 0;
		}
		
		return sumgpa;
	}


}
