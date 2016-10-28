/**
 * 
 */
package com.varc.service.DemocraticElection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.varc.domain.HouXuanRen;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.TouPiao;
import com.varc.domain.User;
import com.varc.domain.extension.HouXuanRenExtension;
import com.varc.domain.extension.MinZhuXuanJuExtension;
import com.varc.domain.extension.TouPiaoExtension;

/**
 * 民主选举管理业务层接口
 * @author danchaobing
 *
 */
public interface IDemocraticElectionService {
	/**
	 * 创建民主选举
	 * @author danchaobing
	 *
	 */
	public boolean createMinZhuXuanJU(MinZhuXuanJu minZhuXuanJu,HttpServletRequest request,String accounts,MultipartFile file,HttpSession ession);
	/**
	 * 
	 * <p>Title: findMinZhuXuanJuExtension</p>
	 * <p>Description: 找到每个村对应的最新一次民主选举信息</p>
	 * @author   HuaJiangbo
	 * @return List<MinZhuXuanJuExtension>
	 */
	public List<MinZhuXuanJuExtension> findMinZhuXuanJuExtension();
	/**
	 * <p>Title: updateMinZhuXuanJuState</p>
	 * <p>Description: 更新民主选举状态</p>
	 * @author   HuaJiangbo
	 * @param minZhuXuanJu
	 * @return boolean
	 */
	public boolean updateMinZhuXuanJuState(MinZhuXuanJu minZhuXuanJu);
	/**
	 * 重载的方法
	 * <p>Title: updateMinZhuXuanJuState</p>
	 * <p>Description: </p>
	 * @author   HuaJiangbo
	 * @param minZhuXuanJu
	 * @param user
	 * @return boolean
	 */
//	public boolean updateMinZhuXuanJuState(MinZhuXuanJu minZhuXuanJu,User user);
	/**
	 * <p>Description: </p>
	 * @author   HuaJiangbo
	 * @param request
	 * @param minZhuXuanJu
	 * @return boolean
	 */
	public boolean findNotFinishApply(HttpServletRequest request,
			MinZhuXuanJu minZhuXuanJu);
	/**
	 * <p>Title: findVillageNameById</p>
	 * <p>Description: 通过村庄ID找村庄名</p>
	 * @author   HuaJiangbo
	 * @param id
	 * @param request
	 * @return String
	 */
	public String findVillageNameById(int id, HttpServletRequest request);
	/**
	 * <p>Title: findLastMinZhuXuanJuByVillageinfoId</p>
	 * <p>Description: 通过村庄ID找到该村最近一次民主选举信息</p>
	 * @author   HuaJiangbo
	 * @param id
	 * @param request
	 * @return MinZhuXuanJu
	 */
	public MinZhuXuanJu findLastMinZhuXuanJuByVillageinfoId(int id, HttpServletRequest request);
	/**
	 * <p>Title: findMaxJieShuFromCunWeiHuiByVillageinfoId</p>
	 * <p>Description: 通过村庄ID找到该村最大村委会届数</p>
	 * @author   HuaJiangbo
	 * @param villageinfoId
	 * @param request
	 * @return int
	 */
	public int findMaxJieShuFromCunWeiHuiByVillageinfoId(int villageinfoId);
	/**
	 * <p>Title: findMinZhuXuanJuById</p>
	 * <p>Description: 通过民主选举表的ID找到对应的民主选举信息</p>
	 * @author   HuaJiangbo
	 * @param id
	 * @param request
	 * @return MinZhuXuanJu
	 */
	public MinZhuXuanJu findMinZhuXuanJuById(int id, HttpServletRequest request);
	/**
	 * <p>Title: deleteHouXuanRenByMinZhuXuanJuId</p>
	 * <p>Description:如果民主选举不通过，删除其对应得候选人 </p>
	 * @author   HuaJiangbo
	 * @return boolean
	 */
	public boolean deleteHouXuanRenByMinZhuXuanJuId(int id);
	/**
	 * <p>Title: findHouXuanRensByMinZhuXuanJuId</p>
	 * <p>Description:通过民主选举的ID找到候选人的信息 </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @param request
	 * @return List<HouXuanRen>
	 */
	public List<HouXuanRen> findHouXuanRensByMinZhuXuanJuId(int id, HttpServletRequest request);
	/**
	 * <p>Title: findHouXuanRenExtensionsByMinZhuXuanJuId</p>
	 * <p>Description:通过民主选举的ID找到候选人的信息 包括姓名 </p>
	 * @author   HuaJiangbo
	 * @param id
	 * @param request
	 * @return List<HouXuanRenExtension>
	 */
	public List<HouXuanRenExtension> findHouXuanRenExtensionsByMinZhuXuanJuId(int id, HttpServletRequest request);
	/**
	 * <p>Title: findTouPiaoByuserIdAndminzhuxuanjuId</p>
	 * <p>Description: 通过用户ID和民主选举表ID找到对应的投票记录</p>
	 * @author   HuaJiangbo
	 * @param touPiao
	 * @return TouPiao
	 */
	public TouPiaoExtension findTouPiaoExtensionByuserIdAndminzhuxuanjuId(TouPiao touPiao);
	/**
	 * <p>Title: findHouXuanRenByuserIdAndminzhuxuanjuId</p>
	 * <p>Description: 通过候选人ID和民主选举ID找到对应的候选人</p>
	 * @author   HuaJiangbo
	 * @param houXuanRen
	 * @return HouXuanRen
	 */
	public HouXuanRen findHouXuanRenByuserIdAndminzhuxuanjuId(HouXuanRen houXuanRen);
	/**
	 * <p>Title: updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId</p>
	 * <p>Description: 更新候选人票数</p>
	 * @author   HuaJiangbo
	 * @param houXuanRen
	 * @return boolean
	 */
	public boolean updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId(HouXuanRen houXuanRen);
	/**
	 * <p>Title: insertTouPiao</p>
	 * <p>Description:插入投票信息 </p>
	 * @author   HuaJiangbo
	 * @param touPiao
	 * @return boolean
	 */
	public boolean insertTouPiao(TouPiao touPiao);
	/**
	 * <p>Title: vote</p>
	 * <p>Description:进行投票操作，投票表加入投票者信息，候选人表被投候选人票数加一 </p>
	 * @author   HuaJiangbo
	 * @param houXuanRen2
	 * @param user
	 * @return boolean
	 */
	public boolean vote(HouXuanRen houXuanRen, User user);
	/**
	 * <p>Title: findUserByAccount</p>
	 * <p>Description:通过account找到user </p>
	 * @author   HuaJiangbo
	 * @param account
	 * @return User
	 */
	public User findUserByAccount(String account);
	/**
	 * 
	 * <p>Title: updateCunWeiHUi</p>
	 * <p>Description: </p>
	 * @author   HuaJiangbo
	 * @param houXuanRens
	 * @param user
	 * @return boolean
	 */
	/*public boolean updateCunWeiHUi(List<HouXuanRen> houXuanRens,MinZhuXuanJu minZhuXuanJu);*/
}
