package cn.gpa.zut.domain;

import java.util.Date;

public class User {
	private String user_Id;
	private Integer department_Id;
	private String user_password;
	private String user_name;
	private String salt;//???
	private String user_phone;
	private String user_email;
	private Date create_time;
	private Integer user_active;
	private Integer is_admin;
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public Integer getDepartment_Id() {
		return department_Id;
	}
	public void setDepartment_Id(Integer department_Id) {
		this.department_Id = department_Id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public Integer getUser_active() {
		return user_active;
	}
	public void setUser_active(Integer user_active) {
		this.user_active = user_active;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
	}
	
	}
	
	
