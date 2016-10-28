/**
 * 
 */
package com.varc.service.WorkAndMessage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.varc.domain.Email;
import com.varc.domain.extension.EmailExtension;
import com.varc.domain.extension.UserExtension;

/**
 * 工作与信息交流业务层接口
 * @author danchaobing
 *
 */
public interface IWorkAndMessageService {
	/**
	 * 发送邮件
	 * @author danchaobing
	 *
	 */
	public boolean sendEmail(HttpServletRequest request,Email email,String sendAccount,String receiveAccount);
	/**
	 * 获取用户的未读邮件数量
	 * @author danchaobing
	 *
	 */
	public int getEmailNoReadCount(HttpServletRequest request,String account);
	/**
	 * 获取用户未读邮件列表
	 * @author danchaobing
	 *
	 */
	public List<EmailExtension> getEmailNoReadList(String account,String page);
	/**
	 * 获取用户所有邮件列表
	 * @author danchaobing
	 *
	 */
	public List<EmailExtension> getEmailListByGetId(String account,String page);
	/**
	 * 获取用户所有邮件数量
	 * @author danchaobing
	 *
	 */
	public int getAllEmailCountByGetId(String account);
	/**
	 * 获取用户邮件具体内容	
	 * @author danchaobing
	 *
	 */
	public byte[] getEmailContentById(String id);
	/**
	 * 删除一封邮件
	 * @author danchaobing
	 *
	 */
	public boolean  deleteAEmail(String id );
	/**
	 * 通过自己的帐号查找联系人
	 * @author danchaobing
	 *
	 */
	public List<UserExtension> findTongxunluByAccount(String account,String page);
	
	/**
	 * 通过自己的帐号查找所有联系人
	 * @author danchaobing
	 */
	public List<UserExtension> findAllTongxunluByAccount(String account);
	/**
	 * 查找用户通讯录联系人数量
	 * @author danchaobing
	 *
	 */
	public Integer findTongxunluCountBySelfId(String account);
	
	/**
	 * 删除联系人
	 * @author danchaobing
	 *
	 */
	public boolean deleteTongxunlu(String otherId);
	/**
	 * 查找用户，通过帐号
	 * @author danchaobing
	 *
	 */
	public Integer findUserByAccount(String account);
	/**
	 * 添加联系人
	 * @author danchaobing
	 *
	 */
	public boolean addTongxunlu(String selfAccount,String otherId,HttpServletRequest request);
}
