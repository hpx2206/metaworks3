var org_uengine_codi_mw3_model_ScheduleEditor = function(objectId, className) {
	
	var object = mw3.objects[objectId];	
	
	this.objectId = objectId;
	this.className = className;
	this.chartPanel = "divGanttChart_" + objectId;
	
	var faceHelper = this;

	// init gantt chart..
	this.init();
}

org_uengine_codi_mw3_model_ScheduleEditor.prototype.init = function() {

	if (!Ext || typeof Ext != "object") {
		if (window.console)
			console.log("Not find object.!! extJS. ");	

		return;
	}

	if (!Ext.ux.gantt.GanttChartPanel || typeof Ext.ux.gantt.GanttChartPanel != "function") {
		if (window.console)
			console.log("Not find object.!! GanttChart.. ");	

		return;
	}
	

	// Init ext..
	Ext.QuickTips.init();

	// drow for gantt chart.
	var objData = this.getChartData();
    var ganttChartPanel = new Ext.ux.gantt.GanttChartPanel({
        header: true,
        width: 1000,
        height: 450,
        treeGridWidth: 400,
        renderTo: this.chartPanel,
        viewMode: "D",
        projectURL: {
            forLoadSubTasks: "/rest/Gantt/LoadSubTasks"
        }
        ,projectData: objData
    });
}

org_uengine_codi_mw3_model_ScheduleEditor.prototype.destroy = function() {
	//alert("destroy" + this.objectId);
	$("#"+ this.chartPanel).remove();	
}

org_uengine_codi_mw3_model_ScheduleEditor.prototype.getChartData = function() {
    // Define Sample Task Data
    // Project 정보
    var project = {
        '@uid': 'String',
        name:'String',
        subject:'String',
        category:'String',
        description:'String',
        author:'String',
        creationDate:'2001-12-17T09:30:47Z',
        updatedDate:'2001-12-17T09:30:47Z',
        startDate:'2010-08-01',
        finishDate:'2010-10-25',
        status:'String',
        Tasks: {
            Task: [
                {
                    '@uid': '1',
                    '@seqNo': '',
                    '@parent':null,
                    '@from': '',
                    '@to': '2',
                    '@wbsLevel': 1,
                    '@isLeaf': true,
                    title:'태스크 1',
                    type:'',
                    startDate:'2010-08-23',
                    endDate:'2010-08-25',
                    duration:3,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '2',
                    '@seqNo': '',
                    '@parent':null,
                    '@from': '1',
                    '@to': '3',
                    '@wbsLevel': 1,
                    '@isLeaf': true,
                    title:'태스트 2',
                    type:'String',
                    startDate:'2010-08-25',
                    endDate:'2010-08-27',
                    duration:3,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '3',
                    '@seqNo': '',
                    '@parent':null,
                    '@from': '2',
                    '@to': '6',
                    '@wbsLevel': 1,
                    '@isLeaf': false,
                    title:'태스크 3',
                    type:'String',
                    startDate:'2010-09-08',
                    endDate:'2010-09-12',
                    duration:5,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '4',
                    '@seqNo': '',
                    '@parent':'3',
                    '@from': '',
                    '@to': '5',
                    '@wbsLevel': 1,
                    '@isLeaf': true,
                    title: '태스크 3-1',
                    type:'',
                    startDate:'2010-09-08',
                    endDate:'2010-09-10',
                    duration:3,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '5',
                    '@seqNo': '',
                    '@parent':'3',
                    '@from': '4',
                    '@to': '7',
                    '@wbsLevel': 2,
                    '@isLeaf': true,
                    title:'태스크 3-2',
                    type:'M',
                    startDate:'2010-09-12',
                    endDate:'2010-09-12',
                    duration:1,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '6',
                    '@seqNo': '',
                    '@parent':null,
                    '@from': '3',
                    '@to': '',
                    '@wbsLevel': 1,
                    '@isLeaf': false,
                    title:'Task 4',
                    type:'String',
                    startDate:'2010-09-15',
                    endDate:'2010-09-19',
                    duration:5,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '7',
                    '@seqNo': '',
                    '@parent':'6',
                    '@from': '5,10',
                    '@to': '8',
                    '@wbsLevel': 2,
                    '@isLeaf': true,
                    title:'Task 4-1',
                    type:'',
                    startDate:'2010-09-15',
                    endDate:'2010-09-17',
                    duration:3,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '8',
                    '@seqNo': '',
                    '@parent':'6',
                    '@from': '7',
                    '@to': '',
                    '@wbsLevel': 2,
                    '@isLeaf': true,
                    title:'Task 4-2',
                    type:'M',
                    startDate:'2010-09-19',
                    endDate:'2010-09-19',
                    duration:1,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '9',
                    '@seqNo': '',
                    '@parent':null,
                    '@from': '',
                    '@to': '',
                    '@wbsLevel': 1,
                    '@isLeaf': false,
                    title:'Task 5',
                    type:'',
                    startDate:'2010-09-10',
                    endDate:'2010-09-12',
                    duration:3,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                },
                {
                    '@uid': '10',
                    '@seqNo': '',
                    '@parent':'9',
                    '@from': '',
                    '@to': '7',
                    '@wbsLevel': 2,
                    '@isLeaf': true,
                    title:'Task 5-1',
                    type:'',
                    startDate:'2010-09-10',
                    endDate:'2010-09-11',
                    duration:2,
                    description:'String',
                    status:'String',
                    priority:0,
                    resources:'String',
                    completeRate:100
                }
            ]
        }
    };

	return project;
}