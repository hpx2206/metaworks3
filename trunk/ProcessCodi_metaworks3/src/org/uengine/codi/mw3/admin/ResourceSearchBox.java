package org.uengine.codi.mw3.admin;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.knowledge.WorkflowyNode;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ResourceFile;
import org.uengine.codi.mw3.model.SearchBox;
import org.uengine.components.serializers.XStreamSerializer;

import com.thoughtworks.xstream.XStream;

public class ResourceSearchBox extends SearchBox {

	public ResourceSearchBox(){	
		super();
	}
	
	public ResourceSearchBox(IUser user) {		
		setUser(user);
	}
	
	IUser user;
		
		public IUser getUser() {
			return user;
		}
	
		public void setUser(IUser user) {
			this.user = user;
		}

	@ServiceMethod(callByContent=true, target="popup")
	public Object search() throws Exception{	
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhere("design");

		ArrayList<ResourceFile> found = new ArrayList<ResourceFile>();
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();

		find(resourceBase, resourceBase, found);
	
	
		ModalWindow searchResult = new ModalWindow();
		searchResult.setTitle("Open Resource");
		searchResult.setPanel(found);
		
		return searchResult;
	}
	
	@ServiceMethod(target="popup", keyBinding="Ctrl+Shift+R@Global")
	public ModalWindow resourceSearchBoxPopup(){
		ModalWindow resourceSearchBoxPopup = new ModalWindow();
		resourceSearchBoxPopup.setTitle("Open Resource");
		resourceSearchBoxPopup.setPanel(new ResourceSearchBox());		
		return resourceSearchBoxPopup;
	}
	

    protected void find(String s, String base, ArrayList<ResourceFile> found) throws Exception
    {
        File file = new File(s);
        File afile[] = file.listFiles();
        for(int i = 0; i < afile.length; i++){
            if(afile[i].getName().indexOf(this.getKeyword()) > -1){
                found.add(convert(afile[i], base));
            }else
            if(afile[i].isDirectory())
                find(afile[i].getPath(), base, found);
        }
    }
    
    protected ResourceFile convert(File file, String base){
		ResourceFile rf = new ResourceFile();
		
		String childAlias = file.getPath().substring(base.length());
		
		rf.setName("<b>" + file.getName()  + "</b> - [" + childAlias + "]");
		rf.setAlias(childAlias);
		rf.setMetaworksContext(getMetaworksContext());
		
		rf.setObjType(childAlias.substring(childAlias.lastIndexOf(".")+1));

		return rf;
    }
		
}
