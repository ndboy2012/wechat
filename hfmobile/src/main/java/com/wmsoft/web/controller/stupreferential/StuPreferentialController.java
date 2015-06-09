package com.wmsoft.web.controller.stupreferential;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wmsoft.web.entity.anonymous.AnonymousEntity;
import com.wmsoft.web.service.stupreferential.StuPreferentialServiceI;
import com.wmsoft.wechat.constant.WechatConstant;

@Controller("stuPreferentialController")
@RequestMapping("stuPreferentialController")
public class StuPreferentialController {

	//private Logger logger = Logger.getLogger(AnonymousController.class);

	private String message = "";

	@Autowired
	private StuPreferentialServiceI stuPreferentialService;
    
	//一登记好马上就跳转
	@RequestMapping(value = "/stuPreferential.do")
	public ModelAndView anonymous(HttpServletRequest request) {
	    String openId = (String) request.getSession().getAttribute(WechatConstant.register_session_openId);
		if (null == openId) {
			return new ModelAndView("register/error");
		} else {
			try {
				request.getSession().setAttribute(
						WechatConstant.register_session_openId, openId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("/stupreferential/stupreferential");
		}
	}
	//登记好后从微信菜单栏中进行跳转
	@RequestMapping(value = "/stuPreferentialWe.do")  
	public ModelAndView anonymousWe(HttpServletRequest request,String openId) {
		if (null == openId) {
			return new ModelAndView("register/error");
		} else {
			try {
				request.getSession().setAttribute(
						WechatConstant.register_session_openId, openId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("/stupreferential/stupreferential");
		}
	}
	
	/**
	 * 大学生特惠活动注册页面
	 * 1 获取OPENID
	 * 2 没有OPENID的用户不是在微信点击跳转页面过去的,不能注册
	 * 3 判断是否是学生号码
	 * 4 检查是否注册登记过
	 * 5 
	 */
	@RequestMapping(value = "/submit.do")
	@ResponseBody
	public AjaxJson submit(HttpServletRequest request, AnonymousEntity ae) {
		AjaxJson j = new AjaxJson();
		//1
		String openId = (String) request.getSession().getAttribute(WechatConstant.register_session_openId);
		if(null!=openId){
			ae.setOpenId(openId);
		}else{
			//2
			j.setSuccess(false);
			message = "信息没有登记成功，感谢您的使用。请在微信中再次尝试打开。";
			return j;
		}
		try {
			//3
			if(!stuPreferentialService.hasIn(ae.getTelephone())){
				j.setSuccess(false);
				message = "很遗憾！您不是本次特惠活动目标客户，不能参加本活动，请您继续关注合肥移动其他优惠，活动正在不断更新ing。";
			//4
			}else if(!stuPreferentialService.hasApplicated(ae.getOpenId())){
				j.setSuccess(false);
				message = "您的信息已经提交过了，不需要重新提交!";
			}else{
				stuPreferentialService.saveAnonymous(ae);
				j.setSuccess(true);
				message = "提交成功！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "服务器出错了！";
		}
		j.setMsg(message);
		return j;
	}
	
	//跳转到成功页面
	@RequestMapping(value = "success.do")
	public ModelAndView retuSucc(HttpServletRequest request) {
		return new ModelAndView("/stupreferential/success");
	}
		
	//跳转到成功页
	@RequestMapping(value = "fail.do")
	public ModelAndView retuFail(HttpServletRequest request) {
		return new ModelAndView("/stupreferential/fail");
	}
}
