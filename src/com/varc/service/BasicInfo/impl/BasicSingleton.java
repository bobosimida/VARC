/**
 * 
 */
package com.varc.service.BasicInfo.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.varc.domain.VillageInfo;
import com.varc.mapper.BasicInfoMapper;

/**
 * @author danchaobing
 *
 */
@Component
public class BasicSingleton {
	@Resource
	BasicInfoMapper basicInfoMapper;
	public synchronized int addVilageinfo(HttpServletRequest request,VillageInfo villageInfo){
			boolean flag=true;
			
			if(villageInfo.getName().trim().equals("")){
				flag=false;
				request.setAttribute("nameError", "请输入10位以内的村庄名");
			}else if(villageInfo.getName().trim().length()>10){
				flag=false;
				request.setAttribute("nameError", "请输入10位以内的村庄名");
			}
			
			if(villageInfo.getSheng().trim().equals("")){
				flag=false;
				request.setAttribute("shengError", "请输入10位以内的省名");
			}else if(villageInfo.getSheng().trim().length()>10){
				flag=false;
				request.setAttribute("shengError", "请输入10位以内的省名");
			}
			if(villageInfo.getShi().trim().equals("")){
				flag=false;
				request.setAttribute("shiError", "请输入10位以内的市名");
			}else if(villageInfo.getShi().trim().length()>10){
				flag=false;
				request.setAttribute("shiError", "请输入10位以内的市名");
			}
			if(villageInfo.getQu().trim().equals("")){
				flag=false;
				request.setAttribute("quError", "请输入10位以内的区名");
			}else if(villageInfo.getQu().trim().length()>10){
				flag=false;
				request.setAttribute("quError", "请输入10位以内的区名");
			}
			if(villageInfo.getXiang().trim().equals("")){
				flag=false;
				request.setAttribute("xiangError", "请输入10位以内的村庄名");
			}else if(villageInfo.getXiang().trim().length()>10){
				flag=false;
				request.setAttribute("xiangError", "请输入10位以内的村庄名");
			}
			if(flag){
				this.basicInfoMapper.addVillageInfo(villageInfo);
				return this.basicInfoMapper.findVillageMaxId();
			}
		return 0;
	}
}
