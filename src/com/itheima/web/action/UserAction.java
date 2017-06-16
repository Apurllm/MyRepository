package com.itheima.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 915490440378659914L;

	//用于封装表单数据
	private User user = new User();
	public User getModel() {
		return user;
	}

	//注入UserService，这里提供UserService属性
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 注册
	 * @return
	 */
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}
	
	/**
	 * 根据用户名，判断用户名是否已存在
	 */  
	public String checkCode(){
		//调用业务层方法查询用户
		User existUser = userService.checkCode(user.getUser_code());
		//获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			//判断是否存在
			if(existUser == null){
				writer.print("no");
			}else {
				writer.print("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 登录功能
	 * @return
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser != null){
			//保存用户信息到session域
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "index";	
		}else{
//			注意：转发时，保存到request域中的数据才能取到，重定向是两个请求！！！拿不到数据
//			ServletActionContext.getRequest().setAttribute("msg", "用户名或密码错误");
			return LOGIN;
		}
	}
	
	/**
	 * 退出
	 * @return
	 */
	public String exit(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");	
		return LOGIN;
	}
	
}

