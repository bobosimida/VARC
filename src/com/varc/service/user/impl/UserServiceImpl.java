package com.varc.service.user.impl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.varc.domain.User;
import com.varc.mapper.UserMapper;
import com.varc.service.user.IUserService;

/**
 * 业务层用户接口实现类
 * @author sunrong
 *
 */
@Component
@Scope("prototype")
public class UserServiceImpl implements IUserService{
	private UserMapper userMapper;
	/**
	 * @author sunrong
	 * */
	@Override
	public boolean verify(User user, HttpServletRequest request) {
		boolean flag=true;
		if(user.getAccount().equals("")){
			request.setAttribute("accountError", "账号不能为空");
			flag= false;
		}
		if(user.getAccount().length()<9 || user.getAccount().length()>20)
		{
			request.setAttribute("accountError", "帐号长度为9-20");
			flag= false;
		}
		if(user.getPwd().equals(""))
		{
			request.setAttribute("passwordError","密码不能为空");
			flag= false;
		}
		if(user.getPwd().length()<9 || user.getPwd().length()>20)
		{
			request.setAttribute("passwordError","密码长度为9-20");
			flag= false;
		}
		return flag;
	
	}
	/**
	 * @author sunrong
	 * */
	@Override
	public User loadUserByAccountAndPwd(User user,HttpServletRequest request) {
		if(this.verify(user, request)){
			user=userMapper.findUserByAccountAndPwd(user);
			if(user==null){
				request.setAttribute("accountError", "帐号或密码错误");
				return null;
			}
			return user;
		}else{
			return null;
		}
	}
	/**
	 * @return the userMapper
	 */
	public UserMapper getUserMapper() {
		return userMapper;
	}
	/**
	 * @param userMapper the userMapper to set
	 */
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	
	
}
