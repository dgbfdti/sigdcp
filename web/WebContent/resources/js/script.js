/**
 * 
 */
function stopRKey(evt) {
	alert('Appele');
	var evt = (evt) ? evt : ((event) ? event : null);
	var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement
			: null);
	if ((evt.keyCode == 13) && (node.type == "text")) {
		return false;
	}
}


