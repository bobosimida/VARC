package com.varc.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varc.domain.Email;
import com.varc.domain.extension.EmailExtension;
import com.varc.domain.extension.UserExtension;
import com.varc.exportexcel.ExcelUtilWithXSSF;
import com.varc.service.WorkAndMessage.IWorkAndMessageService;
import com.varc.service.factory.ServiceFactory;

/**
 * 工作与信息交流控制层
 * @author danchaobing
 *
 */
@Controller
@RequestMapping("/workAndMessageControl")
public class WorkAndMessageControl extends Control{
	/**
	 * 发送邮件
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/send_email")
	public String send_email(HttpServletRequest request,HttpSession session,@ModelAttribute Email  email,String receiveAccount){
		
		String address =this.isNull(request, session, "/WEB-INF/jsp/send_email.jsp");
		if(address.equals("/WEB-INF/jsp/send_email.jsp")){
			
			if(ServiceFactory.getIWorkAndMessageService().sendEmail(request, email, session.getAttribute("account").toString(), receiveAccount)){
				
				return "redirect:/jsp/update_success.html";
			}
		}
		return address;
	}
	/**
	 * 获取未读邮件数量
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/get_email_count")
	public void get_email_count(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		String address = this.isNull(request, session,
				"");
		if (address.equals("")){
			int count=ServiceFactory.getIWorkAndMessageService().getEmailNoReadCount(request, session.getAttribute("account").toString());
			JSONObject json = new JSONObject();
			json.put("count", count);
			PrintWriter out;
			try {
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.print(json.toString());
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 获取所有邮件列表
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/get_readEmail_list")
	public String get_email_list(HttpServletRequest request,HttpSession session,String page,String emailId){
		String address =this.isNull(request, session, "/WEB-INF/jsp/get_readEmail_list.jsp");
		if(address.equals("/WEB-INF/jsp/get_readEmail_list.jsp")){
			IWorkAndMessageService iWorkAndMessageService=ServiceFactory.getIWorkAndMessageService();
			if(page==null){
				page="1";
				request.setAttribute("page", 1);
			}else{
				request.setAttribute("page", Integer.parseInt(page));	
			}
			if(emailId!=null){
				iWorkAndMessageService.deleteAEmail(emailId);	//删除邮件
			}	
			List<EmailExtension> readEmails=iWorkAndMessageService.getEmailListByGetId(session.getAttribute("account").toString(),page);
			request.setAttribute("readEmails", readEmails);
			int emailNum=iWorkAndMessageService.getAllEmailCountByGetId(session.getAttribute("account").toString());
			request.setAttribute("emailNum", emailNum);
			int pageCount= (int)Math.ceil(emailNum/8.0);
			request.setAttribute("pageCount", pageCount);
		}
		return address;
	}
	/**
	 * 获取未读邮件列表
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/get_noReadEmail_list")
	public String get_noReademail_list(HttpServletRequest request,HttpSession session,String page,String emailId){
		String address =this.isNull(request, session, "/WEB-INF/jsp/get_noReadEmail_list.jsp");
		if(address.equals("/WEB-INF/jsp/get_noReadEmail_list.jsp")){
			IWorkAndMessageService iWorkAndMessageService=ServiceFactory.getIWorkAndMessageService();
			if(page==null){
				page="1";
				request.setAttribute("page", 1);
			}else{
				request.setAttribute("page", Integer.parseInt(page));
			}
			if(emailId!=null){
				iWorkAndMessageService.deleteAEmail(emailId);
			}
			List<EmailExtension> readEmails=iWorkAndMessageService.getEmailNoReadList(session.getAttribute("account").toString(), page);
			request.setAttribute("readEmails", readEmails);
			int emailNum=iWorkAndMessageService.getEmailNoReadCount(request, session.getAttribute("account").toString());
			request.setAttribute("emailNum", emailNum);
			int pageCount= (int)Math.ceil(emailNum/8.0);
			request.setAttribute("pageCount", pageCount);
		}
		return address;
	}
	/**
	 * 转发到查看邮件具体内容页面
	 * @author danchaobing
	 * @throws IOException 
	 *
	 */
	@RequestMapping("/look_email")
	public String forward_look_email(HttpServletRequest request,HttpSession session,HttpServletResponse response,String id,String receiveAccount){
		String address =this.isNull(request, session,"/WEB-INF/jsp/look_email.jsp");
		if(address.equals("/WEB-INF/jsp/look_email.jsp")){
			byte[] b=ServiceFactory.getIWorkAndMessageService().getEmailContentById(id);
			response.setCharacterEncoding("utf-8");
			String s=new String(b);
			request.setAttribute("emailContent",s);
			request.setAttribute("receiveAccount", receiveAccount);
		}
		return address;
	}
	/**
	 * 转发到通讯录页面
	 * @author danchaobing
	 *
	 */
	@RequestMapping("/tongxunlu_list")
	public String tongxunlu_list(HttpServletRequest request,HttpSession session,String page,String deleteotherId,String addotherId){
		System.out.println(page + "==========================" + deleteotherId + "++++++++++++++++++=" + addotherId);
		String address =this.isNull(request, session,"/WEB-INF/jsp/tongxunlu_list.jsp");
		if(address.equals("/WEB-INF/jsp/tongxunlu_list.jsp")){
			IWorkAndMessageService iWorkAndMessageService=ServiceFactory.getIWorkAndMessageService();
			if(page==null){
				page="1";
			}
			if(deleteotherId!=null){
				iWorkAndMessageService.deleteTongxunlu(deleteotherId);	//删除联系人
			}
			if(addotherId!= null){
				iWorkAndMessageService.addTongxunlu(session.getAttribute("account").toString(), addotherId,request);
			}
			int tongxunluNum=iWorkAndMessageService.findTongxunluCountBySelfId(session.getAttribute("account").toString());
			request.setAttribute("tongxunluNum", tongxunluNum);
			int pageCount= (int)Math.ceil(tongxunluNum/7.0);
			request.setAttribute("pageCount", pageCount);
			
			request.setAttribute("page", page);
			List<UserExtension> users=iWorkAndMessageService.findTongxunluByAccount(session.getAttribute("account").toString(),page);
			request.setAttribute("users", users);
		}
		return address;
	}
	/**
	 * 导出所有联系人
	 * @author danchaobing
	 * @throws IOException 
	 * @throws ParseException 
	 *
	 */
	@RequestMapping("/tongxunlu_export")
	public void tongxunlu_export(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws IOException, ParseException{
		List<UserExtension> users=ServiceFactory.getIWorkAndMessageService().findAllTongxunluByAccount(session.getAttribute("account").toString());
		InputStream is=new ExcelUtilWithXSSF().CreateExcel(users);
	    response.addHeader("Content-Disposition","attachment;filename=tongxunlu.xls");  
	    ServletOutputStream out = response.getOutputStream();  
	    byte[] buffer=new byte[1024];  
	    int count;  
	    while((count=is.read(buffer))!=-1){  
	        out.write(buffer, 0, count);  
	    }  
	}
	/**
	 * 查找用户
	 * @author danchaobings
	 *
	 */
	@RequestMapping("/find_user")
	public String find_user(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			String otherAccount){
		String address =this.isNull(request, session,"");
		if (address.equals("")) {
			Integer id = ServiceFactory.getIWorkAndMessageService()
					.findUserByAccount(otherAccount);
			JSONObject json = new JSONObject();
			json.put("id", id);
			PrintWriter pw = null;
			System.out.println("id==================" + id);
			System.out.println(address);
			try {
				pw = response.getWriter();
				pw.write(json.toString());
				System.out.println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) { 
					pw.close();
				}	
			}
		}
		return address;
	}
}
