package org.yelp.service.impl.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yelp.service.base.RequestProcessI;
import org.yelp.util.XmlPraseUtil;



@Service
public class RequestProcessImpl implements RequestProcessI{

	@Override
	public Map<String, String> praseRequest(HttpServletRequest request)
			throws Exception {
		return XmlPraseUtil.praseRequest(request);
	}

}
