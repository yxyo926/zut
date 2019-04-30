package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.gpa.zut.domain.TeamRatio;

public interface ITeamRatioDao {
	@Select("select * from sci_teamratio")
	List<TeamRatio> findAll() throws Exception;
}
