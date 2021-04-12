<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="subjectBean" class='zenit.web.bean.SubjectBean' scope='session'></jsp:useBean>
<c:out value="${subjectBean.initialize(pageContext.session)}"></c:out>
<c:out value="${subjectBean.subjectController.refresh()}"></c:out>
<c:set var='subject' value='${subjectBean.subjectController.get(param["subject_id"])}'></c:set>

<c:if test='${param["potvrda"] ne null}'>
	<c:out value='${subjectBean.update(pageContext.request)}'></c:out>
	<c:if test="${param['subj_id'] ne param['subject_id'] and subjectBean.success}">
		<c:redirect url='?subject_id=${param["subj_id"]}'></c:redirect>
	</c:if>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Study Subjects</title>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/general.css'></link>
		<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/WEB-CSS/links.css'></link>
		<script type='text/javascript' src='${pageContext.request.contextPath}/WEB-JS/editors.js'></script>
	</head>
	<body>
		<h2>Study Manervar</h2>
		<c:if test='${subject eq null}'>
			Не постоји предмет за преглед.
		</c:if>
		<c:if test='${subject ne null}'>
			<article class="item-page">
				<table class="predmet" style='width:100%'>
					<tbody>
						<tr>
							<td class="bijeli_naslov" style='white-space: pre-line'>Назив предмета</td>
							<th colspan="4"><div id='content_naziv_predmeta' contenteditable="true" style='white-space: pre-line'><c:out value='${subject.subjectName}'></c:out></div></th>
						</tr>
						<tr class="centriran_bijeli_naslov">
							<td style='text-align:left'>Шифра предмета</td>
							<td>Статус предмета</td>
							<td>Семестар</td>
							<td>Фонд часова</td>
							<td>Број ECTS бодова</td>
						</tr>
						<tr class="centriran">
							<td style='text-align:right'><div id='content_sifra_predmeta' contenteditable="true"><c:out value='${subject.subjectId}'></c:out></div></td>
							<td><div id='content_status_predmeta' contenteditable="true" style='white-space: pre-line'><c:out value='${subject.subjectState}'></c:out></div></td>
							<td><div id='content_semestar' contenteditable="true" style='white-space: pre-line'><c:out value='${subject.semestar}'></c:out></div></td>
							<td><div id='content_fond_casova' contenteditable="true" style='white-space: pre-line'><c:out value='${subject.lessonsFound}'></c:out></div></td>
							<td><div id='content_ectes_bodovi' contenteditable="true" style='white-space: pre-line'><c:out value='${subject.ectsValue}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov">Наставници</td>
							<td colspan="4"><div id='content_nastavnici' contenteditable="true" style='white-space: pre-line'><c:out value='${subject.lectors}'></c:out></div></td>
						</tr>
					</tbody>
				</table>
				<p>&nbsp;</p>
				<table class="predmet">
					<tbody>
						<tr>
							<td class="plavi_naslov" colspan="4">Условљеност другим предметима</td>
							<td class="plavi_naslov" colspan="2" style='width:33%'>Облик условљености</td>
						</tr>
						<tr>
							<td colspan="4"><div style='white-space: pre-line' id='content_uslovljenost' contenteditable="true"><c:out value='${subject.edges}'></c:out></div></td>
							<td colspan="2" style='width:33%'><div style='white-space: pre-line' id='content_oblik_uslovljenosti' contenteditable="true"><c:out value='${subject.edgesForm}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Циљеви изучавања предмета:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_target' style='white-space: pre-line' contenteditable="true"><c:out value='${subject.subjectTarget}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Исходи учења (стечена знања):</td>
						</tr>
						<tr>
							<td colspan="6"><div style='white-space: pre-line' id='content_results' contenteditable="true"><c:out value='${subject.subjectRealisations}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Садржај предмета:</td>
						</tr>
						<tr>
							<td colspan="6"><div style='white-space: pre-line' id='content_theme' contenteditable="true"><c:out value='${subject.subjectContent}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Методе наставе и савладавање градива:</td>
						</tr>
						<tr>
							<td colspan="6"><div id='content_methods' style='white-space: pre-line' contenteditable="true"><c:out value='${subject.methodLectionTransfer}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Литература:</td>
						</tr>
						<tr>
							<td colspan="6">
								<ol contenteditable="true" id='content_reference'>
									<c:if test='${subject.references.size() eq 0}'>
										<li></li>
									</c:if>
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
							<td colspan="6"><div style='white-space: pre-line' id='content_forme' contenteditable="true"><c:out value='${subject.gradulation.gradulationNotes}'></c:out></div></td>
						</tr>
						<tr>
							<td>Први колоквијум</td>
							<td class="bodovi"><div style='white-space: pre-line' contenteditable="true" id='content_prvi_kolokvijum'><c:out value='${subject.gradulation.firstColocvium}'></c:out></div></td>
							<td>Лабораторијске вјежбе</td>
							<td class="bodovi"><div style='white-space: pre-line' contenteditable="true" id='content_laboratorije'><c:out value='${subject.gradulation.laboratoryExercies}'></c:out></div></td>
							<td>Завршни испит</td>
							<td class="bodovi"><div style='white-space: pre-line' contenteditable="true" id='content_ispit'><c:out value='${subject.gradulation.finalExams}'></c:out></div></td>
						</tr>
						<tr>
							<td valign='top'>Други колоквијум</td>
							<td valign='top' class="bodovi"><div style='white-space: pre-line'   contenteditable="true" id='content_drugi_kolokvijum'><c:out value='${subject.gradulation.secondColocvium}'></c:out></div></td>
							<td valign='top' colspan="4" style='width:66%'><div contenteditable="true" id='content_obaveze'><c:out value='${subject.gradulation.otherExams}'></c:out></div></td>
						</tr>
						<tr>
							<td class="plavi_naslov" colspan="6">Посебна назнака за предмет:</td>
						</tr>
						<tr>
							<td colspan="6"><div style='white-space: pre-line' id='content_napomene' contenteditable="true"><c:out value='${subject.notes}'></c:out></div></td>
						</tr>
						<tr>
							<td colspan="6"><strong>Име и презиме наставника који је припремио податке:</strong> <div id='content_post_author' contenteditable="true"><c:out value='${subject.dataMajor}'></c:out></div></td>
						</tr>
					</tbody>
				</table>
		   </article>
		   <form method='POST'>
		   		<input type='hidden' name='subject_id'                    id='subject_id'/>
		   		<input type='hidden' name='subj_id'                       id='subj_id'/>
		   		<input type='hidden' name='subject_name'                  id='subject_name'/>
		   		<input type='hidden' name='semestar'                      id='semestar'/>
		   		<input type='hidden' name='lessons_found'                 id='lessons_found'/>
		   		<input type='hidden' name='ects_value'                    id='ects_value'/>
		   		<input type='hidden' name='lectors'                       id='lectors'/>
		   		<input type='hidden' name='edges'                         id='edges'/>
		   		<input type='hidden' name='edges_form'                    id='edges_form'/>
		   		<input type='hidden' name='subject_target'                id='subject_target'/>
		   		<input type='hidden' name='subject_realisations'          id='subject_realisations'/>
		   		<input type='hidden' name='subject_content'               id='subject_content'/>
		   		<input type='hidden' name='method_lection_transfer'       id='method_lection_transfer'/>
		   		<input type='hidden' name='references'                    id='references'/>
		   		<input type='hidden' name='data_major'                    id='data_major'/>
		   		<input type='hidden' name='subject_notes'                 id='subject_notes'/>
		   		<input type='hidden' name='subject_state'                 id='subject_state'/>
		   		<input type='hidden' name='labs'                          id='labs'/>
		  		<input type='hidden' name='k1'                            id='k1'/>
		  		<input type='hidden' name='k2'                            id='k2'/>
		  		<input type='hidden' name='exams'                         id='exams'/>
		  		<input type='hidden' name='exams_notes'                   id='exams_notes'/>
		  		<input type='hidden' name='other_exams'                   id='other_exams'/>
		  		<input type='hidden' name='ref_id'  value='${subject.subjectId}'/>
		  		<br><br>
		  		<div align='right'>
		  			<input type='submit' value='Потврда' name='potvrda' id='potvrda' onclick='subject_form_prepare_data()' />
		  			<input type='submit' value='текуће'  name='tekuce'  id='tekuce'                                       />
		  			<input type='submit' value='празно'  name='prazno'  id='prazno'                                      />
		  			<c:if test='${param["potvrda"] eq null}'>
		  				<br><div><c:out value='${subjectBean.message}'></c:out></div>
		  			</c:if>
		  		</div>
		   </form>
		</c:if>
		<c:if test='${param["potvrda"] ne null}'>
			<script>document.getElementById('tekuce').click()</script>
		</c:if>
		<c:if test='${param["prazno"] ne null}'>
			<script>claer_form_prepare_data()</script>
		</c:if>
	</body>
</html>

