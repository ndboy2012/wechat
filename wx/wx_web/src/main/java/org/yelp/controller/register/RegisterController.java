package org.yelp.controller.register;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yelp.constant.CommonConstant;
import org.yelp.entity.register.RegisterEntity;
import org.yelp.service.register.RegisterServiceI;
import org.yelp.service.sms.SmsServiceI;
import org.yelp.utils.DateProcessTools;

@Controller
@RequestMapping(value = "register.do")
public class RegisterController {

	@Autowired
	RegisterServiceI registerService;

	@Autowired
	SmsServiceI smsService;

	@RequestMapping(params = "reg")
	public ModelAndView register(RegisterEntity register,
			HttpServletRequest request) {
		if ("".equals(register.getOpenId()) || register.getOpenId() == null) {
			return new ModelAndView("/register/error");
		} else {
			request.getSession().setAttribute(CommonConstant.OPENID_SESSION,
					register.getOpenId());
		}
		return new ModelAndView("/register/register");
	}

	/**
	 * 检查电话号码是否存在
	 * 
	 * @param telephone
	 * @return
	 */
	@RequestMapping(params = "code")
	@ResponseBody
	public AjaxJson checkTelephone(String telephone, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String openId = (String) request.getSession().getAttribute(
				CommonConstant.OPENID_SESSION);
		if (openId == null) { //判断存放在session中的openId是否过期了。
			j.setSuccess(false);
			j.setMsg("请求超时，请重刷新页面！");
			return j;
		} else {
			try {
				if (registerService.checkTelephoneExit(telephone)) {
					j.setMsg("该号码已经登记过了!");
					j.setSuccess(false);
				} else if (registerService.checkOpenIdExit(openId)) {
					j.setMsg("该微信号已经登记过了！");
					j.setSuccess(false);
				} else {
					Map<String, String> rtnMsg = smsService
							.SendRegisterMessage(telephone);
					System.out.println("返回验证码：" + rtnMsg.get("CODE") + "细节："
							+ rtnMsg.get("DETAIL"));
					String code = rtnMsg.get("validateCode").toString();
					request.getSession()
							.setAttribute(CommonConstant.CODE, code);
					j.setMsg("验证码:" + code);
					j.setSuccess(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}
    /**
     * 用户已经获取验证码后进行登记
     * @param register
     * @param validate
     * @param request
     * @return
     */
	@RequestMapping(params = "recorde")
	@ResponseBody
	public AjaxJson recorde(RegisterEntity register, String validate,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "";
		String session_code = (String) request.getSession().getAttribute(
				CommonConstant.CODE);
		String openId = (String) request.getSession().getAttribute(CommonConstant.OPENID_SESSION);
		if (session_code == null) {
			message = "验证码已经过期，请重新获取";
		} else if (!validate.equals(session_code)) {
			message = "验证码错误，请重新输入";
		} else {
			register.setRegisterTime(DateProcessTools.geTimestamp());
			register.setOpenId(openId);
			message = "恭喜你登记成功";
			registerService.saveRegisterEntity(register);
			remove(request);
		}
		j.setMsg(message);
		return j;
	}

	// 60秒过后接收从前台页面发送过来的请求自动删除session
	@RequestMapping(value = "/remove.do")
	public void remove(HttpServletRequest request) {
		if (request.getSession().getAttribute(CommonConstant.CODE) != null) {
			request.getSession().removeAttribute(CommonConstant.CODE);
		}
	}
}
