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
import cn.gpa.zut.domain.Reward;

public interface IRewardDao {
	// 查询所有论文信息
	@Select("select * from sci_rewardinfo")
	List<Reward> findAll() throws Exception;

	// 保存论文信息
	@Insert("insert into sci_rewardinfo(reward_Id,reward_infoID,reward_person,"
			+ "reward_name,reward_lev,reward_Organization,reward_Time," + "reward_rank,reward_num,reward_getGpa)"
			+ " values(#{reward_Id},#{reward_infoID},"
			+ "#{reward_person},#{reward_name},#{reward_lev},#{reward_Organization},"
			+ "#{reward_Time},#{reward_rank},#{reward_num}," + "#{reward_getGpa})")
	void save(Reward reward);

	// 查询名下信息
	@Select("select reward_Id,reward_infoID,reward_person," + "reward_name,reward_Organization,reward_Time,"
			+ "reward_rank,reward_lev,reward_num,reward_getGpa,userteam_getGpa,record_status "
			+ "from sci_rewardinfo inner join sci_record on" + " sci_rewardinfo.reward_Id=sci_record.recordinfo_id"
			+ " INNER JOIN sci_gpadistr on sci_record.record_Id=sci_gpadistr.record_id "
			+ "and sci_gpadistr.user_Id=#{id}")
	@Results({ @Result(id = true, property = "reward_Id", column = "reward_Id"),
			@Result(property = "reward_infoID", column = "reward_infoID"),
			@Result(property = "reward_person", column = "reward_person"),
			@Result(property = "reward_name", column = "reward_name"),
			@Result(property = "reward_lev", column = "reward_lev"),
			@Result(property = "reward_Organization", column = "reward_Organization"),
			@Result(property = "reward_Time", column = "reward_Time"),
			@Result(property = "reward_rank", column = "reward_rank"),
			@Result(property = "reward_getGpa", column = "reward_getGpa"),
			@Result(property = "gpaDistr", column = "userteam_getGpa"),
			@Result(property = "record_status", column = "record_status"),
			@Result(property = "user", column = "reward_person", one = @One(select = "cn.gpa.zut.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
			//@Result(property = "performance_Type",column="subjectinfo_sort",one=@One(select = "cn.gpa.zut.dao.IPerformance_typeDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Dict",column="reward_lev",one=@One(select = "cn.gpa.zut.dao.ISys_DictDao.findById",fetchType = FetchType.EAGER)),
	    	@Result(property = "sys_Ratio",column="reward_rank",one=@One(select = "cn.gpa.zut.dao.ISys_RatioDao.findById",fetchType = FetchType.EAGER)), })
	List<Reward> findAllById(String id);
	
	//更新
		@Update("update sci_rewardinfo set reward_infoID=#{reward_infoID},"
	     		+ "reward_person=#{reward_person},"
	     		+ "reward_name=#{reward_name},"
	     		+ "reward_lev=#{reward_lev},reward_Organization=#{reward_Organization},"
	     		+ "reward_Time=#{reward_Time},reward_rank=#{reward_rank},"
	     		+ "reward_getGpa=#{reward_getGpa} "
	     		+ "where reward_Id=#{reward_Id};")
		void update(Reward reward);
		// 查找单个信息
	@Select("select * from sci_rewardinfo where reward_Id=#{id}")
	Reward findById(String id);
}
