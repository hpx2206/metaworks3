mw3.importScript("scripts/jquery/jquery-ui-latest.js");
mw3.importScript("scripts/jquery/jquery.layout-latest.js");

var outerLayout, middleLayout, innerLayout, inner2Layout; 
var navigationEnable = 1;
var contactEnable = 1;
var worklistEnable = 1;
var oriContactHeight = 0;

var bodyHeight;
var bodyWidth ;

function popUp(e,w,h){
	
		   // alert("e.pageX: " + e.pageX + ", e.pageY: " + e.pageY);
		   //alert(w+","+h);
		    popLayerWidth = w;
		    popLayerHeight = h;
		    
		    $('#adduser-con').show("fast");
		    $('#adduser-con').css({top:e.pageY,left:e.pageX,width:500,height:600});
		    $('#adduser-con').removeClass('clue-left-rounded');
		    $('#adduser-con').addClass('clue-right-rounded');
		    $('.cluetip-arrows').css({top:10});

		    if(popLayerWidth + e.pageX > bodyWidth){
		    	 $('#adduser-con').css({left:e.pageX - popLayerWidth});
		    	 $('#adduser-con').removeClass('clue-right-rounded');
		    	 $('#adduser-con').addClass('clue-left-rounded');
		    };
		    
		    if(popLayerHeight + e.pageY > bodyHeight){    	
		   	 	$('#adduser-con').css({top:e.pageY - popLayerHeight});
		   	 	$('.cluetip-arrows').css({top:popLayerHeight-40});
		    };

};

function check_position(obj, e) {	
	
	if( $(obj).css('display') == 'block' )
	{
		var l_position = $(obj).offset();
		l_position.right = parseInt(l_position.left) + ($(obj).width());
		l_position.bottom = parseInt(l_position.top) + parseInt($(obj).height());


		if( ( l_position.left <= e.pageX && e.pageX <= l_position.right )
			&& ( l_position.top <= e.pageY && e.pageY <= l_position.bottom ) ){
			//alert( 'popup in click' );
		}else{
			//alert( 'popup out click' );
			$(obj).hide("fast");
		}
	}		
}

$(document).mousedown(function(e){		
   
    $('#adduser-con').each(function(){
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
	
	/*
	innerinnerLayout = $('div.inner-center').layout({ 
		center__paneSelector:	".inner-inner-center" 
	,	north__paneSelector:	".inner-inner-north" 
	,	north__size:			100 
	,	spacing_open:			0  // ALL panes
	,	spacing_closed:			0 // ALL panes
	,	north__spacing_open:	5	
	,	west_togglerLength_closed: 0
	,	togglerLength_open:		0		
	
	}); 

	*/
	
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

//	outerLayout = $('#container').layout(); 
	
//	$('#right-container').layout();

};
