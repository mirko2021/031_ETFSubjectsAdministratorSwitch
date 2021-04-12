<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="etfSubjectsBean" class="org.unibl.etf.spider.web.bean.ETFSubjectsBean" scope="session"></jsp:useBean>

<c:if test='${param["links_read_submit"] ne null}'>
	<c:out value="${etfSubjectsBean.readLinks(pageContext.request)}"></c:out>
</c:if>
<c:if test='${param["links_read_reset"] ne null}'>
	<c:out value="${etfSubjectsBean.resetLinks(pageContext.request)}"></c:out>
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
		<h2>Link Collector - ETF Subjects WWW - WEB Link Explorer - I циклус</h2>
		<form method='POST'>
			<table>
				<tr>
					<td>Линк веб странице : </td>
					<td><input type='text' name='url' style='width:100%' placeholder='url' value='${etfSubjectsBean.url}'></td>
				</tr>
				<tr>
					<td>Класа елемената за претрагу линкова : </td>
					<td><input type='text' name='clazz' style='width:100%' placeholder='class' value='${etfSubjectsBean.clazz}'></td>
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
					<th>семестар</th>
					<th>вредност</th>
					<th>часови</th>
					<th>тип</th>
					<th>адреса/URL</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan='5'><a href='${etfSubjectsBean.codeURI()}' target='_blank'>Основни линк</a></td>
					<td><c:out value='${etfSubjectsBean.url}'></c:out></td>
				</tr>
				<c:forEach var='semesterNo' items='${etfSubjectsBean.getSemesters()}'>
					<tr><td colspan='6' align='center'><c:out value='${semesterNo}'></c:out></td></tr>
					<c:forEach var='subjectData' items='${etfSubjectsBean.getDataForSemester(semesterNo)}'>
						<tr>
							<td><a href='${etfSubjectsBean.codeURI(subjectData.href)}' target='_blank'><c:out value='${subjectData.name}'></c:out></a></td>
							<td><c:out value='${subjectData.semester}'></c:out></td>
							<td><c:out value='${subjectData.ects}'></c:out></td>
							<td><c:out value='${subjectData.found}'></c:out></td>
							<td><c:out value='${subjectData.type}'></c:out></td>
							<td><c:out value='${subjectData.href}'></c:out></td>
						</tr>
					</c:forEach>
				</c:forEach>
				<tr><td colspan='6'>Укупно је пронађено <c:out value='${etfSubjectsBean.count+1}'></c:out> линкова.</td></tr>
			</tbody>
		</table><br>

<pre>
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus?layout=edit&amp;id=229
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus/osnovne-informacije/s-udi-s-i-pr-gr-r-cun-rs-v-i-inf-r-i/plan-softversko-inzenjerstvo
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus/osnovne-informacije/s-udi-s-i-pr-gr-r-cun-rs-v-i-inf-r-i/plan-racunarsko-inzenjerstvo
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus/osnovne-informacije/racunarsko-inzenjerstvo-info/plan-elektronika
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus/osnovne-informacije/racunarsko-inzenjerstvo-info/plan-telekomunikacije
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus/osnovne-informacije/s-udi-s-i-pr-gr-l-r-n-rg-i-i-u-i/plan-elektroenergetika
https://etf.unibl.org/index.php/sr-RS/studiranje/1-ciklus/osnovne-informacije/s-udi-s-i-pr-gr-l-r-n-rg-i-i-u-i/plan-automatika
</pre>

<pre>
nastavni_plan
</pre><br>

	</body>
</html>