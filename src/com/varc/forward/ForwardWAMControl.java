/**
 * 
 */
package com.varc.forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.control.Control;

/**
 * 信息与工作管理页面转发类
 * @author danchaobing
 *
 */
@Controller
@RequestMapping("/forwardWAM")
public class ForwardWAMControl extends Control{
	
	/**
	 * 转发到写邮件的页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_send_email")
	public String forward_send_eamil(HttpServletRequest request,HttpSession session,String receiveAccount){
		request.setAttribute("receiveAccount", receiveAccount);
		return this.isNull(request, session, "/WEB-INF/jsp/send_email.jsp");
	}
}
