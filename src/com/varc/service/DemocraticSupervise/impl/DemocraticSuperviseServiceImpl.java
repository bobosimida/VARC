package com.varc.service.DemocraticSupervise.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.varc.domain.MinZhuJianDu;
import com.varc.domain.extension.MinZhuJianDuExtension;
import com.varc.domain.extension.MinZhuJianDuFindByTimeAndId;
import com.varc.mapper.SuperviseMapper;
import com.varc.service.DemocraticSupervise.IDemocraticSuperviseService;


/**
 * 民主监督业务层实现
 * @author sunrong
 *
 */
@Component
@Scope("prototype")
public class DemocraticSuperviseServiceImpl implements IDemocraticSuperviseService {
	@Resource
	private SuperviseMapper superviseMapper;

	@Override
	 public boolean info_Verification(HttpServletRequest request,MinZhuJianDu minzhujiandu)
	{
		boolean flag=true;
		
		if(minzhujiandu.getGongkaishijian()==null || minzhujiandu.getGongkaishijian().equals("")) {
			flag = false;
			request.setAttribute("gongkaishijianError", "请输入正确的公开时间");
		} 
		if(minzhujiandu.getVillageinfo_id()==null){
			request.setAttribute("villageinfoIdError", "请输入正确村庄编号");
			flag=false;
		}else{
			try{
		        Integer villageId=superviseMapper.findVillageIdByVillageId(Integer.parseInt(minzhujiandu.getVillageinfo_id()));
		        
				if (villageId!=null) {
					if (villageId != Integer.parseInt(minzhujiandu
							.getVillageinfo_id())) {
						request.setAttribute("villageinfoIdError", "请输入正确村庄编号");
						flag = false;
					}
				}else{
					request.setAttribute("villageinfoIdError", "请输入正确村庄编号");
					flag = false;
				}
			}catch (NumberFormatException e) {
				request.setAttribute("villageinfoIdError", "请输入正确村庄编号");
				flag=false;
			}
		}
		if(!minzhujiandu.getCaiwu().equals("")){
			if(minzhujiandu.getCaiwu().length()>500){
				request.setAttribute("caiwuError", "请输入500字以内的财务数据");
				flag=false;
			}
		}else{
			request.setAttribute("caiwuError", "请输入500字以内的财务数据");
			flag=false;
		}
		
		if(!minzhujiandu.getCunwu().equals("")){
			if(minzhujiandu.getCunwu().length()>500){
				request.setAttribute("cunwuError", "请输入500字以内的村务数据");
				flag=false;
			}
		}else{
			request.setAttribute("cunwuError", "请输入500字以内的村务数据");
			flag=false;
		}
		
		if(!minzhujiandu.getZhengwu().equals("")){
			if(minzhujiandu.getZhengwu().length()>500){
				request.setAttribute("zhengwuError", "请输入500字以内的政务数据");
				flag=false;
			}
		}else{
			request.setAttribute("zhengwuError", "请输入500字以内的政务数据");
			flag=false;
		}
		
		if(!minzhujiandu.getGongkaixingshi().equals("")){
			if(minzhujiandu.getGongkaixingshi().length()>500){
				request.setAttribute("gongkaixingshiError", "请输入100字以内的公开形式数据");
				flag=false;
			}
		}else{
			request.setAttribute("gongkaixingshiError", "请输入100字以内的公开形式数据");
			flag=false;
		}
		
		if(!minzhujiandu.getCwjdcybpy().equals(""))
		{
			try{
				Integer.parseInt(minzhujiandu.getCwjdcybpy());
			}catch (NumberFormatException e) {
				request.setAttribute("cwjdcybpyError", "请输入正确的数字");
				flag=false;
			}
		}else{
			request.setAttribute("cwjdcybpyError", "请输入正确的数字");
			flag=false;
		}
		if(minzhujiandu.getCglrywgbt()!=null&&!minzhujiandu.getCglrywgbt().equals(""))
		{
			try{
				Integer.parseInt(minzhujiandu.getCglrywgbt());
			}catch (NumberFormatException e) {
				request.setAttribute("cglrywgbtError", "请输入正确的数字");
				flag=false;
			}
		}else{
			request.setAttribute("cglrywgbtError", "请输入正确的数字");
			flag=false;
		}
		if(flag){
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			minzhujiandu.setTianbaoniandu(sdf.format(d));
	
		}		
		return flag;
	}
	@Override
	public boolean updateMinZhuJianDu(HttpServletRequest request,MinZhuJianDu minzhujiandu) {
		boolean flag=true;
		MinZhuJianDu oldMinZhuJianDu=this.superviseMapper.findMinZhuJianDuById(minzhujiandu.getId());
		if(!minzhujiandu.getCaiwu().equals("")){
			oldMinZhuJianDu.setCaiwu(minzhujiandu.getCaiwu());
		}
		if(!minzhujiandu.getCglrywgbt().equals("")){
			oldMinZhuJianDu.setCglrywgbt(minzhujiandu.getCglrywgbt());
		}
		if(!minzhujiandu.getCunwu().equals("")){
			oldMinZhuJianDu.setCunwu(minzhujiandu.getCunwu());
		}
		if(!minzhujiandu.getCwjdcybpy().equals("")){
			oldMinZhuJianDu.setCwjdcybpy(minzhujiandu.getCwjdcybpy());
		}
		if(!minzhujiandu.getGongkaishijian().equals("")){
			oldMinZhuJianDu.setGongkaishijian(minzhujiandu.getGongkaishijian());
		}
		if(!minzhujiandu.getGongkaixingshi().equals("")){
			oldMinZhuJianDu.setGongkaixingshi(minzhujiandu.getGongkaixingshi());
		}
		if(!minzhujiandu.getZhengwu().equals("")){
			oldMinZhuJianDu.setZhengwu(minzhujiandu.getZhengwu());
		}
		if(this.info_Verification(request, oldMinZhuJianDu)){
			flag=true;
			superviseMapper.updateMinZhuJianDu(oldMinZhuJianDu);
		}else{
			flag=false;
		}
		return flag;
	}

