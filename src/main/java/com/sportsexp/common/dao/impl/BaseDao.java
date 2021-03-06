package com.sportsexp.common.dao.impl;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.sportsexp.common.dao.IBaseDao;
import com.sportsexp.common.exception.DaoException;
/**
 * <p>
 * BaseDao.java
 * </p>
 * <p>
 * Title: Dao操作的基类 基于泛型查询
 * </p>
 * <p>
 * Description: Detail Description
 * </p>
 * <p>
 * Department: 技术部
 * </p>
 * <p>
 * Company: 湖南金诚信通信股份有限公司
 * </p>
 * <p>
 * Copyright (c)@2009-2013  
 * </p>
 * @author 
 * @since 2013年8月27日 下午8:06:37
 */
@Repository
@Scope("prototype")
public class BaseDao implements IBaseDao{
 
	private static final Logger LOGGER = Logger.getLogger(BaseDao.class);
	
	
	// @PostConstruct用于配置初始化方法与XML中的init-method作用一致
	@PostConstruct
	public void baseDaoInit() {
		LOGGER.info("baseDaoInit() -- >> BaseDao方法初始化！");
	}
	// @PreDestroy用于配置销毁方法与XML中的destroy-method作用一致
	@PreDestroy
	public void baseDaoDestroy() {
		LOGGER.info("baseDaoDestroy() -- >> BaseDao方法销毁！");
	}
	
	@Resource private SqlSessionTemplate sqlSession;
	
	/**
	 * 新增操作
	 * @Title: save
	 * @Description: TODO
	 * @param @param key
	 * @param @param params 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	@Override
	public int save(String key, Object params) throws DaoException {
		return sqlSession.insert(key, params);
	}
	/**
	 * 修改操作
	 * @Title: update
	 * @Description: TODO
	 * @param @param key
	 * @param @param params 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	@Override
	public int update(String key, Object params) throws DaoException {
		return sqlSession.update(key, params);
	}
	/**
	 * 删除操作
	 * @Title: delete
	 * @Description: TODO
	 * @param @param key
	 * @param @param params 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	@Override
	public int delete(String key, Object params) throws DaoException {
		return sqlSession.delete(key, params);
	}
	/**
	 * 通过id删除
	 * @Title: delete
	 * @Description: TODO
	 * @param @param key
	 * @param @param id 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	@Override
	public int delete(String key, Serializable id) throws DaoException {
		return sqlSession.delete(key, id);
	}
	/**
	 * 获取对象
	 * @Title: get
	 * @Description: TODO
	 * @param @param key
	 * @param @param id
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @throws
	 */
	@Override
	public <T> T get(String key, Serializable id) throws DaoException {
		return sqlSession.selectOne(key, id);
	}
	/**
	 * 获取对象
	 * @Title: get
	 * @Description: TODO
	 * @param @param key
	 * @param @param params
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @throws
	 */
	@Override
	public <T> T get(String key, Object params) throws DaoException {
		return sqlSession.selectOne(key, params);
	}
	/**
	 * 获取对象列表
	 * @Title: getList
	 * @Description: TODO
	 * @param @param key
	 * @param @param params
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @throws
	 */
	@Override
	public <T> List<T> getList(String key, Object params) throws DaoException {
		return sqlSession.selectList(key, params);
	}
}
