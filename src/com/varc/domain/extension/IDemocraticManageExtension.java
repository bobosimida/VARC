package com.varc.domain.extension;

import com.varc.domain.IDemocraticManage;

public class IDemocraticManageExtension {

	private IDemocraticManage demoManager;
	private String name;//帐号对应的用户名
	
	private int thispage; //当前页
	private int rowperpage;//每页多少行
	private int countrow; //一共有多少行
	private int countpage;//一共有多少页
	private int firstpage; //首页
	private int lastpage;//尾页
	private int prepage;//上一页
	private int nextpage;//下一页
	private int start;	//当前页的开始行数
	private String villageinfoName;

	public String getVillageinfoName() {
			return villageinfoName;
		}
		public void setVillageinfoName(String villageinfoName) {
			this.villageinfoName = villageinfoName;
		}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public IDemocraticManage getDemoManager() {
		return demoManager;
	}
	public void setDemoManager(IDemocraticManage demoManager) {
		this.demoManager = demoManager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getThispage() {
		return thispage;
	}
	public void setThispage(int thispage) {
		this.thispage = thispage;
	}
	public int getRowperpage() {
		return rowperpage;
	}
	public void setRowperpage(int rowperpage) {
		this.rowperpage = rowperpage;
	}
	public int getCountrow() {
		return countrow;
	}
	public void setCountrow(int countrow) {
		this.countrow = countrow;
	}
	public int getCountpage() {
		return countpage;
	}
	public void setCountpage(int countpage) {
		this.countpage = countpage;
	}
	public int getFirstpage() {
		return firstpage;
	}
	public void setFirstpage(int firstpage) {
		this.firstpage = firstpage;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public int getPrepage() {
		return prepage;
	}
	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}
	public int getNextpage() {
		return nextpage;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IDemocraticManageExtension [demoManager=" + demoManager
				+ ", name=" + name + ", thispage=" + thispage + ", rowperpage="
				+ rowperpage + ", countrow=" + countrow + ", countpage="
				+ countpage + ", firstpage=" + firstpage + ", lastpage="
				+ lastpage + ", prepage=" + prepage + ", nextpage=" + nextpage
				+ ", start=" + start + ", villageinfoName=" + villageinfoName
				+ "]";
	}
	
}
