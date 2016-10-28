package com.varc.domain.extension;

import com.varc.domain.User;

public class UserExtension {
	private User user;
	private String rollName;
	private String villageName;

	public User getUser() {
		return user;
	}

	public String getRollName() {
		return rollName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	@Override
	public String toString() {
		return "UserExtension [user=" + user + ", rollName=" + rollName
				+ ", villageName=" + villageName + "]";
	}
}
