package org.uengine.codi.mw3.dashboard;

import java.net.UnknownHostException;

import org.metaworks.widget.chart.Area;
import org.metaworks.widget.chart.Guage;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Usage {
	
	Guage guage;
	
		public Guage getGuage() {
			return guage;
		}
	
		public void setGuage(Guage guage) {
			this.guage = guage;
		}
		
		
	Area area;
			
		public Area getArea() {
			return area;
		}
	
		public void setArea(Area area) {
			this.area = area;
		}

	public Usage() throws UnknownHostException, MongoException{
		
		long currTime = System.currentTimeMillis();
		long fromTime = currTime - 7 * 24 * 60 * 60 * 1000;
		
		Mongo m = new Mongo();
		
		DB db = m.getDB("platform");
		
		DBCollection coll = db.getCollection("apiCalls");
		
        BasicDBObject query = new BasicDBObject();

        query.put("time", new BasicDBObject("$gt", fromTime).append("$lte", currTime)); 

        DBCursor cur = coll.find(query);
        

        while(cur.hasNext()) {
            System.out.println(cur.next());
        }
        
        guage = new Guage();
        guage.setLabel("Remain Budget");
        guage.setValue(100 - cur.size());

	}

}
