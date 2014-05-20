package com.bbs.df.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.bbs.df.po.User;


public class UpLevel {
	private static Logger logger = Logger.getLogger(UpLevel.class);
    private static Properties prop=new Properties();
    private UpLevel(){}
    static{
    	try {
    		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("level.properties");
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("配置文件加载错误！！！");
		}
    }
    /**
     * @param user 当前的用户
     * @param type 行为  true：为发贴 +2分 ;false：回帖+0.5分
     * */
    public static Integer readLevel(User user,boolean type){
    	double addScore = 0;
    	if(type==true){
    		addScore = 2;
    	}
    	if(type==false){
    		addScore = 0.5;
    	}
    	Integer level = user.getLevel();
    	Double score=Double.parseDouble((String)prop.get((level+1)+""));
    	user.setScore(user.getScore()+addScore);
    	System.out.println(user.getScore()+"|"+score);
    	if(score!=null&&user.getScore()>=score){
    		user.setLevel(level+1);
    		System.out.println(user.getLevel());
    		return level+1;
    	}
    	return level;
    }
}
