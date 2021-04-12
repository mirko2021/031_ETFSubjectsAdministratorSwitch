"UTF-8"

/**
 *  Функционалности везане за припрему података са едитора, 
 *  који ће бити слани на обраду и пријем, на примјер измјене.
 */

function repack_html_new_lines(data){
	if(!data.includes('<div>')) return data;
	var parser = new DOMParser();
	var xmlDoc = parser.parseFromString(data,"text/html");
	var items = xmlDoc.querySelectorAll('div'); data = '';
	for(var item of items)
		data += item.innerHTML.replace(/<br>/g,"")+"<br>";
	data = data.substring(0, data.length-4);
	return data; 
}

function subject_form_prepare_data(){ 	  
	  var subj_id = document.getElementById('subj_id');
	  var subject_id = document.getElementById('subject_id'); 
	  var subject_name = document.getElementById('subject_name');
	  var semester =  document.getElementById('semestar');
	  var lessons_found = document.getElementById('lessons_found');
	  var ects_value = document.getElementById('ects_value');
	  var lectors = document.getElementById('lectors');
	  var edges = document.getElementById('edges');
	  var edges_form = document.getElementById('edges_form');
	  var subject_target = document.getElementById('subject_target');
	  var subject_realisations = document.getElementById('subject_realisations');
	  var subject_content = document.getElementById('subject_content');
	  var method_lection_transfer = document.getElementById('method_lection_transfer');
	  var references = document.getElementById('references');
	  var data_major = document.getElementById('data_major');
	  var subject_notes = document.getElementById('subject_notes');
	  var subject_state = document.getElementById('subject_state');
	  var labs = document.getElementById('labs');
	  var k1 = document.getElementById('k1');
	  var k2 = document.getElementById('k2');
	  var exams = document.getElementById('exams');
	  var exams_notes = document.getElementById('exams_notes');
	  var other_exams = document.getElementById('other_exams');
	  
	  var content_naziv_predmeta      = document.getElementById('content_naziv_predmeta');
	  var content_sifra_predmeta      = document.getElementById('content_sifra_predmeta');
	  var content_status_predmeta     = document.getElementById('content_status_predmeta');
	  var content_semestar            = document.getElementById('content_semestar');
	  var content_fond_casova         = document.getElementById('content_fond_casova');
	  var content_ectes_bodovi        = document.getElementById('content_ectes_bodovi');
	  var content_nastavnici          = document.getElementById('content_nastavnici');
	  var content_uslovljenost        = document.getElementById('content_uslovljenost');
	  var content_oblik_uslovljenosti = document.getElementById('content_oblik_uslovljenosti');
	  var content_target              = document.getElementById('content_target');
	  var content_results             = document.getElementById('content_results');
	  var content_theme               = document.getElementById('content_theme');
	  var content_methods             = document.getElementById('content_methods');
	  var content_reference           = document.getElementById('content_reference');
	  var content_forme               = document.getElementById('content_forme');
	  var content_prvi_kolokvijum     = document.getElementById('content_prvi_kolokvijum');
	  var content_laboratorije        = document.getElementById('content_laboratorije');
	  var content_ispit               = document.getElementById('content_ispit');
	  var content_drugi_kolokvijum    = document.getElementById('content_drugi_kolokvijum');
	  var content_obaveze             = document.getElementById('content_obaveze');
	  var content_napomene            = document.getElementById('content_napomene');
	  var content_post_author         = document.getElementById('content_post_author');
	 
	  var refs = content_reference.querySelectorAll('li');
	  var refr = [];
	  var first = true;
	  for(var reference of refs){
		  if(reference === '' && first){
			  first = false; 
			  continue;
		  }
		  refr[refr.length] = reference.innerHTML.replace(/<br>/g,""); 
	  }
	  
	  subj_id.value=repack_html_new_lines(content_sifra_predmeta.innerHTML);
	  subject_id.value=repack_html_new_lines(content_sifra_predmeta.innerHTML);
	  subject_name.value=repack_html_new_lines(content_naziv_predmeta.innerHTML);
	  semester.value=repack_html_new_lines(content_semestar.innerHTML);
	  lessons_found.value=repack_html_new_lines(content_fond_casova.innerHTML);
	  ects_value.value=repack_html_new_lines(content_ectes_bodovi.innerHTML);
	  lectors.value=repack_html_new_lines(content_nastavnici.innerHTML);
	  edges.value=repack_html_new_lines(content_uslovljenost.innerHTML);
	  edges_form.value=repack_html_new_lines(content_oblik_uslovljenosti.innerHTML);
	  subject_target.value=repack_html_new_lines(content_target.innerHTML);
	  subject_realisations.value=repack_html_new_lines(content_results.innerHTML);
	  subject_content.value=repack_html_new_lines(content_theme.innerHTML);
	  method_lection_transfer.value=repack_html_new_lines(content_methods.innerHTML);
	  references.value=JSON.stringify(refr);
	  data_major.value=repack_html_new_lines(content_post_author.innerHTML);
	  subject_notes.value=repack_html_new_lines(content_napomene.innerHTML);
	  subject_state.value=repack_html_new_lines(content_status_predmeta.innerHTML);
	  labs.value=repack_html_new_lines(content_laboratorije.innerHTML);
	  k1.value=repack_html_new_lines(content_prvi_kolokvijum.innerHTML);
	  k2.value=repack_html_new_lines(content_drugi_kolokvijum.innerHTML);
	  exams.value=repack_html_new_lines(content_ispit.innerHTML);
	  exams_notes.value=repack_html_new_lines(content_forme.innerHTML);
	  other_exams.value=repack_html_new_lines(content_obaveze.innerHTML);
}

