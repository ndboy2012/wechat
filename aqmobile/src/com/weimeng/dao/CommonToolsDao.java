package com.weimeng.dao;

import java.util.List;

/*
 * 公共基础dao工具，用于提供所有的增删改查
 */
public interface CommonToolsDao {
     
	    public void saveObj(Object object) throws Exception;  
	    
	    public List executeQuery(String sql,Object[] parameters) throws Exception; 
	    /* 获取一个单独的对象 */
	    public Object queryUnique(String sql,Object[] parameters) throws Exception;
	    
	    public int executeUpdate(String sql,Object[] parameters) throws Exception;
	    
	    public void updateObject(Object obj) throws Exception;
}
