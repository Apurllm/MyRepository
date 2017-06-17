package com.itheima.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import jdk.nashorn.internal.objects.annotations.Where;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/**
	 * 更新客户
	 * 
	 */
	public Linkman update(Customer customer) {
		List<Linkman> list = (List<Linkman>) this.getHibernateTemplate().find("from Linkman where lkm_cust_id=?", customer.getCust_id());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

//	public void save(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//	}
//
//	/**
//	 * 分页查询
//	 * 总记录数：count(*)
//	 * 分页数据：limit 
//	 */
//	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,
//			DetachedCriteria criteria) {
//
//		PageBean<Customer> pageBean = new PageBean<Customer>();
//		pageBean.setPageCode(pageCode);
//		pageBean.setPageSize(pageSize);
//
//		criteria.setProjection(Projections.rowCount());
//		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
//		if(list != null && list.size() > 0){
//			//查询出总记录数
//			int totalCount = list.get(0).intValue();
//			pageBean.setTotalCount(totalCount);
//		}
//		criteria.setProjection(null);//先将之前的条件清空
//		// 分页查询数据，每页显示的数据  使用limit
//		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize, pageSize);
//		pageBean.setBeanList(beanList);
//		return pageBean;
//	}
//
//	/**
//	 * 通过id查询
//	 */
//	public Customer findById(Long cust_id) {
//		return this.getHibernateTemplate().get(Customer.class, cust_id);
//	}
//
//	/**
//	 * 删除客户
//	 */
//	public void delete(Customer customer) {
//		this.getHibernateTemplate().delete(customer);
//	}
//
//	/**
//	 * 更新客户信息
//	 */
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//	}
}
