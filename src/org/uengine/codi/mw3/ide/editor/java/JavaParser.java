package org.uengine.codi.mw3.ide.editor.java;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;

public class JavaParser {

	String packageName;
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}

	String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}

	ArrayList<JavaField> fields;
		public ArrayList<JavaField> getFields() {
			return fields;
		}
		public void setFields(ArrayList<JavaField> fields) {
			this.fields = fields;
		}

	ArrayList<JavaMethod> methods;
		public ArrayList<JavaMethod> getMethods() {
			return methods;
		}
		public void setMethods(ArrayList<JavaMethod> methods) {
			this.methods = methods;
		}
	
	public void create() {
		
		if(this.getContent() == null)
			return;
		
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setResolveBindings(true);
		parser.setStatementsRecovery(true);
		parser.setBindingsRecovery(true);
		parser.setSource(this.getContent().toCharArray());
		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		CompilationUnit unit = (CompilationUnit)parser.createAST(null);
				
		PackageDeclaration packageDeclaration = unit.getPackage();
		TypeDeclaration typeDeclaration = (TypeDeclaration) unit.types().get(0);
		
		if(packageDeclaration != null)
			this.setPackageName(packageDeclaration.getName().getFullyQualifiedName());
		
		this.setClassName(className);
		
		ArrayList<JavaMethod> methods = new ArrayList<JavaMethod>();
		ArrayList<JavaField> fields = new ArrayList<JavaField>();
		
		for(Object declarationclass : typeDeclaration.bodyDeclarations()){
			if(declarationclass instanceof MethodDeclaration){				
				MethodDeclaration methodDeclaration  = (MethodDeclaration)declarationclass;
				
				String name = methodDeclaration.getName().getFullyQualifiedName();
				String returnTypeName = null;
				
				if(methodDeclaration.getReturnType2() instanceof SimpleType){
					SimpleType returnType = (SimpleType)methodDeclaration.getReturnType2();
					
					returnTypeName = returnType.getName().getFullyQualifiedName();
				}
					
		    	if(returnTypeName == null)
		    		returnTypeName = "void";
		    	
		    	List<JavaField> parameters = new ArrayList<JavaField>();
                for (Object parameter : methodDeclaration.parameters()) {
                	parameters.add(makeJavaField((SingleVariableDeclaration) parameter));
                }
                                                
				JavaMethod method = new JavaMethod();
		    	method.setName(name);
		    	method.setReturnType(returnTypeName);
		    	method.setParameters(parameters);
		    	method.setOwnerClassName(this.getClassName());
		    	methods.add(method);
			}else if(declarationclass instanceof FieldDeclaration){
				fields.addAll(makeJavaField((FieldDeclaration)declarationclass));
			}				
			else{
				System.out.println("else");
			}
		}
		this.setMethods(methods);
		this.setFields(fields);
		
		//sysoutunit.getPackage();
		
		
		/*
		List types = unit.types();
		
		for(int i=0; i<types.size(); i++){
			TypeDeclaration typeDeclaration  = (TypeDeclaration) types.get(i);
			ITypeBinding binding = typeDeclaration.resolveBinding();
			MethodDeclaration methodDeclaration = (MethodDeclaration) typeDeclaration.bodyDeclarations().get(1);
			IMethodBinding methodBinding = methodDeclaration.resolveBinding();
			Block body = methodDeclaration.getBody();
			VariableDeclarationStatement statement = (VariableDeclarationStatement) body.statements().get(0);
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) statement.fragments().get(0);
			IVariableBinding variableBinding = fragment.resolveBinding();
			ExpressionStatement statement2 = (ExpressionStatement) body.statements().get(1);
			Expression expression = statement2.getExpression();
			MethodInvocation invocation = (MethodInvocation) expression;
			Expression expression2 = invocation.getExpression();
			FieldDeclaration fieldDeclaration = (FieldDeclaration) typeDeclaration.bodyDeclarations().get(0);
			VariableDeclarationFragment fragment2 = (VariableDeclarationFragment) fieldDeclaration.fragments().get(0);
			IVariableBinding variableBinding2 = fragment2.resolveBinding();
		}
		
		*/
		/*
	    final ArrayList<JavaMethod> methods = new ArrayList<JavaMethod>();
	    
		cu.accept(new ASTVisitor() {
		    public boolean visit(MethodDeclaration node)
		    {
		    	String name = node.getName().toString();
		    	String returnType = node.getName().toString();
		    	
		    	if(returnType == null)
		    		returnType = "void";
		    	
		    	List<String> parameters = new ArrayList<String>();
                for (Object parameter : node.parameters()) {
                	VariableDeclaration variableDeclaration = (VariableDeclaration) parameter;
                	parameters.add(variableDeclaration.toString());
                }
                
                //node.
                
		    	JavaMethod method = new JavaMethod();
		    	method.setName(name);
		    	method.setReturnType(returnType);
		    	method.setParameters(parameters);
		    	//method.setOwnerClassName(ownerClassName)
		    	methods.add(method);
		    	
		        return true;
		    }
		});
		
		System.out.println("end ========================");
		System.out.println("end ========================");
		System.out.println("end ========================");
		System.out.println("end ========================");
		
		this.setMethods(methods);
		*/
	}
	
	private JavaField makeJavaField(SingleVariableDeclaration svc){
		JavaField field = new JavaField();
		
		SimpleType simpleType = (SimpleType)svc.getType();
		String fieldType = simpleType.getName().getFullyQualifiedName();
		String fieldName = svc.getName().getFullyQualifiedName();

		field.setName(fieldName);
		field.setType(fieldType);
		
		return field;
	}
		
	
	private ArrayList<JavaField> makeJavaField(FieldDeclaration fd){
		System.out.println("length : " + fd.getLength());
		ArrayList<JavaField> fields = new ArrayList<JavaField>();				

		SimpleType simpleType = (SimpleType)fd.getType();
		String fieldType = simpleType.getName().getFullyQualifiedName();
		
		for (Object fragment : fd.fragments()) {
			VariableDeclaration vd = (VariableDeclaration) fragment;
        
        	JavaField field = new JavaField();
        	field.setName(vd.getName().getFullyQualifiedName());
        	field.setType(fieldType);
        	field.setStatics(Modifier.isStatic(fd.getModifiers()));
        	
        	fields.add(field);
        }
		
		return fields;
	}
}
