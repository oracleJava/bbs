package com.bbs.df.service.impl;

import java.util.List;

import com.bbs.df.dao.PostDao;
import com.bbs.df.dao.UserDao;
import com.bbs.df.po.Post;
import com.bbs.df.po.User;
import com.bbs.df.service.PostService;
import com.bbs.df.util.UpLevel;
import com.bbs.df.web.vo.ScrollPage;


public class PostManager implements PostService {

	
	private PostDao postDao;
    private UserDao userDao;
	public PostDao getPostDao() { 
		return postDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
 
	public void delPost(Post post) {
		// TODO Auto-generated method stub
		postDao.delPost(post);
	}
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

   
	public List<Post> findAllPostsByPid(int pid) {
		// TODO Auto-generated method stub
		return postDao.findAllPostsByPid(pid);
	}

	public List<Post> findMainPosts(int bid) {
		// TODO Auto-generated method stub
		return postDao.findMainPosts(bid);
	}

	public Post findPostById(int id) {
		// TODO Auto-generated method stub
		return postDao.findPostById(id);
	}

	public void updatePost(Post post) {//never used
		// TODO Auto-generated method stub
        postDao.updatePost(post);
	}

	//boolean 为true时，表示发表帖子，为false时，表示回复帖子。
	//发表帖子加2分，回复帖子加0.5分。
	public void replyOrNewPost(Post post,User user,boolean type) {
		// TODO Auto-generated method stub
		UpLevel.readLevel(user, type);
		userDao.updateUser(user);
		postDao.addPost(post);
	}
	
	public int queryCountByBid(int bid) {
		// TODO Auto-generated method stub
		return postDao.queryCountByBid(bid);
	}

	public int queryMainCountByBid(int bid) {
		// TODO Auto-generated method stub
		return postDao.queryMainCountByBid(bid);
	}

	public List<Post> getScrollPage(int firstIndex, int maxResult,int bid) {
		// TODO Auto-generated method stub
		return postDao.getScrollPage(firstIndex, maxResult,bid);
	}
	public List<Post> findAllPosts() {
		// TODO Auto-generated method stub
		return postDao.findAllPosts();
	}
	public List queryByPage(Class clazz, ScrollPage page) {
		// TODO Auto-generated method stub
		return postDao.queryByPage(Post.class, page);
	}

	public List queryByPageOnCondition(String sql, ScrollPage page) {
		// TODO Auto-generated method stub
		return postDao.queryByPageOnCondition(sql, page);
	}

	public void addPost(Post post) {
		// TODO Auto-generated method stub
		postDao.addPost(post);
	}

}
