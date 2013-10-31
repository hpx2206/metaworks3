var divBackGround;
var divBugDescript;
var docObject = window.parent.window.parent.document;
	if (!docObject.getElementById("iframeWihContent")) {
		docObject = document;
	}

function setDivBackGround() {
	if (docObject.getElementById("divBackGround")) {
		divBackGround = docObject.getElementById("divBackGround");
	}
	else
	{
		if(isIE)
		{
			var htmlLayer = "<div id=\"divBackGround\" style=\"filter:alpha(opacity=70); position: absolute; z-index:100;left:0px; top:0px;display: none; width:100%; height:100%;\"></div>"
			divBackGround = docObject.createElement(htmlLayer);
		}
		else
		{
			divBackGround = document.createElement("div");
			divBackGround.id = "divBackGround";
			divBackGround.style.filter = "alpha(opacity=70)";
			divBackGround.style.position = "absolute";
			divBackGround.style.zIndex = "100";
			divBackGround.style.left = "0px";
			divBackGround.style.top = "0px";
			divBackGround.style.width = "100%";
			divBackGround.style.height = "100%";
			divBackGround.style.display = "none";
		}
		docObject.body.appendChild(divBackGround);
	}
	
	divBackGround.innerHTML = "<iframe width=\"100%\" height=\"100%\"frameborder=\"0\" scrolling=\"no\" ></iframe>";
	
}

function createDivBugDescript() {
	if (docObject.getElementById("divBugDescript")) {
		divBugDescript = docObject.getElementById("divBugDescript");
	}
	else
	{
		var elCopyLayer = null;
		if(isIE)
		{
			var htmlLayer = "<div id=\"divBugDescript\" style=\"position: absolute; z-index:101; display: none; \"></div>"; 
			divBugDescript = docObject.createElement(htmlLayer);
		}
		else
		{
			divBugDescript = document.createElement("div");
			divBugDescript.id = "divBugDescript";
			divBugDescript.style.position = "absolute";
			divBugDescript.style.zIndex = "101";
			divBugDescript.style.display = "none";
		}
		docObject.body.appendChild(divBugDescript);
	}
}

function setDivDisplay(strDisplay) {
	divBackGround.style.display = strDisplay;
	divBugDescript.style.display = strDisplay;
}

 /**
  * openPopupLayer
  * @param {type} param 
  */
function openPopupLayer() {
	setDivDisplay("");
	var elLayer = document.getElementById("divOrgBugDescript");
	divBugDescript.innerHTML = elLayer.innerHTML;
	
	var arrBtnClose = new Array();
	arrBtnClose = docObject.getElementsByName("btnCloseMsg");
	
	for (var i = 0; i < arrBtnClose.length; i++) {
		btnClose = arrBtnClose[i];
		addEvent(btnClose, "click", function(){closePopupLayer();});
	}
	
	var scrollTop = docObject.body.scrollTop;
	var scrollLeft = docObject.body.scrollLeft;
	
	var intBodyHeight = 0;
	var intBodyWidth = 0;
	
	
	intBodyHeight = docObject.body.offsetHeight;
	intBodyWidth = docObject.body.offsetWidth;
	
	var wh = (intBodyHeight / 2) - (divBugDescript.offsetHeight / 2) + scrollTop;
	var ww = (intBodyWidth / 2) - (divBugDescript.offsetWidth / 2) + scrollLeft;
	
	if (wh < 0) wh = "0";
	if (ww < 0) ww = "0";
	
	divBugDescript.style.top = wh + "px";
	divBugDescript.style.left = ww + "px";
	
	divBackGround.style.top = scrollTop + "px";
	divBackGround.style.left = scrollLeft + "px";
}

function closePopupLayer() {
	setDivDisplay("none");
}

function setErrorPage() {
	var divErrorHeader = document.getElementById("divErrorHeader");
	
	if (docObject.getElementById("iframeWihContent")) {
		parent.parent.insertMassge(divErrorHeader.innerHTML);
	} else {
		divErrorHeader.style.display = "";
	}
	
	if (docObject.getElementById("idBtnDetailinfo")) {
		docObject.getElementById("idBtnDetailinfo").onclick = function() {openPopupLayer()};
	}
	
	setDivBackGround();
	createDivBugDescript();
}
