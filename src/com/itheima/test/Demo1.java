package com.itheima.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.domain.Customer;

/**
 * fastjson的使用
 * @author Administrator
 */
public class Demo1 {

	@Test
	public void run1(){
		List list = new ArrayList();
		Customer c = new Customer();
		c.setCust_id(2l);
		c.setCust_name("abc");
		c.setCust_phone("123");
		list.add(c);
		list.add(c);
//		String string = JSON.toJSONString(list);
		String string = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		//String cString = FastJsonUtil.toJSONString(c);
		System.out.println(string);
	}
	
}
