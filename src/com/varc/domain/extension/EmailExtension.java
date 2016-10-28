/**
 * 
 */
package com.varc.domain.extension;

import com.varc.domain.Email;
import com.varc.domain.User;

/**
 * 获取邮件时用到的扩展类
 * @author danchaobing
 *
 */
public class EmailExtension {
	private Email email;
	private User user;
	
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "EmailExtension [email=" + email + ", user=" + user + "]";
	}
	
}
