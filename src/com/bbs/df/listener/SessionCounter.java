package com.bbs.df.listener;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.bbs.df.po.User;


public class SessionCounter implements HttpSessionListener,HttpSessionAttributeListener{
	public static Logger logger = Logger.getLogger(SessionCounter.class);
	private HashMap<HttpSession,User> userMap = new HashMap<HttpSession, User>();
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		User user = (User)session.getAttribute("user");
		userMap.put(session,user);
		int onlineNum = userMap.size(),guestNum = 0,memberNum = 0;
		this.eval(onlineNum,guestNum,memberNum,userMap,se.getSession().getServletContext());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		userMap.remove(session);
		int onlineNum = userMap.size(),guestNum = 0,memberNum = 0;
		this.eval(onlineNum,guestNum,memberNum,userMap,se.getSession().getServletContext());
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		User user = (User)session.getAttribute("user");
		userMap.put(session, user);
		userMap.put(session,user);
		int onlineNum = userMap.size(),guestNum = 0,memberNum = 0;
		this.eval(onlineNum,guestNum,memberNum,userMap,se.getSession().getServletContext());
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	
	private void eval(int onlineNum,int guestNum,int memberNum,Map<HttpSession,User> map,ServletContext application){
		Set<Entry<HttpSession,User>> set = userMap.entrySet();
		Iterator<Entry<HttpSession,User>> itr = set.iterator();
		onlineNum = map.size();
		while(itr.hasNext()){
			Entry<HttpSession,User> ety = itr.next();
			if(null == ety.getValue()){
				++guestNum;
			}
		}
		memberNum = onlineNum-guestNum;
		application.setAttribute("userMap",map);
		application.setAttribute("onlineNum", onlineNum);
		application.setAttribute("guestNum", guestNum);
		application.setAttribute("memberNum", memberNum);
	}

}
