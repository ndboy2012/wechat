package com.weimeng.util;

import java.util.List;
import java.util.Map;

/*
 * 抽奖工具
 * 需要参数：预计抽奖人数totalNum,
 *       奖品等级数：prizeLevel
 *       每一等级中奖品的数量:prizeLevelNum[prizeLevel],
 */
public class Sweepstakes {
     
	        private static int totalNum;
	        private static int prizeLevel;
	        private static int[] prizeLevelCount = new int[20];
	        
	        public static int getLottyNum() {
	        	int num = 0;                           //所中奖等级
	        	int j=0;
	        	 String path = "C://aqMobile/prizeDetilConfig.xml";
	        	 try {
					Map<String,Object> map = ParseXml.parse(path, null);
					totalNum = Integer.parseInt((String) map.get("totalNum"));
					prizeLevel = Integer.parseInt((String)map.get("prizeLevel"));
					
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) map.get("prizeInfo");
					for(int i=1;i<list.size();i+=3) {	
						prizeLevelCount[j] = Integer.parseInt(list.get(i));	
						j++;			
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	
	        	int randNum = GenerateRandCode.generateNum(totalNum);    //产生一个随机数	
	        	System.out.println(randNum);
	        	int temp = 0;
	        	boolean step = true;
	        	while(num<prizeLevel&&step) {
	        		if(randNum>=temp&&randNum<temp+prizeLevelCount[num]) {
	        		        step = false;
	        		} else {	        			
	        			temp = temp+prizeLevelCount[num];	        			
	        			num++;
	        		}
	        	}        	 
	        	return num+1;
	        }	        
}
