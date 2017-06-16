package com.itheima.web.action;

import static org.hamcrest.CoreMatchers.nullValue;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.service.LinkmanService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman>{
	
	private Linkman linkman = new Linkman();
	public Linkman getModel() {
		return linkman;
	}
	
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}
	
	/**
	 * 分页查询联系人
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		//加条件  
		//联系人名称  和  客户名称
		String lkm_name = linkman.getLkm_name();
		if(lkm_name != null && !lkm_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		Customer customer = linkman.getCustomer();
		//这里的cust_id是Long类型，所以应该是 !=null
		//如果是String类型，则应该是  !xxx.trim().isEmpty();
		if(customer != null && customer.getCust_id() != null){
			criteria.add(Restrictions.eq("customer.cust_id", customer.getCust_id()));
		}
		
		PageBean<Linkman> page = linkmanService.findByPage(this.getPageCode(), this.getPageSize(), criteria);
		this.setVs("page", page);
		return "page";
	}
	
	/**
	 * 跳转到新增联系人的页面
	 */
	public String initAdd(){
		return "initAdd";
	}
	
	/**
	 * 新增联系人
	 */
     public String add(){
    	 linkmanService.add(linkman);
    	 return "add";
     }
	
}
