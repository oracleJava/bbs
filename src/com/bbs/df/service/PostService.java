package com.bbs.df.service;

import java.util.List;

import com.bbs.df.po.Post;
import com.bbs.df.po.User;
import com.bbs.df.web.vo.ScrollPage;

public interface PostService {
	public List<Post> findAllPosts();
    public Post findPostById(int id);
    /**
     * �����������еĸ���
     * @return ���и����ļ���
     * */
    public void addPost(Post post);
    public List<Post> findAllPostsByPid(int pid);
    /**
     * ɾ������
     * @param user ��Ҫɾ��������
     * */
    public void delPost(Post post);
    /**
     * ����������Ϣ
     * @param user ���µ�����
     * */
    public void updatePost(Post post); 
    /**
     * ��ѯ���е�����
     * */
    public List<Post> findMainPosts(int bid);
    /**
     * ��ѯ��������е�����
     * */
    public int queryCountByBid(int bid);
    /**
     * ��ѯ�����������
     * */
    public int queryMainCountByBid(int bid);
    public void replyOrNewPost(Post post,User user,boolean type);
    
    public List<Post> getScrollPage(int firstIndex, int maxResult,int bid);
    public List queryByPage(Class clazz,ScrollPage page);
    public List queryByPageOnCondition(String sql,ScrollPage page);
    
}
