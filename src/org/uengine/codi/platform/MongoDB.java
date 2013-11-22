package org.uengine.codi.platform;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDB {
	
	public DB getDB() throws UnknownHostException, MongoException{
		Mongo m = new Mongo();

		DB db = m.getDB( "testdb" );

		return db;
	}

}
