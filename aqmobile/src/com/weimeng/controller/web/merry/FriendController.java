package com.weimeng.controller.web.merry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weimeng.dao.merry.MerryDao;
import com.weimeng.util.AjaxJson;

@Controller
@RequestMapping("friendController")
public class FriendController {

	@Autowired
	private MerryDao merryImpl;

	@RequestMapping(value = "friend.do")
	public ModelAndView friend(String openId, HttpServletRequest request) {
		if (openId == null) {
			return new ModelAndView("common/error");
		}
		request.setAttribute("openId", openId);
		return new ModelAndView("web/merry/friend");
	}

	@RequestMapping(value = "numbers.do")
	@ResponseBody
	public AjaxJson numbers(String openId,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int count = merryImpl.queryFriendOpen(openId);
			if(request.getSession().getAttribute("hasExplose")!=null){
				if(count<10) {
					j.setMsg("已有"+count+"个人帮TA打开了红包");
				} else {
					j.setMsg("TA的红包已被好友全部拆完了！");
				}
				j.setSuccess(false);
				return j;
			}
			if (!merryImpl.hasEnvelop(openId)) {
				j.setMsg("您的好友现在暂时没有任何红包");
				j.setSuccess(false);
				return j;
			} else if(count>=10) {
				j.setMsg("TA的红包已被好友全部拆完了！");
				j.setSuccess(false);
				return j;
			} else {
				j.setMsg("已有"+count+"个人帮TA拆开了红包");
				j.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(value="apply.do")
	@ResponseBody
	public AjaxJson apply(String openId,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			merryImpl.updateEnvelopNumbers(openId);   //更新红包数量
			int count = merryImpl.queryFriendOpen(openId); //查询红包数量
			if(count<10){
				j.setMsg("已有"+count+"个人帮TA拆开了红包");
			} else {
				j.setMsg("TA的红包已经被拆完了！");
			}
			request.getSession().setAttribute("hasExplose", "hasExplose");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
}
