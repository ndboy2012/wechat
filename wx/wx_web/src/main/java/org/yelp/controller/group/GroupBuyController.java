package org.yelp.controller.group;

import org.jeecgframework.core.common.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yelp.service.group.GroupBuyServiceI;


@Controller
@RequestMapping("groupBuyController")
public class GroupBuyController {
	@Autowired
	private GroupBuyServiceI groupBuyService;

	@RequestMapping(value = "group.do")
	public ModelAndView group() {
		return new ModelAndView("groupbuy/groupbuy");
	}

	@RequestMapping(value = "product.do")
	@ResponseBody
	public AjaxJson queryAllProduct() {
		AjaxJson j = new AjaxJson();
		try {
			System.out.println(groupBuyService.queryAllProducts().size());
			j.setObj(groupBuyService.queryAllProducts());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
}
