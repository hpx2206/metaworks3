var lastsel;

var org_metaworks_widget_grid_Grid= function(objectId, className){
	this.objectId = objectId;
	
	var grid = mw3.objects[objectId];
	
	//mw3.armObject(grid.gridCell);
	//grid.gridCell.changeData();
	
	var colModel = [];
	
	var i=0; 
	for(var columnIndex in grid.columnNames){
		colModel[i++] = grid.columnModel[grid.columnNames[columnIndex]];
	}
	
	
	jQuery("#grid_" + this.objectId).jqGrid({
		datatype: "local",
		height: 250,
	   	colNames:grid.columnNames,
	   	colModel:colModel,	    
	    rowNum:10,
	   	rowList:[10,20,30],
	   	viewrecords: true,
	   	sortorder: "desc",
	   	caption: "Manipulating Array Data",
	    forceFit : true,
	    cellEdit : true,	    
		cellsubmit: 'clientArray',
		beforeSaveCell : function(rowid,name,val,iRow,iCol) {
		/*
			jQuery("#grid_" + objectId).jqGrid("restoreCell",iRow,iCol);
			
			console.debug("beforeSaveCell");
			console.debug("rowid : " + rowid);
			console.debug("name : " + name);
			console.debug("val : " + val);
			console.debug("iRow : " + iRow);
			console.debug("iCol : " + iCol);	
		*/
		
			var cell = {
					__className : "org.metaworks.widget.grid.GridCell",
					rowId : rowid,
					name : name,
					row : iRow,
					col : iCol,
					value : val}
			var grid = mw3.objects[objectId];
			
			grid.cell = cell;	
			
			try{
				grid.changeCell();
			}catch(e){
				return e.message;
			}			
		},
		afterEditCell : function(rowid,name,val,iRow,iCol) {
		/*
			console.debug("afterEditCell");
			console.debug("rowid : " + rowid);
			console.debug("name : " + name);
			console.debug("val : " + val);
			console.debug("iRow : " + iRow);
			console.debug("iCol : " + iCol);
		*/
		},
		afterSaveCell : function(rowid,name,val,iRow,iCol) {
		/*
			console.debug("afterSaveCell");
			console.debug("rowid : " + rowid);
			console.debug("name : " + name);
			console.debug("val : " + val);
			console.debug("iRow : " + iRow);
			console.debug("iCol : " + iCol);
		*/
		}
	});
	
	
	var keyColmun = grid.keyColumn;
	
	if(grid instanceof Array){
		for(var i=0;i<=grid.data.length;i++){
			jQuery("#grid_" + this.objectId).jqGrid('addRowData', keyColmun, grid.data[i]);
		}			
	}else{
		jQuery("#grid_" + this.objectId).jqGrid('addRowData', keyColmun, grid.data);
	}
}


