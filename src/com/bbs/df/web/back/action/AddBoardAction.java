package com.bbs.df.web.back.action;

import java.util.Random;

import com.bbs.df.po.Board;
import com.bbs.df.service.BoardService;
import com.bbs.df.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class AddBoardAction extends ActionSupport{
    private String name;
    private int id;
    private String introduction;
    private BoardService bs;
    
    
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}



	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String nextPage = "";
		Board board = new Board();
		board.setName(name);
		if(id==-1){
			board.setParent(null);
		}
		Board parent = bs.findBoardById(id);
		board.setParent(parent);
		board.setIntroduction(introduction);
		Random random = new Random();
//		board.setPosition(random.nextInt(100000));
		bs.addBoard(board);   
		nextPage = "succ";
		return nextPage;
	}


	public BoardService getBs() {
		return bs;
	}


	public void setBs(BoardService bs) {
		this.bs = bs;
	}
    
}
