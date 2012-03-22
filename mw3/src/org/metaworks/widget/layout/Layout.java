package org.metaworks.widget.layout;

public class Layout {

	public Layout() {
		setLoad(true);
		setLoadChild(false);		
	}
	
	Object north;
		public Object getNorth() {
			return north;
		}	
		public void setNorth(Object north) {
			this.north = north;
		}
		
	Object west;
		public Object getWest() {
			return west;
		}	
		public void setWest(Object west) {
			this.west = west;
		}
	
	Object center;
		public Object getCenter() {
			return center;
		}	
		public void setCenter(Object center) {
			this.center = center;
		}
		
	Object east;
		public Object getEast() {
			return east;
		}	
		public void setEast(Object east) {
			this.east = east;
		}	
		
	Object south;
		public Object getSouth() {
			return south;
		}	
		public void setSouth(Object south) {
			this.south = south;
		}
		
	String options;
		public String getOptions() {
			return options;
		}
		public void setOptions(String options) {
			this.options = options;
		}
		
	String name;		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	boolean load;	
		public boolean isLoad() {
			return load;
		}
		public void setLoad(boolean load) {
			this.load = load;
		}
		
	boolean loadChild;
		public boolean isLoadChild() {
			return loadChild;
		}
		public void setLoadChild(boolean loadChild) {
			this.loadChild = loadChild;
		}
}
