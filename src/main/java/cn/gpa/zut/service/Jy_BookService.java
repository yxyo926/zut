package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.JY_Book;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.domain.JY_jiaogai;
import cn.gpa.zut.domain.JY_pingtai;


public interface Jy_BookService {
	
	List<JY_ProjectLev> FindBookLev()throws Exception;
	
	List<JY_ProjectLev> FindBookLanguage()throws Exception;
	List<JY_ProjectLev> FindBookBuiled()throws Exception;
	List<JY_ProjectLev> FindBookZhuBian()throws Exception;
	
	Boolean Add_Book(JY_Book jy_book)throws Exception;

	List<JY_Book> findbookById(String userId);;

}
