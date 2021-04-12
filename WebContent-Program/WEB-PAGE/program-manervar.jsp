<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="programBean" class='zenit.web.bean.ProgramBean' scope='session'></jsp:useBean>
<c:out value="${programBean.initialize(pageContext.session)}"></c:out>
<c:out value="${programBean.programController.refresh()}"></c:out>

<c:if test='${param["update_submit"] ne null}'>
	<c:out value='${programBean.updateBasic(pageContext.request)}'></c:out>
</c:if>

<c:if test='${param["add_subject"] ne null}'>
	<c:out value='${programBean.addSubject(pageContext.request)}'></c:out>
</c:if>

<c:if test='${param["remove_subject"] ne null}'>
	<c:out value='${programBean.removeSubject(pageContext.request)}'></c:out>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Study Program Data</title>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
	</head>
	<body>
		<h2>Study Program Data</h2>
		<c:set var='var_program_name' value='${param["program_name"]}'></c:set>
		<c:if test="${var_program_name eq null or var_program_name.trim().length() eq 0 or not programBean.test(var_program_name)}">
			Страница нема садржаја, није изабран нити један студијски програм, или не постоји. 
		</c:if>
		<c:if test="${var_program_name ne null and var_program_name.trim().length() ne 0 and programBean.test(var_program_name)}">
			<h3><c:out value='${var_program_name}'></c:out></h3>
			<c:set var='current_subject' value='${programBean.get(var_program_name)}'></c:set>
			<c:out value='${programBean.message}'></c:out>
			<form method='POST'>
				<dl>
					<dt><b>ПАРАМЕТРИ</b></dt>
					<dd><br></dd>
					<dd>
						<table>
							<tr>
								<td><b>Пуни називн студијског програма (ид):</b></td>
								<td><input type='text' name='program_id'/></td>
								<td><c:out value='${var_program_name}'></c:out></td>
							</tr>
							<tr>
								<td><b>Назив студијског програма:</b></td>
								<td><input type='text' name='program'/></td>
								<td><c:out value='${current_subject.program}'></c:out></td>
							</tr>
							<tr>
								<td><b>Назив студијског смјера:</b></td>
								<td><input type='text' name='course'/></td>
								<td><c:out value='${current_subject.course}'></c:out></td>
							</tr>
						</table>
					</dd>
				</dl>
				<input type='submit' value='Потврда' name='update_submit'/>
				<input type='hidden' value='${var_program_name}' name='program_id'/>
			</form>
			<form method="POST">
			   <dl>
			   		<dt><b>ПРЕДМЕТИ</b></dt>
					<dd><br></dd>
					<dd>
					<c:forEach var='subject' items='${programBean.listSubjects(pageContext.request)}'>
						<a href="${pageContext.request.contextPath}/WEB-PAGE/report_preview.jsp?subject_id=${programBean.utilBean.url(subject)}" style="text-decoration:none" target="_blank"><c:out value="${subject}"></c:out></a><br>
					</c:forEach>
					</dd>
					<dd><br></dd>
					<dt><b>ОПЕРАЦИЈЕ</b></dt>
					<dd><br></dd>
					<dd>
						<table>
							<tr>
								<td>Шифра предмета:</td>
								<td><input type='text' name='subject_id'/></td>
							</tr>
						</table>
					</dd>
				</dl>
				<input type='submit' value='Додавање' name='add_subject'/>
				<input type='submit' value='брисање' name='remove_subject'/>
			</form>
		</c:if>
	</body>
</html>

<c:if test="${param['program_id'] ne param['program_name'] and param['program_name'] ne null and param['program_id'] ne null and programBean.success}">
	<c:redirect url='/WEB-PAGE/program-manervar.jsp?program_name=${param["program_id"]}'></c:redirect>
</c:if>
