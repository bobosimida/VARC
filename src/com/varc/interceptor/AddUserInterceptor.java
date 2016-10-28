/**
 * 
 */
package com.varc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author danchaobing
 *
 */
public class AddUserInterceptor implements HandlerInterceptor{

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
				int i=Integer.parseInt(id.trim());
				if(i<1){
					arg0.setAttribute("villageinfoIdError", "请输入正确的数字");
					flag=false;
				}
			}
		}catch (NumberFormatException e) {
			String rollId=arg0.getParameter("rollId");
			if(rollId.equals("3") || rollId.equals("4")){
				
			}else{
				arg0.setAttribute("villageinfoIdError", "请输入数字");
				flag=false;
			}
		}
		if(!flag){
			arg0.getRequestDispatcher("/WEB-INF/jsp/add_user.jsp").forward(arg0, arg1);
		}
		return flag;
	}
	
}
