package com.wmsoft.web.controller.register;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wmsoft.common.tools.DateProcess;
import com.wmsoft.common.tools.GZipUtils;
import com.wmsoft.web.constant.MessageConstant;
import com.wmsoft.web.entity.register.RegisterEntity;
import com.wmsoft.web.entity.register.WechatRegHasget;
import com.wmsoft.web.service.register.WechatRegisterServiceI;
import com.wmsoft.web.service.sms.SmsServiceI;
import com.wmsoft.wechat.constant.WechatConstant;


@Controller
@RequestMapping("registerController")
public class RegisterController {

	@Autowired
	private WechatRegisterServiceI wechatRegisterServiceImpl;

	@Autowired
	private SmsServiceI smsServiceImpl;

	private Logger logger = Logger.getLogger(RegisterController.class);

	@RequestMapping(value = "/share.do")
	public ModelAndView share(HttpServletRequest request,String openId) { //普通朋友圈分享入口，从这里进入页面必须验证手机号码
		int count = 0;
		try {
			if(openId==null) {    //从朋友圈中进入
				request.setAttribute("telephone", GZipUtils.gzip_compress(WechatConstant.validate_none));
			} else {  
				RegisterEntity register = wechatRegisterServiceImpl.findRegisterInfoByOpenId(openId);
				if(register==null) {
					return new ModelAndView("register/error");   //非法操作，修改了指定路径
				} else {
					request.setAttribute("telephone", GZipUtils.gzip_compress(register.getTelephone()));  //从自己微信端口进入
				}
			}
			count = wechatRegisterServiceImpl.getRegisterNums();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("regCount", count);
		return new ModelAndView("/register/share");
	}
    
	//登记号后直接进入
	@RequestMapping(value = "/shareWe.do")
	public ModelAndView shareWe(HttpServletRequest request) {
		String openId = (String) request.getSession().getAttribute(
				WechatConstant.register_session_openId);
		int count = 0;
		try {
			if (openId == null) {
				return new ModelAndView("register/error");
			} else {
				RegisterEntity register = wechatRegisterServiceImpl.findRegisterInfoByOpenId(openId);
				request.setAttribute("telephone", GZipUtils.gzip_compress(register.getTelephone()));
			}
			count = wechatRegisterServiceImpl.getRegisterNums();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("regCount", count);
		return new ModelAndView("/register/share");
	}

	@RequestMapping(value = "/reg.do")
	public ModelAndView register(HttpServletRequest request, String openId) {
		if (openId == null) {
			return new ModelAndView("register/error");
		} else {
			try {
				if (!wechatRegisterServiceImpl.hasRegister(openId)) {
					request.getSession().setAttribute(
							WechatConstant.register_session_openId, openId);
				} else {
					request.getSession().setAttribute(
							WechatConstant.register_session_openId,
							WechatConstant.has_register);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("register/register");
		}
	}

	@RequestMapping(value = "/validate.do")
	@ResponseBody
	public AjaxJson validate(String telephone) {    //验证手机号码
		AjaxJson j = new AjaxJson();
		String message = WechatConstant.validate_failed;    //验证失败时候提示
		try {
			if (wechatRegisterServiceImpl.validateRegisterBytel(telephone)) {
				message = WechatConstant.validate_success;   //验证成功时候提供
				j.setObj(GZipUtils.gzip_compress(telephone));
				j.setSuccess(true);
			} else {
				j.setObj(GZipUtils.gzip_compress(WechatConstant.validate_failure));
				j.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "/apply.do")
	@ResponseBody
	public AjaxJson apply(String telephone) {   //进行申请
		AjaxJson j = new AjaxJson();
		String message = "";
		j.setSuccess(false);
		try {
			String validTel = GZipUtils.gzip_decompress(telephone);
			if (WechatConstant.validate_none.equals(validTel)) {
				message = "还没有进行验证，首先进行验证操作";
			} else if (WechatConstant.validate_failure.equals(validTel)) {
				message = "号码验证失败，请重新验证";
			} else {
				if (wechatRegisterServiceImpl.hasGet(validTel)) {
					message = "hasget";
					j.setSuccess(true);
				} else {
					WechatRegHasget get = new WechatRegHasget();
					get.setTelephone(validTel);
					get.setGetTime(DateProcess.geTimestamp());
					wechatRegisterServiceImpl.saveHasGet(get);
					message = "已经有"+wechatRegisterServiceImpl.getRegisterNums()+"人参与了流量申请";
					j.setSuccess(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(message);
		return j;
	}
    
	/*
	 * 请求验证码
	 */
	@RequestMapping(value = "/code.do")
	@ResponseBody
	public AjaxJson code(HttpServletRequest request, String telephone) {
		AjaxJson j = new AjaxJson();
		logger.info("合肥移动手机号为" + telephone + "请求验证码");
		j.setSuccess(false);
		
		String openId = (String) request.getSession().getAttribute(
				WechatConstant.register_session_openId);
		if ((WechatConstant.has_register).equals(openId)) {
			 j.setMsg("您已经登记过了，不需要再次登记");
			 return j;
		}
		try {
			if (wechatRegisterServiceImpl.validateRegisterBytel(telephone)) {
				j.setMsg("这个号码已经登记过了，不需要重新登记");
				return j;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = smsServiceImpl.SendRegisterMessage(telephone);
		String validateCode = map.get("validateCode").toString();
		String statusCode = map.get("statusCode").toString();
		System.out.println(validateCode+"发送状态码为:"+statusCode);
		if (statusCode.equals(MessageConstant.success_code)) {
			j.setSuccess(true);
			request.getSession().setAttribute(
					WechatConstant.validate_session_code, validateCode);
		} else {
			j.setMsg("短信发送失败，请重新获取");
		}
		return j;
	}

	@RequestMapping(value = "/submit.do")
	@ResponseBody
	public AjaxJson submit(HttpServletRequest request, RegisterEntity register,
			String validateCode) {
		AjaxJson j = new AjaxJson();
		String message = "";
		String openId = (String) request.getSession().getAttribute(
				WechatConstant.register_session_openId);
		String code = (String) request.getSession().getAttribute( // 获取session中的code
				WechatConstant.validate_session_code);
		j.setSuccess(false);
		if (code == null) {
			message = "验证码已经过期，请重新获取";
		} else if (!validateCode.equals(code)) {
			message = "验证码不正确！请重新输入";
		} else {
			if ((WechatConstant.has_register).equals(openId)) {
				message = "您已经登记过了，不需要再次登记";
			} else {
				register.setOpenId(openId);
				register.setBindTime(DateProcess.geTimestamp());
				try {
					wechatRegisterServiceImpl.saveRegisterInfo(register);
				} catch (Exception e) {
					e.printStackTrace();
				}
				j.setObj(register.getTelephone());
				//message = WechatConstant.register_success;
				message = "恭喜你登记成功，点击\"确定\"跳转到大学生寒假特惠活动链接";
				j.setSuccess(true);
				remove(request); // 最后登记成功后删除session释放内存
			}
		}
		j.setMsg(message);
		return j;
	}

	// 60秒过后接收从前台页面发送过来的请求自动删除session
	@RequestMapping(value = "/remove.do")
	public void remove(HttpServletRequest request) {
		logger.info("begin remove session");
		if (request.getSession().getAttribute(
				WechatConstant.validate_session_code) != null) {
			request.getSession().removeAttribute(
					WechatConstant.validate_session_code);
		}
		logger.info("remove success");
	}
}
