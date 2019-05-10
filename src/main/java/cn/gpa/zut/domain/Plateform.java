package cn.gpa.zut.domain;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Plateform {
	private String plateforminfo_id;//信息编号
	private String plateforminfo_name;//平台名称
	private String plateforminfo_organize;//主持人
	private String plateforminfo_lev;//级别
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date plateforminfo_starttime;//获准时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date plateforminfo_finishtime;//结束时间
	private String plateforminfo_checklev;//考核等级
	private Double plateforminfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	private User user;
	private Sys_Dict sys_Dict;
	private Performance_Type performance_Type;
	private Sys_Ratio sys_Ratio;
	private Integer record_status;
	private String  statuString;
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
	public String getPlateforminfo_checklev() {
		return plateforminfo_checklev;
	}
	public void setPlateforminfo_checklev(String plateforminfo_checklev) {
		this.plateforminfo_checklev = plateforminfo_checklev;
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
	public String getPlateforminfo_lev() {
		return plateforminfo_lev;
	}
	public void setPlateforminfo_lev(String plateforminfo_lev) {
		this.plateforminfo_lev = plateforminfo_lev;
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
