package cn.gpa.zut.domain;

import java.util.Date;

public class WY_ChuBan {
	
	private String project_id;
	private String project_name;
	private String project_type;
	private String lev_select;
	private String wy_zutsign;
	private String builed_lev;
	private String press_name;
	private String press_lev;
	private String qk_name;
	private String qk_num;
	private String zp_num;
	private Double zp_size;
	private Date cb_time;
	private Double  record_piont;
    private Double  gpaDistr;
    
    
    
	public String getBuiled_lev() {
		return builed_lev;
	}
	public void setBuiled_lev(String builed_lev) {
		this.builed_lev = builed_lev;
	}
	public String getPress_lev() {
		return press_lev;
	}
	public void setPress_lev(String press_lev) {
		this.press_lev = press_lev;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_type() {
		return project_type;
	}
	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}
	public String getLev_select() {
		return lev_select;
	}
	public void setLev_select(String lev_select) {
		this.lev_select = lev_select;
	}
	public String getWy_zutsign() {
		return wy_zutsign;
	}
	public void setWy_zutsign(String wy_zutsign) {
		this.wy_zutsign = wy_zutsign;
	}
	public String getPress_name() {
		return press_name;
	}
	public void setPress_name(String press_name) {
		this.press_name = press_name;
	}
	public String getQk_name() {
		return qk_name;
	}
	public void setQk_name(String qk_name) {
		this.qk_name = qk_name;
	}
	public String getQk_num() {
		return qk_num;
	}
	public void setQk_num(String qk_num) {
		this.qk_num = qk_num;
	}
	public String getZp_num() {
		return zp_num;
	}
	public void setZp_num(String zp_num) {
		this.zp_num = zp_num;
	}
	
	public Date getCb_time() {
		return cb_time;
	}
	public void setCb_time(Date cb_time) {
		this.cb_time = cb_time;
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
	public Double getZp_size() {
		return zp_size;
	}
	public void setZp_size(Double zp_size) {
		this.zp_size = zp_size;
	}
	
}
