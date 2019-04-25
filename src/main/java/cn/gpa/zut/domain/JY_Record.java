package cn.gpa.zut.domain;
import java.util.Date;

public class JY_Record {
	
		private String Record_id;
		private String team_id;
		private String record_project_id;
		private String record_sort;
		private double point;
		private Date record_sbtime;
		private String Record_local;
		private int state;
		public String getRecord_id() {
			return Record_id;
		}
		public void setRecord_id(String record_id) {
			Record_id = record_id;
		}
		public String getTeam_id() {
			return team_id;
		}
		public void setTeam_id(String team_id) {
			this.team_id = team_id;
		}
		public String getRecord_project_id() {
			return record_project_id;
		}
		public void setRecord_project_id(String record_project_id) {
			this.record_project_id = record_project_id;
		}
		public String getRecord_sort() {
			return record_sort;
		}
		public void setRecord_sort(String record_sort) {
			this.record_sort = record_sort;
		}
		public double getPoint() {
			return point;
		}
		public void setPoint(double point) {
			this.point = point;
		}
		public Date getRecord_sbtime() {
			return record_sbtime;
		}
		public void setRecord_sbtime(Date record_sbtime) {
			this.record_sbtime = record_sbtime;
		}
		public String getRecord_local() {
			return Record_local;
		}
		public void setRecord_local(String record_local) {
			Record_local = record_local;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		
		

}
