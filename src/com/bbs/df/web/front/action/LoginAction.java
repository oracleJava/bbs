package com.bbs.df.web.front.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.User;
import com.bbs.df.service.impl.UserManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private static Logger logger = Logger.getLogger(LoginAction.class);
    private String username;
    private String password;
    private String validateCode;
    private UserManager umgr;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public UserManager getUmgr() {
		return umgr;
	}

	public void setUmgr(UserManager umgr) {
		this.umgr = umgr;
	}

	@Override
	public String execute() throws Exception {
		String path = "index";
		boolean flag = false;
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			flag = umgr.login(username, password);
			String code = (String)session.getAttribute("ValidateCode");
			System.out.println(code);
			if(validateCode.equalsIgnoreCase(code)){
				flag = true;
			}
		} catch (UserNotFoundErrorException ue) {
			// TODO Auto-generated catch block
			logger.error(ue.getMessage());
			path = "error";
		} catch (PassErrorException pe) {
			logger.error(pe.getMessage());
			path = "error";
		}
		
		if(flag){
			User user = umgr.findByName(username);
		    session.setAttribute("username",username);
		    session.setAttribute("user",user);
			return path;
		}else{
			path = "error";
			return path;
		}
	}
}
