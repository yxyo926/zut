package cn.gpa.zut.domain;

public class Performance_Type {
private Integer id;
private Integer type_grade;
private String name;
private Integer parent_id;
private Integer sort;
private Integer is_enabled;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getType_grade() {
	return type_grade;
}
public void setType_grade(Integer type_grade) {
	this.type_grade = type_grade;
}
public String getTname() {
	return name;
}
public void setTname(String name) {
	this.name = name;
}
public Integer getParent_id() {
	return parent_id;
}
public void setParent_id(Integer parent_id) {
	this.parent_id = parent_id;
}
public Integer getSort() {
	return sort;
}
public void setSort(Integer sort) {
	this.sort = sort;
}
public Integer getIs_enabled() {
	return is_enabled;
}
public void setIs_enabled(Integer is_enabled) {
	this.is_enabled = is_enabled;
}

	
}
