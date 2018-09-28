<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/all.css" />
<link rel="stylesheet" href="./css/input.css" />
<link rel="stylesheet" href="./css/funasshi-gun.css" />
<title>ログイン</title>

<script>
function goLoginAction(){
	document.getElementById("form").action="LoginAction";
}
function goCreateUserAction(){
	document.getElementById("form").action="CreateUserAction";
}
function goResetPasswordAction(){
	document.getElementById("form").action="ResetPasswordAction";
}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>ログイン画面</h1>
<div id="contents">

<div id="errorbox">


	<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.loginIdErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.passwordErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.passwordErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.loginErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.loginErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
</div>


	<s:form id="form" action="LoginAction" class="form">
	<table class="vertical-list-table">
		<tr>
			<th scope="row"><s:label value="ユーザーID:"/></th>
			<s:if test="#session.savedLoginId==true">
			<td><s:textfield name="loginId" class="txt" size="50" placeholder="ユーザーID" value='%{#session.loginId}' autocomplete="new-password"/></td>
			</s:if>
			<s:else>
			<td><s:textfield name="loginId" class="txt" size="50" placeholder="ユーザーID" autocomplete="new-password"/></td>
			</s:else>
		</tr>
		<tr>
			<th scope="row"><s:label value="パスワード:"/></th>
			<td><s:password name="password" class="txt" size="50" placeholder="パスワード" autocomplete="new-password"/></td>
		</tr>
	</table>

	<div class="box">
		<s:if test="#session.savedLoginId==true">
			<s:checkbox name="savedLoginId" checked="checked"/>
		</s:if>
		<s:else>
			<s:checkbox name="savedLoginId"/>
		</s:else>
		<s:label value="ユーザーID保存"/><br>
	</div>

		<s:submit value=" ロ　グ　イ　ン " class="submit_btn"/>
	</s:form>

	<s:form action="CreateUserAction"><s:submit value="新規ユーザー登録" class="submit_btn"/></s:form>

	<s:form action="ResetPasswordAction"><s:submit value="パスワード再設定" class="submit_btn"/></s:form>
</div>

</body>
</html>