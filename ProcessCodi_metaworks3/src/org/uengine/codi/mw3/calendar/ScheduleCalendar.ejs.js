var org_uengine_codi_mw3_calendar_ScheduleCalendar = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	
	this.COLOR_EVENT_SELF 		= "#348017";
	this.COLOR_EVENT_ANOTHER 	= "#C48189";
	this.COLOR_EVENT_COMPLETE 	= "#808080";
	
	
	this.session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
	
	this.KOREA = 'ko';
	this.ENGLISH = 'en';
	
};

org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype = {
	loaded : function(){
		var objectId = this.objectId;
		var object = mw3.objects[objectId];
		
		if(object){
			var facehelper = this;
			
			mw3.importStyle('scripts/jquery/fullcalendar/fullcalendar.css');
			mw3.importScript('scripts/jquery/fullcalendar/fullcalendar.min.js', function(){
				facehelper.load();
			});
		}
	},
	load : function(){
		var object = mw3.objects[this.objectId];
		var objectId = this.objectId;
		$('#objDiv_' + this.objectId).addClass('mw3_layout').attr('objectId', this.objectId);
		
		var calendar = $('#scheduleCalendar_' + this.objectId);
		if(object.metaworksContext.how == 'small'){
			calendar.fullCalendar({
				selectable: true,
				selectHelper: true,
				select: function(start, end, allDay) {
					var calendar = mw3.getObject(objectId);
					
					calendar.selDate = start;
					calendar.linkScheduleCalendar();
				},
				eventClick: function(calEvent, jsEvent, view) {	
					mw3.getFaceHelper(objectId).eventClick(calEvent.id, calEvent.callType, view.name);
				},
				events: object.data,
				firstDay: 1,
				dayNamesShort: ["일","월","화","수","목","금","토"],
				monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
				monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"]
			});			
		}else{
			if(this.session.employee.locale == this.KOREA){
				calendar.fullCalendar({
					height: 650,
					header: {
						left: 'prevYear,prev,next,nextYear today' ,
						center: 'title',
						//right: 'month,agendaWeek,agendaDay'
						right: 'month'
					},
					selectable: true,
					selectHelper: true,
					editable: true,
					events: object.data,
//					dayClick: function(date, allDay, jsEvent, view) {
//						mw3.getFaceHelper(objectId).dayClick(date, view.name);
//					},
					select: function(start, end, allDay) {
						
						var calendar = mw3.getObject(objectId);
						calendar.selDate = start;
						calendar.endDate = end;
						calendar.allDay = allDay;
											
						calendar.linkScheduleDay();
					},
					eventClick: function(calEvent, jsEvent, view) {	
						mw3.getFaceHelper(objectId).eventClick(calEvent.id, calEvent.callType, view.name);
					},
					titleFormat: {
						month: "yyyy년 MMMM",
						week: "[yyyy] MMM d일{ [yyyy] MMM d일}",
						day: "yyyy년 MMM d일 dddd"
						},
						allDayDefault: true,
						firstDay: 1,
						//defaultView: "basicWeek",
						//editable: false,
						//weekends : false,
						monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
						monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
						dayNames: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
						dayNamesShort: ["일","월","화","수","목","금","토"],
						buttonText: {
						today : "today",
						month : "월별",
						week : "주별"
						}
					
				});	
			}else if(this.session.employee.locale == this.ENGLISH){
				calendar.fullCalendar({
					height: 650,
					header: {
						left: 'prevYear,prev,next,nextYear today' ,
						center: 'title',
						//right: 'month,agendaWeek,agendaDay'
						right: 'month,agendaWeek'
					},
					selectable: true,
					selectHelper: true,
					editable: true,
					events: object.data,
//					dayClick: function(date, allDay, jsEvent, view) {
//						mw3.getFaceHelper(objectId).dayClick(date, view.name);
//					},
					select: function(start, end, allDay) {
						
						var calendar = mw3.getObject(objectId);
						calendar.selDate = start;
						calendar.endDate = end;
						calendar.allDay = allDay;
											
						calendar.linkScheduleDay();
					},
					eventClick: function(calEvent, jsEvent, view) {	
						mw3.getFaceHelper(objectId).eventClick(calEvent.id, calEvent.callType, view.name);
					}
				});	
			}
			
		}
		calendar.fullCalendar('gotoDate', object.selDate);
	}
};

org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype.dayClick = function(selDate, viewMode){
	var object = mw3.getObject(this.objectId);
	
	object.selDate = selDate;
	object.viewMode = viewMode;

	object.linkScheduleDay();
};

org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype.eventClick = function(schdId, callType, viewMode){
	var object = mw3.getObject(this.objectId);
	object.schdId = schdId;
	object.callType = callType;
	object.viewMode = viewMode;

	object.linkScheduleEvent();
};

org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype.addEvent = function(event){

	this.removeEvent(event);
	
	var calendar = $('#scheduleCalendar_' + this.objectId);

	if(event.complete){
		event.color = this.EVENT_COLOR_COMPLETE;
		
		if(event.userId == this.session.user.userId)
			event.color = this.EVENT_COLOR_SELF;
		else
			event.color = this.EVENT_COLOR_ANOTHER;
	}
	calendar.fullCalendar( 'renderEvent', event );
};

/*
org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype.addEvent = function(title, instId, startDate, endDate){
	var calendar = $('#scheduleCalendar_' + this.objectId);
	var object = mw3.objects[this.objectId];
	var myEvent = {
			  title: title,
			  id: instId,
			  allDay: object.allDay,
			  callType: 'instance',
			  color: '#0000cd',
			  start: object.startDate,
			  end: object.endDate
			};
	calendar.fullCalendar( 'renderEvent', myEvent );
};
*/

org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype.removeEvent = function(event){
	var calendar = $('#scheduleCalendar_' + this.objectId);
	calendar.fullCalendar( 'removeEvents', event.id );
};

org_uengine_codi_mw3_calendar_ScheduleCalendar.prototype.resize = function(){
	var calendar = $('#scheduleCalendar_' + this.objectId);
	calendar.fullCalendar('render');	
};