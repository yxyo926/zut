package cn.gpa.zut.domain;



public class Patent {
	private String patentinfo_Id;//信息编号
	private String patentinfo_Inventor;//发明人
	private String patentinfo_name;//专利名称
	private String patentinfo_sort;//专利类别
	private Integer patentinfo_status;//专利状态
	private String patentinfo_authorization;//授权号
	private Integer patentinfo_num;//专利权人数
	private Double patentinfoinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	private User user;
	private Sys_Dict sys_Dict;
	private Performance_Type performance_Type;
	private Sys_Ratio sys_Ratio;
	private Integer record_status;
	private String  statuString;
	public String getPatentinfo_Id() {
		return patentinfo_Id;
	}
	public void setPatentinfo_Id(String patentinfo_Id) {
		this.patentinfo_Id = patentinfo_Id;
	}
	public String getPatentinfo_Inventor() {
		return patentinfo_Inventor;
	}
	public void setPatentinfo_Inventor(String patentinfo_Inventor) {
		this.patentinfo_Inventor = patentinfo_Inventor;
	}
	public String getPatentinfo_name() {
		return patentinfo_name;
	}
	public void setPatentinfo_name(String patentinfo_name) {
		this.patentinfo_name = patentinfo_name;
	}
	public String getPatentinfo_sort() {
		return patentinfo_sort;
	}
	public void setPatentinfo_sort(String patentinfo_sort) {
		this.patentinfo_sort = patentinfo_sort;
	}
	public Integer getPatentinfo_status() {
		return patentinfo_status;
	}
	public void setPatentinfo_status(Integer patentinfo_status) {
		this.patentinfo_status = patentinfo_status;
	}
	public String getPatentinfo_authorization() {
		return patentinfo_authorization;
	}
	public void setPatentinfo_authorization(String patentinfo_authorization) {
		this.patentinfo_authorization = patentinfo_authorization;
	}
	public Integer getPatentinfo_num() {
		return patentinfo_num;
	}
	public void setPatentinfo_num(Integer patentinfo_num) {
		this.patentinfo_num = patentinfo_num;
	}
	public Double getPatentinfoinfo_getGpa() {
		return patentinfoinfo_getGpa;
	}
	public void setPatentinfoinfo_getGpa(Double patentinfoinfo_getGpa) {
		this.patentinfoinfo_getGpa = patentinfoinfo_getGpa;
	}
	public String getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(String gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getRecord_status() {
		return record_status;
	}
	public void setRecord_status(Integer record_status) {
		this.record_status = record_status;
	}
	public String getStatuString() {
		if (record_status != null) {
            // 状态 0 关闭 1 开启
            if(record_status==0)
            	statuString="未通过";
            if(record_status==1)
            	statuString="通过";
            if(record_status==2)
            	statuString="审核中";
            if(record_status==3)
            	statuString="未提交";
        }
		return statuString;
	}
	public void setStatuString(String statuString) {
		this.statuString = statuString;
	}
	public Sys_Dict getSys_Dict() {
		return sys_Dict;
	}
	public void setSys_Dict(Sys_Dict sys_Dict) {
		this.sys_Dict = sys_Dict;
	}
	public Performance_Type getPerformance_Type() {
		return performance_Type;
	}
	public void setPerformance_Type(Performance_Type performance_Type) {
		this.performance_Type = performance_Type;
	}
	public Sys_Ratio getSys_Ratio() {
		return sys_Ratio;
	}
	public void setSys_Ratio(Sys_Ratio sys_Ratio) {
		this.sys_Ratio = sys_Ratio;
	}
	@Override
	public String toString() {
		return "Patent [patentinfo_Id=" + patentinfo_Id + ", patentinfo_Inventor=" + patentinfo_Inventor
				+ ", patentinfo_name=" + patentinfo_name + ", patentinfo_sort=" + patentinfo_sort
				+ ", patentinfo_status=" + patentinfo_status + ", patentinfo_authorization=" + patentinfo_authorization
				+ ", patentinfo_num=" + patentinfo_num + ", patentinfoinfo_getGpa=" + patentinfoinfo_getGpa
				+ ", gpaDistr=" + gpaDistr + ", user=" + user + ", sys_Dict=" + sys_Dict + ", performance_Type="
				+ performance_Type + ", sys_Ratio=" + sys_Ratio + ", record_status=" + record_status + ", statuString="
				+ statuString + "]";
	}
}
