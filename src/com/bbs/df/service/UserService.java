package com.bbs.df.service;

import java.util.List;

import com.bbs.df.po.User;
import com.bbs.df.web.vo.ScrollPage;

public interface UserService {
	/**
	 * 添加用户
	 * @param user 需要添加的用户
	 * @return 添加用户的id
	 * */
    public void addUser(User user);
    /**
     * 查找用户
     * @param id 查找用户的id
     * @return 查找的用户
     * */
    public User findUserById(int id);
    /**
     * 查找所有的用户
     * @return 所有用户的集合
     * */
    public List<User> findAllUsers();
    /**
     * 删除用户
     * @param user 需要删除的用户
     * */
    public void delUser(User user);
    /**
     * 更新用户信息
     * @param user 更新的用户
     * */
    public void updateUser(User user);
    /**
     * 登录
     * */
    public boolean login(String username,String password);
    public boolean reg(String username);
    public User findByName(String name);
    public List queryByPage(Class clazz,ScrollPage page);
    public List queryByPageOnCondition(String sql,ScrollPage page);
}
