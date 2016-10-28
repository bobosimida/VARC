/**
 * 
 */
package com.varc.domain.extension;

/**
 * 获取联系人时分页查询使用
 * @author danchaobing
 *
 */
public class TongxunluPage {
	private int Id;
	private int begin;
	
	/**
	 * @return the begin
	 */
	public int getBegin() {
		return begin;
	}
	/**
	 * @param begin the begin to set
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}
	/**
	 * @return the page
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TongxunluPage [Id=" + Id + ", begin=" + begin + "]";
	}
	
}
