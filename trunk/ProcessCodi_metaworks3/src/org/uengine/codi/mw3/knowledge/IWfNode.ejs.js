var workingWfNode = null;

var org_uengine_codi_mw3_knowledge_IWfNode = function(objectId, className){
	console.debug(objectId);
	
	var thisFaceHelper = this;
	
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	
	this.obj = $('#objDiv_' + this.objectId);
	this.mw3Obj = mw3.objects[objectId];
	 
	
	this.prevName = this.mw3Obj.name;	
	this.keyword = this.mw3Obj.name;
	this.timeout;
	this.process = false;
		
	var content = this.content = $('#wfnode_content_' + this.objectId);
	
	if(this.mw3Obj.metaworksContext.when != 'read'){
		content.focus(function() {
			var value = content.val();
			
			if(mw3.getFaceHelper(objectId) && mw3.getFaceHelper(objectId).change) 
					mw3.getFaceHelper(objectId).change(value);			
		});
		content.blur(function() {
			var faceHelper = mw3.getFaceHelper(objectId);
			
			var value = content.val();
			if(faceHelper){
				if (faceHelper.timeout) {
					clearTimeout(faceHelper.timeout);
				}	
				
				if (faceHelper.prevName != value){
					faceHelper.prevName = value;
					
					faceHelper.mw3Obj.name = value;			
					faceHelper.mw3Obj.save();
				}
			}
		});
		
		if(this.mw3Obj && this.mw3Obj.focus){
			content.focus();
			
			var how = this.mw3Obj.metaworksContext.how;
			var name = (this.mw3Obj.name?this.mw3Obj.name:'');
			var nameNext = (this.mw3Obj.nameNext?this.mw3Obj.nameNext:'');

			if(how == "add" || how == "new"){
				content.selectRange(0, 0);
			}else if(how == "remove" || how == "indent" || how == "outdent"){
				var pos = name.length - nameNext.length;

				content.selectRange(pos, pos);
			}

		}

		var object = this.mw3Obj;
		$('#wfnode_' + this.objectId).droppable({
			hoverClass: "ui-state-active",
			drop: function( event, ui ) {
				var dragNodeId = ui.draggable.attr("objectId");
				
				var list = $('#objDiv_' + dragNodeId).find('.workflowy_node');
				for(var i=0; i<list.length; i++){
					if($(list[i]).attr('objectId') == objectId){
						alert('옮길 수 없는 위치입니다.');
						
						return false;
					}
				}
				
				object.dragNode = mw3.objects[dragNodeId]; 
				
				mw3.call(objectId, "move");
			}
		});

		$('#wfnode_' + objectId).hover(			
				function () {
					if(!$('#move').hasClass("moving")){
						var html = '';

						html += '<div id=\"controls\">';  
						html += '  <div id=\"controlsRight\">';
						html += '    <a id=\"move\" title=\"Drag to move\"></a>';
						html += '  </div>'; 
						html += '</div>';

						$(this).append(html);

						$("#controls>#controlsRight>#move").draggable({
							helper: "clone",
							cursor: "move",
							distance: 1,
							start: function(event, ui) {								
								$(this).attr("objectId", objectId);				        	
								$(this).addClass("moving");
							},
							stop: function(event, ui) {
								$('#controls').remove();
							}
						});
					}
				}, 
				function () {	
					if(!$('#move').hasClass("moving"))
						$(this).find('#controls').remove();			  
				}

		);	
	}
	this.obj.addClass("workflowy_node").attr("objectId", objectId).attr("nodeId", this.mw3Obj.id).attr('parentId', this.mw3Obj.parentId).css("position", "relative");
}

