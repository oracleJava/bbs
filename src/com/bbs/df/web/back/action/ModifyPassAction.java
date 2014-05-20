package com.bbs.df.web.back.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.po.User;
import com.bbs.df.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyPassAction extends ActionSupport{
	private UserService us;
    private String password; 
    private String newpass;
   
    
	public UserService getUs() {
		return us;
	}


	public void setUs(UserService us) {
		this.us = us;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNewpass() {
		return newpass;
	}


	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String nextPage = "succ";
		HttpSession session = ServletActionContext.getRequest().getSession();
		User admin = (User)session.getAttribute("admin");
		if(!password.equals(admin.getPassword())){
			session.setAttribute("info","原密码错误！");
			return nextPage;
		}
		admin.setPassword(newpass);
		us.updateUser(admin);
		session.setAttribute("info","密码修改成功！");
		return nextPage;
	}

}
