package cn.gpa.zut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cn.gpa.zut.domain.Paper;
import cn.gpa.zut.domain.Patent;
import cn.gpa.zut.domain.Plateform;
import cn.gpa.zut.domain.Project;

public interface IPlateformDao {
	//查询所有论文信息
		@Select("select * from sci_plateforminfo")
		List<Plateform> findAll() throws Exception;
		
		//保存论文信息
		@Insert("insert into sci_plateforminfo(plateforminfo_id,plateforminfo_name,"
				+ "plateforminfo_organize,plateforminfo_lev,plateforminfo_starttime,"  
			+"plateforminfo_finishtime,plateforminfo_checklev,plateforminfo_getGpa)"
				+ " values(#{plateforminfo_id},#{plateforminfo_name},#{plateforminfo_organize},"
				+ "#{plateforminfo_lev},#{plateforminfo_starttime},#{plateforminfo_finishtime},"
				+ "#{plateforminfo_checklev},#{plateforminfo_getGpa})")
		void save(Plateform plateform);
		//查询用户名下信息
				@Select("select plateforminfo_id,plateforminfo_name,plateforminfo_organize,plateforminfo_lev,plateforminfo_starttime,"
						+ "plateforminfo_finishtime,plateforminfo_checklev,plateforminfo_getGpa,"
						+ "userteam_getGpa,record_status from sci_plateforminfo inner join sci_record on "
						+ "sci_plateforminfo.plateforminfo_id=sci_record.recordinfo_id INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id and sci_gpadistr.user_Id=#{id}")
			    @Results({
			    	@Result(id = true, property = "plateforminfo_id", column = "plateforminfo_id"),
			        @Result(property = "plateforminfo_name", column = "plateforminfo_name"),
			        @Result(property = "plateforminfo_organize", column = "plateforminfo_organize"),
			        @Result(property = "plateforminfo_lev", column = "plateforminfo_lev"),
			        @Result(property = "plateforminfo_starttime", column = "plateforminfo_starttime"),
			        @Result(property = "plateforminfo_finishtime", column = "plateforminfo_finishtime"),
			        @Result(property = "plateforminfo_checklev", column = "plateforminfo_checklev"),
			        @Result(property = "plateforminfo_getGpa", column = "plateforminfo_getGpa"),
			    	@Result(property = "gpaDistr",column="userteam_getGpa"),
			    	@Result(property = "record_status",column="record_status"),
			    	@Result(property = "user",column="plateforminfo_organize",one=@One(select = "cn.gpa.zut.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
			    	@Result(property = "sys_Dict",column="plateforminfo_lev",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
			    	@Result(property = "sys_Ratio",column="plateforminfo_checklev",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)),
			    })
				List<Plateform> findAllById(String id);
	           //查找单个
				@Select("select * from  sci_plateforminfo where plateforminfo_id=#{id} ")
				Plateform findById(String id);
				//更新数据
				@Update("update sci_plateforminfo set plateforminfo_name=#{plateforminfo_name},"
		        		+ "plateforminfo_organize=#{plateforminfo_organize},"
		        		+ "plateforminfo_lev=#{plateforminfo_lev},"
		        		+ "plateforminfo_starttime=#{plateforminfo_starttime},plateforminfo_finishtime=#{plateforminfo_finishtime},"
		        		+ "plateforminfo_checklev=#{plateforminfo_checklev},plateforminfo_getGpa=#{plateforminfo_getGpa} where plateforminfo_id=#{plateforminfo_id};")
				void update(Plateform plateform);
} 
