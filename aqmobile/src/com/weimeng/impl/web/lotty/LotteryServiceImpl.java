package com.weimeng.impl.web.lotty;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.weimeng.dao.lotty.LotteryServiceI;
import com.weimeng.util.ParseXml;


@Service
public class LotteryServiceImpl implements LotteryServiceI {
   
	public static Map<String,Object> map;
	public void storeLottyInfo(String path) throws Exception {	
	    map = ParseXml.parse(path, null);
	}
}
