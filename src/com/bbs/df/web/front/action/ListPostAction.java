package com.bbs.df.web.front.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.Board;
import com.bbs.df.po.Post;
import com.bbs.df.service.impl.BoardManager;
import com.bbs.df.service.impl.PostManager;
import com.bbs.df.service.impl.UserManager;
import com.bbs.df.web.vo.ScrollPage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListPostAction extends ActionSupport{
	private static Logger logger = Logger.getLogger(ListPostAction.class);
    private int bid;
    private PostManager pmgr;
    private BoardManager bmgr;
    private int pageNo;
    
    
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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


	public PostManager getPmgr() {
		return pmgr;
	}

	public void setPmgr(PostManager pmgr) {
		this.pmgr = pmgr;
	}

	@Override
	public String execute() throws Exception {
		String path="list";
		List posts = pmgr.findMainPosts(bid); 
		Board board = bmgr.findBoardById(bid);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("bid",bid);
//		System.out.println(bid+"bid");
//		int totalPages = 0;
//		int totalRecords = posts.size();
//	    totalPages = totalRecords/maxResult;
//	    if(totalRecords%maxResult !=0){
//	    	totalPages = totalRecords/maxResult+1;
//	    }
//		List<Post> ps = pmgr.getScrollPage(firstIndex, maxResult, bid);
//		request.setAttribute("board",board);
//		request.setAttribute("posts",ps);
//		request.setAttribute("totalPages",totalPages);
//		request.setAttribute("firstIndex", firstIndex);
		ScrollPage page = new ScrollPage();
		page.setPageNo(pageNo);
		page.setPageSize(5);
		page.setTotalRecords(posts);
		String hql = "from Post p where p.parent.id=null and p.board.id="+bid;
		List ps = pmgr.queryByPageOnCondition(hql, page);
		request.setAttribute("board",board);
		request.setAttribute("posts", ps);
		request.setAttribute("page",page);
		return path;
	}
}
