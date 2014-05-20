package com.bbs.df.service.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.df.po.Board;

public class BoardManagerTest {
   @Test
   public void testFindBoardById(){
	   ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml"); 
	   BoardManager bm = (BoardManager)ac.getBean("boardmgr");
	   Board b = bm.findBoardById(1);
	   if(b == null){
		   return ;
	   }
	   System.out.println(b.getName());
   }
}
