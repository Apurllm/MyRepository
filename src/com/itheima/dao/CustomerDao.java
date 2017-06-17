package com.itheima.dao;


import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;

public interface CustomerDao extends BaseDao<Customer>{

	public Linkman update(Customer customer);

//	public void save(Customer customer);
//	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
//	public Customer findById(Long cust_id);
//	public void delete(Customer customer);
//	public void update(Customer customer);
	
}
