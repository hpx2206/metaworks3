var lastsel;

var org_metaworks_widget_grid_Grid= function(objectId, className){
	this.objectId = objectId;
	var grid = mw3.objects[objectId];
	//mw3.armObject(grid.gridCell);
	//grid.gridCell.changeData();

	
	var colModel = [];
	
	var i=0; 
	for(var columnName in grid.columnModel){
		colModel[i++] = grid.columnModel[columnName];
	}
	
	jQuery("#grid_" + this.objectId ).jqGrid({
		datatype: "local",
		height: 250,
	   	colNames:grid.columnNames,
	   	colModel:colModel,
	    viewrecords: true,
	    cellEdit : true,
	   	onSelectRow: function(id){
			if(id && id!==lastsel){
				jQuery('#grid_' + this.objectId).jqGrid('restoreRow', "'" + lastsel + "'");
				jQuery('#grid_' + this.objectId).jqGrid('editRow', "'"+ id + "'", true);
				lastsel=id;
				
				var grid = mw3.objects[objectId];
				grid.gridCell.row = id;
				
//				mw3._armObject(grid.gridCell);
//				grid.gridCell.changeData();
			}
		},
		editurl: "local",
	   	caption: "Manipulating Array Data"
	});

	for(var i=0;i<=grid.data.length;i++){
		jQuery("#grid_" + this.objectId).jqGrid('addRowData',i+1,grid.data[i]);
	}
		
}


