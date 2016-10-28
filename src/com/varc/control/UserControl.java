/**
 * 
 */
package com.varc.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.domain.User;
import com.varc.service.factory.ServiceFactory;
import com.varc.service.user.IUserService;

/**
 * 帐号操作类
 * @author danchaobing
 *
 */
@Controller
@RequestMapping("/userControl")
public class UserControl {
	/**
	 * 用户登入
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/login")
	public String login(@ModelAttribute User user,HttpServletRequest request,HttpSession session){
System.out.println("forward/login");
		IUserService iUserService=ServiceFactory.getIUserService();
		User returnUser=iUserService.loadUserByAccountAndPwd(user, request);
		if(returnUser!=null){
			session.setAttribute("account", returnUser.getAccount());
			session.setAttribute("rollId", returnUser.getRollId());
			if(returnUser.getRollId()==1){
				return "redirect:/jsp/index_villager.jsp";
			}else if(returnUser.getRollId()==2){
				return "redirect:/jsp/index_villager_committee.jsp";
			}else if(returnUser.getRollId()==3){
				return "redirect:/jsp/index_township.jsp";
			}else {
				return "redirect:/jsp/index_system.jsp";
			}
		}else{
			return "/WEB-INF/jsp/login.jsp";
		}
	}
	/**
	 * 用户退出
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/out")
	public String out(HttpSession session){
		session.removeAttribute("account");
		session.removeAttribute("rollId");
		return "redirect:/index.jsp";
	}
}
