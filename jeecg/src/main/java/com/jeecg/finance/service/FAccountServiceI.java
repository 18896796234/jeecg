package com.jeecg.finance.service;
import com.jeecg.finance.entity.FAccountEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FAccountServiceI extends CommonService{
	
 	public void delete(FAccountEntity entity) throws Exception;
 	
 	public Serializable save(FAccountEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FAccountEntity entity) throws Exception;
 	
}
