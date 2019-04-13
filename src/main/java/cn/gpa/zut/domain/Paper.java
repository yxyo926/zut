package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Paper {
	private   String paperinfo_Id;
	private   String paperinfo_Author;
	private   String paperinfo_Name;
	private   String paperinfo_CN;
	private   String paperinfo_ISSN;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private   Date paperinfo_Time;
	private   String paperinfo_Lev;
	private   String paperinfo_orglev;
	private  Double  paperinfo_getGpa;
	public Double getPaperinfo_getGpa() {
		return paperinfo_getGpa;
	}
	public void setPaperinfo_getGpa(Double paperinfo_getGpa) {
		this.paperinfo_getGpa = paperinfo_getGpa;
	}
	public String getPaperinfo_Id() {
		return paperinfo_Id;
	}
	public void setPaperinfo_Id(String paperinfo_Id) {
		this.paperinfo_Id = paperinfo_Id;
	}
	public String getPaperinfo_Author() {
		return paperinfo_Author;
	}
	public void setPaperinfo_Author(String paperinfo_Author) {
		this.paperinfo_Author = paperinfo_Author;
	}
	public String getPaperinfo_Name() {
		return paperinfo_Name;
	}
	public void setPaperinfo_Name(String paperinfo_Name) {
		this.paperinfo_Name = paperinfo_Name;
	}
	public String getPaperinfo_CN() {
		return paperinfo_CN;
	}
	public void setPaperinfo_CN(String paperinfo_CN) {
		this.paperinfo_CN = paperinfo_CN;
	}
	public String getPaperinfo_ISSN() {
		return paperinfo_ISSN;
	}
	public void setPaperinfo_ISSN(String paperinfo_ISSN) {
		this.paperinfo_ISSN = paperinfo_ISSN;
	}
	public Date getPaperinfo_Time() {
		return paperinfo_Time;
	}
	public void setPaperinfo_Time(Date paperinfo_Time) {
		this.paperinfo_Time = paperinfo_Time;
	}
	public String getPaperinfo_Lev() {
		return paperinfo_Lev;
	}
	public void setPaperinfo_Lev(String paperinfo_lev) {
		this.paperinfo_Lev = paperinfo_lev;
	}
	public String getPaperinfo_orglev() {
		return paperinfo_orglev;
	}
	public void setPaperinfo_orglev(String paperinfo_orglev) {
		this.paperinfo_orglev = paperinfo_orglev;
	}
	@Override
	public String toString() {
		return "Paper [paperinfo_Id=" + paperinfo_Id + ", paperinfo_Author=" + paperinfo_Author + ", paperinfo_Name="
				+ paperinfo_Name + ", paperinfo_CN=" + paperinfo_CN + ", paperinfo_ISSN=" + paperinfo_ISSN
				+ ", paperinfo_Time=" + paperinfo_Time + ", paperinfo_Lev=" + paperinfo_Lev
				+ ", paperinfo_orglev=" + paperinfo_orglev + "]";
	}
	
	
}
