package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Plateform {
	private String plateforminfo_id;//信息编号
	private String plateforminfo_name;//平台名称
	private String plateforminfo_organize;//主持人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date plateforminfo_starttime;//获准时间
	private Date plateforminfo_finishtime;//结束时间
	private String plateforninfo_checklev;//考核等级
	private Double plateforminfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	public String getPlateforminfo_id() {
		return plateforminfo_id;
	}
	public void setPlateforminfo_id(String plateforminfo_id) {
		this.plateforminfo_id = plateforminfo_id;
	}
	public String getPlateforminfo_name() {
		return plateforminfo_name;
	}
	public void setPlateforminfo_name(String plateforminfo_name) {
		this.plateforminfo_name = plateforminfo_name;
	}
	public String getPlateforminfo_organize() {
		return plateforminfo_organize;
	}
	public void setPlateforminfo_organize(String plateforminfo_organize) {
		this.plateforminfo_organize = plateforminfo_organize;
	}
	public Date getPlateforminfo_starttime() {
		return plateforminfo_starttime;
	}
	public void setPlateforminfo_starttime(Date plateforminfo_starttime) {
		this.plateforminfo_starttime = plateforminfo_starttime;
	}
	public Date getPlateforminfo_finishtime() {
		return plateforminfo_finishtime;
	}
	public void setPlateforminfo_finishtime(Date plateforminfo_finishtime) {
		this.plateforminfo_finishtime = plateforminfo_finishtime;
	}
	public String getPlateforninfo_checklev() {
		return plateforninfo_checklev;
	}
	public void setPlateforninfo_checklev(String plateforninfo_checklev) {
		this.plateforninfo_checklev = plateforninfo_checklev;
	}
	public Double getPlateforminfo_getGpa() {
		return plateforminfo_getGpa;
	}
	public void setPlateforminfo_getGpa(Double plateforminfo_getGpa) {
		this.plateforminfo_getGpa = plateforminfo_getGpa;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
}
