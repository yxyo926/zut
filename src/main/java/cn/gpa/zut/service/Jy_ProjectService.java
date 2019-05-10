package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.JY_Category;
import cn.gpa.zut.domain.JY_HuoJiang;
import cn.gpa.zut.domain.JY_KeCheng;
import cn.gpa.zut.domain.JY_Lunwen;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_Record;
import cn.gpa.zut.domain.JY_ZhuanYe;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.domain.Paper;

public interface Jy_ProjectService {
	
	List<JY_Category> findAll_Category() throws Exception;
	
	List<JY_ProjectLev> findLevByCategoryID(String category_id) throws Exception;
	
	double findgpaByprojectId(String project_id) throws Exception;
	
	double findKhgpaByprojectId(String project_id) throws Exception;
	//提交教改信息
	
	Boolean Add_Jiaogai(JY_jiaogai jy_jiaogai)throws Exception;
	
	//提交专业信息
	Boolean Add_ZhuanYe(JY_ZhuanYe zhuanye)throws Exception;
	
	//提交课程信息
	Boolean Add_KeCheng(JY_KeCheng kecheng)throws Exception;
		//提交获奖信息
	Boolean Add_HuoJiang(JY_HuoJiang huojiang)throws Exception;
	//提交论文信息
		Boolean Add_LunWen(JY_Lunwen lunwen)throws Exception;
	
	Boolean Add_Record(JY_Record jy_record)throws Exception;
	
	
	
	List<JY_jiaogai> findJiaogaiById(String userId);

	List<JY_ZhuanYe> findZhuanyeById(String userId);

	List<JY_KeCheng> findKechengById(String userId);

	List<JY_HuoJiang> findHuojiangById(String userId);

	List<JY_Lunwen> findLunwenById(String userId);
		


}
