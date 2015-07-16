package org.jeecgframework.core.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.common.service.CommonServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonServiceImpl implements CommonServiceI {

	public ICommonDao commonDao = null;

	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
    
	@Transactional
	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	@Transactional
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	@Transactional
	public <T> void delete(T entity) {
		commonDao.delete(entity);

	}

	/**
	 * ɾ��ʵ�弯��
	 * 
	 * @param <T>
	 * @param entities
	 */
	@Transactional
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}

	/**
	 * ���ʵ�����ȡ����
	 */
	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}

	/**
	 * ���ʵ�����ȫ������
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
	public <T> List<T> getList(Class clas) {
		return commonDao.loadAll(clas);
	}

	/**
	 * ���ʵ�����ȡ����
	 */
	public <T> T getEntity(Class entityName, Serializable id) {
		return commonDao.getEntity(entityName, id);
	}

	/**
	 * ���ʵ����ƺ��ֶ���ƺ��ֶ�ֵ��ȡΨһ��¼
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 * �����Բ��Ҷ����б�.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	/**
	 * ����ȫ��ʵ��
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}

	public <T> T singleResult(String hql) {
		return commonDao.singleResult(hql);
	}

	/**
	 * ɾ��ʵ������IDɾ�����
	 * 
	 * @param <T>
	 * @param entities
	 */
	@Transactional
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	/**
	 * ����ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	@Transactional
	public <T> void updateEntitie(T pojo) {
		commonDao.updateEntitie(pojo);

	}

	/**
	 * ͨ��hql ��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql) {
		return commonDao.findByQueryString(hql);
	}

	/**
	 * ���sql����
	 * 
	 * @param query
	 * @return
	 */
	@Transactional
	public int updateBySqlString(String sql) {
		return commonDao.updateBySqlString(sql);
	}

	/**
	 * ���sql����List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query) {
		return commonDao.findListbySql(query);
	}

	/**
	 * ͨ�����Գƻ�ȡʵ�������
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		return commonDao.findByPropertyisOrder(entityClass, propertyName,
				value, isAsc);
	}


	@Override
	@Transactional
	public <T> void batchSave(List<T> entitys) {
		// TODO Auto-generated method stub
		this.commonDao.batchSave(entitys);
	}

	@Override
	public List executeQuery(String sql, Object[] parameters) throws Exception {
		return commonDao.executeQuery(sql, parameters);
	}

	@Override
	public Object queryUnique(String sql, Object[] parameters) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.queryUnique(sql, parameters);
	}

	@Override
	public int executeUpdate(String sql, Object[] parameters) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.executeUpdate(sql, parameters);
	}

}

