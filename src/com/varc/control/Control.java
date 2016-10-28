/**
 * 
 */
package com.varc.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * @author danchaobing
 *
 */
public class Control implements WebBindingInitializer{
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// <3> 定义转换格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//true代表允许输入值为空
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	/**
	 * 判断用户是否有盗链行为
	 * @author danchaobing
	 *
	 */

	public String isNull(HttpServletRequest request,HttpSession session,String address){
		if(request.getHeader("referer")==null ){
			if(session.getAttribute("account")!=null){
				String  rollId=session.getAttribute("rollId").toString();
				if(rollId.equals("1")){
					return "/WEB-INF/jsp/index_villager.jsp";
				}else if(rollId.equals("2")){
					return "/WEB-INF/jsp/index_villager_committee.jsp";
				}else if(rollId.equals("3")){
					return "/WEB-INF/jsp/index_township.jsp";
				}else{
					return "/WEB-INF/jsp/index_system.jsp";
				}
			}else{
				return "/index.jsp";
			}
		}
		System.out.println("页面从哪里跳转过来："  + request.getHeader("referer"));
		return address;
	}
}
