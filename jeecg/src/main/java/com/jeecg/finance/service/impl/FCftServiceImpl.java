package com.jeecg.finance.service.impl;
import com.jeecg.finance.service.FCftServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.finance.entity.FCftEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("fCftService")
@Transactional
public class FCftServiceImpl extends CommonServiceImpl implements FCftServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FCftEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FCftEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FCftEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}