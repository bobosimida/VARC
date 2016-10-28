package com.varc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.domain.CunWeiHui;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.User;
import com.varc.domain.VillageInfo;
import com.varc.domain.extension.CunWeiHuiExtension;
import com.varc.domain.extension.UserExtension;
import com.varc.service.BasicInfo.IBasicInfoService;
import com.varc.service.factory.ServiceFactory;

/**
 * 农村基本信息管理控制层
 * @author danchaobing
 *
 */
@Controller
@RequestMapping("/basicInfoControl")
public class BasicInfoControl extends Control{
	/**
	 * 查看个人信息
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/find_self_info")
	public String find_self_info(HttpServletRequest request,HttpSession session){
		IBasicInfoService iBasicInfoService=ServiceFactory.getIBasicInfoService();
		String address=this.isNull(request, session,"/WEB-INF/jsp/find_self_info.jsp");
		if(address.equals("/WEB-INF/jsp/find_self_info.jsp")){
			UserExtension user=iBasicInfoService.findUserByAccount(session.getAttribute("account").toString());
			request.setAttribute("user",user);
		}
		return address;
	}

	/**
	 * 修改个人信息
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/update_user")
	public String update_user(HttpServletRequest request,HttpSession session,@ModelAttribute User user){
		String address=this.isNull(request, session,"/forward/forward_update_user");
		if(address.equals("/forward/forward_update_user")){
			user.setAccount(session.getAttribute("account").toString());
			IBasicInfoService iBasicInfoService= ServiceFactory.getIBasicInfoService();
			boolean flag=iBasicInfoService.updateUserInfo(request,user);
			if(flag){
				return "redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	
	/**
	 * 查看个人村庄信息
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/find_countryside_info")
	public String find_countryside_info(HttpServletRequest request,HttpSession session){
		String address=this.isNull(request, session, "/WEB-INF/jsp/find_countryside_info.jsp");
		if(address.equals("/WEB-INF/jsp/find_countryside_info.jsp")){
			IBasicInfoService iBasicInfoService=ServiceFactory.getIBasicInfoService();
			VillageInfo villageInfo=iBasicInfoService.findVillageInfoByUserAccount
				(session.getAttribute("account").toString());
			List<CunWeiHuiExtension> cunWeiHui=iBasicInfoService.findCunWeiHuiByVillageinfoId(villageInfo.getId());
			request.setAttribute("cunWeiHui", cunWeiHui);
			request.setAttribute("villageInfo", villageInfo);
		}
		return address;
	}
	/**
	 * 更新密码
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/update_pwd")
	public String update_pwd(HttpServletRequest request,HttpSession session,String  oldPwd,String newPwd,String newPwdA){
		String address=this.isNull(request, session, "/forward/forward_update_pwd");
		if(address.equals("/forward/forward_update_pwd")){
			boolean flag=ServiceFactory.getIBasicInfoService().updateUserPwd
						(request,session.getAttribute("account").toString(),oldPwd, newPwd,newPwdA);
			if(flag){
				address="redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	@RequestMapping("/reset_pwd")
	public void update_pwd(HttpServletRequest request,HttpSession session,String account){
		String address=this.isNull(request, session, "/forward/reset_pwd");
		if(address.equals("/forward/reset_pwd")){
			ServiceFactory.getIBasicInfoService().resetPwd(account);
		}
	}
	/**
	 * 查询村委会成员详细信息页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/find_cunWeiHuiMerber")
	public String find_cunWeiHuiMerber(HttpServletRequest request,HttpSession session,int id){
		String address=this.isNull(request,session, "/WEB-INF/jsp/find_cunWeiHuiMerber.jsp");
		if(address.equals( "/WEB-INF/jsp/find_cunWeiHuiMerber.jsp")){
			IBasicInfoService iBasicInfoService=ServiceFactory.getIBasicInfoService();
			User user=iBasicInfoService.findUserById(id);
			request.setAttribute("user", user);
			CunWeiHui cunWeiHui=iBasicInfoService.findCunWeiHuiMember(user.getId());
			request.setAttribute("cunWeiHui", cunWeiHui);
			if(cunWeiHui.getMinzhuxuanjuId()!=null){
				MinZhuXuanJu minZhuXuanJu=iBasicInfoService.findMinZhuXuanJuByMinZhuXuanJuId(cunWeiHui.getMinzhuxuanjuId());
				request.setAttribute("minZhuXuanJu", minZhuXuanJu);
			}
		}
		return address;
	}
	/**
	 * 添加用户
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/add_user")
	public String add_user(HttpServletRequest request,HttpSession session,@ModelAttribute User user,String zhiweiId,String oldUserId){
		String address=this.isNull(request,session, "/forward/forward_add_user");
		if(address.equals( "/forward/forward_add_user")){
			if(ServiceFactory.getIBasicInfoService().addUser(user, request,zhiweiId, oldUserId)){
				return "redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	/**
	 * 跟新村庄信息
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/update_countryside")
	public String update_countrysideinfo(HttpServletRequest request,HttpSession session,@ModelAttribute VillageInfo villageInfo,String num){
		String address=this.isNull(request,session, "/forward/forward_update_countrysideinfo");
		if(ServiceFactory.getIBasicInfoService().updateVillageInfoByUserAccount(session.getAttribute("account").toString(), villageInfo, request)){
			return "redirect:/jsp/update_success.html";
		}
		return address;
	}
	/**
	 * 返回查找到的所有村庄，根据客服端输入的村庄id查找
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/find_countrysides_info")
	public String find_countrysides_info(HttpServletRequest request,HttpServletResponse response,String id){
		VillageInfo villageInfo=ServiceFactory.getIBasicInfoService().findVillageInfoById(id, request);
	
		  JSONObject json=new JSONObject();
			json.put("villageinfo", villageInfo);
			PrintWriter out;
			try {
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.print(json.toString());
			} catch (IOException e) {
				return "/jsp/404.html";
			}
			
		return null;
	}	
	/**
	 * 转发到村庄具体信息页面上，村庄信息是根据前台传过来的村庄id查询
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/find_countrysideinfo_byId")
	public String find_countrysides_info(HttpServletRequest request,HttpSession session,String id){
		String address=this.isNull(request, session, "/WEB-INF/jsp/find_countryside_info.jsp");
		if(address.equals("/WEB-INF/jsp/find_countryside_info.jsp")){
			IBasicInfoService iBasicInfoService=ServiceFactory.getIBasicInfoService();
			VillageInfo villageInfo=iBasicInfoService.findVillageInfoById(id, request);
			List<CunWeiHuiExtension> cunWeiHui=iBasicInfoService.findCunWeiHuiByVillageinfoId(villageInfo.getId());
			request.setAttribute("cunWeiHui", cunWeiHui);
			request.setAttribute("villageInfo", villageInfo);
		}
		return address;
	}

	@RequestMapping("/find_zhiwei_isUse")
	public String find_zhiwei_isUse(HttpServletRequest request,
			HttpServletResponse response, String zhiWeiId, String villageinfoId) {
		JSONObject json = new JSONObject();
		CunWeiHui cunWeiHui = new CunWeiHui();
		cunWeiHui.setZhiweiId(zhiWeiId);
		cunWeiHui.setVillageinfoId(Integer.parseInt(villageinfoId));
		Integer userId = ServiceFactory.getIBasicInfoService().zhiWeiIsUse(cunWeiHui);
		json.put("userId", userId);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print(json.toString());
		} catch (IOException e) {
			return "/jsp/404.html";
		}

		return null;
	}

	/**
	 * 系统管理员通过输入账号查找用户
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/find_update_user")
	public String find_update_user(HttpServletRequest request,HttpServletResponse response,String account){
			UserExtension user=ServiceFactory.getIBasicInfoService().findUserByAccount(account);
			JSONObject json=new JSONObject();
			json.put("user", user);
			PrintWriter out;
			try {
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.print(json.toString());
			} catch (IOException e) {
				return "/jsp/404.html";
			}
			
		return null;
	}	
	/**
	 * 管理员修改信息
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/system_update_user")
	public String system_update_user(HttpServletRequest request,HttpSession session,String updateAccount,@ModelAttribute User user){
		String address=this.isNull(request, session, "/WEB-INF/jsp/system_update_user.jsp");
		if(address.equals("/WEB-INF/jsp/system_update_user.jsp")){
			user.setAccount(updateAccount);
			boolean flag=ServiceFactory.getIBasicInfoService().updateUserInfo(request,user);
			if(flag){
				return "redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	/**
	 * 修改用户角色权限
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/system_update_roll")
	public String system_update_roll(HttpServletRequest request,HttpSession session,String updateAccount,
			String oldRollId,String rollId,String villageinfoId,String zhiweiId,String oldUserId){
		String address=this.isNull(request, session, "/WEB-INF/jsp/system_update_roll.jsp");
		if(address.equals("/WEB-INF/jsp/system_update_roll.jsp")){
			User user=new User();
			user.setAccount(updateAccount);
			user.setRollId(Integer.parseInt(rollId));
			user.setVillageinfoId(Integer.parseInt(villageinfoId));
			boolean flag=ServiceFactory.getIBasicInfoService().updateRoll(request,user,oldRollId,zhiweiId,oldUserId);
			if(flag){
				return "redirect:/jsp/update_success.html";
			}
			request.setAttribute("updateAccount", updateAccount);
			request.setAttribute("villageinfoId", villageinfoId);
			request.setAttribute("oldRollId", oldRollId);
		}
		return address;
	}
	/**
	 * 删除用户
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/delete_user")
	public String delete_user(HttpServletRequest request,HttpSession session,String account,String page,String id){
		String address=this.isNull(request, session, "redirect:/jsp/update_success.html");
		if(address.equals("redirect:/jsp/update_success.html")){
			ServiceFactory.getIBasicInfoService().deleteUserByAccount(account);
			if(page!=null && id!=null){
				address=this.find_allUser_info(request, session, page, id);
			}
		}
		return address;
	}
	/**
	 * 添加村庄
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/add_countryside")
	public String add_countryside(HttpServletRequest request,HttpSession session,@ModelAttribute VillageInfo villageInfo){
		String address=this.isNull(request, session, "/WEB-INF/jsp/add_countryside.jsp");
		if(address.equals("/WEB-INF/jsp/add_countryside.jsp")){
			int id=ServiceFactory.getBasicSingleton().addVilageinfo(request, villageInfo);
			if(id !=0){
				request.setAttribute("villageinfoId", id);
				return "/jsp/add_villageinfo.jsp";
			}
		}
		return address;
	}
	/**
	 * 跳转到查找村庄页面,并分页查询所有村庄
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/forward_find_countrysides_info")
	public String forward_find_countrysides_info(HttpServletRequest request,HttpSession session,String page){
		String address= this.isNull(request,session, "/WEB-INF/jsp/find_countrysides_info.jsp");
		if(address.equals("/WEB-INF/jsp/find_countrysides_info.jsp")){
			if(page==null){
				page="1";
			}
			IBasicInfoService iBasicInfoService=ServiceFactory.getIBasicInfoService();
			List<VillageInfo> vil=iBasicInfoService.findAllVillageinfo(page);
			int count=iBasicInfoService.findNumberOfVillageInfo();
			int pageCount=(int)Math.ceil(count/6.0);
			request.setAttribute("count", count);
			request.setAttribute("vil", vil);
			request.setAttribute("page", page);
			request.setAttribute("pageCount", pageCount);
		}
		return address;
	}
	@RequestMapping("/find_allUser_info")
	public String find_allUser_info(HttpServletRequest request,HttpSession session,String page,String id){
		String address= this.isNull(request,session, "/WEB-INF/jsp/find_allUser_info.jsp");
		if(address.equals("/WEB-INF/jsp/find_allUser_info.jsp")){
			IBasicInfoService iBasicInfoService=ServiceFactory.getIBasicInfoService();
			if(page==null){
				page="1";
			}
			List<UserExtension> users=iBasicInfoService.findAllUserByVillageInfoId(id, page);
			int count=iBasicInfoService.findCountUserByVillageInfoId(id);
			int pageCount=(int)Math.ceil(count/8.0);
			request.setAttribute("users", users);
			request.setAttribute("count",count);
			request.setAttribute("page", page);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("id", id);
		}
		return address;
	}
	
}
