package com.jeecg.finance.service;
import com.jeecg.finance.entity.FDiaryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FDiaryServiceI extends CommonService{
	
 	public void delete(FDiaryEntity entity) throws Exception;
 	
 	public Serializable save(FDiaryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FDiaryEntity entity) throws Exception;
 	
}
