package com.varc.mapper;
import java.util.List;

import com.varc.domain.MinZhuJianDu;
import com.varc.domain.extension.MinZhuJianDuFindByTimeAndId;
import com.varc.domain.extension.MinZhuJianDuExtension;

public interface SuperviseMapper{
	/**
	 * 创建民主监督
	 * @author panzhonglin
	 *
	 */
	public void addMinZhuJianDu(MinZhuJianDu minzhujiandu);
	/**
	 * 查找村庄id是否存在
	 * @author panzhonglin
	 *
	 */
	public Integer findVillageIdByVillageId(int id);
	 /**
	 * 通过村庄id和填报年度查找监督信息
	 * @author panzhonglin
	 *
	 */
	public MinZhuJianDu findMinZhuJianDuByVillageinfoIdAndTianBaoNianDu(MinZhuJianDu minzhujiandu);
	/**
	 * 更新监督信息
	 * @author sunrong
	 */
	public  void updateMinZhuJianDu(MinZhuJianDu minzhujiandu);
	/**
	 * 一共有多少行数据
	 * @author sunrong
	 * @param MinZhuJianDuExtension
	 * @return
	 *//*
		public Long getCountrow(MinZhuJianDuExtension minzhujianduExtension);*/

	/**
	 * 获取的帐号
	 * @author sunrong
	 * @param account
	 * @return
	 */
		public String findAccount(String account);
		
		
		
		
		
		
		
		/**
		 * 乡镇府无条件分页查询民主监督
		 * @author danchaobing
		 *
		 */
		public List<MinZhuJianDuExtension> findMinZhuJianDu(int page);
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
		public List<MinZhuJianDuExtension> findMinZhuJianDuByTimeAndId(MinZhuJianDuFindByTimeAndId minZhuJianDouFindByTimeAndId);
		/**
		 *  通过村庄id和填报年度查找符合条件的监督信息数量
		 * @author danchaobing
		 *
		 */
		public Integer findMinZhuJianDuCountByTimeAndId(MinZhuJianDuFindByTimeAndId minZhuJianDouFindByTimeAndId);
		/**
		 * 用过村庄帐号查村庄id
		 * @author danchaobing
		 *
		 */
		public Integer findVillageInfoIdByAccount(String account);
		/**
		 * 根据民主监督id查找民主监督信息
		 * @author danchaobing
		 *
		 */
		public MinZhuJianDu findMinZhuJianDuById(int id);
}
