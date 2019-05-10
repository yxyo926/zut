package cn.gpa.zut.domain;

import java.util.Date;

public class WY_BiaoYan {
	
	
	private String project_id;
	private String project_type;
	private String project_name;
	private String project_lev;
	private Date project_time;
	private Double  record_piont;
    private Double  gpaDistr;
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_type() {
		return project_type;
	}
	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_lev() {
		return project_lev;
	}
	public void setProject_lev(String project_lev) {
		this.project_lev = project_lev;
	}
	public Date getProject_time() {
		return project_time;
	}
	public void setProject_time(Date project_time) {
		this.project_time = project_time;
	}
	public Double getRecord_piont() {
		return record_piont;
	}
	public void setRecord_piont(Double record_piont) {
		this.record_piont = record_piont;
	}
	public Double getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(Double gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
	@Override
	public String toString() {
		return "WY_BiaoYan [project_id=" + project_id + ", project_type=" + project_type + ", project_name="
				+ project_name + ", project_lev=" + project_lev + ", project_time=" + project_time + ", record_piont="
				+ record_piont + ", gpaDistr=" + gpaDistr + "]";
	}
    
    
    

}
