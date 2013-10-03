package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.uengine.contexts.TextContext;
import org.uengine.kernel.And;
import org.uengine.kernel.Condition;
import org.uengine.kernel.Evaluate;
import org.uengine.kernel.Or;
import org.uengine.kernel.Otherwise;
import org.uengine.kernel.RoleExist;

public class LineShape extends CanvasDTO {
	
	public LineShape(){
		this.shapeType = "EDGE";
	}
	
	public LineShape(CanvasDTO cv){
		this.shapeId = cv.shapeId;
		this.shapeType = "EDGE";
		this.data = cv.data;
	}
	
}