function claer_form_prepare_data(){ 	  
	  var subj_id = document.getElementById('subj_id'); 
	  var subject_id = document.getElementById('subject_id'); 
	  var subject_name = document.getElementById('subject_name');
	  var semester =  document.getElementById('semestar');
	  var lessons_found = document.getElementById('lessons_found');
	  var ects_value = document.getElementById('ects_value');
	  var lectors = document.getElementById('lectors');
	  var edges = document.getElementById('edges');
	  var edges_form = document.getElementById('edges_form');
	  var subject_target = document.getElementById('subject_target');
	  var subject_realisations = document.getElementById('subject_realisations');
	  var subject_content = document.getElementById('subject_content');
	  var method_lection_transfer = document.getElementById('method_lection_transfer');
	  var references = document.getElementById('references');
	  var data_major = document.getElementById('data_major');
	  var subject_notes = document.getElementById('subject_notes');
	  var subject_state = document.getElementById('subject_state');
	  var labs = document.getElementById('labs');
	  var k1 = document.getElementById('k1');
	  var k2 = document.getElementById('k2');
	  var exams = document.getElementById('exams');
	  var exams_notes = document.getElementById('exams_notes');
	  var other_exams = document.getElementById('other_exams');
	  
	  var content_naziv_predmeta      = document.getElementById('content_naziv_predmeta');
	  var content_sifra_predmeta      = document.getElementById('content_sifra_predmeta');
	  var content_status_predmeta     = document.getElementById('content_status_predmeta');
	  var content_semestar            = document.getElementById('content_semestar');
	  var content_fond_casova         = document.getElementById('content_fond_casova');
	  var content_ectes_bodovi        = document.getElementById('content_ectes_bodovi');
	  var content_nastavnici          = document.getElementById('content_nastavnici');
	  var content_uslovljenost        = document.getElementById('content_uslovljenost');
	  var content_oblik_uslovljenosti = document.getElementById('content_oblik_uslovljenosti');
	  var content_target              = document.getElementById('content_target');
	  var content_results             = document.getElementById('content_results');
	  var content_theme               = document.getElementById('content_theme');
	  var content_methods             = document.getElementById('content_methods');
	  var content_reference           = document.getElementById('content_reference');
	  var content_forme               = document.getElementById('content_forme');
	  var content_prvi_kolokvijum     = document.getElementById('content_prvi_kolokvijum');
	  var content_laboratorije        = document.getElementById('content_laboratorije');
	  var content_ispit               = document.getElementById('content_ispit');
	  var content_drugi_kolokvijum    = document.getElementById('content_drugi_kolokvijum');
	  var content_obaveze             = document.getElementById('content_obaveze');
	  var content_napomene            = document.getElementById('content_napomene');
	  var content_post_author         = document.getElementById('content_post_author');
	  
	  content_status_predmeta.innerHTML='';
	  content_semestar.innerHTML=''; 
	  content_fond_casova.innerHTML='';
	  content_ectes_bodovi.innerHTML='';
	  content_nastavnici.innerHTML=''; 
	  content_uslovljenost.innerHTML='';
	  content_oblik_uslovljenosti.innerHTML='';
      content_target.innerHTML='';
      content_results.innerHTML=''; 
      content_theme.innerHTML=''; 
      content_methods.innerHTML=''; 
      content_reference.innerHTML='<li></li>'; 
      content_forme.innerHTML='';
      content_prvi_kolokvijum.innerHTML='0';
      content_laboratorije.innerHTML='0';
      content_ispit.innerHTML='0';
      content_drugi_kolokvijum.innerHTML='0';
      content_obaveze.innerHTML='';
      content_napomene.innerHTML='';
      content_post_author.innerHTML='';
     
      subj_id.value='';
      subject_id.value='';
	  subject_name.value='';
	  semester.value='';
	  lessons_found.value='';
	  ects_value.value='';
	  lectors.value='';
	  edges.value='';
	  edges_form.value='';
	  subject_target.value='';
	  subject_realisations.value='';
	  subject_content.value='';
	  method_lection_transfer.value='';
	  references.value='[]';
	  data_major.value='';
	  subject_notes.value='';
	  subject_state.value='';
	  labs.value='0';
	  k1.value='0';
	  k2.value='0';
	  exams.value='0';
	  exams_notes.value='';
	  other_exams.value='';
}