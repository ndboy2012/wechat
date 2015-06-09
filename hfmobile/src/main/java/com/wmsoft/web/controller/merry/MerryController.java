package com.wmsoft.web.controller.merry;


import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wmsoft.common.tools.DateProcess;
import com.wmsoft.common.tools.Sweepstakes;
import com.wmsoft.web.entity.merry.MerryAchieverInfo;
import com.wmsoft.web.entity.merry.MerryUnopenInfo;
import com.wmsoft.web.service.merry.MerryServiceI;
import com.wmsoft.wechat.constant.WechatConstant;



/**
 * 圣诞节好友拆红包活动
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("merryController")
public class MerryController {

	@Autowired
	public MerryServiceI merryServiceImpl;

	@RequestMapping(value = "envelop.do")
	public ModelAndView envelop(HttpServletRequest request, String openId) {
		if (openId == null) {
			return new ModelAndView("common/error");
		}
		try {
			if (!merryServiceImpl.hasEnvelop(openId)) { // 如果还没有红包
				MerryUnopenInfo merry = new MerryUnopenInfo();
				merry.setOpenId(openId);
				merry.setTelephone(merryServiceImpl.queryTelByOpenId(openId));
				merry.setOpen(0);
				merry.setBeiginTime(DateProcess.geTimestamp());
				merryServiceImpl.saveMerryUnopenInfo(merry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("openId", openId);
		return new ModelAndView("merry/unopen");
		/*return new ModelAndView("common/activityout");*/
	}
	
	@RequestMapping(value="envelopWe.do")
	public ModelAndView envelopWe(HttpServletRequest request) {
		String openId = (String) request.getSession().getAttribute(WechatConstant.register_session_openId);
		if(openId!=null) {
			MerryUnopenInfo merry = new MerryUnopenInfo();
			merry.setOpenId(openId);
			try {
				merry.setTelephone(merryServiceImpl.queryTelByOpenId(openId));
				merry.setOpen(0);
				merry.setBeiginTime(DateProcess.geTimestamp());
				merryServiceImpl.saveMerryUnopenInfo(merry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return new ModelAndView("common/error");
		}
		request.setAttribute("openId", openId);
		/*return new ModelAndView("merry/unopen");*/
		return new ModelAndView("common/activityout");
	}

	@RequestMapping(value = "queryNum.do")
	@ResponseBody
	public AjaxJson queryNum(String openId) {
		AjaxJson j = new AjaxJson();
		String message = "";
		j.setSuccess(false);
		try {
			int count = merryServiceImpl.queryFriendOpen(openId);
			if(count<10) {
				message = "已有" + count + "个好友为您拆了红包";
			} else if(!merryServiceImpl.hasPrizeContent(openId)) {  //如果还没有打开红包
				message = "恭喜您的神秘红包已被10位好友帮拆开，现在去打开吧";
				j.setSuccess(true);
			} else {
				message = "您已有个打开的红包了";
				j.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(value="open.do")
	@ResponseBody
	public AjaxJson open(String openId) {
		AjaxJson j = new AjaxJson();
		try {
			String prizeContent = merryServiceImpl.hasPrizeInfo(openId);
			if(prizeContent!=null) {  //如果存在奖品的话
				j.setMsg(prizeContent);
				return j;
			}
			
			int prizeLevel = Sweepstakes.getLottyNum();
			while(merryServiceImpl.queryPrizeRemainCounts(prizeLevel)<0) {  //如果奖品数量小于0
				prizeLevel = Sweepstakes.getLottyNum();
			}
			MerryAchieverInfo info = new MerryAchieverInfo();
			info.setOpenId(openId);
			info.setTelephone(merryServiceImpl.queryTelByOpenId(openId));
			prizeContent = merryServiceImpl.queryPrizeNameByLevel(prizeLevel);
			info.setPrizeName(prizeContent);
			info.setAchieveTime(DateProcess.geTimestamp());
			merryServiceImpl.savePrizeAchever(info,prizeLevel);
			j.setMsg(prizeContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
}
