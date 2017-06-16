package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

/**
 * 以后所有的DAO的接口都需要继承BaseDao接口
 * 自定义泛型接口
 * @author Administrator
 */
public interface BaseDao<T> {
	
	public void save(T t);
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	public T findById(Long id);
	public void delete(T t);
	public void update(T t);
	public List<T> findAll();
	
}
