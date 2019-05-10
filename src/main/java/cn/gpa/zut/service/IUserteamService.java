package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Sys_Ratio;
import cn.gpa.zut.domain.Userteam;

public interface IUserteamService {
	public List<Userteam> findAll() throws Exception;
	public List<Userteam> getLev(String id) throws Exception;
	public void save(Userteam userteam);
	public Userteam findByRecordId(String record_Id);
	public Userteam findById(String userteamID);

}
