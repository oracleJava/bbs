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
		logger.info("ע���û�");
		this.merge(user);
	}

	public void delUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("ɾ���û�");
        this.delete(user);
	}

	public List<User> findAllUsers() throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("��ʾ�����û�");
		return (List<User>)this.find("from User u where u.role='COMMONUSER'");
	}

	public User findUserById(int id) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("����id��ѯ�û�");
		return (User)this.get(User.class, id);
	}

	public void updateUser(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("�����û�");
        this.update(user);
	}

	public User findByName(String name) throws RuntimeException{
		// TODO Auto-generated method stub
		logger.info("�����û�����ѯ�û�");
		List<User> users = this.find("from User u where u.name=?",name);
		if(users.size() == 0){
			throw new UserNotFoundErrorException("�û�������");
		}
		return users.get(0);
	}

}
