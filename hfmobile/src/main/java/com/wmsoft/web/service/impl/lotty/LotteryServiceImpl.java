package com.wmsoft.web.service.impl.lotty;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wmsoft.common.tools.XmlPraseUtil;
import com.wmsoft.web.service.lotty.LotteryServiceI;

@Service
public class LotteryServiceImpl implements LotteryServiceI {
   
	public static Map<String,Object> map;
	public void storeLottyInfo(String path) throws Exception {	
	    map = XmlPraseUtil.parse(path, null);
	}
}
