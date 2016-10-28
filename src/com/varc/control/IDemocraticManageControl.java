package com.varc.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.domain.IDemocraticManage;
import com.varc.domain.extension.IDemocraticManageExtension;
import com.varc.service.DemocraticManage.IDemocraticManageService;
import com.varc.service.factory.ServiceFactory;

/**
 * 民主管理控制层
 * 
 */
@Controller
@RequestMapping("/IDemocraticManage")
public class IDemocraticManageControl extends Control {

	/**
	 * 显示民事
	 */
	@RequestMapping("/find_guanlitianbao")
	public String find_guanlitianbao(HttpServletRequest request,
			HttpSession session, String biangengshijian) {
		IDemocraticManageService idemocraticManageService = ServiceFactory
				.getIDemocraticManageService();
		String address = this.isNull(request, session,
				"/WEB-INF/jsp/minzhuguanli/jsp/find_guanlitianbao.jsp");
		if (address
				.equals("/WEB-INF/jsp/minzhuguanli/jsp/find_guanlitianbao.jsp")) {
			List<IDemocraticManageExtension> idme = idemocraticManageService
					.find_guanlitianbao(request, session, biangengshijian);
			request.setAttribute("idme", idme);
		}
		return address;
	}

	// 修改民事
	@RequestMapping("/update_guanlitianbao")
	public String update_user(HttpServletRequest request, HttpSession session,
			@ModelAttribute IDemocraticManage idemocraticManage, int id) {
		IDemocraticManageService idemocraticManageService = ServiceFactory
				.getIDemocraticManageService();
		String address = this.isNull(request, session,
				"/WEB-INF/jsp/minzhuguanli/jsp/update_minzhuguanli.jsp");
		if (address
				.equals("/WEB-INF/jsp/minzhuguanli/jsp/update_minzhuguanli.jsp")) {
			if (idemocraticManageService.account_Verification(request,idemocraticManage)) {
				boolean flag = idemocraticManageService.update_guanlitianbao(
						request, idemocraticManage, id);			
				if (flag) {
					return "redirect:/jsp/update_success.html";
				}
			}
		}
		request.setAttribute("id", id);
		return address;
	}

	@RequestMapping("/insert_tianjiaminshi")
	public String insert_tianjiaminshi(HttpServletRequest request, HttpSession session,
			@ModelAttribute IDemocraticManage idemocraticManage) {
		IDemocraticManageService idemocraticManageService = ServiceFactory
				.getIDemocraticManageService();
		String address = this.isNull(request, session,
				"/WEB-INF/jsp/minzhuguanli/jsp/insert_tianjiaminshi.jsp");
		if (address
				.equals("/WEB-INF/jsp/minzhuguanli/jsp/insert_tianjiaminshi.jsp")) {
			if(idemocraticManageService.account_Verification(request,idemocraticManage)){
			idemocraticManageService.insert_guanlitianbao(request, session,
					idemocraticManage);
			return "redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	//显示所有的民事
		@RequestMapping("/findAll_guanlitianbao")
		public String findAll_guanlitianbao(HttpServletRequest request,
				HttpSession session, String biangengshijian) {
			IDemocraticManageService idemocraticManageService = ServiceFactory
					.getIDemocraticManageService();
			String address = this.isNull(request, session,
					"/WEB-INF/jsp/minzhuguanli/jsp/findAll_guanlitianbao.jsp");
			if (address
					.equals("/WEB-INF/jsp/minzhuguanli/jsp/findAll_guanlitianbao.jsp")) {
				List<IDemocraticManageExtension> idme = idemocraticManageService
						.findAll_guanlitianbao(request, session, biangengshijian);
				request.setAttribute("idme", idme);
			}
			return address;
		}
}