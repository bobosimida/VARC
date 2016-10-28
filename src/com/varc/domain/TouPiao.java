package com.varc.domain;

public class TouPiao {
	private int beUserId;
	private int id;
	private int minzhuxuanjuId;
	private int userId;

	public int getBeUserId() {
		return beUserId;
	}

	public int getId() {
		return id;
	}

	public int getMinzhuxuanjuId() {
		return minzhuxuanjuId;
	}

	public int getUserId() {
		return userId;
	}

	public void setBeUserId(int beUserId) {
		this.beUserId = beUserId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMinzhuxuanjuId(int minzhuxuanjuId) {
		this.minzhuxuanjuId = minzhuxuanjuId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TouPiao [id=" + id + ", userId=" + userId + ", beUserId="
				+ beUserId + ", minzhuxuanjuId=" + minzhuxuanjuId + "]";
	}
}
