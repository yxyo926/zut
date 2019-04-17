package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.DictRatio;
import cn.gpa.zut.domain.Userteam;

public interface IUserteamService {
	public List<Userteam> findAll() throws Exception;
	public List<Userteam> getLev(String id) throws Exception;
	public void save(Userteam userteam);

}
