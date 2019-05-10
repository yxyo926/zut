package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Conclude;
import cn.gpa.zut.domain.Patent;
import cn.gpa.zut.domain.Reward;

public interface IRewardService {
	List<Reward> findAll() throws Exception;
	void save(Reward reward)throws Exception;
	List<Reward> findAllById(String id);
	Reward   findById(String paperId);
	void update(Reward reward);

}
