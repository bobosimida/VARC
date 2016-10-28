/**
 * 
 */
package com.varc.service.BasicInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.varc.domain.CunWeiHui;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.User;
import com.varc.domain.VillageInfo;
import com.varc.domain.extension.CunWeiHuiExtension;
import com.varc.domain.extension.UserExtension;

/**
 * 农村基本信息管理业务层接口
 * 
 * @author danchaobing
 *
 */
public interface IBasicInfoService {
	/**
	 * 修改登入用户的密码
	 * @author danchaobing
	 * @param account 被修改帐号 该参数无需合法性检测
	 * @param oldPwd 用户的旧密码  该参数需要合法性检测,合法可以提交，不合法不可以提交
	 * @param newPwd 用户的新密码  该参数需要合法性检测,合法可以提交，不合法不可以提交
	 * @param newPwdA 用户的新密码确认  该参数需要合法性检测,合法可以提交，不合法不可以提交
	 * @return	修改成功返回true， 修改失败返回false
	 */
	public boolean updateUserPwd(HttpServletRequest request,String account,String oldPwd,String newPwd,String newPwdA);
	/**
	 * 更新个人信息
	 * 更新之前对用户信息做信息的合法检测
	 * @author danchaobing
	 * @param user 被更新对象   对提交的信息进行合法性检测,合法可以提交，不合法不可以提交,检测字段包括（name,unit,tel,email,sex,age）
	 * @return	更新成功返回true，更新失败返回false
	 */
	public boolean updateUserInfo(HttpServletRequest request,User user);
	

	/**
	 * 更新村子基本信息
	 * @author danchaobing
	 * @param account 执行更新人帐号 通过此帐号找到该人所属村子的id	该参数无需合法性检测
	 * @param villageInfo 村子新信息 该参数需要合法性检测,检测字段不包括（id,sheng,shi,qu,xiang）
	 * @return 更新成功返回true ，更新失败返回false
	 */
	public boolean updateVillageInfoByUserAccount(String account,VillageInfo villageInfo,HttpServletRequest request);
	
	/**
	 * 查看个人信息
	 * @author danchaobing
	 * @param account 被查看帐号  该参数无需合法性验证
	 * @return 返回user对象
	 */
	public UserExtension findUserByAccount(String account);
	/**
	 * 通过id查找用户
	 * @author danchaobing
	 *
	 */
	public User findUserById(int id);
	/**
	 * 查看用户所在村庄的基本信息
	 * @author danchaobing
	 * @param account 被查看帐号 参数无需合法性检测
	 * @return	返回VillageInfo对象
	 */
	public VillageInfo findVillageInfoByUserAccount(String account);
	/**
	 * 根据村庄id查找村庄信息
	 * @author danchaobing
	 * @param account 被查看帐号 参数无需合法性检测
	 * @return	返回VillageInfo对象,如果查找失败则返回null
	 */
	public VillageInfo findVillageInfoById(String id,HttpServletRequest request);
	
	/**
	 * 通过村id查找村委会所有成员信息
	 * @author danchaobing
	 *
	 */
	public List<CunWeiHuiExtension> findCunWeiHuiByVillageinfoId(int id);
	/**
	 * 增加账号
	 * @author danchaobing
	 * @param user 	检测参数的合法性,合法可以提交，不合法不可以提交，检测字段包括（account,pwd,rollId,villageinfoId）
	 * @return 添加成功返回true ，添加失败返回false
	 */		
	public boolean addUser(User user,HttpServletRequest request,String zhiwei,String oldUserId);
	/**
	 * 删除账号
	 * @author danchaobing
	 * @param account 被删除帐号 该参数无需合法性检测
	 * @return  删除成功返回true ，删除失败返回false
	 */
	public boolean deleteUserByAccount(String account);
	/**
	 * 通过村委会成员在村委会的选举id查出所参加的民主选举信息
	 * @author danchaobing
	 *
	 */
	public MinZhuXuanJu findMinZhuXuanJuByMinZhuXuanJuId(int id);
	/**
	 * 通过村委会成员id查出在村委会信息
	 * @author danchaobing
	 *
	 */
	public CunWeiHui findCunWeiHuiMember(int userId);
	/**
	 * 查询村委会某职位是否被占用
	 * @author danchaobing
	 *@return 返回占用职位userId
	 */
	public Integer zhiWeiIsUse(CunWeiHui cunWeiHui);
	/**
	 * 修改权限
	 * @author danchaobing
	 *
	 */
	public boolean updateRoll(HttpServletRequest request,User user,String oldRollId,String zhiweiId,String oldUserId);
	/**
	 * 分页查询所有村庄信息
	 * @author danchaobing
	 *
	 */
	public List<VillageInfo> findAllVillageinfo(String page);
	
	/**
	 * 查询村庄总数量
	 * @author danchaobing
	 *
	 */
	public Integer findNumberOfVillageInfo();
	/**
	 * 查询某村的所有用户
	 * @author danchaobing
	 *
	 */
	public List<UserExtension> findAllUserByVillageInfoId(String id , String page);
	/**
	 * 查询某村的所有用户数量
	 * @author danchaobing
	 *
	 */
	public Integer findCountUserByVillageInfoId(String id);
	/**
	 * 重置密码
	 * @author danchaobing
	 *
	 */
	public boolean resetPwd(String account);
	
}
