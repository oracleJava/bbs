package com.bbs.df.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bbs.df.dao.AbstractBaseDao;
import com.bbs.df.dao.UserDao;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.User;

public class UserHibernateDao extends AbstractBaseDao implements UserDao {
    public static Logger logger = Logger.getLogger(UserHibernateDao.class);
	public void addUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("注册用户");
		this.merge(user);
	}

	public void delUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("删除用户");
        this.delete(user);
	}

	public List<User> findAllUsers() throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("显示所有用户");
		return (List<User>)this.find("from User u where u.role='COMMONUSER'");
	}

	public User findUserById(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("根据id查询用户");
		return (User)this.get(User.class, id);
	}

	public void updateUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("更新用户");
        this.update(user);
	}

	public User findByName(String name) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("根据用户名查询用户");
		List<User> users = this.find("from User u where u.name=?",name);
		if(users.size() == 0){
			throw new UserNotFoundErrorException("用户不存在");
		}
		return users.get(0);
	}

}
