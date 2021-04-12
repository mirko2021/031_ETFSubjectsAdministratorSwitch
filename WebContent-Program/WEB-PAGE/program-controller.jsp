<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="programBean" class='zenit.web.bean.ProgramBean' scope='session'></jsp:useBean>
<c:out value="${programBean.initialize(pageContext.session)}"></c:out>
<c:out value="${programBean.programController.refresh()}"></c:out>

<c:set var='subject' value='${linkSubjectBean.get(pageContext.request)}'></c:set>

<c:if test='${param["program_add"] ne null}'>
	<c:out value="${programBean.add(pageContext.request)}"></c:out>
</c:if>
<c:if test='${param["program_remove"] ne null}'>
	<c:out value="${programBean.remove(pageContext.request)}"></c:out>
</c:if>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Study Programs</title>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
	</head>
	<body>
		<h2>Study Programs List</h2>
		<form method='POST'>
			<dl>
				<dt><b>ПРЕДМЕТИ</b></dt>
				<dd><br></dd>
				<dd><table>
					<c:forEach var='programId' items='${programBean.getProgramKeys()}'>
						<c:set var='program' value='${programBean.programController.get(programId)}'></c:set>
						<tr>
							<td>
								<a href='${pageContext.request.contextPath}/WEB-PAGE/program-manervar.jsp?program_name=${programBean.utilBean.url(program.id)}' style='text-decoration:none' target='_blank'>&gt;&lt;</a>
							</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>
								<a href='${pageContext.request.contextPath}/WEB-PAGE/program-editor.jsp?program_name=${programBean.utilBean.url(program.id)}' style='text-decoration:none' target='_blank'>&lt;&gt;</a>
							</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>
								<a href='${pageContext.request.contextPath}/WEB-PAGE/program-report.jsp?program_name=${programBean.utilBean.url(program.id)}' style='text-decoration:none' target='_blank'><c:out value='${program.id}'></c:out></a>
							</td>
						</tr>
				</c:forEach>
				</table></dd>
				<dd><br></dd>
				<dt><b>ОПЕРАЦИЈЕ</b></dt>
				<dd><br></dd>
				<dd>
					<table>
						<tbody>
							<tr><td>Идентификација - назив студијског програма: </td></tr>
							<tr><td><input type="text" name="program_name" style="width:100%"></td></tr>
							<tr>
								<td>
									<input type="submit" name="program_add"    id="program_add"    value="Додавање">
									<input type="submit" name="program_remove" id="program_remove" value="брисање">
								</td>
							</tr>
						</tbody>
					</table>
				</dd>
				<dd>
					<div align='justify'><c:out value='${programBean.message}'></c:out></div>
				</dd>
			</dl>
		</form>
	</body>
</html>