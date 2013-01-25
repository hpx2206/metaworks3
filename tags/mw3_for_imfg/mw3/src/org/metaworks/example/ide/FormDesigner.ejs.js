var org_metaworks_example_ide_FormDesigner = function(objectId, className){
	this.objectId = objectId;	
	this.className = className;
	
	$("#objDiv_" + objectId).css('height', '100%');
	
	
	this.layoutManager;
	
	this.selectObject;	
	
	$(".layoutObject").toggle(function() {
		$(".dragObject").animate({ backgroundColor: "#FFFFFF" });
	    $(this).animate({ backgroundColor: "#68BFEF" });
	    actionFire($(this), 'layout'); 
	},function() {
	    $(this).animate({ backgroundColor: "#FFFFFF" });
	});
	$(".dragObject").toggle(function() {
		$(".dragObject").animate({ backgroundColor: "#FFFFFF" });
	    $(this).animate({ backgroundColor: "#68BFEF" });
	    actionFire($(this)); 
	},function() {
	    $(this).animate({ backgroundColor: "#FFFFFF" });
	});
	
	this.action1 = actionFire;
}

function actionFire(obj, type) {
	this.selectObject = obj;
	
	var myComponent1 = "Placeholder!";
	var myComponent2 = "Placeholder!";
	var myComponent3 = "Placeholder!";
	var myComponent4 = "Placeholder!";
	var myComponent5 = "Placeholder!";
	var myComponent6 = "Placeholder!";
	
	if(type == 'layout') {
		switch(obj.text()) {
		case 'Border Layout': this.layoutManager = jLayout.border({
			    west:   myComponent1,
			    center: myComponent2,
			    north:  myComponent3,
			    vgap: 5
			}); break;
		case 'Grid Layout': this.layoutManager = jLayout.grid({
			    rows: 2,
			    columns: 2,
			    items: [myComponent1, myComponent2, myComponent3, myComponent4]
			}); break;
		case 'FlexGrid Layout': this.layoutManager = jLayout.flexGrid({
			    rows: 2,
			    columns: 2,
			    items: [myComponent1, myComponent2, myComponent3, 
			            myComponent4, myComponent5, myComponent6]
			}); break;
		case 'Flow Layout': this.layoutManager = jLayout.flow({
			    alignment: 'center',
			    items: [myComponent1, myComponent2, myComponent3, 
			            myComponent4, myComponent5]
			}); break;
		}
		
		alert(this.layoutManager);
		this.layoutManager.layout($('#dragTargetArea'));
	}
}

	
	/*
	$(".dragObject").draggable({
        helper: "clone",
        cursor: "move",
        distance: 1,
        start: function(event, ui) {
        },
        stop: function(event, ui) {
        }
	});
	
	
	$("#dragTargetArea").droppable({
		hoverClass: "ui-state-active",
		drop: function( event, ui ) {			
			var dragNodeId = ui.draggable.attr("nodeid");
			
			object.dragNode = mw3.objects[dragNodeId]; 
			
			mw3.call(objectId, "move");
		}
	});
	*/
	


