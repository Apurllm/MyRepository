package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	/**
	 * 分页查询	
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return customerDao.findByPage(pageCode, pageSize, criteria);
	}

	/**
	 * 通过id查询
	 */
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	/**
	 * 删除客户
	 */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	/**
	 * 更新客户信息
	 */
	public Linkman update(Customer customer) {
		return customerDao.update(customer);
	}

	/**
	 * 查询所有客户
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
}
