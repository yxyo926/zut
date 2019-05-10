package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Sys_Ratio;
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
    //@Select("SELECT * from sci_paperinfo where paperinfo_id IN(SELECT recordinfo_id from sci_record where record_Id in( SELECT record_id FROM `sci_gpadistr`where user_Id=#{id}));")
	@Select("select paperinfo_Id,paperinfo_Author,paperinfo_Name,paperinfo_MName,paperinfo_CN,paperinfo_ISSN,paperinfo_Time,paperinfo_Lev,paperinfo_orglev, paperinfo_getGpa,userteam_getGpa,record_status from sci_paperinfo inner join sci_record on sci_paperinfo.paperinfo_Id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
    @Results({
    	@Result(id = true, property = "paperinfo_Id", column = "paperinfo_Id"),
        @Result(property = "paperinfo_Name", column = "paperinfo_Name"),
        @Result(property = "paperinfo_MName", column = "paperinfo_MName"),
        @Result(property = "paperinfo_Author", column = "paperinfo_Author"),
        @Result(property = "paperinfo_CN", column = "paperinfo_CN"),
        @Result(property = "paperinfo_ISSN", column = "paperinfo_ISSN"),
        @Result(property = "paperinfo_Time", column = "paperinfo_Time"),
        @Result(property = "paperinfo_getGpa",column = "paperinfo_getGpa"),
        @Result(property = "paperinfo_Lev", column = "paperinfo_Lev"),
        @Result(property = "paperinfo_orglev", column = "paperinfo_orglev"),
        @Result(property = "record_status",column="record_status"),
    	@Result(property = "user",column="paperinfo_Author",one=@One(select = "cn.gpa.zut.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
    	//@Result(property = "performance_Type",column="subjectinfo_sort",one=@One(select = "cn.gpa.zut.dao.IPerformance_typeDao.findById",fetchType = FetchType.EAGER)),
    	@Result(property = "sys_Dict",column="paperinfo_Lev",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
    	@Result(property = "sys_Ratio",column="paperinfo_orglev",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)),
    	@Result(property = "gpaDistr",column="userteam_getGpa")
    })
	List<Paper> findAllById(String id);
    //查找论文详情
	@Select("select * from sci_paperinfo where paperinfo_Id=#{id}")
	 Paper findByPaperId(String id);

	@Update("update sci_paperinfo set paperinfo_Name=#{paperinfo_Name},"
     		+ "paperinfo_MName=#{paperinfo_MName},"
     		+ "paperinfo_Author=#{paperinfo_Author},"
     		+ "paperinfo_CN=#{paperinfo_CN},paperinfo_ISSN=#{paperinfo_ISSN},"
     		+ "paperinfo_Time=#{paperinfo_Time},paperinfo_getGpa=#{paperinfo_getGpa},"
     		+ "paperinfo_Lev=#{paperinfo_Lev},paperinfo_orglev=#{paperinfo_orglev}"
     		+ "where paperinfo_Id=#{paperinfo_Id};")
	void update(Paper paper);
	
    /*
	 * @Result(property = "paperinfo_Author", column = "paperinfo_Author",javaType =
	 * User.class,one = @One(select = "cn.gpa.zut.dao.IUserDao.findById"))
	 * javaType = DictPara.class,one=@One(select = "cn.gpa.zut.dao.IDictParaDao.findById") 
	 * , javaType = DictRatio.class, one = @One(select = "cn.gpa.zut.dao.IDictRatioDao.findById") 
	 * 
	 */
    
	/*
	 * //查询用户名下论文信息
	 * 
	 * @Select("SELECT * from sci_paperinfo where paperinfo_id IN(SELECT recordinfo_id from sci_record where record_Id in( SELECT record_id FROM `sci_gpadistr`where user_Id=#{id}));"
	 * )
	 * 
	 * @Results({
	 * 
	 * @Result(id = true, property = "paperinfo_Id", column = "paperinfo_Id"),
	 * 
	 * @Result(property = "paperinfo_Name", column = "paperinfo_Name"),
	 * 
	 * @Result(property = "paperinfo_Author", column = "paperinfo_Author"),
	 * 
	 * @Result(property = "paperinfo_CN", column = "paperinfo_CN"),
	 * 
	 * @Result(property = "paperinfo_ISSN", column = "paperinfo_ISSN"),
	 * 
	 * @Result(property = "paperinfo_Time", column = "paperinfo_Time"),
	 * 
	 * @Result(property = "paperinfo_getGpa",column = "paperinfo_getGpa"),
	 * 
	 * @Result(property = "paperinfo_Lev", column = "paperinfo_Lev"),
	 * 
	 * @Result(property = "paperinfo_orglev", column = "paperinfo_orglev") })
	 * List<Paper> findAllById(String id);
	 */
	
	

}
