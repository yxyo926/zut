package cn.gpa.zut.domain;

public class GpaDistr {
	private String  gpadistr_id;
	private String  userteam_id;
	private String  record_id;
	private String   user_Id;
	private String   userteam_profession;
	private Double   userteam_getGpa;
	private Userteam userteam;
	private User  user;
	public String getGpadistr_id() {
		return gpadistr_id;
	}
	public void setGpadistr_id(String gpadistr_id) {
		this.gpadistr_id = gpadistr_id;
	}
	public String getUserteam_id() {
		return userteam_id;
	}
	public void setUserteam_id(String userteam_id) {
		this.userteam_id = userteam_id;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getUserteam_profession() {
		return userteam_profession;
	}
	public void setUserteam_profession(String userteam_profession) {
		this.userteam_profession = userteam_profession;
	}
	public Double getUserteam_getGpa() {
		return userteam_getGpa;
	}
	public void setUserteam_getGpa(Double userteam_getGpa) {
		this.userteam_getGpa = userteam_getGpa;
	}
	@Override
	public String toString() {
		return "GpaDistr [gpadistr_id=" + gpadistr_id + ", userteam_id=" + userteam_id + ", record_id=" + record_id
				+ ", user_Id=" + user_Id + ", userteam_profession=" + userteam_profession + ", userteam_getGpa="
				+ userteam_getGpa + "]";
	}
	public Userteam getUserteam() {
		return userteam;
	}
	public void setUserteam(Userteam userteam) {
		this.userteam = userteam;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
