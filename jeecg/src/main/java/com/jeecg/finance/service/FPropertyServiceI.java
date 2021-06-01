package com.jeecg.finance.service;
import org.jeecgframework.core.common.service.CommonService;

import com.jeecg.finance.entity.FPropertyEntity;

import java.io.Serializable;

public interface FPropertyServiceI extends CommonService{
	
 	public void delete(FPropertyEntity entity) throws Exception;
 	
 	public Serializable save(FPropertyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FPropertyEntity entity) throws Exception;
 	
}
