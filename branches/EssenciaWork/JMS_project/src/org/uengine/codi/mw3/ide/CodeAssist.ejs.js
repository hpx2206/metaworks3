var org_uengine_codi_mw3_ide_CodeAssist = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv.css('display', 'none').find('.cc_complete_option')
		.bind('mouseenter', function(){
			$('.cc_complete_option.selected').removeClass('selected');
			
			$(this).addClass('selected');
		})
		.bind('click', function(){
			mw3.getFaceHelper(objectId).select();
		});
	
	$('body').one('click', {targetDivId : this.objectDivId}, function(event){
		$('#' + event.data.targetDivId).remove();
	});

};

org_uengine_codi_mw3_ide_CodeAssist.prototype = {
	getEditor : function(){
		return mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.Editor@' + this.object.editorId);
	},
	
	loaded : function(){
		var editor = this.getEditor();
		
		var editorFaceHelper = editor.getFaceHelper();
		
		if(editorFaceHelper.loadedCodeAssist){
			editorFaceHelper.loadedCodeAssist();
		}
	},
	
	getSelectedAssist : function(){
		var selectedAssist = this.objectDiv.find('.cc_complete_option.selected');
		
		return this.object.assistMap[selectedAssist.attr('index')];
	},
	
	up : function(){
		var selectedAssist = this.objectDiv.find('.cc_complete_option.selected');
		
		if(selectedAssist.length > 0){
			var prevAssist = selectedAssist.prev();
			
			if(prevAssist.length > 0){
				selectedAssist.removeClass('selected');
				prevAssist.addClass('selected');
			}
			
			
			/*
			this.defaultScroll = this.defaultScroll - 14;
			
			if(this.scrollY > this.defaultScroll){		
				$("#container_content").scrollTop(this.scrollY-28);
				this.scrollY = this.scrollY - 14
			}
			*/			
		}	
		
	},
	
	down : function(){
		var selectedAssist = this.objectDiv.find('.cc_complete_option.selected');
		
		if(selectedAssist.length > 0){
			var nextAssist = selectedAssist.next();
			
			if(nextAssist.length > 0){
				selectedAssist.removeClass('selected');
				nextAssist.addClass('selected');
			}
			
			
			/*
			this.defaultScroll = this.defaultScroll - 14;
			
			if(this.scrollY > this.defaultScroll){		
				$("#container_content").scrollTop(this.scrollY-28);
				this.scrollY = this.scrollY - 14
			}
			*/			
		}
	},
	
	change : function(expression){
		expression = expression.toLowerCase();
		
		var expressionClass = expression;
		var expressionPackage = '';
		
		var pos = expression.lastIndexOf('.');
		if(pos != -1){
			expressionClass = expression.substring(pos+1);
			expressionPackage = expression.substring(0, pos);
		}
			
		var object = mw3.objects[this.objectId];
		var assistMapObjectDiv = this.objectDiv.find('.cc_complete_option');		
		var isFirst = true;
		
		assistMapObjectDiv.filter('.selected').removeClass('selected');
		
		for(var i=0; i<object.assistMap.length; i++){
			var cnt = 0;
			var assist = object.assistMap[i].toLowerCase();
			
			var isInsert = false;
			
			if(assist.indexOf('/package/') != -1){
				if(assist.indexOf(expression) == 0)
					isInsert = true;
			}else{
				if(assist.indexOf(expressionClass) == 0)
					isInsert = true;
			}
			
			if(isInsert)
				$(assistMapObjectDiv[i]).show();
			else
				$(assistMapObjectDiv[i]).hide();
		}
		
		assistMapObjectDiv.filter(':visible:first').addClass('selected');
	},
	
	select : function(){
		var editor = this.getEditor();
		
		if(editor){
			editorFaceHelper = mw3.getFaceHelper(editor.__objectId);
			
			if(editorFaceHelper && editorFaceHelper.selectAssist){
				editorFaceHelper.selectAssist();
				editorFaceHelper.editor.focus();
			}
			
			
		}
	},
};