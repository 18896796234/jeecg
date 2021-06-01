package com.jeecg.finance.service;
import org.jeecgframework.core.common.service.CommonService;

import com.jeecg.finance.entity.FCapitalFlowEntity;

import java.io.Serializable;

public interface FCapitalFlowServiceI extends CommonService{
	
 	public void delete(FCapitalFlowEntity entity) throws Exception;
 	
 	public Serializable save(FCapitalFlowEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FCapitalFlowEntity entity) throws Exception;
 	
}
