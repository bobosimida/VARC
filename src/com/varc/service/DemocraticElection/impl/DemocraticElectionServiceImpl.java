/**
 * 
 */
package com.varc.service.DemocraticElection.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.varc.domain.CunWeiHui;
import com.varc.domain.HouXuanRen;
import com.varc.domain.MinZhuXuanJu;
import com.varc.domain.TouPiao;
import com.varc.domain.User;
import com.varc.domain.extension.HouXuanRenExtension;
import com.varc.domain.extension.MinZhuXuanJuExtension;
import com.varc.domain.extension.TouPiaoExtension;
import com.varc.mapper.BasicInfoMapper;
import com.varc.mapper.ElectionMapper;
import com.varc.service.DemocraticElection.IDemocraticElectionService;
import com.varc.service.factory.ServiceFactory;

/**
 * 民主选举业务层实现
 * @author danchaobing
 *
 */
@Component
@Scope("prototype")
public class DemocraticElectionServiceImpl implements IDemocraticElectionService{
	@Resource
	private ElectionMapper electionMapper;
	@Resource BasicInfoMapper basicInfoMapper;
	@Transactional
	@Override
	public boolean createMinZhuXuanJU(MinZhuXuanJu minZhuXuanJu,HttpServletRequest request,String accounts,MultipartFile file,HttpSession session) {
		boolean flag=true;
		int renshu=0;
		int id=this.basicInfoMapper.findVillageInfoByUserAccount(session.getAttribute("account").toString()).getId();
		if(minZhuXuanJu.getVillageinfoId()!=id){
			request.setAttribute("villageinfoIdError", "请输入本村id");
			flag=false;
		}
		String filename = file.getOriginalFilename();
		try{
			renshu=Integer.parseInt(minZhuXuanJu.getCunweihuirenshu());
			if(minZhuXuanJu.getCunweihuirenshu().equals("") || 
					!(renshu<10 && renshu>=5)){
				request.setAttribute("cunWeiHuiRenShuError", "请输入5-9成员");
				flag=false;
				
			}
		}catch (NumberFormatException e) {
			request.setAttribute("cunWeiHuiRenShuError", "请输入5-9成员");
			flag=false;
		}
		String[] account=accounts.split("、");	
		if(account.length>=renshu){
			for(int i=0;i<account.length;i++){
				Integer s=this.electionMapper.findVillageinfoIdByUserAccount(account[i].trim()); //=========
				if(s==null){
					request.setAttribute("accountsError", "请输入正确的参选用户帐号");
					flag=false;
					break;
				}else{
					if(s!=minZhuXuanJu.getVillageinfoId()){
						System.out.println("s====="+s);
						request.setAttribute("accountsError", account[i]+"不是本村帐号,不能参与选举");
						flag=false;
						break;
					}
				}
			}
		}else{
			request.setAttribute("accountsError", "候选人数少于与下届村委会预计人数，请重新输入");
			flag=false;
		}
		Integer villageId=electionMapper.findVillageIdByVillageId(minZhuXuanJu.getVillageinfoId());
		if(villageId!=minZhuXuanJu.getVillageinfoId()){
			request.setAttribute("villageinfoIdError", "请输入正确村庄编号");
			flag=false;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date xuanjuri;
			xuanjuri = sdf.parse( minZhuXuanJu.getXuanjuri());
			Date now = new Date();
			Date rightDate = new Date(now.getTime() + 1*24*60*60*1000);
			boolean temp = xuanjuri.after(rightDate);
			if(!temp) {
				flag = false;
				request.setAttribute("xuanjuriError", "请输入正确的选举时间");
			} 
		} catch (ParseException e1) {
			flag = false;
			request.setAttribute("xuanjuriError", "请输入正确的选举时间");
		}
				
		InputStream in;
		try {
			in = file.getInputStream();
			byte[] b=new byte[in.available()];
			in.read(b);
			minZhuXuanJu.setShishifangan(b);
		} catch (IOException e) {
			request.setAttribute("fileError", "文件错误");
			flag=false;
		}
		String fileName=file.getOriginalFilename();
		if(fileName.endsWith(".txt") || fileName.endsWith(".doc") || fileName.endsWith(".docx") ||fileName.endsWith(".xls") ||
		   fileName.endsWith(".ppt") ||fileName.endsWith(".pptx") ||fileName.endsWith(".xlsx")){}else{
					request.setAttribute("fileError", "文件格式错误");
					flag=false;
		}
		if(flag){
			minZhuXuanJu.setTianbaoniandu(new Date());
			minZhuXuanJu.setShenqingzhuangtai("0");
			minZhuXuanJu.setXuanjujindu("0");
			minZhuXuanJu.setFilename(filename);
			boolean inserted = electionMapper.insertMinZhuXuanJu(minZhuXuanJu);
			
			if(inserted) {
				int maxId = electionMapper.findMaxMinZhuXuanJuId();
				for(int i=0;i<account.length;i++){
					User user = this.electionMapper.findUserByUserAccount(account[i].trim());
					if(user != null) {
						int userId = user.getId();
						if(userId != 0) {
							HouXuanRen houXuanRen = new HouXuanRen();
							houXuanRen.setUserId(userId);
							houXuanRen.setMinzhuxuanjuId(maxId);
							houXuanRen.setPiaoshu(0);
							flag = this.electionMapper.addHouXuanRenByUserId(houXuanRen);
						}
					}
				}
			} else {
				request.setAttribute("saveError", "保存失败");
			}
		}
		return flag;
	}
	@Override
	public List<MinZhuXuanJuExtension> findMinZhuXuanJuExtension() {
		return electionMapper.findMinZhuXuanJu();
	}
	
