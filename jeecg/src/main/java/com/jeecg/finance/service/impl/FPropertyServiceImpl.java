package com.jeecg.finance.service.impl;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.finance.entity.FPropertyEntity;
import com.jeecg.finance.service.FPropertyServiceI;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("fPropertyService")
@Transactional
public class FPropertyServiceImpl extends CommonServiceImpl implements FPropertyServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FPropertyEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FPropertyEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FPropertyEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}