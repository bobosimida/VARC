/**
 * 
 */
package com.varc.mapper;

import java.util.List;

import com.varc.domain.CunWeiHui;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.User;
import com.varc.domain.VillageInfo;
import com.varc.domain.extension.AccountToVillageInfo;
import com.varc.domain.extension.CunWeiHuiExtension;
import com.varc.domain.extension.Page;
import com.varc.domain.extension.UserExtension;

/**
 * @author danchaobing
 * 
 */
public interface BasicInfoMapper {
	/**
	 * 1 修改用户密码
	 * 
	 * @author danchaobing
	 * @param user
	 * @return
	 */

	public boolean updateUserPwd(User user);

	/**
	 * 2 更新个人信息
	 * 
	 * @author huajiangbo
	 * @param user
	 * @return
	 */
	public boolean updateUserInfo(User user);

	/**
	 * 3.更新村庄信息， 根据农民账户查找该农民村庄信息（把农民账号信息和跟新的村庄信息封装成一个扩展对象）
	 * 
	 * @author huajiangbo
	 * @param accountToVillageInfo
	 * @return
	 */
	public boolean updateVillageInfoByAccount(
			AccountToVillageInfo accountToVillageInfo);

	/**
	 * 4.更新用户角色，根据用户账号根据用户角色
	 * 
	 * @author huajiangbo
	 * @param user
	 * @return
	 */
	public boolean updateRollById(User user);

	/**
	 * 6.查看个人信息,通过个人账户
	 * 
	 * @author danchaobing
	 * @param account
	 *            被查看帐号
	 * @return 返回user对象
	 */
	public UserExtension findUserByAccount(String account);

	/**
	 * 7.用户账号查看用户所在村庄的基本信息
	 * 
	 * @author huajiangbo
	 * @param account
	 * @return
	 */
	public VillageInfo findVillageInfoByUserAccount(String account);

	/**
	 * 8.增加用户
	 * 
	 * @author danchaobing
	 * @param user
	 *            更新字段包括（account,pwd,rollId,villageinfoId）
	 * @return 添加成功返回true ，添加失败返回false
	 */

	public boolean addUser(User user);

	/**
	 * 9.删除用户
	 * 
	 * @author danchaobing
	 * @param account
	 *            被删除帐号
	 * @return 删除成功返回true ，删除失败返回false
	 */
	public boolean deleteUserByAccount(String account);

	/**
	 * 10. 通过id查找用户信息
	 * 
	 * @author huajiangbo
	 * @param id
	 * @return
	 */
	public User findUserByUserId(int id);

	/**
	 * 11. 根据村庄id查询村委会信息，包括村委会对应人员的职位。
	 * 
	 * @author huajiangbo
	 * @param account
	 * @return
	 */
	public List<CunWeiHuiExtension> findCunWeiHuiByVillageinfoId(int id);
	/**
	 * 通过村委会成员id查询该成员在村委会的信息
	 * @author danchaobing
	 *
	 */
	public CunWeiHui findCunWeiHuiMember(int id);
	/**
	 * 通过村id查找村信息
	 * @author danchaobing
	 *
	 */
	public VillageInfo findVillageinfoById(int id);
	
	/**
	 * 通过村委会成员id查出所参加的民主选举信息
	 * @author danchaobing
	 *
	 */
	public MinZhuXuanJu findMinZhuXuanJuByMinZhuXuanJuId(int id);
	/**
	 * 查找所有村庄id
	 * @author danchaobing
	 *
	 */
	public List<Integer> findAllVillageinfoId();
	/**
	 * 通过帐号查找密码
	 * @author danchaobing
	 *
	 */
	public String findPwdByAccount(String account);
	/**
	 * 添加村庄
	 * @author danchaobing
	 *
	 */
	public boolean addVillageInfo(VillageInfo villageInfo);
	/**
	 * 添加村委会成员
	 * @author danchaobing
	 *
	 */
	public boolean addCunWeiHuiMember(CunWeiHui cunWeiHui);
	/**
	 * 通过用户帐号查找用户id
	 * @author danchaobing
	 *
	 */
	public Integer findUserIdByUserAccount(String account);
	/**
	 * 通过村庄id查找村委会届数
	 * @author danchaobing
	 *
	 */
	public Integer findCunWeiHuiJieShuByVillinfoId(Integer id);
	/**
	 * 查看村庄的某职位是否已有人
	 * @author danchaobing
	 *
	 */
	public Integer zhiWeiIsUse(CunWeiHui cunWeiHui);
	/**
	 * 删除村委会表的用户记录
	 * @author danchaobing
	 *
	 */
	public void deleteCunWeiHuiMember1(CunWeiHui cunWeiHui);
	public void deleteCunWeiHuiMember2(CunWeiHui cunWeiHui);
	
	/**
	 * 更新用户角色权限
	 * @author danchaobing
	 *
	 */
	public boolean updateUserRollByAccount(User user);
	/**
	 * 查找用户角色
	 * @author danchaobing
	 *
	 */
	public Integer findRollById(int id);
	/**
	 * 查找出当前村子最大的id
	 * @author danchaobing
	 *
	 */
	public Integer findVillageMaxId();
	/**
	 * 分页查询所有村庄信息
	 * @author danchaobing
	 *
	 */
	public List<VillageInfo> findAllVillageinfo(int page);
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
	public List<UserExtension> findAllUserByVillageInfoId(Page page);
	/**
	 * 查询某村的所有用户数量
	 * @author danchaobing
	 *
	 */
	public Integer findUserCountByVillageInfoId(int id);
	/**
	 * 管理员重置密码  	
	 * @author danchaobing
	 *
	 */
	public boolean resetPwd(User user);
}
