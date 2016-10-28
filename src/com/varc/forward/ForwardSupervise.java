package com.varc.forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.control.Control;

@Controller
@RequestMapping("/forwardSupervise")
public class ForwardSupervise extends Control {

	/**	`
	 * 转发到修改监督页面
	 * @author sunrong
	 *
	 */
	@RequestMapping("/forward_update")
	public String forward_update(HttpServletRequest request,HttpSession session,int id){
		request.setAttribute("id", id);
		return this.isNull(request, session, "/WEB-INF/jsp/minzhujiandu/jsp/update_minzhujiandu.jsp");
	}
	
	/**
	 * 转发到添加民主监督页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/forward_add")
	public String forward_add(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session, "/WEB-INF/jsp/minzhujiandu/jsp/add_minzhujiandu.jsp");
	}
}
