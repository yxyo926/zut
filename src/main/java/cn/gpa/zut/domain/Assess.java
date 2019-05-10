package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Assess {
	private String assessinfo_id;//信息编号
	private String assessinfo_aname;//评价名称
	private String assessinfo_rname;//项目名称
	private String assessinfo_person;//申报人
	private Double assessinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	private User user;
	private Sys_Dict sys_Dict;
	private Performance_Type performance_Type;
	private Sys_Ratio sys_Ratio;
	private Integer record_status;
	private String  statuString;
	private Project  project ;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date   assessinfo_time;//评价时间
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
	public Date getAssessinfo_time() {
		return assessinfo_time;
	}
	public void setAssessinfo_time(Date assessinfo_time) {
		this.assessinfo_time = assessinfo_time;
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
