package com.bbs.df.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Board implements Serializable{
	private Integer id;
	private String name;//°æ¿éÃû
	private String introduction;//°æ¿é½éÉÜ
	private Board parent;//¸¸°æ¿é
	private Integer position;
	private List<Board> children=new ArrayList<Board>();//¸¸°æ¿éÏÂËùÓĞµÄ×Ó°æ¿é
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Board getParent() {
		return parent;
	}

	public void setParent(Board parent) {
		this.parent = parent;
	}

	public List<Board> getChildren() {
		return children;
	}

	public void setChildren(List<Board> children) {
		this.children = children;
	}

	

	

	

}
