package cn.gpa.zut.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Paper {
	private String paperinfo_Id;//信息编号
	private String paperinfo_Name;//论文名称
	private String paperinfo_MName;//期刊名称
	private String paperinfo_CN;//CN号
	private String paperinfo_ISSN;//ISSN
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date paperinfo_Time;//发表时间
	private String paperinfo_Lev;//论文级别
	private String paperinfo_orglev;//单位级别
	private Double paperinfo_getGpa;//总业绩点
	private String  paperinfo_Author;//署名作者
	private User user;
	private DictPara dictPara;
	private DictRatio dictRatio;
	private String  gpaDistr;//个人所得业绩点
	private List<GpaDistr> gpaDistrs;

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

	@Override
	public String toString() {
		return "Paper [paperinfo_Id=" + paperinfo_Id + ", paperinfo_Author=" + paperinfo_Author + ", paperinfo_Name="
				+ paperinfo_Name + ", paperinfo_CN=" + paperinfo_CN + ", paperinfo_ISSN=" + paperinfo_ISSN
				+ ", paperinfo_Time=" + paperinfo_Time + ", paperinfo_Lev=" + paperinfo_Lev + ", paperinfo_orglev="
				+ paperinfo_orglev + "]";
	}	

	public String getPaperinfo_Author() {
		return paperinfo_Author;
	}

	public void setPaperinfo_Author(String paperinfo_Author) {
		this.paperinfo_Author = paperinfo_Author;
	}

	public String getPaperinfo_Lev() {
		return paperinfo_Lev;
	}

	public void setPaperinfo_Lev(String paperinfo_Lev) {
		this.paperinfo_Lev = paperinfo_Lev;
	}

	public String getPaperinfo_orglev() {
		return paperinfo_orglev;
	}

	public void setPaperinfo_orglev(String paperinfo_orglev) {
		this.paperinfo_orglev = paperinfo_orglev;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DictPara getDictPara() {
		return dictPara;
	}

	public void setDictPara(DictPara dictPara) {
		this.dictPara = dictPara;
	}

	public DictRatio getDictRatio() {
		return dictRatio;
	}

	public void setDictRatio(DictRatio dictRatio) {
		this.dictRatio = dictRatio;
	}

	public String getGpaDistr() {
		return gpaDistr;
	}

	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}

	public String getPaperinfo_MName() {
		return paperinfo_MName;
	}

	public void setPaperinfo_MName(String paperinfo_MName) {
		this.paperinfo_MName = paperinfo_MName;
	}

	/*
	 * public GpaDistr getGpaDistr() { return gpaDistr; }
	 * 
	 * public void setGpaDistr(GpaDistr gpaDistr) { this.gpaDistr = gpaDistr; }
	 */
}