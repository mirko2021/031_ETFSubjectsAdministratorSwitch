"UTF-8"

/**
 * Финкционалности манерварског простора прикупљених линкова, са
 * странице ЕТФ. 
 */
function cb_main_click(){
	var header_cb = document.getElementById('main_cb');
	var semester_numbers = document.getElementById('semesters_number').value;
	for(var i=1; i<=semester_numbers; i++)
		document.getElementById('semester_cb_'+i).checked = header_cb.checked; 
	for(var i=1; i<=semester_numbers; i++)
		cb_semester_click(i);
}

function cb_semesters_checked_all(){
	var semester_numbers = document.getElementById('semesters_number').value;
	var checked = true;
	for(var i=1; i<=semester_numbers; i++)
		checked &= document.getElementById('semester_cb_'+i).checked; 
	return checked; 
}

function cb_semester_click(semester_no){
	var semester_cb = document.getElementById('semester_cb_'+semester_no);
	var subjects_number = document.getElementById('subjects_number_'+semester_no).value;
	for(var i=1; i<=subjects_number; i++)
		document.getElementById('subject_cb_'+semester_no+"_"+i).checked = semester_cb.checked;
	var header_cb = document.getElementById('main_cb');
	document.getElementById('main_cb').checked = cb_semesters_checked_all();
}

function cb_subjects_checked_all(semester_no){
	var subjects_number = document.getElementById('subjects_number_'+semester_no).value;
	var checked = true;
	for(var i=1; i<=subjects_number; i++)
		checked &= document.getElementById('subject_cb_'+semester_no+"_"+i).checked; 
	return checked; 
}

function cb_subject_click(semester_no, subject_no){
	document.getElementById('semester_cb_'+semester_no).checked = cb_subjects_checked_all(semester_no);
	document.getElementById('main_cb').checked = cb_semesters_checked_all();
}