<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Link Collector</title>
	</head>
	<body>
		<h2>Link Collector - Index</h2>
		<dl>
			<dt>Опште управљање повезницама</dt>
			<dd><a href='${pageContext.request.contextPath}/WEB-PAGE/link-page-exploer.jsp' target='_blank'>Очитавање линкова веб страница</a></dd>
			<dd><a href='${pageContext.request.contextPath}/WEB-PAGE/class-element-link-explorer.jsp' target='_blank'>Очитавање линкова веб страница из елемената дате класе</a></dd>
		</dl>
		<dl>
			<dt>Управљање повезницама везано за ЕТФ</dt>
			<dd><a href='${pageContext.request.contextPath}/ETF-PAGE/etf-semesters-link.jsp' target='_blank'>Очитавање линкова за предмете 1, 2, 3, 4 године студијских програма, разврстано по семестрима</a></dd>
			<dd><a href='${pageContext.request.contextPath}/ETF-PAGE/etf-semesters-collection-link.jsp' target='_blank'>Манервисање линковима за предмете 1, 2, 3, 4 године студијских програма, разврстано по семестрима</a></dd>
		</dl>
		<dl>
			<dt>Управљањем описом предмета</dt>
			<dd><a href="${pageContext.request.contextPath}/WEB-PAGE/control_page.jsp" target='_blank'>Управљање листом предмета</a></dd>
			<dd><a href="${pageContext.request.contextPath}/WEB-PAGE/program-controller.jsp" target='_blank'>Управљање студијским програмима</a></dd>
		</dl>
		<dl>
			<dt>Услужни механизми</dt>
			<dd><a href="${pageContext.request.contextPath}/WEB-PAGE/alias-controller.jsp" target='_blank'>Управљање спојеним предметима</a></dd>
			<dd><a href="${pageContext.request.contextPath}/WEB-PAGE/data-sources.jsp" target='_blank'>Управљање изворима података</a></dd>
		</dl>
	</body>
</html>