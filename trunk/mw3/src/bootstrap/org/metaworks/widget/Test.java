package bootstrap.org.metaworks.widget;

import java.util.ArrayList;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.Accordion;
import org.metaworks.widget.AccordionPanel;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Tab;
import org.metaworks.widget.TabPanel;

public class Test {
	
	public Object obj;
		public Object getObj() {
			return obj;
		}
		public void setObj(Object obj) {
			this.obj = obj;
		}
	
	TestButton tbj;
		public TestButton getTbj() {
			return tbj;
		}
		public void setTbj(TestButton tbj) {
			this.tbj = tbj;
		}
	
	public Test(){
		testTab();
		
		TestButton tb = new TestButton(TestButton.BUTTON_TYPE_COMBOBOX);
		tb.add(TestButton.BUTTON_TYPE_CHECKBOX, TestButton.BUTTON_TYPE_CHECKBOX);
		tb.add(TestButton.BUTTON_TYPE_COMBOBOX, TestButton.BUTTON_TYPE_COMBOBOX);
		tb.add(TestButton.BUTTON_TYPE_RADIO, TestButton.BUTTON_TYPE_RADIO);
		tb.select(0);
		setTbj(tb);
		
	}
	
	@ServiceMethod(callByContent=true)
	public void testList() throws Exception{
		SampleDTO  sdto = new SampleDTO();
		setObj(sdto.dataList());
	}
	
	
	@ServiceMethod(callByContent=true)
	public void testMenu(){
		TestMenuPanel stp = new TestMenuPanel();
		
		TestMenu st = new TestMenu("Home");
		st.setEventMethod("test1()");
		
		ArrayList<TestMenu> sub1 = new ArrayList<TestMenu>();
		TestMenu submenu = new TestMenu("Sub_Home1");
		submenu.setEventMethod("sub_test1()");
		
		ArrayList<TestMenu> sub2 = new ArrayList<TestMenu>();
		TestMenu submenu2 = new TestMenu("Sub_Sub_Home1");
		submenu2.setEventMethod("sub_sub_test1()");
		sub2.add(submenu2);
		submenu.setChild(sub2);
		sub1.add(submenu);
		
		submenu = new TestMenu("Sub_Home2");
		submenu.setEventMethod("sub_test2()");
		sub1.add(submenu);
		
		st.setChild(sub1);
		stp.addMenu(st);
		
		st = new TestMenu("Profile");
		st.setEventMethod("test2()");
		stp.addMenu(st);
		
		setObj(stp);
	}
	
	@ServiceMethod(callByContent=true)
	public void testTab(){
		TabPanel stp = new TabPanel();
//		stp.setScrollStyle("overflow-y: scroll; height:100px;");
//		stp.setContentStyle("height: 400px; background-color:whiteSmoke;");
		
		
		Tab st = new Tab("Home");
		st.setTabContent("<p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>2</p><p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile1");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile2");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile3");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile4");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile5");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile6");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile7");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		st = new Tab("Profile8");
		st.setTabContent("<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>");
		stp.addTab(st);
		
		setObj(stp);
	}
	
	
	@ServiceMethod(callByContent=true)
	public void testAccordian(){
		AccordionPanel stp = new AccordionPanel();
		
		Accordion st = new Accordion("Collapsible Group Item #1");
		st.setContent("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.");
		stp.add(st);
		
		st = new Accordion("Collapsible Group Item #2");
		st.setContent("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.");
		stp.add(st);
		
		st = new Accordion("Collapsible Group Item #3");
		st.setContent("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.");
		stp.add(st);
		 
		setObj(stp);
	}
	
	
	@ServiceMethod(callByContent=true)
	public void testModal(){
		AccordionPanel stp = new AccordionPanel();
		
		Accordion st = new Accordion("Collapsible Group Item #1");
		st.setContent("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.");
		stp.add(st);
		
		st = new Accordion("Collapsible Group Item #2");
		st.setContent("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.");
		stp.add(st);
		
		st = new Accordion("Collapsible Group Item #3");
		st.setContent("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.");
		stp.add(st);
		
		ModalWindow window = new ModalWindow(stp, 720, 450, "Sign Up");
		setObj(window);	
		
//		TestModal st = new TestModal("Launch demo modal");
//		st.setHeader("Modal header");
//		st.setBody("<p>One fine body…</p>");
//		st.setFooterFunction("test()");
//		st.setFooterName("Save changes");
//		setObj(st);	

	}
	
	@ServiceMethod(callByContent=true)
	public void testCheckbox(){
		TestButton tb = new TestButton(TestButton.BUTTON_TYPE_CHECKBOX);
		
		tb.add("option1", "option1");
		tb.add("option2", "option2");
		tb.add("option3", "option3");
		tb.select(0);
		setObj(tb);
	}
	
	@ServiceMethod(callByContent=true)
	public void testRadio(){
		TestButton tb = new TestButton(TestButton.BUTTON_TYPE_RADIO);
		
		tb.add("option1", "option1");
		tb.add("option2", "option2");
		tb.add("option3", "option3");
		tb.select(0);
		setObj(tb);
	}
	
	@ServiceMethod(callByContent=true)
	public void testCombobox(){
		TestButton tb = new TestButton(TestButton.BUTTON_TYPE_COMBOBOX);
		
		tb.add("option1", "option1");
		tb.add("option2", "option2");
		tb.add("option3", "option3");
		tb.select(0);
		setObj(tb);
	}
}
