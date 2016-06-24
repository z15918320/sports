package com.sportsexp.common.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sportsexp.common.dao.IBaseDao;
import com.sportsexp.common.service.BaseService;

@Service
@Scope("prototype")
public class BaseServiceImpl implements BaseService {
	
	@Resource
	protected IBaseDao baseDao;
	
}
