/**
* @Title: EmployeeInfoController.java
* @Package com.hhcq.laborUnion.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author 贺鸿才
* @date 2020年1月17日
* @version V1.0
*/
package com.hhcq.laborUnion.controller;

import org.apache.log4j.Logger;
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
	    public ModelAndView login(@RequestParam(value="id",required=true) String openId) {
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
}
