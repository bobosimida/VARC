package com.varc.domain.extension;

import com.varc.domain.HouXuanRen;

public class HouXuanRenExtension {
	private HouXuanRen houXuanRen;
	private String name;

	public HouXuanRen getHouXuanRen() {
		return houXuanRen;
	}

	public String getName() {
		return name;
	}

	public void setHouXuanRen(HouXuanRen houXuanRen) {
		this.houXuanRen = houXuanRen;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HouXuanRenExtension [houXuanRen=" + houXuanRen + ", name="
				+ name + "]";
	}
}
