package cn.gpa.zut.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Assess {
	private String assessinfo_id;//信息编号
	private String assessinfo_aname;//评价名称
	private String assessinfo_rname;//项目名称
	private String assessinfo_person;//申报人
	private Double assessinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	public String getAssessinfo_id() {
		return assessinfo_id;
	}
	public void setAssessinfo_id(String assessinfo_id) {
		this.assessinfo_id = assessinfo_id;
	}
	public String getAssessinfo_aname() {
		return assessinfo_aname;
	}
	public void setAssessinfo_aname(String assessinfo_aname) {
		this.assessinfo_aname = assessinfo_aname;
	}
	public String getAssessinfo_rname() {
		return assessinfo_rname;
	}
	public void setAssessinfo_rname(String assessinfo_rname) {
		this.assessinfo_rname = assessinfo_rname;
	}
	public String getAssessinfo_person() {
		return assessinfo_person;
	}
	public void setAssessinfo_person(String assessinfo_person) {
		this.assessinfo_person = assessinfo_person;
	}
	public Double getAssessinfo_getGpa() {
		return assessinfo_getGpa;
	}
	public void setAssessinfo_getGpa(Double assessinfo_getGpa) {
		this.assessinfo_getGpa = assessinfo_getGpa;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
}
