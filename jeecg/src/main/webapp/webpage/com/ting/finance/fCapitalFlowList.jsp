<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="fCapitalFlowList" checkbox="false" pagination="true" fitColumns="true" title="资金流水" actionUrl="fCapitalFlowController.do?datagrid" idField="id" sortName="updateDate" sortOrder="desc" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true" queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="day"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amount"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="方向"  field="direction"  queryMode="single"  dictionary="f_dire"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="username"  queryMode="single"  dictionary="t_s_base_user,username,realname"  width="120"></t:dgCol>
   <t:dgCol title="流水类型"  field="flowType"  queryMode="single"  dictionary="f_type"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="fCapitalFlowController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="fCapitalFlowController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fCapitalFlowController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
<%--    <t:dgToolBar title="批量删除" icon="icon-remove" url="fCapitalFlowController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fCapitalFlowController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入"  icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出"  icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载"  icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'fCapitalFlowController.do?upload', "fCapitalFlowList");
}

//导出
function ExportXls() {
	JeecgExcelExport("fCapitalFlowController.do?exportXls","fCapitalFlowList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("fCapitalFlowController.do?exportXlsByT","fCapitalFlowList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

</script>
