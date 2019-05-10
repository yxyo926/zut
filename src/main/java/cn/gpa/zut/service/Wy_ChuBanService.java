package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.WY_BiaoYan;
import cn.gpa.zut.domain.WY_ChuBan;
import cn.gpa.zut.domain.WY_ProjectLev;
import cn.gpa.zut.domain.WY_Record;

public interface Wy_ChuBanService {
	
	
	void Add_WyChuBan(WY_ChuBan wy_chuban)throws Exception;
	void Add_WyBiaoYan(WY_BiaoYan wy_biaoyan)throws Exception;
	
	
	void Add_Record(WY_Record wy_record)throws Exception;
	//文艺出版/发表记录
	List<WY_ChuBan> findChuBanById(String id) throws Exception;
	
	List<WY_BiaoYan> findBiaoYanById(String id)throws Exception;
	
	List<WY_ProjectLev> findLevByCategoryID(String id)throws Exception;
	
	//业绩点
	Double findgpaByprojectId(String project_id)throws Exception;
	Double findKhgpaByprojectId(String id)throws Exception;
	
	public String findNameByprojectId(String project_id)throws Exception;
	
	

}
