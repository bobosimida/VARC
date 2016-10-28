/**
 * 
 */
package com.varc.domain;

/**
 * @author danchaobing
 *
 */
public class TongXunLu {
	private int id;
	private int selfId;
	private int otherId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSelfId() {
		return selfId;
	}
	public void setSelfId(int selfId) {
		this.selfId = selfId;
	}
	public int getOtherId() {
		return otherId;
	}
	public void setOtherId(int otherId) {
		this.otherId = otherId;
	}
	@Override
	public String toString() {
		return "TongXunLu [id=" + id + ", selfId=" + selfId + ", otherId="
				+ otherId + "]";
	}

	
}
