package com.bbs.df.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.df.dao.BoardDao;
import com.bbs.df.dao.PostDao;
import com.bbs.df.dao.UserDao;
import com.bbs.df.po.Board;
import com.bbs.df.po.Post;
import com.bbs.df.po.User;



public class PostHibernateDaoTest {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	PostDao postDao = (PostDao)ac.getBean("postDao");
	@Test
    public void testAddPost(){
    	 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
     	 PostDao postDao =  (PostDao)ac.getBean("postDao");
     	 BoardDao boardDao =  (BoardDao)ac.getBean("boardDao");
     	 UserDao userDao =  (UserDao)ac.getBean("userDao");
     	 Post parent = new Post();
     	 Board board = boardDao.findBoardById(2);
     	 User user = userDao.findUserById(2);
     	 parent.setBoard(board);
     	 parent.setUser(user);
     	 parent.setParent(null);
     	 parent.setTitle("�Ű�֥��л��������¼�");
     	 parent.setContent("�Ű�֥��л�������֮ʼĩ��˭��˭��");
     	 Post c1 = new Post();
     	 c1.setUser(user);
     	 c1.setContent("·����");
     	 c1.setUpdateDate(new Date());
     	 c1.setParent(parent);
     	 Post c2 = new Post();
     	 c2.setUser(user);
     	 c2.setContent("���ͣ�");
     	 c2.setUpdateDate(new Date());
     	 c2.setParent(parent);
     	 Set<Post> posts = new HashSet<Post>();
     	 posts.add(c1);
     	 posts.add(c2);
     	 parent.setPosts(posts);
     	 parent.setReadNum(0);
     	 parent.setReplyNum(0);
     	 parent.setUpdateDate(new Date());
     	 postDao.addPost(parent);
    }
	@Test
	public void testFindPostById(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostDao postDao = (PostDao)ac.getBean("postDao");
		Post p = postDao.findPostById(3);
		if(p == null){
			System.out.println("���Ӳ�����");
		}
		System.out.println(p.getTitle());
		User u = p.getUser();
		System.out.println(u.getName());
		Set<Post> posts = p.getPosts();
		for(Post post:posts){
			System.out.print(post.getContent()+",");
		}
		System.out.println();
		Board b = p.getBoard();
		if(b == null){
			System.out.println("��鲻����");
		}
		System.out.println(b.getName());
	}
	@Test
	public void testFindMainPosts(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostDao postDao = (PostDao)ac.getBean("postDao");
		List<Post> posts = postDao.findMainPosts(3);
		for(Post p:posts){
			System.out.println(p.getTitle());
		}
	}
	@Test
	public void testFindCountByBid(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostDao postDao = (PostDao)ac.getBean("postDao");
		int count = postDao.queryCountByBid(6);
		System.out.println(count);
		count = postDao.queryMainCountByBid(6);
		System.out.println(count);
	}
	@Test
	public void testFindAllPosts(){
		List<Post> posts = postDao.findAllPosts();
		System.out.println(posts.size());
	}
}