org_uengine_codi_mw3_knowledge_IWfNode.prototype = {
		getPrev : function(){
			var searchObj = this.obj;
				
			do{
				searchObj = searchObj.prev();
				
				if(searchObj.hasClass('workflowy_node'))
					break;
			}while(searchObj.length > 0)
				
			return searchObj;
			
		},
		getNext : function(){
			var searchObj = this.obj;
				
			do{
				searchObj = searchObj.next();
				
				if(searchObj.hasClass('workflowy_node'))
					break;
			}while(searchObj.length > 0)
				
			return searchObj;
			
		},
		getParent : function(){			
			var searchObj = this.obj;
				
			do{
				searchObj = searchObj.parent();
				
				if(searchObj.hasClass('workflowy_node'))
					break;
			}while(searchObj.length > 0)
				
			return searchObj;			
		},
		getFirstChild : function(){
			return this.obj.find('.workflowy_node :first').parent();
			var searchObj = mw3.getFaceHelper(this.objectId).obj;
		},
		getValue : function(){
			return this.mw3Obj;
		},
		focus : function(){
			this.content.focus();
		},
		up : function(isRight){
			var focus = this.getPrev();
			if(focus.length == 0){
				focus = this.getParent();
			}

			if(focus.length > 0){
				var objectId = focus.attr('objectId');
			
				mw3.getFaceHelper(objectId).focus();
				
				// 커서 위치 조정
				var input = focus.find('#wfnode_content_' + objectId);				
				if(isRight){
					var pos = input.val().length;

					input.selectRange(pos,pos);
				} else {
					input.selectRange(0,0);
				}				
			}

		},
		down : function(){

			// 자식 검색
			var searchObj = $('#objDiv_' + this.objectId).find('.workflowy_node:first');
			
			// 자식 미존재, 동일 노드 하위 검색(존재하지 않으면 부모의 동일노드 하위 검색)
			if(!searchObj.length){
				var info = false;
				var exist = false;   

				searchObj = $('#objDiv_' + this.objectId);

				while(!exist && searchObj.length && searchObj.hasClass('workflowy_node')){
					var temp = searchObj.nextUntil('.workflowy_node');

					if(temp.attr('id').indexOf('info_') == 0)
						info = true;
					else
						info = false;

					if(info){
						exist = (temp.next().length > 0);
					}else
						exist = (temp.length > 0);	   

					if(exist){
						if(info)
							searchObj = temp.next();
						else
							searchObj = temp;
					}else{
						searchObj = temp.parent().parent().parent().parent('div');
					}		   
				}
			} 

			if(searchObj.length && searchObj.hasClass('workflowy_node')){
				searchObj = searchObj.find('input:first');

				searchObj.focus();
				searchObj.selectRange(0,0);
			}		   
		},
		change : function(value){
			this.keyword = value;
			
			if (this.timeout) {
				clearTimeout(this.timeout);
			}			
			
			this.timeout = setTimeout(function() {
				var mashup = mw3.getAutowiredObject('org.uengine.codi.mw3.knowledge.MashupGoogleImage');
				var mashupTool = null;
				
				if(mashup)
					mashupTool = mashup.__getFaceHelper();
				
				if(mashupTool){
					if(value == '')
						mashupTool.clear();
					else
						mashupTool.search(value);
				}
			}, 1000);
			
			
		},
		calcValue : function(inputObj){
			var t = inputObj.value, s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj);

			if(s == t.length){
				this.mw3Obj.name = t;
				this.mw3Obj.nameNext = "";
			}else if(s == 0){
				this.mw3Obj.name = "";
				this.mw3Obj.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';		    	  			   
			}else{ //커서의 위치에 따라서 글을 자르는 로직
				this.mw3Obj.name = t.substring(0, s).replace(/ /g, '\xa0') || '\xa0';
				this.mw3Obj.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';
			}			
		},
		keyup : function(inputObj){
			var keyword = inputObj.value;
			
			if(this.keyword == keyword)
				return true;
			
			this.change(keyword);
		},
		keydown : function(inputObj){
			workingWfNode = this;

			if(this.process){
				window.event.returnValue = false;

				return;
			}
			
			var event = window.event;
			
			switch (event.keyCode) {
			case 37   :   // left
				var s = this.getSelectionStart(inputObj);

				if(s == 0){
					window.event.returnValue = false;

					this.up(true);			   
				}

				break;
			case 38   :   // up		   
				this.up();

				window.event.returnValue = false;

				break;

			case 39   :   // right
				var s = this.getSelectionStart(inputObj);

				if(s == inputObj.value.length){
					window.event.returnValue = false;

					this.down();
				}

				break;
			case 40   :   // down
				window.event.returnValue = false;
				this.down();

				break;

			case 9    :	// tab
				window.event.returnValue = false;
				
				this.calcValue(inputObj);

				if(event.shiftKey){					
					if(this.mw3Obj.parentId != '-1'){
						var parent = this.getParent();	
						if(parent.length > 0){
							var parentId = parent.attr('objectId');
														
							if(parentId != '-1'){
								this.mw3Obj.outdent();	
							}
						} 
					}
				}else{
					if(this.mw3Obj.no > 0)
						mw3.call(this.objectId, 'indent');
				}

				break;

			case 13    :	// enter		 
				window.event.returnValue = false;
				
				this.calcValue(inputObj);
				this.mw3Obj.add();

				break;
			case 8     :	// back
				this.calcValue(inputObj);
				
				var s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj);

				if(s == e && s == 0){
					if(this.mw3Obj.no > 0 || this.mw3Obj.parentId != '-1'){
						window.event.returnValue = false;
						
						this.mw3Obj.remove();
					}
				}

				break;
			case 46    :	// del
				var info = false;
				var exist = false;   

				searchObj = $('#objDiv_' + this.objectId);

				var temp = searchObj.nextUntil('.workflowy_node');

				if(temp.attr('id').indexOf('info_') == 0)
					info = true;
				else
					info = false;

				if(info){
					exist = (temp.next().length > 0);
				}else
					exist = (temp.length > 0);	   

				if(exist){
					if(info)
						searchObj = temp.next();
					else
						searchObj = temp;

					//object.name = $('#wfnode_content_' + this.objectId).val();

					targetObjectId = searchObj.attr('objectId');			   
					targetObject = mw3.objects[targetObjectId];

					targetObject.nameNext = targetObject.name;

					mw3.call(targetObjectId, 'remove');
				}		   
			default    :
				break;
			}
		},
		getSelectionStart : function(o) {
			if (o.createTextRange) {
				var r = document.selection.createRange().duplicate()
				r.moveEnd('character', o.value.length)
				if (r.text == '') return o.value.length
				return o.value.lastIndexOf(r.text)
			} else return o.selectionStart
		},
		getSelectionEnd : function(o) {
			if (o.createTextRange) {
				var r = document.selection.createRange().duplicate()
				r.moveStart('character', -o.value.length)
				return r.text.length
			} else return o.selectionEnd
		},
		destroy : function(){
			if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
				mw3.getFaceHelper(this.windowObjectId).endLoading();
		},
		startLoading : function(){
			this.process = true;
			
			if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
				mw3.getFaceHelper(this.windowObjectId).startLoading();
		},
		endLoading : function(){
			this.process = false;
			if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
				mw3.getFaceHelper(this.windowObjectId).endLoading();
		},
		showStatus : function(message){
			this.process = false;
			
			console.debug(message);
		},
		showError : function(message){
			this.process = false;
			
			alert(message);
		},
				
		insertNodeAfter: function(googleData){						
			this.mw3Obj.nameNext = googleData.tbUrl;
			this.mw3Obj.typeNext = "img";
			this.mw3Obj.add();
		}

}