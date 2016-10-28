package com.varc.domain;

import java.util.Arrays;
import java.util.Date;

public class MinZhuXuanJu {
	private String cunweihuirenshu;

	private Integer id;

	private String shenqingzhuangtai;

	private byte[] shishifangan;

	private Date tianbaoniandu;

	private Integer villageinfoId;

	private String xuanjujindu;

	private String xuanjuri;
	
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCunweihuirenshu() {
		return cunweihuirenshu;
	}

	public Integer getId() {
		return id;
	}

	public String getShenqingzhuangtai() {
		return shenqingzhuangtai;
	}

	public byte[] getShishifangan() {
		return shishifangan;
	}

	public Date getTianbaoniandu() {
		return tianbaoniandu;
	}

	public Integer getVillageinfoId() {
		return villageinfoId;
	}

	public String getXuanjujindu() {
		return xuanjujindu;
	}



	public void setCunweihuirenshu(String cunweihuirenshu) {
		this.cunweihuirenshu = cunweihuirenshu;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setShenqingzhuangtai(String shenqingzhuangtai) {
		this.shenqingzhuangtai = shenqingzhuangtai;
	}

	public void setShishifangan(byte[] shishifangan) {
		this.shishifangan = shishifangan;
	}

	public void setTianbaoniandu(Date tianbaoniandu) {
		this.tianbaoniandu = tianbaoniandu;
	}

	public void setVillageinfoId(Integer villageinfoId) {
		this.villageinfoId = villageinfoId;
	}

	public void setXuanjujindu(String xuanjujindu) {
		this.xuanjujindu = xuanjujindu == null ? null : xuanjujindu.trim();
	}


	/**
	 * @return the xuanjuri
	 */
	public String getXuanjuri() {
		return xuanjuri;
	}

	/**
	 * @param xuanjuri the xuanjuri to set
	 */
	public void setXuanjuri(String xuanjuri) {
		this.xuanjuri = xuanjuri;
	}

	@Override
	public String toString() {
		return "MinZhuXuanJu [cunweihuirenshu=" + cunweihuirenshu + ", id="
				+ id + ", shenqingzhuangtai=" + shenqingzhuangtai
				+ ", shishifangan=" + Arrays.toString(shishifangan)
				+ ", tianbaoniandu=" + tianbaoniandu + ", villageinfoId="
				+ villageinfoId + ", xuanjujindu=" + xuanjujindu
				+ ", xuanjuri=" + xuanjuri + ", filename=" + filename + "]";
	}


}