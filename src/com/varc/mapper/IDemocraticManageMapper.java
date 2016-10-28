package com.varc.mapper;

import java.util.List;

import com.varc.domain.IDemocraticManage;
import com.varc.domain.extension.IDemocraticManageExtension;

public interface IDemocraticManageMapper {

	// 通过account查找villageinfo_id
	public int findVillageinfo_idByAccount(String account);

	// 通过account查找user的name
	public String findNameByAccount(String account);

	// 查询民事
	public List<IDemocraticManageExtension> find_guanlitianbao(
			IDemocraticManageExtension idemocraticManageExtension);

	// 修改民事
	public void update_guanlitianbao(IDemocraticManage idemocraticManage);

	// 添加民事
	public void insert_guanlitianbao(IDemocraticManage idemocraticManage);

	// 一共有多少行数据
	public Long getCountrow(
			IDemocraticManageExtension idemocraticManageExtension);

	// 获取的帐号
	public String findAccount(String account);

	// 查询所有的民事
	public List<IDemocraticManageExtension> findAll_guanlitianbao(
			IDemocraticManageExtension queryIDemocraticManage);

	// 所有民事行数
	public Long getCountrowAll(IDemocraticManageExtension queryIDemocraticManage);

	// 根据村id查找村名
	public String findNameByVillageinfo(int villageinfo_id);
}
