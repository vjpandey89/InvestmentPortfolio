package com.info.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vijay
 *
 *         Intercepter which counts the number of active users and restricts in
 *         case there are more session logged in In case 2 users are accessing ,
 *         the API doesn't entertain more users returns a 401 status code
 *
 */
public class UsersLimitInterceptor implements HandlerInterceptor {

	private static Object lock = new Object();
	private static int count = 0;
	int MAX_USERS = 2;

	static Logger logger = Logger.getLogger(UsersLimitInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		synchronized (lock) {
			count--;
		}

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		// logger.info("count is "+count);

		if (count == MAX_USERS) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Reached max number of users : ");
			return false;

		}

		else {
			synchronized (lock) {
				count++;
			}
			return true;
		}
	}

}
