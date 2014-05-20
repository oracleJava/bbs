package com.bbs.df.service.impl;

import java.util.List;

import com.bbs.df.dao.BoardDao;
import com.bbs.df.po.Board;
import com.bbs.df.service.BoardService;
import com.bbs.df.web.vo.ScrollPage;

public class BoardManager implements BoardService {
    

	private BoardDao boardDao;
    
	public BoardDao getBoardDao() throws RuntimeException{
		return boardDao;
	}

	public void setBoardDao(BoardDao boardDao) throws RuntimeException{
		this.boardDao = boardDao;
	}

	public void addBoard(Board board) throws RuntimeException{
		// TODO Auto-generated method stub
		boardDao.addBoard(board);
	}

	public List queryByPage(Class clazz, ScrollPage page) throws RuntimeException{
		// TODO Auto-generated method stub
		return boardDao.queryByPage(Board.class, page);
	}

	public void delBoard(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		boardDao.delBoard(id);
	}

	public Board findBoardById(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		return boardDao.findBoardById(id);
	}

	public List<Board> findBoardsByPid(int pid) throws RuntimeException{
		// TODO Auto-generated method stub
		return boardDao.findBoardsByPid(pid);
	}

	public List<Board> findMainBoards() throws RuntimeException{
		// TODO Auto-generated method stub
		return boardDao.findMainBoards();
	}
	public List queryByPageOnCondition(String sql, ScrollPage page) throws RuntimeException{
		// TODO Auto-generated method stub
		return boardDao.queryByPageOnCondition(sql, page);
	}

}
