package com.itheima.service;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.MD5Utils;

/**
 * UserService业务层
 * @author Administrator
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 校验用户名是否存在
	 */
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}

	/**
	 * 注册用户
	 */
	public void regist(User user) {
		String pwd = user.getUser_password();	
		user.setUser_password(MD5Utils.md5(pwd));
		user.setUser_state("1");
		userDao.regist(user);
	}

	/**
	 * 登录功能
	 */
	public User login(User user) {
		//需要先将密码加密再与数据库中的密码做比较
		String pwd = user.getUser_password();
		user.setUser_password(MD5Utils.md5(pwd));
		return userDao.login(user);

	}
	
	
}
