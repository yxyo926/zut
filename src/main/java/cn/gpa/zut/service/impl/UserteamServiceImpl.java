package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IUserteamDao;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.IUserteamService;

@Service
@Transactional
public class UserteamServiceImpl implements IUserteamService {
	@Autowired
	private IUserteamDao userteamDao;

	@Override
	public List<Userteam> findAll() throws Exception {
		// TODO Auto-generated method stub
		return userteamDao.findAll();
	}

	@Override
	public List<Userteam> getLev(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
