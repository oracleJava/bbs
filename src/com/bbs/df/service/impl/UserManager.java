package com.bbs.df.service.impl;

import java.util.List;

import com.bbs.df.dao.UserDao;
import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.User;
import com.bbs.df.service.UserService;
import com.bbs.df.web.vo.ScrollPage;

public class UserManager implements UserService {
   
	private UserDao userDao;
    
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
        userDao.addUser(user);
	}

	public void delUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
        userDao.delUser(user);
	}

	public List<User> findAllUsers() throws RuntimeException{
		// TODO Auto-generated method stub
		return userDao.findAllUsers();
	}

	public User findUserById(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}

	public void updateUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
        userDao.updateUser(user);
	}

	public boolean login(String username, String password) throws RuntimeException{
		// TODO Auto-generated method stub
		boolean flag = true;
	    User user = userDao.findByName(username);
	    if(user == null){
	    	flag = false;
	    	throw new UserNotFoundErrorException("用户不存在！！！");
	    }
	    if(!user.getPassword().equals(password)){
	    	flag = false;
	    	throw new PassErrorException("密码错误！！！");
	    }
	    return flag;
	}
	
	 public boolean reg(String username) throws RuntimeException{
		// TODO Auto-generated method stub
		boolean flag = false;
		User user = userDao.findByName(username);
		if(user != null){
			System.out.println("用户已存在！！！");
		}
		System.out.println("用户名可用！");
		return flag;
     }
	 public User findByName(String name) throws RuntimeException{
		 return userDao.findByName(name);
	 }

	public List queryByPage(Class clazz, ScrollPage page) {
		// TODO Auto-generated method stub
		return userDao.queryByPage(User.class, page);
	}

	public List queryByPageOnCondition(String sql, ScrollPage page) {
		// TODO Auto-generated method stub
		return userDao.queryByPageOnCondition(sql, page);
	}
	 
	 
}
