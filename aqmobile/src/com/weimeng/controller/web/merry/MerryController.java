package com.weimeng.controller.web.merry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weimeng.dao.merry.MerryDao;
import com.weimeng.entity.web.merry.MerryAchieverInfo;
import com.weimeng.entity.web.merry.MerryUnopenInfo;
import com.weimeng.util.AjaxJson;
import com.weimeng.util.DateProcess;
import com.weimeng.util.Sweepstakes;

/**
 * 圣诞节好友拆红包活动
 * @author Administrator
 */
@Controller
@RequestMapping("merryController")
public class MerryController {

	@Autowired
	public MerryDao merryImpl;
    
	@RequestMapping(value = "envelop.do")
	public ModelAndView envelop(HttpServletRequest request, String openId) {
		if (openId == null) {
			return new ModelAndView("common/error");
		}
		try {
			if (!merryImpl.hasEnvelop(openId)) { // 如果还没有红包
				MerryUnopenInfo merry = new MerryUnopenInfo();
				merry.setOpenId(openId);
				merry.setTelephone(merryImpl.queryTelByOpenId(openId));
				merry.setOpen(0);
				merry.setBeiginTime(DateProcess.geTimestamp());
				merryImpl.saveMerryUnopenInfo(merry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("openId", openId);
		return new ModelAndView("web/merry/unopen");
	}
    
	@RequestMapping(value = "queryNum.do")
	@ResponseBody
	public AjaxJson queryNum(String openId) {
		AjaxJson j = new AjaxJson();
		String message = "";
		j.setSuccess(false);
		try {
			int count = merryImpl.queryFriendOpen(openId);
			if(count<10) {
				message = "已有" + count + "个好友为您拆了红包";
			} else if(!merryImpl.hasPrizeContent(openId)) {  //如果还没有打开红包
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
			String prizeContent = merryImpl.hasPrizeInfo(openId);
			if(merryImpl.hasPrizeInfo(openId)!=null) {  //如果存在奖品的话
				j.setMsg(prizeContent);
				return j;
			}
			
			int prizeLevel = Sweepstakes.getLottyNum();
			while(merryImpl.queryPrizeRemainCounts(prizeLevel)<0) {  //如果奖品数量小于0
				prizeLevel = Sweepstakes.getLottyNum();
			}
			
			MerryAchieverInfo info = new MerryAchieverInfo();
			info.setOpenId(openId);
			info.setTelephone(merryImpl.queryTelByOpenId(openId));
			prizeContent = merryImpl.queryPrizeNameByLevel(prizeLevel); 
			info.setPrizeName(prizeContent);
			info.setAchieveTime(DateProcess.geTimestamp());
			merryImpl.savePrizeAchever(info,prizeLevel);
			j.setMsg(prizeContent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
}
