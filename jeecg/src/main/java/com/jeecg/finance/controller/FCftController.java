package com.jeecg.finance.controller;
import com.jeecg.finance.common.util.DateUtilPJC;
import com.jeecg.finance.common.util.StringUtilPJC;
import com.jeecg.finance.entity.FCftEntity;
import com.jeecg.finance.service.FCftServiceI;

import java.util.ArrayList;
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
 * @Description: 理财
 * @author onlineGenerator
 * @date 2021-05-31 15:57:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fCftController")
public class FCftController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FCftController.class);

	@Autowired
	private FCftServiceI fCftService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 理财列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/finance/fCftList");
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
	public void datagrid(FCftEntity fCft,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FCftEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fCft, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.fCftService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除理财
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FCftEntity fCft, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fCft = systemService.getEntity(FCftEntity.class, fCft.getId());
		message = "理财删除成功";
		try{
			fCftService.delete(fCft);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "理财删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除理财
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "理财删除成功";
		try{
			for(String id:ids.split(",")){
				FCftEntity fCft = systemService.getEntity(FCftEntity.class, 
				id
				);
				fCftService.delete(fCft);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "理财删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加理财
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FCftEntity fCft, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "理财添加成功";
		try{
			fCftService.save(fCft);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "理财添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新理财
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FCftEntity fCft, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "理财更新成功";

		try {
			if (StringUtilPJC.isNotNull(fCft.getId())) {
				FCftEntity t = fCftService.get(FCftEntity.class, fCft.getId());
				MyBeanUtils.copyBeanNotNull2Bean(fCft, t);
				fCftService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} else {
				fCftService.save(fCft);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "理财更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 理财新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FCftEntity fCft, HttpServletRequest req) {
		String month = DateUtilPJC.getNow("YYYYMM");
		fCft.setMonth(month);
		req.setAttribute("fCft", fCft);
		return new ModelAndView("com/jeecg/finance/fCft-update");
	}
	/**
	 * 理财编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FCftEntity fCft, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fCft.getId())) {
			fCft = fCftService.getEntity(FCftEntity.class, fCft.getId());
			req.setAttribute("fCft", fCft);
		}
		return new ModelAndView("com/jeecg/finance/fCft-update");
	}

	/**
	 * 理财编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goCopy")
	public ModelAndView goCopy(FCftEntity fCft, HttpServletRequest req) {
		String month = DateUtilPJC.getNow("YYYYMM");
		if (StringUtil.isNotEmpty(fCft.getId())) {
			fCft = fCftService.getEntity(FCftEntity.class, fCft.getId());
			fCft.setId("");
			fCft.setMonth(month);
			req.setAttribute("fCft", fCft);
		}
		return new ModelAndView("com/jeecg/finance/fCft-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","fCftController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FCftEntity fCft,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FCftEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fCft, request.getParameterMap());
		List<FCftEntity> fCfts = this.fCftService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"理财");
		modelMap.put(NormalExcelConstants.CLASS,FCftEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("理财列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,fCfts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FCftEntity fCft,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"理财");
    	modelMap.put(NormalExcelConstants.CLASS,FCftEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("理财列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FCftEntity> listFCftEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FCftEntity.class,params);
				for (FCftEntity fCft : listFCftEntitys) {
					fCftService.save(fCft);
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
