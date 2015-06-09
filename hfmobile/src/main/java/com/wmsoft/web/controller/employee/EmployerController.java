package com.wmsoft.web.controller.employee;

import java.util.Date;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wmsoft.web.entity.employee.EmployeeEntity;
import com.wmsoft.web.service.employee.EmployeeServiceI;


@Controller("employerController")
@RequestMapping("employerController")
public class EmployerController {
      
	  private Logger logger = Logger.getLogger(EmployerController.class);
	  
	  @Autowired
      private EmployeeServiceI employeeServiceImpl;
	    
	  
	  private String message = "";
	  @RequestMapping(value="/employer.do")
	  public ModelAndView employer(EmployeeEntity employee) {
		  return new ModelAndView("/employee/employee");
	  }
	  
	  @RequestMapping(value="/skip.do")
	  public ModelAndView skip() {
		  return new ModelAndView("/employee/hefeiyidong");
	  }
	  
	  @RequestMapping(value="/submit.do")
	  @ResponseBody
	  public AjaxJson submit(EmployeeEntity employee) {
		  AjaxJson j = new AjaxJson();
	  try {
		 if(!employeeServiceImpl.hasApplicated(employee.getTelephone())) {
			employee.setApplicateDate(new Date());
			employeeServiceImpl.saveEmployee(employee);
			message = "提交信息成功！感谢您参与合肥移动“千名学子见习计划”！点击“确定”跳转到合肥移动校园活动专区";
			j.setSuccess(true);
		  } else {
			  j.setSuccess(false);
			  message = "您的信息已经提交过了，不需要重新提交!";
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(message);
		  return j;
	  }
}
