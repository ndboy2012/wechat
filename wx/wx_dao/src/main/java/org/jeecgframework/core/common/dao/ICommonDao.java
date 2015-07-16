package org.jeecgframework.core.common.dao;

import java.util.List;

/**
 * �Լ���չ��Dao����
 * @author Administrator
 *
 */
public interface ICommonDao extends IGenericBaseCommonDao {
    
	public List executeQuery(String hql, Object[] parameters) throws Exception;
	/* ��ȡһ�������Ķ��� */
	public Object queryUnique(String hql, Object[] parameters) throws Exception;

	public int executeUpdate(String hql, Object[] parameters) throws Exception;
}
