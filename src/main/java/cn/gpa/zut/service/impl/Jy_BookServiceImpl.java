package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.Jy_BookDao;
import cn.gpa.zut.domain.JY_Book;
import cn.gpa.zut.domain.JY_ProjectLev;
import cn.gpa.zut.service.Jy_BookService;


@Service
@Transactional
public class Jy_BookServiceImpl implements Jy_BookService {
	
	@Autowired
	private Jy_BookDao bookdao;

	@Override
	public List<JY_ProjectLev> FindBookLev() throws Exception {
		// TODO Auto-generated method stub
		return bookdao.FindBookLev();
	}

	@Override
	public List<JY_ProjectLev> FindBookLanguage() throws Exception {
		// TODO Auto-generated method stub
		return bookdao.FindBookLanuage();
	}

	@Override
	public Boolean Add_Book(JY_Book jy_book) throws Exception {
		// TODO Auto-generated method stub
		return bookdao.Add_Book(jy_book);
	}

	@Override
	public List<JY_ProjectLev> FindBookBuiled() throws Exception {
		// TODO Auto-generated method stub
		return bookdao.FindBookbuild();
	}

	@Override
	public List<JY_ProjectLev> FindBookZhuBian() throws Exception {
		// TODO Auto-generated method stub
		return bookdao.FindBookZhuBian();
	}

	@Override
	public List<JY_Book> findbookById(String userId) {
		// TODO Auto-generated method stub
		return bookdao.findAllById(userId);
	}

}
