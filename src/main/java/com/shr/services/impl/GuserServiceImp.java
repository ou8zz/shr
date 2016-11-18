package com.shr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shr.model.Guser;
import com.shr.services.GuserService;
import com.utils.Paramer;

@Repository(value="guserService")
@Scope(value="prototype")
@Transactional(propagation=Propagation.REQUIRED)
public class GuserServiceImp implements GuserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void addGuser(Guser u) {
		sqlSession.insert("guser.addGuser", u);
	}

	@Override
	public void delGuser(Integer[] id) {
		List<Integer> list = new ArrayList<Integer>();
		for(Integer i : id) {
			list.add(i);
		}
		sqlSession.delete("guser.delGuser", list);
	}

	@Override
	public void editGuser(Guser u) {
		sqlSession.update("guser.editGuser", u);
	}
	
	@Override
	public void editUserUgroup(Guser u) {
		sqlSession.delete("guser.delUserGroupByUid", u.getId());
		sqlSession.insert("guser.addUserGroupByUid", u);
	}

	@Override
	public Paramer getGusers(Paramer p) {
		Page<Object> startPage = PageHelper.startPage(p.getPageNum(), p.getPageSize());
		startPage = (Page<Object>) sqlSession.selectList("guser.getGuserDetil", p.getOb());
		p.setPage(startPage);
		return p;
	}
	
	@Override
	public Guser getGuser(Guser u) {
		Guser queryForList = sqlSession.selectOne("guser.getGusers", u);
		return queryForList;
	}

}
