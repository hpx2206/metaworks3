package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;

public class MobileDesigner {

	@AutowiredFromClient  //TODO: lesson 0 (auto-wiring client-side objects)
	transient public ClassSourceCodes classSourceCodes;
	
	@ServiceMethod
	public Object[] make() throws Exception {
		StringBuffer sb = new StringBuffer();
/*		sb.append("    <div id=\"header\" data-theme=\"a\" data-role=\"header\">");
		sb.append("        <h3 id=\"headerTitle\">");
		sb.append("            Login Sample");
		sb.append("        </h3>");		
		sb.append("    </div>");
		
*/		
		
		sb.append("<div data-role=\"content\">");
		sb.append("    <h2 id=\"result\">");
		sb.append("        Login Success");
		sb.append("    </h2>");
		sb.append("</div>");
		
		StringBuffer sb2 = new StringBuffer();
		
		sb2.append("package demo;\n")
		   .append("\n")
		   .append("public class Result{\n")
		   .append("}\n");

		
		StringBuffer sb3 = new StringBuffer();
		sb3.append("var demo_Result = function(objectId, className){\n");
		sb3.append("	$('#' + mw3._getObjectDivId(objectId)).trigger('create');\n");
		sb3.append("}");
		
    
		/*
    
		sb.append("<div class=\"ui-body ui-body-b\">");
		sb.append("    <div data-role=\"content\" data-theme=\"b\">");
		sb.append("        <div data-role=\"fieldcontain\">");
		sb.append("            <fieldset data-role=\"controlgroup\">");
		sb.append("                <label for=\"id\">");
		sb.append("                    ID");
		sb.append("                </label>");
		sb.append("                <input name=\"\" id=\"id\" placeholder=\"\" value=\"\" type=\"text\" />");
		sb.append("            </fieldset>");
		sb.append("        </div>");
		sb.append("        <div data-role=\"fieldcontain\">");
		sb.append("            <fieldset data-role=\"controlgroup\">");
		sb.append("                <label for=\"password\">");
		sb.append("                    Password");
		sb.append("                </label>");
		sb.append("                <input name=\"\" id=\"password\" placeholder=\"\" value=\"\" type=\"password\" />");
		sb.append("            </fieldset>");
		sb.append("        </div>");
		sb.append("        <input id=\"login\" type=\"submit\" value=\"Login\" />");
		sb.append("    </div>");
		sb.append("</div>");
		   		*/

		
		FaceEditor faceEditor = new FaceEditor();
		faceEditor.getEditor().setContents(sb.toString());
		faceEditor.setCode(sb.toString());		

		
		
		/*
		sb2.append("package demo;\n")
		   .append("\n")
		   .append("import org.metaworks.annotation.ServiceMethod;\n")
		   .append("\n")
		   .append("public class Login{\n")
		   .append("	String id;\n")		
		   .append("		public String getId(){ return id; }\n")
		   .append("		public void setId(String id){ this.id = id; }\n")
		   .append("\n")
		   .append("	String password;\n")		
		   .append("		public String getPassword(){ return password; }\n")
		   .append("		public void setPassword(String password){ this.id = password; }\n")
		   .append("\n")
		   .append("	@ServiceMethod\n")
		   .append("	public void login() {\n")		
		   .append("		// TODO Auto-generated method stub\n")
		   .append("	}\n")
		   .append("}\n");

		
		StringBuffer sb3 = new StringBuffer();
		sb3.append("var demo_Login = function(objectId, className){\n");
		sb3.append("	$('#' + mw3._getObjectDivId(objectId)).trigger('create');\n");
		sb3.append("}");
		*/
		
		FaceHelperSourceCode faceHelperSourceCode = new FaceHelperSourceCode();
		faceHelperSourceCode.setCode(sb3.toString());
		
		classSourceCodes.getSourceCode().setCode(sb2.toString());
		
		return new Object[]{classSourceCodes.getSourceCode(), faceEditor, faceHelperSourceCode};
		
	}
}
