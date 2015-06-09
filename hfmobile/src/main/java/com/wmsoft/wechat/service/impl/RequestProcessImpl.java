package com.wmsoft.wechat.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.wmsoft.common.tools.XmlPraseUtil;
import com.wmsoft.wechat.service.RequestProcessI;

@Service("requestProcessImpl")
public class RequestProcessImpl implements RequestProcessI{

	@Override
	public Map<String, String> praseRequest(HttpServletRequest request)
			throws Exception {
		return XmlPraseUtil.praseRequest(request);
	}
}
