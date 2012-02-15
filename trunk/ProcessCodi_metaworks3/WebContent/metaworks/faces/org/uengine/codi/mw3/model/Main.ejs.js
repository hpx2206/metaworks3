mw3.importScript("scripts/jquery/jquery-ui-latest.js");
mw3.importScript("scripts/jquery/jquery.layout-latest.js");

var outerLayout, middleLayout, innerLayout, inner2Layout; 
var navigationEnable = 1;
var contactEnable = 1;
var worklistEnable = 1;
var oriContactHeight = 0;

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

$(document).ready(function () { 
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

	innerLayout = $('div.ui-layout-west').layout({ 
		center__paneSelector:	".inner-center" 
	,	north__paneSelector:	".inner-north" 	
	,	north__size:			'50%'
	,	spacing_open:			0  // ALL panes
	,	spacing_closed:			0 // ALL panes
	,	north__spacing_open:	5
	,	south__spacing_open:	5
	,	togglerLength_open:		0		
	,	center__onresize:		function () {  $("#accordion").accordion("resize");}
	
	}); 
	
	middleLayout = $('div.ui-layout-center').layout({ 		
		center__paneSelector:	".middle-center" 												
	,	west__paneSelector:		".middle-west" 
	,	west__size:				'40%' 
	,	west__minSize:			100	
	,	west__maxSize:			'95%'	
	,	center__minSize:		100
	,	spacing_open:			5  // ALL panes
	,	spacing_closed:			0 // ALL panes
	,	north__spacing_open:	0
	,	south__spacing_open:	0
	,	togglerLength_open:		0
	,	west__onresize:			function () { heightResizing();  }
            // TODO : MODIFIED (이승백)
	,	center__onresize:	    function (e) {
            if(ganttChartPanel) {
                ganttChartPanel.setWidth($(".middle-center").width() - 10);
                ganttChartPanel.setHeight($(".middle-center").height());
            }
        }
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

org_uengine_codi_mw3_model_Main = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	$("#objDiv_" + objectId).addClass("mw3_layout").attr("objectid", objectId);
	
	this.layout = new HashMap();
	
	this.layout.put("outer", outerLayout);
	this.layout.put("inner", innerLayout);
	this.layout.put("middle", middleLayout);
	
	this.layout.put("outer_status", 2);
	this.layout.put("inner_status", 2);
	this.layout.put("middle_status", 2);
};


org_uengine_codi_mw3_model_Main.prototype.getLayout = function(name){
	return this.layout.get(name);
};

org_uengine_codi_mw3_model_Main.prototype.putLayout = function(name, value){
	this.layout.put(name, value);
};
