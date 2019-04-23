package cn.gpa.zut.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reward {
	private String reward_Id;//奖励编号
	private String reward_infoID;//信息编号
	private String reward_person;//申报人
	private String reward_name;//奖项名称
	private String  reward_lev;
	private String reward_Organization;//颁奖单位
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date reward_Time;//获奖时间
	private String reward_rank;//合作单位排名
	private Integer reward_num;//合作单位数量
	private Double reward_getGpa;//总业绩点
	private Double  gpaDistr;//个人业绩点
	public String getReward_Id() {
		return reward_Id;
	}
	public void setReward_Id(String reward_Id) {
		this.reward_Id = reward_Id;
	}
	public String getReward_infoID() {
		return reward_infoID;
	}
	public void setReward_infoID(String reward_infoID) {
		this.reward_infoID = reward_infoID;
	}
	public String getReward_person() {
		return reward_person;
	}
	public void setReward_person(String reward_person) {
		this.reward_person = reward_person;
	}
	public String getReward_name() {
		return reward_name;
	}
	public void setReward_name(String reward_name) {
		this.reward_name = reward_name;
	}
	public String getReward_Organization() {
		return reward_Organization;
	}
	public void setReward_Organization(String reward_Organization) {
		this.reward_Organization = reward_Organization;
	}
	public Date getReward_Time() {
		return reward_Time;
	}
	public void setReward_Time(Date reward_Time) {
		this.reward_Time = reward_Time;
	}
	public String getReward_rank() {
		return reward_rank;
	}
	public void setReward_rank(String reward_rank) {
		this.reward_rank = reward_rank;
	}
	public Integer getReward_num() {
		return reward_num;
	}
	public void setReward_num(Integer reward_num) {
		this.reward_num = reward_num;
	}
	public Double getReward_getGpa() {
		return reward_getGpa;
	}
	public void setReward_getGpa(Double reward_getGpa) {
		this.reward_getGpa = reward_getGpa;
	}
	public Double getGpaDistr() {
		return gpaDistr;
	}
	public void setGpaDistr(Double gpaDistr) {
		this.gpaDistr = gpaDistr;
	}
	public String getReward_lev() {
		return reward_lev;
	}
	public void setReward_lev(String reward_lev) {
		this.reward_lev = reward_lev;
	}

}
