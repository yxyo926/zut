package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.DictRatio;

public interface IDictRatioService {

		public List<DictRatio> findAll() throws Exception;
		public List<DictRatio> getLev(String id) throws Exception;

}
