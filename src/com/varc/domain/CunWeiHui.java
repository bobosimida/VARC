package com.varc.domain;

import java.util.Date;

/**
 * 村委会成员信息表
 * 
 * @author danchaobing
 * 
 */
public class CunWeiHui {
	private Integer id;

	private Integer jieshu;

	private String zhiweiId;

	private Date starttime;

	private Date overtime;

	private Integer userId;

	private Integer villageinfoId;

	private Integer minzhuxuanjuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJieshu() {
		return jieshu;
	}

	public void setJieshu(Integer jieshu) {
		this.jieshu = jieshu;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getOvertime() {
		return overtime;
	}

	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVillageinfoId() {
		return villageinfoId;
	}

	public void setVillageinfoId(Integer villageinfoId) {
		this.villageinfoId = villageinfoId;
	}

	public Integer getMinzhuxuanjuId() {
		return minzhuxuanjuId;
	}

	public void setMinzhuxuanjuId(Integer minzhuxuanjuId) {
		this.minzhuxuanjuId = minzhuxuanjuId;
	}

	public String getZhiweiId() {
		return zhiweiId;
	}

	public void setZhiweiId(String zhiweiId) {
		this.zhiweiId = zhiweiId;
	}

	@Override
	public String toString() {
		return "CunWeiHui [id=" + id + ", jieshu=" + jieshu + ", zhiweiId="
				+ zhiweiId + ", starttime=" + starttime + ", overtime="
				+ overtime + ", userId=" + userId + ", villageinfoId="
				+ villageinfoId + ", minzhuxuanjuId=" + minzhuxuanjuId + "]";
	}

}