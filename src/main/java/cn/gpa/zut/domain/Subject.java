package cn.gpa.zut.domain;

import java.util.Date;

public class Subject {
	private String subjectinfo_id;//信息编号
	private String subjectinfo_name;//学科建设名称
	private String subjectinfo_uname;//主持人
	private String subjectinfo_sort;//学科建设类别
	private String subjectinfo_lev;//建设级别
	private Date subjectinfo_starttime;//开始时间
	private Date subjectinfo_finishtime;//结束时间
	private String subjectinfo_checklev;//验收等级
	private Double subjectinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
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

}
