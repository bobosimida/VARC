package com.varc.domain.extension;

import com.varc.domain.MinZhuJianDu;

public class MinZhuJianDuExtension {
	private MinZhuJianDu minZhuJianDu;
	
	
	private String name;
	
	
	public MinZhuJianDu getMinZhuJianDu() {
		return minZhuJianDu;
	}

	public void setMinZhuJianDu(MinZhuJianDu minZhuJianDu) {
		this.minZhuJianDu = minZhuJianDu;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "MinZhuJianDuExtension=[minZhuJianDu="+minZhuJianDu
				+",villageinfoId="+",villageinfoName="
				+name+",tianbaoniandu="+"]";
	}
	
	
}
