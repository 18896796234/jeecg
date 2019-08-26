package com.ting.finance.service.impl;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ting.finance.entity.FCapitalFlowEntity;
import com.ting.finance.service.FCapitalFlowServiceI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

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