package com.bbs.df.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bbs.df.web.vo.ScrollPage;

public abstract class AbstractBaseDao extends HibernateTemplate implements BaseDao{

	public List queryByPage(Class clazz,ScrollPage page) {
		// TODO Auto-generated method stub
		String hql = "select model from "+clazz.getSimpleName()+" as model";
        Query query = this.getSession().createQuery(hql);
		return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list();
	}
	
	public List queryByPageOnCondition(String hql,ScrollPage page){
		Query query = this.getSession().createQuery(hql);
		System.out.println(query.list().size()+"--------------------");
		page.setTotalRecords(query.list());
		return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list();
	}

}
