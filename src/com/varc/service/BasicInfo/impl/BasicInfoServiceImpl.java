/**
 * 
 */
package com.varc.service.BasicInfo.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.varc.domain.CunWeiHui;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.User;
import com.varc.domain.VillageInfo;
import com.varc.domain.extension.AccountToVillageInfo;
import com.varc.domain.extension.CunWeiHuiExtension;
import com.varc.domain.extension.Page;
import com.varc.domain.extension.UserExtension;
import com.varc.mapper.BasicInfoMapper;
import com.varc.service.BasicInfo.IBasicInfoService;

/**
 * @author danchaobing
 *
 */
@Component
@Scope("prototype")
public class BasicInfoServiceImpl implements IBasicInfoService {
	@Resource
	private BasicInfoMapper basicInfoMapper;
	@Override
	public User findUserById(int id) {
		
		return this.basicInfoMapper.findUserByUserId(id);
	}
	@Override
	public CunWeiHui findCunWeiHuiMember(int userId) {
		return this.basicInfoMapper.findCunWeiHuiMember(userId);
	}
	@Override
	public MinZhuXuanJu findMinZhuXuanJuByMinZhuXuanJuId(int id) {
		return this.basicInfoMapper.findMinZhuXuanJuByMinZhuXuanJuId(id);
	}
	
	@Transactional
	@Override
	public boolean updateUserPwd(HttpServletRequest request,String account,String oldPwd,String newPwd,String newPwdA) {
		Boolean flag=true;
		User user=new User();
		if(oldPwd.equals("")){
			request.setAttribute("oldPwd", "请输入旧密码");
			flag=false;
		}
		if(!newPwd.equals("")){
			
			if(!newPwd.equals(newPwdA)){
				flag=false;
				request.setAttribute("pwdA", "两次密码输入不一致");//两次密码输入不一致
			}
			if(newPwd.length()>20 || newPwd.length()<9){
				flag=false;
				request.setAttribute("newPwdLength", "请输入9-20位长度的密码");//两次密码输入不一致
			}
		}else{
			flag=false;
			request.setAttribute("newPwd", "新密码为空");//新密码为空
		}
		if(flag){
			String pwd=this.basicInfoMapper.findPwdByAccount(account);
			if(!pwd.equals(oldPwd)){
				flag=false;
				request.setAttribute("oldPwd", "旧密码输入错误");//旧密码输入错误
			}
		}
		if(flag){
			user.setPwd(newPwd);
			user.setAccount(account);
			basicInfoMapper.updateUserPwd(user);
		}
		return flag;
	}
	@Transactional
	@Override
	public boolean updateUserInfo(HttpServletRequest request,User user) {
		boolean flag=true;
		boolean change=false;
		User oldUser=this.findUserByAccount(user.getAccount()).getUser();
		if (user.getUnit() != null) {
			if (!user.getUnit().trim().equals("")) {
				change = true;
				if (user.getUnit().trim().length() > 50) {
					request.setAttribute("unitError", "请输入50字以内的单位名称");
					flag = false;
				} else {
					oldUser.setUnit(user.getUnit());
				}
			}
		}
		if(user.getTel()!=null){
			if(!user.getTel().trim().equals("")){
				change=true;
				if(user.getTel().trim().length()>12){
					request.setAttribute("telError", "请输入正确的号码");
					flag=false;
				}else{
					try{
						String[] tels=user.getTel().split("-");
						for(int i=0;i<tels.length;i++){
							Double.parseDouble(tels[i].trim());
						}
						oldUser.setTel(user.getTel());
					}catch (NumberFormatException e) {
						request.setAttribute("telError", "请输入正确的号码");
						flag=false;
					}
				}
			}
		}
		if(user.getEmail()!=null){
			if(!user.getEmail().trim().equals("")){
				change=true;
				if(user.getEmail().trim().length()>20){
					request.setAttribute("emailError", "请输入20位以内的邮箱");
					flag=false;
				}else if(!user.getEmail().trim().matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
					request.setAttribute("emailError", "请输入正确的邮箱");
					flag=false;
				}else {
					oldUser.setEmail(user.getEmail());
				}
			}
		}
		if (user.getAge() != null) {
			if (!user.getAge().trim().equals("")) {
				change = true;
				try {
					int age = Integer.parseInt(user.getAge());
					Date date=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String d=sdf.format(date);
					int year=Integer.parseInt(d.split("-")[0]);
					if (age - year > 0) {
						request.setAttribute("ageError", "请输入正确的出生年份");
						flag = false;
					} else {
						oldUser.setAge(age + "");
					}
				} catch (NumberFormatException e) {
					request.setAttribute("ageError", "请输入正确的出生年份");
					flag = false;
				}
			}
		}
		if(user.getSex()!=null){
			oldUser.setSex(user.getSex());
			change=true;
		}
		if(!change){
			request.setAttribute("noChange", "没有修改任何内容");
			flag=false;
		}
		if(flag){
			basicInfoMapper.updateUserInfo(oldUser);
		}else{
			request.setAttribute("updateAccount", user.getAccount());
		}
		return flag;
		
	}
	@Override
	/**
	 * @author sunrong
	 * */
	public UserExtension findUserByAccount(String account) {
		if(!account.equals("")){
			UserExtension user=this.basicInfoMapper.findUserByAccount(account);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date=sdf.format(new Date());
			if(user!=null){
				int age=Integer.parseInt(date.split("-")[0]) - Integer.parseInt(user.getUser().getAge());
				user.getUser().setAge(age+"");
				return user;
			}
		}
		return null;
	}

