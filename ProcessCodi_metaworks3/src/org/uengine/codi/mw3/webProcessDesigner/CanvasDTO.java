package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.annotation.Id;

public class CanvasDTO implements Serializable{
		public static final String SHAPE_TASK = "OG.shape.bpmn.A_Task";
	
	
		String id;
			@Id
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
		String swimlane;
			public String getSwimlane() {
				return swimlane;
			}
			public void setSwimlane(String swimlane) {
				this.swimlane = swimlane;
			}
		boolean drawByObject;
			public boolean isDrawByObject() {
				return drawByObject;
			}
			public void setDrawByObject(boolean drawByObject) {
				this.drawByObject = drawByObject;
			}
		String viewType;
			/* 특별하게 보이고 싶은 경우에 viewType을 셋팅하여 넘긴다*/
			public String getViewType() {
				return viewType;
			}
			public void setViewType(String viewType) {
				this.viewType = viewType;
			}
		String editorId;
			public String getEditorId() {
				return editorId;
			}
			public void setEditorId(String editorId) {
				this.editorId = editorId;
			}
		String classType;
			public String getClassType() {
				return classType;
			}
			public void setClassType(String classType) {
				this.classType = classType;
			}
		String viewClass;
			public String getViewClass() {
				return viewClass;
			}
			public void setViewClass(String viewClass) {
				this.viewClass = viewClass;
			}
		String activityClass;
			public String getActivityClass() {
				return activityClass;
			}
			public void setActivityClass(String activityClass) {
				this.activityClass = activityClass;
			}
}
