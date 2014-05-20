package com.bbs.df.dao;

import java.util.List;

import com.bbs.df.po.Post;

public interface PostDao extends BaseDao{
	/**
	 * 添加帖子
	 * @param post 需要添加的帖子
	 * @return 添加帖子的id
	 * */
    public void addPost(Post post);
    /**
     * 查找帖子
     * @param id 查找帖子的id
     * @return 查找的帖子
     * */
    public Post findPostById(int id);
    /**
     * 查找主贴所有的跟帖
     * @return 所有跟帖的集合
     * */
    public List<Post> findAllPostsByPid(int pid);
    /**
     * 删除帖子
     * @param user 需要删除的帖子
     * */
    public void delPost(Post post);
    /**
     * 更新帖子信息
     * @param user 更新的帖子
     * */
    public void updatePost(Post post); 
    /**
     * 查询版块下所有的主贴
     * */
    public List<Post> findMainPosts(int bid);
    /**
     * 查询版块下所有的帖数
     * */
    public int queryCountByBid(int bid);
    /**
     * 查询版块下主题数
     * */
    public int queryMainCountByBid(int bid);
    public List<Post> getScrollPage(int firstIndex, int maxResult,int bid);
    public List<Post> findAllPosts();
}
