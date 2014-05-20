package com.bbs.df.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.df.dao.BoardDao;
import com.bbs.df.po.Board;
import com.bbs.df.util.HibernateSessionFactory;
import com.bbs.df.web.vo.ScrollPage;


public class BoardHibernateDaoTest {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	BoardDao boardDao=(BoardDao)ac.getBean("boardDao");
    @Test
    public void testAddBoard(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	BoardDao boardDao=(BoardDao)ac.getBean("boardDao");
//    	Board parent=new Board();
//    	parent.setIntroduction("快乐每一天");
//    	parent.setName("娱乐天下");
//    	parent.setParent(null);
//    	Board ch1=new Board();
//    	ch1.setIntroduction("播放最新娱乐资讯");
//    	ch1.setName("娱乐快报");
//    	ch1.setParent(parent);
//    	Board ch2=new Board();
//    	ch2.setIntroduction("最新，最好听的音乐");
//    	ch2.setName("乐坛动态");
//    	ch2.setParent(parent);
//      Set<Board> children =new HashSet<Board>();
//      children.add(ch1);
//      children.add(ch2);
    	Board parent=new Board();
    	parent.setIntroduction("收罗天下奇闻，纵观世界轶事");
    	parent.setName("奇闻轶事");
    	parent.setParent(null);
    	Board ch1=new Board();
    	ch1.setIntroduction("收罗国内轶事");
    	ch1.setName("国内轶事");
    	ch1.setParent(parent);
    	Board ch2=new Board();
    	ch2.setIntroduction("收罗国外轶事");
    	ch2.setName("国外轶事");
    	ch2.setParent(parent);
        Set<Board> children =new HashSet<Board>();
        children.add(ch1);
        children.add(ch2);
//        parent.setChildren(children);
    	boardDao.addBoard(parent);
    }
    @Test
    public void testFindBoardById(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	BoardDao boardDao=(BoardDao)ac.getBean("boardDao");
    	Board board=boardDao.findBoardById(1);
    	System.out.println(board.getName());
    }
    @Test
    public void testFindBoardsByPid(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	BoardDao boardDao=(BoardDao)ac.getBean("boardDao");
    	List<Board> children=boardDao.findBoardsByPid(1);
    	Iterator<Board> itr=children.iterator();
    	while(itr.hasNext()){
    		System.out.println(itr.next().getName());
    	}
    }
    @Test
    public void testDelBoard(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	BoardDao boardDao=(BoardDao)ac.getBean("boardDao");
    	boardDao.delBoard(3);
    }
    @Test
    public void testFindMainBoards(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	BoardDao boardDao=(BoardDao)ac.getBean("boardDao");
    	List<Board> boards=boardDao.findMainBoards();
    	for(Board b:boards){
    		System.out.println(b.getName());
    	}
    	
    }
    @Test
    public void testScrollPage(){
    	List item = boardDao.findMainBoards();
    	ScrollPage page = new ScrollPage();
    	page.setPageNo(1);
    	page.setPageSize(2);
    	page.setTotalRecords(item);
    	List ave = boardDao.queryByPage(Board.class, page);
    	for(Object obj:ave){
    		Board b =(Board)obj;
    		System.out.println(b.getIntroduction());
    	}
    }
}
