package com.ting.finance.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.ting.common.util.DateUtilPJC;
import com.ting.finance.entity.FCapitalFlowEntity;
import com.ting.finance.service.FCapitalFlowServiceI;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jsoup.helper.DataUtil;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 资金流水
 * @author onlineGenerator
 * @date 2018-10-30 10:42:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fCapitalFlowController")
public class FCapitalFlowController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FCapitalFlowController.class);

	@Autowired
	private FCapitalFlowServiceI fCapitalFlowService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 资金流水列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/ting/finance/fCapitalFlowList");
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
	public void datagrid(FCapitalFlowEntity fCapitalFlow,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FCapitalFlowEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fCapitalFlow, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.fCapitalFlowService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除资金流水
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FCapitalFlowEntity fCapitalFlow, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fCapitalFlow = systemService.getEntity(FCapitalFlowEntity.class, fCapitalFlow.getId());
		message = "资金流水删除成功";
		try{
			fCapitalFlowService.delete(fCapitalFlow);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资金流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资金流水
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金流水删除成功";
		try{
			for(String id:ids.split(",")){
				FCapitalFlowEntity fCapitalFlow = systemService.getEntity(FCapitalFlowEntity.class, 
				id
				);
				fCapitalFlowService.delete(fCapitalFlow);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资金流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资金流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FCapitalFlowEntity fCapitalFlow, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金流水添加成功";
		try{
			fCapitalFlowService.save(fCapitalFlow);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资金流水添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新资金流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FCapitalFlowEntity fCapitalFlow, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金流水更新成功";
		FCapitalFlowEntity t = fCapitalFlowService.get(FCapitalFlowEntity.class, fCapitalFlow.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(fCapitalFlow, t);
			fCapitalFlowService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资金流水更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 资金流水新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FCapitalFlowEntity fCapitalFlow, HttpServletRequest req) {
		String day = DateUtilPJC.getNow("yyyyMMdd");
		req.setAttribute("day", day);
		if (StringUtil.isNotEmpty(fCapitalFlow.getId())) {
			fCapitalFlow = fCapitalFlowService.getEntity(FCapitalFlowEntity.class, fCapitalFlow.getId());
			req.setAttribute("fCapitalFlow", fCapitalFlow);
		}
		return new ModelAndView("com/ting/finance/fCapitalFlow-add");
	}
	/**
	 * 资金流水编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FCapitalFlowEntity fCapitalFlow, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fCapitalFlow.getId())) {
			fCapitalFlow = fCapitalFlowService.getEntity(FCapitalFlowEntity.class, fCapitalFlow.getId());
			req.setAttribute("fCapitalFlow", fCapitalFlow);
		}
		return new ModelAndView("com/ting/finance/fCapitalFlow-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","fCapitalFlowController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FCapitalFlowEntity fCapitalFlow,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FCapitalFlowEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fCapitalFlow, request.getParameterMap());
		List<FCapitalFlowEntity> fCapitalFlows = this.fCapitalFlowService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资金流水");
		modelMap.put(NormalExcelConstants.CLASS,FCapitalFlowEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资金流水列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,fCapitalFlows);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FCapitalFlowEntity fCapitalFlow,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资金流水");
    	modelMap.put(NormalExcelConstants.CLASS,FCapitalFlowEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资金流水列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FCapitalFlowEntity> listFCapitalFlowEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FCapitalFlowEntity.class,params);
				for (FCapitalFlowEntity fCapitalFlow : listFCapitalFlowEntitys) {
					fCapitalFlowService.save(fCapitalFlow);
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
