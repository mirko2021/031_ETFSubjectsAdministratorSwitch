<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="subjectMergeBean" class='zenit.merges.bean.SubjectMergeBean' scope='session'></jsp:useBean>
<c:out value="${subjectMergeBean.initialize(pageContext.session)}"></c:out>

<c:if test="${param['alias_add'] ne null}">
	<c:out value="${subjectMergeBean.add(pageContext.request)}"></c:out>
</c:if>
<c:if test="${param['alias_remove'] ne null}">
	<c:out value="${subjectMergeBean.remove(pageContext.request)}"></c:out>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Study Subjects Alias</title>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
	</head>
	<body>
		<h2>Study Subjects Alias</h2>
		<form method='POST'>
			<dl>
				<dt><b>ОПИС</b></dt>
				<dd><br></dd>
				<dd>
					<div style='width:300px' align='justify'>
						Спајање предмета, на неки предмет који представља
						кључ је средство да се измјене предмета кључа 
						прекопирају на предмете који су под њим, што 
						олакшава измјену на примјер истог предмета на
						разним студијским програмима, када се држе 
						одвојено. 
					</div>
				</dd>
				<dd><br></dd>
				<dt><b>ПОВЕЗНИЦЕ</b></dt>
				<dd><br></dd>
				<c:forEach var='alias' items='${subjectMergeBean.get()}'>
					<dd><a href="${pageContext.request.contextPath}/WEB-PAGE/alias-manervar.jsp?alias_name=${alias.aliasId}" target="_blank"><c:out value="${alias.aliasId}"></c:out></a></dd>
				</c:forEach>
				<dd><br></dd>
				<dd>
					<table>
						<tbody>
							<tr><td>Назив спојнице за предмете: </td></tr>
							<tr><td><input type="text" name="alias_name" style="width:100%"></td></tr>
							<tr><td>Шифра предмета којим се диктирају измјене (предмет кључ): </td></tr>
							<tr><td><input type="text" name="alias_key" style="width:100%"></td></tr>
							<tr>
								<td>
									<input type="submit" name="alias_add"    id="alias_add"    value="Додавање">
									<input type="submit" name="alias_remove" id="alias_remove" value="брисање">
									<div><c:out value='${subjectMergeBean.consumeMessage()}'></c:out></div>
								</td>
							</tr>
						</tbody>
					</table>
				</dd>
			</dl>
		</form>
	</body>
</html>