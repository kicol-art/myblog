package com.nm.blog.utils;

import java.io.IOException;
import java.net.URI;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

import com.nm.blog.pojo.UserInfo;

@WebFilter("/back/*")//请求/back路径都过滤
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain)
			throws IOException, ServletException {
		
		//从HttpServletRequest获取请求路径
		HttpServletRequest hreq=(HttpServletRequest) sreq;
		String uri=hreq.getRequestURI();
		System.out.println("URI过滤路径："+uri);
		
		//从HttpSession中获取用户信息
		HttpSession session=hreq.getSession();
		UserInfo user=(UserInfo) session.getAttribute("userinfo");
		if(uri.contains("/login")) {//请求路径包含login不过滤
			chain.doFilter(sreq, sresp);
		}else if(user!=null) {
			chain.doFilter(sreq, sresp);//session中含用户信息不过来
		}else {
			hreq.getRequestDispatcher("/back/login").forward(hreq,sresp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
