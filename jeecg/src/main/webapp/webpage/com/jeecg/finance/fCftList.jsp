<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="fCftList" checkbox="false" pagination="true" fitColumns="true" title="理财" actionUrl="fCftController.do?datagrid" idField="id" sortName="createDate" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="平台"  field="Org"  query="true"  queryMode="single" dictionary="f_org" width="120"></t:dgCol>
   <t:dgCol title="理财项目"  field="project"  query="true"  queryMode="single" dictionary="f_project" width="120"></t:dgCol>
   <t:dgCol title="月份"  field="month"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="balance"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="变动金额"  field="flows"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="fCftController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="fCftController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fCftController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="复制" icon="icon-copy" url="fCftController.do?goCopy" funname="update"  width="768"></t:dgToolBar>
<%--   <t:dgToolBar title="批量删除"  icon="icon-remove" url="fCftController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fCftController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'fCftController.do?upload', "fCftList");
}

//导出
function ExportXls() {
	JeecgExcelExport("fCftController.do?exportXls","fCftList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("fCftController.do?exportXlsByT","fCftList");
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
