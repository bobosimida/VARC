package com.varc.domain;

public class MinZhuJianDu {
	private Integer id;
	
	private String tianbaoniandu;
	
	private String gongkaishijian;
	
	private String caiwu;
	
	private String cunwu;
	
	private String zhengwu;
	
	private String gongkaixingshi;
	
	
	private String cwjdcybpy;
	
	private String cglrywgbt;
	
	private String villageinfo_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getTianbaoniandu() {
		return tianbaoniandu;
	}

	public void setTianbaoniandu(String tianbaoniandu) {
		this.tianbaoniandu = tianbaoniandu;
	}

	public String getGongkaishijian() {
		return gongkaishijian;
	}

	public void setGongkaishijian(String gongkaishijian) {
		this.gongkaishijian = gongkaishijian;
	}

	public String getCaiwu() {
		return caiwu;
	}

	public void setCaiwu(String caiwu) {
		this.caiwu = caiwu;
	}

	public String getCunwu() {
		return cunwu;
	}

	public void setCunwu(String cunwu) {
		this.cunwu = cunwu;
	}

	public String getZhengwu() {
		return zhengwu;
	}

	public void setZhengwu(String zhengwu) {
		this.zhengwu = zhengwu;
	}

	public String getGongkaixingshi() {
		return gongkaixingshi;
	}

	public void setGongkaixingshi(String gongkaixingshi) {
		this.gongkaixingshi = gongkaixingshi;
	}
	public String getCwjdcybpy() {
		return cwjdcybpy;
	}
	public void setCwjdcybpy(String cwjdcybpy) {
		this.cwjdcybpy = cwjdcybpy;
	}

	public String getCglrywgbt() {
		return cglrywgbt;
	}

	/**
	 * @param cglrywgbt the cglrywgbt to set
	 */
	public void setCglrywgbt(String cglrywgbt) {
		this.cglrywgbt = cglrywgbt;
	}




	/**
	 * @return the villageinfo_id
	 */
	public String getVillageinfo_id() {
		return villageinfo_id;
	}

	/**
	 * @param villageinfo_id the villageinfo_id to set
	 */
	public void setVillageinfo_id(String villageinfo_id) {
		this.villageinfo_id = villageinfo_id;
	}

	@Override
	public String toString() {
		return "MinZhuJianDu [id=" + id + ", tianbaoniandu="
				+ tianbaoniandu + ", gongkaishijian=" + gongkaishijian
				+ ", caiwu=" + caiwu+ ", cunwu=" + cunwu + ",zhengwu="
				+ zhengwu + ", gongkaixingshi=" + gongkaixingshi
				+ ", cwjdcybpy=" + cwjdcybpy
				+ ", cglrywgbt=" + cglrywgbt+ ", villageinfo_id=" + villageinfo_id + "]";			
	}
}
