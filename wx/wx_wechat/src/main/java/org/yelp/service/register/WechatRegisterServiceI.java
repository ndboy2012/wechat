package org.yelp.service.register;

public interface WechatRegisterServiceI {
      
	  /**
	   * 是否已经登记过了
	   * @param open_id
	   * @return
	   */
	  public boolean isRegister(String open_id);
}
