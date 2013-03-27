package org.uengine.codi.mw3.ide.templete;

import java.io.File;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.JavaBuildPath;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"packageName,name"})
public class NewClass extends Templete {

	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public JavaBuildPath jbPath;
	
	String packageName;
		@Face(displayName="Package")
		@Hidden
		public String getPackageName() {
			return packageName;
		}
	
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

	String name;
		@Face(displayName="Name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
	public void load(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			String packageName = "";
			
			if(node.getId().length() > 0){
				if(node.getId().startsWith(jbPath.getSrcPath()) && node.getId().length() != jbPath.getSrcPath().length()){
					packageName = node.getId().substring(jbPath.getSrcPath().length() + 1);
				}
			}
			
			this.setPackageName(packageName);
		}
	}
	
	public String make(){
		ASTParser parser = ASTParser.newParser(AST.JLS2);
		parser.setSource("".toCharArray());

		CompilationUnit unit = (CompilationUnit) parser.createAST(null); 
		unit.recordModifications();
		
		AST ast = unit.getAST();
		
		if(this.getPackageName() != null && this.getPackageName().length() > 0){
			PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
			unit.setPackage(packageDeclaration);
			packageDeclaration.setName(ast.newSimpleName(this.getPackageName()));
		}
		
		TypeDeclaration classType = ast.newTypeDeclaration();
		classType.setInterface(false);
		classType.setModifiers(Modifier.PUBLIC);
		classType.setName(ast.newSimpleName(this.getName()));
		unit.types().add(classType);
		
		return unit.toString();
	}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] finish() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			String parentId = jbPath.getSrcPath();
			if(this.getPackageName() != null && this.getPackageName().length() > 0)
				parentId += File.separatorChar + this.getPackageName();
			
			ResourceNode targetNode = new ResourceNode();
			targetNode.setId(parentId);
			
			ResourceNode node = new ResourceNode();
			node.setName(this.getName() + ".java");
			node.setId(parentId + File.separatorChar + node.getName());			
			node.setType(TreeNode.TYPE_FILE_TEXT);
			
			JavaCodeEditor editor = new JavaCodeEditor();
			editor.jbPath = jbPath;
			editor.setFilename(jbPath.getSrcPath() + File.separatorChar + this.getPackageName() + File.separatorChar + this.getName());
			editor.setName(this.getName());
			editor.setLoaded(true);
			editor.setContent(this.make());
			editor.save();
			
			return new Object[]{new Remover(new ModalWindow()), new ToAppend(targetNode, node), new ToAppend(new CloudWindow("editor"), editor)};
		}else{
			throw new MetaworksException("finish error");
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
