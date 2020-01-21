/**
* @Title: EmployeeInfoController.java
* @Package com.hhcq.laborUnion.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author 贺鸿才
* @date 2020年1月17日
* @version V1.0
*/
package com.hhcq.laborUnion.controller;

import java.util.Map;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hhcq.laborUnion.domain.EmployeeInfo;
import com.hhcq.laborUnion.service.EmployeeInfoService;

/**
* @ClassName: EmployeeInfoController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 贺鸿才
* @date 2020年1月17日
*
*/
@Controller
public class EmployeeInfoController {
	@Autowired
	private EmployeeInfoService employeeInfoService;
	private static Logger logger = Logger.getLogger(EmployeeInfoController.class);  
	 
	    @RequestMapping(value = "/employeeInfo", method = RequestMethod.GET)
	    public ModelAndView employeeInfo(@RequestParam(value="id",required=true) String openId) {
	    	logger.info("sdfds");
	        EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByOpenId(openId);
	        ModelAndView mav = new ModelAndView();
	        if (employeeInfo == null) {
	        	//跳转至失败页
	        	mav.setViewName("fail");
	            return mav;
	        } else {
	        	//跳转至成功页
	        	mav.addObject("id", employeeInfo.getId());
	        	mav.addObject("name", employeeInfo.getUserName());
	        	mav.addObject("gender", employeeInfo.getGender());
	        	mav.addObject("birthday", employeeInfo.getBirthday());
	            mav.setViewName("success");
	            return mav;
	        }
	    }
	    
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(@RequestParam(value="id",required=false) String openId,Map<String, Object> model) {
	    	logger.info("sdfds");
	    	model.put("msg", "工会系统，请输入账号密码");
	        return "login";
	        
	    }
	    
	    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
	    public String loginIn(@RequestParam(value="userName",required=true) String userName,@RequestParam(value="password",required=true) String password, Map<String, Object> model) {
	    	logger.info("sdfds");
	        logger.info(userName+password);
	        Subject subject = SecurityUtils.getSubject();
	        
	        EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByUsernameAndPassword(userName,password);

	        logger.info(ReflectionToStringBuilder.toString(employeeInfo));

	        UsernamePasswordToken token = new UsernamePasswordToken(employeeInfo.getUserName(),
	        		employeeInfo.getPassword());
	        try {
	            subject.login(token);
		        model.put("id", employeeInfo.getId());
		        model.put("name", employeeInfo.getUserName());
		        model.put("gender", employeeInfo.getGender());
		        model.put("birthday", employeeInfo.getBirthday());
	        } catch (AuthenticationException e) {
	            return e.getMessage();
	        }
	        if(subject.hasRole("admin")){
	        	 return "success";
	        }
	        
	        return "success"; 
	       
	    }
}
