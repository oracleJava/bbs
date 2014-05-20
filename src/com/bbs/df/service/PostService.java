package com.bbs.df.service;

import java.util.List;

import com.bbs.df.po.Post;
import com.bbs.df.po.User;
import com.bbs.df.web.vo.ScrollPage;

public interface PostService {
	public List<Post> findAllPosts();
    public Post findPostById(int id);
    /**
     * 查找主贴所有的跟帖
     * @return 所有跟帖的集合
     * */
    public void addPost(Post post);
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
     * 查询所有的主贴
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
    public void replyOrNewPost(Post post,User user,boolean type);
    
    public List<Post> getScrollPage(int firstIndex, int maxResult,int bid);
    public List queryByPage(Class clazz,ScrollPage page);
    public List queryByPageOnCondition(String sql,ScrollPage page);
    
}
