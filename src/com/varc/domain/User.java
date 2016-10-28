/**
 * 
 */
package com.varc.domain;

import org.springframework.stereotype.Component;

/**
 * 用户基本信息类
 * 对应数据库user表
 * @author danchaobing
 * 
 */
@Component
public class User {
	private Integer id;

	private String account;

	private String pwd;

	private String name;

	private String unit;

	private String tel;

	private String email;

	private String sex;

	private String age;

	private Integer rollId;

	private Integer villageinfoId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		if (account == null) {
			account = null;
		} else {
			account.trim();
		}
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age == null ? null : age.trim();
	}

	public Integer getRollId() {
		return rollId;
	}

	public void setRollId(Integer rollId) {
		this.rollId = rollId;
	}

	public Integer getVillageinfoId() {
		return villageinfoId;
	}

	public void setVillageinfoId(Integer villageinfoId) {
		this.villageinfoId = villageinfoId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", pwd=" + pwd
				+ ", name=" + name + ", unit=" + unit + ", tel=" + tel
				+ ", email=" + email + ", sex=" + sex + ", age=" + age
				+ ", rollId=" + rollId + ", villageinfoId=" + villageinfoId
				+ "]";
	}
}