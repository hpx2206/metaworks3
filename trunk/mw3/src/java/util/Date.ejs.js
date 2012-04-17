var java_util_Date = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.inputId = mw3.createInputId(objectId);
	
	var when = $('#' + this.inputId).attr('when');
	
	$('#' + this.inputId).datepicker({
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],		// 월 한글로 출력
		dayNamesMin: ['일','월','화','수','목','금','토'],		// 요일 한글로 출력
	  	dateFormat: 'yy-mm-dd', //데이터 포멧형식
	  	showMonthAfterYear: true,
		showOn: 'button',
		buttonImageOnly: true,
		buttonImage :'images/waveStyle/calendar.gif'
	});
	
  	
	$('#' + this.inputId).datepicker("setDate", mw3.objects[objectId]);
	
	if(when != 'edit' && when != 'new')
		$('#objDiv_' + objectId + ' .ui-datepicker-trigger').remove();	
}

java_util_Date.prototype.getValue = function(){
	
	var inputObj = $("#" + this.inputId);
	
	if(inputObj.lenght){	
		var selDate = new Date($("#" + this.inputId).datepicker("getDate"));
	
		return selDate.getTime();
	}else{
		return null;
	}
		
}