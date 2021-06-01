package com.jeecg.finance.controller;
import com.jeecg.finance.common.util.DateUtilPJC;
import com.jeecg.finance.entity.FDiaryEntity;
import com.jeecg.finance.service.FDiaryServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

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
 * @Description: 日记
 * @author onlineGenerator
 * @date 2019-04-10 12:03:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fDiaryController")
public class FDiaryController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FDiaryController.class);

	@Autowired
	private FDiaryServiceI fDiaryService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 日记列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/finance/fDiaryList");
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
	public void datagrid(FDiaryEntity fDiary,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FDiaryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fDiary, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.fDiaryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除日记
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FDiaryEntity fDiary, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fDiary = systemService.getEntity(FDiaryEntity.class, fDiary.getId());
		message = "日记删除成功";
		try{
			fDiaryService.delete(fDiary);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "日记删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除日记
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "日记删除成功";
		try{
			for(String id:ids.split(",")){
				FDiaryEntity fDiary = systemService.getEntity(FDiaryEntity.class, 
				id
				);
				fDiaryService.delete(fDiary);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "日记删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加日记
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FDiaryEntity fDiary, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "日记添加成功";
		try{
			fDiaryService.save(fDiary);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "日记添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新日记
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FDiaryEntity fDiary, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "日记更新成功";
		FDiaryEntity t = fDiaryService.get(FDiaryEntity.class, fDiary.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(fDiary, t);
			fDiaryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "日记更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 日记新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FDiaryEntity fDiary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fDiary.getId())) {
			fDiary = fDiaryService.getEntity(FDiaryEntity.class, fDiary.getId());
			String date = DateUtilPJC.getNow("yyyy-MM-dd");
			fDiary.setCreateDate(new Date());
			req.setAttribute("fDiary", fDiary);
		}
		String dateString = DateUtilPJC.getNow("yyyy-MM-dd");
		req.setAttribute("dateString", dateString);
		return new ModelAndView("com/jeecg/finance/fDiary-add");
	}
	/**
	 * 日记编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FDiaryEntity fDiary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fDiary.getId())) {
			fDiary = fDiaryService.getEntity(FDiaryEntity.class, fDiary.getId());
			req.setAttribute("fDiary", fDiary);
		}
		return new ModelAndView("com/jeecg/finance/fDiary-update");
	}
	
	/**
	 * 日记编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goLook")
	public ModelAndView goLook(FDiaryEntity fDiary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fDiary.getId())) {
			fDiary = fDiaryService.getEntity(FDiaryEntity.class, fDiary.getId());
			req.setAttribute("fDiary", fDiary);
		}
		return new ModelAndView("com/jeecg/finance/fDiary-look");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","fDiaryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FDiaryEntity fDiary,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FDiaryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fDiary, request.getParameterMap());
		List<FDiaryEntity> fDiarys = this.fDiaryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"日记");
		modelMap.put(NormalExcelConstants.CLASS,FDiaryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("日记列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,fDiarys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FDiaryEntity fDiary,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"日记");
    	modelMap.put(NormalExcelConstants.CLASS,FDiaryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("日记列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FDiaryEntity> listFDiaryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FDiaryEntity.class,params);
				for (FDiaryEntity fDiary : listFDiaryEntitys) {
					fDiaryService.save(fDiary);
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
