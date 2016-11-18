package com.shr.services.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shr.model.Logs;
import com.shr.services.LogsService;
import com.utils.Paramer;

@Repository(value="logsService")
@Scope(value="prototype")
@Transactional(propagation=Propagation.REQUIRED)
public class LogsServiceImp implements LogsService {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 记录日志
	 * @param log
	 */
	@Override
	public void addLog(Logs log) {
		sqlSession.insert("logs.addLogs", log);
	}

	/**
	 * 查询日志列表
	 * @param p
	 */
	@Override
	public Paramer getLogs(Paramer p) {
		Page<Object> startPage = PageHelper.startPage(p.getPageNum(), p.getPageSize());
		startPage = (Page<Object>) sqlSession.selectList("logs.getLogs", p.getOb());
		p.setPage(startPage);
		return p;
	}

	/***
	 * 查询日志明细
	 * @param log
	 */
	@Override
	public Logs getLog(Logs log) {
		Logs ob = sqlSession.selectOne("logs.getLogs", log);
		return ob;
	}

}
