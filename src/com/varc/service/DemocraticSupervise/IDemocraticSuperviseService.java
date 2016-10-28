package com.varc.service.DemocraticSupervise;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.varc.domain.MinZhuJianDu;
import com.varc.domain.extension.MinZhuJianDuExtension;



/**
 * 民主监督业务层接口
 * @author sunrong
 *
 */

public interface IDemocraticSuperviseService {
	/**
	 * 填报民主监督
	 * @author sunrong
	 */
	public void addMinZhuJianDu(HttpServletRequest request,HttpSession session, MinZhuJianDu minzhujiandu);

    /**	 	
     * 通过所属村庄ID和填报年度找到该村民主监督信息
	 * @author   sunrong
	 */
    public List<MinZhuJianDuExtension> findMinZhuJianDuByVillageIdAndTianBaoNianDu(HttpServletRequest request, HttpSession session,Integer id,java.util.Date date);
	/**
	 * 通过民主监督表的ID更新对应的民主监督信息
	 * @author   sunrong
	 */
	public boolean updateMinZhuJianDu(HttpServletRequest request,
			MinZhuJianDu minzhujiandu);
	/**
	 *更新信息和查询时验证
	 *@author   sunrong
	 */
	public boolean info_Verification(HttpServletRequest request,MinZhuJianDu minzhujiandu);
	
	
	
	
	/**
	 * 乡镇府无条件分页查询民主监督
	 * @author danchaobing
	 *
	 */
	public List<MinZhuJianDuExtension> findMinZhuJianDu(String page);
	/**
	 * 查找民主监督的信息总数
	 * @author danchaobing
	 *
	 */
	public Integer findMinZhuJianDuCount();
	/**
	 * 通过村庄id和填报年度查找监督信息
	 * @author danchaobing
	 *
	 */
	public List<MinZhuJianDuExtension> findMinZhuJianDuByTimeAndId(String id,String date,HttpServletRequest request,String page);
	/**
	 *  通过村庄id和填报年度查找符合条件的监督信息数量
	 * @author danchaobing
	 *
	 */
	public Integer findMinZhuJianDuCountByTimeAndId(String id ,String date,HttpServletRequest request,String page);

	/**
	 * 通过村庄帐号查村庄id
	 * @author danchaobing
	 *
	 */
	public Integer findVillageInfoIdByAccount(String account);

}