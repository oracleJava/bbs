package com.bbs.df.web.front.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.Board;
import com.bbs.df.po.Post;
import com.bbs.df.po.User;
import com.bbs.df.service.BoardService;
import com.bbs.df.service.PostService;
import com.bbs.df.service.UserService;
import com.bbs.df.service.impl.BoardManager;
import com.bbs.df.service.impl.PostManager;
import com.bbs.df.service.impl.UserManager;
import com.bbs.df.util.UpLevel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReplyPostAction extends ActionSupport{
	private static Logger logger = Logger.getLogger(ReplyPostAction.class);
	private int id;
	private String content;
	private PostService pmgr;
	private UserService umgr;
	private BoardService bmgr;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	

	public PostService getPmgr() {
		return pmgr;
	}


	public UserService getUmgr() {
		return umgr;
	}


	public BoardService getBmgr() {
		return bmgr;
	}


	public void setPmgr(PostManager pmgr) {
		this.pmgr = pmgr;
	}

	public void setUmgr(UserManager umgr) {
		this.umgr = umgr;
	}


	public void setBmgr(BoardManager bmgr) {
		this.bmgr = bmgr;
	}

	public void setPmgr(PostService pmgr) {
		this.pmgr = pmgr;
	}


	public void setUmgr(UserService umgr) {
		this.umgr = umgr;
	}


	public void setBmgr(BoardService bmgr) {
		this.bmgr = bmgr;
	}


	@Override
	public String execute() throws Exception {
		String path = "succ";
		Post p = new Post();
		p.setContent(content);
		
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        User u = (User)session.getAttribute("user");
        if(u == null){
        	System.out.println("ÓÃ»§Î´µÇÂ¼£¡£¡£¡");
        	return null;
        }
        u = umgr.findUserById(((User)session.getAttribute("user")).getId());
        p.setUser(u);
        
        Post parent = pmgr.findPostById(id);
        parent.setUpdateDate(new Date());
        parent.setReplyNum(parent.getReplyNum()+1);
        parent.setReadNum(parent.getReadNum()-2);
        System.out.println(parent.getUpdateDate()+":"+parent.getReadNum()+"------------------------------");
        p.setParent(parent);
        parent.getPosts().add(p);
        p.setUpdateDate(new Date());
        p.setPostDate(new Date());
        
//        pmgr.replyOrNewPost(p,u,false);
        UpLevel.readLevel(u,false);
        pmgr.addPost(parent);    
        umgr.updateUser(u);                                                                        
		return path;
	}
}
