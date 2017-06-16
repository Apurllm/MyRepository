package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.User;

/**
 * UserDao持久层
 * @author Administrator
 */

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 校验用户名是否存在
	 */
	public User checkCode(String user_code) {
		HibernateTemplate template = this.getHibernateTemplate();
		List<User> list = (List<User>) template.find("from User where user_code=?", user_code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 用户注册
	 */
	public void regist(User user) {
		HibernateTemplate template = this.getHibernateTemplate();
		template.save(user);
	}

	/**
	 * 登录功能
	 */
	public User login(User user) {
		//得到离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		//添加条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password",user.getUser_password()));
		criteria.add(Restrictions.eq("user_state", "1"));
		//获取模板
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
