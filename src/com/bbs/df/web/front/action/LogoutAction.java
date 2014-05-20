package com.bbs.df.web.front.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.po.User;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{
    String path = "index";
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			System.out.println("****");
			return null;
		}
		session.invalidate();
		return path;
	}
   
}
