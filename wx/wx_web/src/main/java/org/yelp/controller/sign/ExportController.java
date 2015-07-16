package org.yelp.controller.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yelp.entity.signInggk.TeleAndPrizes;
import org.yelp.entity.signInggk.WechatSignInfo;
import org.yelp.entity.signInggk.WechatTotalSignAccount;
import org.yelp.entity.signInggk.WechatWinPrizes;
import org.yelp.entity.signInggk.WechatWinnerUtil;
import org.yelp.service.register.RegisterServiceI;
import org.yelp.service.sign.SignServiceI;
import org.yelp.utils.XmlPraseUtil;

@Controller
@RequestMapping("exportController")
public class ExportController {
	
	private Logger logger = Logger.getLogger(ExportController.class);
	
	@Autowired
	private SignServiceI wechatSignImpl;
   
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
		   Map<String,Object> map = XmlPraseUtil.parse("C://aqMobile/winner.xml", null);
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
	
	/*
	 * 更新前台显示
	 */
	@RequestMapping(value = "/updateWinnerlist.do",method=RequestMethod.GET)
	public String updateWinnerlist() {
		try {
			List<WechatWinPrizes> list = wechatSignImpl.getWinPrizes(4);
			List<TeleAndPrizes> listtel = new ArrayList<TeleAndPrizes>();
			Map<String,Object> map = new HashMap<String,Object>();
			TeleAndPrizes tel = null;
		    for(int i=0;i<list.size();i++) {
		    	tel = new TeleAndPrizes();
		    	WechatWinPrizes win = list.get(i);
		    	tel.setOpenId(win.getOpenId());
		    	tel.setTelephone(wechatSignImpl.getTelephone(win.getOpenId()));
		    	tel.setPrizeName(wechatSignImpl.getPrizeName(win.getPrizeNo()));
		    	listtel.add(tel);
		    }
		    map.put("winner", listtel);
		    XmlPraseUtil.ConvertToXmlFile("C://aqMobile/winner.xml", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 更新后台数据库获奖信息表，表中包括open_id,telephone,获奖内容
	 */
	@RequestMapping(value = "/updateWinnerInfo.do",method=RequestMethod.GET)
	public String updateWinnerInfo(String datebef,String dateaft) {
		String result = null;
		try {
			logger.info("***********开始导出获奖内容***************");
			List<Object> list = wechatSignImpl.getWinPrizes(datebef,dateaft);
			WechatWinnerUtil wechatWinnerUtil = null;
			for(int i=0;i<list.size();i++) {
				wechatWinnerUtil = new WechatWinnerUtil();
				Object[] obj = (Object[]) list.get(i);
				String open_id = obj[0].toString();        //获得open_id
				int  prize_no =Integer.parseInt(obj[1].toString());     //获得奖品等级
				
				wechatWinnerUtil.setTelphone(wechatSignImpl.getTelephone(open_id));   //通过open_id来得到电话号码
		        wechatWinnerUtil.setPrizeContent(wechatSignImpl.getPrizeName(prize_no));  //获得奖品内容
		        wechatWinnerUtil.setOpenId(open_id);
		        wechatSignImpl.saveWechatWinnerUtil(wechatWinnerUtil);
			}
	     logger.info("****************全部导出完毕***************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * 更新后台数据库的每月签到表，表中包括了open_id,telephone,本月签到次数。
	 */
	@RequestMapping(value="/updateTotalSignAccount.do",method = RequestMethod.GET)
	public String updateTotalSignAccount(int account) {
		   try {
			List<WechatSignInfo> list = wechatSignImpl.getWechatSignInfos(account);
			WechatTotalSignAccount wechattotal = null;
			for(int i=0;i<list.size();i++) {
				logger.info("***********开始导出获奖内容***************");
				wechattotal = new WechatTotalSignAccount();
				WechatSignInfo wechatSignInfo = list.get(i);
				String open_id = wechatSignInfo.getOpenId();
				
				wechattotal.setTelephone(wechatSignImpl.getTelephone(open_id));
				wechattotal.setOpenId(open_id);
				wechattotal.setMonthSignAccounts(wechatSignInfo.getMonthSignAccounts());
				wechatSignImpl.saveWechatTotalSign(wechattotal);
			}
			logger.info("***************全部导出完毕*************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		   return null;
	}
}
