package org.uengine.codi.platform;

import java.net.UnknownHostException;
import java.util.Set;

import org.metaworks.annotation.ServiceMethod;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

public class MongoDBTest {

	@ServiceMethod
	public void test() throws UnknownHostException, MongoException{

		Mongo m = new Mongo();
//		// or
//		Mongo m = new Mongo( "localhost" );
//		// or
//		Mongo m = new Mongo( "localhost" , 27017 );

		DB db = m.getDB( "testdb" );
		
		Set<String> colls = db.getCollectionNames();

		for (String s : colls) {
		    System.out.println(s);
		}
		
		DBCollection coll = db.getCollection("testCollection");
		
		
	      BasicDBObject doc = new BasicDBObject();

	        doc.put("name", "MongoDB");
	        doc.put("type", "database");
	        doc.put("count", 1);

	        BasicDBObject info = new BasicDBObject();

	        info.put("x", 203);
	        info.put("y", 102);

	        doc.put("info", info);

	        coll.insert(doc);
	        
	        
	        DBObject myDoc = coll.findOne();
	        System.out.println(myDoc);

	        for (int i=0; i < 100; i++) {
	            coll.insert(new BasicDBObject().append("i", i));
	        }
	        
	        DBCursor cur = coll.find();

	        while(cur.hasNext()) {
	            System.out.println(cur.next());
	        }
	        
	        
	        BasicDBObject query = new BasicDBObject();

	        query.put("i", 71);

	        cur = coll.find(query);

	        while(cur.hasNext()) {
	            System.out.println(cur.next());
	        }
	        
	        
	        
	        
	        query = new BasicDBObject();

	        query.put("i", new BasicDBObject("$gt", 50));  // e.g. find all where i > 50

	        cur = coll.find(query);

	        while(cur.hasNext()) {
	            System.out.println(cur.next());
	        }
		
	        
	        query = new BasicDBObject();

	        query.put("i", new BasicDBObject("$gt", 20).append("$lte", 30));  // i.e.   20 < i <= 30

	        cur = coll.find(query);

	        while(cur.hasNext()) {
	            System.out.println(cur.next());
	        }
	        
	        
	        for (String s : m.getDatabaseNames()) {
	            System.out.println(s);
	        }
	        
	        
	        
	}
	
	
	public static void main(String args[]) throws UnknownHostException, MongoException{
		new MongoDBTest().test();
	}
	
	
}
