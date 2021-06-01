package com.jeecg.finance.service.impl;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.finance.entity.FCapitalFlowEntity;
import com.jeecg.finance.service.FCapitalFlowServiceI;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("fCapitalFlowService")
@Transactional
public class FCapitalFlowServiceImpl extends CommonServiceImpl implements FCapitalFlowServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FCapitalFlowEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FCapitalFlowEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FCapitalFlowEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}