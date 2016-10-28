package com.varc.domain.extension;

import com.varc.domain.MinZhuXuanJu;

public class MinZhuXuanJuExtension {
	private MinZhuXuanJu minZhuXuanJu;
	private String villageinfoName;
	private String tianbaoniandu;
	private String xuanjuri;

	public String getTianbaoniandu() {
		return tianbaoniandu;
	}

	public void setTianbaoniandu(String tianbaoniandu) {
		this.tianbaoniandu = tianbaoniandu;
	}

	public String getXuanjuri() {
		return xuanjuri;
	}

	public void setXuanjuri(String xuanjuri) {
		this.xuanjuri = xuanjuri;
	}

	public MinZhuXuanJu getMinZhuXuanJu() {
		return minZhuXuanJu;
	}

	public String getVillageinfoName() {
		return villageinfoName;
	}

	public void setMinZhuXuanJu(MinZhuXuanJu minZhuXuanJu) {
		this.minZhuXuanJu = minZhuXuanJu;
	}

	public void setVillageinfoName(String villageinfoName) {
		this.villageinfoName = villageinfoName;
	}

	@Override
	public String toString() {
		return "MinZhuXuanJuExtension [minZhuXuanJu=" + minZhuXuanJu
				+ ", villageinfoName=" + villageinfoName + ", tianbaoniandu="
				+ tianbaoniandu + ", xuanjuri=" + xuanjuri + "]";
	}

}
