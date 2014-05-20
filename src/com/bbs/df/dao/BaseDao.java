package com.bbs.df.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbs.df.po.User;
import com.bbs.df.web.vo.ScrollPage;

public interface BaseDao {
   public List queryByPage(Class clazz,ScrollPage page);
   public List queryByPageOnCondition(String sql,ScrollPage page);
}
