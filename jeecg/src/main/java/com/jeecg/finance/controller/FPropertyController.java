package com.jeecg.finance.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.finance.common.util.StringUtilPJC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.jeecg.finance.common.util.DateUtilPJC;
import com.jeecg.finance.entity.FPropertyEntity;
import com.jeecg.finance.service.FPropertyServiceI;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 财产
 * @author onlineGenerator
 * @date 2018-10-30 10:33:21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fPropertyController")
public class FPropertyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FPropertyController.class);

	@Autowired
	private FPropertyServiceI fPropertyService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 财产列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/finance/fPropertyList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FPropertyEntity fProperty,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FPropertyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fProperty, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.fPropertyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除财产
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FPropertyEntity fProperty, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fProperty = systemService.getEntity(FPropertyEntity.class, fProperty.getId());
		message = "财产删除成功";
		try{
			fPropertyService.delete(fProperty);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "财产删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除财产
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财产删除成功";
		try{
			for(String id:ids.split(",")){
				FPropertyEntity fProperty = systemService.getEntity(FPropertyEntity.class, 
				id
				);
				fPropertyService.delete(fProperty);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "财产删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加财产
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FPropertyEntity fProperty, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财产添加成功";
		try{
			fPropertyService.save(fProperty);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "财产添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新财产
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FPropertyEntity fProperty, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财产更新成功";
		try {
			if (StringUtilPJC.isNotNull(fProperty.getId())) {
				FPropertyEntity t = fPropertyService.get(FPropertyEntity.class, fProperty.getId());
				MyBeanUtils.copyBeanNotNull2Bean(fProperty, t);
				fPropertyService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} else {
				fPropertyService.save(fProperty);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "财产更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 财产新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FPropertyEntity fProperty, HttpServletRequest req) {
		String month = DateUtilPJC.getNow("YYYYMM");
		fProperty.setMonth(month);
		req.setAttribute("fProperty", fProperty);
		return new ModelAndView("com/jeecg/finance/fProperty-update");
	}
	/**
	 * 财产编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FPropertyEntity fProperty, HttpServletRequest req) {
		String month = DateUtilPJC.getNow("YYYYMM");
		req.setAttribute("month", month);
		if (StringUtil.isNotEmpty(fProperty.getId())) {
			fProperty = fPropertyService.getEntity(FPropertyEntity.class, fProperty.getId());
			req.setAttribute("fProperty", fProperty);
		}
		return new ModelAndView("com/jeecg/finance/fProperty-update");
	}

	/**
	 * 财产编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goCopy")
	public ModelAndView goCopy(FPropertyEntity fProperty, HttpServletRequest req) {
		String month = DateUtilPJC.getNow("YYYYMM");
		if (StringUtil.isNotEmpty(fProperty.getId())) {
			fProperty = fPropertyService.getEntity(FPropertyEntity.class, fProperty.getId());
			fProperty.setId("");
			fProperty.setMonth(month);
			req.setAttribute("fProperty", fProperty);
		}
		return new ModelAndView("com/jeecg/finance/fProperty-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","fPropertyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FPropertyEntity fProperty,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FPropertyEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fProperty, request.getParameterMap());
		List<FPropertyEntity> fPropertys = this.fPropertyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"财产");
		modelMap.put(NormalExcelConstants.CLASS,FPropertyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("财产列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,fPropertys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FPropertyEntity fProperty,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"财产");
    	modelMap.put(NormalExcelConstants.CLASS,FPropertyEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("财产列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<FPropertyEntity> listFPropertyEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FPropertyEntity.class,params);
				for (FPropertyEntity fProperty : listFPropertyEntitys) {
					fPropertyService.save(fProperty);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
