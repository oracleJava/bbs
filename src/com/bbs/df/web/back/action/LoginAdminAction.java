package com.bbs.df.web.back.action;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.User;
import com.bbs.df.service.UserService;
import com.bbs.df.system.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAdminAction extends ActionSupport{
    private UserService us;
    private String name;
    private String password;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String nextPage = "";
		boolean flag = false;
		try{
		   flag = us.login(name, password);
		}catch(UserNotFoundErrorException e){
			nextPage = "error";
			ServletActionContext.getRequest().setAttribute("info",e.getMessage());
			return nextPage;
		}catch(PassErrorException e){
			nextPage = "error";
			ServletActionContext.getRequest().setAttribute("info",e.getMessage());
			return nextPage;
		}
		User u = null;
		if(flag){
			u = us.findByName(name);
			if(!(Constant.ADMINISTRATOR).equals(u.getRole())){
				nextPage = "error";
				ServletActionContext.getRequest().setAttribute("info", "È¨ÏÞ²»×ã£¡");
				return nextPage;
			}
		}
		ServletActionContext.getRequest().getSession().setAttribute("admin",u);
		ActionContext.getContext().getSession().put("admin",u);
		nextPage = "succ";
		return nextPage;
	}
    
}