	@Transactional
	@Override
	public boolean updateMinZhuXuanJuState(MinZhuXuanJu minZhuXuanJu) {
		boolean flag = false;
		//更新民主选举表申请状态
		flag = electionMapper.updateMinZhuXuanJuState(minZhuXuanJu);
		//如果不同意民主选举申请，删除候选人表
		if("2".trim().equals(minZhuXuanJu.getShenqingzhuangtai())) {
			boolean deleted = electionMapper.deleteHouXuanRenByMinZhuXuanJuId(minZhuXuanJu.getId());
			flag = deleted;
		}
		//如果同意民主选举结束，更改村委会表，删除投票人
		//更改村委会表——1.候选人表按票数排序，票数越高担任职位越高；2.往村委会表插入新任村委会人员信息；
//		3.更改用户表rollId，新任rollId由1改为2，前任村委会成员rollId由2改为1
//		4.删除投票人
		if("4".trim().equals(minZhuXuanJu.getShenqingzhuangtai())) {
			List<HouXuanRen> houXuanRens = electionMapper.findHouXuanRenByMinZhuXuanJuIdDESCPiaoShu(
					minZhuXuanJu.getId());
			if(houXuanRens != null) {
				//更新新一届村委会名单
				MinZhuXuanJu minZhuXuanJu2 = electionMapper.findMinZhuXuanJuById(minZhuXuanJu.getId());
				boolean updated = updateCunWeiHUi(houXuanRens,minZhuXuanJu2);
				flag = updated;
			} else {
				flag = false;
			}
			
			//完成更新操作之后，删除投票表
			boolean deleted = electionMapper.deleteTouPiaoBiaoByMinZhuXuanJuId(minZhuXuanJu.getId());
			flag = deleted;
		}
		//如果不同意民主选举结束申请，删除投票人
		if("5".trim().equals(minZhuXuanJu.getShenqingzhuangtai())) {
			boolean deleted = electionMapper.updateMinZhuXunaJuZhuangTaiToOneById(minZhuXuanJu.getId());
			flag = deleted;
		}
		return flag;
	}
	@Transactional 
	private boolean updateCunWeiHUi(List<HouXuanRen> houXuanRens, MinZhuXuanJu minZhuXuanJu2) {
		//从候选人表中取出数据，插入到村委会表中
		boolean flag = true;
		//通过村庄ID拿到该村最大村委会届数+1
		MinZhuXuanJu minZhuXuanJu = minZhuXuanJu2;
		int maxJieShu = findMaxJieShuFromCunWeiHuiByVillageinfoId(minZhuXuanJu.getVillageinfoId());
//		int maxZhiWeiId = electionMapper.findMaxzhiweiIdfromCunWeiHuiByVillageinfoId(minZhuXuanJu.getVillageinfoId());
		Date startTime = new Date();
		Date overtime = DateUtils.add(startTime, 1, 4); 
		for(int i = 0; i <Integer.parseInt( minZhuXuanJu.getCunweihuirenshu()); i++) {
			CunWeiHui cunWeiHui = new CunWeiHui();
			cunWeiHui.setJieshu(maxJieShu + 1);
			cunWeiHui.setZhiweiId(String.valueOf(i + 1));
			cunWeiHui.setStarttime(startTime);
			cunWeiHui.setOvertime(overtime);
			cunWeiHui.setUserId(houXuanRens.get(i).getUserId());
			cunWeiHui.setVillageinfoId(minZhuXuanJu.getVillageinfoId()); 
			cunWeiHui.setMinzhuxuanjuId(houXuanRens.get(i).getMinzhuxuanjuId());
			
			boolean insertedCunWeiHui = electionMapper.insertCunWeiHui(cunWeiHui);
			flag = insertedCunWeiHui;
		}
		//上一届的村委会成员rollId 由2改为1
		//查找上一届村委会成员，
		CunWeiHui cunWeiHui = new CunWeiHui();
		cunWeiHui.setJieshu(maxJieShu);
		cunWeiHui.setVillageinfoId(minZhuXuanJu.getVillageinfoId());
		List<Integer> users = electionMapper.findLastCunWeiHuiMembersByjieshuAndvillageinfoId(cunWeiHui);
		//通过userId找到对应的user
		boolean updatedUser = false;
		for(int i = 0; i < users.size(); i++) {
			User user = basicInfoMapper.findUserByUserId(users.get(i));
			/*if(user.getRollId() == 1) {
				user.setRollId(2);
			} else if(user.getRollId() ==2) {
				user.setRollId(1);
			}*/
			user.setRollId(1);
			updatedUser = electionMapper.updateUserRollByUserId(user);
			flag = updatedUser;
		}
		//新任村委会成员rollId由1改为2
		boolean updatedUser2 = false;
		for(int i = 0; i < Integer.parseInt(minZhuXuanJu.getCunweihuirenshu()); i++) {
			HouXuanRen houXuanRen = houXuanRens.get(i);
			User user = new User();
			user.setId(houXuanRen.getUserId());
			user.setRollId(2);
			updatedUser2 = electionMapper.updateUserRollByUserId(user);
			flag = updatedUser2;
		}
		return flag;
	}
	@Override
	public boolean findNotFinishApply(HttpServletRequest request,
			MinZhuXuanJu minZhuXuanJu) {
		boolean flag = false;
		if(minZhuXuanJu == null) {
			flag = false;
			request.setAttribute("errMsg", "村庄编号不能为空");
		} else {
			//在拦截器中有进行村庄编码不能为空的判断，所以这里不用进行非空判断
			flag = true;
		}
		if(flag) {
			MinZhuXuanJu minZhuXuanJu2 = electionMapper.finNotFinishApply(minZhuXuanJu.getVillageinfoId());
			if(minZhuXuanJu2 != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	@Override
	public String findVillageNameById(int id, HttpServletRequest request) {
		if(id == 0) {
			request.setAttribute("errMsg", "无效的村庄ID");
			return null;
		} else {
			return electionMapper.findVillageNameById(id);
		}
	}
	@Override
	public MinZhuXuanJu findLastMinZhuXuanJuByVillageinfoId(int id,
			HttpServletRequest request) {
		if(id == 0) {
			request.setAttribute("errMsg", "无效的村庄ID");
			return null;
		} else {
			return electionMapper.findLastMinZhuXuanJuByVillageinfoId(id);
		}
	}
	@Override
	public int findMaxJieShuFromCunWeiHuiByVillageinfoId(int villageinfoId) {
		if(villageinfoId != 0)  {
			return electionMapper.findMaxJieShuFromCunWeiHuiByVillageinfoId(villageinfoId);
		} else {
			return 0;
		}
		
	}
	@Override
	public MinZhuXuanJu findMinZhuXuanJuById(int id, HttpServletRequest request) {
		if(id != 0) {
			return electionMapper.findMinZhuXuanJuById(id);
		} else {
			return null;
		}
	}
	@Override
	public boolean deleteHouXuanRenByMinZhuXuanJuId(int id) {
		return electionMapper.deleteHouXuanRenByMinZhuXuanJuId(id);
	}
	@Override
	public List<HouXuanRen> findHouXuanRensByMinZhuXuanJuId(int id,
			HttpServletRequest request) {
		if(id != 0) {
			return electionMapper.findHouXuanRensByMinZhuXuanJuId(id);
		} else {
			return null;
		}
	}
	@Override
	public List<HouXuanRenExtension> findHouXuanRenExtensionsByMinZhuXuanJuId(
			int id, HttpServletRequest request) {
		if(id != 0) {
			return electionMapper.findHouXuanRenExtensionsByMinZhuXuanJuId(id);
		} else {
			return null;
		}
	}
	@Override
	public TouPiaoExtension findTouPiaoExtensionByuserIdAndminzhuxuanjuId(TouPiao touPiao) {
		if(touPiao != null) {
			return electionMapper.findTouPiaoExtensionByuserIdAndminzhuxuanjuId(touPiao);
		} else {
			return null;
		}
		
	}
	@Override
	public HouXuanRen findHouXuanRenByuserIdAndminzhuxuanjuId(
			HouXuanRen houXuanRen) {
		if(houXuanRen != null) {
			return electionMapper.findHouXuanRenByuserIdAndminzhuxuanjuId(houXuanRen);
		} else {
			return null;
		}
	}
	@Override
	public boolean updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId(
			HouXuanRen houXuanRen) {
		if(houXuanRen != null) {
			return electionMapper.updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId(houXuanRen);
		} else {
			return false;
		}
	}
	@Override
	public boolean insertTouPiao(TouPiao touPiao) {
		if(touPiao != null) {
			return electionMapper.insertTouPiao(touPiao);
		} else {
			return false;
		}
	}
	@Transactional
	@Override
	public boolean vote(HouXuanRen houXuanRen, User user) {
		TouPiao touPiao = new TouPiao();
		touPiao.setUserId(user.getId());
		touPiao.setBeUserId(houXuanRen.getUserId());
		touPiao.setMinzhuxuanjuId(houXuanRen.getMinzhuxuanjuId());
		boolean inserted = false;
		boolean updated = false;
		if(houXuanRen != null && user != null) {
			//投票表加入投票者信息
			inserted = electionMapper.insertTouPiao(touPiao);
			//操作候选人表
			//拿到当前候选人信息
			/*HouXuanRen houXuanRen2 = electionMapper.findHouXuanRenByuserIdAndminzhuxuanjuId(houXuanRen);
			//候选人表被投候选人票数加一
			houXuanRen2.setPiaoshu(houXuanRen2.getPiaoshu() + 1);
			updated = electionMapper.updateHouXuanRenPiaoShuByuserIdAndminzhuxuanjuId(houXuanRen2);*/
			
			updated = ServiceFactory.getVoteAndUpdatepiaoshu().VoteAndUpdatepiaoshu(houXuanRen);
			
			if(inserted && updated) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	@Override
	public User findUserByAccount(String account) {
		System.out.println(account);
		return electionMapper.findUserByAccount(account);
	}
	
}
