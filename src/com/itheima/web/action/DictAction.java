package com.itheima.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.Dict;
import com.itheima.service.DictService;
import com.itheima.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 字典控制器 web层 
 * @author Administrator
 */
public class DictAction extends ActionSupport implements ModelDriven<Dict>{

	private static final long serialVersionUID = 864574376431875277L;
	
	private Dict dict = new Dict();
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	/**
	 * 通过类别代码查询
	 * @return
	 */
	public String findByCode(){
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
		String jsonString = FastJsonUtil.toJSONString(list);
		System.out.println("action:"+jsonString);
		//将json串写到浏览器端
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	

}
