package com.varc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.domain.MinZhuJianDu;
import com.varc.domain.extension.MinZhuJianDuExtension;
import com.varc.service.DemocraticSupervise.IDemocraticSuperviseService;
import com.varc.service.factory.ServiceFactory;

/**
 * 民主监督控制层
 * @author danchaobing
 *
 */

@Controller
@RequestMapping("/democraticSuperviseControl")
public class DemocraticSuperviseControl extends Control {
	
	/**
	 * 填报监督信息
	 * @author sunrong
	 *
	 */
	@RequestMapping("/add_minzhujiandu")
	public String addMinZhuJianDu(HttpServletRequest request,HttpSession session
			,@ModelAttribute MinZhuJianDu minzhujiandu) throws IOException{
		IDemocraticSuperviseService iDemocraticSuperviseService = ServiceFactory.getIDemocraticSuperviseService();
		String address = this.isNull(request, session,"/WEB-INF/jsp/minzhujiandu/jsp/add_minzhujiandu.jsp");
		if (address.equals("/WEB-INF/jsp/minzhujiandu/jsp/add_minzhujiandu.jsp")) {
			if(iDemocraticSuperviseService.info_Verification(request,minzhujiandu)){
				iDemocraticSuperviseService.addMinZhuJianDu(request, session,minzhujiandu);
				return "redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	/**
	 * 查询民主监督
	 * @author sunrong
	 */
	@RequestMapping("/find_minzhujiandus_info")
	public String findMinZhuJianDuByVillageIdAndTianBaoNianDu(HttpServletRequest request,
			HttpSession session,String page){
		String address=this.isNull(request, session, "/WEB-INF/jsp/minzhujiandu/jsp/find_minzhujiandus_info.jsp");
		if(address.equals("/WEB-INF/jsp/minzhujiandu/jsp/find_minzhujiandus_info.jsp")){
			if(page==null){
				page="1";
			}
			IDemocraticSuperviseService iDemocraticSuperviseService = ServiceFactory.getIDemocraticSuperviseService();
			List<MinZhuJianDuExtension> minZhuJianDu=iDemocraticSuperviseService.findMinZhuJianDu(page);
			int count=iDemocraticSuperviseService.findMinZhuJianDuCount();
			int pageCount=(int)Math.ceil(count/8.0);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("count", count);
			request.setAttribute("page", page);
			request.setAttribute("minzhujiandus", minZhuJianDu);
		}
		return address;
	}
	/**
	 * 查看民主监督信息
	 * @author sunrong
	 */
	@RequestMapping("/see_minzhujianduState")
	public String seeMinZhuJianDuState(HttpServletRequest request,HttpSession session,String id,String date,String page){
		String address=this.isNull(request, session, "/WEB-INF/jsp/minzhujiandu/jsp/see_minzhujianduState.jsp");
		if(address.equals("/WEB-INF/jsp/minzhujiandu/jsp/see_minzhujianduState.jsp")){
			if(page==null){
				page="1";
			}
			//总数
			IDemocraticSuperviseService iDemocraticSuperviseService = ServiceFactory.getIDemocraticSuperviseService();
			if(id==null){
				id=iDemocraticSuperviseService.findVillageInfoIdByAccount(session.getAttribute("account").toString()).toString();
			}
			List<MinZhuJianDuExtension> minzhujiandu = iDemocraticSuperviseService.findMinZhuJianDuByTimeAndId(id, date, request, page);
			Integer count = iDemocraticSuperviseService.findMinZhuJianDuCountByTimeAndId(id, date, request, page);
			int pageCount =0;
			if(count!=null){
				pageCount = (int) Math.ceil(count / 8.0);
			}
			request.setAttribute("minzhujiandus", minzhujiandu);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("count", count);
			request.setAttribute("page", page);
			request.setAttribute("id", id);
			request.setAttribute("date", date);
		}
		return address;
	}
	/**
	 * 修改民主监督信息
	 * @author sunrong
	 */
		@RequestMapping("/update_minzhujiandu")
		public String updateMinZhuJianDu(HttpServletRequest request, HttpSession session,
				@ModelAttribute MinZhuJianDu minzhujiandu) {
			IDemocraticSuperviseService iDemocraticSuperviseService = ServiceFactory
					.getIDemocraticSuperviseService();
			String address = this.isNull(request, session,
					"/WEB-INF/jsp/minzhujiandu/jsp/update_minzhujiandu.jsp");
			if (address.equals("/WEB-INF/jsp/minzhujiandu/jsp/update_minzhujiandu.jsp")) 
			{
				if (iDemocraticSuperviseService.updateMinZhuJianDu(request, minzhujiandu)){
					return "redirect:/jsp/update_success.html";
				}
			}
			request.setAttribute("id", minzhujiandu.getId());
			return address;
		}
}
