var org_uengine_codi_gantt_ProcessGanttChart = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.object = mw3.objects[this.objectId];
	var faceHelper = this;

	// make project
	var project = faceHelper.makeData(this.object);

	// make tasks
	if(project.Tasks){
		project.Tasks = faceHelper.makeData(project.Tasks);

		for(var i=0; i<project.Tasks.Task.length; i++){
			
			var taskObject = project.Tasks.Task[i];
			project.Tasks.Task[i] = faceHelper.makeData(taskObject);

		}
	}	

	try{
		var ganttChartPanel = new Ext.ux.gantt.GanttChartPanel({
		header : true,
		width : 1000,
		height : 450,
		treeGridWidth : 400,
		renderTo : mw3._getObjectDivId(this.objectId),
		viewMode : 'D',
		barMode : 'D',
		wireMode : '_plan',
	  //readOnly      : false,
		projectURL : {
				forLoadSubTasks : '/rest/Gantt/LoadSubTasks'
			},
			projectData : project
		});

return true;

		var viewport = new Ext.Viewport({
			layout : 'border',
			items : [ {
				region : 'center',
				contentEl : mw3._getObjectDivId(this.objectId),
				border : true,
				listeners : {
					resize : function(component, adjWidth, adjHeight, rawWidth,
							rawHeight) {
						if (ganttChartPanel != null) {
							ganttChartPanel.setWidth(adjWidth);
							ganttChartPanel.setHeight(adjHeight);
						}
					}
				}
			} ]
		});
	}catch(e){
		console.log(e);
	}

}

org_uengine_codi_gantt_ProcessGanttChart.prototype = {
	makeData : function (object) {

		var data = {};
		var objectMetadata = mw3.getMetadata(object.__className);

		for (var i=0; i<objectMetadata.fieldDescriptors.length; i++){
			var fd = objectMetadata.fieldDescriptors[i];

			var upperCase = fd.getOptionValue('upperCase', '');
			var prefix = fd.getOptionValue('prefix', '');

			if(upperCase){
				data[fd.name.charAt(0).toUpperCase() + fd.name.slice(1)] = object[fd.name];	
			}else if(prefix){
				data[prefix + fd.name] = object[fd.name];
			}else{
				data[fd.name] = object[fd.name];
			}
			
		}
		return data;
	}
}