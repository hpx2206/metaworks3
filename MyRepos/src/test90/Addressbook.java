package test90;

import test100.Person;
import org.metaworks.annotation.*;

public class Addressbook{
    
    Person person;
    public Person getPerson(){
        return person;
    }
    
    public void setPerson(Person person){
        this.person = person;
    }
    
    @ServiceMethod
    public void run(){
        setPerson(new Person());
    }
    
}