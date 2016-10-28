package com.varc.service.DemocraticManage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.varc.domain.IDemocraticManage;
import com.varc.domain.extension.IDemocraticManageExtension;

/**
 * 民主管理业务层接口
 * 
 * @author danchaobing
 * 
 */

public interface IDemocraticManageService {

	// 查询村民事件
	public List<IDemocraticManageExtension> find_guanlitianbao(
			HttpServletRequest request, HttpSession session,
			String biangengshijian);

	// 修改民事
	public boolean update_guanlitianbao(HttpServletRequest request,
			IDemocraticManage idemocraticManage, int id);

	// 添加民事
	public void insert_guanlitianbao(HttpServletRequest request,
			HttpSession session, IDemocraticManage idemocraticManage);

	public boolean account_Verification(HttpServletRequest request,
			IDemocraticManage idemocraticManage);

	// 获取所有事件
	public List<IDemocraticManageExtension> findAll_guanlitianbao(
			HttpServletRequest request, HttpSession session,
			String biangengshijian);
}
