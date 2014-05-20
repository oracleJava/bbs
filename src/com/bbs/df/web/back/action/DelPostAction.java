package com.bbs.df.web.back.action;

import com.bbs.df.po.Post;
import com.bbs.df.service.PostService;
import com.opensymphony.xwork2.ActionSupport;

public class DelPostAction extends ActionSupport{
    private PostService ps;
    private int id;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PostService getPs() {
		return ps;
	}

	public void setPs(PostService ps) {
		this.ps = ps;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String nextPage = "";
		Post post =  ps.findPostById(id);
		ps.delPost(post);
		nextPage = "succ";
		return nextPage;
	}
   
}
