package com.varc.service.DemocraticElection.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.varc.domain.HouXuanRen;
import com.varc.mapper.ElectionMapper;

/**
 * <p>Title: VoteEnter</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author   HuaJiangbo
 * @Date: 2016年9月9日
 */
@Component
@Scope("singleton")
public class VoteEnter {
	@Resource
	private ElectionMapper electionMapper;
	public synchronized boolean VoteAndUpdatepiaoshu(HouXuanRen houXuanRen) {
		boolean updated = false;
		//拿到当前候选人信息
		HouXuanRen houXuanRen2 = electionMapper.findHouXuanRenByuserIdAndminzhuxuanjuId(houXuanRen);
		//候选人表被投候选人票数加一
		houXuanRen2.setPiaoshu(houXuanRen2.getPiaoshu() + 1);
		updated = electionMapper.updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId(houXuanRen2);
		return updated;
	}
	
}
