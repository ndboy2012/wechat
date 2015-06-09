package com.weimeng.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimeng.dao.CommonDao;
import com.weimeng.dao.CommonToolsDao;

@Transactional
@Service("commonToolsImpl")
public class CommonToolsImpl extends CommonDao implements CommonToolsDao {

	@Override
	public void saveObj(Object object) throws Exception {
          getCurrentSession().save(object);
	}

	@Override
	public List executeQuery(String sql, Object[] parameters) throws Exception {
		List list = null;
		Query query = getCurrentSession().createQuery(sql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		list = query.list();
		return list;
	}

	@Override
	public Object queryUnique(String sql, Object[] parameters) throws Exception {
		Query query = getCurrentSession().createQuery(sql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return query.uniqueResult();
	}

	@Override
	public int executeUpdate(String sql, Object[] parameters) throws Exception {
		Query query = getCurrentSession().createSQLQuery(sql);
		if(parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public void updateObject(Object obj) throws Exception {
		  getCurrentSession().update(obj);
	}

}
