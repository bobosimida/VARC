/**
 * 
 */
package com.varc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**申请民主选举时用到，检测输入id是否数字，检测是否输入选举日期
 * @author danchaobing
 *
 */
public class CreatMinZhuXuanJuInterceptor implements HandlerInterceptor{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		boolean flag=true;
		try{
			String id=arg0.getParameter("villageinfoId");
			if(id==null ){
				
			}else{
				Integer.parseInt(id.trim());
			}
		}catch (NumberFormatException e) {
			arg0.setAttribute("villageinfoIdError", "请输入数字");
			flag = false;
		}
		if(arg0.getParameter("xuanjuri")==null){
			arg0.setAttribute("xuanjuri", "请输入日期");
			flag=false;
		}
		if(!flag){
			arg0.getRequestDispatcher("/WEB-INF/jsp/add_minzhuxuanju.jsp").forward(arg0, arg1);
		}
		return flag;
	}

}
