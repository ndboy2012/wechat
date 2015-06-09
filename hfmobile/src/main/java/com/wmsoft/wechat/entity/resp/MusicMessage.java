package com.wmsoft.wechat.entity.resp;

import com.wmsoft.wechat.entity.commom.Music;



public class MusicMessage extends BaseMessage{
   
	// 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}
