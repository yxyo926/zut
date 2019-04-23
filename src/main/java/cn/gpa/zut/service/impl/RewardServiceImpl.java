package cn.gpa.zut.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gpa.zut.dao.IRewardDao;
import cn.gpa.zut.domain.Reward;

import cn.gpa.zut.service.IRewardService;

@Service
@Transactional
public class RewardServiceImpl implements IRewardService{
@Autowired
public IRewardDao rewardDao;
	@Override
	public List<Reward> findAll() throws Exception {
		// TODO Auto-generated method stub
		return rewardDao.findAll();
	}

	@Override
	public void save(Reward reward) throws Exception {
		// TODO Auto-generated method stub
		rewardDao.save(reward);
	}

	@Override
	public List<Reward> findAllById(String id) {
		// TODO Auto-generated method stub
		return rewardDao.findAllById(id);
	}

	@Override
	public Reward findAllByPaper(String paperId) {
		// TODO Auto-generated method stub
		return null;
	}

}