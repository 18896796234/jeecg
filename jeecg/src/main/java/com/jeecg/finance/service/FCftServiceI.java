package com.jeecg.finance.service;
import com.jeecg.finance.entity.FCftEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FCftServiceI extends CommonService{
	
 	public void delete(FCftEntity entity) throws Exception;
 	
 	public Serializable save(FCftEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FCftEntity entity) throws Exception;
 	
}
