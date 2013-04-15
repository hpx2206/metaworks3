package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.uengine.kernel.Activity;

public class CanvasDTO implements Serializable{
		String id;
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
		String parent;
			public String getParent() {
				return parent;
			}
			public void setParent(String parent) {
				this.parent = parent;
			}
		String shapeType;
			public String getShapeType() {
				return shapeType;
			}
			public void setShapeType(String shapeType) {
				this.shapeType = shapeType;
			}
		String shapeId;
			public String getShapeId() {
				return shapeId;
			}
			public void setShapeId(String shapeId) {
				this.shapeId = shapeId;
			}
		String x;
			public String getX() {
				return x;
			}
			public void setX(String x) {
				this.x = x;
			}
		String y;
			public String getY() {
				return y;
			}
			public void setY(String y) {
				this.y = y;
			}
		String width;
			public String getWidth() {
				return width;
			}
			public void setWidth(String width) {
				this.width = width;
			}
		String height;
			public String getHeight() {
				return height;
			}
			public void setHeight(String height) {
				this.height = height;
			}
		String style;
			public String getStyle() {
				return style;
			}
			public void setStyle(String style) {
				this.style = style;
			}
		String from;
			public String getFrom() {
				return from;
			}
			public void setFrom(String from) {
				this.from = from;
			}
		String to;
			public String getTo() {
				return to;
			}
			public void setTo(String to) {
				this.to = to;
			}
		String fromEdge;
			public String getFromEdge() {
				return fromEdge;
			}
			public void setFromEdge(String fromEdge) {
				this.fromEdge = fromEdge;
			}
		String toEdge;
			public String getToEdge() {
				return toEdge;
			}
			public void setToEdge(String toEdge) {
				this.toEdge = toEdge;
			}
		String label;
			public String getLabel() {
				return label;
			}
			public void setLabel(String label) {
				this.label = label;
			}
		String angle;
			public String getAngle() {
				return angle;
			}
			public void setAngle(String angle) {
				this.angle = angle;
			}
		String value;
			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}
		String data;
			public String getData() {
				return data;
			}
			public void setData(String data) {
				this.data = data;
			}
		String jsonString;
			public String getJsonString() {
				return jsonString;
			}
			public void setJsonString(String jsonString) {
				this.jsonString = jsonString;
			}
		String tracingTag;
			public String getTracingTag() {
				return tracingTag;
			}
			public void setTracingTag(String tracingTag) {
				this.tracingTag = tracingTag;
			}
			
		String classname;
			public String getClassname() {
				return classname;
			}
			public void setClassname(String classname) {
				this.classname = classname;
			}
		String classType;
			public String getClassType() {
				return classType;
			}
			public void setClassType(String classType) {
				this.classType = classType;
			}
		String roleName;
			public String getRoleName() {
				return roleName;
			}
			public void setRoleName(String roleName) {
				this.roleName = roleName;
			}
		/*  viewer 부분에서  필요한 정보들 transient */
		transient String instStatus;
			public String getInstStatus() {
				return instStatus;
			}
			public void setInstStatus(String instStatus) {
				this.instStatus = instStatus;
			}
		transient String backgroundColor;
			public String getBackgroundColor() {
				return backgroundColor;
			}
			public void setBackgroundColor(String backgroundColor) {
				this.backgroundColor = backgroundColor;
			}
		transient Activity activity;
			public Activity getActivity() {
				return activity;
			}
			public void setActivity(Activity activity) {
				this.activity = activity;
			}
}
