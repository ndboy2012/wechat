package org.jeecgframework.core.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonDao extends GenericBaseCommonDao implements ICommonDao {
    
	@Override
	public List executeQuery(String sql, Object[] parameters) {
		List list = null;
		Query query = getSession().createQuery(sql);
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
		Query query = getSession().createQuery(sql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i,parameters[i]);
			}
		}
		return query.uniqueResult();
	}
	
	@Override
	@Transactional
	public int executeUpdate(String sql, Object[] parameters) throws Exception {
		Query query = getSession().createSQLQuery(sql);
		if(parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i,parameters[i]);
			}
		}
		return query.executeUpdate();
	}

}
