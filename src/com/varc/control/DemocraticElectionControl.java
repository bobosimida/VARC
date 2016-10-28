package com.varc.control;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varc.domain.HouXuanRen;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.TouPiao;
import com.varc.domain.extension.HouXuanRenExtension;
import com.varc.domain.extension.MinZhuXuanJuExtension;
import com.varc.domain.extension.TouPiaoExtension;
import com.varc.domain.extension.UserExtension;
import com.varc.service.factory.ServiceFactory;

/**
 * 民主选举管理控制层
 * @author danchaobing
 *
 */
@Controller
@RequestMapping("/democraticElectionControl")
public class DemocraticElectionControl extends Control{
		
	
	/**
	 * 跳转到添加民主选举信息页面，跳转前做检测该村是否有未完成的选举
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/_add_minzhuxuanju")
	public String forward_add_minzhuxuanju(HttpServletRequest request,HttpSession session){
		String address=this.isNull(request, session, "/WEB-INF/jsp/add_minzhuxuanju.jsp");
		if(address.equals("/WEB-INF/jsp/add_minzhuxuanju.jsp")){
			
		}
		return address;
	}
	/**
	 * 填报选举信息
	 * @author danchaobing
	 * @throws IOException 
	 *
	 */
	@RequestMapping("/add_minzhuxuanju")
	public String add_minzhuxuanju(HttpServletRequest request,HttpSession session
			,String accounts
			,@ModelAttribute MinZhuXuanJu minZhuXuanJu
			,@RequestParam(value = "file", required = false) MultipartFile file
			) throws IOException{
		
		String address=this.isNull(request, session, "/democraticElectionControl/_add_minzhuxuanju");
		boolean finded = false;
		if(address.equals( "/democraticElectionControl/_add_minzhuxuanju")){
//			在添加申请执念查看是否还有未完成的申请 0,1,3 
			finded =  ServiceFactory.getIDemocraticElectionService().findNotFinishApply(request, minZhuXuanJu);
			if(!finded) {
				boolean flag2 = ServiceFactory.getIDemocraticElectionService().createMinZhuXuanJU(minZhuXuanJu,
						request,accounts,file,session);
				if(flag2) {
					return "redirect:/jsp/update_success.html";
				} else {
					return address;
				}
			} else {
				return "redirect:/jsp/applyFailure.html";
			}
		}
		return address;
	}
	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			int id) throws IOException {
		List<MinZhuXuanJuExtension> minZhuXuanJuExtensions = ServiceFactory.getIDemocraticElectionService().findMinZhuXuanJuExtension();
		if(minZhuXuanJuExtensions != null) {
			byte[] buffer = new byte[minZhuXuanJuExtensions.get(id).getMinZhuXuanJu().getShishifangan().length + 1024];
			//改文件编码
			String fileName=new String(minZhuXuanJuExtensions.get(id).getMinZhuXuanJu().getFilename().getBytes("utf-8"),"ISO-8859-1");
			response.addHeader("Content-Disposition","attachment;filename=" + fileName);
			OutputStream out = response.getOutputStream();  
			buffer = minZhuXuanJuExtensions.get(id).getMinZhuXuanJu().getShishifangan();
			out.write(buffer);
		}
	}
	/**
	 * 
	 * <p>Title: handle_minzhuxuanju</p>
	 * <p>Description: </p>
	 * @author   HuaJiangbo
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping("/handle_minzhuxuanju")
	public String handle_minzhuxuanju(HttpServletRequest request,HttpSession session) {
		String address=this.isNull(request, session, "/WEB-INF/jsp/handle_minzhuxuanju.jsp");
		List<MinZhuXuanJuExtension> minZhuXuanJuExtensions = ServiceFactory.getIDemocraticElectionService().findMinZhuXuanJuExtension();
		if(minZhuXuanJuExtensions != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(MinZhuXuanJuExtension minZhuXuanJuExtension: minZhuXuanJuExtensions) {
				minZhuXuanJuExtension.setXuanjuri(minZhuXuanJuExtension.getMinZhuXuanJu().getXuanjuri());
				minZhuXuanJuExtension.setTianbaoniandu(sdf.format(minZhuXuanJuExtension.getMinZhuXuanJu().getTianbaoniandu()));
			}
		}
		request.setAttribute("minZhuXuanJuExtensions", minZhuXuanJuExtensions);
		return address;
	}
	/**
	 * 
	 * <p>Title: handle_minzhuxuanju_update</p>
	 * <p>Description: </p>
	 * @author   HuaJiangbo
	 * @param request
	 * @param session
	 * @param shenqingzhuangtai
	 * @param id
	 * @return String
	 */
	 @RequestMapping("handle_minzhuxuanju_update/{shenqingzhuangtai}/{id}")
	 public String handle_minzhuxuanju_update(HttpServletRequest request, HttpSession session,
			 @PathVariable  int shenqingzhuangtai,
			 @PathVariable  int id
			 ) {
		int zhuangtai = shenqingzhuangtai;
		int minzhuxuanjuId = id;
		request.setAttribute("zhuangtai", zhuangtai);
		request.setAttribute("minzhuxuanjuId", minzhuxuanjuId);
		 String address=this.isNull(request, session, "/WEB-INF/jsp/handle_minzhuxuanju_updateState.jsp");
		 return address;
	 }
	 /**
	  * <p>Title: handle_minzhuxuanju_update_db</p>
	  * <p>Description: 更新（同意、不同意）民主选举申请/更新（同意、不同意）民主选举结束申请</p>
	  * @author   HuaJiangbo
	  * @param request
	  * @param session
	  * @param minzhuxuanju
	  * @return String
	  */
	@RequestMapping("/handle_minzhuxuanju_update_db") 
	public String handle_minzhuxuanju_update_db(HttpServletRequest request, HttpSession session,
			@ModelAttribute MinZhuXuanJu minzhuxuanju) {
//		String account = (String)session.getAttribute("account");
//		User user = ServiceFactory.getIDemocraticElectionService().findUserByAccount(account);
		boolean flag = false;
		String address=this.isNull(request, session, "/democraticElectionControl/handle_minzhuxuanju");
		if("/democraticElectionControl/handle_minzhuxuanju".equals(address)) {
			if(minzhuxuanju != null) {
				//进行数据库的跟新,其中包括删除候选人表，通过民主选举表ID，当不同意民主选举的时候。
				flag = ServiceFactory.getIDemocraticElectionService().updateMinZhuXuanJuState(minzhuxuanju);
			} 
		}
		if(flag == true) {
			return "redirect:/jsp/update_success.html";
		} else {
			return address;
			}
	}
	
	@RequestMapping("/checkPresentXuanJuState") 
	public String checkPresentXuanJuState(HttpServletRequest request, HttpSession session) {
		String address=this.isNull(request, session, "/democraticElectionControl/checkPresentXuanJuState");
		if("/democraticElectionControl/checkPresentXuanJuState".trim().equals(address)) {
				int villageinfoId = ServiceFactory.getIBasicInfoService().findUserByAccount(session.getAttribute("account").toString()).
						getUser().getVillageinfoId();
				MinZhuXuanJu minZhuXuanJu = ServiceFactory.getIDemocraticElectionService().
						findLastMinZhuXuanJuByVillageinfoId(villageinfoId, request);
				String villageName = ServiceFactory.getIDemocraticElectionService().findVillageNameById(villageinfoId, request);
				int maxJieShu = ServiceFactory.getIDemocraticElectionService().findMaxJieShuFromCunWeiHuiByVillageinfoId(villageinfoId);
				if(minZhuXuanJu != null && villageName != "" && maxJieShu != 0) {
					request.setAttribute("minZhuXuanJu", minZhuXuanJu);
					request.setAttribute("villageName", villageName);
					request.setAttribute("maxJieShu", maxJieShu);
					return "/WEB-INF/jsp/checkPresentXuanJuStat.jsp";
				} else{
					return "redirect:/jsp/nominzhuxuanju.html";
				}
		}
		return address;
	}
	
	/**
	 * <p>Title: applyXuanJuFinish</p>
	 * <p>Description: </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @param request
	 * @param session
	 * @return String
	 * @throws ParseException 
	 */
	@RequestMapping("/applyXuanJuFinish/{id}")
	public String applyXuanJuFinish(@PathVariable int id, HttpServletRequest request, HttpSession session) throws ParseException {
		MinZhuXuanJu minZhuXuanJu = ServiceFactory.getIDemocraticElectionService().
				findMinZhuXuanJuById(id, request);
		boolean flag = true;
		if(minZhuXuanJu != null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(minZhuXuanJu.getXuanjuri());
//			Date sevenDaysLater = new Date(date.getTime()+ 7*24*60*60*1000);
//			flag = sevenDaysLater.before(new Date());
			request.setAttribute("minZhuXuanJu", minZhuXuanJu);
		} 
		String address=this.isNull(request, session, "/WEB-INF/jsp/applyXuanJuFinish.jsp");
		if("/WEB-INF/jsp/applyXuanJuFinish.jsp".trim().equals(address)) {
			if(flag) {
				return address;
			} else {
				return "redirect:/jsp/dateErr.html";
			}
		} else {
			return "redirect:/jsp/index_villager.jsp";
		}
		
	}
	
	@RequestMapping("/applyXuanJuFinish_updateState")
	public String applyXuanJuFinishUpdateState (@ModelAttribute MinZhuXuanJu minZhuXuanJu,
			HttpServletRequest request,HttpSession session) {
		String address = this.isNull(request, session, "/democraticElectionControl/applyXuanJuFinish_updateState");
		boolean finded = false;
		if("/democraticElectionControl/applyXuanJuFinish_updateState".trim().equals(address)) {
			if(minZhuXuanJu != null) {
				MinZhuXuanJu minZhuXuanJu2 = ServiceFactory.getIDemocraticElectionService().findMinZhuXuanJuById(minZhuXuanJu.getId(), request);
				if(minZhuXuanJu2 != null) {
					minZhuXuanJu2.setShenqingzhuangtai(minZhuXuanJu.getShenqingzhuangtai());
					finded = ServiceFactory.getIDemocraticElectionService().updateMinZhuXuanJuState(minZhuXuanJu2);
				} else {
					return "/democraticElectionControl/checkPresentXuanJuState";
				}
			}
			if(finded) {
				return "redirect:/jsp/update_success.html";
			} else {
				return "/democraticElectionControl/checkPresentXuanJuState";
			}
		} else {
			return "redirect:/jsp/index_villager.jsp";
		}
	}
	/**
	 * <p>Title: minzhuxuanjuEnter</p>
	 * <p>Description: 村名进入民主选举入口</p>
	 * @author   HuaJiangbo
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping("/minzhuxuanju_enter")
	public String minzhuxuanjuEnter(HttpServletRequest request, HttpSession session) {
		String address = this.isNull(request, session, "/democraticElectionControl/minzhuxuanju_enter");
		String account = (String) session.getAttribute("account");
		UserExtension userExtension = null;
		MinZhuXuanJu minZhuXuanJu = null;
		TouPiao touPiao = new TouPiao();
		List<HouXuanRenExtension> houXuanRenExtensions = null;
		if("/democraticElectionControl/minzhuxuanju_enter".trim().equals(address)) {
			userExtension = ServiceFactory.getIBasicInfoService().findUserByAccount(account);
			System.out.println("account=="+account);
			System.out.println(userExtension);
			minZhuXuanJu = ServiceFactory.getIDemocraticElectionService().findLastMinZhuXuanJuByVillageinfoId(
					userExtension.getUser().getVillageinfoId(), request);
			if(minZhuXuanJu!=null){
				//未满18岁不能进行投票
				if(Integer.parseInt(userExtension.getUser().getAge()) >=18) {
					//没有发起民主选举申请或民主选举已经结束
					if(Integer.parseInt(minZhuXuanJu.getShenqingzhuangtai()) == 1) {
						//判断该用户是否已经投票
						touPiao.setUserId(userExtension.getUser().getId());
						touPiao.setMinzhuxuanjuId(minZhuXuanJu.getId());
						TouPiaoExtension touPiaoExtension = ServiceFactory.getIDemocraticElectionService().findTouPiaoExtensionByuserIdAndminzhuxuanjuId(touPiao);
						if(touPiaoExtension == null) {
							houXuanRenExtensions = ServiceFactory.getIDemocraticElectionService().findHouXuanRenExtensionsByMinZhuXuanJuId
									(minZhuXuanJu.getId(), request);
							request.setAttribute("houXuanRenExtensions", houXuanRenExtensions); 
							return "/WEB-INF/jsp/minzhuxuanju_enter.jsp";
						} else {
							return "redirect:/jsp/minzhuxuanju_enter_err_voted.html";
						}
						
					} else {
						return "redirect:/jsp/minzhuxuanju_enter_err.html";
					}
				} else {
					return "redirect:/jsp/tooYoung.html";
				}
			}else{
				return "redirect:/jsp/nominzhuxuanju.html";
			}
		} else {
			return "redirect:/jsp/index_villager.jsp";
		}
		
	}
	
	@RequestMapping("/minzhuxuanju_submit")
	public String minzhuxuanjuSubmit(HttpServletRequest request, HttpSession session ,
			int userId,int minzhuxuanjuId) {
		HouXuanRen houXuanRen = new HouXuanRen();
		houXuanRen.setUserId(userId);
		houXuanRen.setMinzhuxuanjuId(minzhuxuanjuId);
		String account = "";
		String address = this.isNull(request, session, "/democraticElectionControl/minzhuxuanju_submit");
		if("/democraticElectionControl/minzhuxuanju_submit".trim().equals(address)) {
			//拿到当前投票者信息
			account = (String)session.getAttribute("account");
			UserExtension userExtension = ServiceFactory.getIBasicInfoService().findUserByAccount(account);
			if(userExtension != null) {
				//拿到被投票者信息 
				HouXuanRen houXuanRen2 = ServiceFactory.getIDemocraticElectionService().findHouXuanRenByuserIdAndminzhuxuanjuId(houXuanRen);
				//进行投票操作，投票表加入投票者信息，候选人表票数 + 1
				boolean vote = ServiceFactory.getIDemocraticElectionService().vote(houXuanRen2,userExtension.getUser());
				if(vote) {
					return "redirect:/jsp/success.html";
				} else {
					return "redirect:/jsp/vote_failure.html";
				}
			} else {
				return "redirect:/jsp/index_villager.jsp";
			}
		}else {
			return "redirect:/jsp/index_villager.jsp";
		}
	}
	
	@RequestMapping("/see_minzhuxuanjuState")
	public String check_minzhuxuanju(HttpServletRequest request, HttpSession session) {
		MinZhuXuanJu minZhuXuanJu = null;
//		HouXuanRen houXuanRen = new HouXuanRen();
		UserExtension userExtension = null;
		List<HouXuanRenExtension> houXuanRenExtensions = null;
		//拿到当前用户信息
		String account = (String)session.getAttribute("account");
		userExtension = ServiceFactory.getIBasicInfoService().findUserByAccount(account);
		String address = this.isNull(request, session, "/democraticElectionControl/see_minzhuxuanjuState");
		
		if("/democraticElectionControl/see_minzhuxuanjuState".trim().equals(address)) {
			if(userExtension != null) {
				//拿到最近一次民主选举信息
				minZhuXuanJu = ServiceFactory.getIDemocraticElectionService().findLastMinZhuXuanJuByVillageinfoId(
						userExtension.getUser().getVillageinfoId(), request);
				//找到该村的最大届数
				int maxJieShu = ServiceFactory.getIDemocraticElectionService().findMaxJieShuFromCunWeiHuiByVillageinfoId(
						userExtension.getUser().getVillageinfoId());
				if(minZhuXuanJu != null) {
					String tianbaoniandu = new SimpleDateFormat("yyyy-MM-dd").format(minZhuXuanJu.getTianbaoniandu());
					
					request.setAttribute("tianbaoniandu", tianbaoniandu);
					request.setAttribute("minZhuXuanJu", minZhuXuanJu);
					request.setAttribute("userExtension", userExtension);
					request.setAttribute("maxJieShu", maxJieShu+1);
					//拿到最近一次民主选举候选人信息
					houXuanRenExtensions = ServiceFactory.getIDemocraticElectionService().
							findHouXuanRenExtensionsByMinZhuXuanJuId(minZhuXuanJu.getId(), request);
					if(houXuanRenExtensions != null) {
						request.setAttribute("houXuanRenExtensions", houXuanRenExtensions);
					} else {
						request.setAttribute("houXuanRenErr", "没有找到对应候选人");
					}
					//拿到本人参与民主选举的投票信息
					TouPiao touPiao = new TouPiao();
					touPiao.setUserId(userExtension.getUser().getId());
					touPiao.setMinzhuxuanjuId(minZhuXuanJu.getId());
					TouPiaoExtension touPiaoExtension = ServiceFactory.getIDemocraticElectionService().
							findTouPiaoExtensionByuserIdAndminzhuxuanjuId(touPiao);
					if(touPiaoExtension != null) {
						request.setAttribute("touPiaoExtension", touPiaoExtension);
					} else {
						request.setAttribute("touPiaoErr", "没有找到对应信息");
					}
				
				} else {
					request.setAttribute("minzhuxuanjuErr", "没找到对应的民主选举信息");
				}
				return "/WEB-INF/jsp/see_minzhuxuanjuState.jsp";
			} else {
				return "redirect:/jsp/index_villager.jsp";
			}
		} else {
			return "redirect:/jsp/index_villager.jsp";
		}
		
	}
	
	
	
	
	
}
