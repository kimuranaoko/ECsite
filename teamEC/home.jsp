<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/kumamon.css" />
<link rel="stylesheet" href="./css/home.css" />
<link rel="stylesheet" href="./css/all.css" />
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<title>ホーム</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div id="contents">
 <div class="btn">
 <div class="imgimg"><img src="./images/kumamon-button.png" width=300px height=450px/></div>
 <a class="h_imgimg">素敵なイベントを一緒に盛り上げるんだモン！<br>日本地図をチェッくま〜☆</a>

 </div>
 	<div id="nihon" style="zoom: 1.4;">
  		   <ul id="imagemap">
			<li id="hokkaidotouhoku"><a href='<s:url action="SearchItemAction"><s:param name="categoryId" value="2"/></s:url>'><span>北海道_東北</span></a><div class="hukidashi"><p class="moji">北海道と東北なの<br></p><p class="image"><img src="./images/jin.png" /></p></div></li>
			<li id="kanto"><a href='<s:url action="SearchItemAction"><s:param name="categoryId" value="3"/></s:url>'><span>関東</span></a><div class=hukidashi><p class="moji">関東ねば</p><p class="image"><img src="./images/neba.png"></p></div></li>
			<li id="tyubukinki"><a href='<s:url action="SearchItemAction"><s:param name="categoryId" value="4"/></s:url>'><span>中部_近畿</span></a><div class="hukidashi"><p class="moji">中部と近畿でござる</p><p class="image"><img src="./images/oka.png"></p></div></li>
			<li id="tyugokushikokui"><a href='<s:url action="SearchItemAction"><s:param name="categoryId" value="5"/></s:url>'><span>中国_四国</span></a><div class="hukidashi"><p class="moji">中国と四国じゃけぇ<p class="image"><img src="./images/hassakun.png"></p></div></li>
			<li id="kyushuokinawa"><a href='<s:url action="SearchItemAction"><s:param name="categoryId" value="6"/></s:url>'><span>九州_沖縄</span></a><div class="hukidashi"><p class="moji">九州と沖縄ばい</p><p class="image"><img src="./images/kuru.png"></p></div></li>
			</ul>
	</div>
</div>
</body>
</html>