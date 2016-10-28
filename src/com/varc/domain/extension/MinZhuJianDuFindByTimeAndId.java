/**
 * 
 */
package com.varc.domain.extension;

/**
 * @author danchaobing
 *
 */
public class MinZhuJianDuFindByTimeAndId {
	private int id;
	private String date;
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	@Override
	public String toString() {
		return "MinZhuJianDuFindByTimeAndId [id=" + id + ", date=" + date
				+ ", page=" + page + "]";
	}
	
}
