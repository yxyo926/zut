package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Writings {
	private String writingsinfo_Id;//信息编码
	private String writingsinfo_Editor;//申请人
	private String writingsinfo_Name;//论著名称
	private String writingsinfo_Press;//出版社
	private String writingsinfo_ISBN;//ISBN书号
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date writingsinfo_time;//出版 时间
	private Integer writingsinfo_wordsnum;//字数
	private String writinginfo_org;//立项单位
	private String writinginfo_lev;//出版社级别
	private Double writinginfo_getGpa;//总业绩点
	private String  gpaDistr;//个人所得业绩点
	public String getWritingsinfo_Id() {
		return writingsinfo_Id;
	}
	public void setWritingsinfo_Id(String writingsinfo_Id) {
		this.writingsinfo_Id = writingsinfo_Id;
	}
	public String getWritingsinfo_Editor() {
		return writingsinfo_Editor;
	}
	public void setWritingsinfo_Editor(String writingsinfo_Editor) {
		this.writingsinfo_Editor = writingsinfo_Editor;
	}
	public String getWritingsinfo_Name() {
		return writingsinfo_Name;
	}
	public void setWritingsinfo_Name(String writingsinfo_Name) {
		this.writingsinfo_Name = writingsinfo_Name;
	}
	public String getWritingsinfo_Press() {
		return writingsinfo_Press;
	}
	public void setWritingsinfo_Press(String writingsinfo_Press) {
		this.writingsinfo_Press = writingsinfo_Press;
	}
	public String getWritingsinfo_ISBN() {
		return writingsinfo_ISBN;
	}
	public void setWritingsinfo_ISBN(String writingsinfo_ISBN) {
		this.writingsinfo_ISBN = writingsinfo_ISBN;
	}
	public Date getWritingsinfo_time() {
		return writingsinfo_time;
	}
	public void setWritingsinfo_time(Date writingsinfo_time) {
		this.writingsinfo_time = writingsinfo_time;
	}
	public String getWritinginfo_org() {
		return writinginfo_org;
	}
	public void setWritinginfo_org(String writinginfo_org) {
		this.writinginfo_org = writinginfo_org;
	}
	public String getWritinginfo_lev() {
		return writinginfo_lev;
	}
	public void setWritinginfo_lev(String writinginfo_lev) {
		this.writinginfo_lev = writinginfo_lev;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
	public Double getWritinginfo_getGpa() {
		return writinginfo_getGpa;
	}
	public void setWritinginfo_getGpa(Double writinginfo_getGpa) {
		this.writinginfo_getGpa = writinginfo_getGpa;
	}
	public Integer getWritingsinfo_wordsnum() {
		return writingsinfo_wordsnum;
	}
	public void setWritingsinfo_wordsnum(Integer writingsinfo_wordsnum) {
		this.writingsinfo_wordsnum = writingsinfo_wordsnum;
	}
	
}
