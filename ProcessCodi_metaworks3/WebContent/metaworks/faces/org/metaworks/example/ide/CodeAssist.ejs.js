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
	}else{
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

org_metaworks_example_ide_CodeAssist.prototype.enter = function(value){
	var object = mw3.objects[this.objectId];
	var sourceCode = mw3.objects[object.srcCodeObjectId];
	
	var editor = sourceCode.__getFaceHelper().editor;
	editor.insert(value);
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