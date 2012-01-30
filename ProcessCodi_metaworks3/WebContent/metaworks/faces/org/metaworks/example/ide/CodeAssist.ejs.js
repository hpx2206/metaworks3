var org_metaworks_example_ide_CodeAssist = function(objectId, className){
	this.objectId = objectId;

	var sel = document.getElementById("assist_" + objectId );
	sel.selectedIndex = 1; 
	sel.focus();
	
	var thisFaceHelper = this;
	
   document.addEventListener(
		"keyup",

   		function(e) {
			if (e && e.keyCode==13 && sel == e.srcElement){
				thisFaceHelper.enter(sel);
			}
		},
		
		false
	);

}

org_metaworks_example_ide_CodeAssist.prototype.enter = function(sel){
	var object = mw3.objects[this.objectId];
	var sourceCode = mw3.objects[object.srcCodeObjectId];
	
	var value = sel.options[sel.selectedIndex].value;
	
	var editor = sourceCode.__getFaceHelper().editor;
	editor.insert(value);
	editor.focus();
	
	$("#" + mw3.popupDivId).remove();
}