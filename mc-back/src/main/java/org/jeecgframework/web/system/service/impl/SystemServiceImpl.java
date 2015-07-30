package org.jeecgframework.web.system.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.entity.dictionary.DictionaryBean;
import com.ddmt.entity.dictionary.DictionaryEntity;

@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService {
	
	private Logger logger = Logger.getLogger(SystemServiceImpl.class);
	/**
	 * 添加日志
	 */
	public void addLog(String logcontent, Short loglevel, Short operatetype) {
	    logger.info(logcontent);
	}
	public void initAllDict() {
		if (logger.isDebugEnabled()) {
			logger.debug("--------初始化字典表--------");
		}
		List<DictionaryEntity> dictList = this.commonDao
				.loadAll(DictionaryEntity.class);
		List<String> categoryList = new ArrayList<String>();
		if (MapUtils.isNotEmpty(DictionaryBean.keyDict)) {
			DictionaryBean.keyDict.clear();
			DictionaryBean.allDicts.clear();
		}
		for (DictionaryEntity dict : dictList) {
			DictionaryBean.keyDict.put(dict.getDkey(), dict);
			if (!categoryList.contains(dict.getCategoryCode())) {
				categoryList.add(dict.getCategoryCode());
			}
		}
		for (String category : categoryList) {
			List<DictionaryEntity> sDictList = new ArrayList<DictionaryEntity>();
			for (DictionaryEntity dict : dictList) {
				System.out.println(dict.getName()+","+dict.getDkey()+","+dict.getCategoryCode());
				if (dict.getCategoryCode().equals(category)) {
					sDictList.add(dict);
				}
			}
			DictionaryBean.allDicts.put(category, sDictList);
		}
	}
}
