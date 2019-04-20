package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Patent {
	private String patentinfo_Id;//信息编号
	private String patentinfo_Inventor;//发明人
	private String patentinfo_name;//专利名称
	private String patentinfo_sort;//专利类别
	private Integer patentinfo_status;//专利状态
	private String patentinfo_authorization;//授权号
	private Integer patentinfo_num;//专利权人数
	private Double patentinfoinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	public String getPatentinfo_Id() {
		return patentinfo_Id;
	}
	public void setPatentinfo_Id(String patentinfo_Id) {
		this.patentinfo_Id = patentinfo_Id;
	}
	public String getPatentinfo_Inventor() {
		return patentinfo_Inventor;
	}
	public void setPatentinfo_Inventor(String patentinfo_Inventor) {
		this.patentinfo_Inventor = patentinfo_Inventor;
	}
	public String getPatentinfo_name() {
		return patentinfo_name;
	}
	public void setPatentinfo_name(String patentinfo_name) {
		this.patentinfo_name = patentinfo_name;
	}
	public String getPatentinfo_sort() {
		return patentinfo_sort;
	}
	public void setPatentinfo_sort(String patentinfo_sort) {
		this.patentinfo_sort = patentinfo_sort;
	}
	public Integer getPatentinfo_status() {
		return patentinfo_status;
	}
	public void setPatentinfo_status(Integer patentinfo_status) {
		this.patentinfo_status = patentinfo_status;
	}
	public String getPatentinfo_authorization() {
		return patentinfo_authorization;
	}
	public void setPatentinfo_authorization(String patentinfo_authorization) {
		this.patentinfo_authorization = patentinfo_authorization;
	}
	public Integer getPatentinfo_num() {
		return patentinfo_num;
	}
	public void setPatentinfo_num(Integer patentinfo_num) {
		this.patentinfo_num = patentinfo_num;
	}
	public Double getPatentinfoinfo_getGpa() {
		return patentinfoinfo_getGpa;
	}
	public void setPatentinfoinfo_getGpa(Double patentinfoinfo_getGpa) {
		this.patentinfoinfo_getGpa = patentinfoinfo_getGpa;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
}
