<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="subjectMergeBean" class='zenit.merges.bean.SubjectMergeBean' scope='session'></jsp:useBean>
<c:out value="${subjectMergeBean.initialize(pageContext.session)}"></c:out>

<c:if test='${param["alias_put"] ne null}'>
	<c:out value="${subjectMergeBean.accept(pageContext.request)}"></c:out>
	<script>location.assign("${pageContext.request.contextPath}/WEB-PAGE/alias-manervar.jsp?alias_name=${param['alias_name_update']}");</script>
</c:if>

<c:if test='${param["add_merge_subject"] ne null}'>
	<c:out value="${subjectMergeBean.mergeSubject(pageContext.request)}"></c:out>
</c:if>

<c:if test='${param["remove_merge_subject"] ne null}'>
	<c:out value="${subjectMergeBean.splitSubject(pageContext.request)}"></c:out>
</c:if>


<c:if test='${param["alias_put"] eq null}'>
	<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Study Subjects Alias Manervar</title>
			<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
		</head>
		<body>
			<h2>Study Subject Alias Manervar</h2>
			<c:if test='${param["alias_name"] eq null or subjectMergeBean.get(param["alias_name"]) eq null}'>
				Спојница за предмете није задана. 
			</c:if>
			<c:if test='${param["alias_name"] ne null and subjectMergeBean.get(param["alias_name"]) ne null}'>
				<b><c:out value='${param["alias_name"]}'></c:out></b>
				<c:set var='currentAlias' value='${subjectMergeBean.get(param["alias_name"])}'></c:set><br>
				<div><c:out value="${subjectMergeBean.consumeMessage()}"></c:out></div>
				<dl>
					<dt><b>КАРАКТЕРИСТИКЕ СПОЈНИЦЕ</b></dt>
					<dd><br></dd>
					<dd>
						<form name='alias_put'>
							<table>
								<tbody>
									<tr><td>Назив спојнице за предмете: </td></tr>
									<tr><td><input type="text" name="alias_name_update" style="width:100%" value='${currentAlias.aliasId}'></td></tr>
									<tr><td>Шифра предмета којим се диктирају измјене (предмет кључ): </td></tr>
									<tr><td><input type="text" name="alias_key_update" style="width:100%" value='${currentAlias.mainSubjectId}'></td></tr>
									<tr>
										<td>
											<input type="submit" name="alias_put"  id="alias_put"  value="Потврда"/>
											<input type="hidden" name="alias_name" id="alias_name" value="${param['alias_name']}"/>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</dd>
					<dd><br><dd>
					<dt><b>СПОЈЕНИ ПРЕДМЕТИ</b></dt>
					<dd><br><dd>
					<c:forEach var='aliasSubject' items='${subjectMergeBean.list(param["alias_name"])}'>
						<a style='text-decoration: none' href='${pageContext.request.contextPath}/WEB-PAGE/report_preview.jsp?subject_id=${aliasSubject}' target='_blank'><c:out value='${aliasSubject}'></c:out></a><br>
					</c:forEach>
				</dl>
				<form name='array_merge_subjects_form' method='POST'>
					<dl>
						<dt><b>ОПЕРАЦИЈЕ</b></dt>
						<dd><br><dd>
						<dd>
							<table>
								<tbody><tr>
									<td>Шифра предмета за спајање:</td>
									<td><input type="text" name="merge_subject_id"></td>
								</tr>
							</tbody></table>
						</dd>
						<dd>
							<input type="submit" value="Додавање" name="add_merge_subject"                />
							<input type="submit" value="брисање" name="remove_merge_subject"              />
							<input type="hidden" name="general_alias_name" value="${param['alias_name']}" />
						</dd>
					</dl>
				</form>
			</c:if>
		</body>
	</html>
</c:if>