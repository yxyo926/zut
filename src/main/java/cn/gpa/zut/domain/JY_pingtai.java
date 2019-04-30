package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JY_pingtai {
	

	private String plateforminfo_id;   
	private String plateforminfo_name ;     
	private String plateforminfo_type;    
	private String plateforminfo_organize ;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date plateforminfo_starttime ;  
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date plateforminfo_finishtime ;   
	private String plateforminfo_Tlev  ;
	private Double  record_piont;
	private Double  gpaDistr;
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
	public String getPlateforminfo_type() {
		return plateforminfo_type;
	}
	public void setPlateforminfo_type(String plateforminfo_type) {
		this.plateforminfo_type = plateforminfo_type;
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
	public String getPlateforminfo_Tlev() {
		return plateforminfo_Tlev;
	}
	public void setPlateforminfo_Tlev(String plateforminfo_Tlev) {
		this.plateforminfo_Tlev = plateforminfo_Tlev;
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

	
	
	
}
