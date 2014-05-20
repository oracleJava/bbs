package com.bbs.df.service.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.df.po.User;


public class UserManagerTest {
   @Test
   public void testFindUserById(){
	  ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	  UserManager um = (UserManager)ac.getBean("usermgr");
	  User user = um.findUserById(2);
	  System.out.println(user.getName());
   }
}
