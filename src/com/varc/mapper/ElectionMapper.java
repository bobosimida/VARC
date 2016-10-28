/**
 * 
 */
package com.varc.mapper;

import java.util.List;

import com.varc.domain.CunWeiHui;
import com.varc.domain.HouXuanRen;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.TouPiao;
import com.varc.domain.User;
import com.varc.domain.extension.HouXuanRenExtension;
import com.varc.domain.extension.MinZhuXuanJuExtension;
import com.varc.domain.extension.TouPiaoExtension;

/**
 * @author danchaobing
 *
 */
public interface ElectionMapper {
	/**
	 * 创建民主选举
	 * @author danchaobing
	 *
	 */
	public boolean insertMinZhuXuanJu(MinZhuXuanJu minZhuXuanJu);
	 
	/**
	 * 查找用户是否存在
	 * @author danchaobing
	 *
	 */
	public Integer findVillageinfoIdByUserAccount(String acount);
	/**
	 * 通过用户账号查找用户
	 * @author huajiangbo
	 *
	 */
	public User findUserByUserAccount(String acount);
	/**
	 * 添加候选人信息
	 * @author huajiangbo
	 *
	 */
	public boolean addHouXuanRenByUserId(HouXuanRen houXuanRen);
	/**
	 * 找到该村最近一次民主选举
	 * @author huajiangbo
	 *
	 */
	public int findMaxMinZhuXuanJuId();
	/**
	 * 查找村庄id是否存在
	 * @author danchaobing
	 *
	 */
	public Integer findVillageIdByVillageId(int id);
	/**
	 * 
	 * <p>Title: findMinZhuXuanJu</p>
	 * <p>Description: 找出每个村最近一次的民主选举信息</p>
	 * @author   HuaJiangbo
	 * @return List<MinZhuXuanJuExtension>
	 */
	public List<MinZhuXuanJuExtension> findMinZhuXuanJu();
	/**
	 * <p>Title: updateMinZhuXuanJuState</p>
	 * <p>Description:更新民主选举状态 </p>
	 * @author   HuaJiangbo
	 * @param minZhuXuanJu
	 * @return boolean
	 */
	public boolean updateMinZhuXuanJuState(MinZhuXuanJu minZhuXuanJu);
	/**
	 * 
	 * <p>Title: finNotFinishApply</p>
	 * <p>Description:查找是否有未完成申请 </p>
	 * @author   HuaJiangbo
	 * @param villageinfoId
	 * @return MinZhuXuanJu
	 */
	public MinZhuXuanJu finNotFinishApply(int villageinfoId);
	/**
	 * <p>Title: findLastMinZhuXuanJuByVillageinfoId</p>
	 * <p>Description:10.找到该村庄最近一次民主选举 </p>
	 * @author   HuaJiangbo
	 * @return MinZhuXuanJu
	 */
	public MinZhuXuanJu findLastMinZhuXuanJuByVillageinfoId(int villageinfoId);
	/**
	 * <p>Title: findVillageNameById</p>
	 * <p>Description:11.通过ID找出村庄名称 </p>
	 * @author   HuaJiangbo
	 * @param villageinfoId
	 * @return String
	 */
	public String findVillageNameById(int villageinfoId);
	/**
	 * <p>Title: findMaxJieShuFromCunWeiHuiByVillageinfoId</p>
	 * <p>Description:12.找到某村的村委会最大届数 </p>
	 * @author   HuaJiangbo
	 * @param villageinfoId
	 * @return CunWeiHui
	 */
	public int findMaxJieShuFromCunWeiHuiByVillageinfoId(int villageinfoId);
	/**
	 * <p>Title: findMinZhuXuanJuById</p>
	 * <p>Description:13.通过民主选举表ID找到对应的民主选举记录 </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @return MinZhuXuanJu
	 */
	public MinZhuXuanJu findMinZhuXuanJuById(int id);
	/**
	 * <p>Title: deleteHouXuanRenByMinZhuXuanJuId</p>
	 * <p>Description:14.通过民主选举表ID删除候选人 </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @return boolean
	 */
	public boolean deleteHouXuanRenByMinZhuXuanJuId(int id);
	/**
	 * <p>Title: findHouXuanRensByMinZhuXuanJuId</p>
	 * <p>Description:15.根据民主选举ID找出候选人</p>
	 * @author   HuaJiangbo
	 * @param id
	 * @return List<HouXuanRen>
	 */
	public List<HouXuanRen> findHouXuanRensByMinZhuXuanJuId(int id);
	/**
	 * <p>Title: findHouXuanRenExtensionsByMinZhuXuanJuId</p>
	 * <p>Description:16.根据民主选举ID找出符合条件的候选人 </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @return List<HouXuanRenExtension>
	 */
	public List<HouXuanRenExtension> findHouXuanRenExtensionsByMinZhuXuanJuId(int id);
	/**
	 * <p>Title: findHouXuanRenByuserIdAndminzhuxuanjuId</p>
	 * <p>Description: 17.通过候选人ID和民主选举ID找出对应的候选人</p>
	 * @author   HuaJiangbo
	 * @param houXuanRen
	 * @return HouXuanRen
	 */
	public HouXuanRen findHouXuanRenByuserIdAndminzhuxuanjuId(HouXuanRen houXuanRen);
	/**
	 * <p>Title: updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId</p>
	 * <p>Description: 18.更新候选人票数通过候选人ID和民主选举ID</p>
	 * @author   HuaJiangbo
	 * @param houXuanRen
	 * @return boolean
	 */
	public boolean updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId(HouXuanRen houXuanRen);
	/**
	 * <p>Title: insertTouPiao</p>
	 * <p>Description:19、插入投票者和被投票者信息 </p>
	 * @author   HuaJiangbo
	 * @param touPiao
	 * @return boolean
	 */
	public boolean insertTouPiao(TouPiao touPiao);
	/**
	 * <p>Title: findTouPiaoByuserIdAndminzhuxuanjuId</p>
	 * <p>Description:20.用投票者ID和民主选举的ID找到是否该投票者已经投票 </p>
	 * @author   HuaJiangbo
	 * @param touPiao
	 * @return TouPiao
	 */
	public TouPiaoExtension findTouPiaoExtensionByuserIdAndminzhuxuanjuId(TouPiao touPiao);
	/**
	 * <p>Title: findMaxJieShuFromCunWeiHui</p>
	 * <p>Description: 21.通过villageinfoId找到该村村委会最大届数</p>
	 * @author   HuaJiangbo
	 * @param villageinfoId
	 * @return int
	 */
//	public int findMaxJieShuFromCunWeiHui(int villageinfoId);
	/**
	 * <p>Title: findHouXuanRenByMinZhuXuanJuIdDESCPiaoShu</p>
	 * <p>Description:21.通过民主选举ID找到对应候选人，用票数的降序排列</p>
	 * @author   HuaJiangbo
	 * @param minzhuxuanjuId
	 * @return HouXuanRen
	 */
	public List<HouXuanRen> findHouXuanRenByMinZhuXuanJuIdDESCPiaoShu(int minzhuxuanjuId);
	/**
	 * <p>Title: deleteTouPiaoBiaoByMinZhuXuanJuId</p>
	 * <p>Description:22.根据民主选举ID删除投票表 </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @return boolean
	 */
	public boolean deleteTouPiaoBiaoByMinZhuXuanJuId(int minzhuxuanjuId);
	/**
	 * <p>Title: findUserByAccount</p>
	 * <p>Description:23.通过账号查找用户 </p>
	 * @author   HuaJiangbo
	 * @param account
	 * @return User
	 */
	public User findUserByAccount(String account);
	/**
	 * <p>Title: insertCunWeiHui</p>
	 * <p>Description: 24.往村委会表插入村委会成员</p>
	 * @author   HuaJiangbo
	 * @param cunWeiHui
	 * @return boolean
	 */
	public boolean insertCunWeiHui(CunWeiHui cunWeiHui);
	/**
	 * <p>Title: findLastCunWeiHuiMembersByjieshuAndvillageinfoId</p>
	 * <p>Description: 25.通过届数和村庄ID找到上一届村委会成员</p>
	 * @author   HuaJiangbo
	 * @param cunWeiHui
	 * @return List<User>
	 */
	public List<Integer> findLastCunWeiHuiMembersByjieshuAndvillageinfoId(CunWeiHui cunWeiHui);
	/**
	 * <p>Title: findMaxzhiweiIdfromCunWeiHuiByVillageinfoId</p>
	 * <p>Description:26. 通过村庄ID找到对应该村最大的职位ID</p>
	 * @author   HuaJiangbo
	 * @param villageinfoId
	 * @return int
	 */
	public int findMaxzhiweiIdfromCunWeiHuiByVillageinfoId(int villageinfoId);
	/**
	 * <p>Title: updateUserRollByUserId</p>
	 * <p>Description:27.根据userId更新userRollId </p>
	 * @author   HuaJiangbo
	 * @param user
	 * @return boolean
	 */
	public boolean updateUserRollByUserId(User user);
	
	public boolean updateMinZhuXunaJuZhuangTaiToOneById(int id);
	
}
