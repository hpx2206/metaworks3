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
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.util.CodiFileUtil;
import org.uengine.codi.util.CodiStringUtil;

@Face(displayName="$templete.class", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"packageName,name"})
public class NewClass extends Templete {

	String packageName;
		@Face(displayName="$templete.class.package")
		@Hidden
		public String getPackageName() {
			return packageName;
		}
	
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

	String name;
		@Face(displayName="$templete.class.name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
	public void load(){
		String packageName = ResourceNode.makePackageName(this.getResourceNode().getId());
		
		this.setPackageName(packageName);
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
		
	@Override
	public Object[] finish() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode targetNode = (ResourceNode)clipboard;
			
			ResourceNode node = new ResourceNode();
			node.setName(this.getName() + ".java");
			node.setId(targetNode.getId() + File.separatorChar + node.getName());
			node.setPath(targetNode.getPath() + File.separatorChar + node.getName());
			node.setProjectId(targetNode.getProjectId());
			node.setParentId(targetNode.getParentId());
			node.setType(targetNode.getType());
			
			if(CodiFileUtil.exists(node.getPath()))
				throw new Exception("$file.already.exists");

			Editor editor = null;
			try {
				editor = (Editor)node.beforeAction();
				editor.setContent(this.make());
			} catch (Exception e) {
			}
			
			editor.save();
			
			return new Object[]{new ToAppend(targetNode, node), new ToAppend(new CloudWindow("editor"), editor) , new Remover(new ModalWindow())};
		}else{
			throw new MetaworksException("finish error");
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
