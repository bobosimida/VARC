/**
 * 
 */
package com.varc.service.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.varc.service.BasicInfo.IBasicInfoService;
import com.varc.service.BasicInfo.impl.BasicSingleton;
import com.varc.service.DemocraticElection.IDemocraticElectionService;
import com.varc.service.DemocraticElection.impl.VoteEnter;
import com.varc.service.DemocraticManage.IDemocraticManageService;
import com.varc.service.DemocraticSupervise.IDemocraticSuperviseService;
import com.varc.service.WorkAndMessage.IWorkAndMessageService;
import com.varc.service.user.IUserService;
import com.varc.service.user.impl.UserServiceImpl;

/**
 * 工厂类，获取Service层的实现类
 * @author danchaobing
 *
 */
public class ServiceFactory {
	private static ApplicationContext context;
	static{
		context=new ClassPathXmlApplicationContext("spring.xml");
	}
	/**
	 * 获取用户层接口
	 * @author danchaobing
	 *
	 */
	public static IUserService getIUserService(){
		return (UserServiceImpl)context.getBean("userServiceImpl");
	}
	/**
	 * 获取基本信息管理业务层接口
	 * @author danchaobing
	 *
	 */
	public static IBasicInfoService getIBasicInfoService(){
		return (IBasicInfoService)context.getBean("basicInfoServiceImpl");
	}
	/**
	 * 获取民主选举业务管理层接口
	 * @author danchaobing
	 *
	 */
	public static IDemocraticElectionService getIDemocraticElectionService(){
		return (IDemocraticElectionService)context.getBean("democraticElectionServiceImpl");
	}
	/**
	 * 获取工作与信息交流业务管理层接口
	 * @author danchaobing
	 *
	 */
	public static IWorkAndMessageService getIWorkAndMessageService(){
		return (IWorkAndMessageService)context.getBean("workAndMessageServiceImpl");
	}
	
	public static VoteEnter getVoteAndUpdatepiaoshu() {
		return (VoteEnter)context.getBean("voteEnter");
	}
	/**
	 * 获取民主管理业务层接口
	 * @author danchaobing
	 *
	 */
	public static IDemocraticManageService getIDemocraticManageService(){
		return (IDemocraticManageService)context.getBean("democraticManageServiceImpl");
	}
	/**
	 * 获取民主监督业务层接口
	 * @author danchaobing
	 *
	 */
	public static IDemocraticSuperviseService getIDemocraticSuperviseService(){
		return (IDemocraticSuperviseService)context.getBean("democraticSuperviseServiceImpl");
	}
	/**
	 * 获取添加用户和村庄的单例类
	 * @author danchaobing
	 *
	 */
	public static BasicSingleton getBasicSingleton(){
		return (BasicSingleton)context.getBean("basicSingleton");
	}
}
