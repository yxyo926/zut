package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.Assess;
import cn.gpa.zut.domain.JY_Book;
import cn.gpa.zut.domain.JY_ProjectLev;


public interface Jy_BookDao {
	
	@Select("select * from tch_projectlev where tch_category_id='教材类型' ")
	public List<JY_ProjectLev> FindBookLev() throws Exception;
	
	@Select("select * from tch_projectlev where tch_category_id='语言类型' ")
	public List<JY_ProjectLev> FindBookLanuage() throws Exception;
	
	@Select("select * from tch_projectlev where tch_category_id='教材立项' ")
	public List<JY_ProjectLev> FindBookbuild() throws Exception;
	@Select("select * from tch_projectlev where tch_category_id='主编身份' ")
	public List<JY_ProjectLev> FindBookZhuBian() throws Exception;
	
	@Insert("insert into tch_bookinfo(bookinfo_id,bookinfo_name,bookinfo_object,bookinfo_Tname,bookinfo_Enum,bookinfo_republic,bookinfo_Pname,bookinfo_buildlev,bookinfo_booklev,bookinfo_language,bookinfo_press) "
	+ "value(#{book_id},#{book_name},#{book_object},#{book_Tname},#{book_Enum},#{repiblic},#{book_Pname},#{build_id},#{booklev},#{language},#{book_press})")
	Boolean Add_Book(JY_Book jy_book)throws Exception;
	
	
	//查询用户名下信息
	@Select("select bookinfo_id,bookinfo_name,bookinfo_object,bookinfo_author,"
			+ "bookinfo_Tname,bookinfo_Enum,bookinfo_Pname,bookinfo_republic,bookinfo_buildlev, "
			+ "bookinfo_booklev,bookinfo_language,bookinfo_press,record_piont,userteam_getGpa "
			+ "from tch_bookinfo inner join tch_record on" + 
			" tch_bookinfo.bookinfo_id=tch_record.record_project_id INNER JOIN sci_gpadistr on "
			+ "tch_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
    @Results({
    	@Result(id = true, property = "book_id", column = "bookinfo_id"),
        @Result(property = "book_name", column = "bookinfo_name"),
        @Result(property = "book_object", column = "bookinfo_object"),
        @Result(property = "bookinfo_author", column = "bookinfo_author"),
        @Result(property = "book_Tname", column = "bookinfo_Tname"),
        @Result(property = "book_Enum", column = "bookinfo_Enum"),
        @Result(property = "book_Ename", column = "bookinfo_Pname"),
        @Result(property = "repiblic", column = "bookinfo_republic"),
        @Result(property = "build_id", column = "bookinfo_buildlev"),
        @Result(property = "language", column = "bookinfo_language"),
        @Result(property = "book_press", column = "bookinfo_press"),
        @Result(property = "booklev", column = "bookinfo_booklev"),
        @Result(property = "record_piont", column = "record_piont"),
    	@Result(property = "gpaDistr",column="userteam_getGpa")
    })
	List<JY_Book> findAllById(String id);
	
}
