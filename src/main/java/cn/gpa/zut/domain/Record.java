package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Record {
	   private String record_Id;
	   private String record_infoid;        
	   private String category_Id;
	   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	   private Date record_time;          
	   private String  record_proof;         
	   private Integer record_status;
	public String getRecord_Id() {
		return record_Id;
	}
	public void setRecord_Id(String record_Id) {
		this.record_Id = record_Id;
	}
	public String getRecord_infoid() {
		return record_infoid;
	}
	public void setRecord_infoid(String record_infoid) {
		this.record_infoid = record_infoid;
	}
	public String getCategory_Id() {
		return category_Id;
	}
	public void setCategory_Id(String category_Id) {
		this.category_Id = category_Id;
	}
	public Date getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}
	public String getRecord_proof() {
		return record_proof;
	}
	public void setRecord_proof(String record_proof) {
		this.record_proof = record_proof;
	}
	public Integer getRecord_status() {
		return record_status;
	}
	public void setRecord_status(Integer record_status) {
		this.record_status = record_status;
	}
	   
}
