package com.itheima.service;

import java.util.List;

import com.itheima.dao.DictDao;
import com.itheima.domain.Dict;
/**
 * Dict字典业务层
 * @author Administrator
 *
 */
public class DictServiceImpl implements DictService {

	private DictDao dictDao;
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 通过类别代码查询
	 */
	public List<Dict> findByCode(String dict_type_code) {
		System.out.println("service:"+dictDao.findByCode(dict_type_code));
		return dictDao.findByCode(dict_type_code);
	}
	
	
}
