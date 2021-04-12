<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="etfSubjectsCollectionBean" class="org.unibl.etf.spider.web.bean.ETFSubjectsCollectionBean" scope="session"></jsp:useBean>

<c:if test='${param["links_read_submit"] ne null}'>
	<c:out value="${etfSubjectsCollectionBean.readLinks(pageContext.request)}"></c:out>
</c:if>
<c:if test='${param["links_read_reset"] ne null}'>
	<c:out value="${etfSubjectsCollectionBean.resetLinks(pageContext.request)}"></c:out>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Link Collector</title>
		<script type="text/javascript" src='${pageContext.request.contextPath}/ETF-JS/etf_links_manervar.js'></script>
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
		<h2>Link Collector - ETF Subjects WWW - WEB Link Manervar - I циклус</h2>
		<form name='address_form' method='POST'>
			<table>
				<tr>
					<td>Линк веб странице : </td>
					<td><input type='text' name='url' style='width:100%' placeholder='url' value='${etfSubjectsCollectionBean.url}'></td>
				</tr>
				<tr>
					<td>Класа елемената за претрагу линкова : </td>
					<td><input type='text' name='clazz' style='width:100%' placeholder='class' value='${etfSubjectsCollectionBean.clazz}'></td>
				</tr>
			</table>
			<br>
			<input type='submit' name='links_read_submit' value='Очитавање'>
			<input type='submit' name='links_read_reset' value='ресетовање'>
		</form>
		<br>
		<form name='report_form' method='POST'>
			<c:set var='semestersCollection' value='${etfSubjectsCollectionBean.getSemesters()}'></c:set>
			<input type='hidden' id='semesters_number' value='${semestersCollection.size()}'/>
			<c:forEach var='semester_no' begin='1' end='${semestersCollection.size()}' step='1' varStatus='loop'>
				<input type='hidden' id='subjects_number_${semester_no}' value='0'/>
			</c:forEach>
			<table class='content'>
				<thead>
					<tr>
						<th colspan='2' align='left'><input type='checkbox' name='header_cb' id='main_cb' onclick='cb_main_click()'></th>
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
						<td colspan='2'></td>
						<td colspan='5'><a href='${etfSubjectsCollectionBean.codeURI()}' target='_blank'>Основни линк</a></td>
						<td><c:out value='${etfSubjectsCollectionBean.url}'></c:out></td>
					</tr>

					<c:set var='semesterCounter' value='1'></c:set>
					<c:forEach var='semesterNo' items='${semestersCollection}'>
						<tr>
							<td colspan='2'><input type='checkbox' id='semester_cb_${semesterCounter}' onclick='cb_semester_click("${semesterCounter}")'/></td>
							<td colspan='6' align='center'><c:out value='${semesterNo}'></c:out></td>
							<c:set var='semesterCounter' value='${semesterCounter+1}'></c:set>
						</tr>
						<c:set var='subjectCounter' value='1'></c:set>
						<c:forEach var='subjectData' items='${etfSubjectsCollectionBean.getDataForSemester(semesterNo)}'>
							<tr>
								<td><input type='checkbox' id='subject_cb_${semesterCounter-1}_${subjectCounter}' onclick='cb_subject_click("${semesterCounter-1}","${subjectCounter}")'/></td>
								<td><a href='${pageContext.request.contextPath}/WEB-PAGE/link_report_preview.jsp?href=${etfSubjectsCollectionBean.codeURI(subjectData.href)}' target='_blank'>&gt;&lt;</a></td>
								<td><a href='${etfSubjectsCollectionBean.codeURI(subjectData.href)}' target='_blank'><c:out value='${subjectData.name}'></c:out></a></td>
								<td><c:out value='${subjectData.semester}'></c:out></td>
								<td><c:out value='${subjectData.ects}'></c:out></td>
								<td><c:out value='${subjectData.found}'></c:out></td>
								<td><c:out value='${subjectData.type}'></c:out></td>
								<td><c:out value='${subjectData.href}'></c:out></td>
							</tr>
							<c:set var='subjectCounter' value='${subjectCounter+1}'></c:set>
						</c:forEach>
					</c:forEach>
					<tr>
						<td colspan='2'></td>
						<td colspan='6'>Укупно је пронађено <c:out value='${etfSubjectsCollectionBean.count+1}'></c:out> линкова.</td>
					</tr>
				</tbody>
			</table><br>
			<c:set var='semesterCounter' value='1'></c:set>
			<c:forEach var='semesterNo' items='${semestersCollection}'>
				<script>document.getElementById('subjects_number_${semesterCounter}').value='${etfSubjectsCollectionBean.getDataForSemester(semesterNo).size()}'</script>
				<c:set var='semesterCounter' value='${semesterCounter+1}'></c:set>
			</c:forEach>
		</form>

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