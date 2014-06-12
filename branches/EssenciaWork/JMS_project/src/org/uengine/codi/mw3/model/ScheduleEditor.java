package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.persistence.Id;

import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.viewer.ViewerOptions;
import org.uengine.processmanager.ProcessManagerRemote;

public class ScheduleEditor {

	String instanceId;
	@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}


	String chartData;		
		public String getChartData() {
			return chartData;
		}	
		public void setChartData(String chartData) {
			this.chartData = chartData;
		}
		
	public void load(ProcessManagerRemote processManager)
		throws RemoteException {
		
		ViewerOptions options = new ViewerOptions();
		
		StringBuffer data = new StringBuffer(); 
		data.append("    var project = {");
		data.append("        '@uid': 'String',");
		data.append("        name:'String',");
		data.append("        subject:'String',");
		data.append("        category:'String',");
		data.append("        description:'String',");
		data.append("        author:'String',");
		data.append("        creationDate:'2001-12-17T09:30:47Z',");
		data.append("        updatedDate:'2001-12-17T09:30:47Z',");
		data.append("        startDate:'2010-08-01',");
		data.append("        finishDate:'2010-10-25',");
		data.append("        status:'String',");
		data.append("        Tasks: {");
		data.append("            Task: [");
		data.append("                {");
		data.append("                    '@uid': '1',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':null,");
		data.append("                    '@from': '',");
		data.append("                    '@to': '2',");
		data.append("                    '@wbsLevel': 1,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title:'태스크 1',");
		data.append("                    type:'',");
		data.append("                    startDate:'2010-08-23',");
		data.append("                    endDate:'2010-08-25',");
		data.append("                    duration:3,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '2',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':null,");
		data.append("                    '@from': '1',");
		data.append("                    '@to': '3',");
		data.append("                    '@wbsLevel': 1,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title:'태스트 2',");
		data.append("                    type:'String',");
		data.append("                    startDate:'2010-08-25',");
		data.append("                    endDate:'2010-08-27',");
		data.append("                    duration:3,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '3',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':null,");
		data.append("                    '@from': '2',");
		data.append("                    '@to': '6',");
		data.append("                    '@wbsLevel': 1,");
		data.append("                    '@isLeaf': false,");
		data.append("                    title:'태스크 3',");
		data.append("                    type:'String',");
		data.append("                    startDate:'2010-09-08',");
		data.append("                    endDate:'2010-09-12',");
		data.append("                    duration:5,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '4',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':'3',");
		data.append("                    '@from': '',");
		data.append("                    '@to': '5',");
		data.append("                    '@wbsLevel': 1,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title: '태스크 3-1',");
		data.append("                    type:'',");
		data.append("                    startDate:'2010-09-08',");
		data.append("                    endDate:'2010-09-10',");
		data.append("                    duration:3,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '5',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':'3',");
		data.append("                    '@from': '4',");
		data.append("                    '@to': '7',");
		data.append("                    '@wbsLevel': 2,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title:'태스크 3-2',");
		data.append("                    type:'M',");
		data.append("                    startDate:'2010-09-12',");
		data.append("                    endDate:'2010-09-12',");
		data.append("                    duration:1,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '6',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':null,");
		data.append("                    '@from': '3',");
		data.append("                    '@to': '',");
		data.append("                    '@wbsLevel': 1,");
		data.append("                    '@isLeaf': false,");
		data.append("                    title:'Task 4',");
		data.append("                    type:'String',");
		data.append("                    startDate:'2010-09-15',");
		data.append("                    endDate:'2010-09-19',");
		data.append("                    duration:5,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '7',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':'6',");
		data.append("                    '@from': '5,10',");
		data.append("                    '@to': '8',");
		data.append("                    '@wbsLevel': 2,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title:'Task 4-1',");
		data.append("                    type:'',");
		data.append("                    startDate:'2010-09-15',");
		data.append("                    endDate:'2010-09-17',");
		data.append("                    duration:3,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '8',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':'6',");
		data.append("                    '@from': '7',");
		data.append("                    '@to': '',");
		data.append("                    '@wbsLevel': 2,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title:'Task 4-2',");
		data.append("                    type:'M',");
		data.append("                    startDate:'2010-09-19',");
		data.append("                    endDate:'2010-09-19',");
		data.append("                    duration:1,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '9',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':null,");
		data.append("                    '@from': '',");
		data.append("                    '@to': '',");
		data.append("                    '@wbsLevel': 1,");
		data.append("                    '@isLeaf': false,");
		data.append("                    title:'Task 5',");
		data.append("                    type:'',");
		data.append("                    startDate:'2010-09-10',");
		data.append("                    endDate:'2010-09-12',");
		data.append("                    duration:3,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                },");
		data.append("                {");
		data.append("                    '@uid': '10',");
		data.append("                    '@seqNo': '',");
		data.append("                    '@parent':'9',");
		data.append("                    '@from': '',");
		data.append("                    '@to': '7',");
		data.append("                    '@wbsLevel': 2,");
		data.append("                    '@isLeaf': true,");
		data.append("                    title:'Task 5-1',");
		data.append("                    type:'',");
		data.append("                    startDate:'2010-09-10',");
		data.append("                    endDate:'2010-09-11',");
		data.append("                    duration:2,");
		data.append("                    description:'String',");
		data.append("                    status:'String',");
		data.append("                    priority:0,");
		data.append("                    resources:'String',");
		data.append("                    completeRate:100");
		data.append("                }");
		data.append("            ]");
		data.append("        }");
		data.append("    };");
		
		setChartData(data.toString());
	}
}
