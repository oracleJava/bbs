package com.bbs.df.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;


import com.bbs.df.dao.AbstractBaseDao;
import com.bbs.df.dao.PostDao;
import com.bbs.df.po.Board;
import com.bbs.df.po.Post;

public class PostHibernateDao extends AbstractBaseDao implements PostDao {

	public List<Post> findAllPosts() {
		// TODO Auto-generated method stub
		String sql = "from Post p where p.parent.id = null";
		return (List<Post>)this.find(sql);
	}

	public int queryCountByBid(int bid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from t_post where pid in (select id from t_post where bid="+bid+") or bid="+bid+"";
		return ((BigInteger)this.getSession().createSQLQuery(sql).list().get(0)).intValue();
	}

	public int queryMainCountByBid(int bid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from t_post where bid="+bid;
		return ((BigInteger)this.getSession().createSQLQuery(sql).list().get(0)).intValue();
	}

	public void addPost(Post post) {
		// TODO Auto-generated method stub
        this.merge(post);
	}

	public void delPost(Post post) {
		// TODO Auto-generated method stub
        this.delete(post);
	}

	public List<Post> findAllPostsByPid(int pid) {
		// TODO Auto-generated method stub
		return (List<Post>)this.find("from Post p where p.parent.id=?",pid);
	}

	public Post findPostById(int id) {
		// TODO Auto-generated method stub
		return (Post)this.get(Post.class, id);
	}

	public void updatePost(Post post) {
		// TODO Auto-generated method stub
        this.update(post);
	}

	public List<Post> findMainPosts(int bid) {
		// TODO Auto-generated method stub
		return (List<Post>)this.find("from Post p where p.parent.id=null and p.board.id=?",bid);
	}
	
	public List<Post> getScrollPage(int firstIndex, int maxResult,int bid){
		return (List<Post>)this.getSession().createQuery("from Post p where p.parent.id=null and p.board.id=:id").setParameter("id",bid).setFirstResult(firstIndex).setMaxResults(maxResult).list();
	}

}
