package com.bbs.df.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post implements Serializable{
	private Integer id;
	private String title;// ���ӱ���
	private String content;// ��������
	private Date postDate;//����ʱ��
	private Date updateDate;// ����ʱ��
	private double readNum;// �����
	private double replyNum;// �ظ���
	private User user;// ����
	private Post parent;// ����
	private Board board;// �������ڰ��
	private Set<Post> posts=new HashSet<Post>();//�������еĸ���(����û�и���)
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
