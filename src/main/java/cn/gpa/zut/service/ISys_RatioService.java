package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Sys_Ratio;

public interface ISys_RatioService {

		public List<Sys_Ratio> findAll() throws Exception;
		//根据三级父类查询系数subject适用
		public List<Sys_Ratio> findAllLevbyId(Integer ratioid) throws Exception;
		//根据id查询
		public Sys_Ratio findByid(String check);
		//根据id查数据
		public Sys_Ratio getByID(int pid);
		//根据父类id和名称查数据
		public Sys_Ratio getByPId(int pid, String name);

}
