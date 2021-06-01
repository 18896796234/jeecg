package com.jeecg.finance.service.impl;
import com.jeecg.finance.service.FDiaryServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.finance.entity.FDiaryEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("fDiaryService")
@Transactional
public class FDiaryServiceImpl extends CommonServiceImpl implements FDiaryServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FDiaryEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FDiaryEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FDiaryEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}