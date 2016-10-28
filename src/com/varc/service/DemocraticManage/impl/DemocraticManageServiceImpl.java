package com.varc.service.DemocraticManage.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.varc.domain.IDemocraticManage;
import com.varc.domain.extension.IDemocraticManageExtension;
import com.varc.mapper.IDemocraticManageMapper;
import com.varc.service.DemocraticManage.IDemocraticManageService;

@Component
@Scope("prototype")
public class DemocraticManageServiceImpl implements IDemocraticManageService {

	@Resource
	private IDemocraticManageMapper demoManagerMapper;

	// 显示民事
	@Override
	public List<IDemocraticManageExtension> find_guanlitianbao(
			HttpServletRequest request, HttpSession session,
			String biangengshijian) {
		// 获取当前要显示的页和每页的记录数
		int thispage;
		if (request.getParameter("thispage") == null) {
			thispage = 1;
		} else {
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 5;
		// 获取登录帐号
		String account = session.getAttribute("account").toString();
		// 通过登录帐号获取村id
		int villageinfo_id = demoManagerMapper
				.findVillageinfo_idByAccount(account);

		// 把查询条件设置到IDemocraticManage中
		IDemocraticManage idemocraticManage = new IDemocraticManage();
		idemocraticManage.setVillageinfo_id(villageinfo_id);
		if (biangengshijian != null) {
			request.setAttribute("biangeng", biangengshijian);
		} else {
			biangengshijian = request.getParameter("biangeng");
			request.setAttribute("biangeng", biangengshijian);
		}
		idemocraticManage.setBiangengshijian(biangengshijian);
		// 把查询条件设置到IDemocraticManageExtension中
		IDemocraticManageExtension queryIDemocraticManage = new IDemocraticManageExtension();
		queryIDemocraticManage.setDemoManager(idemocraticManage);
		int start = (thispage - 1) * rowperpage;
		queryIDemocraticManage.setStart(start);
		queryIDemocraticManage.setRowperpage(rowperpage);

		// 通过条件查询返回IDemocraticManageExtension集合
		List<IDemocraticManageExtension> queryList = demoManagerMapper
				.find_guanlitianbao(queryIDemocraticManage);
		for (IDemocraticManageExtension idemocraticManageExtension : queryList) {
			String name = demoManagerMapper
					.findNameByAccount(idemocraticManageExtension
							.getDemoManager().getAccount());
			idemocraticManageExtension.setName(name);
			// 当前页
			idemocraticManageExtension.setThispage(thispage);
			// 共有多少行
			int countrow = demoManagerMapper
					.getCountrow(queryIDemocraticManage).intValue();
			idemocraticManageExtension.setCountrow(countrow);
			// 共有多少页
			int countpage = countrow / rowperpage
					+ (countrow % rowperpage == 0 ? 0 : 1);
			idemocraticManageExtension.setCountpage(countpage);
			// 首页
			idemocraticManageExtension.setFirstpage(1);
			// 尾页
			idemocraticManageExtension.setLastpage(countpage);
			// 上一页
			idemocraticManageExtension.setPrepage(thispage == 1 ? 1
					: thispage - 1);
			// 下一页
			idemocraticManageExtension
					.setNextpage(thispage == countpage ? countpage
							: thispage + 1);
		}
		return queryList;
	}

	// 显示所有民事
	public List<IDemocraticManageExtension> findAll_guanlitianbao(
			HttpServletRequest request, HttpSession session,
			String biangengshijian) {

		// 把查询条件设置到IDemocraticManage中
		IDemocraticManage idemocraticManage = new IDemocraticManage();
		if (biangengshijian != null) {
			request.setAttribute("biangeng", biangengshijian);
		} else {
			biangengshijian = request.getParameter("biangeng");
			request.setAttribute("biangeng", biangengshijian);
		}
		idemocraticManage.setBiangengshijian(biangengshijian);

		// 获取当前要显示的页和每页的记录数
		int thispage;
		if (request.getParameter("thispage") == null) {
			thispage = 1;
		} else {
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 5;
		// 把查询条件设置到IDemocraticManageExtension中
		IDemocraticManageExtension queryIDemocraticManage = new IDemocraticManageExtension();
		queryIDemocraticManage.setDemoManager(idemocraticManage);
		int start = (thispage - 1) * rowperpage;
		queryIDemocraticManage.setStart(start);
		queryIDemocraticManage.setRowperpage(rowperpage);

		// 通过条件查询返回IDemocraticManageExtension集合
		List<IDemocraticManageExtension> queryList = demoManagerMapper
				.findAll_guanlitianbao(queryIDemocraticManage);
		for (IDemocraticManageExtension idemocraticManageExtension : queryList) {
			// 查询帐号对应的姓名
			String name = demoManagerMapper
					.findNameByAccount(idemocraticManageExtension
							.getDemoManager().getAccount());
			idemocraticManageExtension.setName(name);
			// 查询村id对应的村名
			String villageinfoName = demoManagerMapper
					.findNameByVillageinfo(idemocraticManageExtension
							.getDemoManager().getVillageinfo_id());
			idemocraticManageExtension.setVillageinfoName(villageinfoName);
			// 当前页
			idemocraticManageExtension.setThispage(thispage);
			// 共有多少行
			int countrow = demoManagerMapper.getCountrowAll(
					queryIDemocraticManage).intValue();
			idemocraticManageExtension.setCountrow(countrow);
			// 共有多少页
			int countpage = countrow / rowperpage
					+ (countrow % rowperpage == 0 ? 0 : 1);
			idemocraticManageExtension.setCountpage(countpage);
			// 首页
			idemocraticManageExtension.setFirstpage(1);
			// 尾页
			idemocraticManageExtension.setLastpage(countpage);
			// 上一页
			idemocraticManageExtension.setPrepage(thispage == 1 ? 1
					: thispage - 1);
			// 下一页
			idemocraticManageExtension
					.setNextpage(thispage == countpage ? countpage
							: thispage + 1);
		}
		return queryList;
	}

	// 修改民事
	@Override
	public boolean update_guanlitianbao(HttpServletRequest request,
			IDemocraticManage idemocraticManage, int id) {
		boolean flag = false;
		idemocraticManage.setId(id);
		if (id != 0) {
			demoManagerMapper.update_guanlitianbao(idemocraticManage);
			flag = true;
		}
		return flag;
	}

	// 添加民事
	@Override
	public void insert_guanlitianbao(HttpServletRequest request,
			HttpSession session, IDemocraticManage idemocraticManage) {
		String account = session.getAttribute("account").toString();
		int villageinfo_id = demoManagerMapper
				.findVillageinfo_idByAccount(account);
		idemocraticManage.setVillageinfo_id(villageinfo_id);
		demoManagerMapper.insert_guanlitianbao(idemocraticManage);
	}

	// 验证保存的或者修改的数据是否合格
	@Override
	public boolean account_Verification(HttpServletRequest request,
			IDemocraticManage idemocraticManage) {
		boolean flags = false;
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		String accountFlag = demoManagerMapper.findAccount(idemocraticManage
				.getAccount());
		if (accountFlag == null) {
			request.setAttribute("accountError", "帐号不存在");
			flag1 = false;
		} else if (idemocraticManage.getReason().length() > 255) {
			request.setAttribute("reasonError", "输入的字数过长");
			flag2 = false;
		} else if ("".equals(idemocraticManage.getBiangengshijian())
				|| idemocraticManage.getBiangengshijian() == null) {
			request.setAttribute("biangengshijianError", "变更时间不能为空");
			flag3 = false;
		}
		if (flag1 && flag2 && flag3) {
			flags = true;
		}
		return flags;
	}
}
