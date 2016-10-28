/**
 * 
 */
package com.varc.mapper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.varc.domain.User;

/**
 * 用户表操作接口，对用户进行增删改查
 * 
 * @author danchaobing
 * 
 */
@Component
@Scope("prototype")
public interface UserMapper {
	/**
	 * 查找用户信息，根据帐号和密码
	 * 
	 * @author danchaobing
	 * @param user
	 * @return 如果查找到用户则返回user登入扩展对象，否则返回null
	 */
	public User findUserByAccountAndPwd(User user);

	
}
