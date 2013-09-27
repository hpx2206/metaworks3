var org_uengine_kernel_ParameterContextPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	var wholeValList = this.object.wholeVariableList;
	var activityValList = this.object.activityVariableList;
	
	jQuery("#wvlist_" + this.objectId).jqGrid({
		datatype: "local",
		width:300, 		//가로길이 지정
        height:330,	 	//세로길이 지정
	   	colNames:['변수이름','변수설명'],
	   	colModel:[
	   		{name:'name',editable:true, width:100},
	   		{name:'displayName',editable:true, width:140},
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#wvlist_pager_'+objectId,
	   	pgbuttons: false,
	   	editurl:"index.html",
	   	caption: "전체변수"
	}).navGrid("#wvlist_pager_" + this.objectId,{edit:true,add:true,del:true});
	
	for(var i=0;i<wholeValList.length;i++){
		var variable = wholeValList[i];
		var name = variable.name;
		var displayName = variable.displayName ? variable.displayName.text : '';
		var data = {'name':name , 'displayName':displayName};
		jQuery("#wvlist_" + this.objectId).jqGrid('addRowData',i+1,data);
	}
	
	jQuery("#avlist_" + this.objectId).jqGrid({
		datatype: "local",
		width:300, 
		height: 330,
		colNames:['변수이름','변수설명'],
		colModel:[
		          {name:'name',index:'name', width:100},
		          {name:'displayName',index:'displayName', width:140},
		          ],
		pgbuttons: false,
        caption: "엑티비티변수"
	});
	
	for(var i=0;i<activityValList.length;i++){
		var variable = activityValList[i];
		var name = variable.name;
		var displayName = variable.displayName ? variable.displayName.text : '';
		var data = {'name':name , 'displayName':displayName};
		jQuery("#avlist_" + this.objectId).jqGrid('addRowData',i+1,data);
	}
	      
	$("#del_" + this.objectId).click(function(){
		jQuery("#wvlist_"+objectId).jqGrid('editGridRow',"new",
				{
					height:280,
					closeAfterAdd: true,
					closeAfterEdit: true, 
					reloadAfterSubmit:false,
					afterSubmit: function(response, postdata){
						console.log(response);
						console.log(postdata);
						return true;
					}
				}
		);
	});
	$("#add_" + this.objectId).click(function(){
		var gr = jQuery("#wvlist_"+objectId).jqGrid('getGridParam','selrow');
		if( gr != null ){
			
			jQuery("#wvlist_"+objectId).jqGrid('editGridRow',gr,
					{
						height:280,
						closeAfterAdd: true,
						closeAfterEdit: true, 
						reloadAfterSubmit:false
					}
			);
		}else{
			alert("Please Select Row");
		}
	});
};
