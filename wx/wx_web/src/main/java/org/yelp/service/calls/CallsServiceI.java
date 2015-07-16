package org.yelp.service.calls;

import java.util.List;

import org.yelp.entity.calls.WechatRecommCustomer;

public interface CallsServiceI {
     
	 /*判断用户是否在基本信息表中登记过了*/
	  public boolean isExitOpenidInBase(String open_id) throws Exception;	  
	  /*判断被推荐的号码是否在基本信息表中登记过了*/
	  public boolean isExitTelBase(String recommTel) throws Exception;
	  /*判断这个open_id是否是第一次登记*/
	  public boolean isExitOpenidInRecomm(String open_id) throws Exception;
	  /*通过open_id获取来获取电话号码*/
	  public String getTelephone(String open_id) throws Exception;
	  
	  public boolean isExitTelCalls(String recommTel) throws Exception;
	  
	  public boolean isExitOpenidCalls(String open_id) throws Exception;
	  /*通过电话号码来获取open_id*/
	  public String getOpenid(String telephone) throws Exception;
	  
	  public void saveObject(Object obj) throws Exception;
	  
	  public boolean isRepeateRecomm(String open_id,String telephone) throws Exception;
	  
	  public boolean updateAmmountByTel(String tele) throws Exception;
	  
	  public boolean updateAmmountByOpenId(String open_id) throws Exception;
	  
	  public boolean isFirstRecommend(String open_id) throws Exception;
	  
	  public int getAmmountByOpenId(String open_id) throws Exception;
	  
	  public int getAmmountByTel(String tel) throws Exception;
	  /*查询被推荐的次数*/
	  public int getRecommedNum(String tel) throws Exception;
	  
	  public List<WechatRecommCustomer> getAllRecommInfoByTel(String Tel) throws Exception;
	
} 
