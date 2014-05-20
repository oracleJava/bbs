package com.bbs.df.dao;

import java.util.List;

import com.bbs.df.po.Board;

public interface BoardDao extends BaseDao{
    public void addBoard(Board board);
    public void delBoard(int id);
    public Board findBoardById(int id);
    public List<Board> findMainBoards();
    public List<Board> findBoardsByPid(int pid);
}
