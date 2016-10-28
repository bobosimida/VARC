/**
 * 
 */
package com.varc.service.user;

import javax.servlet.http.HttpServletRequest;

import com.varc.domain.User;

/**
 * 业务层用户接口
 * @author danchaobing
 *
 */
public interface IUserService {
	/**
	 * 检测用户输入的账号和密码是否合法,
	 * 如果不合法将错误错误信息写入request
	 * @author danchaobing
	 * @param user
	 * @author 
	 * @return 检测合法返回true,检测不合法返回false
	 */
	public boolean verify(User user,HttpServletRequest request);
	/**
	 * 获取用户信息，对帐号和密码的合法性进行检测，合法则获取，不合法则不获取
	 * @author danchaobing
	 * @param user
	 * @return 获取成功返回获取到的user登入扩展对象，获取失败则返回null
	 */
	public User loadUserByAccountAndPwd(User user,HttpServletRequest request);
	
	
}
