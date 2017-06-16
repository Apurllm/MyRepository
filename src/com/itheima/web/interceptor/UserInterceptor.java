package com.itheima.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 用户的过滤器
 * @author Administrator
 *
 */
public class UserInterceptor extends MethodFilterInterceptor{
	
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
	    User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user == null){
			//如果为空，则说明没有登录，后面就不会执行了
			return "login";
		}
		return invocation.invoke();
	}

}
