package com.bbs.df.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.df.po.Board;
import com.bbs.df.po.Post;
import com.bbs.df.po.User;


public class PostManagerTest {
   @Test
   public void testFindPostById(){
	   ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	   PostManager pm = (PostManager)ac.getBean("postmgr");
	   if(pm == null){
		   return ;
	   }
	   Post p = pm.findPostById(3); 
	   System.out.println(p.getContent());
   }
   
   @Test
   /**
    * ����
    * ���ִﵽ��׼��û����������
    * */
   public void testReplyOrNewPost(){
	   ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	   //AOP��������ʱ���������ɵ��Ǵ���������Ի��������ת������
	   PostManager pm = (PostManager)ac.getBean("postmgr");
	   UserManager um = (UserManager)ac.getBean("usermgr");
	   BoardManager bm = (BoardManager)ac.getBean("boardmgr");
	   User u = um.findUserById(2);
	   Board b = bm.findBoardById(3);
	   Post p = new Post();
	   p.setBoard(b);
	   p.setUser(u);
	   p.setTitle("����Ҳ��Խ������");
	   p.setContent("ǧ��֮����������Խ�ˡ�������");
	   p.setParent(null);
	   p.setReadNum(0);
	   p.setReplyNum(0);
	   p.setUpdateDate(new Date());
//	   pm.replyOrNewPost(p, true);
   }
   
   @Test
   public void testGetScrollPage(){
	   ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	   //AOP��������ʱ���������ɵ��Ǵ���������Ի��������ת������
	   PostManager pm = (PostManager)ac.getBean("postmgr");
	   UserManager um = (UserManager)ac.getBean("usermgr");
	   BoardManager bm = (BoardManager)ac.getBean("boardmgr");
	   
	   List<Post> ps = pm.getScrollPage(1, 4, 3);
   }
}
