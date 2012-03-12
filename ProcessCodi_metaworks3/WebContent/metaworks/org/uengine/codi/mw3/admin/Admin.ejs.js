mw3.importScript("scripts/jquery/jquery-ui-latest.js");
mw3.importScript("scripts/jquery/jquery.layout-latest.js");

var org_uengine_codi_mw3_admin_Admin = function(objectId, className){
	console.debug("admin");
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[objectId];	
	var theHelper = this;
	
	console.debug(object.defId);
	
	if(object.defId != null && object.defId.length > 0){	
		theHelper.setClassDefinition();
	}
}

org_uengine_codi_mw3_admin_Admin.prototype.setClassDefinition = function(){
	mw3.call(this.objectId, "loadClassDef");
}
/*
function check_position(obj, e) {	
	if( $(obj).css('display') == 'block' )
	{
		var l_position = $(obj).offset();
		l_position.right = parseInt(l_position.left) + ($(obj).width());
		l_position.bottom = parseInt(l_position.top) + parseInt($(obj).height());


		if( ( l_position.left <= e.pageX && e.pageX <= l_position.right )
			&& ( l_position.top <= e.pageY && e.pageY <= l_position.bottom ) ){
		}else{
			$(obj).remove();
		}
	}		
}

$(document).mousedown(function(e){		   
    $('.mw3_popup').each(function(){
    	check_position(this, e);
	});   
    
});
*/

$(document).ready(function () { 
	console.debug("ready");
	
	bodyHeight = $('#container').height();
	bodyWidth = $('#container').width();
	
	outerLayout = $('#container').layout({ 
		center__paneSelector:	".ui-layout-center" 
	,	west__paneSelector:		".ui-layout-west" 
	,	north__size:			52
	,	west__size:				200 
	,	west__minSize: 			120
	,	west__maxSize: 			650
	,	spacing_open:			0 // ALL panes
	,	spacing_closed:			0 // ALL panes
	,	west__spacing_open:		5
	,	togglerLength_open:		0
	,	center__onresize:		"middleLayout.resizeAll"
	,	west__onresize:		    "innerLayout.resizeAll"
	}); 

	
	//Page Flip on hover
	$("#pageflip").hover(function() {
		$("#pageflip img , .msg_block").stop()
			.animate({
				width: '307px', 
				height: '319px'
			}, 700); 
		} , function() {
		$("#pageflip img").stop() 
			.animate({
				width: '42px', 
				height: '42px'
			}, 700);
		$(".msg_block").stop() 
			.animate({
				width: '42px', 
				height: '42px'
			}, 700);
	});
		
}); 