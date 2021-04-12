<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="programReportBean" class='zenit.agregator.web.bean.ProgramReportBean' scope='session'></jsp:useBean>
<c:out value="${programReportBean.initialize(pageContext.session)}"></c:out>
<c:out value="${programReportBean.host(pageContext.request)}"></c:out>

<c:set var='schema' value='${programReportBean.schema}'></c:set>

<c:if test='${schema ne null and schema.countSubjects("5") + schema.countSubjects("6")  ne 0}'>
	<table class="nastavni_plan">
		<tbody>
			<tr>
				<th colspan="6">Трећа&nbsp;година</th>
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
			<c:forEach var='subject' items='${schema.get("5")}'>
				<tr>
					<td><c:out value='${i}'></c:out></td>
					<td class="naziv_predmeta"><a href="${pageContext.request.contextPath}/WEB-PAGE/report_preview.jsp?subject_id=${programReportBean.uriEncode(subject.subjectId)}" rel="alternate" target='_blank'><c:out value='${subject.subjectName}'></</c:out></a></td>
					<td>5</td>
					<td><c:out value='${subject.ectsValue}'></c:out></td>
					<td><c:out value='${subject.lessonsFound}'></c:out></td>
					<td><div style='white-space: pre-line' id='subject_state_${programReportBean.escape(subject.subjectId)}' contenteditable='true'><c:out value='${subject.subjectState}'></c:out></div></td>
				</tr>
				<c:set var='i' value='${i+1}'></c:set>
			</c:forEach>
			<c:if test='${schema.countSubjects("5") ne 0}'>
				<tr>
					<th colspan="2">Укупно пети семестар</th>
					<th><div style='white-space: pre-line' id='semester_5_field_1' contenteditable='true'><c:out value='${schema.getF1("5")}'></c:out></div></th>
					<th><div style='white-space: pre-line' id='semester_5_field_2' contenteditable='true'><c:out value='${schema.ectsInSemesterString("5")}'></c:out></div></th>
					<th><div style='white-space: pre-line' id='semester_5_field_3' contenteditable='true'><c:out value='${schema.lessonsInSemesterString("5")}'></c:out></div></th>
					<th><div style='white-space: pre-line' id='semester_5_field_4' contenteditable='true'><c:out value='${schema.getF4("5")}'></c:out></div></th>
				</tr>
			</c:if>
			<c:forEach var='subject' items='${schema.get("6")}'>
				<tr>
					<td><c:out value='${i}'></c:out></td>
					<td class="naziv_predmeta"><a href="${pageContext.request.contextPath}/WEB-PAGE/report_preview.jsp?subject_id=${programReportBean.uriEncode(subject.subjectId)}" rel="alternate" target='_blank'><c:out value='${subject.subjectName}'></</c:out></a></td>
					<td>6</td>
					<td><c:out value='${subject.ectsValue}'></c:out></td>
					<td><c:out value='${subject.lessonsFound}'></c:out></td>
					<td><div style='white-space: pre-line' id='subject_state_${programReportBean.escape(subject.subjectId)}' contenteditable='true'><c:out value='${subject.subjectState}'></c:out></div></td>
				</tr>
				<c:set var='i' value='${i+1}'></c:set>
			</c:forEach>
			<c:if test='${schema.countSubjects("6") ne 0}'>
				<tr>
					<th colspan="2">Укупно шести семестар</th>
					<th><div style='white-space: pre-line' id='semester_6_field_1' contenteditable='true'><c:out value='${schema.getF1("6")}'></c:out></div></th>
					<th><div style='white-space: pre-line' id='semester_6_field_2' contenteditable='true'><c:out value='${schema.ectsInSemesterString("6")}'></c:out></div></th>
					<th><div style='white-space: pre-line' id='semester_6_field_3'contenteditable='true'><c:out value='${schema.lessonsInSemesterString("6")}'></c:out></div></th>
					<th><div style='white-space: pre-line' id='semester_6_field_4' contenteditable='true'><c:out value='${schema.getF4("6")}'></c:out></div></th>
				</tr>
			</c:if>
		</tbody>
	</table>
	<br><br>
</c:if>