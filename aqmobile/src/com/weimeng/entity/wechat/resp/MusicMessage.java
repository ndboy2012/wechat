package com.weimeng.entity.wechat.resp;

import com.weimeng.entity.wechat.Music;
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
