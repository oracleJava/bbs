package com.bbs.df.dao.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.df.dao.UserDao;
import com.bbs.df.po.User;


public class UserHibernateDaoTest {
	@Test
	public void testAddUser() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");
		User u = new User();
		u.setLevel(0);
		u.setName("董飞");
		u.setPassword("dongfei");
		u.setPhoto("dfdfd");
		u.setScore(0);
		u.setSign(null);
		u.setRegDate(new Date());
		userDao.addUser(u);
	}

	@Test
	public void testFindUserById() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");
		User u = userDao.findUserById(1);
		if (u == null) {
			return;
		}
		System.out.println(u.getName());
	}

	@Test
	public void testFindAllUsers() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");
		List<User> users = userDao.findAllUsers();
		for(User user:users){
			System.out.println(user.getName());
		}
	}
	@Test
	public void testUpdateUser(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	UserDao userDao = (UserDao)ac.getBean("userDao");
    	User user = userDao.findUserById(1);
    	if(user == null){
    		System.out.println("用户不存在");
    		return ;
    	}
    	user.setName("徐昕");
        userDao.updateUser(user);
	}
	@Test
	public void testDelUser(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	UserDao userDao = (UserDao)ac.getBean("userDao");
    	User user = userDao.findUserById(1);
    	if(user == null){
    		System.out.println("用户不存在");
    	}
    	userDao.delUser(user);
	}
	@Test
	public void testFindByName(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    	UserDao userDao = (UserDao)ac.getBean("userDao");
    	User user = userDao.findByName("董飞");
    	System.out.println(user.getPassword());
	}
}
