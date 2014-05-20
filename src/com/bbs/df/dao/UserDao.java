package com.bbs.df.dao;

import java.util.List;

import com.bbs.df.po.User;

public interface UserDao extends BaseDao{
	/**
	 * ����û�
	 * @param user ��Ҫ��ӵ��û�
	 * @return ����û���id
	 * */
    public void addUser(User user);
    /**
     * �����û�
     * @param id �����û���id
     * @return ���ҵ��û�
     * */
    public User findUserById(int id);
    /**
     * �������е��û�
     * @return �����û��ļ���
     * */
    public List<User> findAllUsers();
    /**
     * ɾ���û�
     * @param user ��Ҫɾ�����û�
     * */
    public void delUser(User user);
    /**
     * �����û���Ϣ
     * @param user ���µ��û�
     * */
    public void updateUser(User user);
    public User findByName(String name);
}
