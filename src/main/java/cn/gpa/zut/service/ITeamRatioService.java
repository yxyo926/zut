package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.TeamRatio;

public interface ITeamRatioService {
	List<TeamRatio> findAll() throws Exception;
}