	@Override
	public List<MinZhuJianDuExtension> findMinZhuJianDuByVillageIdAndTianBaoNianDu(
			HttpServletRequest request, HttpSession session, Integer id,
			Date date) {
		
		return null;
	}
	
	
	
	
	
	
	
	
	@Transactional
	@Override
	public void addMinZhuJianDu(HttpServletRequest request,
			HttpSession session, MinZhuJianDu minzhujiandu) {
		superviseMapper.addMinZhuJianDu(minzhujiandu);
	}
	
	
	
	@Override
	public List<MinZhuJianDuExtension> findMinZhuJianDu(String page){
		return this.superviseMapper.findMinZhuJianDu((Integer.parseInt(page)-1)*8);
	}
	@Override
	public Integer findMinZhuJianDuCount(){
		return this.superviseMapper.findMinZhuJianDuCount();
	}
	@Override
	public List<MinZhuJianDuExtension> findMinZhuJianDuByTimeAndId(String id,String date,HttpServletRequest request,String page){
		if(id==null && date==null) return null;
		MinZhuJianDuFindByTimeAndId andId=new MinZhuJianDuFindByTimeAndId();
		andId.setDate(date);
		andId.setPage((Integer.parseInt(page)-1)*8);
		try{
			andId.setId(Integer.parseInt(id));
		}catch (NumberFormatException e) {
			request.setAttribute("errorId", "请输入正确的村庄id");
			return null;
		}
		return this.superviseMapper.findMinZhuJianDuByTimeAndId(andId);
	}
	@Override
	public Integer findMinZhuJianDuCountByTimeAndId(String id ,String date,HttpServletRequest request,String page){
		if(id==null && date==null) return null;
		MinZhuJianDuFindByTimeAndId andId=new MinZhuJianDuFindByTimeAndId();
		andId.setDate(date);
		andId.setPage(Integer.parseInt(page));
		try{
		andId.setId(Integer.parseInt(id));
		}catch (NumberFormatException e) {
			request.setAttribute("errorId", "请输入正确的村庄id");
			return null;
		}
		return this.superviseMapper.findMinZhuJianDuCountByTimeAndId(andId);
	}
	@Override
	public Integer findVillageInfoIdByAccount(String account){
		return this.superviseMapper.findVillageInfoIdByAccount(account);
	}
}
