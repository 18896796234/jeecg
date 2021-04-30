package com.jeecg.finance.service.impl;
import com.jeecg.finance.service.FAccountServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.finance.entity.FAccountEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("fAccountService")
@Transactional
public class FAccountServiceImpl extends CommonServiceImpl implements FAccountServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FAccountEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FAccountEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FAccountEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}