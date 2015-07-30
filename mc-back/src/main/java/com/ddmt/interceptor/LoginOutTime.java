package com.ddmt.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ddmt.constant.Constant;

public class LoginOutTime implements HandlerInterceptor {

	private List<String> excludUrls;

	public List<String> getExcludUrls() {
		return excludUrls;
	}

	public void setExcludUrls(List<String> excludUrls) {
		this.excludUrls = excludUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse respone, Object arg2) throws Exception {

		boolean result = false;
		String url = request.getRequestURI().replace(request.getContextPath(),
				"");
		if (excludUrls.contains(url)) {
			result = true;
		} else if (request.getSession().getAttribute(
				Constant.UPPER_ADMIN_SESSION) != null
				|| request.getSession().getAttribute(
						Constant.COMMON_ADMIN_SESSION) != null) {
			result = true;
		} else {
			forward(request, respone);
			result = false;
		}
		return result;
	}

	public void forward(HttpServletRequest request, HttpServletResponse respone) {
		request.setAttribute("redirectUrl", Constant.ROOT_URL
				+ "loginController.do?login");
		try {
			request.getRequestDispatcher("/webpage/common/loginTimeout.jsp")
					.forward(request, respone);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
