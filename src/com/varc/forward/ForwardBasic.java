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
 * 跳转页面控制层
 * @author danchaobing
 *
 */
@Controller
@RequestMapping("forward")
public class ForwardBasic extends Control{
	
	/**
	 * 转发到登入界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/logins")
	public String login(){
		return "/WEB-INF/jsp/login.jsp";
	}
	/**
	 * 转发到村民首页界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/index_villager")
	public String index_villager(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session,"/WEB-INF/jsp/index_villager.jsp");
	}
	/**
	 * 转发到村委会成员首页界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/index_villager_committee")
	public String index_villager_committee(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session,"/WEB-INF/jsp/index_villager_committee.jsp");
	}
	/**
	 * 转发到乡镇府首页界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/index_township")
	public String index_township(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session,"/WEB-INF/jsp/index_township.jsp");
	}
	/**
	 * 转发到系统管理员首页界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/index_system")
	public String index_system(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session,"/WEB-INF/jsp/index_system.jsp");
	}

	/**
	 * 转发到我的桌面界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request,HttpSession session){
		return this.isNull(request,session, "/WEB-INF/jsp/welcome.jsp");
	}
	/**
	 * 转发到更新个人信息界面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_update_user")
	public String foward_update_user(HttpServletRequest request,HttpSession session){
		return this.isNull(request,session, "/WEB-INF/jsp/update_user.jsp");
	}
	/**
	 * 转发到修改密码页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_update_pwd")
	public String foward_update_pwd(HttpServletRequest request,HttpSession session){
		return this.isNull(request,session, "/WEB-INF/jsp/update_pwd.jsp");
	}
	/**
	 * 转发到添加用户帐号页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_add_user")
	public String forward_add_user(HttpServletRequest request,HttpSession session,String villageinfoId){
		request.setAttribute("villageinfoId",villageinfoId);
		return this.isNull(request,session, "/WEB-INF/jsp/add_user.jsp");
	}

	/**
	 * 跳转到修改村庄页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_update_countrysideinfo")
	public String forward_update_contrysideinfo1(HttpServletRequest request,HttpSession session,String num) {
		return this.isNull(request, session, "/WEB-INF/jsp/update_countryside"+num+".jsp");
	}
	/**
	 * 跳转到系统管理员查找用户信息页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_find_update_user")
	public String forward_find_update_user(HttpServletRequest request,HttpSession session,String num) {
		return this.isNull(request, session, "/WEB-INF/jsp/find_update_user.jsp");
	}
	/**
	 * 跳转到系统管理员修改用户信息页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_system_update_user")
	public String forward_system_update_user(HttpServletRequest request,HttpSession session,String account){
			request.setAttribute("updateAccount", account);
			return this.isNull(request, session, "/WEB-INF/jsp/system_update_user.jsp");
	}	
	/**
	 * 跳转到删除用户页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_delete_user")
	public String forward_delete_user(HttpServletRequest request,HttpSession session){
			return this.isNull(request, session, "/WEB-INF/jsp/find_delete_user.jsp");
	}
	/**
	 * 跳转到增加村庄页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_add_countryside")
	public String forward_add_countryside(HttpServletRequest request,HttpSession session){
			return this.isNull(request, session, "/WEB-INF/jsp/add_countryside.jsp");
	}
	/**
	 * 跳转到查询用户的页面，用于修改用户权限
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_find_update_roll")
	public String forward_find_update_roll(HttpServletRequest request,HttpSession session){
		return this.isNull(request, session, "/WEB-INF/jsp/find_update_roll.jsp");
	}
	/**
	 * 跳转到修改用户权限页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_system_update_roll")
	public String forward_system_update_roll(HttpServletRequest request,HttpSession session,String account,String oldRollId,String villageinfoId){
		request.setAttribute("updateAccount", account);
		request.setAttribute("oldRollId", oldRollId);
		request.setAttribute("villageinfoId", villageinfoId);
		return this.isNull(request, session, "/WEB-INF/jsp/system_update_roll.jsp");
	}
}
