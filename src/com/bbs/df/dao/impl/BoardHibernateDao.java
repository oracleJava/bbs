package com.bbs.df.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bbs.df.dao.AbstractBaseDao;
import com.bbs.df.dao.BoardDao;
import com.bbs.df.po.Board;
import com.bbs.df.web.vo.ScrollPage;

public class BoardHibernateDao extends AbstractBaseDao implements BoardDao { 

	public void addBoard(Board board) throws RuntimeException{ 
		// TODO Auto-generated method stub
		this.merge(board);
	}

	public void delBoard(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		this.delete(get(Board.class,id));
	}

	public Board findBoardById(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		return (Board)get(Board.class, id);
	}

	public List<Board> findBoardsByPid(int pid) throws RuntimeException{
		// TODO Auto-generated method stub
		return (List<Board>)find("from Board b where b.parent.id=?", pid);
	}
    public List<Board> findMainBoards()throws RuntimeException{
    	return (List<Board>)this.find("from Board b where b.parent.id=null");
    }

}
