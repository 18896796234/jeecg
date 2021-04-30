package com.jeecg.finance.controller;
import com.jeecg.finance.entity.FAccountEntity;
import com.jeecg.finance.service.FAccountServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.util.*;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 账户管理
 * @author onlineGenerator
 * @date 2021-04-30 16:49:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fAccountController")
public class FAccountController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FAccountController.class);

	@Autowired
	private FAccountServiceI fAccountService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 账户管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/finance/fAccountList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FAccountEntity fAccount,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FAccountEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fAccount, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.fAccountService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除账户管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FAccountEntity fAccount, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fAccount = systemService.getEntity(FAccountEntity.class, fAccount.getId());
		message = "账户管理删除成功";
		try{
			fAccountService.delete(fAccount);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "账户管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除账户管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "账户管理删除成功";
		try{
			for(String id:ids.split(",")){
				FAccountEntity fAccount = systemService.getEntity(FAccountEntity.class, 
				id
				);
				fAccountService.delete(fAccount);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "账户管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加账户管理
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FAccountEntity fAccount, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "账户管理添加成功";
		try{
			String plaintext = fAccount.getAccountPsw();
			String password = fAccount.getAccountNumber();
			String plaintextNew = PasswordUtil.encrypt(plaintext,password, PasswordUtil.getStaticSalt());
			fAccount.setAccountPsw(plaintextNew);
			fAccountService.save(fAccount);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "账户管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新账户管理
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FAccountEntity fAccount, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "账户管理更新成功";
		FAccountEntity t = fAccountService.get(FAccountEntity.class, fAccount.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(fAccount, t);

			String plaintext = fAccount.getAccountPsw();
			String password = fAccount.getAccountNumber();
			String plaintextNew = PasswordUtil.encrypt(plaintext,password, PasswordUtil.getStaticSalt());
			t.setAccountPsw(plaintextNew);

			fAccountService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "账户管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 账户管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FAccountEntity fAccount, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fAccount.getId())) {
			fAccount = fAccountService.getEntity(FAccountEntity.class, fAccount.getId());
			req.setAttribute("fAccount", fAccount);
		}
		return new ModelAndView("com/jeecg/finance/fAccount-add");
	}
	/**
	 * 账户管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FAccountEntity fAccount, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fAccount.getId())) {
			fAccount = fAccountService.getEntity(FAccountEntity.class, fAccount.getId());

			String ciphertext = fAccount.getAccountPsw();
			String password = fAccount.getAccountNumber();
			String plaintextNew = PasswordUtil.decrypt(ciphertext, password,PasswordUtil.getStaticSalt());
			fAccount.setAccountPsw(plaintextNew);

			req.setAttribute("fAccount", fAccount);
		}
		return new ModelAndView("com/jeecg/finance/fAccount-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","fAccountController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FAccountEntity fAccount,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FAccountEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fAccount, request.getParameterMap());
		List<FAccountEntity> fAccounts = this.fAccountService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"账户管理");
		modelMap.put(NormalExcelConstants.CLASS,FAccountEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("账户管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,fAccounts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FAccountEntity fAccount,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"账户管理");
    	modelMap.put(NormalExcelConstants.CLASS,FAccountEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("账户管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FAccountEntity> listFAccountEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FAccountEntity.class,params);
				for (FAccountEntity fAccount : listFAccountEntitys) {
					fAccountService.save(fAccount);
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
