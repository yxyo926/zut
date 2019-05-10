package cn.gpa.zut.service;

import java.time.Year;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gpa.zut.domain.TongJi;

public interface TongJiService {
	
	List<TongJi> findtchGpaByUserId(String user_id)throws Exception;
	List<TongJi> findsciGpaByUserId(String user_id)throws Exception;
	List<TongJi> findwyGpaByUserId(String user_id)throws Exception;
	
	List<String> findSciYear(String user_id)throws Exception;
	List<String> findTchYear(String user_id)throws Exception;
	List<String> findWyYear(String user_id)throws Exception;
	
	Double  sci_findSumGpa(String user_id,String year)throws Exception;
	Double  tch_findSumGpa(String user_id,String year)throws Exception;
	Double  wy_findSumGpa(String user_id,String year)throws Exception;

}
