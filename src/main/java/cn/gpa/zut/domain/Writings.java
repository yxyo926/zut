package cn.gpa.zut.domain;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Writings {
	private String writingsinfo_Id;//信息编码
	private String writingsinfo_Editor;//申请人
	private String writingsinfo_Name;//论著名称
	private String writingsinfo_Press;//出版社
	private String writingsinfo_ISBN;//ISBN书号
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date writingsinfo_time;//出版 时间
	private Integer writingsinfo_wordsnum;//字数
	private String writinginfo_org;//出版社级别
	private String writinginfo_lev;//著作级别
	private Double writinginfo_getGpa;//总业绩点
	private String  gpaDistr;//个人所得业绩点
	private User user;
	private Sys_Dict sys_Dict;
	private Performance_Type performance_Type;
	private Sys_Ratio sys_Ratio;
	private Integer record_status;
	private String  statuString;
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
	public Sys_Ratio getSys_Ratio() {
		return sys_Ratio;
	}
	public void setSys_Ratio(Sys_Ratio sys_Ratio) {
		this.sys_Ratio = sys_Ratio;
	}
	
}
