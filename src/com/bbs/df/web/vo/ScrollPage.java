package com.bbs.df.web.vo;

import java.util.ArrayList;
import java.util.List;



public class ScrollPage {
	private int totalRecords;// 总记录数
	private int pageSize=5;//每页的大小
	private int pageNo=1;//当前的页数
	private int totalPage;
	
	
	


	public int getPageNo() {
		return pageNo;
	}
	public void setTotalRecords(List item) {
		this.totalRecords = item.size();
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo==0?1:pageNo;
	}
	public int getPageSize(){
		return pageSize;
	}
	
	public int getFirstResult(){
		return pageNo==1?0:(pageNo-1)*pageSize;
	}
    
	public int getTotalPage(){
		if(totalRecords%pageSize==0){
			totalPage = totalRecords/pageSize;
		}else{
			totalPage = totalRecords/pageSize+1;
		}
		return totalPage;
	}
	
	
}
