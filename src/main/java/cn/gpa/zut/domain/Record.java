package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Record {
	   private String record_Id;
	   private String recordinfo_id;
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
	public String getRecordinfo_id() {
		return recordinfo_id;
	}
	public void setRecordinfo_id(String recordinfo_id) {
		this.recordinfo_id = recordinfo_id;
	}
	   
}
