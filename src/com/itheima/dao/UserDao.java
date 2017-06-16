package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao {

	User checkCode(String user_code);

	void regist(User user);

	User login(User user);

}
