package com.bbs.df.web.front.action;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.po.Board;
import com.bbs.df.po.Post;
import com.bbs.df.po.User;
import com.bbs.df.service.impl.BoardManager;
import com.bbs.df.service.impl.PostManager;
import com.bbs.df.service.impl.UserManager;
import com.opensymphony.xwork2.ActionSupport;

public class NewPostAction extends ActionSupport{
	private int bid;
	private String title;
	private String content;
	private PostManager pmgr;
	private UserManager umgr;
	private BoardManager bmgr;
    
	public UserManager getUmgr() {
		return umgr;
	}

	public void setUmgr(UserManager umgr) {
		this.umgr = umgr;
	}

	public BoardManager getBmgr() {
		return bmgr;
	}

	public void setBmgr(BoardManager bmgr) {
		this.bmgr = bmgr;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public PostManager getPmgr() {
		return pmgr;
	}


	public void setPmgr(PostManager pmgr) {
		this.pmgr = pmgr;
	}

    
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String path = "success";
		Post p = new Post();
		p.setTitle(title);
		p.setContent(content);
		
        Board b = bmgr.findBoardById(bid);
        p.setBoard(b);
        
        HttpSession session = ServletActionContext.getRequest().getSession();
//        User u = (User)session.getAttribute("user");
        User u = umgr.findUserById(((User)session.getAttribute("user")).getId());
        p.setUser(u);
        
        p.setParent(null);
        p.setReadNum(0);
        p.setReplyNum(0);
        p.setPostDate(new Date());
        p.setUpdateDate(new Date());
        
        pmgr.replyOrNewPost(p,u, true);
		return path;
	}
   
}
