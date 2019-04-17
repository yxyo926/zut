package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.DictPara;
import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.User;



public interface IPaperDao {
	
	//查询所有论文信息
	@Select("select * from sci_paperinfo")
	List<Paper> findAll() throws Exception;
	
	//保存论文信息
	@Insert("insert into sci_paperinfo(paperinfo_Id,paperinfo_Author,paperinfo_Name,paperinfo_CN,paperinfo_ISSN,paperinfo_Time,paperinfo_Lev,paperinfo_orglev,paperinfo_getGpa)"
			+ " values(#{paperinfo_Id},#{paperinfo_Author},#{paperinfo_Name},#{paperinfo_CN},#{paperinfo_ISSN},#{paperinfo_Time},#{paperinfo_Lev},#{paperinfo_orglev},#{paperinfo_getGpa})")
	void save(Paper paper);
	//查询用户名下论文信息
    @Select("SELECT * from sci_paperinfo where paperinfo_id IN(SELECT recordinfo_id from sci_record where record_Id in( SELECT record_id FROM `sci_gpadistr`where user_Id=#{id}));")
	/*
	 * @Results({
	 * 
	 * @Result(id = true, property = "paperinfo_Id", column = "paperinfo_Id"),
	 * 
	 * @Result(property = "paperinfo_Name", column = "paperinfo_Name"),
	 * 
	 * @Result(property = "paperinfo_CN", column = "paperinfo_CN"),
	 * 
	 * @Result(property = "paperinfo_ISSN", column = "paperinfo_ISSN"),
	 * 
	 * @Result(property = "paperinfo_Time", column = "paperinfo_Time"),
	 *     
	 * @Result(property = "paperinfo_getGpa",column = "paperinfo_getGpa"),
	 * 
	 * @Result(property = "paperinfo_Lev", column = "paperinfo_Lev",javaType =
	 * DictPara.class, one = @One(select =
	 * "com.itheima.ssm.dao.IProductDao.findById")),
	 * 
	 * @Result(property = "paperinfo_orglev", column = "paperinfo_orglev", javaType
	 * = DictRatio.class, one = @One(select =
	 * "com.itheima.ssm.dao.IProductDao.findById")),
	 * 
	 * @Result(property = "paperinfo_Author", column = "paperinfo_Author",javaType =
	 * User.class,one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById"))
	 * 
	 * })
	 */
	List<Paper> findAllById(String id);
	
	
	

}
