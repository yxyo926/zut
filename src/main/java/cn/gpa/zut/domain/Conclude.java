package cn.gpa.zut.domain;

public class Conclude {
	private String concludeinfo_id;//编号
	private String concludeinfo_name;//项目名称
	private String concludeinfo_person;//申报人
	private String concludeinfo_olev;//组织单位级别
	private String concludeinfo_lev;//结项级别 
	private Double concludetinfo_getGpa;//总业绩点
	private String  gpaDistr;//个人业绩点
	private User user;
	private Sys_Dict sys_Dict;
	private Project project;
	private Performance_Type performance_Type;
	private Sys_Ratio sys_Ratio;
	private Integer record_status;
	private String  statuString;
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
