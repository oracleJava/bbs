package com.bbs.df.web.back.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bbs.df.po.Board;
import com.bbs.df.service.BoardService;
import com.opensymphony.xwork2.ActionSupport;

public class BoardListAction extends ActionSupport {
    private BoardService bs;
    
	public BoardService getBs() {
		return bs;
	}

	public void setBs(BoardService bs) {
		this.bs = bs;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String nextPage = "";
		List<Board> fboards = bs.findMainBoards();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("fboards",fboards);
		nextPage="succ";
		return nextPage;
	}
   
}
