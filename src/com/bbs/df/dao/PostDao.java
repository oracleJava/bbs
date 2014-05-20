package com.bbs.df.dao;

import java.util.List;

import com.bbs.df.po.Post;

public interface PostDao extends BaseDao{
	/**
	 * �������
	 * @param post ��Ҫ��ӵ�����
	 * @return ������ӵ�id
	 * */
    public void addPost(Post post);
    /**
     * ��������
     * @param id �������ӵ�id
     * @return ���ҵ�����
     * */
    public Post findPostById(int id);
    /**
     * �����������еĸ���
     * @return ���и����ļ���
     * */
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
     * ��ѯ��������е�����
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
    public List<Post> getScrollPage(int firstIndex, int maxResult,int bid);
    public List<Post> findAllPosts();
}
