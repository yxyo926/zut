package cn.gpa.zut.dao;

import java.time.Year;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.TongJi;

public interface TongjiDao {
	
	
	@Select("select user_Id,userteam_getGpa,record_sbtime from tch_gpadistr inner join tch_record "
			+ "on tch_gpadistr.record_id=tch_record.record_id AND tch_gpadistr.user_Id='${user_id}'")
	List<TongJi> findTchGpaByUserId(@Param("user_id") String user_id)throws Exception;
	
	@Select("select user_Id,userteam_getGpa,record_time from sci_gpadistr inner join sci_record on "
			+ "sci_gpadistr.record_id=sci_record.record_id AND sci_gpadistr.user_Id='${user_id}'")
	List<TongJi> findKYGpaByUserId(@Param("user_id") String user_id)throws Exception;
	
	@Select("select user_Id,userteam_getGpa,record_sbtime from wy_gpadistr inner join wy_record on "
			+ "wy_gpadistr.record_id=wy_record.record_id AND wy_gpadistr.user_Id='${user_id}'")
	List<TongJi> findWYGpaByUserId(@Param("user_id") String user_id)throws Exception;
	
	//获得年份
	@Select("SELECT  DATE_FORMAT(record_time,'%Y') FROM sci_gpadistr a,sci_record b WHERE a.record_id=b.record_id AND a.user_Id='${user_id}' GROUP BY DATE_FORMAT(record_time,'%Y') ")
	List<String> findSciYear(@Param("user_id") String user_id)throws Exception;
	
	//获得年份
	@Select("SELECT  DATE_FORMAT(record_sbtime,'%Y') FROM tch_gpadistr a,tch_record b WHERE a.record_id=b.record_id AND a.user_Id='${user_id}' GROUP BY DATE_FORMAT(record_sbtime,'%Y') ")
	List<String> findTchYear(@Param("user_id") String user_id)throws Exception;
		
	//获得年份
	@Select("SELECT  DATE_FORMAT(record_sbtime,'%Y') FROM wy_gpadistr a,wy_record b WHERE a.record_id=b.record_id AND a.user_Id='${user_id}' GROUP BY DATE_FORMAT(record_sbtime,'%Y') ")
	List<String> findWyYear(@Param("user_id") String user_id)throws Exception;
	
	//根据用户id/年份 获得该年份总业绩点
	@Select("SELECT SUM(userteam_getGpa) FROM sci_gpadistr a,sci_record b WHERE a.record_id=b.record_id AND a.user_Id='${user_id}' AND DATE_FORMAT(b.record_time,'%Y')='${year}' ")
	Double  sci_findSumGpa(@Param("user_id") String user_id,@Param("year") String year)throws Exception;
	
	@Select("SELECT SUM(userteam_getGpa) FROM tch_gpadistr a,tch_record b WHERE a.record_id=b.record_id AND a.user_Id='${user_id}' AND DATE_FORMAT(b.record_sbtime,'%Y')='${year}' ")
	Double  tch_findSumGpa(@Param("user_id") String user_id,@Param("year") String year)throws Exception;
	
	@Select("SELECT SUM(userteam_getGpa) FROM wy_gpadistr a,wy_record b WHERE a.record_id=b.record_id AND a.user_Id='${user_id}' AND DATE_FORMAT(b.record_sbtime,'%Y')='${year}' ")
	Double  wy_findSumGpa(@Param("user_id") String user_id,@Param("year") String year)throws Exception;
}
