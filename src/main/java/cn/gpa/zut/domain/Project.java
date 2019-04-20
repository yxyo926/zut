package cn.gpa.zut.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Project {
	private String projectinfo_Id;/* 信息编号 */
	private String projectinfo_origin;//项目来源
	private String projectinfo_Name;//项目名称
	private String projectinfo_Leader;//项目负责人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date projectinfo_StartTime;//开始时间
	private Date projectinfo_FinishTime;//完成时间
	private Double projectinfo_StartMoney;//立项经费
	private Double projectinfo_getGpa;//总业绩点
	private Double  gpaDistr;//个人所得业绩点
	public String getProjectinfo_origin() {
		return projectinfo_origin;
	}
	public void setProjectinfo_origin(String projectinfo_origin) {
		this.projectinfo_origin = projectinfo_origin;
	}
	public String getProjectinfo_Name() {
		return projectinfo_Name;
	}
	public void setProjectinfo_Name(String projectinfo_Name) {
		this.projectinfo_Name = projectinfo_Name;
	}
	public String getProjectinfo_Leader() {
		return projectinfo_Leader;
	}
	public void setProjectinfo_Leader(String projectinfo_Leader) {
		this.projectinfo_Leader = projectinfo_Leader;
	}
	public Date getProjectinfo_StartTime() {
		return projectinfo_StartTime;
	}
	public void setProjectinfo_StartTime(Date projectinfo_StartTime) {
		this.projectinfo_StartTime = projectinfo_StartTime;
	}
	public Date getProjectinfo_FinishTime() {
		return projectinfo_FinishTime;
	}
	public void setProjectinfo_FinishTime(Date projectinfo_FinishTime) {
		this.projectinfo_FinishTime = projectinfo_FinishTime;
	}
	public Double getProjectinfo_StartMoney() {
		return projectinfo_StartMoney;
	}
	public void setProjectinfo_StartMoney(Double projectinfo_StartMoney) {
		this.projectinfo_StartMoney = projectinfo_StartMoney;
	}
	public Double getProjectinfo_getGpa() {
		return projectinfo_getGpa;
	}
	public void setProjectinfo_getGpa(Double projectinfo_getGpa) {
		this.projectinfo_getGpa = projectinfo_getGpa;
	}
	public Double getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(Double gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
	public String getProjectinfo_Id() {
		return projectinfo_Id;
	}
	public void setProjectinfo_Id(String projectinfo_Id) {
		this.projectinfo_Id = projectinfo_Id;
	}

}
