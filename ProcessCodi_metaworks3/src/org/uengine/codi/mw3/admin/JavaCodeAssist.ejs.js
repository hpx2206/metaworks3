var org_metaworks_example_ide_CodeAssist = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	$('#tabs_assist').smartTab({autoProgress: false,transitionEffect:'none'});
	
	var buttons = { previous:$('#jslidernews3 .button-previous') ,
					next:$('#jslidernews3 .button-next') };
	$('#jslidernews3').lofJSidernews( { interval:5000,
										 	easing:'easeOutExpo',
											duration:200,
											auto:false,
											mainWidth:580,
											mainHeight:300,
											navigatorHeight		: 27,
											navigatorWidth		: 100,
											maxItemDisplay:9,
											buttons:buttons,
											keyNavigation:false} );
	
	
	
	var object = mw3.objects[this.objectId];
	var thisFaceHelper = this;
	
	if(object==null || object.assistances.length==0){
		var sourceCode = mw3.objects[object.srcCodeObjectId];		
		var editor = sourceCode.__getFaceHelper().editor;
		
		$("#" + mw3.popupDivId).remove();
		editor.focus();
	} else {
		if(object.assistances.length==1 && $("#objDiv_" + objectId).find(".active").attr("assistname").indexOf(".") > 0) {		
	
			var value = $("#objDiv_" + objectId).find(".active").attr("assistname");
			if(value.indexOf(".") > 0) thisFaceHelper.enter(value);
			
		} else {
			$('#assist_text_' + objectId).unbind('keyup');
			$('#assist_text_' + objectId).bind('keyup', function(e){
				if (e && e.keyCode==13){
					var value = $("#objDiv_" + objectId).find(".active").attr("assistname");
					
					//$("#objDiv_" + objectId).find(".active").click();					
					thisFaceHelper.enter(value);					
				}else if(e && e.keyCode==38){
					$("#assist_up").click();
				}else if(e && e.keyCode==40){
					$("#assist_down").click();
				}else{
					var key = $('#assist_text_' + objectId).val();
					
					$(".navigator-content li").each(function(index) {
						console.debug($(this));
						
						var value = $(this).attr("assistname");
						
						if(value.substring(0, key.length) == key){						
							var index = $(this).attr("index");						
							
							seft.jumping(index, true);
							seft.setNavActive(index);
							thisFaceHelper.requestDoc(value);
							
							return false;
						}
					});
				}
			});
			
			$('#atabs-1').click(function(){
				$('#assist_text_' + objectId).focus();
			});
				
			$("#assist_text_" + objectId).focus();
			
			$("#assist_up").click(function(event){
				$('#assist_text_' + objectId).focus();
				
				var value = $("#objDiv_" + objectId).find(".active").attr("assistname");
				
				thisFaceHelper.requestDoc(value);
			});
			
			
			$("#assist_down").click(function(event){
				$('#assist_text_' + objectId).focus();
				
				var value = $("#objDiv_" + objectId).find(".active").attr("assistname");
				
				thisFaceHelper.requestDoc(value);
			});		
		}
	}
	
	object.ExtendImport = function(){
		console.debug('extendImport');
		console.debug(objectId);
		
		var object = mw3.objects[objectId];
		
		console.debug(object);
		
		var sourceCode = mw3.objects[object.srcCodeObjectId];
		
		var editor = sourceCode.__getFaceHelper().editor;
		editor.insert(object.extendImportValue);
		editor.focus();
		
		$("#" + mw3.popupDivId).remove();
		editor.focus();		
	}
}

org_metaworks_example_ide_CodeAssist.prototype.enter = function(value){
	
	var object = mw3.objects[this.objectId];
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
	}
	
	editor.focus();
	
	$("#" + mw3.popupDivId).remove();
}

org_metaworks_example_ide_CodeAssist.prototype.requestDoc = function(value) {
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