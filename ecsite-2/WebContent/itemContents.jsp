<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Style-Type" content="text/css"/>
	<meta http-equiv="Content-Script-Type" content="text/javascript"/>
	<meta http-equiv="imagetoolbar" content="no"/>
	<meta name="description" content=""/>
	<meta name="keywords" content=""/>
	<title>ItemContents画面</title>

	<style type="text/css">
		body{
			margin:0;
			padding:0;
			line-height:1.6;
			letter-spacing:1px;
			font-family:Verdana,Helvetica,sans-serif;
			font-size:12px;
			color:#333;
			background:#fff
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

		#inline{
		display: inline
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
			<p>ItemContents</p>
		</div>
<!-- flg2ならボックス出現 -->
			<s:if test="flg==2">
					<s:form action="ItemContentsAction">
					<input type="hidden" name="flg" value="3">
						<tr>
							<td>
								<label>商品名：</label>
							</td>
							<td>
								<s:textfield type="text" name="itemName" value='%{session.AItemName}'/>
							</td>
						</tr>
						<tr>
							<td>
								<label>金額(円)：</label>
							</td>
							<td>
								<s:textfield type="text" name="itemPrice" value='%{session.AItemPrice}'/>
							</td>
						</tr>
						<tr>
							<td>
								<label>在庫数：</label>
							</td>
							<td>
								<select name="itemStock">
									<option value="100" selected="selected">100</option>
									<option value="200">200</option>
									<option value="300">300</option>
									<option value="400">400</option>
									<option value="500">500</option>
								</select>
							</td>
						</tr>
						<s:submit value="更新"/>
					</s:form>

			</s:if>

			<s:elseif test="message ==null && editMessage == null">
				<h3>★商品詳細★</h3>
				<table>
					<tr id="box">
						<td>
							<label>商品ID：</label>
						</td>
						<td>
							<s:property value="session.AItemId" escape="false"/>
						</td>
					</tr>
					<tr id="box">
						<td>
							<label>商品名：</label>
						</td>
						<td>
							<s:property value="session.AItemName" escape="false"/>
						</td>
					</tr>
					<tr id="box">
						<td>
							<label>値段：</label>
						</td>
						<td>
							<s:property value="session.AItemPrice" escape="false"/>
						</td>
					</tr>
					<tr id="box">
						<td>
							<label>在庫：</label>
						</td>
						<td>
							<s:property value="session.AItemStock" escape="false"/>
						</td>
					</tr>
					<tr id="box">
						<td>
							<label>登録日：</label>
						</td>
						<td>
							<s:property value="session.Ainsert_date" escape="false"/>
						</td>
					</tr>
					<tr id="box">
						<td>
							<label>更新日：</label>
						</td>
						<td>
							<s:property value="session.Aupdate_date" escape="false"/>
						</td>
					</tr>
				</table>

				<s:form action="">
					<input type="hidden" name="Flg" value="2">
					<s:submit value="編集" method="edit1"/>
				</s:form>

				<s:form action="">
					<input type="hidden" name="Flg" value="1">
					<s:submit value="削除" method="delete"/>
				</s:form>




			</s:elseif>


			<s:if test="editMessage !=null">
					<h3><s:property value="editMessage"/></h3>
			</s:if>

			<s:if test="message !=null">
					<h3><s:property value="message"/></h3>
			</s:if>

		<div>
			<p>前画面に戻る場合は<a href='<s:url action="ItemTableAction"/>'>こちら</a></p>
		</div>
	</div>
	<div id="footer">
		<div id="pr">
		</div>
	</div>
</body>
</html>