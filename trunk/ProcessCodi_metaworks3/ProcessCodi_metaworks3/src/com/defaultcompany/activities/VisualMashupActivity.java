package com.defaultcompany.activities;

import java.io.IOException;

import javax.swing.tree.TreeModel;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.metaworks.FieldDescriptor;
import org.metaworks.Type;
import org.metaworks.inputter.Picker;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.processdesigner.ProcessDesigner;
import org.uengine.ui.DomToTreeModelAdapter;
import org.uengine.ui.XMLValueInput;
import org.uengine.ui.XMLValuePicker;

public class VisualMashupActivity extends HumanActivity{

	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
	
	private static final String URL_pageXML_JSP = "/html/uengine-web/usermanager/pageListXML.jsp";
	
	public static void metaworksCallback_changeMetadata(Type type){
	//	RoleResolutionContext.metaworksCallback_changeMetadata(type); //call the super's one first

		FieldDescriptor fd;
		
		fd = type.getFieldDescriptor("Page");	
		fd.setInputter(new XMLValueInputForVisualMashup(URL_pageXML_JSP));
	}

	public VisualMashupActivity(){
		//super();
		setName("VisualMashup");
	}
	
	public String getTool() {
		//return super.getTool();
		return "mashupHandler";
    }
	
	String page;
		public String getPage() {
			return page;
		}
	
		public void setPage(String page) {
			this.page = page;
		}
		
}

// XMLValueInput

class XMLValueInputForVisualMashup extends XMLValueInput{
	String xmlURL;
	boolean partialLoading;
	
	
	public XMLValueInputForVisualMashup(String url){
		this(url, false);
	}
	
	public XMLValueInputForVisualMashup(String url, boolean partialLoading){
		super(url, partialLoading);
		this.xmlURL = url;
		this.partialLoading = partialLoading;
	}

	public XMLValueInputForVisualMashup(String url, boolean partialLoading, boolean isAbsoluteCreatePicker){
		super(url, partialLoading, isAbsoluteCreatePicker);
		this.xmlURL = url;
		this.partialLoading = partialLoading;
	}
	
	public Picker getNewPicker(){
		return new XMLValuePickerForVisualMashup(xmlURL, partialLoading);
	}

}


// XMLValuePicker

class XMLValuePickerForVisualMashup extends XMLValuePicker {
	
	public XMLValuePickerForVisualMashup(final String url, final boolean partialLoading) {
		super(url, partialLoading);
	} 
	
	protected TreeModel createTreeModel(final String url, final boolean partialLoading) {
		return new DomToTreeModelAdapter(url, partialLoading, true);
	}

}


