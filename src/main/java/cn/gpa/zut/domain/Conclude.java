package cn.gpa.zut.domain;

public class Conclude {
	private String concludeinfo_id;//编号
	private String concludeinfo_name;//项目名称
	private String concludeinfo_person;//申报人
	private String concludeinfo_olev;//组织单位级别
	private String concludeinfo_lev;//结项级别 
	private Double concludetinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	public String getConcludeinfo_id() {
		return concludeinfo_id;
	}
	public void setConcludeinfo_id(String concludeinfo_id) {
		this.concludeinfo_id = concludeinfo_id;
	}
	public String getConcludeinfo_name() {
		return concludeinfo_name;
	}
	public void setConcludeinfo_name(String concludeinfo_name) {
		this.concludeinfo_name = concludeinfo_name;
	}
	public String getConcludeinfo_person() {
		return concludeinfo_person;
	}
	public void setConcludeinfo_person(String concludeinfo_person) {
		this.concludeinfo_person = concludeinfo_person;
	}
	public String getConcludeinfo_olev() {
		return concludeinfo_olev;
	}
	public void setConcludeinfo_olev(String concludeinfo_olev) {
		this.concludeinfo_olev = concludeinfo_olev;
	}
	public String getConcludeinfo_lev() {
		return concludeinfo_lev;
	}
	public void setConcludeinfo_lev(String concludeinfo_lev) {
		this.concludeinfo_lev = concludeinfo_lev;
	}
	public Double getConcludetinfo_getGpa() {
		return concludetinfo_getGpa;
	}
	public void setConcludetinfo_getGpa(Double concludetinfo_getGpa) {
		this.concludetinfo_getGpa = concludetinfo_getGpa;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
}
