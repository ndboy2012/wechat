package com.weimeng.controller.web.recommend;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimeng.dao.WechatTelRecommDao;
import com.weimeng.entity.web.recommend.WechatCallsCumulative;
import com.weimeng.entity.web.recommend.WechatRecommCustomer;
import com.weimeng.util.AjaxJson;

/*
 * 微信登记号码推荐功能实现
 */
@Controller("wechatTelRecommController")
@RequestMapping("web/recommend/wechatTelRecommController") 
public class WechatTelRecommController {
     
	private Logger logger = Logger.getLogger(WechatTelRecommController.class);
	
	@Resource(name = "wechatTelRecommImpl")
	private WechatTelRecommDao wechatTelRecommImpl;
	
	/*
	 * 用户一打开register页面判断是否已经登记过了
	 */
	@RequestMapping(value = "/isExitOpenid.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson isExitOpenid(String open_id) {
		logger.info("open__id" + "******" + open_id);
		AjaxJson ajax = new AjaxJson();
		String msg = null;
		try {
			if (wechatTelRecommImpl.isExitOpenidInBase(open_id)) {
				msg = "exit";
				logger.info("用户已经登记过了");
			} else {
				logger.info("用户还没有登记");
				msg = "unexit";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajax.setMsg(msg);
		return ajax;
	}

	/*
	 * 号码验证
	 */
	@RequestMapping(value = "/isExitTel.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson isExitTel(String telephone, HttpServletRequest request) {
		logger.info("验证手机号码*****" + telephone);
		AjaxJson ajax = new AjaxJson();
		String msg = null;
		try {
			if (wechatTelRecommImpl.isExitTelBase(telephone)) {
				logger.info("手机号码已经登记");
				/*
				 * 首先判断是不是重复推荐了 如果不是就添加
				 */
				String open_id = wechatTelRecommImpl.getOpenid(telephone);
				if (!wechatTelRecommImpl.isRepeateRecomm(open_id, telephone)) {
					
					WechatRecommCustomer wechatReco = new WechatRecommCustomer(); // 自己推荐自己
					wechatReco.setOpenId(open_id); // 推荐人的open_id
					wechatReco.setRecommendTel(telephone); // 被推荐人的电话号码
					wechatReco.setRecommendDate(new Date()); // 推荐日期
					wechatTelRecommImpl.saveObject(wechatReco);

					if (!wechatTelRecommImpl.isExitTelCalls(telephone)) { // 如果在号码累积表中不存在记录的话
						WechatCallsCumulative wechatCall = new WechatCallsCumulative();
						wechatCall.setOpenId(open_id);
						wechatCall.setTelephone(telephone);
						wechatCall.setAmmounts(1);
						wechatTelRecommImpl.saveObject(wechatCall);
					} else {
						wechatTelRecommImpl.updateAmmountByTel(telephone);
					}
					msg = "恭喜您获得1元话费，推荐朋友获更多话费！";
				} else {
					msg = "验证成功";
				}
				request.getSession().setAttribute("telephone", telephone);
				request.getSession().setAttribute("validated", "ok");

			} else {
				request.getSession().setAttribute("validated", "error");
				logger.info("手机号码尚未登记");
				msg = "亲!您还没有登记,请先去安庆移动微信登记";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajax.setMsg(msg);
		return ajax;
	}

	@RequestMapping(value = "recommTelephone.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson recommTelephone(String telephone, String recommendtel,HttpServletRequest request) {
		AjaxJson ajax = new AjaxJson();
		String msg = null;
		String result = (String) request.getSession().getAttribute("validated");
		if (result != null && result.equals("ok")) { // 号码已经验证过了
			try {
				if (wechatTelRecommImpl.isExitTelBase(recommendtel)) { // 被推荐的号码是否已经登记过了马鞍山移动微信

					String recommOpenid = wechatTelRecommImpl.getOpenid(recommendtel); // 被推荐人的openid
					String openid = wechatTelRecommImpl.getOpenid(telephone); // 推荐人的openid
				
					if (wechatTelRecommImpl.getAmmountByOpenId(recommOpenid) < 100) { // 被推荐用户是否达到推荐上限
                        
						if(!wechatTelRecommImpl.isRepeateRecomm(recommOpenid, telephone)) {
						  if (wechatTelRecommImpl.isFirstRecommend(openid)) { // 如果是第一次推荐就为他增加1元话费
							      wechatTelRecommImpl.updateAmmountByOpenId(openid);
							if (!wechatTelRecommImpl.isExitOpenidCalls(recommOpenid)) { // 如果被推荐的好友是首次被推荐的话
								WechatCallsCumulative wechatcalls = new WechatCallsCumulative();
								wechatcalls.setOpenId(recommOpenid);
								wechatcalls.setTelephone(recommendtel);
								wechatcalls.setAmmounts(1);
								wechatTelRecommImpl.saveObject(wechatcalls);
							} else {
								wechatTelRecommImpl.updateAmmountByOpenId(recommOpenid);
							}
							// 更新推荐表加入推荐记录
							WechatRecommCustomer wechatre = new WechatRecommCustomer();
							wechatre.setOpenId(openid);
							wechatre.setRecommendTel(recommendtel);
							wechatre.setRecommendDate(new Date());
							wechatTelRecommImpl.saveObject(wechatre);
							msg = "success";
						} else {
							msg = "亲!每个人只限一次推荐机会.";
						}
					   } else {
						   msg = "亲!您不能自己推荐自己";
					  }
					} else {
						msg = "亲!您的好友已经达到被推荐上限了";
					}
				} else {
					msg = "亲!推荐您的好友暂未在安庆移动微信登记，请通知其先登记！";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			msg = "亲!您还没有进行号码验证。";
		}
		ajax.setMsg(msg);
		return ajax;
	}

	@RequestMapping(value = "/switchPage.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson switchPage(HttpServletRequest request) {
		AjaxJson ajax = new AjaxJson();
		String msg = null;
		String result = (String) request.getSession().getAttribute("validated");
		logger.info(result);
		if (result != null && result.equals("ok")) {
			msg = "right";
		} else {
			msg = "error";
		}
		ajax.setMsg(msg);
		return ajax;
	}

	@RequestMapping(value = "/listAllrecommendInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listAllrecommendInfo(String telephone) {
		logger.info(telephone);
		AjaxJson ajax = new AjaxJson();
		int callsfree = 0;               //话费数量
		int recommNum = 0;               //被推荐数量
		List list = null;
		if (!"null".equals(telephone)) {
			try {
				list = wechatTelRecommImpl.getAllRecommInfoByTel(telephone);
				callsfree = wechatTelRecommImpl.getAmmountByTel(telephone);
				recommNum = wechatTelRecommImpl.getRecommedNum(telephone);		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String msg = "您总共获取" + callsfree + "元话费，您被" +(recommNum-1)
				+ "个好友所推荐";
		ajax.setMsg(msg);
		ajax.setObj(list);
		return ajax;
	}
	
	
	
}
