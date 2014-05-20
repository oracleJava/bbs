package com.bbs.df.service;

import java.util.List;

import com.bbs.df.po.User;
import com.bbs.df.web.vo.ScrollPage;

public interface UserService {
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
    /**
     * ��¼
     * */
    public boolean login(String username,String password);
    public boolean reg(String username);
    public User findByName(String name);
    public List queryByPage(Class clazz,ScrollPage page);
    public List queryByPageOnCondition(String sql,ScrollPage page);
}
