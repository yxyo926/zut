package cn.gpa.zut.domain;

public class Sys_Dict {
	private Integer id;//编号
	private String name;//名称
	private String type;//一级分类
	private Integer two_type;//二级
	private Integer three_type;//三级
	private String code;//字典码
	private Double value;//数值
	private Integer order_num;//排序
	private String remark;
	private Integer del_flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTwo_type() {
		return two_type;
	}
	public void setTwo_type(Integer two_type) {
		this.two_type = two_type;
	}
	public Integer getThree_type() {
		return three_type;
	}
	public void setThree_type(Integer three_type) {
		this.three_type = three_type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(Integer del_flag) {
		this.del_flag = del_flag;
	}
}
