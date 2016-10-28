package com.varc.domain.extension;

import com.varc.domain.TouPiao;

public class TouPiaoExtension {
	private String beVoteUserName;
	private TouPiao touPiao;

	public String getBeVoteUserName() {
		return beVoteUserName;
	}

	public TouPiao getTouPiao() {
		return touPiao;
	}

	public void setBeVoteUserName(String beVoteUserName) {
		this.beVoteUserName = beVoteUserName;
	}

	public void setTouPiao(TouPiao touPiao) {
		this.touPiao = touPiao;
	}

	@Override
	public String toString() {
		return "TouPiaoExtension [touPiao=" + touPiao + ", beVoteUserName="
				+ beVoteUserName + "]";
	}
}
