package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
	
}
