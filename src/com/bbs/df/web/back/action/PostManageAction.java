package com.bbs.df.web.back.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.po.Post;
import com.bbs.df.service.PostService;
import com.bbs.df.web.vo.ScrollPage;
import com.opensymphony.xwork2.ActionSupport;

public class PostManageAction extends ActionSupport{
    private PostService ps;
    private int pageNo;
    private String boardName;
    private String title;
    private String authorName;
    
    
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
		ScrollPage page = new ScrollPage();
		page.setPageNo(pageNo);
		page.setPageSize(5);
		String hql="from Post p where p.parent.id = null";
		if(!"".equals(boardName)&&boardName!=null){
			hql+=" and p.board.name like '%"+boardName+"%'";
		}
		if(!"".equals(title)&&title!=null){
			hql+=" and p.title like '%"+title+"%'";
		}
		if(!"".equals(authorName)&&authorName!=null){
			hql+=" and p.user.name like '%"+authorName+"%'";
		}
		System.out.println(hql);
		List<Post> posts = ps.queryByPageOnCondition(hql, page);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("posts", posts);
		request.setAttribute("page",page);
		nextPage = "succ";
		return nextPage;
	}
    
}
