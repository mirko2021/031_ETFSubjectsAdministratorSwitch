<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="programReportBean" class='zenit.agregator.web.bean.ProgramReportBean' scope='session'></jsp:useBean>
<c:out value="${programReportBean.initialize(pageContext.session)}"></c:out>
<c:out value="${programReportBean.host(pageContext.request)}"></c:out>

<c:set var='schema' value='${programReportBean.schema}'></c:set>

<c:if test='${schema ne null and schema.countSubjects("0")  ne 0}'>
	<table class="nastavni_plan">
		<tbody>
			<tr>
				<th colspan="6">Некатегорисано</th>
			</tr>
			<tr>
				<th>Ред. бр.</th>
				<th>Назив предмета</th>
				<th>Семестар</th>
				<th>Бр.&nbsp;кредита</th>
				<th>Бр. часова</th>
				<th>Тип</th>
			</tr>
			<c:set var='i' value='1'></c:set>
			<c:forEach var='subject' items='${schema.get("0")}'>
				<tr>
					<td><c:out value='${i}'></c:out></td>
					<td class="naziv_predmeta"><a href="${pageContext.request.contextPath}/WEB-PAGE/report_preview.jsp?subject_id=${programReportBean.uriEncode(subject.subjectId)}" rel="alternate" target='_blank'><c:out value='${subject.subjectName}'></</c:out></a></td>
					<td>-</td>
					<td><c:out value='${subject.ectsValue}'></c:out></td>
					<td><c:out value='${subject.lessonsFound}'></c:out></td>
					<td><div style='white-space: pre-line'><c:out value='${subject.subjectState}'></c:out></div></td>
				</tr>
				<c:set var='i' value='${i+1}'></c:set>
			</c:forEach>
			<c:if test='${schema.countSubjects("0") ne 0}'>
				<tr>
					<th colspan="2">Укупно некатегорисано</th>
					<th><div style='white-space: pre-line'><c:out value='${schema.getF1("0")}'></c:out></div></th>
					<th><div style='white-space: pre-line'><c:out value='${schema.ectsInSemesterString("0")}'></c:out></div></th>
					<th><div style='white-space: pre-line'><c:out value='${schema.lessonsInSemesterString("0")}'></c:out></div></th>
					<th><div style='white-space: pre-line'><c:out value='${schema.getF4("0")}'></c:out></div></th>
				</tr>
			</c:if>
		</tbody>
	</table>
	<br><br>
</c:if>