package com.test.dashboard;

import com.test.widget.Guage2;
import com.test.widget.chart.Area;
import org.metaworks.annotation.ServiceMethod;

public class Dashboard{

	Guage2 balance;
		public Guage2 getBalance(){ return balance; }
		public void setBalance(Guage2 balance){ this.balance = balance; }

	Area burnDown;
		public Area getBurnDown(){ return burnDown; }
		public void setBurnDown(Area burnDown){ this.burnDown = burnDown; }
        
    @ServiceMethod
    public void load(){
        balance = new Guage2();
        balance.setLabel("Balance");
        balance.setValue(50);
    }
        

}