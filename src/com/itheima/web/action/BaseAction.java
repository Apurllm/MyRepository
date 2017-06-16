package com.itheima.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	/**
	 * 当前页
	 * 如果没传，默认为1.
	 * 传了为空，设置为1.
	 * 封装
	 */
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if(pageCode == null){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	public Integer getPageCode() {
		return pageCode;
	}
	public Integer getPageSize() {
		return pageSize;
	}


	/**
	 *	每页记录数 
	 *	设置为2
	 */
	private Integer pageSize = 2;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 调用值栈的set方法
	 */
	public void setVs(String key, Object obj){
		ActionContext.getContext().getValueStack().set(key, obj);
	}
	
	/**
	 * 调用值栈的push方法
	 */
	public void pushVs(Object obj){
		ActionContext.getContext().getValueStack().push(obj);
	}
	
	
}
