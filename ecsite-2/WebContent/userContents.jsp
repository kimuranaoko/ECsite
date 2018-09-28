<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Style-Type" content="text/css"/>
	<meta http-equiv="Content-Script-Type" content="text/jacascript"/>
	<meta http-equiv="imagetoolbar" content="no"/>
	<meta name="descriprion" content=""/>
	<meta name="keywords" content=""/>

	<title>UserContents画面</title>

	<style type="text/css">
		body{
			margin:0;
			padding:0;
			line-height:1.6;
			letter-spacing:1px;
			font-family:Verdana,Helvetica,sans-serif;
			font-size:12px;
			color:#333;
			background:#fff;
		}

		table{
			text-align:center;
			margin:0 auto;
		}

		#top{
			width:780px;
			margin:30px auto;
			border:1px solid #333;
		}

		#header{
			width:100%;
			height:80px;
			background-color:black;
		}

		#main{
		width:100%;
		height:500px;
		text-align:center;
		}

		#footer{
			width:100%;
			height:80px;
			background-color:black;
			clear:both;
		}

		#text-center{
			display:inline-block;
			text-align:center;
		}
	</style>
</head>
<body>
	<div id="header">
		<div id="pr">
		</div>
	</div>
	<div id="main">
		<div id="top">
		<p>UserContents</p>
		</div>
		<div>
			<s:if test="flg == 2 && editMessage == null">
				<s:form action="UserContentsAction">
				<h4>変更してください</h4>
				<input type="hidden" name="flg" value="3">
					<tr>
						<td>
							<label>ログインＩＤ：</label>
						</td>
						<td>
							<s:textfield type="text" name="loginId" value='%{#session.loginId}'/>
						</td>
					</tr>
					<tr>
						<td>
							<label>ログインＰＡＳＳ：</label>
						</td>
						<td>
							<s:textfield type="text" name="loginPass" value='%{#session.loginPass}'/>
						</td>
					</tr>
					<tr>
						<td>
							<label>ユーザー名：</label>
						</td>
						<td>
							<s:textfield type="text" name="userName" value='%{#session.userName}'/>
						</td>
					</tr>
				<s:submit value="更新"/>
				</s:form>
			</s:if>



			<s:elseif test="message == null && editMessage == null">
			<h3>★登録情報詳細★</h3>
			<table>
				<tr id="box">
					<td>
						<label>ＩＤ：</label>
					</td>
					<td>
						<s:property value="session.id" escape="false"/>
					</td>
				</tr>
				<tr id="box">
					<td>
						<label>ログインＩＤ：</label>
					</td>
					<td>
						<s:property value="session.loginId" escape="false"/>
					</td>
				</tr>
				<tr id="box">
					<td>
						<label>パスワード：</label>
					</td>
					<td>
						<s:property value="session.loginPass" escape="false"/>
					</td>
				</tr>
				<tr id="box">
					<td>
						<label>ユーザー名：</label>
					</td>
					<td>
						<s:property value="session.userName" escape="false"/>
					</td>
				</tr>
				<tr id="box">
					<td>
						<label>登録日：</label>
					</td>
					<td>
						<s:property value="session.insert_date" escape="false"/>
					</td>
				</tr>
				<tr id="box">
					<td>
						<label>更新日：</label>
					</td>
					<td>
						<s:property value="session.update_date" escape="false"/>
					</td>
				</tr>
				<tr id="box">
				</tr>
			</table>

			<s:form action="">
				<input type="hidden" name="flg" value="2">
				<s:submit value="編集" method="delete"/>
			</s:form>
			<s:form action="">
				<input type="hidden" name="flg" value="1">
				<s:submit value="削除" method="delete"/>
			</s:form>
			</s:elseif>

			<s:if test="message != null">
				<s:property value="message" escape="false"/>
			</s:if>
			<s:if test="editMessage != null">
				<s:property value="EditMessage" escape="false"/>
			</s:if>

		</div>
		<div>
			<p>前画面に戻る場合は<a href='<s:url action="UserTableAction"/>'>こちら</a></p>
		</div>
	</div>
	<div id="footer">
		<div id="pr">
		</div>
	</div>
</body>
</html>