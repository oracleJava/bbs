package com.bbs.df.web.back.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.po.User;
import com.bbs.df.service.PostService;
import com.bbs.df.service.UserService;
import com.bbs.df.web.vo.ScrollPage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MemberListAction extends ActionSupport {
    private UserService us;
    private int pageNo;
    private String name;
    
    
	public void setName(String name) {
		this.name = name;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
		HttpServletRequest request = ServletActionContext.getRequest();
		ScrollPage page = new ScrollPage(); 
		page.setPageNo(pageNo);
		page.setPageSize(5);
		String hql = "from User u where u.role!='ADMIN'";
		if(!"".equals(name)&&name!=null){
			hql+=" and u.name like '%"+name+"%'";
		}
		List<User> users = us.queryByPageOnCondition(hql, page);
		request.setAttribute("members",users);
		request.setAttribute("page",page);
		nextPage = "succ";
		return nextPage;
	}
    
}
