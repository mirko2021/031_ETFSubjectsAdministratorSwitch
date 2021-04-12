<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="linkSubjectBean" class='zenit.collector.bean.SubjectBean' scope='session'></jsp:useBean>
<c:out value="${linkSubjectBean.initialize(pageContext.session)}"></c:out>
<c:set var='subject' value='${linkSubjectBean.get(pageContext.request)}'></c:set>

<c:if test='${param["subject_load_submit"] ne null}'>
	<c:out value="${linkSubjectBean.update(pageContext.request)}"></c:out>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Study Subjects</title>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/general.css'></link>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
	</head>
	<body>
		<h2>Study Report</h2>
		<c:if test='${subject eq null}'>
			Не постоји предмет за преглед.
		</c:if>
		<c:if test='${subject ne null}'>
			<article class="item-page">
				<table class="predmet">
					<tbody>
						<tr>
							<td class="bijeli_naslov">Назив предмета</td>
							<th colspan="4"><div id='content_naziv_predmeta'><c:out value='${subject.subjectName}'></c:out></div></th>
						</tr>
						<tr class="centriran_bijeli_naslov">
							<td style='text-align:left'>Шифра предмета</td>
							<td>Статус предмета</td>
							<td>Семестар</td>
							<td>Фонд часова</td>
							<td>Број ECTS бодова</td>
						</tr>
						<tr class="centriran">
							<td style='text-align:right'><c:out value='${subject.subjectId}'>&nbsp;</c:out></td>
							<td><div id='content_status_predmeta'><c:out value='${subject.subjectState}'></c:out>&nbsp;</div></td>
							<td><div id='content_semestar'><c:out value='${subject.semestar}'></c:out>&nbsp;</div></td>
							<td><div id='content_fond_casova'><c:out value='${subject.lessonsFound}'></c:out>&nbsp;</div></td>
							<td><div id='content_ectes_bodovi'><c:out value='${subject.ectsValue}'></c:out>&nbsp;</div></td>
						</tr>
						<tr>
							<td class="plavi_naslov">Наставници</td>
							<td colspan="4"><div id='content_nastavnici'><c:out value='${subject.lectors}'></c:out></div></td>
						</tr>
					</tbody>
				</table>
				<p>&nbsp;</p>
				<table class="predmet">
					<tbody>
						<tr>
							<td class="plavi_naslov" colspan="4" style='white-space: pre-line'>Условљеност другим предметима</td>
							<td class="plavi_naslov" colspan="2" style='white-space: pre-line; width:33%'>Облик условљености</td>
						</tr>
						<tr>
							<td colspan="4"><div id='content_uslovljenost' align='justify' style='white-space: pre-line'><c:out value='${subject.edges}'></c:out><c:if test='${subject.edges.trim().length() eq 0}'>&nbsp;</c:if></div></td>
							<td colspan="2" style='width:33%; white-space: inhert'><div id='content_oblik_uslovljenosti' style='white-space: pre-line'><c:out value='${subject.edgesForm}'></c:out><c:if test='${subject.edgesForm.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Циљеви изучавања предмета:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_target' align='justify' style='white-space: pre-line'><c:out value='${subject.subjectTarget}'></c:out><c:if test='${subject.subjectTarget.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Исходи учења (стечена знања):</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_results' align='justify' style='white-space: pre-line'><c:out value='${subject.subjectRealisations}'></c:out><c:if test='${subject.subjectRealisations.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Садржај предмета:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_theme' align='justify' style='white-space: pre-line'><c:out value='${subject.subjectContent}'></c:out><c:if test='${subject.subjectContent.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Методе наставе и савладавање градива:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_methods' align='justify' style='white-space: pre-line'><c:out value='${subject.methodLectionTransfer}'></c:out><c:if test='${subject.methodLectionTransfer.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Литература:</td>
						</tr>
						<tr>
							<td colspan="6">
								<ol id='content_reference'>
									<c:forEach var='ref' items='${subject.references}'>
										<li><c:out value='${ref}'></c:out></li>
									</c:forEach>
								</ol>
							</td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Облици провјере знања и оцјењивање:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_forme' align='justify' style='white-space: pre-line'><c:out value='${subject.gradulation.gradulationNotes}'></c:out><c:if test='${subject.gradulation.gradulationNotes.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td>Први колоквијум</td>
							<td class="bodovi"><div align='justify' id='content_prvi_kolokvijum' style='white-space: pre-line'><c:out value='${subject.gradulation.firstColocvium}'></c:out></div></td>
							<td>Лабораторијске вјежбе</td>
							<td class="bodovi"><div align='justify' id='content_laboratorije' style='white-space: pre-line'><c:out value='${subject.gradulation.laboratoryExercies}'></c:out></div></td>
							<td>Завршни испит</td>
							<td class="bodovi"><div align='justify' id='content_ispit' style='white-space: pre-line'><c:out value='${subject.gradulation.finalExams}'></c:out></div></td>
						</tr>
						<tr>
							<td valign='top'>Други колоквијум</td>
							<td class="bodovi" valign='top'><div align='justify' id='content_drugi_kolokvijum'><c:out value='${subject.gradulation.secondColocvium}'></c:out></div></td>
							<td colspan="4" style='width:66%' style='white-space: pre-line' valign='top'><div align='justify' id='content_obaveze'><c:out value='${subject.gradulation.otherExams}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Посебна назнака за предмет:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_napomene' style='white-space: pre-line'><c:out value='${subject.notes}'></c:out><c:if test='${subject.notes.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
						<tr>
							<td colspan="6"><strong>Име и презиме наставника који је припремио податке:</strong> <div id='content_post_author'><c:out value='${subject.dataMajor}'></c:out><c:if test='${subject.gradulation.gradulationNotes.trim().length() eq 0}'>&nbsp;</c:if></div></td>
						</tr>
					</tbody>
				</table> 
		   </article>
		   <div align='right'><br>
		   	 <form name='subject_loader' method='POST'>
		   	 	 <input type='submit' name='subject_load_submit' value='Учитавање'/>
		   	 </form>
		   	 <c:out value='${linkSubjectBean.message}'></c:out>
		   </div>
		</c:if>
	</body>
</html>