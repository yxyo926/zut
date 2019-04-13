package cn.gpa.zut.domain;

import java.util.Date;


public class User {
	 private String  user_Id;             
	 private String Department_Id;        
	 private String user_Password;       
	 private String user_name;           
	 private Integer  user_gender;         
	 private String  user_post;           
	 private String  user_phone;          
	 private String  user_email;           
	 private Date   user_birthday;        
     private String   user_college;        
	 private Integer  user_active;          
	 private Integer  user_Permissions;
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getDepartment_Id() {
		return Department_Id;
	}
	public void setDepartment_Id(String department_Id) {
		Department_Id = department_Id;
	}
	public String getUser_Password() {
		return user_Password;
	}
	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(Integer user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_post() {
		return user_post;
	}
	public void setUser_post(String user_post) {
		this.user_post = user_post;
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
	public Date getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_college() {
		return user_college;
	}
	public void setUser_college(String user_college) {
		this.user_college = user_college;
	}
	public Integer getUser_active() {
		return user_active;
	}
	public void setUser_active(Integer user_active) {
		this.user_active = user_active;
	}
	public Integer getUser_Permissions() {
		return user_Permissions;
	}
	public void setUser_Permissions(Integer user_Permissions) {
		this.user_Permissions = user_Permissions;
	}   
	 
	 
	 
}

