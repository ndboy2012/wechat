package org.yelp.service.seckill;

import org.yelp.entity.seckill.SeckillPhoneInfo;
import org.yelp.entity.seckill.SeckillUserInfo;

public interface WechatSeckillServiceI {
     
	 public boolean hasParticipate(String openId) ;
	  
	  public int isRightTime(int day,int hourBef,int hourAft) ;
	  
	  public boolean hasSoldOut(int hour) ;
	  
	  public SeckillPhoneInfo getPhoneInfo(int hour) ;
	  
	  public void saveSeckillUserInfo(SeckillUserInfo user,int hour) ;
	  
	  public String getTelephoneByOpenid(String openId) ;
	  
	  public SeckillUserInfo getUserInfoByOpenid(String openid) ; 
	  
	  public boolean hasParticipateGroup(String openid) ;
}
