package org.yelp.controller.sign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yelp.entity.signInggk.TeleAndPrizes;
import org.yelp.entity.signInggk.WechatSignInfo;
import org.yelp.entity.signInggk.WechatWinPrizes;
import org.yelp.service.register.RegisterServiceI;
import org.yelp.service.sign.SignServiceI;
import org.yelp.utils.DateProcessTools;
import org.yelp.utils.Sweepstakes;
import org.yelp.utils.XmlPraseUtil;


@Controller("signController")
@RequestMapping("signController")
public class SignController {

	private Logger logger = Logger.getLogger(SignController.class);
    private boolean exit = false; // 判定用户是否有签到记录，默认是没有的
	
	@Autowired
	private SignServiceI wechatSignImpl;
   
	@Autowired
	private RegisterServiceI weiChatRegisterImpl;
	
	@RequestMapping(value="skip.do")
	public ModelAndView skip(String openId,HttpServletRequest request) {
		request.getSession().setAttribute("openId", openId);
		return new ModelAndView("/signInggk/ggk");
	}
	
	/**
	 * 验证用户是否已经登记过了
	 * @param open_id
	 * @return
	 */
	@RequestMapping(value = "/flashLoginVerify.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson flashLoginVerify(
			@RequestParam(required = true, defaultValue = "abc") String open_id) {
		AjaxJson ajax = new AjaxJson();
		logger.info("查看用户是否登记过了"); 
		/**
		 * 判断用户是否绑定过了
		 * */
		String msg = null;
		try {
			if (weiChatRegisterImpl.checkOpenIdExit(open_id)) {
				msg = "true";
				logger.info("已经登记好了");
			} else {
				msg = "false";
				logger.info("还没有登记");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajax.setMsg(msg);
		return ajax;
	} 
	
	/*
	 * 查看用户的签到情况，在网页一开启的时候就进行检测，通过接受open_id来获取签到数次和抽奖次数
	 * 1.判断用户是否已经登记过了,如果没有登记则直接签到次数和抽奖次数都返回0 2.查询签到表，取出签到信息情况 3.返回签到签到次数和抽奖次数
	 */
	@RequestMapping(value = "/registerInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson registerInfo(
			@RequestParam(required = true, defaultValue = "abc") String open_id,
			HttpServletRequest request) {
		AjaxJson ajax = new AjaxJson();
		boolean exitregisterinfo = false;
		try {
			exitregisterinfo = weiChatRegisterImpl.checkOpenIdExit(open_id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String prizeLevel = null;
		logger.info(open_id + "*****************registerInfo.do");

		if (exitregisterinfo) { // 如果用户登记过了
			logger.info("用户已经登记过了");
			try {
				if (wechatSignImpl.isExitOpenId(open_id)) {
					logger.info("用户信息在登记表中存在，也在签到表中存在");
					exit = true;
				} else {
					logger.info("用户第一次签到");
					WechatSignInfo signinfo = new WechatSignInfo();
					signinfo.setLastSignDate(DateProcessTools.convertString2date("1970-01-01"));
					signinfo.setMonthSignAccounts(0);
					signinfo.setOpenId(open_id);
					try {
						wechatSignImpl.saveWeiChatSign(signinfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					exit = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			int dayCount = 0;
			
			try {
				WechatSignInfo signinfo = wechatSignImpl.getSignInfo(open_id);
				if (DateProcessTools.compareDate(new Date(),signinfo.getLastSignDate())) {
					// 没有签到机会了
					logger.info("用户今天已经签到过了");
					dayCount = 0;
					prizeLevel = "-1";
				} else {
					dayCount = 1;
					/*
					 * 获取奖品等级
					 */
					int catchPrize = 0;
					int remainCount = 0; // 获取奖品剩余数量
					boolean step = true;
					while (remainCount == 0 && step) {
						catchPrize = Sweepstakes.getLottyNum();
						logger.info(catchPrize);
						if (catchPrize == 7) {
							step = false;
						} else if(catchPrize==1||catchPrize==2||catchPrize==3) {      //如果客户已经抽到了1等或者2等奖
							 if(wechatSignImpl.alreadyGetPrize(open_id, catchPrize)) {  //判断用户以前是否抽到过了1等或者2等奖，如果抽到过了remainCount = 0;
								 remainCount = 0;
							 } else {
								 remainCount = wechatSignImpl.getPrizeRemainCount(catchPrize);
							 }
						} else {
							remainCount = wechatSignImpl.getPrizeRemainCount(catchPrize);
						}
					}
					prizeLevel = Integer.toString(catchPrize);
				}
				ajax.setObj(signinfo); // 签到次数，
			} catch (Exception e) {
				e.printStackTrace();
			}
			ajax.setMsg(Integer.toString(dayCount));
		} else {
			logger.info("用户还没有登记");
			ajax.setMsg("0");
			ajax.setObj(null);
			prizeLevel = "-3";
		}
		request.getSession().setAttribute(open_id, prizeLevel);
		return ajax;
	}
	
	@RequestMapping(value = "/newRandomPrize.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson newRandomPrize(String open_id,HttpServletRequest request) {
		AjaxJson ajax = new AjaxJson();
		String prizeLevel = (String) request.getSession().getAttribute(open_id);
		logger.info(prizeLevel + "*************newRandPrize.do");
		ajax.setMsg(prizeLevel);
		return ajax;
	}
	
	/*
	 * 更新用户奖项，并把奖项记录到数据库中去
	 */
	@RequestMapping(value = "/updatePrizeInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson updatePrize(String openid,HttpServletRequest request) {
		AjaxJson ajax = new AjaxJson();
		int monthCount = 0;       // 本月签到次数
		int prizeNo = -2;
		String prizeLevel = "-1";
		if(request.getSession().getAttribute(openid)==null) {
			prizeNo = -1;
		} else {
			 prizeLevel = (String) request.getSession().getAttribute(openid);
			 prizeNo = Integer.parseInt(prizeLevel);
		}
		
		if (prizeNo >= 0) { // 有效签到次数 如果用户没有刮奖
			logger.info(openid+"所中奖品等级为"+prizeLevel);
			if (prizeNo < 7) { // 如果中奖的话就得保存到数据库中
				WechatWinPrizes win = new WechatWinPrizes();
				win.setPrizeNo(prizeNo);
				win.setOpenId(openid);
				win.setWinDate(new Date());
				int remainCount = wechatSignImpl.getPrizeRemainCount(prizeNo);
				try {
					wechatSignImpl.saveWinPrize(win); // 保存记录到数据库中
					wechatSignImpl.updatePrizeReainCount(prizeNo,remainCount - 1);
					logger.info("成功保存到中奖库中");
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getSession().removeAttribute(openid);
			}
			/*
			 * 更新签到次数和最后签到日期,用户前台显示
			 */
			if (exit) {
				try {
					
					WechatSignInfo signinfo = wechatSignImpl.getSignInfo(openid);
					monthCount = signinfo.getMonthSignAccounts();
					monthCount = monthCount + 1;
					signinfo.setMonthSignAccounts(monthCount);
					signinfo.setLastSignDate(new Date());
					wechatSignImpl.updateSignInfo(signinfo);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (prizeNo == -1) { // 如果用户签到过了之后还去刮奖
			try {
				monthCount = wechatSignImpl.getSignCount(openid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ajax.setMsg(Integer.toString(monthCount));
		return ajax;
	}
	
	/*
     * 显示所有中奖的信息
     */
	@RequestMapping(value = "/listPrizeInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listPrizeInfo() {
		
		AjaxJson ajax = new AjaxJson();
		ArrayList<TeleAndPrizes> list = new ArrayList<TeleAndPrizes>();
		TeleAndPrizes tel = null;
		try {
		   Map<String,Object> map = XmlPraseUtil.parse("/home/files/winner.xml", null);
		   @SuppressWarnings("unchecked")
		   List<String> winnerlist = (List<String>) map.get("winner");
		   for(int i = 0;i<winnerlist.size();i++) {
			     if(i%3==0) {
			    	 tel = new TeleAndPrizes();
			    	 tel.setOpenId(winnerlist.get(i));
			     }
			     if(i%3==1) {
			    	 tel.setTelephone(winnerlist.get(i));
			     }
			     if(i%3==2) {
			    	 tel.setPrizeName(winnerlist.get(i));
			    	 list.add(tel);
			     }
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajax.setObj(list);
		return ajax;
	}
	
	@RequestMapping(value="show.do")
	public ModelAndView showPrizes(String openId,HttpServletRequest request) {
		request.getSession().setAttribute("openId", openId);
		return new ModelAndView("/signInggk/showPrizes");
	}
	/*
     * 显示单人的中奖信息
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listSinglePrizeInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listSinglePrizeInfo(String open_id) {
		AjaxJson ajax = new AjaxJson();
		try {
			if(wechatSignImpl.isExitOpenId(open_id)) {
				List<WechatWinPrizes> list = null;
				list = wechatSignImpl.listAllPrizeByOpenid(open_id);
				logger.info("所有的prize都查出来了");
				ajax.setObj(list);
			} else {
				ajax.setObj(null);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return ajax;
	}
	
}
