var workingWfNode = null;

var org_uengine_codi_mw3_knowledge_WfNode = function(objectId, className){
	var object = mw3.objects[objectId];
	
	var thisFaceHelper = this;
	
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.prevName = object.name;
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	this.wfInfoDiv = "#wfinfo_" + objectId;
	
	var content = $('#wfnode_content_' + this.objectId);
	
	if(object.metaworksContext.when != 'read'){
		content.blur(function() {
			var value = content.val();
			if(mw3.getFaceHelper(objectId).prevName != value){
				mw3.getFaceHelper(objectId).prevName = value;
				
				object.name = value;			
				object.save();
			}
		});
		
		content.keyup(function(){
			
			workingWfNode = thisFaceHelper;
			
			var value = content.val();
			var mashup = mw3.getAutowiredObject('org.uengine.codi.mw3.knowledge.MashupGoogleImage');
			var mashupTool = null;
			
			if(mashup)
				mashupTool = mashup.__getFaceHelper();
			
			if(mashupTool){
				mashupTool.search(value);
			}

		});

		if(object && object.focus){
			content.focus();
			
			var how = object.metaworksContext.how;
			var name = (object.name?object.name:'');
			var nameNext = (object.nameNext?object.nameNext:'');

			if(how == "add" || how == "new"){
				content.selectRange(0, 0);
			}else if(how == "remove" || how == "indent" || how == "outdent"){
				var pos = name.length - nameNext.length;

				content.selectRange(pos, pos);
			}

		}

		$('#wfnode_' + this.objectId).droppable({
			hoverClass: "ui-state-active",
			drop: function( event, ui ) {			
				var dragNodeId = ui.draggable.attr("nodeid");

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
								$(this).attr("nodeid", objectId);				        	
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

	$('#objDiv_' + objectId).addClass("workflowy_node");
	$('#objDiv_' + objectId).attr("nodeid", objectId);
	$('#objDiv_' + objectId).css("position", "relative");
}

org_uengine_codi_mw3_knowledge_WfNode.prototype = {
		getValue : function(){

			var object = mw3.objects[this.objectId];

			//object.name = $('#wfnode_content_' + this.objectId).val();

			return object;
		},
		up : function(isRight){
			var searchObj = $('#objDiv_' + this.objectId).prev();

			if(!searchObj.length){
				searchObj = $('#objDiv_' + this.objectId).parent().parent().parent().parent('div');
			}	   

			if(searchObj.length){
				if(searchObj.attr('id').indexOf('info_') == 0){
					searchObj = searchObj.prev();

					if(searchObj.find('.workflowy_node:last').length)
						searchObj = searchObj.find('.workflowy_node:last');
				}

				searchObj = searchObj.find('input:first');		   
				searchObj.focus();

				if(isRight){
					var pos = searchObj.val().length;

					searchObj.selectRange(pos,pos);
				} else {
					searchObj.selectRange(0,0);
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
		press : function(inputObj){
			workingWfNode = this;

			if(!mw3.loaded){
				window.event.returnValue = false;

				return;
			}		
			
			
			var event = window.event;	
			

			switch (event.keyCode) {
			case 37   :   // left
				var t = inputObj.value, s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj)

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
				var t = inputObj.value, s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj)

				if(s == t.length){
					window.event.returnValue = false;

					this.down();
				}

				break;
			case 40   :   // down
				window.event.returnValue = false;
				this.down();

				break;

			case 9    :	// tab
				var object = mw3.objects[this.objectId];
				
				object.name = inputObj.value;
				
				//var t = inputObj.value, s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj)

/*				if(s == t.length){
					object.nameNext = "";
				}else if(s == 0){
					object.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';		    	  			   
				}else{
					object.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';
				}*/

				window.event.returnValue = false;

				if(event.shiftKey)
					mw3.call(this.objectId, 'outdent');
				else
					mw3.call(this.objectId, 'indent');

				break;

			case 13    :	// enter		  
				var object = mw3.objects[this.objectId];
				
				var t = inputObj.value, s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj)

				if(s == t.length){
					object.name = t;
					object.nameNext = "";
				}else if(s == 0){
					object.name = "";
					object.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';		    	  			   
				}else{ //커서의 위치에 따라서 글을 자르는 로직
					object.name = t.substring(0, s).replace(/ /g, '\xa0') || '\xa0';
					object.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';
				}

				//inputObj.value = object.name;

				window.event.returnValue = false;

				mw3.call(this.objectId, 'add');

				break;
			case 8     :	// back
				var object = mw3.objects[this.objectId];
				
				var t = inputObj.value, s = this.getSelectionStart(inputObj), e = this.getSelectionEnd(inputObj)

				if(s == e && s == 0){
					if(s == t.length){
						object.name = t;
						object.nameNext = "";
					}else if(s == 0){
						object.name = "";
						object.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';		    	  
					}else{
						object.name = t.substring(0, s).replace(/ /g, '\xa0') || '\xa0';
						object.nameNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';
					}

					//inputObj.value = object.name;

					window.event.returnValue = false;

					//if(object.name == "")
					//	this.up(true);

					mw3.call(this.objectId, 'remove');
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

					targetObjectId = searchObj.attr('nodeid');			   
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
		startLoading : function(){
			if(this.windowObjectId)
				mw3.getFaceHelper(this.windowObjectId).startLoading();
		},
		endLoading : function(){
			if(this.windowObjectId)
				mw3.getFaceHelper(this.windowObjectId).endLoading();
		},
		showStatus : function(message){
			mw3.log(message);
		},
		
		
		insertNodeAfter: function(previous, newOne){
						
			new MetaworksObject(
					{
						__className:	'org.metaworks.Refresh',
						
						target:		previous
					
					},
					
					'body'
					
			);
			
			new MetaworksObject(
					{
						__className:	'org.metaworks.ToNext',
						
						previous: 	previous,
						target:		newOne
//						target:		{
//							__className:'org.uengine.codi.mw3.knowledge.WfNode',
//							name: 'test',
//							no:	 currNode + 1
//						}
					
					},
					
					'body'
					
			);
		}

}