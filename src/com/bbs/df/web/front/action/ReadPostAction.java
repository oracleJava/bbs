package com.bbs.df.web.front.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.Post;
import com.bbs.df.service.impl.PostManager;
import com.bbs.df.service.impl.UserManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReadPostAction extends ActionSupport{
	private static Logger logger = Logger.getLogger(ReadPostAction.class);
    private int id;
    private PostManager pmgr;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PostManager getPmgr() {
		return pmgr;
	}

	public void setPmgr(PostManager pmgr) {
		this.pmgr = pmgr;
	}

	@Override
	public String execute() throws Exception {
		String path="thread";
		Post post = pmgr.findPostById(id);
		List<Post> posts = pmgr.findAllPostsByPid(id); 
		HttpServletRequest request = ServletActionContext.getRequest();
		post.setReadNum(post.getReadNum()+1);
		pmgr.updatePost(post);//同一个会话，如何防止重复刷新浏览记录。

		request.setAttribute("post",post);
		System.out.println(post.getReadNum());
		request.setAttribute("posts",posts);
		return path;
	}
}
