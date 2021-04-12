"UTF-8"

/**
 * Баратање при биљежењу када су програми у питању. 
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

function inform_glue_program_notes(){
	var dest = document.getElementById('accept_glue_note_form');
	var fields = document.querySelectorAll('div[contenteditable=true]'); 
	for(var field of fields){
		var input = document.createElement("input");
		dest.appendChild(input);
		input.type='hidden';
		input.name = field.id; 
		input.value = encodeURI(repack_html_new_lines(field.innerHTML));
	}
}