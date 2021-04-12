<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="programBean" class='zenit.web.bean.ProgramBean' scope='session'></jsp:useBean>
<c:out value="${programBean.initialize(pageContext.session)}"></c:out>
<c:out value="${programBean.programController.refresh()}"></c:out>

<jsp:useBean id="programReportBean" class='zenit.agregator.web.bean.ProgramReportBean' scope='session'></jsp:useBean>
<c:out value="${programReportBean.initialize(pageContext.session)}"></c:out>

<c:if test='${param["accept_action_button"] ne null}'>
	<c:out value="${programReportBean.putGlueNotes(pageContext.request)}"></c:out>
</c:if>
<c:if test='${param["reset_action_button"] ne null}'>
	<c:out value="${programReportBean.resetGlueNotes(pageContext.request)}"></c:out>
</c:if>

<c:out value="${programReportBean.refresh(pageContext.request)}"></c:out>
<c:out value="${programReportBean.host(pageContext.request)}"></c:out>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Study Program Visualize Editor</title>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/general.css'></link>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
		<script type='text/javascript' src='${pageContext.request.contextPath}/WEB-JS/program_gn_editor.js'></script>
		<style>
			a{text-decoration: none;}
		</style>
	</head>
	<body>
		<h2>Study Program Visualize Editor</h2>
		<c:if test="${param['program_name'] eq null or param['program_name'].trim().length() eq 0 or not programBean.test(param['program_name'])}">
			Страница нема садржаја, није изабран нити један студијски програм, или не постоји. 
		</c:if>
		<c:if test="${param['program_name'] ne null and param['program_name'].trim().length() ne 0 and programBean.test(param['program_name'])}">
			<h3><c:out value='${param["program_name"]}'></c:out></h3>
			<c:set var='program_object' value='${programBean.get(param["program_name"])}'></c:set>
			<b><c:out value="${program_object.program}"></c:out></b><br>
			<b><c:out value="${program_object.course}"></c:out></b><br><br>
			<jsp:include page="/WEB-INF/tables/editable-first-year.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/tables/editable-second-year.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/tables/editable-third-year.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/tables/editable-fourth-year.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/tables/editable-zero-year.jsp"></jsp:include>
			<div align='right'>
				<form id='accept_glue_note_form' method='POST'>
					<input type='hidden' name='program_id' value='${param["program_name"]}'/>
					<input type='submit' name='accept_action_button' value='Потврда' onclick='inform_glue_program_notes()'/>
					<input type='submit' name='reset_action_button' value='ресетовање'/>
				</form>
			</div>
			<br><br>
		</c:if>
	</body>
</html>