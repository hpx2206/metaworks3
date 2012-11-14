/* 패키지 선언 */
if (typeof org == "undefined") {
   function org(){}
}
if (typeof org.uengine == "undefined") {
   org.uengine = function(){}
}
if (typeof org.uengine.codi == "undefined") {
	org.uengine.codi = function(){}
}
if (typeof org.uengine.codi.mw3 == "undefined") {
	org.uengine.codi.mw3 = function(){}
}
if (typeof org.uengine.codi.mw3.webProcessDesigner == "undefined") {
	org.uengine.codi.mw3.webProcessDesigner = function(){}
}

org.uengine.codi.mw3.webProcessDesigner.ProcessValiable = function (){
	/*** 최상위 xml document */
	this.xmlDocument;
	/*** 화면에서 사용할 xml document */
	this.rootElement;
	/*** 화면에서 사용할 랜덤 id 값 */
	this.idCnt = new Date().getTime();
};

org.uengine.codi.mw3.webProcessDesigner.ProcessValiable.prototype = {
	addValiable:function(valiableNode) {
//		var findNode = this.selectSingleNode("", this.rootElement, "//processValiable[@name='"+ valiableNode.getAttribute("name") +"'] | //processValiable[@type='"+ valiableNode.getAttribute("type") +"']");	
//		if( findNode != null && findNode.getAttribute("name") == valiableNode.getAttribute("name") ){
			this.rootElement.appendChild(valiableNode);
//		}
		return valiableNode.getAttribute("id");
	},
	createValiable:function(){
		var valiableElement = this.xmlDocument.createElement("processValiable");
		valiableElement.setAttribute("id",this.getUniqueId());
		return valiableElement;
	},
	removeValiable:function(valiableNode){		
		valiableNode.parentNode.removeChild(valiableNode);
	},
	searchValiable:function(valiableId){		
		var valiableNode = this.selectSingleNode("", this.rootElement, "//processValiable[@id='"+ valiableId +"']");		
		return valiableNode;
	},
	loadXML:function(xmlString) {
		this.xmlDocument = this.loadXMLString( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+xmlString+"" );
		this.rootElement = this.xmlDocument.documentElement;
	},
	getUniqueId:function() {
		return this.idCnt++;
	},

	// xml 유틸
	loadXMLString : function(text) {
		if (window.DOMParser) {
			parser = new DOMParser();
			xmlDoc = parser.parseFromString(text, "text/xml");
		}
		// Internet Explorer
		else {
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = "false";
			xmlDoc.loadXML(text);
		}

		return xmlDoc;
	},
	createXML : function() {
		if (document.implementation && document.implementation.createDocument) {
			xmlDoc = document.implementation.createDocument("","",null);
		}
		// Internet Explorer
		else if (typeof ActiveXObject != "undefined") {
			try {
				xmlDoc = new ActiveXObject("Msxml2.DOMDocument");
			} catch (e) {
				xmlDoc = new ActiveXObject("Msxml.DOMDocument");
			}
		}
		
		return xmlDoc;
	},
	selectNodes : function(xml, xmlDoc, elementPath) {
		if (document.implementation && document.implementation.createDocument) {
			var xpe = new XPathEvaluator();
            var nsResolver = xpe.createNSResolver( xmlDoc.ownerDocument == null ? xmlDoc.documentElement : xmlDoc.ownerDocument.documentElement);
			xmlNode = xpe.evaluate(elementPath, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);			
		}
		// Internet Explorer
		else if (typeof ActiveXObject != "undefined") {
			xmlNode = xmlDoc.selectNodes(elementPath);
		}

		return xmlNode;
	},
	selectSingleNode : function(xml, xmlDoc, elementPath) {
		if (document.implementation && document.implementation.createDocument) {
			var xpe = new XPathEvaluator();
            var nsResolver = xpe.createNSResolver( xmlDoc.ownerDocument == null ? xmlDoc.documentElement : xmlDoc.ownerDocument.documentElement);
            var results = xpe.evaluate(elementPath,xmlDoc,nsResolver,XPathResult.FIRST_ORDERED_NODE_TYPE, null);
            xmlNode =  results.singleNodeValue; 
		}
		// Internet Explorer
		else if (typeof ActiveXObject != "undefined") {
			xmlNode = xmlDoc.selectSingleNode(elementPath);
		}

		return xmlNode;
	},
	toXML : function(obj) {
		if (document.implementation && document.implementation.createDocument) {
			text = (new XMLSerializer()).serializeToString(obj);
		}
		// Internet Explorer
		else if (typeof ActiveXObject != "undefined") {
			text = obj.xml;
		}

		return text;
	}
};