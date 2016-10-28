package com.varc.domain.extension;

import com.varc.domain.VillageInfo;

public class AccountToVillageInfo {
	private VillageInfo villageInfo = null;
	private String account;

	public VillageInfo getVillageInfo() {
		return villageInfo;
	}

	public String getAccount() {
		return account;
	}

	public void setVillageInfo(VillageInfo villageInfo) {
		this.villageInfo = villageInfo;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "AccountToVillageInfo [villageInfo=" + villageInfo
				+ ", account=" + account + "]";
	}
}
