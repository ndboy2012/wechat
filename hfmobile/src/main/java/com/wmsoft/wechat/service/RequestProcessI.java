package com.wmsoft.wechat.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RequestProcessI {
   
	 public Map<String, String> praseRequest(HttpServletRequest request) throws Exception;
}
