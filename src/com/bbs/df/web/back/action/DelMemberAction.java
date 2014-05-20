package com.bbs.df.web.back.action;

import org.apache.log4j.Logger;

import com.bbs.df.po.User;
import com.bbs.df.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class DelMemberAction extends ActionSupport{
	public static final Logger logger = Logger.getLogger(DelMemberAction.class);
    private UserService us ;
    private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		System.out.println(id);
		String nextPage = "";
		try{
	       User user = us.findUserById(id);
	       us.delUser(user);
		}catch(RuntimeException e){
		   logger.error("É¾³ýÓÃ»§Ê§°Ü£¡");
		   nextPage = "error";
		}
		nextPage = "succ";
		return nextPage;
	}
   
}
