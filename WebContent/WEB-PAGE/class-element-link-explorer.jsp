<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="webClassParserBean" class="zenit.spider.web.bean.WebClassParserBean" scope="session"></jsp:useBean>

<c:if test='${param["links_read_submit"] ne null}'>
	<c:out value="${webClassParserBean.readLinks(pageContext.request)}"></c:out>
</c:if>
<c:if test='${param["links_read_reset"] ne null}'>
	<c:out value="${webClassParserBean.resetLinks(pageContext.request)}"></c:out>
</c:if>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Link Collector</title>
		<style>
			table.content {
				border-spacing: 0px;
			}
			
			table.content th {
				border-top: 1px solid black;
				border-bottom: 1px solid black;
				border-right: 1px solid black;
				padding : 5px
			}
			
			table.content tr th:first-child{
				border-left: 1px solid black;
			}
			
			table.content td {
				border-bottom: 1px solid black;
				border-right: 1px solid black;
				padding : 5px
			}
			
			table.content tr td:first-child{
				border-left: 1px solid black;
			}
			
			table.content  a {
				text-decoration: none;
			}
		</style>
	</head>
	<body>
		<h2>Link Collector - Class Link Explorer</h2>
		<form method='POST'>
			<table>
				<tr>
					<td>Линк веб странице : </td>
					<td><input type='text' name='url' style='width:100%' placeholder='url' value='${webClassParserBean.url}'></td>
				</tr>
				<tr>
					<td>Класа елемената за претрагу линкова : </td>
					<td><input type='text' name='clazz' style='width:100%' placeholder='class' value='${webClassParserBean.clazz}'></td>
				</tr>
			</table>
			<br>
			<input type='submit' name='links_read_submit' value='Очитавање'>
			<input type='submit' name='links_read_reset' value='ресетовање'>
		</form>
		<br>
		<table class='content'>
			<thead>
				<tr>
					<th>Повезница/линк</th>
					<th>адреса/URL</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href='${webClassParserBean.codeURI()}' target='_blank'>Основни линк</a></td>
					<td><c:out value='${webClassParserBean.url}'></c:out></td>
				</tr>
				<c:set var='linksMap' value='${webClassParserBean.getLinksMap()}'></c:set>
				<c:forEach var='mapEntry' items='${linksMap.entrySet()}'>
					<tr>
						<td><a href='${webClassParserBean.codeURI(mapEntry.getKey().toString())}' target='_blank'><c:out value='${mapEntry.getValue()}'></c:out></a></td>
						<td><c:out value='${mapEntry.getKey().toString()}'></c:out></td>
					</tr>
				</c:forEach>
				<tr><td colspan='2'>Укупно је пронађено <c:out value='${linksMap.size()+1}'></c:out> линкова.</td></tr>
			</tbody>
		</table>
	</body>
</html>