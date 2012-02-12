package com.abc;

import com.test.widget.Guage2;
import com.test.widget.chart.Area;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.platform.FileSystem;
import org.uengine.codi.platform.MongoDB;
import org.springframework.beans.factory.annotation.Autowired;


import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

import java.net.UnknownHostException;

import java.util.Set;

public class Dashboard{
    Guage2 balance;
    	public Guage2 getBalance(){ return balance; }
		public void setBalance(Guage2 balance){ this.balance = balance; }

//	Area burnDown;
//		public Area getBurnDown(){ return burnDown; }
//		public void setBurnDown(Area burnDown){ this.burnDown = burnDown; }

    @ServiceMethod
    public void load(){
        balance = new Guage2();
        balance.setLabel("Balance");
        balance.setValue(50);
    }
    
    @ServiceMethod
    public void runInfiniteLoop(){
        for(;;) System.out.println("xxxxx");    
    }
    
    @ServiceMethod
    public void fileAccessDirectly(){
        java.io.File f = new java.io.File("filecreation.test");  
    }
    
    @ServiceMethod
    public void fileAccessViaPlatformAPI(){
        fileSystem.createFile("filecreation.test");
    }

    @ServiceMethod
    public void dbAccessViaPlatformAPI() throws UnknownHostException, MongoException{
        DB db = mongoDB.getDB("testdb");
        Set<String> colls = db.getCollectionNames();

    	for (String s : colls) {
		    System.out.println(s);
		}
    }
    
    @Autowired
    public FileSystem fileSystem;
    
    @Autowired
    public MongoDB mongoDB;
    
}