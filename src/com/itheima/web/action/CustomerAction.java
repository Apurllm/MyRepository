package com.itheima.web.action;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.FileUploadInterceptor;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Dict;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.utils.FastJsonUtil;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 客户的控制层
 * @author Administrator
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private static final long serialVersionUID = 113695314694166436L;
	
	//自己new一个JavaBean
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	//customerService属性，提供set方法
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
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

	/**
	 *	每页记录数 
	 *	设置为2
	 */
	private Integer pageSize = 2;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页查询
	 * 当前页，每页记录数，离线查询
	 *************************************
	 * 注意!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * 如果在list.jsp页面，有一个下拉选择框，那么下拉选择框value值必须为"",
	 * 否则在没有条件的情况下，option值为"请选择"，结果为空！！！
	 * @return
	 */
	public String findByPage(){
		//离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//拼接用户名条件
		String cust_name = customer.getCust_name();
		if(cust_name != null && !cust_name.trim().isEmpty()){
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		//拼接来源的条件
		Dict source = customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		//拼接级别的条件
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		PageBean<Customer> page = customerService.findByPage(pageCode, pageSize, criteria);
		//压栈
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("page", page);
		return "ok";
	}

	//跳转页面
	public String initAdd(){
		return "initAdd";	
	}
	
	/**
	 * private File upload----表示要上传的文件
	 * private String uploadFileName----表示要上传文件的名称
	 * private String uploadContentType----表示要上传文件的MIME类型
	 */
	private File upload;//要上传的文件
	private String uploadFileName;//上传文件名称
	private String uploadContentType;//上传文件的MIME类型
	//提供set方法
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	/**
	 * 新增客户
	 * @throws IOException 
	 */
	public String save() throws IOException{
		//上传图片
		if(uploadFileName != null){
			/**
			 * 文件名称
			 * 路径  
			 */
			String filename = UploadUtils.getUUIDName(uploadFileName);
			String path = "C:\\tomcat\\apache-tomcat-7.0.59\\webapps\\upload";
			File file = new File(path+"\\"+filename);
			FileUtils.copyFile(upload, file);
			//把上传文件的路径保存到数据库中
			customer.setFilePath(path+"\\"+filename);
		}
		customerService.save(customer);
		return "save";
	}
	
	/**
	 * 删除客户
	 * 要删除文件，就要先通过id查询，得到文件路径
	 */
	public String delete(){
		//通过id查询客户
		Customer customer1 = customerService.findById(customer.getCust_id());
		
		//获取文件上传的路径，然后删除客户
		String filepath = customer1.getFilePath();
		customerService.delete(customer1);
		
		//创建File对象，如果存在，删除
		File file = new File(filepath);
		if(file.exists()){
			file.delete();
		}
		return "delete";
	}
	
	/**
	 * 跳转到修改的页面
	 * 修改：先查再改
	 */
	public String initUpdate(){
		customer = customerService.findById(customer.getCust_id());
		return "initUpdate";
	}
	
	/**
	 * 修改
	 * @throws IOException 
	 */
	public String upload() throws IOException{
		/**
		 * >判断文件名是否为空
		 *   >不空：
		 *   >删除原来的文件
		 *   >保存新文件
		 *   >将新的路径到设置到customer中
		 * >更新数据库  
		 */
		if(uploadFileName != null){
			String oldFilePath = customer.getFilePath();
			if(oldFilePath != null && !oldFilePath.trim().isEmpty()){
				//如果路径存在，则删除文件
				File file = new File(oldFilePath);
				file.delete();
			}
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String newFilePath = "C:\\tomcat\\apache-tomcat-7.0.59\\webapps\\upload";
			File file = new File(newFilePath+"\\"+uuidName);
			FileUtils.copyFile(upload, file);
			//将新的路径到设置到customer中
			customer.setFilePath(newFilePath+"\\"+uuidName);
		}
		System.out.println(customer.getCust_id());
		customerService.update(customer);
		return "update";
	}
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public String findAll(){
		List<Customer> customers = customerService.findAll();
		String jsonString = FastJsonUtil.toJSONString(customers);
		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return "none";
	}
	
}




