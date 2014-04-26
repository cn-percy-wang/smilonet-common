// /////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2012 ikamobile.
// All rights reserved
// /////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.dao.mybatis;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * DAO层mybatis实现
 * 
 * @author 王龙 email:wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public abstract class BaseDao extends SqlSessionDaoSupport {

	@Override
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 查询所有符合参数结果
	 * @param selectRequest
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List select(SelectRequest selectRequest) {
		if (log.isDebugEnabled()) {
			log.debug("根据参数:" + selectRequest.getParam() + ",调用:" + selectRequest.getExecutedStatementName());
		}
		try {
			return getSqlSession().selectList(selectRequest.getExecutedStatementName(), selectRequest.getParam());
		} catch (Exception e) {
			log.error("根据参数:" + selectRequest.getParam() + ",调用:" + selectRequest.getExecutedStatementName() + ",发生错误", e);
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * 查询所有符合参数的第一个结果
	 * @param selectRequest
	 * @return
	 */
	public Object selectOne(SelectRequest selectRequest) {
		@SuppressWarnings("rawtypes")
		List result = select(selectRequest);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询所有符合参数的分页结果
	 * @param executeRequest
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page selectPage(SelectPageRequest selectPageRequest) {
		try {
			selectPageRequest.setAsCountMode();
			if (log.isDebugEnabled()) {
				log.debug("根据参数:" + selectPageRequest.getParam() + ",调用:" + selectPageRequest.getExecutedStatementName());
			}
			Long totalCount = getSqlSession().selectOne(selectPageRequest.getExecutedStatementName(), selectPageRequest.getParam());
			selectPageRequest.setAsResultMode();
			if (log.isDebugEnabled()) {
				log.debug("根据参数:" + selectPageRequest.getParam() + ",调用:" + selectPageRequest.getExecutedStatementName());
			}
			return new PageImpl(getSqlSession().selectList(selectPageRequest.getExecutedStatementName(), selectPageRequest.getParam()), selectPageRequest.getParam().getPager(), totalCount);
		} catch (Exception e) {
			log.error("根据参数:" + selectPageRequest.getParam() + ",调用:" + selectPageRequest.getExecutedStatementName() + ",发生错误", e);
			return new PageImpl(Collections.EMPTY_LIST, selectPageRequest.getParam().getPager(), 0);
		}
	}

	/**
	 * 删除数据
	 * @param executeRequest
	 */
	public void deleteByCondition(ExecuteRequest<Object> executeRequest) {
		if (log.isDebugEnabled()) {
			log.debug("根据参数:" + executeRequest.getParam() + ",调用:" + executeRequest.getExecutedStatementName());
		}
		try {
			getSqlSession().delete(executeRequest.getExecutedStatementName(), executeRequest.getParam());
		} catch (Exception e) {
			log.error("根据参数:" + executeRequest.getParam() + ",调用:" + executeRequest.getExecutedStatementName() + ",发生错误", e);
		}
	}

	/**
	 * 插入数据
	 * @param executeRequest
	 * @return 成功插入记录数量
	 */
	public int insert(ExecuteRequest<Object> executeRequest) {
		if (log.isDebugEnabled()) {
			log.debug("根据参数:" + executeRequest.getParam() + ",调用:" + executeRequest.getExecutedStatementName());
		}
		try {
			return getSqlSession().insert(executeRequest.getExecutedStatementName(), executeRequest.getParam());
		} catch (Exception e) {
			log.error("根据参数:" + executeRequest.getParam() + ",调用:" + executeRequest.getExecutedStatementName() + ",发生错误", e);
			return 0;
		}
	}

    /**
     * 修改数据
     * @param executeRequest
     * @return
     */
    public int update(ExecuteRequest<UpdateParam> executeRequest) {
        if (log.isDebugEnabled()) {
			log.debug("根据参数:" + executeRequest.getParam() + ",调用:" + executeRequest.getExecutedStatementName());
        }
    	try {
    		return getSqlSession().update(executeRequest.getExecutedStatementName(), executeRequest.getParam());
	    } catch (Exception e) {
	    	log.error("根据参数:" + executeRequest.getParam() + ",调用:" + executeRequest.getExecutedStatementName() + ",发生错误", e);
			return 0;
	    }
    }
}
