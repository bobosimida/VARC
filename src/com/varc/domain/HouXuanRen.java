package com.varc.domain;

public class HouXuanRen {
	private int id;
	private int userId;
	private int piaoshu;
	private int minzhuxuanjuId;
	@Override
	public String toString() {
		return "HouXuanRen [id=" + id + ", userId=" + userId + ", piaoshu="
				+ piaoshu + ", minzhuxuanjuId=" + minzhuxuanjuId + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPiaoshu() {
		return piaoshu;
	}
	public void setPiaoshu(int piaoshu) {
		this.piaoshu = piaoshu;
	}
	public int getMinzhuxuanjuId() {
		return minzhuxuanjuId;
	}
	public void setMinzhuxuanjuId(int minzhuxuanjuId) {
		this.minzhuxuanjuId = minzhuxuanjuId;
	}
}
