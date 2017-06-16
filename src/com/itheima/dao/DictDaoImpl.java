package com.itheima.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.Dict;

/**
 *Dict字典持久层
 */
@SuppressWarnings("all")
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	/**
	 * 通过类别代码查询
	 */
	public List<Dict> findByCode(String dict_type_code) {
		List<Dict> list = (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?", dict_type_code);
		System.out.println("dao:"+list);
		return list;
	}

}