	@Override
	public VillageInfo findVillageInfoByUserAccount(String account) {
		VillageInfo villageInfo=this.basicInfoMapper.findVillageInfoByUserAccount(account);
		shiFou(villageInfo);
		
		return villageInfo;
	}
	/**
	 * 将村庄的01变成是否
	 * @author danchaobing
	 * 
	 */
	private void shiFou(VillageInfo villageInfo) {
		if(villageInfo.getZirancun().equals("0")){
			villageInfo.setZirancun("否");
		}else{villageInfo.setZirancun("是");}

		if(villageInfo.getTongshui().equals("0")){
			villageInfo.setTongshui("否");
		}else{villageInfo.setTongshui("是");}

		if(villageInfo.getTongdian().equals("0")){
			villageInfo.setTongdian("否");
		}else{villageInfo.setTongdian("是");}

		if(villageInfo.getYinshuikunnan().equals("0")){
			villageInfo.setYinshuikunnan("否");
		}else{villageInfo.setYinshuikunnan("是");}

		if(villageInfo.getTongwangluo().equals("0")){
			villageInfo.setTongwangluo("否");
		}else{villageInfo.setTongwangluo("是");}

		if(villageInfo.getTongdianhua().equals("0")){
			villageInfo.setTongdianhua("否");
		}else{villageInfo.setTongdianhua("是");}

		if(villageInfo.getTongyouxian().equals("0")){
			villageInfo.setTongyouxian("否");
		}else{villageInfo.setTongyouxian("是");}
	}
	public VillageInfo findVillageInfoById(String id,HttpServletRequest request){
		boolean flag=true;
		int num=0;
		try{
			num=Integer.parseInt(id);
		}catch (NumberFormatException e) {
			request.setAttribute("idError", "请输入正确的数字");
			flag=false;
		}
		if(flag){
			VillageInfo villageInfo= this.basicInfoMapper.findVillageinfoById(num);
			if(villageInfo==null){
				request.setAttribute("idError", "村庄id不存在");
			}
			shiFou(villageInfo);
			return villageInfo;
		}
		return null;
	}
	@Override
	public List<CunWeiHuiExtension> findCunWeiHuiByVillageinfoId(int id) {
		return this.basicInfoMapper.findCunWeiHuiByVillageinfoId(id);
	}
	
	
	@Override
	public boolean updateVillageInfoByUserAccount(String account,VillageInfo villageInfo,HttpServletRequest request) {
		boolean flag=true;
		boolean nocChange=true;
		VillageInfo oldVillageInfo=this.basicInfoMapper.findVillageInfoByUserAccount(account);
		if(villageInfo.getDibao()!=null && !villageInfo.getDibao().equals("")){
			nocChange=false;
			try {
				if( Integer.parseInt(villageInfo.getDibao())<0){
					request.setAttribute("diBaoError", "不能为负数");
					flag=false;
				}else{
					oldVillageInfo.setDibao(villageInfo.getDibao());
				}
				
			} catch (NumberFormatException e) {
				request.setAttribute("diBaoError", "请输入正确数字");
				flag=false;
			}
		}
		if(villageInfo.getWubao()!=null && !villageInfo.getWubao().equals("")){
			nocChange=false;
			try
		        {
		          if(Integer.parseInt(villageInfo.getWubao())<0){
		        	  request.setAttribute("wuBaoError","不能为负数" );
		        	  flag=false;
		          }else{
		        	  oldVillageInfo.setWubao(villageInfo.getWubao());
					}
		          
		        }catch (NumberFormatException e)
					{
		        	 request.setAttribute("wuBaoError","请输入正确数字" );
		        	  flag=false;
				}
		}
		if(villageInfo.getCanji()!=null && !villageInfo.getCanji().equals("")){
			nocChange = false;
			try {
				if (Integer.parseInt(villageInfo.getCanji()) < 0) {
					request.setAttribute("canJiError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setCanji(villageInfo.getCanji());
				}
				
			} catch (NumberFormatException e) {
				request.setAttribute("canJiError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getShengchanzongzhi()!=null && !villageInfo.getShengchanzongzhi().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getShengchanzongzhi()) < 0) {
					request.setAttribute("shengchanZongzhiError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setShengchanzongzhi(villageInfo.getShengchanzongzhi());
				}

			} catch (NumberFormatException e) {
				request.setAttribute("shengchanZongzhiError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getRenjunshouru()!=null && !villageInfo.getRenjunshouru().equals("")){
			nocChange = false;
			try {
				if (Integer.parseInt(villageInfo.getRenjunshouru()) < 0) {
					request.setAttribute("renjunShouruError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setRenjunshouru(villageInfo.getRenjunshouru());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("renjunShouruError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getNongyechanzhi()!=null && !villageInfo.getNongyechanzhi().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getNongyechanzhi()) < 0) {
					request.setAttribute("nongyeChanzhiError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setNongyechanzhi(villageInfo.getNongyechanzhi());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("nongyeChanzhiError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getCunshouru()!=null && !villageInfo.getCunshouru().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getCunshouru()) < 0) {
					request.setAttribute("cunshouruError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setCunshouru(villageInfo.getCunshouru());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("cunshouruError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getCunzhaiwu()!=null && !villageInfo.getCunzhaiwu().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getCunzhaiwu()) < 0) {
					request.setAttribute("cunzhaiwuError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setCunzhaiwu(villageInfo.getCunzhaiwu());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("cunzhaiwuError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getXiaqumianji()!=null && !villageInfo.getXiaqumianji().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getXiaqumianji()) < 0) {
					request.setAttribute("xiaquMianjiError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setXiaqumianji(villageInfo.getXiaqumianji());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("xiaquMianjiError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getGendimianji()!=null && !villageInfo.getGendimianji().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getGendimianji()) < 0) {
					request.setAttribute("gendiMianjiError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setGendimianji(villageInfo.getGendimianji());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("gendiMianjiError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getCunxiaoxue()!=null && !villageInfo.getCunxiaoxue().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getCunxiaoxue()) < 0) {
					request.setAttribute("cunxiaoxueError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setCunxiaoxue(villageInfo.getCunxiaoxue());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("cunxiaoxueError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getCunzhongxue()!=null && !villageInfo.getCunzhongxue().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getCunzhongxue()) < 0) {
					request.setAttribute("cunzhongxueError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setCunzhongxue(villageInfo.getCunzhongxue());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("cunzhongxueError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getRenkouzongshu()!=null && !villageInfo.getRenkouzongshu().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getRenkouzongshu()) < 0) {
					request.setAttribute("renkouZongshuError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setRenkouzongshu(villageInfo.getRenkouzongshu());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("renkouZongshuError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getChangzhurenkoushu()!=null && !villageInfo.getChangzhurenkoushu().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getChangzhurenkoushu()) < 0) {
					request.setAttribute("changzhuRenkouError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setChangzhurenkoushu(villageInfo.getChangzhurenkoushu());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("changzhuRenkouError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getHujirenkoushu()!=null && !villageInfo.getHujirenkoushu().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getHujirenkoushu()) < 0) {
					request.setAttribute("hujiRenkoushuError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setHujirenkoushu(villageInfo.getHujirenkoushu());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("hujiRenkoushuError", "请输入正确数字");
				flag = false;
			}
		}
		if(villageInfo.getNongyerenkoushu()!=null && !villageInfo.getNongyerenkoushu().equals("")){
			nocChange=false;
			try {
				if (Integer.parseInt(villageInfo.getNongyerenkoushu()) < 0) {
					request.setAttribute("nongyeRenkoushuError", "不能为负数");
					flag = false;
				}else{
					oldVillageInfo.setNongyerenkoushu(villageInfo.getNongyerenkoushu());
				}
			} catch (NumberFormatException e) {
				request.setAttribute("nongyeRenkoushuError", "请输入正确数字");
				flag = false;
			}
		}
		if (villageInfo.getName() != null && !villageInfo.getName().equals("")) {
			nocChange = false;
			if (villageInfo.getName().length() > 10) {
				request.setAttribute("nameError", "请输入10位以内的村庄名称");
				flag = false;
			} else {
				oldVillageInfo.setName(villageInfo.getName());
			}
		}
		if (villageInfo.getSheng() != null && !villageInfo.getSheng().equals("")) {
			nocChange = false;
			if (villageInfo.getSheng().length() > 12) {
				request.setAttribute("shengError", "请输入12位以内的省的名称");
				flag = false;
			} else{
				oldVillageInfo.setSheng(villageInfo.getSheng());
			}
		} 
		if (villageInfo.getShi()!= null && !villageInfo.getShi().equals("")) {
			nocChange = false;
			if (villageInfo.getShi().length() > 12) {
				request.setAttribute("shiError", "请输入12位以内的市的名称");
				flag = false;
			}else{
				oldVillageInfo.setShi(villageInfo.getShi());
			}
		} 
		if (villageInfo.getQu() != null && !villageInfo.getQu().equals("")) {
			nocChange = false;
			if (villageInfo.getQu().length() > 12) {

				request.setAttribute("quError", "请输入12位以内的区的名称");
				flag = false;
			}else{
				oldVillageInfo.setQu(villageInfo.getQu());
			}
		}
		if (villageInfo.getXiang() != null
				&& !villageInfo.getXiang().equals("")) {
			nocChange = false;
			if (villageInfo.getXiang().length() > 12) {
				request.setAttribute("xiangError", "请输入12位以内的县的名称");
				flag = false;
			}else{
				oldVillageInfo.setXiang(villageInfo.getXiang());
			}
		}
		if (nocChange) {
			flag = false;
			request.setAttribute("nochange", "没有修改任何内容");
		}else{
			AccountToVillageInfo accountToVillageInfo=new AccountToVillageInfo();
			accountToVillageInfo.setVillageInfo(oldVillageInfo);
			accountToVillageInfo.setAccount(account);
		}
		return flag;
	}
	
	

	@Override
	public boolean addUser(User user,HttpServletRequest request,String zhiweiId,String oldUserId) {
		boolean flag=true;
		if(user.getAccount().trim().equals("")){
			request.setAttribute("accountError", "请输入9-20位的帐号");
			flag=false;
		}else if(user.getAccount().trim().length()>20 || user.getAccount().trim().length()<9){
			flag=false;
			request.setAttribute("accountError", "请输入9-20位的帐号");
		}else{
			UserExtension u=this.basicInfoMapper.findUserByAccount(user.getAccount());
			if(u!=null){
				flag=false;
				request.setAttribute("accountError", "该帐号已存在");
			}
		}
		if (user.getName() != null) {
			if (!user.getName().trim().equals("")) {
				if (user.getName().trim().length() > 10
						|| user.getName().trim().length() < 2) {
					request.setAttribute("nameError", "请输入2-10个字的姓名");
					flag = false;
				} 
			}
		}
		if(user.getRollId()<3){
			boolean b=true;
			List<Integer> list=this.basicInfoMapper.findAllVillageinfoId();
			for(int i=0;i<list.size();i++){
				if(user.getVillageinfoId()==list.get(i)){
					b=true;
					request.setAttribute("villageinfoError", "");
					break;
				} 
				b=false;
				request.setAttribute("villageinfoError", "该村庄不存在");
				
			}
			if(flag){
				flag=b;
			}
			if(user.getAge().trim().equals("")){
				request.setAttribute("ageError", "请输入出生年份");
				flag=false;
			}else{
				try{
					int age=Integer.parseInt(user.getAge());
					if(age-Integer.parseInt(user.getAge())>0 ){
						request.setAttribute("ageError", "请输入正确的出生年份");
						flag=false;
					}
					if(user.getAge().length()!=4 ){
						request.setAttribute("ageError", "请输入正确的出生年份");
						flag=false;
					}
				}catch (NumberFormatException e) {
					request.setAttribute("ageError", "请输入正确的出生年份");
					flag=false;
				}
			}
			if(user.getSex()==null ){
				request.setAttribute("sexError", "请选择性别");
				flag=false;
			}
		}else{
			user.setAge(null);
			user.setSex(null);
			user.setVillageinfoId(0);
		}
		if (zhiweiId!=null) {
			if (zhiweiId.equals("0")) {
				request.setAttribute("zheweiIdError", "请选择职位");
				flag = false;
			}
		}
		System.out.println(flag);
		if(flag){
			user.setPwd("123456789");
			
			if(user.getRollId()==4){
				user.setName("管理员");
			}
			if(user.getRollId()==3){
				user.setName("乡镇府人员");
			}
			this.basicInfoMapper.addUser(user);
			if(user.getRollId()==2){
				user.setId(this.basicInfoMapper.findUserIdByUserAccount(user.getAccount()));
				this.updateRollIdTo2(user, zhiweiId, oldUserId);
				this.basicInfoMapper.updateRollById(user);
			}
		}
		return flag;
	}
	/**
	 * 当将权限该变为2时，删除原村委会相应的职务的用户在村委会的信息
	 * 将原村委会用户权限改为村民，
	 * @author danchaobing
	 *
	 */
	private void updateRollIdTo2(User user,String zhiweiId,String oldUserId){

		CunWeiHui cunWeiHui=new CunWeiHui();
		cunWeiHui.setStarttime(new Date());
		cunWeiHui.setUserId(user.getId());
		
		Integer jieshu=this.basicInfoMapper.findCunWeiHuiJieShuByVillinfoId(user.getVillageinfoId());
		if(jieshu==null){
			jieshu=1;
		}
		cunWeiHui.setJieshu(jieshu);
		cunWeiHui.setZhiweiId(zhiweiId);
		cunWeiHui.setVillageinfoId(user.getVillageinfoId());
		
		CunWeiHui old=new CunWeiHui();
		old.setJieshu(jieshu);
		old.setZhiweiId(zhiweiId);
		old.setVillageinfoId(user.getVillageinfoId());
		this.basicInfoMapper.deleteCunWeiHuiMember1(old);
		if(!oldUserId.equals("")){
			User u=new User();
			u.setId(Integer.parseInt(oldUserId));
			u.setRollId(1);
			this.basicInfoMapper.updateRollById(u);
		}
		this.basicInfoMapper.addCunWeiHuiMember(cunWeiHui);
	}
	@Override
	public boolean updateRoll(HttpServletRequest request, User user,String oldRollId,String zhiweiId,String oldUserId){
		if (zhiweiId!=null) {
			if (zhiweiId.equals("0")) {
				request.setAttribute("zhiWeiIdError", "请选择职位");
				return false;
			}
		}
		if(Integer.parseInt(oldRollId)>=3){
			request.setAttribute("oldRollIdError", "乡政府或管理员权限无法更改");
			return false;
		}
		if(user.getRollId()==2){
			user.setId(this.basicInfoMapper.findUserIdByUserAccount(user.getAccount()));
			this.updateRollIdTo2(user, zhiweiId, oldUserId);
			return this.basicInfoMapper.updateUserRollByAccount(user);
		}
		if(Integer.parseInt(oldRollId)== 2){
			CunWeiHui cunWeiHui=new CunWeiHui();
			Integer jieshu=this.basicInfoMapper.findCunWeiHuiJieShuByVillinfoId(user.getVillageinfoId());
			if(jieshu==null){
				jieshu=1;
			}
			cunWeiHui.setJieshu(jieshu);
			cunWeiHui.setVillageinfoId(user.getVillageinfoId());
			this.basicInfoMapper.deleteCunWeiHuiMember2(cunWeiHui);
			return this.basicInfoMapper.updateUserRollByAccount(user);
		}
		return this.basicInfoMapper.updateUserRollByAccount(user);
	}
	@Override
	public boolean deleteUserByAccount(String account) {
		//待处理
		//村委会表
		
		//通讯录表
		//email表
		//投票表
		//候选人表
		//用户表
		
		return this.basicInfoMapper.deleteUserByAccount(account);
	}
	
	@Override
	public Integer zhiWeiIsUse(CunWeiHui cunWeiHui){
		return this.basicInfoMapper.zhiWeiIsUse(cunWeiHui);
	}
	@Override
	public List<VillageInfo> findAllVillageinfo(String page) {
		List<VillageInfo> list=this.basicInfoMapper.findAllVillageinfo((Integer.parseInt(page)-1)*6);
		return list;
	}
	@Override
	public Integer findNumberOfVillageInfo() {
		return this.basicInfoMapper.findNumberOfVillageInfo();
	}
	@Override
	public List<UserExtension> findAllUserByVillageInfoId(String id, String page) {
		Page p=new Page();
		p.setBegin((Integer.parseInt(page)-1)*8);
		p.setId(Integer.parseInt(id));
		return this.basicInfoMapper.findAllUserByVillageInfoId(p);
	}
	@Override
	public Integer findCountUserByVillageInfoId(String id) {
		return this.basicInfoMapper.findUserCountByVillageInfoId(Integer.parseInt(id));
	}
	@Override
	public boolean resetPwd(String account) {
		User u=new User();
		u.setAccount(account);
		u.setPwd("123456789");
		return this.basicInfoMapper.resetPwd(u);
	}
}
