package org.metaworks.example.addressbook;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs")
public class Addressbook implements ContextAware{
	
	Person harry;
		public Person getHarry() {
			return harry;
		}
		public void setHarry(Person harry) {
			this.harry = harry;
		}

	Person sally;
		public Person getSally() {
			return sally;
		}
		public void setSally(Person sally) {
			this.sally = sally;
		}

	Person doogie;
		public Person getDoogie() {
			return doogie;
		}
		public void setDoogie(Person doogie) {
			this.doogie = doogie;
		}

	
	String message;
	@NonEditable
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	@ServiceMethod
	public void load(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("view");
		
		harry = new Person();
		harry.setName("Harry");
		harry.setAge(10);
		
		sally = new Person();
		sally.setName("Sally");
		sally.setAge(9);
		
		doogie = new Person();
		doogie.setName("Doogie");
		doogie.setAge(3);
	}
	
	
	public void gameOver(Person winner) {
		setMessage("<b><font size=5 color=red>Game Over, the winner is " + winner.getName() + "!</font></b>");
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("game-over");
	}

	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
