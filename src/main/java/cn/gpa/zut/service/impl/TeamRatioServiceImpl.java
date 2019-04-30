package cn.gpa.zut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.ITeamRatioDao;
import cn.gpa.zut.domain.TeamRatio;
import cn.gpa.zut.service.ITeamRatioService;
@Service
@Transactional
public class TeamRatioServiceImpl implements ITeamRatioService {

	@Autowired
	private ITeamRatioDao teamRatioDao;
	@Override
	public List<TeamRatio> findAll() throws Exception {
		// TODO Auto-generated method stub
		return teamRatioDao.findAll();
	}

}
