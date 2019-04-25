package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import cn.gpa.zut.domain.ProjectPara;

public interface IProjectParaDao {

	@Select("select * from sci_projectpara")
	List<ProjectPara> findAll() throws Exception;

}
