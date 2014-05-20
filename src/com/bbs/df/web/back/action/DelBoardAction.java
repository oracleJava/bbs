package com.bbs.df.web.back.action;

import com.bbs.df.po.Board;
import com.bbs.df.service.BoardService;
import com.opensymphony.xwork2.ActionSupport;

public class DelBoardAction extends ActionSupport{
	private BoardService bs;
    private int bid;
    
	public void setBid(int bid) {
		this.bid = bid;
	}
    
	
	public void setBs(BoardService bs) {
		this.bs = bs;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String nextPage = "";
		try{
			bs.delBoard(bid);
		}catch(Exception e){
			return ERROR;
		}
		nextPage = "succ";
		return nextPage;
	}
      
}
