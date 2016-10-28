package com.varc.domain.extension;

import com.varc.domain.CunWeiHui;

public class CunWeiHuiExtension {
	private String zhiwei;
	private CunWeiHui cunWeiHui;
	private String name;
//	private Integer id;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZhiwei() {
		return zhiwei;
	}

	public CunWeiHui getCunWeiHui() {
		return cunWeiHui;
	}
	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}
	
	public void setCunWeiHui(CunWeiHui cunWeiHui) {
		this.cunWeiHui = cunWeiHui;
	}
	@Override
	public String toString() {
		return "CunWeiHuiExtension [zhiwei=" + zhiwei + ", cunWeiHui="
				+ zhiwei + ", name=" + name + "]";
	}

}
