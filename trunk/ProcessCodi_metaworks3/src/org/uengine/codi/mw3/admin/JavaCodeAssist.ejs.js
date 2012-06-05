var org_uengine_codi_mw3_admin_JavaCodeAssist = function(objectId, className){

	this.objectId = objectId;
	this.className = className;

	this.defaultScroll = 14;
	this.scrollY = 14;
	
	this.list = $("#assistancesList_" + this.objectId);
	
	// make smart tab
	$('#tabs_assist').smartTab({autoProgress: false,transitionEffect:'none'});

	// first item focusing
	var object = mw3.objects[this.objectId];
	
	if(object.assistances.length > 0)
		this.list.children(':first').css('background', 'yellow').addClass('selected');	
	
	object.ExtendImport = function(){
		var object = mw3.objects[objectId];
		
		var sourceCode = mw3.objects[object.srcCodeObjectId];
		
		var editor = sourceCode.__getFaceHelper().editor;
		editor.insert(object.extendImportValue);
		editor.focus();
		
		$("#" + mw3.popupDivId).remove();
		editor.focus();		
	}
}

org_uengine_codi_mw3_admin_JavaCodeAssist.prototype.change = function(expression){
	expression = expression.toLowerCase();
	console.debug('expression : ' + expression);
	
	var pos = expression.lastIndexOf('.');
	var expressionClass = '';
	var expressionPackage = '';
	
	if(pos != -1){
		expressionClass = expression.substring(pos+1);
		expressionPackage = expression.substring(0, pos);
	}
		
	console.debug('expressionClass : ' + expressionClass);
	
	var object = mw3.objects[this.objectId];

	var html = '';

	for(var i in object.assistances){
		var assistance = object.assistances[i].toLowerCase();
				
		if(assistance.indexOf(expression) == 0 || (assistance.indexOf(expressionClass) == 0 && assistance.indexOf('/'+expressionPackage+'/') != -1 )){
			assistance = object.assistances[i];
			
			var packageName = '';
			var assistType = 'package';
			
			if(assistance.indexOf('/') == -1){
				assistance += '.*'; 
			}else{
				var temp = assistance.split('/');
				
				assistance = temp[0];
				packageName = ' - ' + temp[1];
				assistType = temp[2];
			}

			
			html += '<li onclick=\"mw3.getFaceHelper(\'' + this.objectId + '\').select();\" index=\"' + i + '\">';
			html += '	<span class=\"' + assistType + '\">' + assistance + '</span>';
			if(packageName.length > 0)
				html += '	<span>' + packageName + '</span>';
			html += '</li>';
		}		
	}
	
	//console.debug(html);
	//console.debug('size : ' + object.assistances.length);
	
	if(html == ''){
		mw3.removeObject(this.objectId);
		
		return false;
	}
	
	this.list.html(html);
	
	this.list.children(':first').addClass('selected').css('background', 'yellow');
}

org_uengine_codi_mw3_admin_JavaCodeAssist.prototype.up = function(){
	mw3.log('up');
	
	var prev = this.list.children('.selected').prev();
	
	if(prev.length > 0){
		this.list.children().removeClass('selected').css('background', '');
		prev.addClass('selected').css('background', 'yellow');
		
		this.defaultScroll = this.defaultScroll - 14;
		
		if(this.scrollY > this.defaultScroll){		
			$("#container_content").scrollTop(this.scrollY-28);
			this.scrollY = this.scrollY - 14
		}				
	}	
}


org_uengine_codi_mw3_admin_JavaCodeAssist.prototype.down = function(){
	mw3.log('down');
	
	var next = this.list.children('.selected').next();
	
	if(next.length > 0){
		this.list.children().removeClass('selected').css('background', '');
		next.addClass('selected').css('background', 'yellow');
		
		this.defaultScroll = this.defaultScroll + 14;
		
		if($("#container_content").height() < this.defaultScroll){		
			$("#container_content").scrollTop(this.scrollY);
			this.scrollY = this.scrollY + 14
		}		
	}
}

org_uengine_codi_mw3_admin_JavaCodeAssist.prototype.select = function(){
	
	var selected = this.list.children('.selected');
	var index = selected.attr('index');
	
	var object = mw3.objects[this.objectId];
	
	console.debug(object);
	console.debug(index);
	
	return object.assistances[index];
	
/*	var selected = this.list.children('.selected');
 *  var html = '';
	
	if(selected.length > 0){
		var child = selected.children(':first');
		
		html = child.html();
		
		if(child.next().length > 0)
			html += child.next().html().trim();
	}
		 
	return html;
*/	
	/*var object = mw3.objects[this.objectId];
	var sourceCode = mw3.objects[object.srcCodeObjectId];
	
	var editor = sourceCode.__getFaceHelper().editor;
	
	var keyOptions = '';
	if(value.indexOf("-") > -1) {
		keyOptions = value.substring(value.lastIndexOf("-") + 1);
		value = value.substring(0, value.lastIndexOf("-"));
	}
	
	if(keyOptions == 'f3') {
		editor.focus();
		
		$("#" + mw3.popupDivId).remove();
		
		alert(value + " 파일 불러와서 여기에 띄우면 됨.");
		
		return;
	}
	
	if(value.indexOf(".") < 0 && value.indexOf("@") < 0) {
		
		editor.insert(value);
		
	} else {		
		
		var whereEnd = editor.getCursorPosition();
		
		var values = value.split(" ");
		if(value.indexOf("@") > -1) {			
			editor.insert(values[0].substring(1));
		}
		
		var check = false;
		
		for(var pos = whereEnd.row; pos >= 0; pos--) {
			var fullLine = editor.getSession().doc.getLine(pos);
			var lines = fullLine.split(" ");	
			if(lines[0] == "import" && (fullLine.substring(fullLine.lastIndexOf("import") + 7) == (value + ";"))) {
				check = true;
				break;
			}
		}
		
		if(!check) {
			for(var pos = whereEnd.row; pos >= 0; pos--) {
				var fullLine = editor.getSession().doc.getLine(pos);
				var lines = fullLine.split(" ");
				
				if(lines[0] == "import" || lines[0] == "package") {
					//editor.getSession().getSelection().selectionLead.setPosition(0, ++pos); 
					editor.moveCursorTo(++pos, 0);
					if(value.indexOf("@") > -1) {
						editor.insert("import " + values[1] + "." + values[0].substring(1) + ";\n");
					} else {
						editor.insert("import " + value + ";\n");
					}
					editor.moveCursorTo(whereEnd.row + 1, whereEnd.column);
					break;
				}
			}
			
			if(pos < 0) {
				editor.moveCursorToPosition(0, 0);
				if(value.indexOf("@") > -1) {
					editor.insert("import " + values[1] + "." + values[0].substring(1) + ";\n");
				} else {
					editor.insert("import " + value + ";\n");
				}
				editor.moveCursorTo(whereEnd.row + 1, whereEnd.column);
			}
		}
	}*/
	
	mw3.removeObject(this.objectId);
}

org_uengine_codi_mw3_admin_JavaCodeAssist.prototype.requestDoc = function(value) {
	var object = mw3.objects[this.objectId];

	var sourceCode = mw3.objects[object.srcCodeObjectId];
	var theEditor = sourceCode.__getFaceHelper().editor;

	var whereEnd = theEditor.getCursorPosition();
	var whereStart = {column: 0, row: whereEnd.row};
	
	
	var line = theEditor.getSession().doc.getTextRange({start: whereStart, end: whereEnd});

	object.selectedItem = value;
	object.lineAssistRequested = line; 
	object.showDoc();
}

