package com.bbs.df.service;

import java.util.List;

import com.bbs.df.po.Board;
import com.bbs.df.web.vo.ScrollPage;

public interface BoardService {
	public void addBoard(Board board);
    public void delBoard(int id);
    public Board findBoardById(int id);
    public List<Board> findMainBoards();
    public List<Board> findBoardsByPid(int pid);
    public List queryByPage(Class clazz,ScrollPage page);
    public List queryByPageOnCondition(String sql,ScrollPage page); 
}
