/**
 * 
 */
package com.varc.mapper;

import java.util.List;

import com.varc.domain.Email;
import com.varc.domain.TongXunLu;
import com.varc.domain.extension.EmailExtension;
import com.varc.domain.extension.EmailPage;
import com.varc.domain.extension.TongxunluPage;
import com.varc.domain.extension.UserExtension;

/**
 * 工作与信息交流业务层接口
 * @author danchaobing
 *
 */
public interface WorkAndMessageMapper {
	/**
	 * 添加邮件
	 * @author danchaobing
	 *
	 */
	public boolean addEmail(Email email);
	
	/**
	 * 获取用户的未读邮件
	 * @author danchaobing
	 *
	 */
	public int getEmailNoreadCountByUserId(int id);
	/**
	 * 获取用户的未读邮件
	 * @author danchaobing
	 *
	 */
	public List<EmailExtension> getEmailNoReadListByGetId(EmailPage emailPage);
	/**
	 * 获取用户的已读邮件
	 * @author danchaobing
	 *
	 */
	public List<EmailExtension> getEmailListByGetId(EmailPage emailPage);
	
	/**
	 * 获取用户所有的邮件数量
	 * @author danchaobing
	 *
	 */
	public Integer getAllEmailCount(int id);
	/**
	 * 读取邮件具体内容
	 * @author danchaobing
	 *
	 */
	public Email findEmailContentById(int id);
	/**
	 *  删除一封邮件
	 * @author danchaobing
	 *
	 */
	public boolean deleteAEmail(int id);
	/**
	 * 将未读邮件改为已读邮件
	 * @author danchaobing
	 *
	 */
	public boolean updateIsRead(int id);
	/**
	 * 通过自己的id查找联系人，分页查询
	 * @author danchaobing
	 *
	 */
	public List<Integer> findTongxunluBySelfId(TongxunluPage tongxunluPage);
	
	/**
	 * 通过自己的id查找所有联系人
	 * @author danchaobing
	 */
	public List<Integer> findAllTongxunluBySelfId(int id);
	/**
	 * 查找用户信息
	 * @author danchaobing
	 *
	 */
	public UserExtension findUserEntensionById(int id);
	/**
	 * 查找用户通讯录联系人数量
	 * @author danchaobing
	 *
	 */
	public Integer findTongxunluCountBySelfId(int id);
	/**
	 * 删除联系人
	 * @author danchaobing
	 *
	 */
	public boolean deleteTongxunlu(int id);
	/**
	 * 添加联系人
	 * @author danchaobing
	 *
	 */
	public boolean addTongxunlu(TongXunLu tongXunLu);
}
