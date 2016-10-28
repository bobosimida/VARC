package com.varc.service.WorkAndMessage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.varc.domain.Email;
import com.varc.domain.TongXunLu;
import com.varc.domain.extension.EmailExtension;
import com.varc.domain.extension.EmailPage;
import com.varc.domain.extension.TongxunluPage;
import com.varc.domain.extension.UserExtension;
import com.varc.mapper.BasicInfoMapper;
import com.varc.mapper.WorkAndMessageMapper;
import com.varc.service.WorkAndMessage.IWorkAndMessageService;

/**
 * 工作与信息交流业务层实现
 * @author danchaobing
 *
 */
@Controller
@Scope("prototype")
public class WorkAndMessageServiceImpl implements IWorkAndMessageService{
	@Resource
	private BasicInfoMapper basicInfoMapper;
	@Resource
	private WorkAndMessageMapper workAndMessageMapper;
	@Override
	@Transactional
	public boolean sendEmail(HttpServletRequest request,Email email,String sendAccount,String receiveAccount) {
		boolean flag=true;
		if(email.getTitle().length()>30){
			flag=false;
			request.setAttribute("titleError", "请输入30字以内的标题");
		}
		if(email.getTitle().equals("")){
			flag=false;
			request.setAttribute("titleError", "标题不能为空");
		}
		if(email.getContent().length>16*1024*1024){
			flag=false;
			request.setAttribute("contentError", "输入内容过长，请重新输入");
		}
		if(receiveAccount.length()>20 || receiveAccount.length()<9 || receiveAccount.equals("")){
			flag=false;
			request.setAttribute("receiveAccountError", "收件人帐号输入错误");
		}
		if(flag){
			Integer receiveId=this.basicInfoMapper.findUserIdByUserAccount(receiveAccount);
			if(receiveId==null){
				flag=false;
				request.setAttribute("receiveAccountError", "帐号输入错误");
			}
		}
		if(flag){
			email.setSendTime(new Date());
			Integer sendId=this.basicInfoMapper.findUserIdByUserAccount(sendAccount);
			email.setSendId(sendId);
			Integer getId=this.basicInfoMapper.findUserIdByUserAccount(receiveAccount);
			email.setGetId(getId);
			email.setIsread("0");
			this.workAndMessageMapper.addEmail(email);
		}
		
		return flag;
	}
	@Override
	public int getEmailNoReadCount(HttpServletRequest request, String account) {
		int count=this.workAndMessageMapper.getEmailNoreadCountByUserId(this.basicInfoMapper.findUserIdByUserAccount(account));
		return count;
	}
	@Override
	public List<EmailExtension> getEmailNoReadList(String account,String page) {
		EmailPage emailPage=new EmailPage();
		emailPage.setId(this.basicInfoMapper.findUserIdByUserAccount(account));
		emailPage.setBegin((Integer.parseInt(page)-1)*8);
		return this.workAndMessageMapper.getEmailNoReadListByGetId(emailPage);
	}
	@Override
	public List<EmailExtension> getEmailListByGetId(String account,String page) {
		EmailPage emailPage=new EmailPage();
		emailPage.setId(this.basicInfoMapper.findUserIdByUserAccount(account));
		emailPage.setBegin((Integer.parseInt(page)-1)*8);
		return this.workAndMessageMapper.getEmailListByGetId(emailPage);
	}
	@Override
	public byte[] getEmailContentById(String id) {
		Email email=this.workAndMessageMapper.findEmailContentById(Integer.parseInt(id));
		if(email.getIsread().equals("0")){
			this.workAndMessageMapper.updateIsRead(Integer.parseInt(id));
		}
		return email.getContent();
	}
	@Override
	public int getAllEmailCountByGetId(String account) {
		return this.workAndMessageMapper.getAllEmailCount(this.basicInfoMapper.findUserIdByUserAccount(account));
	}
	/**
	 * id为邮件id
	 */
	@Override
	@Transactional
	public boolean  deleteAEmail(String id){
		return this.workAndMessageMapper.deleteAEmail(Integer.parseInt(id));
	}
	/**
	 * 通过自己的id查找联系人
	 * @author danchaobing
	 *
	 */
	@Override
	public List<UserExtension> findTongxunluByAccount(String account ,String page){
		TongxunluPage tongxunluPage=new TongxunluPage();
		tongxunluPage.setId(this.basicInfoMapper.findUserIdByUserAccount(account));
		tongxunluPage.setBegin((Integer.parseInt(page)-1)*7);
		List<Integer> otherId=this.workAndMessageMapper.findTongxunluBySelfId(tongxunluPage);
		List<UserExtension> users=new ArrayList<UserExtension>();
		for(int i=0;i<otherId.size();i++){
				users.add(this.workAndMessageMapper.findUserEntensionById(otherId.get(i)));
		}
		return users;
	}
	@Override
	public Integer findTongxunluCountBySelfId(String account){
		int id=this.basicInfoMapper.findUserIdByUserAccount(account);
		return this.workAndMessageMapper.findTongxunluCountBySelfId(id);
	}
	@Override
	public boolean deleteTongxunlu(String otherId){
		return this.workAndMessageMapper.deleteTongxunlu(Integer.parseInt(otherId));
	}
	@Override
	public Integer findUserByAccount(String account){
		return this.basicInfoMapper.findUserIdByUserAccount(account);
	}
	@Override
	public boolean addTongxunlu(String selfAccount,String otherId,HttpServletRequest request){
		boolean flag=true;
		TongXunLu tongXunLu=new TongXunLu();
		int id=this.basicInfoMapper.findUserIdByUserAccount(selfAccount);
		if(id==Integer.parseInt(otherId)){
			flag=false;
			request.setAttribute("otherIdError", "不能添加自己为联系人");
		}
		if(this.basicInfoMapper.findRollById(Integer.parseInt(otherId))==4){
			flag=false;
			request.setAttribute("otherIdError", "不能添加管理员为联系人");
		}
		if(this.workAndMessageMapper.findTongxunluCountBySelfId(id)>=1000){
			flag=false;
			request.setAttribute("otherIdNumError", "您的好友数量已达上限");
		}
		if(flag){
			tongXunLu.setSelfId(id);
			tongXunLu.setOtherId(Integer.parseInt(otherId));
			this.workAndMessageMapper.addTongxunlu(tongXunLu);
		}
		return flag;
	}

	@Override
	public List<UserExtension> findAllTongxunluByAccount(String account) {
		List<Integer> list=this.workAndMessageMapper.findAllTongxunluBySelfId(this.basicInfoMapper.findUserIdByUserAccount(account));
		List<UserExtension> users=new ArrayList<UserExtension>();
		for(int i=0;i<list.size();i++){
			users.add(this.workAndMessageMapper.findUserEntensionById(list.get(i)));
		}
		return users;
	}
}
