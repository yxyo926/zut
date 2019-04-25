package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.TeamDao;
import cn.gpa.zut.domain.User;
import cn.gpa.zut.domain.Userteam;
import cn.gpa.zut.service.TeamService;



@Service
@Transactional
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamDao teamdao;
//	@Autowired
//	private Userteam team;

	public List<User> findAll_uer() throws Exception {
		// TODO Auto-generated method stub
		return teamdao.findAll_uer();
	}
	public List<User> findAllUserByCollege(String user_college) throws Exception {
		// TODO Auto-generated method stub
		return teamdao.findAllUserByCollege(user_college);
	}

	public List<User> findAllUserByUserName(String user_name) throws Exception {
		// TODO Auto-generated method stub
		return teamdao.findAllUserByName(user_name);
	}

	@Override
	public Boolean AddTeam(Userteam userteam) throws Exception {
		// TODO Auto-generated method stub
		
		
		return teamdao.AddTeam(userteam);
	}
	@Override
	public List<Userteam> findAll() throws Exception {
		// TODO Auto-generated method stub
		return teamdao.findAll();
	}

	
}
