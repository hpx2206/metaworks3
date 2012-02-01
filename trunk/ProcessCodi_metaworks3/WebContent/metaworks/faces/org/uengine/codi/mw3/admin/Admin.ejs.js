mw3.importScript("scripts/jquery/jquery-ui-latest.js");
mw3.importScript("scripts/jquery/jquery.layout-latest.js");

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