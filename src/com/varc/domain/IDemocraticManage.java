package com.varc.domain;

import java.sql.Date;

public class IDemocraticManage {

	private int id;
	private Date tianbaoniandu;
	private String renshibiangeng;
	private String cunweizizhizhangcheng;
	private String account;
	private String reason;
	private String position;
	private String biangengshijian;
	private int villageinfo_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTianbaoniandu() {
		return tianbaoniandu;
	}
	public void setTianbaoniandu(Date tianbaoniandu) {
		this.tianbaoniandu = tianbaoniandu;
	}
	public String getRenshibiangeng() {
		return renshibiangeng;
	}
	public void setRenshibiangeng(String renshibiangeng) {
		this.renshibiangeng = renshibiangeng;
	}
	public String getCunweizizhizhangcheng() {
		return cunweizizhizhangcheng;
	}
	public void setCunweizizhizhangcheng(String cunweizizhizhangcheng) {
		this.cunweizizhizhangcheng = cunweizizhizhangcheng;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBiangengshijian() {
		return biangengshijian;
	}
	public void setBiangengshijian(String biangengshijian) {
		this.biangengshijian = biangengshijian;
	}
	public int getVillageinfo_id() {
		return villageinfo_id;
	}
	public void setVillageinfo_id(int villageinfo_id) {
		this.villageinfo_id = villageinfo_id;
	}
	@Override
	public String toString() {
		return "IDemocraticManage [id=" + id + ", tianbaoniandu="
				+ tianbaoniandu + ", renshibiangeng=" + renshibiangeng
				+ ", cunweizizhizhangcheng=" + cunweizizhizhangcheng
				+ ", account=" + account + ", reason=" + reason + ", position="
				+ position + ", biangengshijian=" + biangengshijian
				+ ", villageinfo_id=" + villageinfo_id + "]";
	}	
}
