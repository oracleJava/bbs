package com.bbs.df.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post implements Serializable{
	private Integer id;
	private String title;// 帖子标题
	private String content;// 帖子内容
	private Date postDate;//发表时间
	private Date updateDate;// 更新时间
	private double readNum;// 浏览数
	private double replyNum;// 回复数
	private User user;// 作者
	private Post parent;// 父贴
	private Board board;// 主贴所在版块
	private Set<Post> posts=new HashSet<Post>();//主贴所有的跟帖(主贴没有父贴)
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public double getReadNum() {
		return readNum;
	}

	public void setReadNum(double readNum) {
		this.readNum = readNum;
	}

	public double getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(double replyNum) {
		this.replyNum = replyNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getParent() {
		return parent;
	}

	public void setParent(Post parent) {
		this.parent = parent;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

}
