<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='dataSourceBean' class='zenit.learning.datasource.bean.DataSourceBean' scope='session'></jsp:useBean>
<c:out value='${dataSourceBean.initialize(pageContext.session)}'></c:out>

<jsp:useBean id='subjectBean' class='zenit.web.bean.SubjectBean' scope='session'></jsp:useBean>
<c:out value="${subjectBean.initialize(pageContext.session)}"></c:out>
<c:out value="${subjectBean.subjectController.refresh()}"></c:out>

<jsp:useBean id="programBean" class='zenit.web.bean.ProgramBean' scope='session'></jsp:useBean>
<c:out value="${programBean.initialize(pageContext.session)}"></c:out>
<c:out value="${programBean.programController.refresh()}"></c:out>

<c:if test='${param["io_schema"] ne null}'>
	<c:out value="${dataSourceBean.set(param['io_schema']) or true? '': ''}"></c:out>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Извори података</title>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/WEB-CSS/links-red.css'></link>
	</head>
	<body>
		<h2>Техничка подршка - извори података</h2>
		<dl>
			<dt><b>ИЗВОРИ ПОДАТАКА</b></dt>
			<dd><br></dd>
			<c:forEach var='dataSource' items='${dataSourceBean.list()}'>
				<dd><a href="?io_schema=${dataSource}" style='text-decoration: none'><c:out value='${dataSource}'></c:out></a></dd>
			</c:forEach>
			<dd><br></dd>
			<dt><b>ТРЕНУТНИ ИЗВОР ПОДАТАКА</b></dt>
			<dd><br></dd>
			<dd><c:out value='${dataSourceBean.get()}'></c:out></dd>
			<dd><br></dd>
		</dl>
	</body>
</html>