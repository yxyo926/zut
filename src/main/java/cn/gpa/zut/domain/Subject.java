package cn.gpa.zut.domain;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Subject {
	private String subjectinfo_id;//信息编号
	private String subjectinfo_name;//学科建设名称
	private String subjectinfo_uname;//主持人
	private String subjectinfo_sort;//学科建设类别
	private String subjectinfo_lev;//建设级别
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date subjectinfo_starttime;//开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date subjectinfo_finishtime;//结束时间
	private String subjectinfo_checklev;//验收等级
	private Double subjectinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	private User user;
	private Sys_Dict sys_Dict;
	private Performance_Type performance_Type;
	private Sys_Ratio sys_Ratio;
	private Integer record_status;
	private String  statuString;
	public String getSubjectinfo_id() {
		return subjectinfo_id;
	}
	public void setSubjectinfo_id(String subjectinfo_id) {
		this.subjectinfo_id = subjectinfo_id;
	}
	public String getSubjectinfo_name() {
		return subjectinfo_name;
	}
	public void setSubjectinfo_name(String subjectinfo_name) {
		this.subjectinfo_name = subjectinfo_name;
	}
	public String getSubjectinfo_uname() {
		return subjectinfo_uname;
	}
	public void setSubjectinfo_uname(String subjectinfo_uname) {
		this.subjectinfo_uname = subjectinfo_uname;
	}
	public Date getSubjectinfo_starttime() {
		return subjectinfo_starttime;
	}
	public void setSubjectinfo_starttime(Date subjectinfo_starttime) {
		this.subjectinfo_starttime = subjectinfo_starttime;
	}
	public Date getSubjectinfo_finishtime() {
		return subjectinfo_finishtime;
	}
	public void setSubjectinfo_finishtime(Date subjectinfo_finishtime) {
		this.subjectinfo_finishtime = subjectinfo_finishtime;
	}
	public String getSubjectinfo_checklev() {
		return subjectinfo_checklev;
	}
	public void setSubjectinfo_checklev(String subjectinfo_checklev) {
		this.subjectinfo_checklev = subjectinfo_checklev;
	}
	public Double getSubjectinfo_getGpa() {
		return subjectinfo_getGpa;
	}
	public void setSubjectinfo_getGpa(Double subjectinfo_getGpa) {
		this.subjectinfo_getGpa = subjectinfo_getGpa;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
	public String getSubjectinfo_sort() {
		return subjectinfo_sort;
	}
	public void setSubjectinfo_sort(String subjectinfo_sort) {
		this.subjectinfo_sort = subjectinfo_sort;
	}
	public String getSubjectinfo_lev() {
		return subjectinfo_lev;
	}
	public void setSubjectinfo_lev(String subjectinfo_lev) {
		this.subjectinfo_lev = subjectinfo_lev;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getRecord_status() {
		return record_status;
	}
	public void setRecord_status(Integer record_status) {
		this.record_status = record_status;
	}
	public String getStatuString() {
		if (record_status != null) {
            // 状态 0 关闭 1 开启
            if(record_status==0)
            	statuString="未通过";
            if(record_status==1)
            	statuString="通过";
            if(record_status==2)
            	statuString="审核中";
            if(record_status==3)
            	statuString="未提交";
        }
		return statuString;
	}
	public void setStatuString(String statuString) {
		this.statuString = statuString;
	}
	public Sys_Dict getSys_Dict() {
		return sys_Dict;
	}
	public void setSys_Dict(Sys_Dict sys_Dict) {
		this.sys_Dict = sys_Dict;
	}
	public Performance_Type getPerformance_Type() {
		return performance_Type;
	}
	public void setPerformance_Type(Performance_Type performance_Type) {
		this.performance_Type = performance_Type;
	}
	@Override
	public String toString() {
		return "Subject [subjectinfo_id=" + subjectinfo_id + ", subjectinfo_name=" + subjectinfo_name
				+ ", subjectinfo_uname=" + subjectinfo_uname + ", subjectinfo_sort=" + subjectinfo_sort
				+ ", subjectinfo_lev=" + subjectinfo_lev + ", subjectinfo_starttime=" + subjectinfo_starttime
				+ ", subjectinfo_finishtime=" + subjectinfo_finishtime + ", subjectinfo_checklev="
				+ subjectinfo_checklev + ", subjectinfo_getGpa=" + subjectinfo_getGpa + ", gpaDistr=" + gpaDistr
				+ ", user=" + user + ", sys_Dict=" + sys_Dict + ", performance_Type=" + performance_Type
				+ ", record_status=" + record_status + ", statuString=" + statuString + "]";
	}
	public Sys_Ratio getSys_Ratio() {
		return sys_Ratio;
	}
	public void setSys_Ratio(Sys_Ratio sys_Ratio) {
		this.sys_Ratio = sys_Ratio;
	}

}
