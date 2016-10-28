package com.varc.forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.control.Control;

@Controller
@RequestMapping("/ForwardManager")
public class ForwardManager extends Control {

	/**
	 * 转发到修改民事页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_update")
	public String forward_update(HttpServletRequest request,HttpSession session,int id){
		request.setAttribute("id", id);
		return this.isNull(request, session, "/WEB-INF/jsp/minzhuguanli/jsp/update_minzhuguanli.jsp");
	}
	
	//转发到添加民事页面
	@RequestMapping("/forward_insert")
	public String forward_insert(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session, "/WEB-INF/jsp/minzhuguanli/jsp/insert_tianjiaminshi.jsp");
	}
}
