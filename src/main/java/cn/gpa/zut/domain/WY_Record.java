package cn.gpa.zut.domain;
import java.util.Date;

public class WY_Record {
	
		private String record_id;
		private String record_project_id;
		private String record_sort;
		private Date record_sbtime;
		private String Record_local;
		private int state;
		private double  record_piont;
	    private Double  gpaDistr;
		public String getRecord_id() {
			return record_id;
		}
		public void setRecord_id(String record_id) {
			this.record_id = record_id;
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
