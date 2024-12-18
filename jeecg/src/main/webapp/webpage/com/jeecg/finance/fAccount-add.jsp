<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>账户管理</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="fAccountController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="accountNumber" class="col-sm-3 control-label">账号：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="accountNumber" name="accountNumber" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入账号"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="accountType" class="col-sm-3 control-label">账号类型：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:dictSelect field="accountType" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="account_tp"  hasLabel="false"  title="账号类型"></t:dictSelect>     
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="accountPsw" class="col-sm-3 control-label">账号密码：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="accountPsw" name="accountPsw" value='' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入密码"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="remark" class="col-sm-3 control-label">备注：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="remark" name="remark" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入备注"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="username" class="col-sm-3 control-label">用户：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:dictSelect field="username" type="list" extendJson="{class:'form-control input-sm'}"   dictTable="t_s_base_user" dictField="username" dictText="realname"  hasLabel="false"  title="用户"></t:dictSelect>     
				</div>
			</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
		
});
</script>
</body>
</html>