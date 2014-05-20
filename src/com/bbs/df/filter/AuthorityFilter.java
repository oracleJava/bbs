package com.bbs.df.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.df.po.User;

public class AuthorityFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;    
		HttpServletResponse resp = (HttpServletResponse)response;
        if(req.getSession().getAttribute("admin")==null){
        	resp.sendRedirect(req.getContextPath()+"/manager/index.jsp");
        }
        chain.doFilter(request, response);  
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
    
}
