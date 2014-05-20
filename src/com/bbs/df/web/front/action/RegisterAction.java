package com.bbs.df.web.front.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.bbs.df.exception.PassErrorException;
import com.bbs.df.exception.UserNotFoundErrorException;
import com.bbs.df.po.User;
import com.bbs.df.service.impl.UserManager;
import com.bbs.df.system.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{
	private static Logger logger = Logger.getLogger(RegisterAction.class);
    private String name;
    private String password;
    private String sign;
	private UserManager umgr;
	private File photo;
	private String photoContentType;
	private String photoFileName;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public UserManager getUmgr() {
		return umgr;
	}

	public void setUmgr(UserManager umgr) {
		this.umgr = umgr;
	}
    public static void write(File src,File dist){
    	InputStream in = null;
 	    OutputStream out = null;
    	try{
    	   in = new BufferedInputStream(new FileInputStream(src));
    	   out = new BufferedOutputStream(new FileOutputStream(dist));
    	   
    	   byte[] buffer = new byte[1024];
    	   while(in.read(buffer)>0){
    		   out.write(buffer);
    	   }
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	
        		try {
        			if(in!=null){
					   in.close();
        			}
					if(out!=null){
		        		out.close();
		        	}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	
        }
    }

	@Override
	public String execute() throws Exception {
		String path = "index";
		User us = new User();
		us.setName(name);
		us.setPassword(password);
		us.setSign(sign);
		us.setLevel(0);
		us.setRegDate(new Date());
		us.setScore(0);
		photoFileName=new Date().getTime()+photoFileName.substring(photoFileName.lastIndexOf("."));
		System.out.println(photoFileName);
        File dist = new File(ServletActionContext.getServletContext().getRealPath("/upload")+"\\"+photoFileName);
		us.setPhoto(ServletActionContext.getServletContext().getRealPath("/upload")+"\\"+photoFileName);
		us.setRole(Constant.COMMONUSER);
        write(photo,dist);
        System.out.println(us.getPhoto());
        umgr.addUser(us);
		return path;
	}
}
