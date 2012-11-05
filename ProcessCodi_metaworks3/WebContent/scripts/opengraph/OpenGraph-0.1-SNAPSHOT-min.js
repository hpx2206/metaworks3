var OG=window.OG||{};
OG.common={};
OG.geometry={};
OG.graph={};
OG.handler={};
OG.layout={};
OG.renderer={};
OG.shape={};
OG.shape.bpmn={};
OG.common.Constants={CANVAS_BACKGROUND:"#f9f9f9",GEOM_TYPE:{NULL:0,POINT:1,LINE:2,POLYLINE:3,POLYGON:4,RECTANGLE:5,CIRCLE:6,ELLIPSE:7,CURVE:8,BEZIER_CURVE:9,COLLECTION:10},GEOM_NAME:["","Point","Line","PolyLine","Polygon","Rectangle","Circle","Ellipse","Curve","BezierCurve","Collection"],NUM_PRECISION:0,NODE_TYPE:{ROOT:"ROOT",SHAPE:"SHAPE"},SHAPE_TYPE:{GEOM:"GEOM",TEXT:"TEXT",HTML:"HTML",IMAGE:"IMAGE",EDGE:"EDGE",GROUP:"GROUP"},EDGE_TYPE:{STRAIGHT:"straight",PLAIN:"plain",BEZIER:"bezier"},EDGE_PADDING:20,LABEL_PADDING:5,LABEL_EDITOR_WIDTH:70,LABEL_EDITOR_HEIGHT:16,FROMTO_LABEL_OFFSET_TOP:15,LABEL_SUFFIX:"_LABEL",LABEL_EDITOR_SUFFIX:"_LABEL_EDITOR",FROM_LABEL_SUFFIX:"_FROMLABEL",TO_LABEL_SUFFIX:"_TOLABEL",DEFAULT_STYLE:{SHAPE:{cursor:"default"},GEOM:{stroke:"black",fill:"white","fill-opacity":0,"label-position":"center"},TEXT:{stroke:"none","text-anchor":"middle"},HTML:{"label-position":"bottom","text-anchor":"middle","vertical-align":"top"},IMAGE:{"label-position":"bottom","text-anchor":"middle","vertical-align":"top"},EDGE:{stroke:"black",fill:"none","fill-opacity":0,"stroke-width":1,"stroke-opacity":1,"edge-type":"plain","edge-direction":"c c","arrow-start":"none","arrow-end":"classic-wide-long","stroke-dasharray":"","label-position":"center"},EDGE_SHADOW:{stroke:"#00FF00",fill:"none","fill-opacity":0,"stroke-width":1,"stroke-opacity":1,"arrow-start":"none","arrow-end":"none","stroke-dasharray":"- "},EDGE_HIDDEN:{stroke:"white",fill:"none","fill-opacity":0,"stroke-width":5,"stroke-opacity":0},GROUP:{stroke:"none",fill:"white","fill-opacity":0,"label-position":"bottom","text-anchor":"middle","vertical-align":"top"},GUIDE_BBOX:{stroke:"#00FF00",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},GUIDE_UL:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_UR:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LL:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LR:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_LC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_UC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_RC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_LWC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_FROM:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_TO:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_CTL_H:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_CTL_V:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_SHADOW:{stroke:"black",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},RUBBER_BAND:{stroke:"#0000FF",opacity:0.2,fill:"#0077FF"},TERMINAL:{stroke:"#808080","stroke-width":1,fill:"r(0.5, 0.5)#FFFFFF-#808080","fill-opacity":0.5,cursor:"pointer"},TERMINAL_OVER:{stroke:"#0077FF","stroke-width":4,fill:"r(0.5, 0.5)#FFFFFF-#0077FF","fill-opacity":1,cursor:"pointer"},TERMINAL_BBOX:{stroke:"none",fill:"white","fill-opacity":0},DROP_OVER_BBOX:{stroke:"#0077FF",fill:"none",opacity:0.6,"shape-rendering":"crispEdges"},LABEL:{"font-size":12,"font-color":"black"},LABEL_EDITOR:{position:"absolute",overflow:"visible",resize:"none","text-align":"center",display:"block",padding:0},COLLAPSE:{stroke:"black",fill:"white","fill-opacity":0,cursor:"pointer","shape-rendering":"crispEdges"},COLLAPSE_BBOX:{stroke:"none",fill:"white","fill-opacity":0}},RUBBER_BAND_ID:"OG_R_BAND",GUIDE_SUFFIX:{GUIDE:"_GUIDE",BBOX:"_GUIDE_BBOX",UL:"_GUIDE_UL",UR:"_GUIDE_UR",LL:"_GUIDE_LL",LR:"_GUIDE_LR",LC:"_GUIDE_LC",UC:"_GUIDE_UC",RC:"_GUIDE_RC",LWC:"_GUIDE_LWC",FROM:"_GUIDE_FROM",TO:"_GUIDE_TO",CTL:"_GUIDE_CTL_",CTL_H:"_GUIDE_CTL_H_",CTL_V:"_GUIDE_CTL_V_"},GUIDE_RECT_SIZE:8,GUIDE_MIN_SIZE:18,COLLAPSE_SUFFIX:"_COLLAPSE",COLLAPSE_BBOX_SUFFIX:"_COLLAPSE_BBOX",COLLAPSE_SIZE:10,MOVE_SNAP_SIZE:5,DROP_OVER_BBOX_SUFFIX:"_DROP_OVER",TERMINAL_SUFFIX:{GROUP:"_TERMINAL",BOX:"_TERMINAL_BOX"},TERMINAL_TYPE:{C:"C",E:"E",W:"W",S:"S",N:"N",IN:"IN",OUT:"OUT",INOUT:"INOUT"},TERMINAL_SIZE:3,COPY_PASTE_PADDING:20,AUTO_EXTENSIONAL:true,AUTO_EXTENSION_SIZE:100,SELECTABLE:true,DRAG_SELECTABLE:true,MOVABLE:true,MOVABLE_GEOM:true,MOVABLE_TEXT:true,MOVABLE_HTML:true,MOVABLE_IMAGE:true,MOVABLE_EDGE:true,MOVABLE_GROUP:true,RESIZABLE:true,RESIZABLE_GEOM:true,RESIZABLE_TEXT:true,RESIZABLE_HTML:true,RESIZABLE_IMAGE:true,RESIZABLE_EDGE:true,RESIZABLE_GROUP:true,CONNECTABLE:true,SELF_CONNECTABLE:true,CONNECT_CLONEABLE:true,CONNECT_REQUIRED:true,LABEL_EDITABLE:true,LABEL_EDITABLE_GEOM:true,LABEL_EDITABLE_TEXT:true,LABEL_EDITABLE_HTML:true,LABEL_EDITABLE_IMAGE:true,LABEL_EDITABLE_EDGE:true,LABEL_EDITABLE_GROUP:true,GROUP_DROPABLE:true,GROUP_COLLAPSIBLE:true,DRAG_GRIDABLE:true,ENABLE_HOTKEY:true,ENABLE_HOTKEY_DELETE:true,ENABLE_HOTKEY_CTRL_A:true,ENABLE_HOTKEY_CTRL_C:true,ENABLE_HOTKEY_CTRL_V:true,ENABLE_HOTKEY_CTRL_D:true,ENABLE_HOTKEY_CTRL_G:true,ENABLE_HOTKEY_CTRL_U:true,ENABLE_HOTKEY_ARROW:true,ENABLE_HOTKEY_SHIFT_ARROW:true,ENABLE_CONTEXTMENU:true};
OG.Constants=OG.common.Constants;
if(typeof KeyEvent==="undefined"){var KeyEvent={DOM_VK_CANCEL:3,DOM_VK_HELP:6,DOM_VK_BACK_SPACE:8,DOM_VK_TAB:9,DOM_VK_CLEAR:12,DOM_VK_RETURN:13,DOM_VK_ENTER:14,DOM_VK_SHIFT:16,DOM_VK_CONTROL:17,DOM_VK_ALT:18,DOM_VK_PAUSE:19,DOM_VK_CAPS_LOCK:20,DOM_VK_ESCAPE:27,DOM_VK_SPACE:32,DOM_VK_PAGE_UP:33,DOM_VK_PAGE_DOWN:34,DOM_VK_END:35,DOM_VK_HOME:36,DOM_VK_LEFT:37,DOM_VK_UP:38,DOM_VK_RIGHT:39,DOM_VK_DOWN:40,DOM_VK_PRINTSCREEN:44,DOM_VK_INSERT:45,DOM_VK_DELETE:46,DOM_VK_0:48,DOM_VK_1:49,DOM_VK_2:50,DOM_VK_3:51,DOM_VK_4:52,DOM_VK_5:53,DOM_VK_6:54,DOM_VK_7:55,DOM_VK_8:56,DOM_VK_9:57,DOM_VK_SEMICOLON:59,DOM_VK_EQUALS:61,DOM_VK_A:65,DOM_VK_B:66,DOM_VK_C:67,DOM_VK_D:68,DOM_VK_E:69,DOM_VK_F:70,DOM_VK_G:71,DOM_VK_H:72,DOM_VK_I:73,DOM_VK_J:74,DOM_VK_K:75,DOM_VK_L:76,DOM_VK_M:77,DOM_VK_N:78,DOM_VK_O:79,DOM_VK_P:80,DOM_VK_Q:81,DOM_VK_R:82,DOM_VK_S:83,DOM_VK_T:84,DOM_VK_U:85,DOM_VK_V:86,DOM_VK_W:87,DOM_VK_X:88,DOM_VK_Y:89,DOM_VK_Z:90,DOM_VK_COMMAND:91,DOM_VK_CONTEXT_MENU:93,DOM_VK_NUMPAD0:96,DOM_VK_NUMPAD1:97,DOM_VK_NUMPAD2:98,DOM_VK_NUMPAD3:99,DOM_VK_NUMPAD4:100,DOM_VK_NUMPAD5:101,DOM_VK_NUMPAD6:102,DOM_VK_NUMPAD7:103,DOM_VK_NUMPAD8:104,DOM_VK_NUMPAD9:105,DOM_VK_MULTIPLY:106,DOM_VK_ADD:107,DOM_VK_SEPARATOR:108,DOM_VK_SUBTRACT:109,DOM_VK_DECIMAL:110,DOM_VK_DIVIDE:111,DOM_VK_F1:112,DOM_VK_F2:113,DOM_VK_F3:114,DOM_VK_F4:115,DOM_VK_F5:116,DOM_VK_F6:117,DOM_VK_F7:118,DOM_VK_F8:119,DOM_VK_F9:120,DOM_VK_F10:121,DOM_VK_F11:122,DOM_VK_F12:123,DOM_VK_F13:124,DOM_VK_F14:125,DOM_VK_F15:126,DOM_VK_F16:127,DOM_VK_F17:128,DOM_VK_F18:129,DOM_VK_F19:130,DOM_VK_F20:131,DOM_VK_F21:132,DOM_VK_F22:133,DOM_VK_F23:134,DOM_VK_F24:135,DOM_VK_NUM_LOCK:144,DOM_VK_SCROLL_LOCK:145,DOM_VK_COMMA:188,DOM_VK_PERIOD:190,DOM_VK_SLASH:191,DOM_VK_BACK_QUOTE:192,DOM_VK_OPEN_BRACKET:219,DOM_VK_BACK_SLASH:220,DOM_VK_CLOSE_BRACKET:221,DOM_VK_QUOTE:222,DOM_VK_META:224}
};OG.common.Util={isEmpty:function(b,a){return b===null||b===undefined||((OG.Util.isArray(b)&&!b.length))||(!a?b==="":false)
},isArray:function(a){return Object.prototype.toString.apply(a)==="[object Array]"
},isDate:function(a){return Object.prototype.toString.apply(a)==="[object Date]"
},isObject:function(a){return !!a&&Object.prototype.toString.call(a)==="[object Object]"
},isPrimitive:function(a){return OG.Util.isString(a)||OG.Util.isNumber(a)||OG.Util.isBoolean(a)
},isFunction:function(a){return Object.prototype.toString.apply(a)==="[object Function]"
},isNumber:function(a){return typeof a==="number"&&isFinite(a)
},isString:function(a){return typeof a==="string"
},isBoolean:function(a){return typeof a==="boolean"
},isElement:function(a){return !!a&&a.tagName?true:false
},isDefined:function(a){return typeof a!=="undefined"
},isWebKit:function(){return(/webkit/).test(navigator.userAgent.toLowerCase())
},isGecko:function(){return !OG.Util.isWebKit()&&(/gecko/).test(navigator.userAgent.toLowerCase())
},isOpera:function(){return(/opera/).test(navigator.userAgent.toLowerCase())
},isChrome:function(){return(/\bchrome\b/).test(navigator.userAgent.toLowerCase())
},isSafari:function(){return !OG.Util.isChrome()&&(/safari/).test(navigator.userAgent.toLowerCase())
},isFirefox:function(){return(/firefox/).test(navigator.userAgent.toLowerCase())
},isIE:function(){return !OG.Util.isOpera()&&(/msie/).test(navigator.userAgent.toLowerCase())
},isIE6:function(){return OG.Util.isIE()&&(/msie 6/).test(navigator.userAgent.toLowerCase())
},isIE7:function(){return OG.Util.isIE()&&((/msie 7/).test(navigator.userAgent.toLowerCase())||document.documentMode===7)
},isIE8:function(){return OG.Util.isIE()&&((/msie 8/).test(navigator.userAgent.toLowerCase())||document.documentMode===8)
},isIE9:function(){return OG.Util.isIE()&&((/msie 9/).test(navigator.userAgent.toLowerCase())||document.documentMode===9)
},isWindows:function(){return(/windows|win32/).test(navigator.userAgent.toLowerCase())
},isMac:function(){return(/macintosh|mac os x/).test(navigator.userAgent.toLowerCase())
},isLinux:function(){return(/linux/).test(navigator.userAgent.toLowerCase())
},clone:function(g){if(g===null||g===undefined){return g
}if(g.nodeType&&g.cloneNode){return g.cloneNode(true)
}var e,c,b,h,d,f=Object.prototype.toString.call(g),a=["hasOwnProperty","valueOf","isPrototypeOf","propertyIsEnumerable","toLocaleString","toString","constructor"];
if(f==="[object Date]"){return new Date(g.getTime())
}if(f==="[object Array]"){e=g.length;
h=[];
while(e--){h[e]=this.clone(g[e])
}}else{if(f==="[object Object]"&&g.constructor===Object){h={};
for(d in g){h[d]=this.clone(g[d])
}if(a){for(c=a.length;
c--;
){b=a[c];
h[b]=g[b]
}}}}return h||g
},round:function(a){return this.roundPrecision(a,OG.Constants.NUM_PRECISION)
},roundPrecision:function(c,a){var b=Math.pow(10,a);
return Math.round(c*b)/b
},roundGrid:function(a,b){b=b||OG.Constants.MOVE_SNAP_SIZE;
return OG.Util.round(a/b)*b
},apply:function(d,a,c){var b;
if(c){this.apply(d,c)
}if(d&&a&&typeof a==="object"){for(b in a){d[b]=a[b]
}}return d
},extend:(function(){var b=function(d){var c;
for(c in d){this[c]=d[c]
}},a=Object.prototype.constructor;
return function(h,e,g){if(OG.Util.isObject(e)){g=e;
e=h;
h=g.constructor!==a?g.constructor:function(){e.apply(this,arguments)
}
}var d=function(){},f,c=e.prototype;
d.prototype=c;
f=h.prototype=new d();
f.constructor=h;
h.superclass=c;
if(c.constructor===a){c.constructor=e
}h.override=function(i){OG.Util.override(h,i)
};
f.superclass=f.supr=(function(){return c
}());
f.override=b;
OG.Util.override(h,g);
h.extend=function(i){return OG.Util.extend(h,i)
};
return h
}
}()),override:function(a,c){if(c){var b=a.prototype;
OG.Util.apply(b,c);
if((/msie/).test(navigator.userAgent.toLowerCase())&&c.hasOwnProperty("toString")){b.toString=c.toString
}}},xmlToJson:function(c){var b={},a=function(f){var e={};
for(var g in f){if(f.hasOwnProperty(g)){e[g]=f[g]
}}return e
},d=function(g,m,r){if(g.nodeType===3){if(!g.nodeValue.match(/[\S]+/)){return
}if(m["$"] instanceof Array){m["$"].push(g.nodeValue)
}else{if(m["$"] instanceof Object){m["$"]=[m["$"],g.nodeValue]
}else{m["$"]=g.nodeValue
}}}else{if(g.nodeType===1){var f={};
var s=g.nodeName;
for(var n=0;
g.attributes&&n<g.attributes.length;
n++){var q=g.attributes[n];
var e=q.nodeName;
var t=q.nodeValue;
if(e==="xmlns"){r["$"]=t
}else{if(e.indexOf("xmlns:")===0){r[e.substr(e.indexOf(":")+1)]=t
}else{f["@"+e]=t
}}}for(var o in r){if(r.hasOwnProperty(o)){f["@xmlns"]=f["@xmlns"]||{};
f["@xmlns"][o]=r[o]
}}if(m[s] instanceof Array){m[s].push(f)
}else{if(m[s] instanceof Object){m[s]=[m[s],f]
}else{m[s]=f
}}for(var l=0;
l<g.childNodes.length;
l++){d(g.childNodes[l],f,a(r))
}}else{if(g.nodeType===9){for(var h=0;
h<g.childNodes.length;
h++){d(g.childNodes[h],m,a(r))
}}}}};
d(c,b,{});
return b
},jsonToXml:function(d){if(typeof d!=="object"){return null
}var b=function(f){var e={};
for(var g in f){if(f.hasOwnProperty(g)){e[g]=f[g]
}}return e
};
var c=function(n,e,o){var m="";
if(e instanceof Array){for(var k=0;
k<e.length;
k++){m+=c(n,e[k],b(o))
}return m
}else{if(typeof e==="object"){var f="<"+n;
var h="";
var q="";
if(e["@xmlns"]){var g=e["@xmlns"];
for(var l in g){if(g.hasOwnProperty(l)){if(l==="$"){if(o[l]!==g[l]){h+=' xmlns="'+g[l]+'"';
o[l]=g[l]
}}else{if(!o[l]||(o[l]!==g[l])){h+=" xmlns:"+l+'="'+g[l]+'"';
o[l]=g[l]
}}}}}for(var p in e){if(e.hasOwnProperty(p)&&p!=="@xmlns"){var j=e[p];
if(p==="$"){q+=j
}else{if(p.indexOf("@")===0){h+=" "+p.substring(1)+'="'+j+'"'
}else{m+=c(p,j,b(o))
}}}}m=q+m;
return(m!=="")?f+h+">"+m+"</"+n+">":f+h+"/>"
}}};
for(var a in d){if(d.hasOwnProperty(a)&&a.indexOf("@")==-1){return'<?xml version="1.0" encoding="UTF-8"?>'+c(a,d[a],{})
}}return null
},parseXML:function(b){var a,c;
if(window.ActiveXObject){a=new ActiveXObject("Microsoft.XMLDOM");
a.async="false";
a.loadXML(b)
}else{c=new DOMParser();
a=c.parseFromString(b,"text/xml")
}return a
}};
OG.Util=OG.common.Util;OG.common.CurveUtil={CatmullRomSpline:function(c){var b=[],e,f={},d={},a=function(g){return function(j,i){var h=c.length,k,l;
if(h<2){return NaN
}j=j-1;
if(!i&&b[g]){i=true
}if(!i){f[g]=2*c[0][g]-c[1][g];
d[g]=2*c[h-1][g]-c[h-2][g];
e=[f].concat(c,[d]);
b[g]=[];
for(k=0;
k<h-1;
k++){b[g][k]=[2*e[k+1][g],-e[k][g]+e[k+2][g],2*e[k][g]-5*e[k+1][g]+4*e[k+2][g]-e[k+3][g],-e[k][g]+3*e[k+1][g]-3*e[k+2][g]+e[k+3][g]]
}}h+=2;
if(isNaN(j)){return NaN
}if(j<0){return e[1][g]
}else{if(j>=h-3){return e[h-2][g]
}}k=Math.floor(j);
if(k===j){return e[k][g]
}j-=k;
l=b[g][k];
return 0.5*(((l[3]*j+l[2])*j+l[1])*j+l[0])
}
};
return{getX:a(0),getY:a(1),maxT:c.length+1}
},Bezier:function(c){var a,b=function(d){return function(f,e){var i=Math.floor(f)*3,h=f,g=1-h;
if(!e&&a){e=true
}if(!e){a=Math.floor(c.length/3)
}if(f<0){return c[0][d]
}if(f>=a){return c[c.length-1][d]
}if(isNaN(f)){return NaN
}return g*g*(g*c[i][d]+3*h*c[i+1][d])+(3*g*c[i+2][d]+h*c[i+3][d])*h*h
}
};
return{getX:b(0),getY:b(1),maxT:Math.floor(c.length/3)+1}
},BSpline:function(d,a){var e,g=[],f=function(m,h){var i,l=[];
for(i=0;
i<m+h+1;
i++){if(i<h){l[i]=0
}else{if(i<=m){l[i]=i-h+1
}else{l[i]=m-h+2
}}}return l
},c=function(v,x,h,l,w){var o,m,r,q,u,p=[];
if(x[w]<=v&&v<x[w+1]){p[w]=1
}else{p[w]=0
}for(o=2;
o<=l;
o++){for(m=w-o+1;
m<=w;
m++){if(m<=w-o+1||m<0){r=0
}else{r=p[m]
}if(m>=w){q=0
}else{q=p[m+1]
}u=x[m+o-1]-x[m];
if(u===0){p[m]=0
}else{p[m]=(v-x[m])/u*r
}u=x[m+o]-x[m+1];
if(u!==0){p[m]+=(x[m+o]-v)/u*q
}}}return p
},b=function(h){return function(p,l){var i=d.length,u,o,q,r=i-1,m=a;
if(r<=0){return NaN
}if(r+2<=m){m=r+1
}if(p<=0){return d[0][h]
}if(p>=r-m+2){return d[r][h]
}e=f(r,m);
q=Math.floor(p)+m-1;
g=c(p,e,r,m,q);
u=0;
for(o=q-m+1;
o<=q;
o++){if(o<i&&o>=0){u+=d[o][h]*g[o]
}}return u
}
};
return{getX:b(0),getY:b(1),maxT:d.length-2}
}};
OG.CurveUtil=OG.common.CurveUtil;OG.common.NotSupportedException=function(a){this.name="OG.NotSupportedException";
this.message=a||"Not Supported!"
};
OG.NotSupportedException=OG.common.NotSupportedException;
OG.common.NotImplementedException=function(a){this.name="OG.NotImplementedException";
this.message=a||"Not Implemented!"
};
OG.NotImplementedException=OG.common.NotImplementedException;
OG.common.ParamError=function(a){this.name="OG.ParamError";
this.message=a||"Invalid Parameter Error!"
};
OG.ParamError=OG.common.ParamError;OG.common.HashMap=function(a){this.map=a||{};
this.put=function(b,c){this.map[b]=c
};
this.get=function(b){return this.map[b]
};
this.containsKey=function(b){return this.map.hasOwnProperty(b)
};
this.containsValue=function(b){var c;
for(c in this.map){if(this.map[c]===b){return true
}}return false
};
this.isEmpty=function(){return this.size()===0
};
this.clear=function(){var b;
for(b in this.map){delete this.map[b]
}};
this.remove=function(b){if(this.map[b]){delete this.map[b]
}};
this.keys=function(){var b=[],c;
for(c in this.map){b.push(c)
}return b
};
this.values=function(){var b=[],c;
for(c in this.map){b.push(this.map[c])
}return b
};
this.size=function(){var b=0,c;
for(c in this.map){b++
}return b
};
this.toString=function(){var b=[],c;
for(c in this.map){b.push("'"+c+"':'"+this.map[c]+"'")
}return"{"+b.join()+"}"
}
};
OG.common.HashMap.prototype=new OG.common.HashMap();
OG.common.HashMap.prototype.constructor=OG.common.HashMap;
OG.HashMap=OG.common.HashMap;OG.common.JSON=new (function(){var useHasOwn=!!{}.hasOwnProperty,USE_NATIVE_JSON=false,isNative=(function(){var useNative=null;
return function(){if(useNative===null){useNative=USE_NATIVE_JSON&&window.JSON&&JSON.toString()==="[object JSON]"
}return useNative
}
}()),m={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"},pad=function(n){return n<10?"0"+n:n
},doDecode=function(json){return eval("("+json+")")
},encodeString=function(s){if(/["\\\x00-\x1f]/.test(s)){return'"'+s.replace(/([\x00-\x1f\\"])/g,function(a,b){var c=m[b];
if(c){return c
}c=b.charCodeAt();
return"\\u00"+Math.floor(c/16).toString(16)+(c%16).toString(16)
})+'"'
}return'"'+s+'"'
},encodeArray=function(o){var a=["["],b,i,l=o.length,v;
for(i=0;
i<l;
i+=1){v=o[i];
switch(typeof v){case"undefined":case"function":case"unknown":break;
default:if(b){a.push(",")
}a.push(v===null?"null":OG.common.JSON.encode(v));
b=true
}}a.push("]");
return a.join("")
},doEncode=function(o){if(!OG.Util.isDefined(o)||o===null){return"null"
}else{if(OG.Util.isArray(o)){return encodeArray(o)
}else{if(OG.Util.isDate(o)){return OG.common.JSON.encodeDate(o)
}else{if(OG.Util.isString(o)){return encodeString(o)
}else{if(typeof o==="number"){return isFinite(o)?String(o):"null"
}else{if(OG.Util.isBoolean(o)){return String(o)
}else{var a=["{"],b,i,v;
for(i in o){if(!o.getElementsByTagName){if(!useHasOwn||o.hasOwnProperty(i)){v=o[i];
switch(typeof v){case"undefined":case"function":case"unknown":break;
default:if(b){a.push(",")
}a.push(doEncode(i),":",v===null?"null":doEncode(v));
b=true
}}}}a.push("}");
return a.join("")
}}}}}}};
this.encodeDate=function(o){return'"'+o.getFullYear()+"-"+pad(o.getMonth()+1)+"-"+pad(o.getDate())+"T"+pad(o.getHours())+":"+pad(o.getMinutes())+":"+pad(o.getSeconds())+'"'
};
this.encode=(function(){var ec;
return function(o){if(!ec){ec=isNative()?JSON.stringify:doEncode
}return ec(o)
}
}());
this.decode=(function(){var dc;
return function(json){if(!dc){dc=isNative()?JSON.parse:doDecode
}return dc(json)
}
}())
})();
OG.JSON=OG.common.JSON;OG.geometry.Style=function(c){var b={},a={};
OG.Util.apply(a,c,b);
OG.geometry.Style.superclass.call(this,a)
};
OG.geometry.Style.prototype=new OG.common.HashMap();
OG.geometry.Style.superclass=OG.common.HashMap;
OG.geometry.Style.prototype.constructor=OG.geometry.Style;
OG.Style=OG.geometry.Style;OG.geometry.Coordinate=function(a,b){this.x=undefined;
this.y=undefined;
if(arguments.length===1&&a.constructor===Array){this.x=a[0];
this.y=a[1]
}else{if(arguments.length===2&&typeof a==="number"&&typeof b==="number"){this.x=a;
this.y=b
}else{if(arguments.length!==0){throw new OG.ParamError()
}}}this.distance=function(e){if(e.constructor===Array){e=new OG.geometry.Coordinate(e[0],e[1])
}var d=this.x-e.x,c=this.y-e.y;
return OG.Util.round(Math.sqrt(Math.pow(d,2)+Math.pow(c,2)))
};
this.move=function(c,d){this.x+=c;
this.y+=d;
return this
};
this.rotate=function(f,d){if(d.constructor===Array){d=new OG.geometry.Coordinate(d[0],d[1])
}f*=Math.PI/180;
var c=this.distance(d),e=f+Math.atan2(this.y-d.y,this.x-d.x);
this.x=OG.Util.round(d.x+(c*Math.cos(e)));
this.y=OG.Util.round(d.y+(c*Math.sin(e)));
return this
};
this.isEquals=function(c){if(c.constructor===Array){c=new OG.geometry.Coordinate(c[0],c[1])
}if(c&&c instanceof OG.geometry.Coordinate){if(this.x===c.x&&this.y===c.y){return true
}}return false
};
this.toString=function(){var c=[];
c.push(this.x);
c.push(this.y);
return"["+c.join()+"]"
}
};
OG.geometry.Coordinate.prototype=new OG.geometry.Coordinate();
OG.geometry.Coordinate.prototype.constructor=OG.geometry.Coordinate;
OG.Coordinate=OG.geometry.Coordinate;OG.geometry.Envelope=function(b,c,n){var o,f=c,g=n,m,d,j,a,k,h,l,e,i;
if(b){if(b.constructor===Array){o=new OG.geometry.Coordinate(b)
}else{o=new OG.geometry.Coordinate(b.x,b.y)
}}this.getUpperLeft=function(){return o
};
this.setUpperLeft=function(p){if(p.constructor===Array){p=new OG.geometry.Coordinate(p[0],p[1])
}o=p;
i()
};
this.getUpperRight=function(){if(!m){m=new OG.geometry.Coordinate(o.x+f,o.y)
}return m
};
this.getLowerRight=function(){if(!j){j=new OG.geometry.Coordinate(o.x+f,o.y+g)
}return j
};
this.getLowerLeft=function(){if(!d){d=new OG.geometry.Coordinate(o.x,o.y+g)
}return d
};
this.getLeftCenter=function(){if(!a){a=new OG.geometry.Coordinate(o.x,OG.Util.round(o.y+g/2))
}return a
};
this.getUpperCenter=function(){if(!k){k=new OG.geometry.Coordinate(OG.Util.round(o.x+f/2),o.y)
}return k
};
this.getRightCenter=function(){if(!h){h=new OG.geometry.Coordinate(o.x+f,OG.Util.round(o.y+g/2))
}return h
};
this.getLowerCenter=function(){if(!l){l=new OG.geometry.Coordinate(OG.Util.round(o.x+f/2),o.y+g)
}return l
};
this.getCentroid=function(){if(!e){e=new OG.geometry.Coordinate(OG.Util.round(o.x+f/2),OG.Util.round(o.y+g/2))
}return e
};
this.setCentroid=function(p){if(p.constructor===Array){p=new OG.geometry.Coordinate(p[0],p[1])
}this.move(p.x-this.getCentroid().x,p.y-this.getCentroid().y)
};
this.getWidth=function(){return f
};
this.setWidth=function(p){f=p;
i()
};
this.getHeight=function(){return g
};
this.setHeight=function(p){g=p;
i()
};
this.getVertices=function(){var p=[];
p.push(this.getUpperLeft());
p.push(this.getUpperCenter());
p.push(this.getUpperRight());
p.push(this.getRightCenter());
p.push(this.getLowerRight());
p.push(this.getLowerCenter());
p.push(this.getLowerLeft());
p.push(this.getLeftCenter());
p.push(this.getUpperLeft());
return p
};
this.isContains=function(p){if(p.constructor===Array){return p[0]>=o.x&&p[0]<=this.getLowerRight().x&&p[1]>=o.y&&p[1]<=this.getLowerRight().y
}else{return p.x>=o.x&&p.x<=this.getLowerRight().x&&p.y>=o.y&&p.y<=this.getLowerRight().y
}};
this.isContainsAll=function(q){var p;
for(p=0;
p<q.length;
p++){if(!this.isContains(q[p])){return false
}}return true
};
this.move=function(p,q){o.move(p,q);
i();
return this
};
this.resize=function(r,p,s,q){r=r||0;
p=p||0;
s=s||0;
q=q||0;
if(f+(s+q)<0||g+(r+p)<0){throw new OG.ParamError()
}o.move(-1*s,-1*r);
f+=(s+q);
g+=(r+p);
i();
return this
};
this.isEquals=function(p){if(p&&p instanceof OG.geometry.Envelope){if(this.getUpperLeft().isEquals(p.getUpperLeft())&&this.getWidth()===p.getWidth()&&this.getHeight()===p.getHeight()){return true
}}return false
};
this.toString=function(){var p=[];
p.push("upperLeft:"+this.getUpperLeft());
p.push("width:"+this.getWidth());
p.push("height:"+this.getHeight());
p.push("upperRight:"+this.getUpperRight());
p.push("lowerLeft:"+this.getLowerLeft());
p.push("lowerRight:"+this.getLowerRight());
p.push("leftCenter:"+this.getLeftCenter());
p.push("upperCenter:"+this.getUpperCenter());
p.push("rightCenter:"+this.getRightCenter());
p.push("lowerCenter:"+this.getLowerCenter());
p.push("centroid:"+this.getCentroid());
return"{"+p.join()+"}"
};
i=function(){m=null;
d=null;
j=null;
a=null;
k=null;
h=null;
l=null;
e=null
}
};
OG.geometry.Envelope.prototype=new OG.geometry.Envelope();
OG.geometry.Envelope.prototype.constructor=OG.geometry.Envelope;
OG.Envelope=OG.geometry.Envelope;OG.geometry.Geometry=function(){this.TYPE=OG.Constants.GEOM_TYPE.NULL;
this.IS_CLOSED=false;
this.style=new OG.geometry.Style();
this.boundary=null;
this.isEquals=function(a){return a&&a.toString()===this.toString()
};
this.isContains=function(a){throw new OG.NotImplementedException()
};
this.isWithin=function(a){throw new OG.NotImplementedException()
};
this.getBoundary=function(){if(this.boundary===null){var f,e,c,a,b,d,j,h=this.getVertices(),g;
for(g=0;
g<h.length;
g++){if(g===0){f=c=h[g].x;
e=a=h[g].y
}f=h[g].x<f?h[g].x:f;
e=h[g].y<e?h[g].y:e;
c=h[g].x>c?h[g].x:c;
a=h[g].y>a?h[g].y:a
}b=new OG.geometry.Coordinate(f,e);
d=c-f;
j=a-e;
this.boundary=new OG.geometry.Envelope(b,d,j)
}return this.boundary
};
this.getCentroid=function(){return this.getBoundary().getCentroid()
};
this.getVertices=function(){throw new OG.NotImplementedException()
};
this.minDistance=function(c){var d=Number.MAX_VALUE,e=0,a=this.getVertices(),b;
c=this.convertCoordinate(c);
if(a.length===1){return c.distance(a[0])
}for(b=0;
b<a.length-1;
b++){e=this.distanceToLine(c,[a[b],a[b+1]]);
if(e<d){d=e
}}return d
};
this.distance=function(a){return this.getCentroid().distance(a.getCentroid())
};
this.getLength=function(){var c=0,a=this.getVertices(),b;
for(b=0;
b<a.length-1;
b++){c+=a[b].distance(a[b+1])
}return c
};
this.move=function(a,b){throw new OG.NotImplementedException()
};
this.moveCentroid=function(b){var a=this.getCentroid();
b=new OG.geometry.Coordinate(b);
this.move(b.x-a.x,b.y-a.y)
};
this.resize=function(c,a,d,b){throw new OG.NotImplementedException()
};
this.resizeBox=function(d,a){var e=this.getBoundary(),c=OG.Util.round((d-e.getWidth())/2),b=OG.Util.round((a-e.getHeight())/2);
this.resize(b,b,c,c);
return this
};
this.rotate=function(b,a){throw new OG.NotImplementedException()
};
this.fitToBoundary=function(e){var f=this.getBoundary(),c=f.getUpperCenter().y-e.getUpperCenter().y,a=e.getLowerCenter().y-f.getLowerCenter().y,d=f.getLeftCenter().x-e.getLeftCenter().x,b=e.getRightCenter().x-f.getRightCenter().x;
this.resize(c,a,d,b);
return this
};
this.convertCoordinate=function(a){if(a){if(a.constructor===Array){return new OG.geometry.Coordinate(a)
}else{if(a instanceof OG.geometry.Coordinate){return new OG.geometry.Coordinate(a.x,a.y)
}else{throw new OG.ParamError()
}}}else{return undefined
}};
this.distanceToLine=function(e,b){var a=this.convertCoordinate(b[0]),f=this.convertCoordinate(b[1]),d,c;
e=this.convertCoordinate(e);
if(a.isEquals(f)){return e.distance(a)
}d=((e.x-a.x)*(f.x-a.x)+(e.y-a.y)*(f.y-a.y))/((f.x-a.x)*(f.x-a.x)+(f.y-a.y)*(f.y-a.y));
if(d<=0){return e.distance(a)
}if(d>=1){return e.distance(f)
}c=((a.y-e.y)*(f.x-a.x)-(a.x-e.x)*(f.y-a.y))/((f.x-a.x)*(f.x-a.x)+(f.y-a.y)*(f.y-a.y));
return OG.Util.round(Math.abs(c)*Math.sqrt(((f.x-a.x)*(f.x-a.x)+(f.y-a.y)*(f.y-a.y))))
};
this.distanceLineToLine=function(i,g){var f=this.convertCoordinate(i[0]),e=this.convertCoordinate(i[1]),d=this.convertCoordinate(g[0]),b=this.convertCoordinate(g[1]),c,k,j,h,l,a;
if(f.isEquals(e)){return this.distanceToLine(f,[d,b])
}if(d.isEquals(b)){return this.distanceToLine(b,[f,e])
}c=(f.y-d.y)*(b.x-d.x)-(f.x-d.x)*(b.y-d.y);
k=(e.x-f.x)*(b.y-d.y)-(e.y-f.y)*(b.x-d.x);
j=(f.y-d.y)*(e.x-f.x)-(f.x-d.x)*(e.y-f.y);
h=(e.x-f.x)*(b.y-d.y)-(e.y-f.y)*(b.x-d.x);
if((k===0)||(h===0)){return Math.min(this.distanceToLine(f,[d,b]),Math.min(this.distanceToLine(e,[d,b]),Math.min(this.distanceToLine(d,[f,e]),this.distanceToLine(b,[f,e]))))
}l=j/h;
a=c/k;
if((a<0)||(a>1)||(l<0)||(l>1)){return Math.min(this.distanceToLine(f,[d,b]),Math.min(this.distanceToLine(e,[d,b]),Math.min(this.distanceToLine(d,[f,e]),this.distanceToLine(b,[f,e]))))
}return 0
};
this.intersectToLine=function(c){var d=this.getVertices(),b=[],a,f,e=function(h,i){var g;
for(g=0;
g<h.length;
g++){if(h[g].isEquals(i)){return true
}}return false
};
for(f=0;
f<d.length-1;
f++){a=this.intersectLineToLine(c,[d[f],d[f+1]]);
if(a&&!e(b,a)){b.push(a)
}}return b
};
this.intersectLineToLine=function(j,h){var f=this.convertCoordinate(j[0]),e=this.convertCoordinate(j[1]),d=this.convertCoordinate(h[0]),c=this.convertCoordinate(h[1]),n,g,b,l,k,i,a,m;
if(f.isEquals(e)){return this.distanceToLine(f,[d,c])===0?f:undefined
}if(d.isEquals(c)){return this.distanceToLine(d,[f,e])===0?d:undefined
}b=(f.y-d.y)*(c.x-d.x)-(f.x-d.x)*(c.y-d.y);
l=(e.x-f.x)*(c.y-d.y)-(e.y-f.y)*(c.x-d.x);
k=(f.y-d.y)*(e.x-f.x)-(f.x-d.x)*(e.y-f.y);
i=(e.x-f.x)*(c.y-d.y)-(e.y-f.y)*(c.x-d.x);
if(l!==0&&i!==0){a=b/l;
m=k/i;
if(0<=a&&a<=1&&0<=m&&m<=1){g="Intersection";
n=new OG.Coordinate(OG.Util.round(f.x+a*(e.x-f.x)),OG.Util.round(f.y+a*(e.y-f.y)))
}else{g="No Intersection"
}}else{if(b===0||k===0){g="Coincident"
}else{g="Parallel"
}}return n
};
this.intersectCircleToLine=function(c,j,o,p){var q=[],n=(p.x-o.x)*(p.x-o.x)+(p.y-o.y)*(p.y-o.y),m=2*((p.x-o.x)*(o.x-c.x)+(p.y-o.y)*(o.y-c.y)),i=c.x*c.x+c.y*c.y+o.x*o.x+o.y*o.y-2*(c.x*o.x+c.y*o.y)-j*j,d=m*m-4*n*i,l,h=function(e,b,a){return new OG.Coordinate(OG.Util.round(e.x+(b.x-e.x)*a),OG.Util.round(e.y+(b.y-e.y)*a))
},k,g,f;
if(d<0){l="Outside"
}else{if(d===0){l="Tangent"
}else{k=Math.sqrt(d);
g=(-m+k)/(2*n);
f=(-m-k)/(2*n);
if((g<0||g>1)&&(f<0||f>1)){if((g<0&&f<0)||(g>1&&f>1)){l="Outside"
}else{l="Inside"
}}else{l="Intersection";
if(0<=g&&g<=1){q.push(h(o,p,g))
}if(0<=f&&f<=1){q.push(h(o,p,f))
}}}}return q
};
this.reset=function(){this.boundary=null
}
};
OG.geometry.Geometry.prototype=new OG.geometry.Geometry();
OG.geometry.Geometry.prototype.constructor=OG.geometry.Geometry;OG.geometry.GeometryCollection=function(c){var b,a;
this.TYPE=OG.Constants.GEOM_TYPE.COLLECTION;
this.geometries=c;
this.IS_CLOSED=false;
this.style=new OG.geometry.Style();
this.getVertices=function(){var d=[],e;
for(b=0;
b<this.geometries.length;
b++){e=this.geometries[b].getVertices();
for(a=0;
a<e.length;
a++){d.push(e[a])
}}return d
};
this.move=function(d,f){var e;
this.getBoundary().move(d,f);
for(e=0;
e<this.geometries.length;
e++){this.geometries[e].move(d,f);
this.geometries[e].reset()
}return this
};
this.resize=function(n,i,g,o){var f=this.getBoundary(),j=g+o,h=n+i,e=f.getWidth()+j,p=f.getHeight()+h,m=f.getWidth()===0?1:e/f.getWidth(),l=f.getHeight()===0?1:p/f.getHeight(),d=f.getUpperLeft(),k;
if(e<0||p<0){throw new OG.ParamError()
}for(b=0;
b<this.geometries.length;
b++){k=this.geometries[b].vertices;
for(a=0;
a<k.length;
a++){k[a].x=OG.Util.round((d.x-g)+(k[a].x-d.x)*m);
k[a].y=OG.Util.round((d.y-n)+(k[a].y-d.y)*l)
}this.geometries[b].reset()
}f.resize(n,i,g,o);
return this
};
this.resizeBox=function(g,d){var h=this.getBoundary(),f=OG.Util.round((g-h.getWidth())/2),e=OG.Util.round((d-h.getHeight())/2);
this.resize(e,e,f,f);
return this
};
this.rotate=function(e,d){d=d||this.getCentroid();
for(b=0;
b<this.geometries.length;
b++){this.geometries[b].rotate(e,d);
this.geometries[b].reset()
}this.reset();
return this
};
this.fitToBoundary=function(h){var i=this.getBoundary(),f=i.getUpperCenter().y-h.getUpperCenter().y,d=h.getLowerCenter().y-i.getLowerCenter().y,g=i.getLeftCenter().x-h.getLeftCenter().x,e=h.getRightCenter().x-i.getRightCenter().x;
this.resize(f,d,g,e);
return this
};
this.toString=function(){var d=[];
for(b=0;
b<this.geometries.length;
b++){d.push(this.geometries[b].toString())
}return"{type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"',geometries:["+d.join()+"]}"
}
};
OG.geometry.GeometryCollection.prototype=new OG.geometry.Geometry();
OG.geometry.GeometryCollection.prototype.constructor=OG.geometry.GeometryCollection;
OG.GeometryCollection=OG.geometry.GeometryCollection;OG.geometry.PolyLine=function(a){var b;
this.TYPE=OG.Constants.GEOM_TYPE.POLYLINE;
this.IS_CLOSED=false;
this.style=new OG.geometry.Style();
this.vertices=[];
if(a&&a.length>0){for(b=0;
b<a.length;
b++){this.vertices.push(this.convertCoordinate(a[b]))
}}this.getVertices=function(){return this.vertices
};
this.move=function(c,d){this.getBoundary().move(c,d);
for(b=0;
b<this.vertices.length;
b++){this.vertices[b].move(c,d)
}return this
};
this.resize=function(l,h,f,m){var e=this.getBoundary(),i=f+m,g=l+h,d=e.getWidth()+i,n=e.getHeight()+g,k=e.getWidth()===0?1:d/e.getWidth(),j=e.getHeight()===0?1:n/e.getHeight(),c=e.getUpperLeft();
if(d<0||n<0){throw new OG.ParamError()
}for(b=0;
b<this.vertices.length;
b++){this.vertices[b].x=OG.Util.round((c.x-f)+(this.vertices[b].x-c.x)*k);
this.vertices[b].y=OG.Util.round((c.y-l)+(this.vertices[b].y-c.y)*j)
}e.resize(l,h,f,m);
return this
};
this.rotate=function(d,c){c=c||this.getCentroid();
for(b=0;
b<this.vertices.length;
b++){this.vertices[b].rotate(d,c)
}this.reset();
return this
};
this.toString=function(){var c=[];
c.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
c.push("vertices:["+this.vertices+"]");
return"{"+c.join()+"}"
}
};
OG.geometry.PolyLine.prototype=new OG.geometry.Geometry();
OG.geometry.PolyLine.prototype.constructor=OG.geometry.PolyLine;
OG.PolyLine=OG.geometry.PolyLine;OG.geometry.Curve=function(controlPoints){OG.geometry.Curve.superclass.call(this,controlPoints);
var t,cmRomSpline=OG.CurveUtil.CatmullRomSpline(eval("["+this.vertices.toString()+"]"));
this.vertices=[];
for(t=0;
t<=cmRomSpline.maxT;
t+=0.1){this.vertices.push(new OG.geometry.Coordinate(OG.Util.round(cmRomSpline.getX(t)),OG.Util.round(cmRomSpline.getY(t))))
}this.TYPE=OG.Constants.GEOM_TYPE.CURVE;
this.IS_CLOSED=false;
this.style=new OG.geometry.Style();
this.getVertices=function(){var vertices=[],i;
for(i=10;
i<=this.vertices.length-10;
i++){vertices.push(this.vertices[i])
}return vertices
};
this.getControlPoints=function(){var controlPoints=[],i;
for(i=10;
i<=this.vertices.length-10;
i+=10){controlPoints.push(this.vertices[i])
}return controlPoints
};
this.toString=function(){var s=[];
s.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
s.push("vertices:["+this.getVertices()+"]");
s.push("controlPoints:["+this.getControlPoints()+"]");
return"{"+s.join()+"}"
}
};
OG.geometry.Curve.prototype=new OG.geometry.PolyLine();
OG.geometry.Curve.superclass=OG.geometry.PolyLine;
OG.geometry.Curve.prototype.constructor=OG.geometry.Curve;
OG.Curve=OG.geometry.Curve;OG.geometry.Ellipse=function(a,c,b,e){var h=this.convertCoordinate(a),g=e||0,d,f,j=[];
if(h){for(f=-45;
f<=405;
f+=45){d=Math.PI/180*f;
j.push((new OG.geometry.Coordinate(OG.Util.round(h.x+c*Math.cos(d)),OG.Util.round(h.y+b*Math.sin(d)))).rotate(g,h))
}}OG.geometry.Ellipse.superclass.call(this,j);
this.TYPE=OG.Constants.GEOM_TYPE.ELLIPSE;
this.IS_CLOSED=true;
this.style=new OG.geometry.Style();
this.getVertices=function(){var i=[];
for(f=20;
f<this.vertices.length-20;
f++){i.push(this.vertices[f])
}return i
};
this.getControlPoints=function(){var i=[];
for(f=10;
f<=this.vertices.length-10;
f+=10){i.push(this.vertices[f])
}return i
};
this.getLength=function(){var i=this.getControlPoints(),l=a.distance(i[1]),k=a.distance(i[3]);
return Math.PI*(5*(l+k)/4-l*k/(l+k))
};
this.toString=function(){var l=[],k=this.getControlPoints(),i=this.getCentroid(),o=i.distance(k[1]),m=i.distance(k[3]),n=OG.Util.round(Math.atan2(k[1].y-i.y,k[1].x-i.x)*180/Math.PI);
l.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
l.push("center:"+i);
l.push("radiusX:"+o);
l.push("radiusY:"+m);
l.push("angle:"+n);
return"{"+l.join()+"}"
}
};
OG.geometry.Ellipse.prototype=new OG.geometry.Curve();
OG.geometry.Ellipse.superclass=OG.geometry.Curve;
OG.geometry.Ellipse.prototype.constructor=OG.geometry.Ellipse;
OG.Ellipse=OG.geometry.Ellipse;OG.geometry.Circle=function(b,a){OG.geometry.Circle.superclass.call(this,b,a,a,0);
this.TYPE=OG.Constants.GEOM_TYPE.CIRCLE;
this.IS_CLOSED=true;
this.style=new OG.geometry.Style();
this.getLength=function(){var c=this.getControlPoints(),d=b.distance(c[1]);
return 2*Math.PI*d
};
this.toString=function(){var e=[],d=this.getControlPoints(),c=this.getCentroid(),h=c.distance(d[1]),f=c.distance(d[3]),g=OG.Util.round(Math.atan2(d[1].y-c.y,d[1].x-c.x)*180/Math.PI);
if(h===f){e.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
e.push("center:"+c);
e.push("radius:"+h)
}else{e.push("type:'"+OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE]+"'");
e.push("center:"+c);
e.push("radiusX:"+h);
e.push("radiusY:"+f);
e.push("angle:"+g)
}return"{"+e.join()+"}"
}
};
OG.geometry.Circle.prototype=new OG.geometry.Ellipse();
OG.geometry.Circle.superclass=OG.geometry.Ellipse;
OG.geometry.Circle.prototype.constructor=OG.geometry.Circle;
OG.Circle=OG.geometry.Circle;OG.geometry.Line=function(d,c){var b=this.convertCoordinate(d),a=this.convertCoordinate(c);
OG.geometry.Line.superclass.call(this,[[b.x,b.y],[a.x,a.y]]);
this.TYPE=OG.Constants.GEOM_TYPE.LINE;
this.IS_CLOSED=false;
this.style=new OG.geometry.Style();
this.toString=function(){var e=[];
e.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
e.push("from:"+this.vertices[0]);
e.push("to:"+this.vertices[1]);
return"{"+e.join()+"}"
}
};
OG.geometry.Line.prototype=new OG.geometry.PolyLine();
OG.geometry.Line.superclass=OG.geometry.PolyLine;
OG.geometry.Line.prototype.constructor=OG.geometry.Line;
OG.Line=OG.geometry.Line;OG.geometry.Point=function(a){this.TYPE=OG.Constants.GEOM_TYPE.POINT;
this.IS_CLOSED=false;
this.style=new OG.geometry.Style();
this.coordinate=this.convertCoordinate(a);
this.vertices=[this.coordinate];
this.getVertices=function(){return this.vertices
};
this.move=function(b,c){this.getBoundary().move(b,c);
this.coordinate.move(b,c);
this.vertices=[this.coordinate];
return this
};
this.moveCentroid=function(b){this.getBoundary().setUpperLeft(b);
this.coordinate=new OG.geometry.Coordinate(b);
this.vertices=[this.coordinate]
};
this.resize=function(d,b,e,c){var f=this.getBoundary();
f.resize(d,b,e,c);
this.coordinate=f.getCentroid();
this.vertices=[this.coordinate];
this.boundary=new OG.Envelope(this.coordinate,0,0);
return this
};
this.resizeBox=function(c,b){return this
};
this.rotate=function(c,b){b=b||this.getCentroid();
this.coordinate.rotate(c,b);
this.vertices=[this.coordinate];
this.reset();
return this
};
this.fitToBoundary=function(b){this.coordinate=b.getCentroid();
this.vertices=[this.coordinate];
this.boundary=new OG.Envelope(this.coordinate,0,0);
return this
};
this.toString=function(){var b=[];
b.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
b.push("coordinate:"+this.coordinate);
return"{"+b.join()+"}"
}
};
OG.geometry.Point.prototype=new OG.geometry.Geometry();
OG.geometry.Point.prototype.constructor=OG.geometry.Point;
OG.Point=OG.geometry.Point;OG.geometry.Polygon=function(a){OG.geometry.Polygon.superclass.call(this,a);
if(this.vertices.length>0&&!this.vertices[0].isEquals(this.vertices[this.vertices.length-1])){this.vertices.push(new OG.geometry.Coordinate(this.vertices[0].x,this.vertices[0].y))
}this.TYPE=OG.Constants.GEOM_TYPE.POLYGON;
this.IS_CLOSED=true;
this.style=new OG.geometry.Style()
};
OG.geometry.Polygon.prototype=new OG.geometry.PolyLine();
OG.geometry.Polygon.superclass=OG.geometry.PolyLine;
OG.geometry.Polygon.prototype.constructor=OG.geometry.Polygon;
OG.Polygon=OG.geometry.Polygon;OG.geometry.Rectangle=function(c,d,a){var b=this.convertCoordinate(c),e=this.convertCoordinate([b.x+d,b.y+a]);
if(b.x>e.x||b.y>e.y){throw new OG.ParamError()
}OG.geometry.Rectangle.superclass.call(this,[[b.x,b.y],[b.x+(e.x-b.x),b.y],[e.x,e.y],[b.x,b.y+(e.y-b.y)],[b.x,b.y]]);
this.TYPE=OG.Constants.GEOM_TYPE.RECTANGLE;
this.IS_CLOSED=true;
this.style=new OG.geometry.Style();
this.toString=function(){var f=[],g=OG.Util.round(Math.atan2(this.vertices[1].y-this.vertices[0].y,this.vertices[1].x-this.vertices[0].x)*180/Math.PI);
f.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
f.push("upperLeft:"+this.vertices[0]);
f.push("width:"+(this.vertices[0].distance(this.vertices[1])));
f.push("height:"+(this.vertices[0].distance(this.vertices[3])));
f.push("angle:"+g);
return"{"+f.join()+"}"
}
};
OG.geometry.Rectangle.prototype=new OG.geometry.Polygon();
OG.geometry.Rectangle.superclass=OG.geometry.Polygon;
OG.geometry.Rectangle.prototype.constructor=OG.geometry.Rectangle;
OG.Rectangle=OG.geometry.Rectangle;OG.shape.IShape=function(){this.TYPE=OG.Constants.NODE_TYPE.SHAPE;
this.SHAPE_ID="OG.shape.IShape";
this.geom=null;
this.label=null;
this.isCollapsed=false;
this.createTerminal=function(){return[]
};
this.createShape=function(){throw new OG.NotImplementedException("OG.shape.IShape.createShape")
};
this.clone=function(){throw new OG.NotImplementedException("OG.shape.IShape.clone")
}
};
OG.shape.IShape.prototype=new OG.shape.IShape();
OG.shape.IShape.prototype.constructor=OG.shape.IShape;
OG.IShape=OG.shape.IShape;OG.shape.Terminal=function(a,c,b){this.position=a;
this.direction=c||OG.Constants.TERMINAL_TYPE.E;
this.inout=b||OG.Constants.TERMINAL_TYPE.INOUT
};
OG.shape.Terminal.prototype=new OG.shape.Terminal();
OG.shape.Terminal.prototype.constructor=OG.shape.Terminal;
OG.Terminal=OG.shape.Terminal;OG.shape.GeomShape=function(){this.TYPE=OG.Constants.SHAPE_TYPE.GEOM;
this.SHAPE_ID="OG.shape.GeomShape";
this.createTerminal=function(){if(!this.geom){return[]
}var a=this.geom.getBoundary();
return[new OG.Terminal(a.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(a.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(a.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(a.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(a.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){throw new OG.NotImplementedException("OG.shape.IShape.createShape")
};
this.clone=function(){throw new OG.NotImplementedException("OG.shape.IShape.clone")
}
};
OG.shape.GeomShape.prototype=new OG.shape.IShape();
OG.shape.GeomShape.prototype.constructor=OG.shape.GeomShape;
OG.GeomShape=OG.shape.GeomShape;OG.shape.TextShape=function(a){this.TYPE=OG.Constants.SHAPE_TYPE.TEXT;
this.SHAPE_ID="OG.shape.TextShape";
this.text=a||"Text Here";
this.angle=0;
this.createShape=function(){return this.text
};
this.clone=function(){return new OG.shape.TextShape(this.text)
}
};
OG.shape.TextShape.prototype=new OG.shape.IShape();
OG.shape.TextShape.prototype.constructor=OG.shape.TextShape;
OG.TextShape=OG.shape.TextShape;OG.shape.ImageShape=function(b,a){this.TYPE=OG.Constants.SHAPE_TYPE.IMAGE;
this.SHAPE_ID="OG.shape.ImageShape";
this.image=b;
this.angle=0;
this.label=a;
this.createTerminal=function(){if(!this.geom){return[]
}var c=this.geom.getBoundary();
return[new OG.Terminal(c.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){return this.image
};
this.clone=function(){return new OG.shape.ImageShape(this.image,this.label)
}
};
OG.shape.ImageShape.prototype=new OG.shape.IShape();
OG.shape.ImageShape.prototype.constructor=OG.shape.ImageShape;
OG.ImageShape=OG.shape.ImageShape;OG.shape.EdgeShape=function(e,d,a,b,c){this.TYPE=OG.Constants.SHAPE_TYPE.EDGE;
this.SHAPE_ID="OG.shape.EdgeShape";
this.from=e;
this.to=d;
this.label=a;
this.fromLabel=b;
this.toLabel=c;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(e,d);
return this.geom
};
this.clone=function(){return new OG.shape.EdgeShape(this.from,this.to,this.label,this.fromLabel,this.toLabel)
}
};
OG.shape.EdgeShape.prototype=new OG.shape.IShape();
OG.shape.EdgeShape.prototype.constructor=OG.shape.EdgeShape;
OG.EdgeShape=OG.shape.EdgeShape;OG.shape.CircleShape=function(a){this.SHAPE_ID="OG.shape.CircleShape";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Circle([50,50],50);
return this.geom
};
this.clone=function(){return new OG.shape.CircleShape(this.label)
}
};
OG.shape.CircleShape.prototype=new OG.shape.GeomShape();
OG.shape.CircleShape.prototype.constructor=OG.shape.CircleShape;
OG.CircleShape=OG.shape.CircleShape;OG.shape.EllipseShape=function(a){this.SHAPE_ID="OG.shape.EllipseShape";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Ellipse([50,50],50,30);
return this.geom
};
this.clone=function(){return new OG.shape.EllipseShape(this.label)
}
};
OG.shape.EllipseShape.prototype=new OG.shape.GeomShape();
OG.shape.EllipseShape.prototype.constructor=OG.shape.EllipseShape;
OG.EllipseShape=OG.shape.EllipseShape;OG.shape.GroupShape=function(a){this.TYPE=OG.Constants.SHAPE_TYPE.GROUP;
this.SHAPE_ID="OG.shape.GroupShape";
this.label=a;
this.createTerminal=function(){if(!this.geom){return[]
}var b=this.geom.getBoundary();
return[new OG.Terminal(b.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({stroke:"none"});
return this.geom
};
this.clone=function(){return new OG.shape.GroupShape(this.label)
}
};
OG.shape.GroupShape.prototype=new OG.shape.IShape();
OG.shape.GroupShape.prototype.constructor=OG.shape.GroupShape;
OG.GroupShape=OG.shape.GroupShape;OG.shape.HorizontalLaneShape=function(a){this.TYPE=OG.Constants.SHAPE_TYPE.GROUP;
this.SHAPE_ID="OG.shape.HorizontalLaneShape";
this.label=a;
this.createTerminal=function(){if(!this.geom){return[]
}var b=this.geom.getBoundary();
return[new OG.Terminal(b.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({"label-direction":"vertical","vertical-align":"top"});
return this.geom
};
this.clone=function(){return new OG.shape.HorizontalLaneShape(this.label)
}
};
OG.shape.HorizontalLaneShape.prototype=new OG.shape.GroupShape();
OG.shape.HorizontalLaneShape.prototype.constructor=OG.shape.HorizontalLaneShape;
OG.HorizontalLaneShape=OG.shape.HorizontalLaneShape;OG.shape.HtmlShape=function(b,a){this.TYPE=OG.Constants.SHAPE_TYPE.HTML;
this.SHAPE_ID="OG.shape.HtmlShape";
this.html=b||"";
this.label=a;
this.angle=0;
this.createTerminal=function(){if(!this.geom){return[]
}var c=this.geom.getBoundary();
return[new OG.Terminal(c.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){return this.html
};
this.clone=function(){return new OG.shape.HtmlShape(this.html,this.label)
}
};
OG.shape.HtmlShape.prototype=new OG.shape.IShape();
OG.shape.HtmlShape.prototype.constructor=OG.shape.HtmlShape;
OG.HtmlShape=OG.shape.HtmlShape;OG.shape.RectangleShape=function(a){this.SHAPE_ID="OG.shape.RectangleShape";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
return this.geom
};
this.clone=function(){return new OG.shape.RectangleShape(this.label)
}
};
OG.shape.RectangleShape.prototype=new OG.shape.GeomShape();
OG.shape.RectangleShape.prototype.constructor=OG.shape.RectangleShape;
OG.RectangleShape=OG.shape.RectangleShape;OG.shape.VerticalLaneShape=function(a){this.TYPE=OG.Constants.SHAPE_TYPE.GROUP;
this.SHAPE_ID="OG.shape.VerticalLaneShape";
this.label=a;
this.createTerminal=function(){if(!this.geom){return[]
}var b=this.geom.getBoundary();
return[new OG.Terminal(b.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({"label-direction":"horizontal","vertical-align":"top"});
return this.geom
};
this.clone=function(){return new OG.shape.VerticalLaneShape(this.label)
}
};
OG.shape.VerticalLaneShape.prototype=new OG.shape.GroupShape();
OG.shape.VerticalLaneShape.prototype.constructor=OG.shape.VerticalLaneShape;
OG.VerticalLaneShape=OG.shape.VerticalLaneShape;OG.shape.bpmn.A_Subprocess=function(a){this.SHAPE_ID="OG.shape.bpmn.A_Subprocess";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({stroke:"black",r:6});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.A_Subprocess(this.label)
}
};
OG.shape.bpmn.A_Subprocess.prototype=new OG.shape.GroupShape();
OG.shape.bpmn.A_Subprocess.prototype.constructor=OG.shape.bpmn.A_Subprocess;
OG.A_Subprocess=OG.shape.bpmn.A_Subprocess;OG.shape.bpmn.A_Task=function(a){this.SHAPE_ID="OG.shape.bpmn.A_Task";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({r:6});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.A_Task(this.label)
}
};
OG.shape.bpmn.A_Task.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.A_Task.prototype.constructor=OG.shape.bpmn.A_Task;
OG.A_Task=OG.shape.bpmn.A_Task;OG.shape.bpmn.C_Association=function(c,b,a){this.SHAPE_ID="OG.shape.bpmn.C_Association";
this.from=c;
this.to=b;
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(this.from||[0,0],this.to||[70,0]);
this.geom.style=new OG.geometry.Style({"edge-type":"straight","arrow-start":"none","arrow-end":"none","stroke-dasharray":"."});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.C_Association(this.from,this.to,this.label)
}
};
OG.shape.bpmn.C_Association.prototype=new OG.shape.EdgeShape();
OG.shape.bpmn.C_Association.prototype.constructor=OG.shape.bpmn.C_Association;
OG.C_Association=OG.shape.bpmn.C_Association;OG.shape.bpmn.C_Conditional=function(c,b,a){this.SHAPE_ID="OG.shape.bpmn.C_Conditional";
this.from=c;
this.to=b;
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(this.from||[0,0],this.to||[70,0]);
this.geom.style=new OG.geometry.Style({"edge-type":"straight","arrow-start":"open_diamond-wide-long","arrow-end":"open_block-wide-long"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.C_Conditional(this.from,this.to,this.label)
}
};
OG.shape.bpmn.C_Conditional.prototype=new OG.shape.EdgeShape();
OG.shape.bpmn.C_Conditional.prototype.constructor=OG.shape.bpmn.C_Conditional;
OG.C_Conditional=OG.shape.bpmn.C_Conditional;OG.shape.bpmn.C_DataAssociation=function(c,b,a){this.SHAPE_ID="OG.shape.bpmn.C_DataAssociation";
this.from=c;
this.to=b;
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(this.from||[0,0],this.to||[70,0]);
this.geom.style=new OG.geometry.Style({"edge-type":"straight","arrow-start":"none","arrow-end":"classic-wide-long","stroke-dasharray":"."});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.C_DataAssociation(this.from,this.to,this.label)
}
};
OG.shape.bpmn.C_DataAssociation.prototype=new OG.shape.EdgeShape();
OG.shape.bpmn.C_DataAssociation.prototype.constructor=OG.shape.bpmn.C_DataAssociation;
OG.C_DataAssociation=OG.shape.bpmn.C_DataAssociation;OG.shape.bpmn.C_Message=function(c,b,a){this.SHAPE_ID="OG.shape.bpmn.C_Message";
this.from=c;
this.to=b;
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(this.from||[0,0],this.to||[80,0]);
this.geom.style=new OG.geometry.Style({"edge-type":"straight","arrow-start":"open_oval-wide-long","arrow-end":"open_block-wide-long","stroke-dasharray":"."});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.C_Message(this.from,this.to,this.label)
}
};
OG.shape.bpmn.C_Message.prototype=new OG.shape.EdgeShape();
OG.shape.bpmn.C_Message.prototype.constructor=OG.shape.bpmn.C_Message;
OG.C_Message=OG.shape.bpmn.C_Message;OG.shape.bpmn.C_Sequence=function(c,b,a){this.SHAPE_ID="OG.shape.bpmn.C_Sequence";
this.from=c;
this.to=b;
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(this.from||[0,0],this.to||[80,0]);
this.geom.style=new OG.geometry.Style({"edge-type":"plain","arrow-start":"none","arrow-end":"classic-wide-long"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.C_Sequence(this.from,this.to,this.label)
}
};
OG.shape.bpmn.C_Sequence.prototype=new OG.shape.EdgeShape();
OG.shape.bpmn.C_Sequence.prototype.constructor=OG.shape.bpmn.C_Sequence;
OG.C_Sequence=OG.shape.bpmn.C_Sequence;OG.shape.bpmn.D_Data=function(a){this.SHAPE_ID="OG.shape.bpmn.D_Data";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.PolyLine([[0,0],[0,100],[100,100],[100,20],[80,0],[0,0],[80,0],[80,20],[100,20]]);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.D_Data(this.label)
}
};
OG.shape.bpmn.D_Data.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.D_Data.prototype.constructor=OG.shape.bpmn.D_Data;
OG.D_Data=OG.shape.bpmn.D_Data;OG.shape.bpmn.D_Store=function(a){this.SHAPE_ID="OG.shape.bpmn.D_Store";
this.label=a;
this.createShape=function(){var g,f,e,d,b,c=[];
if(this.geom){return this.geom
}g=new OG.geometry.Ellipse([50,10],50,10);
f=new OG.geometry.Line([0,10],[0,90]);
e=new OG.geometry.Line([100,10],[100,90]);
d=new OG.geometry.Curve([[100,90],[96,94],[85,97],[50,100],[15,97],[4,94],[0,90]]);
b=new OG.geometry.Rectangle([0,10],100,80);
b.style=new OG.geometry.Style({stroke:"none"});
c.push(g);
c.push(f);
c.push(e);
c.push(d);
c.push(b);
this.geom=new OG.geometry.GeometryCollection(c);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.D_Store(this.label)
}
};
OG.shape.bpmn.D_Store.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.D_Store.prototype.constructor=OG.shape.bpmn.D_Store;
OG.D_Store=OG.shape.bpmn.D_Store;OG.shape.bpmn.E_End=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Circle([50,50],50);
this.geom.style=new OG.geometry.Style({"stroke-width":3,"label-position":"bottom"});
return this.geom
};
this.createTerminal=function(){if(!this.geom){return[]
}var b=this.geom.getBoundary();
return[new OG.Terminal(b.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.IN),new OG.Terminal(b.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.IN),new OG.Terminal(b.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.IN),new OG.Terminal(b.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.IN),new OG.Terminal(b.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.IN)]
};
this.clone=function(){return new OG.shape.bpmn.E_End(this.label)
}
};
OG.shape.bpmn.E_End.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End.prototype.constructor=OG.shape.bpmn.E_End;
OG.E_End=OG.shape.bpmn.E_End;OG.shape.bpmn.E_End_Cancel=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End_Cancel";
this.label=a;
this.createShape=function(){var e,d,c,b=[];
if(this.geom){return this.geom
}e=new OG.geometry.Circle([50,50],50);
e.style=new OG.geometry.Style({"stroke-width":3});
d=new OG.geometry.Line([25,25],[75,75]);
d.style=new OG.geometry.Style({"stroke-width":5});
c=new OG.geometry.Line([25,75],[75,25]);
c.style=new OG.geometry.Style({"stroke-width":5});
b.push(e);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_End_Cancel(this.label)
}
};
OG.shape.bpmn.E_End_Cancel.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End_Cancel.prototype.constructor=OG.shape.bpmn.E_End_Cancel;
OG.E_End_Cancel=OG.shape.bpmn.E_End_Cancel;OG.shape.bpmn.E_End_Compensation=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End_Compensation";
this.label=a;
this.createShape=function(){var e,d,c,b=[];
if(this.geom){return this.geom
}e=new OG.geometry.Circle([50,50],50);
e.style=new OG.geometry.Style({"stroke-width":3});
d=new OG.geometry.Polygon([[15,50],[45,70],[45,30]]);
d.style=new OG.geometry.Style({fill:"black","fill-opacity":1});
c=new OG.geometry.Polygon([[45,50],[75,70],[75,30]]);
c.style=new OG.geometry.Style({fill:"black","fill-opacity":1});
b.push(e);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_End_Compensation(this.label)
}
};
OG.shape.bpmn.E_End_Compensation.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End_Compensation.prototype.constructor=OG.shape.bpmn.E_End_Compensation;
OG.E_End_Compensation=OG.shape.bpmn.E_End_Compensation;OG.shape.bpmn.E_End_Error=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End_Error";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":3});
c=new OG.geometry.PolyLine([[20,75],[40,40],[60,60],[80,20]]);
c.style=new OG.geometry.Style({"stroke-width":2});
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_End_Error(this.label)
}
};
OG.shape.bpmn.E_End_Error.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End_Error.prototype.constructor=OG.shape.bpmn.E_End_Error;
OG.E_End_Error=OG.shape.bpmn.E_End_Error;OG.shape.bpmn.E_End_Link=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End_Link";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":3});
c=new OG.geometry.Polygon([[20,40],[20,60],[60,60],[60,80],[85,50],[60,20],[60,40]]);
c.style=new OG.geometry.Style({fill:"black","fill-opacity":1});
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_End_Link(this.label)
}
};
OG.shape.bpmn.E_End_Link.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End_Link.prototype.constructor=OG.shape.bpmn.E_End_Link;
OG.E_End_Link=OG.shape.bpmn.E_End_Link;OG.shape.bpmn.E_End_Message=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End_Message";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":3});
c=new OG.geometry.PolyLine([[20,30],[20,70],[80,70],[80,30],[20,30],[50,50],[80,30]]);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_End_Message(this.label)
}
};
OG.shape.bpmn.E_End_Message.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End_Message.prototype.constructor=OG.shape.bpmn.E_End_Message;
OG.E_End_Message=OG.shape.bpmn.E_End_Message;OG.shape.bpmn.E_End_Multiple=function(a){this.SHAPE_ID="OG.shape.bpmn.E_End_Multiple";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":3});
c=new OG.geometry.Polygon([[50,15],[39,33],[20,33],[29,50],[19,67],[40,67],[50,85],[60,68],[80,68],[70,50],[79,33],[60,33]]);
c.style=new OG.geometry.Style({fill:"black","fill-opacity":1});
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_End_Multiple(this.label)
}
};
OG.shape.bpmn.E_End_Multiple.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_End_Multiple.prototype.constructor=OG.shape.bpmn.E_End_Multiple;
OG.E_End_Multiple=OG.shape.bpmn.E_End_Multiple;OG.shape.bpmn.E_Intermediate=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate";
this.label=a;
this.createShape=function(){var b=[];
if(this.geom){return this.geom
}b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate(this.label)
}
};
OG.shape.bpmn.E_Intermediate.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate.prototype.constructor=OG.shape.bpmn.E_Intermediate;
OG.E_Intermediate=OG.shape.bpmn.E_Intermediate;OG.shape.bpmn.E_Intermediate_Compensation=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Compensation";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Polygon([[15,50],[45,70],[45,30]]);
c=new OG.geometry.Polygon([[45,50],[75,70],[75,30]]);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Compensation(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Compensation.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Compensation.prototype.constructor=OG.shape.bpmn.E_Intermediate_Compensation;
OG.E_Intermediate_Compensation=OG.shape.bpmn.E_Intermediate_Compensation;OG.shape.bpmn.E_Intermediate_Error=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Error";
this.label=a;
this.createShape=function(){var c,b=[];
if(this.geom){return this.geom
}c=new OG.geometry.PolyLine([[20,75],[40,40],[60,60],[80,20]]);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Error(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Error.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Error.prototype.constructor=OG.shape.bpmn.E_Intermediate_Error;
OG.E_Intermediate_Error=OG.shape.bpmn.E_Intermediate_Error;OG.shape.bpmn.E_Intermediate_Link=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Link";
this.label=a;
this.createShape=function(){var c,b=[];
if(this.geom){return this.geom
}c=new OG.geometry.Polygon([[20,40],[20,60],[60,60],[60,80],[85,50],[60,20],[60,40]]);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Link(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Link.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Link.prototype.constructor=OG.shape.bpmn.E_Intermediate_Link;
OG.E_Intermediate_Link=OG.shape.bpmn.E_Intermediate_Link;OG.shape.bpmn.E_Intermediate_Message=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Message";
this.label=a;
this.createShape=function(){var c,b=[];
if(this.geom){return this.geom
}c=new OG.geometry.PolyLine([[20,30],[20,70],[80,70],[80,30],[20,30],[50,50],[80,30]]);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Message(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Message.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Message.prototype.constructor=OG.shape.bpmn.E_Intermediate_Message;
OG.E_Intermediate_Message=OG.shape.bpmn.E_Intermediate_Message;OG.shape.bpmn.E_Intermediate_Multiple=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Multiple";
this.label=a;
this.createShape=function(){var c,b=[];
if(this.geom){return this.geom
}c=new OG.geometry.Polygon([[50,15],[39,33],[20,33],[29,50],[19,67],[40,67],[50,85],[60,68],[80,68],[70,50],[79,33],[60,33]]);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Multiple(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Multiple.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Multiple.prototype.constructor=OG.shape.bpmn.E_Intermediate_Multiple;
OG.E_Intermediate_Multiple=OG.shape.bpmn.E_Intermediate_Multiple;OG.shape.bpmn.E_Intermediate_Rule=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Rule";
this.label=a;
this.createShape=function(){var c,b=[];
if(this.geom){return this.geom
}c=new OG.geometry.Rectangle([25,20],50,60);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(c);
b.push(new OG.geometry.Line([30,30],[70,30]));
b.push(new OG.geometry.Line([30,45],[70,45]));
b.push(new OG.geometry.Line([30,60],[70,60]));
b.push(new OG.geometry.Line([30,70],[70,70]));
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Rule(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Rule.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Rule.prototype.constructor=OG.shape.bpmn.E_Intermediate_Rule;
OG.E_Intermediate_Rule=OG.shape.bpmn.E_Intermediate_Rule;OG.shape.bpmn.E_Intermediate_Timer=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Intermediate_Timer";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],32);
c=new OG.geometry.PolyLine([[50,30],[50,50],[70,50]]);
b.push(new OG.geometry.Circle([50,50],50));
b.push(new OG.geometry.Circle([50,50],42));
b.push(d);
b.push(new OG.geometry.Line([50,18],[50,25]));
b.push(new OG.geometry.Line([50,82],[50,75]));
b.push(new OG.geometry.Line([18,50],[25,50]));
b.push(new OG.geometry.Line([82,50],[75,50]));
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Intermediate_Timer(this.label)
}
};
OG.shape.bpmn.E_Intermediate_Timer.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Intermediate_Timer.prototype.constructor=OG.shape.bpmn.E_Intermediate_Timer;
OG.E_Intermediate_Timer=OG.shape.bpmn.E_Intermediate_Timer;OG.shape.bpmn.E_Start=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Start";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Circle([50,50],50);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.createTerminal=function(){if(!this.geom){return[]
}var b=this.geom.getBoundary();
return[new OG.Terminal(b.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.OUT),new OG.Terminal(b.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.OUT),new OG.Terminal(b.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.OUT),new OG.Terminal(b.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.OUT),new OG.Terminal(b.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.OUT)]
};
this.clone=function(){return new OG.shape.bpmn.E_Start(this.label)
}
};
OG.shape.bpmn.E_Start.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Start.prototype.constructor=OG.shape.bpmn.E_Start;
OG.E_Start=OG.shape.bpmn.E_Start;OG.shape.bpmn.E_Start_Link=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Start_Link";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":1});
c=new OG.geometry.Polygon([[20,40],[20,60],[60,60],[60,80],[85,50],[60,20],[60,40]]);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Start_Link(this.label)
}
};
OG.shape.bpmn.E_Start_Link.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Start_Link.prototype.constructor=OG.shape.bpmn.E_Start_Link;
OG.E_Start_Link=OG.shape.bpmn.E_Start_Link;OG.shape.bpmn.E_Start_Message=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Start_Message";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
c=new OG.geometry.PolyLine([[20,30],[20,70],[80,70],[80,30],[20,30],[50,50],[80,30]]);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Start_Message(this.label)
}
};
OG.shape.bpmn.E_Start_Message.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Start_Message.prototype.constructor=OG.shape.bpmn.E_Start_Message;
OG.E_Start_Message=OG.shape.bpmn.E_Start_Message;OG.shape.bpmn.E_Start_Multiple=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Start_Multiple";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
c=new OG.geometry.Polygon([[50,15],[39,33],[20,33],[29,50],[19,67],[40,67],[50,85],[60,68],[80,68],[70,50],[79,33],[60,33]]);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Start_Multiple(this.label)
}
};
OG.shape.bpmn.E_Start_Multiple.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Start_Multiple.prototype.constructor=OG.shape.bpmn.E_Start_Multiple;
OG.E_Start_Multiple=OG.shape.bpmn.E_Start_Multiple;OG.shape.bpmn.E_Start_Rule=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Start_Rule";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":1});
c=new OG.geometry.Rectangle([25,20],50,60);
b.push(d);
b.push(c);
b.push(new OG.geometry.Line([30,30],[70,30]));
b.push(new OG.geometry.Line([30,45],[70,45]));
b.push(new OG.geometry.Line([30,60],[70,60]));
b.push(new OG.geometry.Line([30,70],[70,70]));
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Start_Rule(this.label)
}
};
OG.shape.bpmn.E_Start_Rule.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Start_Rule.prototype.constructor=OG.shape.bpmn.E_Start_Rule;
OG.E_Start_Rule=OG.shape.bpmn.E_Start_Rule;OG.shape.bpmn.E_Start_Timer=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Start_Timer";
this.label=a;
this.createShape=function(){var e,d,c,b=[];
if(this.geom){return this.geom
}e=new OG.geometry.Circle([50,50],50);
e.style=new OG.geometry.Style({"stroke-width":1});
d=new OG.geometry.Circle([50,50],32);
c=new OG.geometry.PolyLine([[50,30],[50,50],[70,50]]);
b.push(e);
b.push(d);
b.push(new OG.geometry.Line([50,18],[50,25]));
b.push(new OG.geometry.Line([50,82],[50,75]));
b.push(new OG.geometry.Line([18,50],[25,50]));
b.push(new OG.geometry.Line([82,50],[75,50]));
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Start_Timer(this.label)
}
};
OG.shape.bpmn.E_Start_Timer.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.E_Start_Timer.prototype.constructor=OG.shape.bpmn.E_Start_Timer;
OG.E_Start_Timer=OG.shape.bpmn.E_Start_Timer;OG.shape.bpmn.E_Terminate=function(a){this.SHAPE_ID="OG.shape.bpmn.E_Terminate";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Circle([50,50],50);
d.style=new OG.geometry.Style({"stroke-width":3});
c=new OG.geometry.Circle([50,50],30);
c.style=new OG.geometry.Style({fill:"black","fill-opacity":1});
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({"label-position":"bottom"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.E_Terminate(this.label)
}
};
OG.shape.bpmn.E_Terminate.prototype=new OG.shape.bpmn.E_End();
OG.shape.bpmn.E_Terminate.prototype.constructor=OG.shape.bpmn.E_Terminate;
OG.E_Terminate=OG.shape.bpmn.E_Terminate;OG.shape.bpmn.G_Complex=function(a){this.SHAPE_ID="OG.shape.bpmn.G_Complex";
this.label=a;
this.createShape=function(){var g,f,e,d,b,c=[];
if(this.geom){return this.geom
}g=new OG.geometry.Polygon([[0,50],[50,100],[100,50],[50,0]]);
f=new OG.geometry.Line([30,30],[70,70]);
f.style=new OG.geometry.Style({"stroke-width":3});
e=new OG.geometry.Line([30,70],[70,30]);
e.style=new OG.geometry.Style({"stroke-width":3});
d=new OG.geometry.Line([20,50],[80,50]);
d.style=new OG.geometry.Style({"stroke-width":3});
b=new OG.geometry.Line([50,20],[50,80]);
b.style=new OG.geometry.Style({"stroke-width":3});
c.push(g);
c.push(f);
c.push(e);
c.push(d);
c.push(b);
this.geom=new OG.geometry.GeometryCollection(c);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.G_Complex(this.label)
}
};
OG.shape.bpmn.G_Complex.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.G_Complex.prototype.constructor=OG.shape.bpmn.G_Complex;
OG.G_Complex=OG.shape.bpmn.G_Complex;OG.shape.bpmn.G_Exclusive=function(a){this.SHAPE_ID="OG.shape.bpmn.G_Exclusive";
this.label=a;
this.createShape=function(){var e,d,c,b=[];
if(this.geom){return this.geom
}e=new OG.geometry.Polygon([[0,50],[50,100],[100,50],[50,0]]);
d=new OG.geometry.Line([30,30],[70,70]);
d.style=new OG.geometry.Style({"stroke-width":5});
c=new OG.geometry.Line([30,70],[70,30]);
c.style=new OG.geometry.Style({"stroke-width":5});
b.push(e);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.G_Exclusive(this.label)
}
};
OG.shape.bpmn.G_Exclusive.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.G_Exclusive.prototype.constructor=OG.shape.bpmn.G_Exclusive;
OG.G_Exclusive=OG.shape.bpmn.G_Exclusive;OG.shape.bpmn.G_Gateway=function(a){this.SHAPE_ID="OG.shape.bpmn.G_Gateway";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Polygon([[0,50],[50,100],[100,50],[50,0]]);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.G_Gateway(this.label)
}
};
OG.shape.bpmn.G_Gateway.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.G_Gateway.prototype.constructor=OG.shape.bpmn.G_Gateway;
OG.G_Gateway=OG.shape.bpmn.G_Gateway;OG.shape.bpmn.G_Inclusive=function(a){this.SHAPE_ID="OG.shape.bpmn.G_Inclusive";
this.label=a;
this.createShape=function(){var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Polygon([[0,50],[50,100],[100,50],[50,0]]);
c=new OG.geometry.Circle([50,50],25);
c.style=new OG.geometry.Style({"stroke-width":3});
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.G_Inclusive(this.label)
}
};
OG.shape.bpmn.G_Inclusive.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.G_Inclusive.prototype.constructor=OG.shape.bpmn.G_Inclusive;
OG.G_Inclusive=OG.shape.bpmn.G_Inclusive;OG.shape.bpmn.G_Parallel=function(a){this.SHAPE_ID="OG.shape.bpmn.G_Parallel";
this.label=a;
this.createShape=function(){var e,d,c,b=[];
if(this.geom){return this.geom
}e=new OG.geometry.Polygon([[0,50],[50,100],[100,50],[50,0]]);
d=new OG.geometry.Line([20,50],[80,50]);
d.style=new OG.geometry.Style({"stroke-width":5});
c=new OG.geometry.Line([50,20],[50,80]);
c.style=new OG.geometry.Style({"stroke-width":5});
b.push(e);
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.G_Parallel(this.label)
}
};
OG.shape.bpmn.G_Parallel.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.G_Parallel.prototype.constructor=OG.shape.bpmn.G_Parallel;
OG.G_Parallel=OG.shape.bpmn.G_Parallel;OG.shape.bpmn.M_Annotation=function(a){this.SHAPE_ID="OG.shape.bpmn.M_Annotation";
this.label=a||"Annotation";
this.createShape=function(){if(this.geom){return this.geom
}var d,c,b=[];
if(this.geom){return this.geom
}d=new OG.geometry.Rectangle([0,0],100,100);
d.style=new OG.geometry.Style({stroke:"none"});
c=new OG.geometry.PolyLine([[10,0],[0,0],[0,100],[10,100]]);
c.style=new OG.geometry.Style({stroke:"black"});
b.push(d);
b.push(c);
this.geom=new OG.geometry.GeometryCollection(b);
this.geom.style=new OG.geometry.Style({});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.M_Annotation(this.label)
}
};
OG.shape.bpmn.M_Annotation.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.M_Annotation.prototype.constructor=OG.shape.bpmn.M_Annotation;
OG.M_Annotation=OG.shape.bpmn.M_Annotation;OG.shape.bpmn.M_Group=function(a){this.SHAPE_ID="OG.shape.bpmn.M_Group";
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({"stroke-dasharray":"-",r:6});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.M_Group(this.label)
}
};
OG.shape.bpmn.M_Group.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.M_Group.prototype.constructor=OG.shape.bpmn.M_Group;
OG.M_Group=OG.shape.bpmn.M_Group;OG.shape.bpmn.M_Text=function(a){this.SHAPE_ID="OG.shape.bpmn.M_Text";
this.label=a||"Text";
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
this.geom.style=new OG.geometry.Style({stroke:"none"});
return this.geom
};
this.clone=function(){return new OG.shape.bpmn.M_Text(this.label)
}
};
OG.shape.bpmn.M_Text.prototype=new OG.shape.GeomShape();
OG.shape.bpmn.M_Text.prototype.constructor=OG.shape.bpmn.M_Text;
OG.M_Text=OG.shape.bpmn.M_Text;OG.renderer.IRenderer=function(){this.drawShape=function(a,b,c,d,e){throw new OG.NotImplementedException()
};
this.drawGeom=function(b,a,c){throw new OG.NotImplementedException()
};
this.drawText=function(a,d,b,c,e){throw new OG.NotImplementedException()
};
this.drawImage=function(a,d,b,c,e){throw new OG.NotImplementedException()
};
this.drawEdge=function(a,c,d,b){throw new OG.NotImplementedException()
};
this.drawLabel=function(a,c,b){throw new OG.NotImplementedException()
};
this.drawEdgeLabel=function(a,c,b){throw new OG.NotImplementedException()
};
this.redrawShape=function(a,b){throw new OG.NotImplementedException()
};
this.redrawEdge=function(a){throw new OG.NotImplementedException()
};
this.redrawConnectedEdge=function(a,b){throw new OG.NotImplementedException()
};
this.connect=function(e,d,c,b,a){throw new OG.NotImplementedException()
};
this.disconnect=function(a){throw new OG.NotImplementedException()
};
this.drawDropOverGuide=function(a){throw new OG.NotImplementedException()
};
this.drawGuide=function(a){throw new OG.NotImplementedException()
};
this.removeGuide=function(a){throw new OG.NotImplementedException()
};
this.removeAllGuide=function(){throw new OG.NotImplementedException()
};
this.drawEdgeGuide=function(a){throw new OG.NotImplementedException()
};
this.drawRubberBand=function(a,b,c){throw new OG.NotImplementedException()
};
this.removeRubberBand=function(a){throw new OG.NotImplementedException()
};
this.drawTerminal=function(a,b){throw new OG.NotImplementedException()
};
this.removeTerminal=function(a){throw new OG.NotImplementedException()
};
this.removeAllTerminal=function(){throw new OG.NotImplementedException()
};
this.drawCollapseGuide=function(a){throw new OG.NotImplementedException()
};
this.removeCollapseGuide=function(a){throw new OG.NotImplementedException()
};
this.group=function(a){throw new OG.NotImplementedException()
};
this.ungroup=function(a){throw new OG.NotImplementedException()
};
this.addToGroup=function(a,b){throw new OG.NotImplementedException()
};
this.collapse=function(a){throw new OG.NotImplementedException()
};
this.expand=function(a){throw new OG.NotImplementedException()
};
this.clear=function(){throw new OG.NotImplementedException()
};
this.removeShape=function(a){throw new OG.NotImplementedException()
};
this.remove=function(a){throw new OG.NotImplementedException()
};
this.removeChild=function(a){throw new OG.NotImplementedException()
};
this.getRootElement=function(){throw new OG.NotImplementedException()
};
this.getRootGroup=function(){throw new OG.NotImplementedException()
};
this.getElementByPoint=function(a){throw new OG.NotImplementedException()
};
this.getElementsByBBox=function(a){throw new OG.NotImplementedException()
};
this.setAttr=function(a,b){throw new OG.NotImplementedException()
};
this.getAttr=function(b,a){throw new OG.NotImplementedException()
};
this.setShapeStyle=function(a,b){throw new OG.NotImplementedException()
};
this.toFront=function(a){throw new OG.NotImplementedException()
};
this.toBack=function(a){throw new OG.NotImplementedException()
};
this.setCanvasSize=function(a){throw new OG.NotImplementedException()
};
this.setViewBox=function(a,b,c){throw new OG.NotImplementedException()
};
this.show=function(a){throw new OG.NotImplementedException()
};
this.hide=function(a){throw new OG.NotImplementedException()
};
this.appendChild=function(a,b){throw new OG.NotImplementedException()
};
this.insertAfter=function(a,b){throw new OG.NotImplementedException()
};
this.insertBefore=function(a,b){throw new OG.NotImplementedException()
};
this.move=function(a,b){throw new OG.NotImplementedException()
};
this.moveCentroid=function(b,a){throw new OG.NotImplementedException()
};
this.rotate=function(a,b){throw new OG.NotImplementedException()
};
this.resize=function(a,b){throw new OG.NotImplementedException()
};
this.resizeBox=function(b,a){throw new OG.NotImplementedException()
};
this.intersectionEdge=function(b,c,e,d,a){throw new OG.NotImplementedException()
};
this.clone=function(a){throw new OG.NotImplementedException()
};
this.getElementById=function(a){throw new OG.NotImplementedException()
};
this.getBBox=function(a){throw new OG.NotImplementedException()
};
this.getRootBBox=function(){throw new OG.NotImplementedException()
};
this.getContainer=function(){throw new OG.NotImplementedException()
};
this.isSVG=function(){throw new OG.NotImplementedException()
};
this.isVML=function(){throw new OG.NotImplementedException()
}
};
OG.renderer.IRenderer.prototype=new OG.renderer.IRenderer();
OG.renderer.IRenderer.prototype.constructor=OG.renderer.IRenderer;OG.renderer.RaphaelRenderer=function(m,q,f,a){var p=new Raphael(m,q?q[0]:null,q?q[1]:null),n=Math.round(Math.random()*10000),i=0,s=new OG.HashMap(),j,t=this,d=f||OG.Constants.CANVAS_BACKGROUND,c,o,u,g,v,e,r,l,b,h,k;
c=function(){var w="OG_"+n+"_"+i;
i++;
return w
};
o=function(x,z,w,y){x.id=z||c();
x.node.id=x.id;
x.node.raphaelid=x.id;
if(w){$(x.node).attr("_type",w)
}if(y){$(x.node).attr("_shape",y)
}s.put(x.id,x);
return x
};
u=function(w){var y,x;
if(w){y=w.node.childNodes;
for(x=y.length-1;
x>=0;
x--){u(v(y[x].id))
}s.remove(w.id);
w.remove()
}};
g=function(w){var y,x;
if(w){y=w.node.childNodes;
for(x=y.length-1;
x>=0;
x--){u(v(y[x].id))
}}};
v=function(w){return s.get(w)
};
e=function(C,E,w,F){var z=0,G="",D,A,x,y={},B=function(P,M){var L,K,I,H,J,N=[],O=function(T,S,R){var Q=Math.PI/180*R;
return new OG.geometry.Coordinate(OG.Util.round(T.x+S*Math.cos(Q)),OG.Util.round(T.y+S*Math.sin(Q)))
};
L=OG.JSON.decode(P.toString());
K=P.getVertices();
J=L.angle;
I=O(K[0],M,90+J);
H=O(K[0],M,J);
N=N.concat(["M",I.x,I.y,"Q",K[0].x,K[0].y,H.x,H.y]);
I=O(K[1],M,180+J);
H=O(K[1],M,90+J);
N=N.concat(["L",I.x,I.y,"Q",K[1].x,K[1].y,H.x,H.y]);
I=O(K[2],M,270+J);
H=O(K[2],M,180+J);
N=N.concat(["L",I.x,I.y,"Q",K[2].x,K[2].y,H.x,H.y]);
I=O(K[3],M,J);
H=O(K[3],M,270+J);
N=N.concat(["L",I.x,I.y,"Q",K[3].x,K[3].y,H.x,H.y,"Z"]);
return N.toString()
};
if(F){OG.Util.apply(y,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Util.apply({},E.style.map,OG.Util.apply({},F,OG.Constants.DEFAULT_STYLE.GEOM)))
}else{OG.Util.apply(y,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Util.apply({},E.style.map,OG.Constants.DEFAULT_STYLE.GEOM))
}E.style.map=y;
switch(E.TYPE){case OG.Constants.GEOM_TYPE.POINT:A=p.circle(E.coordinate.x,E.coordinate.y,0.5);
A.attr(y);
break;
case OG.Constants.GEOM_TYPE.LINE:case OG.Constants.GEOM_TYPE.POLYLINE:case OG.Constants.GEOM_TYPE.POLYGON:G="";
D=E.getVertices();
for(z=0;
z<D.length;
z++){if(z===0){G="M"+D[z].x+" "+D[z].y
}else{G+="L"+D[z].x+" "+D[z].y
}}A=p.path(G);
A.attr(y);
break;
case OG.Constants.GEOM_TYPE.RECTANGLE:if((y.r||0)===0){G="";
D=E.getVertices();
for(z=0;
z<D.length;
z++){if(z===0){G="M"+D[z].x+" "+D[z].y
}else{G+="L"+D[z].x+" "+D[z].y
}}}else{G=B(E,y.r||0)
}A=p.path(G);
A.attr(y);
break;
case OG.Constants.GEOM_TYPE.CIRCLE:x=OG.JSON.decode(E.toString());
if(x.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.CIRCLE]){A=p.circle(x.center[0],x.center[1],x.radius)
}else{if(x.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE]){if(x.angle===0){A=p.ellipse(x.center[0],x.center[1],x.radiusX,x.radiusY)
}else{G="";
D=E.getControlPoints();
G="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 0 "+D[5].x+" "+D[5].y;
G+="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 1 "+D[5].x+" "+D[5].y;
A=p.path(G)
}}}A.attr(y);
break;
case OG.Constants.GEOM_TYPE.ELLIPSE:x=OG.JSON.decode(E.toString());
if(x.angle===0){A=p.ellipse(x.center[0],x.center[1],x.radiusX,x.radiusY)
}else{G="";
D=E.getControlPoints();
G="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 0 "+D[5].x+" "+D[5].y;
G+="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 1 "+D[5].x+" "+D[5].y;
A=p.path(G)
}A.attr(y);
break;
case OG.Constants.GEOM_TYPE.CURVE:G="";
D=E.getControlPoints();
for(z=0;
z<D.length;
z++){if(z===0){G="M"+D[z].x+" "+D[z].y
}else{if(z===1){G+="R"+D[z].x+" "+D[z].y
}else{G+=" "+D[z].x+" "+D[z].y
}}}A=p.path(G);
A.attr(y);
break;
case OG.Constants.GEOM_TYPE.COLLECTION:for(z=0;
z<E.geometries.length;
z++){e(C,E.geometries[z],w,E.style.map)
}break
}if(A){o(A);
C.appendChild(A.node);
return A.node
}else{return C
}};
r=function(y,x,B,A){var w={x:B[0],y:B[1]},z={x:A[0],y:A[1]};
if(y==="c"&&x==="c"){if(w.x<=z.x&&w.y<=z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="e";
x="w"
}else{y="s";
x="n"
}}else{if(w.x<=z.x&&w.y>z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="e";
x="w"
}else{y="n";
x="s"
}}else{if(w.x>z.x&&w.y<=z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="w";
x="e"
}else{y="s";
x="n"
}}else{if(w.x>z.x&&w.y>z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="w";
x="e"
}else{y="n";
x="s"
}}}}}}else{if(y==="c"&&x!=="c"){if(w.x<=z.x&&w.y<=z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="e"
}else{y="s"
}}else{if(w.x<=z.x&&w.y>z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="e"
}else{y="n"
}}else{if(w.x>z.x&&w.y<=z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="w"
}else{y="s"
}}else{if(w.x>z.x&&w.y>z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){y="w"
}else{y="n"
}}}}}}else{if(y!=="c"&&x==="c"){if(w.x<=z.x&&w.y<=z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){x="w"
}else{x="n"
}}else{if(w.x<=z.x&&w.y>z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){x="w"
}else{x="s"
}}else{if(w.x>z.x&&w.y<=z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){x="e"
}else{x="n"
}}else{if(w.x>z.x&&w.y>z.y){if(Math.abs(z.x-w.x)>Math.abs(z.y-w.y)){x="e"
}else{x="s"
}}}}}}}}return y+" "+x
};
l=function(y,D,E){var B={x:D[0],y:D[1]},w={x:E[0],y:E[1]},F=t.drawTerminal(y),z=F.terminal.childNodes,C,A,x;
if(Math.abs(w.x-B.x)>Math.abs(w.y-B.y)){if(w.x>B.x){C="e"
}else{C="w"
}}else{if(w.y>B.y){C="s"
}else{C="n"
}}A=z[0];
for(x=0;
x<z.length;
x++){if(z[x].terminal&&z[x].terminal.direction.toLowerCase()===C){A=z[x];
break
}}return A
};
b=function(y,C,D){var B={x:C[0],y:C[1]},w={x:D[0],y:D[1]},E=t.drawTerminal(y),z=E.terminal.childNodes,A,F,x;
if(Math.abs(w.x-B.x)>Math.abs(w.y-B.y)){if(w.x>B.x){A="w"
}else{A="e"
}}else{if(w.y>B.y){A="n"
}else{A="s"
}}F=z[0];
for(x=0;
x<z.length;
x++){if(z[x].terminal&&z[x].terminal.direction.toLowerCase()===A){F=z[x];
break
}}return F
};
h=function(w){var x=OG.Util.isElement(w)?w.id:w;
if(x){return t.getElementById(x.substring(0,x.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)))
}else{return null
}};
k=function(S,J,L,Q,N,C){var B=OG.Constants.LABEL_PADDING,O=L?L[0]-B*2:null,M=L?L[1]-B*2:null,R=L?L[2]||0:0,F,z,w,E={},G,P,D,A,K,I,H;
OG.Util.apply(E,(Q instanceof OG.geometry.Style)?Q.map:Q||{},OG.Constants.DEFAULT_STYLE.TEXT);
if(N===0||N){F=v(N);
if(F){g(F)
}else{F=p.group();
o(F,N)
}}else{F=p.group();
o(F,N)
}G=E["text-anchor"]||"middle";
E["text-anchor"]="middle";
z=p.text(S[0],S[1],J);
z.attr(E);
D=z.getBBox();
O=O?(O>D.width?O:D.width):D.width;
M=M?(M>D.height?M:D.height):D.height;
A=OG.Util.round(S[0]-O/2);
K=OG.Util.round(S[1]-M/2);
P=new OG.Rectangle([A,K],O,M);
if(E["label-direction"]==="vertical"){switch(G){case"start":H=P.getBoundary().getLowerCenter().y;
break;
case"end":H=P.getBoundary().getUpperCenter().y;
break;
case"middle":H=P.getBoundary().getCentroid().y;
break;
default:H=P.getBoundary().getCentroid().y;
break
}switch(E["vertical-align"]){case"top":I=OG.Util.round(P.getBoundary().getLeftCenter().x+D.height/2);
break;
case"bottom":I=OG.Util.round(P.getBoundary().getRightCenter().x-D.height/2);
break;
case"middle":I=P.getBoundary().getCentroid().x;
break;
default:I=P.getBoundary().getCentroid().x;
break
}R=-90
}else{switch(G){case"start":I=P.getBoundary().getLeftCenter().x;
break;
case"end":I=P.getBoundary().getRightCenter().x;
break;
case"middle":I=P.getBoundary().getCentroid().x;
break;
default:I=P.getBoundary().getCentroid().x;
break
}switch(E["vertical-align"]){case"top":H=OG.Util.round(P.getBoundary().getUpperCenter().y+D.height/2);
break;
case"bottom":H=OG.Util.round(P.getBoundary().getLowerCenter().y-D.height/2);
break;
case"middle":H=P.getBoundary().getCentroid().y;
break;
default:H=P.getBoundary().getCentroid().y;
break
}}z.attr({x:I,y:H,stroke:"none",fill:E["font-color"]||OG.Constants.DEFAULT_STYLE.LABEL["font-color"],"font-size":E["font-size"]||OG.Constants.DEFAULT_STYLE.LABEL["font-size"],"fill-opacity":1});
if(R||E["label-angle"]){if(R===0){R=parseInt(E["label-angle"],10)
}z.rotate(R)
}z.attr({"text-anchor":G});
if(C&&J){D=z.getBBox();
w=p.rect(D.x-B/2,D.y-B/2,D.width+B,D.height+B);
w.attr({stroke:"none",fill:d,"fill-opacity":1});
o(w);
F.node.appendChild(w.node)
}o(z);
F.node.appendChild(z.node);
return F.node
};
j=o(p.group(),null,OG.Constants.NODE_TYPE.ROOT);
p.id="OG_"+n;
p.canvas.id="OG_"+n;
$(p.canvas).css({"background-color":d,"user-select":"none","-o-user-select":"none","-moz-user-select":"none","-khtml-user-select":"none","-webkit-user-select":"none"});
if(a){$(p.canvas).css({"background-image":a})
}if($(p.canvas.parentNode).css("position")==="static"){$(p.canvas.parentNode).css({position:"relative",left:"0",top:"0"})
}this.drawShape=function(C,D,H,w,x){var y=H?H[0]:100,G=H?H[1]:100,z,E,F,A,B;
if(D instanceof OG.shape.GeomShape){E=D.createShape();
E.moveCentroid(C);
E.resizeBox(y,G);
z=this.drawGeom(E,w,x);
D.geom=z.geom
}else{if(D instanceof OG.shape.TextShape){F=D.createShape();
z=this.drawText(C,F,H,w,x);
D.text=z.text;
D.angle=z.angle;
D.geom=z.geom
}else{if(D instanceof OG.shape.ImageShape){A=D.createShape();
z=this.drawImage(C,A,H,w,x);
D.image=z.image;
D.angle=z.angle;
D.geom=z.geom
}else{if(D instanceof OG.shape.HtmlShape){B=D.createShape();
z=this.drawHtml(C,B,H,w,x);
D.html=z.html;
D.angle=z.angle;
D.geom=z.geom
}else{if(D instanceof OG.shape.EdgeShape){E=D.geom||D.createShape();
z=this.drawEdge(E,w,x);
D.geom=z.geom
}else{if(D instanceof OG.shape.GroupShape){E=D.createShape();
E.moveCentroid(C);
E.resizeBox(y,G);
z=this.drawGroup(E,w,x);
D.geom=z.geom
}}}}}}if(D.geom){z.shape=D
}z.shapeStyle=(w instanceof OG.geometry.Style)?w.map:w;
$(z).attr("_shape_id",D.SHAPE_ID);
if(!(D instanceof OG.shape.TextShape)){this.drawLabel(z);
if(D instanceof OG.shape.EdgeShape){this.drawEdgeLabel(z,null,"FROM");
this.drawEdgeLabel(z,null,"TO")
}}if(z.geom){if(OG.Util.isIE7()){z.removeAttribute("geom")
}else{delete z.geom
}}if(z.text){if(OG.Util.isIE7()){z.removeAttribute("text")
}else{delete z.text
}}if(z.image){if(OG.Util.isIE7()){z.removeAttribute("image")
}else{delete z.image
}}if(z.angle){if(OG.Util.isIE7()){z.removeAttribute("angle")
}else{delete z.angle
}}$(p.canvas).trigger("drawShape",[z]);
return z
};
this.drawGeom=function(z,x,A){var y,w={};
OG.Util.apply(w,(x instanceof OG.geometry.Style)?x.map:x||{});
if(A===0||A){y=v(A);
if(y){g(y)
}else{y=p.group();
o(y,A,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GEOM);
j.node.appendChild(y.node)
}}else{y=p.group();
o(y,A,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GEOM);
j.node.appendChild(y.node)
}e(y.node,z,w);
y.node.geom=z;
y.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(y.node.shape){y.node.shape.geom=z;
if(y.node.geom){if(OG.Util.isIE7()){y.node.removeAttribute("geom")
}else{delete y.node.geom
}}}return y.node
};
this.drawText=function(F,L,O,w,z){var A=O?O[0]:null,M=O?O[1]:null,D=O?O[2]||0:0,K,E,C={},I,N,B,H,J,G;
OG.Util.apply(C,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Constants.DEFAULT_STYLE.TEXT);
if(z===0||z){K=v(z);
if(K){g(K)
}else{K=p.group();
o(K,z,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.TEXT);
j.node.appendChild(K.node)
}}else{K=p.group();
o(K,z,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.TEXT);
j.node.appendChild(K.node)
}E=p.text(F[0],F[1],L);
E.attr(C);
N=E.getBBox();
A=A?(A>N.width?A:N.width):N.width;
M=M?(M>N.height?M:N.height):N.height;
B=OG.Util.round(F[0]-A/2);
H=OG.Util.round(F[1]-M/2);
I=new OG.Rectangle([B,H],A,M);
I.style.map=C;
switch(C["text-anchor"]){case"start":J=I.getBoundary().getLeftCenter().x;
break;
case"end":J=I.getBoundary().getRightCenter().x;
break;
case"middle":J=I.getBoundary().getCentroid().x;
break;
default:J=I.getBoundary().getCentroid().x;
break
}switch(C["vertical-align"]){case"top":G=OG.Util.round(I.getBoundary().getUpperCenter().y+N.height/2);
break;
case"bottom":G=OG.Util.round(I.getBoundary().getLowerCenter().y-N.height/2);
break;
case"middle":G=I.getBoundary().getCentroid().y;
break;
default:G=I.getBoundary().getCentroid().y;
break
}E.attr({x:J,y:G});
E.attr({stroke:"none",fill:C["font-color"]||OG.Constants.DEFAULT_STYLE.LABEL["font-color"],"font-size":C["font-size"]||OG.Constants.DEFAULT_STYLE.LABEL["font-size"]});
if(D){E.rotate(D)
}o(E);
K.node.appendChild(E.node);
K.node.text=L;
K.node.angle=D;
K.node.geom=I;
K.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(K.node.shape){K.node.shape.text=L;
K.node.shape.angle=D;
K.node.shape.geom=I;
if(K.node.text){if(OG.Util.isIE7()){K.node.removeAttribute("text")
}else{delete K.node.text
}}if(K.node.angle){if(OG.Util.isIE7()){K.node.removeAttribute("angle")
}else{delete K.node.angle
}}if(K.node.geom){if(OG.Util.isIE7()){K.node.removeAttribute("geom")
}else{delete K.node.geom
}}}return K.node
};
this.drawHtml=function(E,D,K,w,x){var y=K?K[0]:null,I=K?K[1]:null,B=K?K[2]||0:0,H,C,A={},J,G,z,F;
OG.Util.apply(A,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Constants.DEFAULT_STYLE.HTML);
if(x===0||x){H=v(x);
if(H){g(H)
}else{H=p.group();
o(H,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.HTML);
j.node.appendChild(H.node)
}}else{H=p.group();
o(H,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.HTML);
j.node.appendChild(H.node)
}C=p.foreignObject(D,E[0],E[1],y,I);
C.attr(A);
J=C.getBBox();
y=y||J.width;
I=I||J.height;
z=OG.Util.round(E[0]-y/2);
F=OG.Util.round(E[1]-I/2);
C.attr({x:z,y:F});
G=new OG.Rectangle([z,F],y,I);
if(B){C.rotate(B)
}G.style.map=A;
o(C);
H.node.appendChild(C.node);
H.node.html=D;
H.node.angle=B;
H.node.geom=G;
H.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(H.node.shape){H.node.shape.html=D;
H.node.shape.angle=B;
H.node.shape.geom=G;
if(H.node.html){if(OG.Util.isIE7()){H.node.removeAttribute("html")
}else{delete H.node.html
}}if(H.node.angle){if(OG.Util.isIE7()){H.node.removeAttribute("angle")
}else{delete H.node.angle
}}if(H.node.geom){if(OG.Util.isIE7()){H.node.removeAttribute("geom")
}else{delete H.node.geom
}}}return H.node
};
this.drawImage=function(D,J,K,w,x){var y=K?K[0]:null,H=K?K[1]:null,B=K?K[2]||0:0,G,C,A={},I,F,z,E;
OG.Util.apply(A,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Constants.DEFAULT_STYLE.IMAGE);
if(x===0||x){G=v(x);
if(G){g(G)
}else{G=p.group();
o(G,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.IMAGE);
j.node.appendChild(G.node)
}}else{G=p.group();
o(G,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.IMAGE);
j.node.appendChild(G.node)
}C=p.image(J,D[0],D[1],y,H);
C.attr(A);
I=C.getBBox();
y=y||I.width;
H=H||I.height;
z=OG.Util.round(D[0]-y/2);
E=OG.Util.round(D[1]-H/2);
C.attr({x:z,y:E});
F=new OG.Rectangle([z,E],y,H);
if(B){C.rotate(B)
}F.style.map=A;
o(C);
G.node.appendChild(C.node);
G.node.image=J;
G.node.angle=B;
G.node.geom=F;
G.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(G.node.shape){G.node.shape.image=J;
G.node.shape.angle=B;
G.node.shape.geom=F;
if(G.node.image){if(OG.Util.isIE7()){G.node.removeAttribute("image")
}else{delete G.node.image
}}if(G.node.angle){if(OG.Util.isIE7()){G.node.removeAttribute("angle")
}else{delete G.node.angle
}}if(G.node.geom){if(OG.Util.isIE7()){G.node.removeAttribute("geom")
}else{delete G.node.geom
}}}return G.node
};
this.drawEdge=function(J,w,x,A){var H,z={},B=J.getVertices(),E=B[0],D=B[B.length-1],G=[],y,C,I=function(M,L,K){if(K){return[[M[0],M[1]],[L[0],M[1]],[L[0],L[1]]]
}else{return[[M[0],M[1]],[M[0],L[1]],[L[0],L[1]]]
}},F=function(M,L,K){if(K){return[[M[0],M[1]],[OG.Util.round((M[0]+L[0])/2),M[1]],[OG.Util.round((M[0]+L[0])/2),L[1]],[L[0],L[1]]]
}else{return[[M[0],M[1]],[M[0],OG.Util.round((M[1]+L[1])/2)],[L[0],OG.Util.round((M[1]+L[1])/2)],[L[0],L[1]]]
}};
OG.Util.apply(z,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Util.apply({},J.style.map,OG.Constants.DEFAULT_STYLE.EDGE));
if(x===0||x){H=v(x);
if(H){g(H)
}else{H=p.group();
o(H,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.EDGE);
j.node.appendChild(H.node)
}}else{H=p.group();
o(H,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.EDGE);
j.node.appendChild(H.node)
}if(A){G=[[E.x,E.y-OG.Constants.GUIDE_RECT_SIZE/2],[E.x+OG.Constants.GUIDE_RECT_SIZE*2,E.y-OG.Constants.GUIDE_RECT_SIZE],[E.x+OG.Constants.GUIDE_RECT_SIZE*2,E.y+OG.Constants.GUIDE_RECT_SIZE],[E.x,E.y+OG.Constants.GUIDE_RECT_SIZE/2]]
}else{if(J instanceof OG.geometry.Line){switch(z["edge-type"].toLowerCase()){case OG.Constants.EDGE_TYPE.STRAIGHT:G=[E,D];
break;
case OG.Constants.EDGE_TYPE.PLAIN:C=z["edge-direction"].toLowerCase().split(" ");
if(C[0]==="c"||C[1]==="c"){C=r(C[0],C[1],[E.x,E.y],[D.x,D.y]).split(" ")
}if(C[0]==="e"){switch(C[1]){case"e":if(E.x<=D.x){G=I([E.x,E.y],[D.x+OG.Constants.EDGE_PADDING,D.y],true);
G.push([D.x,D.y])
}else{G=[[E.x,E.y]];
G=G.concat(I([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x,D.y],false))
}break;
case"w":if(E.x<=D.x){G=F([E.x,E.y],[D.x,D.y],true)
}else{G=[[E.x,E.y]];
G=G.concat(F([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x-OG.Constants.EDGE_PADDING,D.y],false));
G.push([D.x,D.y])
}break;
case"s":if(E.x<=D.x&&E.y<=D.y){G=F([E.x,E.y],[D.x,D.y+OG.Constants.EDGE_PADDING],true);
G.push([D.x,D.y])
}else{if(E.x<=D.x&&E.y>D.y){G=I([E.x,E.y],[D.x,D.y],true)
}else{if(E.x>D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x,D.y+OG.Constants.EDGE_PADDING],false));
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x,D.y],false))
}}}}break;
case"n":if(E.x<=D.x&&E.y<=D.y){G=I([E.x,E.y],[D.x,D.y],true)
}else{if(E.x<=D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x,D.y-OG.Constants.EDGE_PADDING],false));
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x,D.y],false))
}else{if(E.x>D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x+OG.Constants.EDGE_PADDING,E.y],[D.x,D.y-OG.Constants.EDGE_PADDING],false));
G.push([D.x,D.y])
}}}}break
}}else{if(C[0]==="w"){switch(C[1]){case"e":if(E.x<=D.x){G=[[E.x,E.y]];
G=G.concat(F([E.x-OG.Constants.EDGE_PADDING,E.y],[D.x+OG.Constants.EDGE_PADDING,D.y],false));
G.push([D.x,D.y])
}else{G=F([E.x,E.y],[D.x,D.y],true)
}break;
case"w":if(E.x<=D.x){G=[[E.x,E.y]];
G=G.concat(I([E.x-OG.Constants.EDGE_PADDING,E.y],[D.x,D.y],false))
}else{G=I([E.x,E.y],[D.x-OG.Constants.EDGE_PADDING,D.y],true);
G.push([D.x,D.y])
}break;
case"s":if(E.x<=D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x-OG.Constants.EDGE_PADDING,E.y],[D.x,D.y+OG.Constants.EDGE_PADDING],false));
G.push([D.x,D.y])
}else{if(E.x<=D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x-OG.Constants.EDGE_PADDING,E.y],[D.x,D.y],false))
}else{if(E.x>D.x&&E.y<=D.y){G=F([E.x,E.y],[D.x,D.y+OG.Constants.EDGE_PADDING],true);
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y>D.y){G=I([E.x,E.y],[D.x,D.y],true)
}}}}break;
case"n":if(E.x<=D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x-OG.Constants.EDGE_PADDING,E.y],[D.x,D.y],false))
}else{if(E.x<=D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x-OG.Constants.EDGE_PADDING,E.y],[D.x,D.y-OG.Constants.EDGE_PADDING],false));
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y<=D.y){G=G.concat(I([E.x,E.y],[D.x,D.y],true))
}else{if(E.x>D.x&&E.y>D.y){G=F([E.x,E.y],[D.x,D.y-OG.Constants.EDGE_PADDING],true);
G.push([D.x,D.y])
}}}}break
}}else{if(C[0]==="s"){switch(C[1]){case"e":if(E.x<=D.x&&E.y<=D.y){G=F([E.x,E.y],[D.x+OG.Constants.EDGE_PADDING,D.y],false);
G.push([D.x,D.y])
}else{if(E.x<=D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x,E.y+OG.Constants.EDGE_PADDING],[D.x+OG.Constants.EDGE_PADDING,D.y],true));
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y<=D.y){G=I([E.x,E.y],[D.x,D.y],false)
}else{if(E.x>D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x,E.y+OG.Constants.EDGE_PADDING],[D.x,D.y],true))
}}}}break;
case"w":if(E.x<=D.x&&E.y<=D.y){G=I([E.x,E.y],[D.x,D.y],false)
}else{if(E.x<=D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x,E.y+OG.Constants.EDGE_PADDING],[D.x,D.y],true))
}else{if(E.x>D.x&&E.y<=D.y){G=F([E.x,E.y],[D.x-OG.Constants.EDGE_PADDING,D.y],false);
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y>D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x,E.y+OG.Constants.EDGE_PADDING],[D.x-OG.Constants.EDGE_PADDING,D.y],true));
G.push([D.x,D.y])
}}}}break;
case"s":if(E.y<=D.y){G=I([E.x,E.y],[D.x,D.y+OG.Constants.EDGE_PADDING],false);
G.push([D.x,D.y])
}else{G=[[E.x,E.y]];
G=G.concat(I([E.x,E.y+OG.Constants.EDGE_PADDING],[D.x,D.y],true))
}break;
case"n":if(E.y<=D.y){G=F([E.x,E.y],[D.x,D.y],false)
}else{G=[[E.x,E.y]];
G=G.concat(F([E.x,E.y+OG.Constants.EDGE_PADDING],[D.x,D.y-OG.Constants.EDGE_PADDING],true));
G.push([D.x,D.y])
}break
}}else{if(C[0]==="n"){switch(C[1]){case"e":if(E.x<=D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x,E.y-OG.Constants.EDGE_PADDING],[D.x+OG.Constants.EDGE_PADDING,D.y],true));
G.push([D.x,D.y])
}else{if(E.x<=D.x&&E.y>D.y){G=F([E.x,E.y],[D.x+OG.Constants.EDGE_PADDING,D.y],false);
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x,E.y-OG.Constants.EDGE_PADDING],[D.x,D.y],true))
}else{if(E.x>D.x&&E.y>D.y){G=I([E.x,E.y],[D.x,D.y],false)
}}}}break;
case"w":if(E.x<=D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x,E.y-OG.Constants.EDGE_PADDING],[D.x,D.y],true))
}else{if(E.x<=D.x&&E.y>D.y){G=I([E.x,E.y],[D.x,D.y],false)
}else{if(E.x>D.x&&E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x,E.y-OG.Constants.EDGE_PADDING],[D.x-OG.Constants.EDGE_PADDING,D.y],true));
G.push([D.x,D.y])
}else{if(E.x>D.x&&E.y>D.y){G=F([E.x,E.y],[D.x-OG.Constants.EDGE_PADDING,D.y],false);
G.push([D.x,D.y])
}}}}break;
case"s":if(E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(F([E.x,E.y-OG.Constants.EDGE_PADDING],[D.x,D.y+OG.Constants.EDGE_PADDING],true));
G.push([D.x,D.y])
}else{G=F([E.x,E.y],[D.x,D.y],false)
}break;
case"n":if(E.y<=D.y){G=[[E.x,E.y]];
G=G.concat(I([E.x,E.y-OG.Constants.EDGE_PADDING],[D.x,D.y],true))
}else{G=I([E.x,E.y],[D.x,D.y-OG.Constants.EDGE_PADDING],false);
G.push([D.x,D.y])
}break
}}}}}break;
case OG.Constants.EDGE_TYPE.BEZIER:break
}}else{if(J instanceof OG.geometry.Curve){G=J.getControlPoints()
}else{G=B
}}}if(A){y=new OG.Curve(G)
}else{if(J instanceof OG.geometry.Curve){y=new OG.Curve(G)
}else{y=new OG.PolyLine(G)
}}e(H.node,y,OG.Constants.DEFAULT_STYLE.EDGE_HIDDEN);
e(H.node,y,z);
H.node.geom=y;
H.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(H.node.shape){H.node.shape.geom=y;
if(H.node.geom){if(OG.Util.isIE7()){H.node.removeAttribute("geom")
}else{delete H.node.geom
}}}return H.node
};
this.drawGroup=function(D,x,y){var E,G,A={},F,B,z,w,C={};
OG.Util.apply(A,(x instanceof OG.geometry.Style)?x.map:x||{});
if(y===0||y){E=v(y);
if(E){F=E.node.childNodes;
for(B=F.length-1;
B>=0;
B--){if($(F[B]).attr("_type")!==OG.Constants.NODE_TYPE.SHAPE){u(v(F[B].id))
}}}else{E=p.group();
o(E,y,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GROUP);
j.node.appendChild(E.node)
}}else{E=p.group();
o(E,y,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GROUP);
j.node.appendChild(E.node)
}G=e(E.node,D,A);
E.node.geom=D;
E.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
OG.Util.apply(C,D.style.map,A);
if(C["label-direction"]&&C["vertical-align"]==="top"){z=D.getBoundary();
if(C["label-direction"]==="vertical"){w=new OG.geometry.Line([z.getUpperLeft().x+20,z.getUpperLeft().y],[z.getLowerLeft().x+20,z.getLowerLeft().y])
}else{w=new OG.geometry.Line([z.getUpperLeft().x,z.getUpperLeft().y+20],[z.getUpperRight().x,z.getUpperRight().y+20])
}e(E.node,w,A)
}if(G.id!==E.node.firstChild.id){E.node.insertBefore(G,E.node.firstChild)
}if(E.node.shape){if(!E.node.shape.isCollapsed||E.node.shape.isCollapsed===false){E.node.shape.geom=D
}if(E.node.geom){if(OG.Util.isIE7()){E.node.removeAttribute("geom")
}else{delete E.node.geom
}}}return E.node
};
this.drawLabel=function(E,G,x){var B=v(OG.Util.isElement(E)?E.id:E),z,C,D,y={},A,H,F=function(M){var K,I,N=0,L,J;
K=M.shape.geom.getVertices();
I=M.shape.geom.getLength();
for(L=0;
L<K.length-1;
L++){N+=K[L].distance(K[L+1]);
if(N>I/2){J=M.shape.geom.intersectCircleToLine(K[L+1],N-I/2,K[L+1],K[L]);
break
}}return J[0]
},w;
OG.Util.apply(y,(x instanceof OG.geometry.Style)?x.map:x||{});
if(B&&B.node.shape){z=B.node;
D=z.shape.geom.getBoundary();
OG.Util.apply(z.shape.geom.style.map,y);
z.shape.label=G===undefined?z.shape.label:G;
if(z.shape.label!==undefined){if(z.shape instanceof OG.shape.EdgeShape){w=F(z);
A=[w.x,w.y];
H=[0,0]
}else{switch(z.shape.geom.style.get("label-position")){case"left":A=[D.getCentroid().x-D.getWidth(),D.getCentroid().y];
break;
case"right":A=[D.getCentroid().x+D.getWidth(),D.getCentroid().y];
break;
case"top":A=[D.getCentroid().x,D.getCentroid().y-D.getHeight()];
break;
case"bottom":A=[D.getCentroid().x,D.getCentroid().y+D.getHeight()];
break;
default:A=[D.getCentroid().x,D.getCentroid().y];
break
}H=[D.getWidth(),D.getHeight()]
}C=k(A,z.shape.label,H,z.shape.geom.style,z.id+OG.Constants.LABEL_SUFFIX,z.shape instanceof OG.shape.EdgeShape);
z.appendChild(C)
}}return C
};
this.drawEdgeLabel=function(D,F,C){var z=v(OG.Util.isElement(D)?D.id:D),x,A,B,y,w,E;
if(z&&z.node.shape){x=z.node;
if(x.shape instanceof OG.shape.EdgeShape){A=x.shape.geom.getVertices();
if(C==="FROM"){y=[A[0].x,A[0].y+OG.Constants.FROMTO_LABEL_OFFSET_TOP];
x.shape.fromLabel=F||x.shape.fromLabel;
w=x.shape.fromLabel;
E=OG.Constants.FROM_LABEL_SUFFIX
}else{y=[A[A.length-1].x,A[A.length-1].y+OG.Constants.FROMTO_LABEL_OFFSET_TOP];
x.shape.toLabel=F||x.shape.toLabel;
w=x.shape.toLabel;
E=OG.Constants.TO_LABEL_SUFFIX
}if(w){B=k(y,w,[0,0],x.shape.geom.style,x.id+E,false);
x.appendChild(B)
}}}return B
};
this.redrawShape=function(A,D){var B=this,C,x,y,E,w,z;
z=function(N,I){var O,M,K,G=I.childNodes,F,L,J,H;
for(L=G.length-1;
L>=0;
L--){if($(G[L]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){z(N,G[L]);
H=false;
O=$(G[L]).attr("_fromedge");
if(O){O=O.split(",");
for(J=0;
J<O.length;
J++){M=B.getElementById(O[J]);
if(M){F=h($(M).attr("_from"));
if($(F).parents("#"+N.id).length===0){H=true
}}}}O=$(G[L]).attr("_toedge");
if(O){O=O.split(",");
for(J=0;
J<O.length;
J++){K=B.getElementById(O[J]);
if(K){F=h($(K).attr("_to"));
if($(F).parents("#"+N.id).length===0){H=true
}}}}if(H===true){B.redrawConnectedEdge(G[L])
}}}};
if(A&&A.shape.geom){switch($(A).attr("_shape")){case OG.Constants.SHAPE_TYPE.GEOM:A=this.drawGeom(A.shape.geom,{},A.id);
this.redrawConnectedEdge(A,D);
this.drawLabel(A);
break;
case OG.Constants.SHAPE_TYPE.TEXT:C=A.shape.geom.getBoundary();
x=C.getCentroid();
y=C.getWidth();
E=C.getHeight();
A=this.drawText([x.x,x.y],A.shape.text,[y,E,A.shape.angle],A.shape.geom.style,A.id);
this.redrawConnectedEdge(A,D);
break;
case OG.Constants.SHAPE_TYPE.IMAGE:C=A.shape.geom.getBoundary();
x=C.getCentroid();
y=C.getWidth();
E=C.getHeight();
A=this.drawImage([x.x,x.y],A.shape.image,[y,E,A.shape.angle],A.shape.geom.style,A.id);
this.redrawConnectedEdge(A,D);
this.drawLabel(A);
break;
case OG.Constants.SHAPE_TYPE.HTML:C=A.shape.geom.getBoundary();
x=C.getCentroid();
y=C.getWidth();
E=C.getHeight();
A=this.drawHtml([x.x,x.y],A.shape.html,[y,E,A.shape.angle],A.shape.geom.style,A.id);
this.redrawConnectedEdge(A,D);
this.drawLabel(A);
break;
case OG.Constants.SHAPE_TYPE.EDGE:A=this.drawEdge(A.shape.geom,A.shape.geom.style,A.id);
this.drawLabel(A);
this.drawEdgeLabel(A,null,"FROM");
this.drawEdgeLabel(A,null,"TO");
break;
case OG.Constants.SHAPE_TYPE.GROUP:if(A.shape.isCollapsed){C=A.shape.geom.getBoundary();
w=C.getUpperLeft();
A=this.drawGroup(new OG.geometry.Rectangle(w,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2),A.shape.geom.style,A.id);
z(A,A)
}else{A=this.drawGroup(A.shape.geom,A.shape.geom.style,A.id);
this.redrawConnectedEdge(A,D);
this.drawLabel(A)
}break
}}$(p.canvas).trigger("redrawShape",[A]);
return A
};
this.redrawEdge=function(x){var B,S,N,V,J,D,I,K,z,C,T,y,O,P,E,G,F,Q,H,M,A,U,w,L,R;
B=OG.Util.isElement(x)?x:this.getElementById(x);
S=$(B).attr("_from");
N=$(B).attr("_to");
if(S){V=h(S);
D=parseInt(S.substring(S.lastIndexOf("_")+1),10);
K=V.shape.createTerminal()[D];
T=K.direction.toLowerCase();
O=K.position
}else{C=B.shape.geom.getVertices();
T="c";
O=C[0]
}if(N){J=h(N);
I=parseInt(N.substring(N.lastIndexOf("_")+1),10);
z=J.shape.createTerminal()[I];
y=z.direction.toLowerCase();
P=z.position
}else{C=B.shape.geom.getVertices();
y="c";
P=C[C.length-1]
}E=O;
G=P;
F=T;
Q=y;
if(V&&T==="c"){H=this.intersectionEdge(B.shape.geom.style.get("edge-type"),V,[E.x,E.y],[G.x,G.y],true);
O=H.position;
T=H.direction
}if(J&&y==="c"){H=this.intersectionEdge(B.shape.geom.style.get("edge-type"),J,[E.x,E.y],[G.x,G.y],false);
P=H.position;
y=H.direction
}M=V&&J&&V.id===J.id;
if(M){O=P=V.shape.geom.getBoundary().getRightCenter()
}else{if(V){A=$(V).parents("[_collapsed=true]");
if(A.length!==0){U=A[A.length-1].shape.geom.getBoundary();
w=U.getUpperLeft();
L=new OG.geometry.Rectangle(w,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2);
switch(T.toUpperCase()){case OG.Constants.TERMINAL_TYPE.E:R=L.getBoundary().getRightCenter();
break;
case OG.Constants.TERMINAL_TYPE.W:R=L.getBoundary().getLeftCenter();
break;
case OG.Constants.TERMINAL_TYPE.S:R=L.getBoundary().getLowerCenter();
break;
case OG.Constants.TERMINAL_TYPE.N:R=L.getBoundary().getUpperCenter();
break
}if(R){O=[R.x,R.y]
}}}if(J){A=$(J).parents("[_collapsed=true]");
if(A.length!==0){U=A[A.length-1].shape.geom.getBoundary();
w=U.getUpperLeft();
L=new OG.geometry.Rectangle(w,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2);
switch(y.toUpperCase()){case OG.Constants.TERMINAL_TYPE.E:R=L.getBoundary().getRightCenter();
break;
case OG.Constants.TERMINAL_TYPE.W:R=L.getBoundary().getLeftCenter();
break;
case OG.Constants.TERMINAL_TYPE.S:R=L.getBoundary().getLowerCenter();
break;
case OG.Constants.TERMINAL_TYPE.N:R=L.getBoundary().getUpperCenter();
break
}if(R){P=[R.x,R.y]
}}}}B=this.drawEdge(new OG.Line(O,P),OG.Util.apply(B.shape.geom.style.map,{"edge-direction":T+" "+y}),B.id,M);
this.drawLabel(B);
this.drawEdgeLabel(B,null,"FROM");
this.drawEdgeLabel(B,null,"TO");
OG.Util.apply(B.shape.geom.style.map,{"edge-direction":F+" "+Q})
};
this.redrawConnectedEdge=function(w,y){var z,x=this;
z=$(w).attr("_fromedge");
if(z){$.each(z.split(","),function(A,B){if(!y||y.toString().indexOf(B)<0){x.redrawEdge(B)
}})
}z=$(w).attr("_toedge");
if(z){$.each(z.split(","),function(A,B){if(!y||y.toString().indexOf(B)<0){x.redrawEdge(B)
}})
}this.removeAllTerminal()
};
this.connect=function(K,w,A,N,F){var E={},P,H,G,J,L,B,C,O,x,D,M,I,z,y=function(S,R,T){var U=$(S).attr(R),V=U?U.split(","):[],Q=[];
$.each(V,function(W,X){if(X!==T){Q.push(X)
}});
Q.push(T);
$(S).attr(R,Q.toString());
return S
};
OG.Util.apply(E,(N instanceof OG.geometry.Style)?N.map:N||{},OG.Constants.DEFAULT_STYLE.EDGE);
if(OG.Util.isElement(K)){P=h(K);
J=[K.terminal.position.x,K.terminal.position.y];
O=K.terminal.direction.toLowerCase()
}else{J=K;
O="c"
}if(OG.Util.isElement(w)){H=h(w);
L=[w.terminal.position.x,w.terminal.position.y];
x=w.terminal.direction.toLowerCase()
}else{L=w;
x="c"
}if(P&&H){z=jQuery.Event("beforeConnectShape",{edge:A,fromShape:P,toShape:H});
$(p.canvas).trigger(z);
if(z.isPropagationStopped()){this.remove(A);
return false
}}B=J;
C=L;
D=O;
M=x;
if(P&&O==="c"){G=this.intersectionEdge(E["edge-type"],P,B,C,true);
J=G.position;
O=G.direction
}if(H&&x==="c"){G=this.intersectionEdge(E["edge-type"],H,B,C,false);
L=G.position;
x=G.direction
}I=P&&H&&P.id===H.id;
if(I){J=L=P.shape.geom.getBoundary().getRightCenter()
}A=this.drawEdge(new OG.Line(J,L),OG.Util.apply(E,{"edge-direction":O+" "+x}),A?A.id:null,I);
this.drawLabel(A,F);
this.drawEdgeLabel(A,null,"FROM");
this.drawEdgeLabel(A,null,"TO");
OG.Util.apply(A.shape.geom.style.map,{"edge-direction":D+" "+M});
A.shapeStyle=A.shape.geom.style.map;
this.disconnect(A);
if(OG.Util.isElement(K)){$(A).attr("_from",K.id);
y(P,"_toedge",A.id)
}if(OG.Util.isElement(w)){$(A).attr("_to",w.id);
y(H,"_fromedge",A.id)
}this.removeAllTerminal();
if(P&&H){$(p.canvas).trigger("connectShape",[A,P,H])
}return A
};
this.disconnect=function(B){var E=this,F,C,G,z,x,w,D,A,y=function(J,I,K){var L=$(J).attr(I),M=L?L.split(","):[],H=[];
$.each(M,function(N,O){if(O!==K){H.push(O)
}});
$(J).attr(I,H.toString());
return J
};
if(B){if($(B).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){F=$(B).attr("_from");
C=$(B).attr("_to");
if(F){G=h(F);
y(G,"_toedge",B.id);
$(B).removeAttr("_from")
}if(C){z=h(C);
y(z,"_fromedge",B.id);
$(B).removeAttr("_to")
}if(G&&z){$(p.canvas).trigger("disconnectShape",[B,G,z])
}}else{x=$(B).attr("_fromedge");
w=$(B).attr("_toedge");
if(x){$.each(x.split(","),function(H,I){D=E.getElementById(I);
F=$(D).attr("_from");
if(F){G=h(F);
y(G,"_toedge",I)
}if(G&&B){$(p.canvas).trigger("disconnectShape",[D,G,B])
}E.remove(D)
})
}if(w){$.each(w.split(","),function(H,I){A=E.getElementById(I);
C=$(A).attr("_to");
if(C){z=h(C);
y(z,"_fromedge",I)
}if(B&&z){$(p.canvas).trigger("disconnectShape",[A,B,z])
}E.remove(A)
})
}}}};
this.drawDropOverGuide=function(A){var x=v(OG.Util.isElement(A)?A.id:A),D=x?x.node.shape.geom:null,C,z,B,w=OG.Constants.GUIDE_RECT_SIZE/2,y=w/2;
if(x&&D&&$(A).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE&&!v(x.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)){C=D.getBoundary();
z=C.getUpperLeft();
B=p.rect(z.x-y,z.y-y,C.getWidth()+w,C.getHeight()+w);
B.attr(OG.Util.apply({"stroke-width":w},OG.Constants.DEFAULT_STYLE.DROP_OVER_BBOX));
o(B,x.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
B.insertAfter(x)
}};
this.drawGuide=function(y){var O=v(OG.Util.isElement(y)?y.id:y),E=O?O.node.shape.geom:null,x,H,S,G,K,A,C,L,J,R,U,B,P,I,N,F,w,z,Q,T,D=OG.Constants.GUIDE_RECT_SIZE,M=OG.Util.round(D/2);
if(O&&E){if($(y).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){return this.drawEdgeGuide(y)
}else{x=E.getBoundary();
K=x.getUpperLeft();
A=x.getUpperRight();
C=x.getLowerLeft();
L=x.getLowerRight();
J=x.getLeftCenter();
R=x.getUpperCenter();
U=x.getRightCenter();
B=x.getLowerCenter();
if(v(O.id+OG.Constants.GUIDE_SUFFIX.GUIDE)){u(v(O.id+OG.Constants.GUIDE_SUFFIX.BBOX));
G=p.rect(K.x,K.y,x.getWidth(),x.getHeight());
G.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
o(G,O.id+OG.Constants.GUIDE_SUFFIX.BBOX);
G.insertBefore(O);
P=v(O.id+OG.Constants.GUIDE_SUFFIX.UL);
I=v(O.id+OG.Constants.GUIDE_SUFFIX.UR);
N=v(O.id+OG.Constants.GUIDE_SUFFIX.LL);
F=v(O.id+OG.Constants.GUIDE_SUFFIX.LR);
w=v(O.id+OG.Constants.GUIDE_SUFFIX.LC);
z=v(O.id+OG.Constants.GUIDE_SUFFIX.UC);
Q=v(O.id+OG.Constants.GUIDE_SUFFIX.RC);
T=v(O.id+OG.Constants.GUIDE_SUFFIX.LWC);
P.attr({x:K.x-M,y:K.y-M});
I.attr({x:A.x-M,y:A.y-M});
N.attr({x:C.x-M,y:C.y-M});
F.attr({x:L.x-M,y:L.y-M});
w.attr({x:J.x-M,y:J.y-M});
z.attr({x:R.x-M,y:R.y-M});
Q.attr({x:U.x-M,y:U.y-M});
T.attr({x:B.x-M,y:B.y-M});
return null
}H=v(O.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
if(H){u(H);
u(v(O.id+OG.Constants.GUIDE_SUFFIX.BBOX))
}H=p.group();
G=p.rect(K.x,K.y,x.getWidth(),x.getHeight());
P=p.rect(K.x-M,K.y-M,D,D);
I=p.rect(A.x-M,A.y-M,D,D);
N=p.rect(C.x-M,C.y-M,D,D);
F=p.rect(L.x-M,L.y-M,D,D);
w=p.rect(J.x-M,J.y-M,D,D);
z=p.rect(R.x-M,R.y-M,D,D);
Q=p.rect(U.x-M,U.y-M,D,D);
T=p.rect(B.x-M,B.y-M,D,D);
G.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
P.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UL);
I.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UR);
N.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LL);
F.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LR);
w.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LC);
z.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UC);
Q.attr(OG.Constants.DEFAULT_STYLE.GUIDE_RC);
T.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LWC);
H.appendChild(P);
H.appendChild(I);
H.appendChild(N);
H.appendChild(F);
H.appendChild(w);
H.appendChild(z);
H.appendChild(Q);
H.appendChild(T);
o(H,O.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
o(G,O.id+OG.Constants.GUIDE_SUFFIX.BBOX);
o(P,O.id+OG.Constants.GUIDE_SUFFIX.UL);
o(I,O.id+OG.Constants.GUIDE_SUFFIX.UR);
o(N,O.id+OG.Constants.GUIDE_SUFFIX.LL);
o(F,O.id+OG.Constants.GUIDE_SUFFIX.LR);
o(w,O.id+OG.Constants.GUIDE_SUFFIX.LC);
o(z,O.id+OG.Constants.GUIDE_SUFFIX.UC);
o(Q,O.id+OG.Constants.GUIDE_SUFFIX.RC);
o(T,O.id+OG.Constants.GUIDE_SUFFIX.LWC);
S={bBox:G.node,group:H.node,ul:P.node,ur:I.node,ll:N.node,lr:F.node,lc:w.node,uc:z.node,rc:Q.node,lwc:T.node};
G.insertBefore(O);
H.insertAfter(O);
$(O.node).attr("_selected","true");
return S
}}return null
};
this.removeGuide=function(y){var x=v(OG.Util.isElement(y)?y.id:y),w=v(x.id+OG.Constants.GUIDE_SUFFIX.GUIDE),z=v(x.id+OG.Constants.GUIDE_SUFFIX.BBOX);
x.node.removeAttribute("_selected");
u(w);
u(z)
};
this.removeAllGuide=function(){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(w,x){if(OG.Util.isElement(x)&&x.id){t.removeGuide(x)
}})
};
this.drawEdgeGuide=function(B){var F=v(OG.Util.isElement(B)?B.id:B),J=F?F.node.shape.geom:null,G,E,K,x,L,z,y,D,w,H=[],I=OG.Constants.GUIDE_RECT_SIZE,C=OG.Util.round(I/2),A;
if(F&&J){G=J.getVertices();
E=$(B).attr("_from")&&$(B).attr("_to")&&$(B).attr("_from")===$(B).attr("_to");
if(v(F.id+OG.Constants.GUIDE_SUFFIX.GUIDE)){u(v(F.id+OG.Constants.GUIDE_SUFFIX.BBOX));
L="";
for(A=0;
A<G.length;
A++){if(A===0){L="M"+G[A].x+" "+G[A].y
}else{L+="L"+G[A].x+" "+G[A].y
}}z=p.path(L);
z.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
o(z,F.id+OG.Constants.GUIDE_SUFFIX.BBOX);
z.insertBefore(F);
y=v(F.id+OG.Constants.GUIDE_SUFFIX.FROM);
y.attr({x:G[0].x-C,y:G[0].y-C});
D=v(F.id+OG.Constants.GUIDE_SUFFIX.TO);
D.attr({x:G[G.length-1].x-C,y:G[G.length-1].y-C});
if(!E){for(A=1;
A<G.length-2;
A++){if(G[A].x===G[A+1].x){w=v(F.id+OG.Constants.GUIDE_SUFFIX.CTL_H+A);
if(w){w.attr({x:G[A].x-C,y:OG.Util.round((G[A].y+G[A+1].y)/2)-C})
}}else{w=v(F.id+OG.Constants.GUIDE_SUFFIX.CTL_V+A);
if(w){w.attr({x:OG.Util.round((G[A].x+G[A+1].x)/2)-C,y:G[A].y-C})
}}}}return null
}K=v(F.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
if(K){u(K);
u(v(F.id+OG.Constants.GUIDE_SUFFIX.BBOX))
}K=p.group();
L="";
for(A=0;
A<G.length;
A++){if(A===0){L="M"+G[A].x+" "+G[A].y
}else{L+="L"+G[A].x+" "+G[A].y
}}z=p.path(L);
z.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
y=p.rect(G[0].x-C,G[0].y-C,I,I);
y.attr(OG.Constants.DEFAULT_STYLE.GUIDE_FROM);
K.appendChild(y);
o(y,F.id+OG.Constants.GUIDE_SUFFIX.FROM);
D=p.rect(G[G.length-1].x-C,G[G.length-1].y-C,I,I);
D.attr(OG.Constants.DEFAULT_STYLE.GUIDE_TO);
K.appendChild(D);
o(D,F.id+OG.Constants.GUIDE_SUFFIX.TO);
if(!E){for(A=1;
A<G.length-2;
A++){if(G[A].x===G[A+1].x){w=p.rect(G[A].x-C,OG.Util.round((G[A].y+G[A+1].y)/2)-C,I,I);
w.attr(OG.Constants.DEFAULT_STYLE.GUIDE_CTL_H);
o(w,F.id+OG.Constants.GUIDE_SUFFIX.CTL_H+A)
}else{w=p.rect(OG.Util.round((G[A].x+G[A+1].x)/2)-C,G[A].y-C,I,I);
w.attr(OG.Constants.DEFAULT_STYLE.GUIDE_CTL_V);
o(w,F.id+OG.Constants.GUIDE_SUFFIX.CTL_V+A)
}K.appendChild(w);
H.push(w.node)
}}o(z,F.id+OG.Constants.GUIDE_SUFFIX.BBOX);
o(K,F.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
x={bBox:z.node,group:K.node,from:y.node,to:D.node,controls:H};
z.insertBefore(F);
K.insertAfter(F);
$(F.node).attr("_selected","true");
return x
}return null
};
this.drawRubberBand=function(B,G,w){var E=B?B[0]:0,C=B?B[1]:0,z=G?G[0]:0,F=G?G[1]:0,D=v(OG.Constants.RUBBER_BAND_ID),A={};
if(D){D.attr({x:E,y:C,width:Math.abs(z),height:Math.abs(F)});
return D
}OG.Util.apply(A,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Constants.DEFAULT_STYLE.RUBBER_BAND);
D=p.rect(E,C,z,F).attr(A);
o(D,OG.Constants.RUBBER_BAND_ID);
return D.node
};
this.removeRubberBand=function(w){this.setAttr(OG.Constants.RUBBER_BAND_ID,{x:0,y:0,width:0,height:0});
$(w).removeData("dragBox_first");
$(w).removeData("rubberBand")
};
this.drawTerminal=function(w,z){var A=v(OG.Util.isElement(w)?w.id:w),I=A?A.node.shape.createTerminal():null,C=A?A.node.shape.geom.getBoundary():null,H,G,E,F,D,J=OG.Constants.TERMINAL_SIZE,B=J*2;
if(A&&I&&I.length>0){H=v(A.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
E=v(A.id+OG.Constants.TERMINAL_SUFFIX.BOX);
if(H||E){return{bBox:E.node,terminal:H.node}
}H=p.group();
E=p.rect(C.getUpperLeft().x-B,C.getUpperLeft().y-B,C.getWidth()+B*2,C.getHeight()+B*2);
E.attr(OG.Constants.DEFAULT_STYLE.TERMINAL_BBOX);
o(E,A.id+OG.Constants.TERMINAL_SUFFIX.BOX);
$.each(I,function(x,y){if(!z||y.inout.indexOf(z)>=0){F=y.position.x;
D=y.position.y;
G=p.circle(F,D,J);
G.attr(OG.Constants.DEFAULT_STYLE.TERMINAL);
G.node.terminal=y;
H.appendChild(G);
o(G,A.id+OG.Constants.TERMINAL_SUFFIX.GROUP+"_"+y.direction+"_"+y.inout+"_"+x)
}});
o(H,A.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
E.insertBefore(A);
H.insertAfter(A);
return{bBox:E.node,terminal:H.node}
}return null
};
this.removeTerminal=function(x){var w=v(OG.Util.isElement(x)?x.id:x),z,y;
if(w){z=v(w.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
if(z){u(z)
}y=v(w.id+OG.Constants.TERMINAL_SUFFIX.BOX);
if(y){u(y)
}}};
this.removeAllTerminal=function(){var w=this;
$.each(s.keys(),function(x,y){w.removeTerminal(y)
})
};
this.drawCollapseGuide=function(x){var A=v(OG.Util.isElement(x)?x.id:x),C=A?A.node.shape.geom:null,B,E,w,z,D=OG.Constants.COLLAPSE_SIZE,y=D/2;
if(A&&C&&$(x).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){w=v(A.id+OG.Constants.COLLAPSE_BBOX_SUFFIX);
if(w){u(w)
}z=v(A.id+OG.Constants.COLLAPSE_SUFFIX);
if(z){u(z)
}B=C.getBoundary();
E=B.getUpperLeft();
w=p.rect(B.getUpperLeft().x-D,B.getUpperLeft().y-D,B.getWidth()+D*2,B.getHeight()+D*2);
w.attr(OG.Constants.DEFAULT_STYLE.COLLAPSE_BBOX);
o(w,A.id+OG.Constants.COLLAPSE_BBOX_SUFFIX);
if(A.node.shape.isCollapsed===true){z=p.path("M"+(E.x+y)+" "+(E.y+y)+"h"+D+"v"+D+"h"+(-1*D)+"v"+(-1*D)+"m1 "+y+"h"+(D-2)+"M"+(E.x+y)+" "+(E.y+y)+"m"+y+" 1v"+(D-2))
}else{z=p.path("M"+(E.x+y)+" "+(E.y+y)+"h"+D+"v"+D+"h"+(-1*D)+"v"+(-1*D)+"m1 "+y+"h"+(D-2))
}z.attr(OG.Constants.DEFAULT_STYLE.COLLAPSE);
o(z,A.id+OG.Constants.COLLAPSE_SUFFIX);
w.insertBefore(A);
z.insertAfter(A);
return{bBox:w.node,collapse:z.node}
}return null
};
this.removeCollapseGuide=function(y){var w=v(OG.Util.isElement(y)?y.id:y),z,x;
if(w){z=v(w.id+OG.Constants.COLLAPSE_BBOX_SUFFIX);
if(z){u(z)
}x=v(w.id+OG.Constants.COLLAPSE_SUFFIX);
if(x){u(x)
}}};
this.group=function(w){var y,x=[],C,D,A,B,E,z;
if(w&&w.length>1){for(z=0;
z<w.length;
z++){x.push(w[z].shape.geom)
}C=new OG.GeometryCollection(x);
D=C.getBoundary();
A=[D.getCentroid().x,D.getCentroid().y];
B=new OG.GroupShape();
E=[D.getWidth(),D.getHeight()];
y=this.drawShape(A,B,E);
for(z=0;
z<w.length;
z++){y.appendChild(w[z])
}}return y
};
this.ungroup=function(z){var A=[],y,x,w;
if(z&&z.length>0){for(x=0;
x<z.length;
x++){y=$(z[x]).children("[_type='"+OG.Constants.NODE_TYPE.SHAPE+"']");
for(w=0;
w<y.length;
w++){z[x].parentNode.appendChild(y[w]);
A.push(y[w])
}this.removeShape(z[x])
}}return A
};
this.addToGroup=function(x,y){var w;
for(w=0;
w<y.length;
w++){x.appendChild(y[w])
}};
this.collapse=function(y){var z=this,A,x,w;
w=function(J,E){var K,I,G,C=E.childNodes,B,H,F,D;
for(H=C.length-1;
H>=0;
H--){if($(C[H]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){w(J,C[H]);
D=false;
K=$(C[H]).attr("_fromedge");
if(K){K=K.split(",");
for(F=0;
F<K.length;
F++){I=z.getElementById(K[F]);
if(I){B=h($(I).attr("_from"));
if($(B).parents("#"+J.id).length!==0){z.hide(I)
}else{D=true
}}}}K=$(C[H]).attr("_toedge");
if(K){K=K.split(",");
for(F=0;
F<K.length;
F++){G=z.getElementById(K[F]);
if(G){B=h($(G).attr("_to"));
if($(B).parents("#"+J.id).length!==0){z.hide(G)
}else{D=true
}}}}if(D===true){z.redrawConnectedEdge(C[H])
}}}};
if(y.shape){A=y.childNodes;
for(x=A.length-1;
x>=0;
x--){if($(A[x]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.hide(A[x])
}}y.shape.isCollapsed=true;
$(y).attr("_collapsed",true);
w(y,y);
this.redrawShape(y)
}};
this.expand=function(y){var z=this,A,x,w;
w=function(J,E){var K,I,G,C=E.childNodes,B,H,F,D;
for(H=C.length-1;
H>=0;
H--){if($(C[H]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){w(J,C[H]);
D=false;
K=$(C[H]).attr("_fromedge");
if(K){K=K.split(",");
for(F=0;
F<K.length;
F++){I=z.getElementById(K[F]);
if(I){B=h($(I).attr("_from"));
if($(B).parents("#"+J.id).length!==0){z.show(I)
}else{D=true
}}}}K=$(C[H]).attr("_toedge");
if(K){K=K.split(",");
for(F=0;
F<K.length;
F++){G=z.getElementById(K[F]);
if(G){B=h($(G).attr("_to"));
if($(B).parents("#"+J.id).length!==0){z.show(G)
}else{D=true
}}}}if(D===true){z.redrawConnectedEdge(C[H])
}}}};
if(y.shape){A=y.childNodes;
for(x=A.length-1;
x>=0;
x--){if($(A[x]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.show(A[x])
}}y.shape.isCollapsed=false;
$(y).attr("_collapsed",false);
w(y,y);
this.redrawShape(y)
}};
this.clear=function(){p.clear();
s.clear();
j=o(p.group(),null,OG.Constants.NODE_TYPE.ROOT)
};
this.removeShape=function(y){var w=v(OG.Util.isElement(y)?y.id:y),z,x;
z=w.node.childNodes;
for(x=z.length-1;
x>=0;
x--){if($(z[x]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.removeShape(z[x])
}}$(p.canvas).trigger("removeShape",[w.node]);
this.disconnect(w.node);
this.removeTerminal(w.node);
this.removeGuide(w.node);
this.removeCollapseGuide(w.node);
this.remove(w.node)
};
this.remove=function(x){var y=OG.Util.isElement(x)?x.id:x,w=v(y);
u(w)
};
this.removeChild=function(x){var y=OG.Util.isElement(x)?x.id:x,w=v(y);
g(w)
};
this.getRootElement=function(){return p.canvas
};
this.getRootGroup=function(){return j.node
};
this.getElementByPoint=function(w){var x=p.getElementByPoint(w[0],w[1]);
return x?x.node.parentNode:null
};
this.getElementsByBBox=function(x){var w=[];
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(y,z){if(z.shape.geom&&x.isContainsAll(z.shape.geom.getVertices())){w.push(z)
}});
return w
};
this.setAttr=function(x,y){var w=v(OG.Util.isElement(x)?x.id:x);
if(w){w.attr(y)
}};
this.getAttr=function(y,x){var w=v(OG.Util.isElement(y)?y.id:y);
if(w){return w.attr(x)
}return null
};
this.setShapeStyle=function(x,y){var w=v(OG.Util.isElement(x)?x.id:x);
if(w&&x.shape&&x.shape.geom){OG.Util.apply(x.shape.geom.style.map,y||{});
x.shapeStyle=x.shapeStyle||{};
OG.Util.apply(x.shapeStyle,y||{});
this.redrawShape(x)
}};
this.toFront=function(x){var w=v(OG.Util.isElement(x)?x.id:x);
if(w){w.toFront()
}};
this.toBack=function(x){var w=v(OG.Util.isElement(x)?x.id:x);
if(w){w.toBack()
}};
this.setCanvasSize=function(w){p.setSize(w[0],w[1])
};
this.setViewBox=function(w,x,y){p.setViewBox(w[0],w[1],x[0],x[1],y)
};
this.show=function(x){var w=v(OG.Util.isElement(x)?x.id:x);
if(w){w.show()
}};
this.hide=function(x){var w=v(OG.Util.isElement(x)?x.id:x);
if(w){w.hide()
}};
this.appendChild=function(w,x){var y=v(OG.Util.isElement(w)?w.id:w),z=v(OG.Util.isElement(x)?x.id:x);
z.appendChild(y);
return y
};
this.insertAfter=function(w,x){var y=v(OG.Util.isElement(w)?w.id:w),z=v(OG.Util.isElement(x)?x.id:x);
y.insertAfter(z);
return y
};
this.insertBefore=function(w,x){var y=v(OG.Util.isElement(w)?w.id:w),z=v(OG.Util.isElement(x)?x.id:x);
y.insertBefore(z);
return y
};
this.move=function(x,C,A){var w=v(OG.Util.isElement(x)?x.id:x),y=w?w.node.getAttribute("_shape"):null,B=w?w.node.shape.geom:null,z=this;
this.removeCollapseGuide(x);
if(w&&y&&B){$(w.node).children("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(D,E){z.move(E,C,A)
});
B.move(C[0],C[1]);
$(p.canvas).trigger("moveShape",[w.node,C]);
return this.redrawShape(w.node,A)
}else{if(w){w.transform("...t"+C[0]+","+C[1]);
$(p.canvas).trigger("moveShape",[w.node,C]);
return w.node
}}return null
};
this.moveCentroid=function(z,x,B){var y=v(OG.Util.isElement(z)?z.id:z),C=y?y.node.shape.geom:null,A,w={};
if(y&&C){w=C.getCentroid();
return this.move(z,[x[0]-w.x,x[1]-w.y],B)
}else{if(y){A=y.getBBox();
w.x=A.x+OG.Util.round(A.width/2);
w.y=A.y+OG.Util.round(A.height/2);
return this.move(z,[x[0]-w.x,x[1]-w.y])
}}this.removeCollapseGuide(z);
return null
};
this.rotate=function(x,B,z){var w=v(OG.Util.isElement(x)?x.id:x),y=w?w.node.getAttribute("_shape"):null,A=w?w.node.shape.geom:null;
if(w&&y&&A){A.rotate(B);
return this.redrawShape(w.node,z)
}else{if(w){w.rotate(B);
return w.node
}}return null
};
this.resize=function(z,x,F){var B=v(OG.Util.isElement(z)?z.id:z),D=B?B.node.getAttribute("_shape"):null,E=B?B.node.shape.geom:null,I,A,y,w,H,G,C;
this.removeCollapseGuide(z);
if(B&&D&&E){E.resize(x[0],x[1],x[2],x[3]);
$(p.canvas).trigger("resizeShape",[B.node,x]);
return this.redrawShape(B.node,F)
}else{if(B){I=B.getBBox();
A=x[2]+x[3];
y=x[0]+x[1];
w=I.width+A;
H=I.height+y;
G=I.width===0?1:w/I.width;
C=I.height===0?1:H/I.height;
B.transform("...t"+(-1*x[2])+","+(-1*x[0]));
B.transform("...s"+G+","+C+","+I.x+","+I.y);
$(p.canvas).trigger("resizeShape",[B.node,x]);
return B.node
}}return null
};
this.resizeBox=function(z,y){var x=v(OG.Util.isElement(z)?z.id:z),C=x?x.node.shape.geom:null,D,B,A,w;
this.removeCollapseGuide(z);
if(x&&C){D=C.getBoundary();
A=OG.Util.round((y[0]-D.getWidth())/2);
w=OG.Util.round((y[1]-D.getHeight())/2);
return this.resize(z,[w,w,A,A])
}else{if(x){B=x.getBBox();
A=OG.Util.round((y[0]-B.width)/2);
w=OG.Util.round((y[1]-B.height)/2);
return this.resize(z,[w,w,A,A])
}}return null
};
this.intersectionEdge=function(M,B,G,H,z){var D,C,F,J,A,I=Number.MAX_VALUE,w,y,x,E,L,K;
if(B){y=$(B).parents("[_collapsed=true]");
if(y.length!==0){x=y[y.length-1].shape.geom.getBoundary();
E=x.getUpperLeft();
L=new OG.geometry.Rectangle(E,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2)
}}switch(M){case OG.Constants.EDGE_TYPE.PLAIN:D=z?l(B,G,H):b(B,G,H);
C=[D.terminal.position.x,D.terminal.position.y];
F=D.terminal.direction.toLowerCase();
if(L){switch(D.terminal.direction){case OG.Constants.TERMINAL_TYPE.E:K=L.getBoundary().getRightCenter();
break;
case OG.Constants.TERMINAL_TYPE.W:K=L.getBoundary().getLeftCenter();
break;
case OG.Constants.TERMINAL_TYPE.S:K=L.getBoundary().getLowerCenter();
break;
case OG.Constants.TERMINAL_TYPE.N:K=L.getBoundary().getUpperCenter();
break
}if(K){C=[K.x,K.y]
}}break;
case OG.Constants.EDGE_TYPE.STRAIGHT:if(L){K=L.getBoundary().getCentroid();
if(z===true){G=[K.x,K.y]
}else{H=[K.x,K.y]
}J=L.intersectToLine([G,H])
}else{J=B.shape.geom.intersectToLine([G,H])
}C=z?G:H;
F="c";
for(A=0;
A<J.length;
A++){w=J[A].distance(z?H:G);
if(w<I){I=w;
C=[J[A].x,J[A].y];
F="c"
}}break;
default:break
}return{position:C,direction:F}
};
this.clone=function(x){var w=v(OG.Util.isElement(x)?x.id:x),y;
y=w.clone();
o(y);
return y.node
};
this.getElementById=function(x){var w=v(x);
return w?w.node:null
};
this.getBBox=function(x){var w=v(OG.Util.isElement(x)?x.id:x);
return w.getBBox()
};
this.getRootBBox=function(){var A=p.canvas.parentNode,B=OG.Util.isFirefox()?p.canvas.width.baseVal.value:p.canvas.scrollWidth,z=OG.Util.isFirefox()?p.canvas.height.baseVal.value:p.canvas.scrollHeight,w=A.offsetLeft,C=A.offsetTop;
return{width:B,height:z,x:w,y:C,x2:w+B,y2:C+z}
};
this.getContainer=function(){return p.canvas.parentNode
};
this.isSVG=function(){return Raphael.svg
};
this.isVML=function(){return Raphael.vml
}
};
OG.renderer.RaphaelRenderer.prototype=new OG.renderer.IRenderer();
OG.renderer.RaphaelRenderer.prototype.constructor=OG.renderer.RaphaelRenderer;
OG.RaphaelRenderer=OG.renderer.RaphaelRenderer;OG.handler.EventHandler=function(F){var y=this,h=F,j,s,H,x,e,Q=function(U){return parseInt(U,10)
},J=function(V,U){return OG.Constants.DRAG_GRIDABLE?OG.Util.roundGrid(V,U):V
},i=function(U){var V=OG.Util.isElement(U)?U.id:U;
return h.getElementById(V.substring(0,V.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)))
},M=function(U,ac){var aa,Y,ab,V,Z=false,X=false,W;
aa=$(U).attr("_from");
Y=$(U).attr("_to");
if(aa){ab=i(aa)
}if(Y){V=i(Y)
}for(W=0;
W<ac.length;
W++){if(ab&&ac[W].id===ab.id){Z=true
}if(V&&ac[W].id===V.id){X=true
}}return{none:!Z&&!X,all:Z&&X,any:Z||X,either:(Z&&!X)||(!Z&&X),attrEither:(aa&&!Y)||(!aa&&Y)}
},m=function(V){var U=h.getContainer();
return{x:V.pageX-$(U).offset().left+U.scrollLeft,y:V.pageY-$(U).offset().top+U.scrollTop}
},N=function(){var W=[],V,U=[];
$("[id$="+OG.Constants.GUIDE_SUFFIX.BBOX+"]").each(function(X,Y){if(Y.id){V=h.clone(Y);
h.setAttr(V,OG.Constants.DEFAULT_STYLE.GUIDE_SHADOW);
W.push({id:Y.id.replace(OG.Constants.GUIDE_SUFFIX.BBOX,""),box:V})
}});
$.each(W,function(Y,Z){var aa=h.getElementById(Z.id),X;
if($(aa).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){X=M(aa,W);
if(X.all||X.none||(X.either&&X.attrEither)){U.push(Z)
}else{h.remove(Z.box);
h.removeGuide(aa)
}}});
$.each(W,function(X,Y){var Z=h.getElementById(Y.id);
if($(Z).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){U.push(Y)
}});
return U
},o=function(Y,V,U){var X=[],W=[];
$.each(Y,function(Z,aa){var ab=h.getElementById(aa.id);
if($(ab).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){X.push(aa.id)
}});
$.each(Y,function(Z,aa){var ab=h.getElementById(aa.id);
h.remove(aa.box);
h.move(ab,[V,U],X);
h.drawGuide(ab);
if($(ab).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(M(ab,Y).none){h.disconnect(ab)
}}W.push(ab)
});
return W
},I=function(){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape=EDGE][_selected=true]").each(function(U,V){if(V.id){h.removeShape(V)
}});
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(U,V){if(V.id){h.removeShape(V)
}})
},P=function(){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(V,W){if($(W.parentNode).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){var U=h.drawGuide(W);
if(U){j(W,U,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.removeTerminal(W)
}}})
},w=function(){var U=h.getRootGroup(),V=[];
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(W,X){V.push(X)
});
$(U).data("copied",V)
},l=function(){w();
I()
},C=function(){var V=h.getRootGroup(),U=$(V).data("copied"),W=[];
if(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(X,Y){if(Y.id){h.removeGuide(Y)
}});
$.each(U,function(X,aa){var ac=aa.shape.geom.getBoundary(),Z,ab,Y;
Z=aa.shape.clone();
if($(aa).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){Z.geom=new OG.PolyLine(aa.shape.geom.getVertices());
Z.geom.style=aa.shape.geom.style;
Z.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
ab=h.drawShape(null,Z,null,aa.shapeStyle)
}else{ab=h.drawShape([ac.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,ac.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],Z,[ac.getWidth(),ac.getHeight()],aa.shapeStyle)
}ab.data=aa.data;
Y=h.drawGuide(ab);
y.setClickSelectable(ab,OG.Constants.SELECTABLE);
y.setMovable(ab,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
j(ab,Y,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if(OG.Constants.GROUP_DROPABLE){y.enableDragAndDropGroup(ab)
}if(OG.Constants.GROUP_COLLAPSIBLE){y.enableCollapse(ab)
}if($(ab).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){y.enableConnect(ab)
}if(OG.Constants.LABEL_EDITABLE){y.enableEditLabel(ab)
}}x(aa,ab);
W.push(ab)
});
$(V).data("copied",W)
}},B=function(){w();
C()
},O=function(){var V=h.group($("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]")),U;
if(V){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(W,X){h.removeGuide(X)
});
U=h.drawGuide(V);
if(U){y.setClickSelectable(V,OG.Constants.SELECTABLE);
y.setMovable(V,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
j(V,U,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if(OG.Constants.GROUP_DROPABLE){y.enableDragAndDropGroup(V)
}h.removeAllTerminal();
h.toFront(U.group)
}}},n=function(){var V=h.ungroup($("[_shape="+OG.Constants.SHAPE_TYPE.GROUP+"][_selected=true]")),U;
$.each(V,function(W,X){U=h.drawGuide(X);
if(U){h.removeAllTerminal();
h.toFront(U.group)
}})
},E=function(V){var U;
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape="+OG.Constants.SHAPE_TYPE.EDGE+"][_selected=true]").each(function(W,X){h.removeGuide(X)
});
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(W,X){if(X.shape&&X.shape.TYPE!==OG.Constants.SHAPE_TYPE.EDGE){h.rotate(X,V);
h.removeGuide(X);
U=h.drawGuide(X);
j(X,U,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.toFront(U.group)
}})
},q=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"stroke-width":U})
})
},k=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{stroke:U})
})
},z=function(V){var U;
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape="+OG.Constants.SHAPE_TYPE.EDGE+"][_selected=true]").each(function(W,X){OG.Util.apply(X.shape.geom.style.map,{"edge-type":V});
X.shapeStyle=X.shapeStyle||{};
OG.Util.apply(X.shapeStyle,{"edge-type":V});
h.redrawEdge(X);
h.removeGuide(X);
U=h.drawGuide(X);
j(X,U,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.toFront(U.group)
})
},r=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"stroke-dasharray":U})
})
},A=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"arrow-start":U})
})
},K=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"arrow-end":U})
})
},g=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{fill:U,"fill-opacity":1})
})
},u=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"fill-opacity":U})
})
},D=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"font-family":U})
})
},d=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"font-size":U})
})
},L=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"font-color":U})
})
},b=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"font-weight":U})
})
},S=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"font-style":U})
})
},a=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"text-decoration":U})
})
},T=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"label-direction":U})
})
},c=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"label-angle":U})
})
},R=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"label-position":U})
})
},f=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"vertical-align":U})
})
},t=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.setShapeStyle(W,{"text-anchor":U})
})
},v=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(V,W){h.drawLabel(W,U)
})
},p=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape="+OG.Constants.SHAPE_TYPE.EDGE+"][_selected=true]").each(function(V,W){h.drawEdgeLabel(W,U,"FROM")
})
},G=function(U){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape="+OG.Constants.SHAPE_TYPE.EDGE+"][_selected=true]").each(function(V,W){h.drawEdgeLabel(W,U,"TO")
})
};
j=function(W,U,X){var V=h.getRootGroup();
if(!W||!U){return
}if(X){if(($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.GEOM&&OG.Constants.RESIZABLE_GEOM)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT&&OG.Constants.RESIZABLE_TEXT)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.HTML&&OG.Constants.RESIZABLE_HTML)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.IMAGE&&OG.Constants.RESIZABLE_IMAGE)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE&&OG.Constants.RESIZABLE_EDGE)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP&&OG.Constants.RESIZABLE_GROUP)){if($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){$(U.from).draggable({start:function(ad){var ab=W.shape.geom.getVertices(),Y={},Z=$(W).attr("_to"),ae,aa=[ab[ab.length-1].x,ab[ab.length-1].y],ac=h.drawEdge(new OG.PolyLine(ab),OG.Util.apply(Y,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,W.shape.geom.style.map));
if(Z){ae=i(Z);
h.drawTerminal(ae);
aa=h.getElementById(Z)
}$(V).data("to_terminal",aa);
$(V).data("edge",ac);
$(V).data("dragged_guide","from");
h.removeRubberBand(h.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(ag,af){if(af.id&&$(af).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){h.removeGuide(af)
}})
},drag:function(Y){var ac=m(Y),aa=$(V).data("edge"),ah=$(V).data("edge_terminal"),am=$(V).data("to_terminal"),ai=ah?[ah.terminal.position.x,ah.terminal.position.y]:[ac.x,ac.y],ae=OG.Util.isElement(am)?[am.terminal.position.x,am.terminal.position.y]:am,aj=ah?ah.terminal.direction.toLowerCase():"c",af=OG.Util.isElement(am)?am.terminal.direction.toLowerCase():"c",ak=ah?i(ah):null,ad=OG.Util.isElement(am)?i(am):null,Z,al,ab,ag;
$(this).css({position:"",left:"",top:""});
Z=ai;
al=ae;
if(ak&&aj==="c"){ab=h.intersectionEdge(aa.geom.style.get("edge-type"),ak,[Z[0],Z[1]],[al[0],al[1]],true);
ai=ab.position;
aj=ab.direction
}if(ad&&af==="c"){ab=h.intersectionEdge(aa.geom.style.get("edge-type"),ad,[Z[0],Z[1]],[al[0],al[1]],false);
ae=ab.position;
af=ab.direction
}ag=ak&&ad&&ak.id===ad.id;
if(ag){ai=ae=ak.shape.geom.getBoundary().getRightCenter()
}if(!ag||OG.Constants.SELF_CONNECTABLE){h.drawEdge(new OG.Line(ai,ae),OG.Util.apply(aa.geom.style.map,{"edge-direction":aj+" "+af}),aa.id,ag)
}},stop:function(ac){var af=m(ac),ae=$(V).data("edge_terminal")||[af.x,af.y],Y=$(V).data("to_terminal"),aa=OG.Util.isElement(ae)?i(ae):null,ad=OG.Util.isElement(Y)?i(Y):null,ab=$(V).data("edge"),Z;
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(V).removeData("to_terminal");
$(V).removeData("edge");
$(V).removeData("edge_terminal");
$(V).removeData("dragged_guide");
h.remove(ab);
h.removeGuide(W);
if(aa){h.remove(aa.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}Z=aa&&ad&&aa.id===ad.id;
if(!Z||OG.Constants.SELF_CONNECTABLE){W=h.connect(ae,Y,W,W.shape.geom.style);
if(W){U=h.drawGuide(W);
if(W&&U){j(W,U,true);
h.toFront(U.group)
}}}}});
$(U.to).draggable({start:function(ad){var aa=W.shape.geom.getVertices(),Z={},Y=$(W).attr("_from"),ab,ae=[aa[0].x,aa[0].y],ac=h.drawEdge(new OG.PolyLine(aa),OG.Util.apply(Z,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,W.shape.geom.style.map));
if(Y){ab=i(Y);
h.drawTerminal(ab);
ae=h.getElementById(Y)
}$(V).data("from_terminal",ae);
$(V).data("edge",ac);
$(V).data("dragged_guide","to");
h.removeRubberBand(h.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(ag,af){if(af.id&&$(af).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){h.removeGuide(af)
}})
},drag:function(Y){var ac=m(Y),aa=$(V).data("edge"),ah=$(V).data("from_terminal"),am=$(V).data("edge_terminal"),ai=OG.Util.isElement(ah)?[ah.terminal.position.x,ah.terminal.position.y]:ah,ae=am?[am.terminal.position.x,am.terminal.position.y]:[ac.x,ac.y],aj=OG.Util.isElement(ah)?ah.terminal.direction.toLowerCase():"c",af=am?am.terminal.direction.toLowerCase():"c",ak=OG.Util.isElement(ah)?i(ah):null,ad=am?i(am):null,Z,al,ab,ag;
$(this).css({position:"",left:"",top:""});
Z=ai;
al=ae;
if(ak&&aj==="c"){ab=h.intersectionEdge(aa.geom.style.get("edge-type"),ak,[Z[0],Z[1]],[al[0],al[1]],true);
ai=ab.position;
aj=ab.direction
}if(ad&&af==="c"){ab=h.intersectionEdge(aa.geom.style.get("edge-type"),ad,[Z[0],Z[1]],[al[0],al[1]],false);
ae=ab.position;
af=ab.direction
}ag=(ak!==null)&&(ad!==null)&&ak.id===ad.id;
if(ag){ai=ae=ad.shape.geom.getBoundary().getRightCenter()
}if(!ag||OG.Constants.SELF_CONNECTABLE){h.drawEdge(new OG.Line(ai,ae),OG.Util.apply(aa.geom.style.map,{"edge-direction":aj+" "+af}),aa.id,ag)
}},stop:function(ac){var af=m(ac),ae=$(V).data("from_terminal"),Y=$(V).data("edge_terminal")||[af.x,af.y],aa=OG.Util.isElement(ae)?i(ae):null,ad=OG.Util.isElement(Y)?i(Y):null,ab=$(V).data("edge"),Z;
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(V).removeData("from_terminal");
$(V).removeData("edge");
$(V).removeData("edge_terminal");
$(V).removeData("dragged_guide");
h.remove(ab);
h.removeGuide(W);
if(ad){h.remove(ad.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}Z=aa&&ad&&aa.id===ad.id;
if(!Z||OG.Constants.SELF_CONNECTABLE){W=h.connect(ae,Y,W,W.shape.geom.style);
if(W){U=h.drawGuide(W);
if(U){j(W,U,true);
h.toFront(U.group)
}}}}});
$.each(U.controls,function(Y,Z){$(Z).draggable({start:function(aa){var ab=m(aa);
$(this).data("start",{x:ab.x,y:ab.y});
$(this).data("offset",{x:ab.x-Q(h.getAttr(Z,"x")),y:ab.y-Q(h.getAttr(Z,"y"))});
h.remove(U.bBox);
h.removeRubberBand(h.getRootElement())
},drag:function(ab){var ad=m(ab),ac=$(this).data("start"),ae=$(this).data("offset"),aa=ad.x-ae.x,ai=ad.y-ae.y,ag=W.shape.geom.getVertices(),ah=Z.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,af=ah?parseInt(Z.id.replace(W.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(Z.id.replace(W.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"",left:"",top:""});
if(ah){ag[af].x=aa;
ag[af+1].x=aa
}else{ag[af].y=ai;
ag[af+1].y=ai
}W=h.drawEdge(new OG.PolyLine(ag),W.shape.geom.style,W.id);
h.drawGuide(W);
h.removeAllTerminal();
h.drawLabel(W);
h.drawEdgeLabel(W,null,"FROM");
h.drawEdgeLabel(W,null,"TO")
},stop:function(ab){var ad=m(ab),ac=$(this).data("start"),ae=$(this).data("offset"),aa=ad.x-ae.x,ai=ad.y-ae.y,ag=W.shape.geom.getVertices(),ah=Z.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,af=ah?parseInt(Z.id.replace(W.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(Z.id.replace(W.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(ah){ag[af].x=aa;
ag[af+1].x=aa
}else{ag[af].y=ai;
ag[af+1].y=ai
}W=h.drawEdge(new OG.PolyLine(ag),W.shape.geom.style,W.id);
h.drawGuide(W);
h.drawLabel(W);
h.drawEdgeLabel(W,null,"FROM");
h.drawEdgeLabel(W,null,"TO")
}})
})
}else{$(U.rc).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.rc,"x")),y:Z.y-Q(h.getAttr(U.rc,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(Z){var aa=m(Z),ad=$(this).data("start"),ac=$(this).data("offset"),ab=J(aa.x-ac.x),Y=J(ab-Q(h.getAttr(U.lc,"x")));
$(this).css({position:"",left:"",top:""});
if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.rc,{x:ab});
h.setAttr(U.ur,{x:ab});
h.setAttr(U.lr,{x:ab});
h.setAttr(U.uc,{x:OG.Util.round((Q(h.getAttr(U.lc,"x"))+ab)/2)});
h.setAttr(U.lwc,{x:OG.Util.round((Q(h.getAttr(U.lc,"x"))+ab)/2)});
h.setAttr(U.bBox,{width:Y})
}h.removeAllTerminal()
},stop:function(Z){var aa=m(Z),ab=$(this).data("start"),Y=aa.x-ab.x;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getWidth()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getWidth()
}h.resize(W,[0,0,0,J(Y)]);
h.drawGuide(W)
}}});
$(U.lwc).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.lwc,"x")),y:Z.y-Q(h.getAttr(U.lwc,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(Z){var aa=m(Z),ad=$(this).data("start"),ac=$(this).data("offset"),ab=J(aa.y-ac.y),Y=J(ab-Q(h.getAttr(U.uc,"y")));
$(this).css({position:"",left:"",top:""});
if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.lwc,{y:ab});
h.setAttr(U.ll,{y:ab});
h.setAttr(U.lr,{y:ab});
h.setAttr(U.lc,{y:OG.Util.round((Q(h.getAttr(U.uc,"y"))+ab)/2)});
h.setAttr(U.rc,{y:OG.Util.round((Q(h.getAttr(U.uc,"y"))+ab)/2)});
h.setAttr(U.bBox,{height:Y})
}h.removeAllTerminal()
},stop:function(Z){var aa=m(Z),ab=$(this).data("start"),Y=aa.y-ab.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getHeight()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getHeight()
}h.resize(W,[0,J(Y),0,0]);
h.drawGuide(W)
}}});
$(U.lr).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.lr,"x")),y:Z.y-Q(h.getAttr(U.lr,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(aa){var ab=m(aa),af=$(this).data("start"),ae=$(this).data("offset"),ad=J(ab.x-ae.x),Z=J(ad-Q(h.getAttr(U.lc,"x"))),ac=J(ab.y-ae.y),Y=J(ac-Q(h.getAttr(U.uc,"y")));
$(this).css({position:"",left:"",top:""});
if(Z>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.rc,{x:ad});
h.setAttr(U.ur,{x:ad});
h.setAttr(U.lr,{x:ad});
h.setAttr(U.uc,{x:OG.Util.round((Q(h.getAttr(U.lc,"x"))+ad)/2)});
h.setAttr(U.lwc,{x:OG.Util.round((Q(h.getAttr(U.lc,"x"))+ad)/2)});
h.setAttr(U.bBox,{width:Z})
}if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.lwc,{y:ac});
h.setAttr(U.ll,{y:ac});
h.setAttr(U.lr,{y:ac});
h.setAttr(U.lc,{y:OG.Util.round((Q(h.getAttr(U.uc,"y"))+ac)/2)});
h.setAttr(U.rc,{y:OG.Util.round((Q(h.getAttr(U.uc,"y"))+ac)/2)});
h.setAttr(U.bBox,{height:Y})
}h.removeAllTerminal()
},stop:function(aa){var ab=m(aa),ac=$(this).data("start"),Z=ab.x-ac.x,Y=ab.y-ac.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getWidth()+Z<OG.Constants.GUIDE_MIN_SIZE){Z=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getWidth()
}if(W.shape.geom.getBoundary().getHeight()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getHeight()
}h.resize(W,[0,J(Y),0,J(Z)]);
h.drawGuide(W)
}h.removeAllTerminal()
}});
$(U.lc).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.lc,"x")),y:Z.y-Q(h.getAttr(U.lc,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(Z){var aa=m(Z),ad=$(this).data("start"),ac=$(this).data("offset"),ab=J(aa.x-ac.x),Y=J(Q(h.getAttr(U.rc,"x"))-ab);
$(this).css({position:"",left:"",top:""});
if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.lc,{x:ab});
h.setAttr(U.ul,{x:ab});
h.setAttr(U.ll,{x:ab});
h.setAttr(U.uc,{x:OG.Util.round((Q(h.getAttr(U.rc,"x"))+ab)/2)});
h.setAttr(U.lwc,{x:OG.Util.round((Q(h.getAttr(U.rc,"x"))+ab)/2)});
h.setAttr(U.bBox,{x:OG.Util.round(ab+Q(h.getAttr(U.lc,"width"))/2),width:Y})
}h.removeAllTerminal()
},stop:function(Z){var aa=m(Z),ab=$(this).data("start"),Y=ab.x-aa.x;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getWidth()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getWidth()
}h.resize(W,[0,0,J(Y),0]);
h.drawGuide(W)
}}});
$(U.ll).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.ll,"x")),y:Z.y-Q(h.getAttr(U.ll,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(aa){var ab=m(aa),af=$(this).data("start"),ae=$(this).data("offset"),ad=J(ab.x-ae.x),ac=J(ab.y-ae.y),Z=J(Q(h.getAttr(U.rc,"x"))-ad),Y=J(ac-Q(h.getAttr(U.uc,"y")));
$(this).css({position:"",left:"",top:""});
if(Z>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.lc,{x:ad});
h.setAttr(U.ul,{x:ad});
h.setAttr(U.ll,{x:ad});
h.setAttr(U.uc,{x:OG.Util.round((Q(h.getAttr(U.rc,"x"))+ad)/2)});
h.setAttr(U.lwc,{x:OG.Util.round((Q(h.getAttr(U.rc,"x"))+ad)/2)});
h.setAttr(U.bBox,{x:OG.Util.round(ad+Q(h.getAttr(U.lc,"width"))/2),width:Z})
}if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.lwc,{y:ac});
h.setAttr(U.ll,{y:ac});
h.setAttr(U.lr,{y:ac});
h.setAttr(U.lc,{y:OG.Util.round((Q(h.getAttr(U.uc,"y"))+ac)/2)});
h.setAttr(U.rc,{y:OG.Util.round((Q(h.getAttr(U.uc,"y"))+ac)/2)});
h.setAttr(U.bBox,{height:Y})
}h.removeAllTerminal()
},stop:function(aa){var ab=m(aa),ac=$(this).data("start"),Z=ac.x-ab.x,Y=ab.y-ac.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getWidth()+Z<OG.Constants.GUIDE_MIN_SIZE){Z=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getWidth()
}if(W.shape.geom.getBoundary().getHeight()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getHeight()
}h.resize(W,[0,J(Y),J(Z),0]);
h.drawGuide(W)
}}});
$(U.uc).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.uc,"x")),y:Z.y-Q(h.getAttr(U.uc,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(Z){var aa=m(Z),ad=$(this).data("start"),ac=$(this).data("offset"),ab=J(aa.y-ac.y),Y=J(Q(h.getAttr(U.lwc,"y"))-ab);
$(this).css({position:"",left:"",top:""});
if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.uc,{y:ab});
h.setAttr(U.ul,{y:ab});
h.setAttr(U.ur,{y:ab});
h.setAttr(U.lc,{y:OG.Util.round((Q(h.getAttr(U.lwc,"y"))+ab)/2)});
h.setAttr(U.rc,{y:OG.Util.round((Q(h.getAttr(U.lwc,"y"))+ab)/2)});
h.setAttr(U.bBox,{y:OG.Util.round(ab+Q(h.getAttr(U.uc,"width"))/2),height:Y})
}h.removeAllTerminal()
},stop:function(Z){var aa=m(Z),ab=$(this).data("start"),Y=ab.y-aa.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getHeight()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getHeight()
}h.resize(W,[J(Y),0,0,0]);
h.drawGuide(W)
}}});
$(U.ul).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.ul,"x")),y:Z.y-Q(h.getAttr(U.ul,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(aa){var ab=m(aa),af=$(this).data("start"),ae=$(this).data("offset"),ad=J(ab.x-ae.x),ac=J(ab.y-ae.y),Z=J(Q(h.getAttr(U.rc,"x"))-ad),Y=J(Q(h.getAttr(U.lwc,"y"))-ac);
$(this).css({position:"",left:"",top:""});
if(Z>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.lc,{x:ad});
h.setAttr(U.ul,{x:ad});
h.setAttr(U.ll,{x:ad});
h.setAttr(U.uc,{x:OG.Util.round((Q(h.getAttr(U.rc,"x"))+ad)/2)});
h.setAttr(U.lwc,{x:OG.Util.round((Q(h.getAttr(U.rc,"x"))+ad)/2)});
h.setAttr(U.bBox,{x:OG.Util.round(ad+Q(h.getAttr(U.lc,"width"))/2),width:Z})
}if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.uc,{y:ac});
h.setAttr(U.ul,{y:ac});
h.setAttr(U.ur,{y:ac});
h.setAttr(U.lc,{y:OG.Util.round((Q(h.getAttr(U.lwc,"y"))+ac)/2)});
h.setAttr(U.rc,{y:OG.Util.round((Q(h.getAttr(U.lwc,"y"))+ac)/2)});
h.setAttr(U.bBox,{y:OG.Util.round(ac+Q(h.getAttr(U.uc,"height"))/2),height:Y})
}h.removeAllTerminal()
},stop:function(aa){var ab=m(aa),ac=$(this).data("start"),Z=ac.x-ab.x,Y=ac.y-ab.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getWidth()+Z<OG.Constants.GUIDE_MIN_SIZE){Z=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getWidth()
}if(W.shape.geom.getBoundary().getHeight()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getHeight()
}h.resize(W,[J(Y),0,J(Z),0]);
h.drawGuide(W)
}}});
$(U.ur).draggable({start:function(Y){var Z=m(Y);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(U.ur,"x")),y:Z.y-Q(h.getAttr(U.ur,"y"))});
h.removeRubberBand(h.getRootElement())
},drag:function(aa){var ab=m(aa),af=$(this).data("start"),ae=$(this).data("offset"),ad=J(ab.x-ae.x),ac=J(ab.y-ae.y),Z=J(ad-Q(h.getAttr(U.lc,"x"))),Y=J(Q(h.getAttr(U.lwc,"y"))-ac);
$(this).css({position:"",left:"",top:""});
if(Z>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.rc,{x:ad});
h.setAttr(U.ur,{x:ad});
h.setAttr(U.lr,{x:ad});
h.setAttr(U.uc,{x:OG.Util.round((Q(h.getAttr(U.lc,"x"))+ad)/2)});
h.setAttr(U.lwc,{x:OG.Util.round((Q(h.getAttr(U.lc,"x"))+ad)/2)});
h.setAttr(U.bBox,{width:Z})
}if(Y>=OG.Constants.GUIDE_MIN_SIZE){h.setAttr(U.uc,{y:ac});
h.setAttr(U.ul,{y:ac});
h.setAttr(U.ur,{y:ac});
h.setAttr(U.lc,{y:OG.Util.round((Q(h.getAttr(U.lwc,"y"))+ac)/2)});
h.setAttr(U.rc,{y:OG.Util.round((Q(h.getAttr(U.lwc,"y"))+ac)/2)});
h.setAttr(U.bBox,{y:OG.Util.round(ac+Q(h.getAttr(U.uc,"width"))/2),height:Y})
}h.removeAllTerminal()
},stop:function(aa){var ab=m(aa),ac=$(this).data("start"),Z=ab.x-ac.x,Y=ac.y-ab.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(W&&W.shape.geom){if(W.shape.geom.getBoundary().getWidth()+Z<OG.Constants.GUIDE_MIN_SIZE){Z=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getWidth()
}if(W.shape.geom.getBoundary().getHeight()+Y<OG.Constants.GUIDE_MIN_SIZE){Y=OG.Constants.GUIDE_MIN_SIZE-W.shape.geom.getBoundary().getHeight()
}h.resize(W,[J(Y),0,0,J(Z)]);
h.drawGuide(W)
}}})
}}}else{if($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){h.setAttr(U.from,{cursor:"default"});
h.setAttr(U.to,{cursor:"default"});
$.each(U.controls,function(Y,Z){h.setAttr(Z,{cursor:"default"})
})
}else{h.setAttr(U.ul,{cursor:"default"});
h.setAttr(U.ur,{cursor:"default"});
h.setAttr(U.ll,{cursor:"default"});
h.setAttr(U.lr,{cursor:"default"});
h.setAttr(U.lc,{cursor:"default"});
h.setAttr(U.uc,{cursor:"default"});
h.setAttr(U.rc,{cursor:"default"});
h.setAttr(U.lwc,{cursor:"default"})
}}};
s=function(V){var U=V.parentNode;
if(U){if(s(U)){return true
}if($(U).attr("_type")===OG.Constants.NODE_TYPE.SHAPE&&$(U).attr("_selected")==="true"){return true
}}return false
};
H=function(V){var U=V.childNodes;
$.each(U,function(W,X){if($(X).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){if(X.childNodes.length>0){H(X)
}if($(X).attr("_selected")==="true"){h.removeGuide(X);
$(X).draggable("destroy")
}}})
};
x=function(V,W){var U=V.childNodes;
$.each(U,function(X,aa){if($(aa).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){var ac=aa.shape.geom.getBoundary(),Z,ab,Y;
Z=aa.shape.clone();
if($(aa).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){Z.geom=new OG.PolyLine(aa.shape.geom.getVertices());
Z.geom.style=aa.shape.geom.style;
Z.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
ab=h.drawShape(null,Z,null,aa.shapeStyle)
}else{ab=h.drawShape([ac.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,ac.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],Z,[ac.getWidth(),ac.getHeight()],aa.shapeStyle)
}ab.data=aa.data;
W.appendChild(ab);
y.setClickSelectable(ab,OG.Constants.SELECTABLE);
y.setMovable(ab,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.GROUP_DROPABLE){y.enableDragAndDropGroup(ab)
}if(OG.Constants.GROUP_COLLAPSIBLE){y.enableCollapse(ab)
}if($(ab).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){y.enableConnect(ab)
}if(OG.Constants.LABEL_EDITABLE){y.enableEditLabel(ab)
}}if(aa.childNodes.length>0){x(aa,ab)
}}})
};
e=function(W,V){var U=h.getRootBBox();
if(OG.Constants.AUTO_EXTENSIONAL&&U.width<W){h.setCanvasSize([U.width+OG.Constants.AUTO_EXTENSION_SIZE,U.height])
}if(OG.Constants.AUTO_EXTENSIONAL&&U.height<V){h.setCanvasSize([U.width,U.height+OG.Constants.AUTO_EXTENSION_SIZE])
}};
this.setResizable=j;
this.enableEditLabel=function(U){if(($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.GEOM&&OG.Constants.LABEL_EDITABLE_GEOM)||($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT&&OG.Constants.LABEL_EDITABLE_TEXT)||($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.HTML&&OG.Constants.LABEL_EDITABLE_HTML)||($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.IMAGE&&OG.Constants.LABEL_EDITABLE_IMAGE)||($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE&&OG.Constants.LABEL_EDITABLE_EDGE)||($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP&&OG.Constants.LABEL_EDITABLE_GROUP)){$(U).bind({dblclick:function(Y){var Z=h.getContainer(),af=U.shape.geom.getBoundary(),X=af.getUpperLeft(),aj,ad=X.x-1,ah=X.y-1,ab=af.getWidth(),ai=af.getHeight(),ac=U.id+OG.Constants.LABEL_EDITOR_SUFFIX,W,aa="center",ak,ae,ag=function(ap){var an,al,aq=0,ao,am;
an=ap.shape.geom.getVertices();
al=ap.shape.geom.getLength();
for(ao=0;
ao<an.length-1;
ao++){aq+=an[ao].distance(an[ao+1]);
if(aq>al/2){am=ap.shape.geom.intersectCircleToLine(an[ao+1],aq-al/2,an[ao+1],an[ao]);
break
}}return am[0]
},V;
$(Z).append("<textarea id='"+U.id+OG.Constants.LABEL_EDITOR_SUFFIX+"'></textarea>");
W=$("#"+ac);
switch(U.shape.geom.style.get("text-anchor")){case"start":aa="left";
break;
case"middle":aa="center";
break;
case"end":aa="right";
break;
default:aa="center";
break
}if($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.HTML){$(W).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:ad,top:ah,width:ab,height:ai,"text-align":"left",overflow:"hidden",resize:"none"}));
$(W).focus();
$(W).val(U.shape.html);
$(W).bind({focusout:function(){U.shape.html=this.value;
if(U.shape.html){h.redrawShape(U);
this.parentNode.removeChild(this)
}else{h.removeShape(U);
this.parentNode.removeChild(this)
}}})
}else{if($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT){$(W).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:ad,top:ah,width:ab,height:ai,"text-align":aa,overflow:"hidden",resize:"none"}));
$(W).focus();
$(W).val(U.shape.text);
$(W).bind({focusout:function(){U.shape.text=this.value;
if(U.shape.text){h.redrawShape(U);
this.parentNode.removeChild(this)
}else{h.removeShape(U);
this.parentNode.removeChild(this)
}}})
}else{if($(U).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(U.shape.label&&h.isSVG()){$(U).children("[id$=_LABEL]").each(function(al,am){$(am).find("text").each(function(ao,an){aj=h.getBBox(an);
ad=aj.x-10;
ah=aj.y;
ab=aj.width+20;
ai=aj.height
})
})
}else{V=ag(U);
ad=V.x-OG.Constants.LABEL_EDITOR_WIDTH/2;
ah=V.y-OG.Constants.LABEL_EDITOR_HEIGHT/2;
ab=OG.Constants.LABEL_EDITOR_WIDTH;
ai=OG.Constants.LABEL_EDITOR_HEIGHT
}$(Y.srcElement).parents("[id$=_FROMLABEL]").each(function(al,am){$(am).find("text").each(function(ao,an){aj=h.getBBox(an);
ad=aj.x-10;
ah=aj.y;
ab=aj.width+20;
ai=aj.height;
ak=U.shape.fromLabel
})
});
$(Y.srcElement).parents("[id$=_TOLABEL]").each(function(al,am){$(am).find("text").each(function(ao,an){aj=h.getBBox(an);
ad=aj.x-10;
ah=aj.y;
ab=aj.width+20;
ai=aj.height;
ae=U.shape.toLabel
})
});
$(W).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:ad,top:ah,width:ab,height:ai,overflow:"hidden",resize:"none"}));
$(W).focus();
if(ak||ae){$(W).val(ak?U.shape.fromLabel:U.shape.toLabel)
}else{$(W).val(U.shape.label)
}$(W).bind({focusout:function(){if(ak){h.drawEdgeLabel(U,this.value,"FROM")
}else{if(ae){h.drawEdgeLabel(U,this.value,"TO")
}else{h.drawLabel(U,this.value)
}}this.parentNode.removeChild(this)
}})
}else{$(W).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:ad,top:ah,width:ab,height:ai,"text-align":aa,overflow:"hidden",resize:"none"}));
$(W).focus();
$(W).val(U.shape.label);
$(W).bind({focusout:function(){h.drawLabel(U,this.value);
this.parentNode.removeChild(this)
}})
}}}}})
}};
this.enableConnect=function(V){var W,U=h.getRootGroup();
if(V&&$(V).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){$(V).bind({mouseover:function(){W=h.drawTerminal(V,$(U).data("dragged_guide")==="to"?OG.Constants.TERMINAL_TYPE.IN:OG.Constants.TERMINAL_TYPE.OUT);
if(W&&W.terminal&&W.terminal.childNodes.length>0){if($(U).data("edge")){$.each(W.terminal.childNodes,function(X,aa){var ab=$(U).data("from_terminal"),Z=ab&&OG.Util.isElement(ab)?i(ab):null,Y=V&&Z&&V.id===Z.id;
if(aa.terminal&&aa.terminal.direction.toLowerCase()==="c"&&(($(U).data("dragged_guide")==="to"&&aa.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.IN)>=0)||($(U).data("dragged_guide")==="from"&&aa.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.OUT)>=0))&&(!Y||OG.Constants.SELF_CONNECTABLE)){h.drawDropOverGuide(V);
$(U).data("edge_terminal",aa);
return false
}})
}$(W.bBox).bind({mouseout:function(){if(!$(U).data("edge")){h.removeTerminal(V)
}}});
$.each(W.terminal.childNodes,function(X,Y){if(Y.terminal){$(Y).bind({mouseover:function(ab){var ac=$(U).data("from_terminal"),aa=ac&&OG.Util.isElement(ac)?i(ac):null,Z=V&&aa&&V.id===aa.id;
if((($(U).data("dragged_guide")==="to"&&Y.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.IN)>=0)||($(U).data("dragged_guide")==="from"&&Y.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.OUT)>=0)||(!$(U).data("dragged_guide")&&Y.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.OUT)>=0))&&(!Z||OG.Constants.SELF_CONNECTABLE)){h.setAttr(Y,OG.Constants.DEFAULT_STYLE.TERMINAL_OVER);
$(U).data("edge_terminal",Y)
}},mouseout:function(){h.setAttr(Y,OG.Constants.DEFAULT_STYLE.TERMINAL);
$(U).removeData("edge_terminal")
}});
$(Y).draggable({start:function(ab){var Z=Y.terminal.position.x,ac=Y.terminal.position.y,aa=h.drawShape(null,new OG.EdgeShape([Z,ac],[Z,ac]),null,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW);
$(U).data("edge",aa);
$(U).data("from_terminal",Y);
$(U).data("dragged_guide","to");
h.removeRubberBand(h.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(ae,ad){if(ad.id){h.removeGuide(ad)
}})
},drag:function(Z){var ad=m(Z),ab=$(U).data("edge"),ai=$(U).data("from_terminal"),am=$(U).data("edge_terminal"),aj=[ai.terminal.position.x,ai.terminal.position.y],af=am?[am.terminal.position.x,am.terminal.position.y]:[ad.x,ad.y],ak=ai.terminal.direction.toLowerCase(),ag=am?am.terminal.direction.toLowerCase():"c",ae=am?i(am):null,aa,al,ac,ah;
$(this).css({position:"",left:"",top:""});
aa=aj;
al=af;
if(!V.shape.geom.getBoundary().isContains(af)&&ak==="c"){ac=h.intersectionEdge(ab.shape.geom.style.get("edge-type"),V,[aa[0],aa[1]],[al[0],al[1]],true);
aj=ac.position;
ak=ac.direction
}if(ae&&ag==="c"){ac=h.intersectionEdge(ab.shape.geom.style.get("edge-type"),ae,[aa[0],aa[1]],[al[0],al[1]],false);
af=ac.position;
ag=ac.direction
}ah=V&&ae&&V.id===ae.id;
if(ah){aj=af=V.shape.geom.getBoundary().getRightCenter()
}if(!ah||OG.Constants.SELF_CONNECTABLE){h.drawEdge(new OG.Line(aj,af),OG.Util.apply(ab.shape.geom.style.map,{"edge-direction":ak+" "+ag}),ab.id,ah)
}},stop:function(Z){var ak=m(Z),ac=$(U).data("edge"),ai=$(U).data("from_terminal"),al=$(U).data("edge_terminal")||[ak.x,ak.y],ad=OG.Util.isElement(al)?i(al):null,ab,ae,aj,ag,aa,af,ah;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(!$(U).data("edge_terminal")&&OG.Constants.CONNECT_CLONEABLE){ab=V.shape.geom.getBoundary();
ae=h.drawShape([ak.x,ak.y],V.shape.clone(),[ab.getWidth(),ab.getHeight()],V.shapeStyle);
y.setClickSelectable(ae,OG.Constants.SELECTABLE);
y.setMovable(ae,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.GROUP_DROPABLE){y.enableDragAndDropGroup(ae)
}if(OG.Constants.GROUP_COLLAPSIBLE){y.enableCollapse(ae)
}if($(ae).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){y.enableConnect(ae)
}if(OG.Constants.LABEL_EDITABLE){y.enableEditLabel(ae)
}}aj=h.drawTerminal(ae,OG.Constants.TERMINAL_TYPE.IN);
ag=aj.terminal.childNodes;
al=ag[0];
for(af=0;
af<ag.length;
af++){if(ag[af].terminal&&ag[af].terminal.direction.toLowerCase()==="c"){al=ag[af];
break
}}}ah=V&&ad&&V.id===ad.id;
if(al&&(OG.Util.isElement(al)||!OG.Constants.CONNECT_REQUIRED)&&(!ah||OG.Constants.SELF_CONNECTABLE)){ac=h.connect(ai,al,ac);
if(ac){aa=h.drawGuide(ac);
if(ac&&aa){y.setClickSelectable(ac,OG.Constants.SELECTABLE);
y.setMovable(ac,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
j(ac,aa,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if($(ac).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.LABEL_EDITABLE){y.enableEditLabel(ac)
}}h.toFront(aa.group)
}}}else{h.removeShape(ac)
}$(U).removeData("edge");
$(U).removeData("from_terminal");
$(U).removeData("edge_terminal");
$(U).removeData("dragged_guide");
if(ad){h.remove(ad.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}}})
}})
}else{h.removeTerminal(V)
}},mouseout:function(X){if($(V).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE&&$(U).data("edge")){h.remove(V.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(U).removeData("edge_terminal")
}}})
}};
this.enableDragAndDropGroup=function(W){var U=h.getRootGroup(),V;
if(W&&$(W).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){$(W).bind({mouseover:function(){if($(U).data("bBoxArray")){V=false;
$.each($(U).data("bBoxArray"),function(X,Y){if(W.id===Y.id){V=true
}});
if(!V){$(U).data("groupTarget",W);
h.drawDropOverGuide(W)
}}},mouseout:function(X){h.remove(W.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(U).removeData("groupTarget")
}})
}};
this.enableCollapse=function(W){var V,U;
U=function(X,Y){if(Y&&Y.bBox&&Y.collapse){$(Y.collapse).bind("click",function(Z){if(X.shape.isCollapsed===true){h.expand(X);
Y=h.drawCollapseGuide(X);
U(X,Y)
}else{h.collapse(X);
Y=h.drawCollapseGuide(X);
U(X,Y)
}});
$(Y.bBox).bind("mouseout",function(Z){h.remove(X.id+OG.Constants.COLLAPSE_BBOX);
h.remove(X.id+OG.Constants.COLLAPSE_SUFFIX)
})
}};
if(W&&$(W).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){$(W).bind({mouseover:function(){V=h.drawCollapseGuide(this);
if(V&&V.bBox&&V.collapse){U(W,V)
}}})
}};
this.setMovable=function(W,V){var U=h.getRootGroup();
if(!W){return
}if(V){if(($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.GEOM&&OG.Constants.MOVABLE_GEOM)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT&&OG.Constants.MOVABLE_TEXT)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.HTML&&OG.Constants.MOVABLE_HTML)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.IMAGE&&OG.Constants.MOVABLE_IMAGE)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE&&OG.Constants.MOVABLE_EDGE)||($(W).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP&&OG.Constants.MOVABLE_GROUP)){$(W).draggable({start:function(Y){var Z=m(Y),X;
if(h.getElementById(W.id+OG.Constants.GUIDE_SUFFIX.GUIDE)===null){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(aa,ab){if(OG.Util.isElement(ab)&&ab.id){h.removeGuide(ab)
}});
h.removeAllTerminal()
}h.removeGuide(W);
X=h.drawGuide(W);
$(this).data("start",{x:Z.x,y:Z.y});
$(this).data("offset",{x:Z.x-Q(h.getAttr(X.bBox,"x")),y:Z.y-Q(h.getAttr(X.bBox,"y"))});
$(U).data("bBoxArray",N());
h.removeRubberBand(h.getRootElement());
h.removeAllTerminal()
},drag:function(Z){var aa=m(Z),ac=$(this).data("start"),ab=$(U).data("bBoxArray"),Y=J(aa.x-ac.x),X=J(aa.y-ac.y);
e(aa.x,aa.y);
$(this).css({position:"",left:"",top:""});
$.each(ab,function(ad,ae){h.setAttr(ae.box,{transform:"t"+Y+","+X})
});
h.removeAllTerminal()
},stop:function(X){var aa=m(X),Y=$(this).data("start"),ad=$(U).data("bBoxArray"),af=J(aa.x-Y.x),ae=J(aa.y-Y.y),ab=$(U).data("groupTarget"),ac,Z;
$(this).css({position:"",left:"",top:""});
ac=o(ad,af,ae);
if(ab&&OG.Util.isElement(ab)){h.addToGroup(ab,ac);
$.each(ac,function(ag,ah){h.removeGuide(ah)
});
Z=h.drawGuide(ab);
j(ab,Z,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.toFront(Z.group);
h.remove(ab.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(U).removeData("groupTarget")
}else{h.addToGroup(U,ac);
$.each(ac,function(ag,ah){h.removeGuide(ah);
Z=h.drawGuide(ah);
j(ah,Z,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.toFront(Z.group)
})
}$(U).removeData("bBoxArray")
}});
h.setAttr(W,{cursor:"move"});
OG.Util.apply(W.shape.geom.style.map,{cursor:"move"})
}}else{$(W).draggable("destroy");
h.setAttr(W,{cursor:OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor});
OG.Util.apply(W.shape.geom.style.map,{cursor:OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
this.setClickSelectable=function(V,U){if(U){$(V).bind("click",function(X){var W;
if(V.shape){if(!X.shiftKey&&!X.ctrlKey){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(Y,Z){if(Z.id){h.removeGuide(Z)
}})
}if($(V).attr("_selected")==="true"){if(X.shiftKey||X.ctrlKey){h.removeGuide(V)
}}else{H(V);
if(!s(V)){W=h.drawGuide(V);
if(W){j(V,W,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.removeAllTerminal();
h.toFront(W.group)
}}}return false
}});
if(OG.Constants.ENABLE_CONTEXTMENU){$(V).bind("contextmenu",function(X){var W;
if(V.shape){if($(V).attr("_selected")!=="true"){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(Y,Z){if(Z.id){h.removeGuide(Z)
}});
H(V);
if(!s(V)){W=h.drawGuide(V);
if(W){j(V,W,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.removeAllTerminal();
h.toFront(W.group)
}}}return true
}})
}if(U&&OG.Constants.MOVABLE){h.setAttr(V,{cursor:"move"});
OG.Util.apply(V.shape.geom.style.map,{cursor:"move"})
}else{h.setAttr(V,{cursor:"pointer"});
OG.Util.apply(V.shape.geom.style.map,{cursor:"pointer"})
}}else{$(V).click("destroy");
h.setAttr(V,{cursor:OG.Constants.DEFAULT_STYLE.SHAPE.cursor});
OG.Util.apply(V.shape.geom.style.map,{cursor:OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
this.setDragSelectable=function(U){var V=h.getRootElement();
$(V).bind("click",function(X){var W=$(this).data("dragBox");
if(!W||(W&&W.width<1&&W.height<1)){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(Y,Z){if(OG.Util.isElement(Z)&&Z.id){h.removeGuide(Z)
}});
h.removeAllTerminal()
}});
if(U){$(V).bind("mousedown",function(W){var X=m(W);
$(this).data("dragBox_first",{x:X.x,y:X.y});
h.drawRubberBand([X.x,X.y])
});
$(V).bind("mousemove",function(Z){var ab=$(this).data("dragBox_first"),aa,Y,X,W,ac;
if(ab){aa=m(Z);
Y=aa.x-ab.x;
X=aa.y-ab.y;
W=Y<=0?ab.x+Y:ab.x;
ac=X<=0?ab.y+X:ab.y;
h.drawRubberBand([W,ac],[Math.abs(Y),Math.abs(X)])
}});
$(V).bind("mouseup",function(W){var aa=$(this).data("dragBox_first"),Z,X,ae,ad,ac,ab,Y;
h.removeRubberBand(V);
if(aa){Z=m(W);
X=Z.x-aa.x;
ae=Z.y-aa.y;
ad=X<=0?aa.x+X:aa.x;
ac=ae<=0?aa.y+ae:aa.y;
ab=new OG.Envelope([ad,ac],Math.abs(X),Math.abs(ae));
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(af,ag){if(ag.shape.geom&&ab.isContainsAll(ag.shape.geom.getVertices())){H(ag);
if(!s(ag)){Y=h.drawGuide(ag);
if(Y){j(ag,Y,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
h.removeAllTerminal()
}}}});
$(this).data("dragBox",{width:X,height:ae,x:ad,y:ac})
}});
$(V).bind("contextmenu",function(W){h.removeRubberBand(V)
})
}else{$(V).unbind("mousedown");
$(V).unbind("mousemove");
$(V).unbind("mouseup");
$(V).unbind("contextmenu")
}};
this.setEnableHotKey=function(U){if(U){$(window.document).bind("keydown",function(V){if(OG.Constants.ENABLE_HOTKEY_DELETE&&V.keyCode===KeyEvent.DOM_VK_DELETE){I()
}if(OG.Constants.ENABLE_HOTKEY_CTRL_A&&OG.Constants.SELECTABLE&&V.ctrlKey&&V.keyCode===KeyEvent.DOM_VK_A){P()
}if(OG.Constants.ENABLE_HOTKEY_CTRL_C&&V.ctrlKey&&V.keyCode===KeyEvent.DOM_VK_C){w()
}if(OG.Constants.ENABLE_HOTKEY_CTRL_V&&V.ctrlKey&&V.keyCode===KeyEvent.DOM_VK_V){C()
}if(OG.Constants.ENABLE_HOTKEY_CTRL_D&&V.ctrlKey&&V.keyCode===KeyEvent.DOM_VK_D){B()
}if(OG.Constants.ENABLE_HOTKEY_CTRL_G&&V.ctrlKey&&V.keyCode===KeyEvent.DOM_VK_G){O()
}if(OG.Constants.ENABLE_HOTKEY_CTRL_U&&V.ctrlKey&&V.keyCode===KeyEvent.DOM_VK_U){n()
}if(OG.Constants.ENABLE_HOTKEY_SHIFT_ARROW){if(V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_LEFT){o(N(),-1*(OG.Constants.DRAG_GRIDABLE?OG.Constants.MOVE_SNAP_SIZE:1),0)
}if(V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_RIGHT){o(N(),(OG.Constants.DRAG_GRIDABLE?OG.Constants.MOVE_SNAP_SIZE:1),0)
}if(V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_UP){o(N(),0,-1*(OG.Constants.DRAG_GRIDABLE?OG.Constants.MOVE_SNAP_SIZE:1))
}if(V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_DOWN){o(N(),0,(OG.Constants.DRAG_GRIDABLE?OG.Constants.MOVE_SNAP_SIZE:1))
}}if(OG.Constants.ENABLE_HOTKEY_ARROW){if(!V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_LEFT){o(N(),-1*OG.Constants.MOVE_SNAP_SIZE,0)
}if(!V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_RIGHT){o(N(),OG.Constants.MOVE_SNAP_SIZE,0)
}if(!V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_UP){o(N(),0,-1*OG.Constants.MOVE_SNAP_SIZE)
}if(!V.shiftKey&&V.keyCode===KeyEvent.DOM_VK_DOWN){o(N(),0,OG.Constants.MOVE_SNAP_SIZE)
}}})
}else{$(window.document).unbind("keydown")
}};
this.enableRootContextMenu=function(){$.contextMenu({selector:"#"+h.getRootElement().id,build:function(U,X){var W=h.getRootGroup(),V=$(W).data("copied");
return{items:{selectAll:{name:"Select All",callback:P},sep1:"---------",paste:{name:"Paste",callback:C,disabled:(V?false:true)}}}
}})
};
this.enableShapeContextMenu=function(){$.contextMenu({selector:"[_type=SHAPE]",build:function(U,V){return{items:{"delete":{name:"Delete",callback:I},sep1:"---------",cut:{name:"Cut",callback:l},copy:{name:"Copy",callback:w},sep2:"---------",duplicate:{name:"Duplicate",callback:B},sep3:"---------",group:{name:"Group",callback:O},unGroup:{name:"UnGroup",callback:n},sep4:"---------",shapeRotate:{name:"Rotate",items:{rotate_select:{name:"Select",type:"select",options:{"0":"0","45":"45","90":"90","135":"135","180":"180","-45":"-45","-90":"-90","-135":"-135","-180":"-180"},selected:"0",events:{change:function(W){E(W.target.value)
}}},sep5_6_1:"---------",rotate_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){E(W.target.value)
}}}}}},sep5:"---------",format:{name:"Format",items:{fillColor:{name:"Fill Color",items:{fillColor_select:{name:"Select",type:"select",options:{"":"",white:"white",gray:"gray",blue:"blue",red:"red",yellow:"yellow",orange:"orange",green:"green",black:"black"},selected:"",events:{change:function(W){if(W.target.value!==""){g(W.target.value)
}}}},sep5_1_1:"---------",fillColor_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){g(W.target.value)
}}}}}},fillOpacity:{name:"Fill Opacity",items:{fillOpacity_select:{name:"Select",type:"select",options:{"":"","0.0":"0%","0.1":"10%","0.2":"20%","0.3":"30%","0.4":"40%","0.5":"50%","0.6":"60%","0.7":"70%","0.8":"80%","0.9":"90%","1.0":"100%"},selected:"",events:{change:function(W){if(W.target.value!==""){u(W.target.value)
}}}}}},sep5_1:"---------",lineType:{name:"Line Type",items:{lineType_straight:{name:"Straight",type:"radio",radio:"lineType",value:"straight",events:{change:function(W){z(W.target.value)
}}},lineType_plain:{name:"Plain",type:"radio",radio:"lineType",value:"plain",events:{change:function(W){z(W.target.value)
}}}}},lineStyle:{name:"Line Style",items:{lineStyle_1:{name:"solid",type:"radio",radio:"lineStyle",value:"",events:{change:function(W){r(W.target.value)
}}},lineStyle_2:{name:"----------",type:"radio",radio:"lineStyle",value:"-",events:{change:function(W){r(W.target.value)
}}},lineStyle_3:{name:"..........",type:"radio",radio:"lineStyle",value:".",events:{change:function(W){r(W.target.value)
}}},lineStyle_4:{name:"-.-.-.-.-.",type:"radio",radio:"lineStyle",value:"-.",events:{change:function(W){r(W.target.value)
}}},lineStyle_5:{name:"-..-..-..-",type:"radio",radio:"lineStyle",value:"-..",events:{change:function(W){r(W.target.value)
}}},lineStyle_6:{name:". . . . . ",type:"radio",radio:"lineStyle",value:". ",events:{change:function(W){r(W.target.value)
}}},lineStyle_7:{name:"- - - - - ",type:"radio",radio:"lineStyle",value:"- ",events:{change:function(W){r(W.target.value)
}}},lineStyle_8:{name:"-- -- -- -",type:"radio",radio:"lineStyle",value:"-- ",events:{change:function(W){r(W.target.value)
}}},lineStyle_9:{name:"- .- .- .-",type:"radio",radio:"lineStyle",value:"- .",events:{change:function(W){r(W.target.value)
}}},lineStyle_10:{name:"--.--.--.-",type:"radio",radio:"lineStyle",value:"--.",events:{change:function(W){r(W.target.value)
}}},lineStyle_11:{name:"--..--..--",type:"radio",radio:"lineStyle",value:"--..",events:{change:function(W){r(W.target.value)
}}}}},lineColor:{name:"Line Color",items:{lineColor_select:{name:"Select",type:"select",options:{"":"",white:"white",gray:"gray",blue:"blue",red:"red",yellow:"yellow",orange:"orange",green:"green",black:"black"},selected:"",events:{change:function(W){if(W.target.value!==""){k(W.target.value)
}}}},sep5_4_1:"---------",lineColor_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){k(W.target.value)
}}}}}},lineWidth:{name:"Line Width",items:{lineWidth_select:{name:"Select",type:"select",options:{0:"",1:"1px",2:"2px",3:"3px",4:"4px",5:"5px",6:"6px",8:"8px",10:"10px",12:"12px",16:"16px",24:"24px"},selected:0,events:{change:function(W){if(W.target.value!==0){q(W.target.value)
}}}},sep5_5_1:"---------",lineWidth_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){q(W.target.value)
}}}}}}}},sep6:"---------",text:{name:"Text",items:{fontFamily:{name:"Font Family",items:{fontFamily_1:{name:'<span style="font-family: Arial">Arial</span>',type:"radio",radio:"fontFamily",value:"Arial",events:{change:function(W){D(W.target.value)
}}},fontFamily_2:{name:"<span style=\"font-family: 'Comic Sans MS'\">Comic Sans MS</span>",type:"radio",radio:"fontFamily",value:"Comic Sans MS",events:{change:function(W){D(W.target.value)
}}},fontFamily_3:{name:"<span style=\"font-family: 'Courier New'\">Courier New</span>",type:"radio",radio:"fontFamily",value:"Courier New",events:{change:function(W){D(W.target.value)
}}},fontFamily_4:{name:'<span style="font-family: Garamond">Garamond</span>',type:"radio",radio:"fontFamily",value:"Garamond",events:{change:function(W){D(W.target.value)
}}},fontFamily_5:{name:'<span style="font-family: Georgia">Georgia</span>',type:"radio",radio:"fontFamily",value:"Georgia",events:{change:function(W){D(W.target.value)
}}},fontFamily_6:{name:"<span style=\"font-family: 'Lucida Console'\">Lucida Console</span>",type:"radio",radio:"fontFamily",value:"Lucida Console",events:{change:function(W){D(W.target.value)
}}},fontFamily_7:{name:"<span style=\"font-family: 'MS Gothic'\">MS Gothic</span>",type:"radio",radio:"fontFamily",value:"MS Gothic",events:{change:function(W){D(W.target.value)
}}},fontFamily_8:{name:"<span style=\"font-family: 'MS Sans Serif'\">MS Sans Serif</span>",type:"radio",radio:"fontFamily",value:"MS Sans Serif",events:{change:function(W){D(W.target.value)
}}},fontFamily_9:{name:'<span style="font-family: Verdana">Verdana</span>',type:"radio",radio:"fontFamily",value:"Verdana",events:{change:function(W){D(W.target.value)
}}},fontFamily_10:{name:"<span style=\"font-family: 'Times New Roman'\">Times New Roman</span>",type:"radio",radio:"fontFamily",value:"Times New Roman",events:{change:function(W){D(W.target.value)
}}},sep6_1_1:"---------",fontFamily_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){D(W.target.value)
}}}}}},fontColor:{name:"Font Color",items:{fontColor_select:{name:"Select",type:"select",options:{"":"",white:"white",gray:"gray",blue:"blue",red:"red",yellow:"yellow",orange:"orange",green:"green",black:"black"},selected:"",events:{change:function(W){if(W.target.value!==""){L(W.target.value)
}}}},sep6_1_2:"---------",fontColor_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){L(W.target.value)
}}}}}},fontSize:{name:"Font Size",items:{fontSize_select:{name:"Select",type:"select",options:{"":"","6":"6","8":"8","9":"9","10":"10","11":"11","12":"12","14":"14","18":"18","24":"24","36":"36","48":"48","72":"72"},selected:"",events:{change:function(W){if(W.target.value!==""){d(W.target.value)
}}}},sep6_1_3:"---------",fontSize_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){d(W.target.value)
}}}}}},sep6_1:"---------",fontWeight_bold:{name:'<span style="font-weight: bold">Bold</span>',type:"checkbox",events:{change:function(W){if(W.target.checked){b("bold")
}else{b("normal")
}}}},fontWeight_italic:{name:'<span style="font-style: italic">Italic</span>',type:"checkbox",events:{change:function(W){if(W.target.checked){S("italic")
}else{S("normal")
}}}},sep6_2:"---------",position:{name:"Text Position",items:{position_left:{name:"Left",type:"radio",radio:"position",value:"left",events:{change:function(W){R(W.target.value)
}}},position_center:{name:"Center",type:"radio",radio:"position",value:"center",events:{change:function(W){R(W.target.value)
}}},position_right:{name:"Right",type:"radio",radio:"position",value:"right",events:{change:function(W){R(W.target.value)
}}},position_top:{name:"Top",type:"radio",radio:"position",value:"top",events:{change:function(W){R(W.target.value)
}}},position_bottom:{name:"Bottom",type:"radio",radio:"position",value:"bottom",events:{change:function(W){R(W.target.value)
}}}}},vertical:{name:"Vertical Align",items:{vertical_top:{name:"Top",type:"radio",radio:"vertical",value:"top",events:{change:function(W){f(W.target.value)
}}},vertical_middle:{name:"Middle",type:"radio",radio:"vertical",value:"middle",events:{change:function(W){f(W.target.value)
}}},vertical_bottom:{name:"Bottom",type:"radio",radio:"vertical",value:"bottom",events:{change:function(W){f(W.target.value)
}}}}},horizontal:{name:"Horizontal Align",items:{vertical_start:{name:"Left",type:"radio",radio:"horizontal",value:"start",events:{change:function(W){t(W.target.value)
}}},horizontal_middle:{name:"Middle",type:"radio",radio:"horizontal",value:"middle",events:{change:function(W){t(W.target.value)
}}},horizontal_end:{name:"Right",type:"radio",radio:"horizontal",value:"end",events:{change:function(W){t(W.target.value)
}}}}},sep6_5:"---------",textRotate:{name:"Text Rotate",items:{textRotate_select:{name:"Select",type:"select",options:{"0":"0","45":"45","90":"90","135":"135","180":"180","-45":"-45","-90":"-90","-135":"-135","-180":"-180"},selected:"0",events:{change:function(W){c(W.target.value)
}}},sep6_6_1:"---------",textRotate_custom:{name:"Custom",type:"text",events:{keyup:function(W){if(W.target.value!==""){c(W.target.value)
}}}}}}}},sep7:"---------",label:{name:"Label",items:{label_shape:{name:"Cell Label",type:"text",events:{keyup:function(W){v(W.target.value)
}}},sep7_1:"---------",label_from:{name:"Edge From",type:"text",events:{keyup:function(W){p(W.target.value)
}}},label_to:{name:"Edge To",type:"text",events:{keyup:function(W){G(W.target.value)
}}}}}}}
}})
}
};
OG.handler.EventHandler.prototype=new OG.handler.EventHandler();
OG.handler.EventHandler.prototype.constructor=OG.handler.EventHandler;
OG.EventHandler=OG.handler.EventHandler;OG.graph.Canvas=function(container,containerSize,backgroundColor,backgroundImage){var _RENDERER=container?new OG.RaphaelRenderer(container,containerSize,backgroundColor,backgroundImage):null,_HANDLER=new OG.EventHandler(_RENDERER),_CONTAINER=OG.Util.isElement(container)?container:document.getElementById(container);
this.initConfig=function(config){if(config){OG.Constants.SELECTABLE=config.selectable===undefined?OG.Constants.SELECTABLE:config.selectable;
OG.Constants.DRAG_SELECTABLE=config.dragSelectable===undefined?OG.Constants.DRAG_SELECTABLE:config.dragSelectable;
OG.Constants.MOVABLE=config.movable===undefined?OG.Constants.MOVABLE:config.movable;
OG.Constants.RESIZABLE=config.resizable===undefined?OG.Constants.RESIZABLE:config.resizable;
OG.Constants.CONNECTABLE=config.connectable===undefined?OG.Constants.CONNECTABLE:config.connectable;
OG.Constants.SELF_CONNECTABLE=config.selfConnectable===undefined?OG.Constants.SELF_CONNECTABLE:config.selfConnectable;
OG.Constants.CONNECT_CLONEABLE=config.connectCloneable===undefined?OG.Constants.CONNECT_CLONEABLE:config.connectCloneable;
OG.Constants.CONNECT_REQUIRED=config.connectRequired===undefined?OG.Constants.CONNECT_REQUIRED:config.connectRequired;
OG.Constants.LABEL_EDITABLE=config.labelEditable===undefined?OG.Constants.LABEL_EDITABLE:config.labelEditable;
OG.Constants.GROUP_DROPABLE=config.groupDropable===undefined?OG.Constants.GROUP_DROPABLE:config.groupDropable;
OG.Constants.GROUP_COLLAPSIBLE=config.collapsible===undefined?OG.Constants.GROUP_COLLAPSIBLE:config.collapsible;
OG.Constants.ENABLE_HOTKEY=config.enableHotKey===undefined?OG.Constants.ENABLE_HOTKEY:config.enableHotKey
}_HANDLER.setDragSelectable(OG.Constants.SELECTABLE&&OG.Constants.DRAG_SELECTABLE);
_HANDLER.setEnableHotKey(OG.Constants.ENABLE_HOTKEY);
if(OG.Constants.ENABLE_CONTEXTMENU){_HANDLER.enableRootContextMenu();
_HANDLER.enableShapeContextMenu()
}this.CONFIG_INITIALIZED=true
};
this.getRenderer=function(){return _RENDERER
};
this.getContainer=function(){return _CONTAINER
};
this.getEventHandler=function(){return _HANDLER
};
this.drawShape=function(position,shape,size,style,id,parentId,gridable){if(OG.Constants.DRAG_GRIDABLE&&(!OG.Util.isDefined(gridable)||gridable===true)){if(position){position[0]=OG.Util.roundGrid(position[0]);
position[1]=OG.Util.roundGrid(position[1])
}if(size){size[0]=OG.Util.roundGrid(size[0],OG.Constants.MOVE_SNAP_SIZE*2);
size[1]=OG.Util.roundGrid(size[1],OG.Constants.MOVE_SNAP_SIZE*2)
}}var element=_RENDERER.drawShape(position,shape,size,style,id);
if(position&&(shape.TYPE===OG.Constants.SHAPE_TYPE.EDGE)){element=_RENDERER.move(element,position)
}if(parentId&&_RENDERER.getElementById(parentId)){_RENDERER.appendChild(element,parentId)
}if(!this.CONFIG_INITIALIZED){this.initConfig()
}_HANDLER.setClickSelectable(element,OG.Constants.SELECTABLE);
_HANDLER.setMovable(element,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.CONNECTABLE){_HANDLER.enableConnect(element)
}if(OG.Constants.LABEL_EDITABLE){_HANDLER.enableEditLabel(element)
}if(OG.Constants.GROUP_DROPABLE){_HANDLER.enableDragAndDropGroup(element)
}if(OG.Constants.GROUP_COLLAPSIBLE){_HANDLER.enableCollapse(element)
}return element
};
this.setShapeStyle=function(shapeElement,style){_RENDERER.setShapeStyle(shapeElement,style)
};
this.drawLabel=function(shapeElement,text,style){return _RENDERER.drawLabel(shapeElement,text,style)
};
this.redrawConnectedEdge=function(element,excludeEdgeId){_RENDERER.redrawConnectedEdge(element,excludeEdgeId)
};
this.connect=function(fromElement,toElement,style,label){var terminalGroup,childTerminals,fromTerminal,toTerminal,i,edge,guide;
terminalGroup=_RENDERER.drawTerminal(fromElement,OG.Constants.TERMINAL_TYPE.OUT);
childTerminals=terminalGroup.terminal.childNodes;
fromTerminal=childTerminals[0];
for(i=0;
i<childTerminals.length;
i++){if(childTerminals[i].terminal&&childTerminals[i].terminal.direction.toLowerCase()==="c"){fromTerminal=childTerminals[i];
break
}}_RENDERER.removeTerminal(fromElement);
terminalGroup=_RENDERER.drawTerminal(toElement,OG.Constants.TERMINAL_TYPE.IN);
childTerminals=terminalGroup.terminal.childNodes;
toTerminal=childTerminals[0];
for(i=0;
i<childTerminals.length;
i++){if(childTerminals[i].terminal&&childTerminals[i].terminal.direction.toLowerCase()==="c"){toTerminal=childTerminals[i];
break
}}_RENDERER.removeTerminal(toElement);
edge=_RENDERER.drawShape(null,new OG.EdgeShape(fromTerminal.terminal.position,toTerminal.terminal.position));
edge=_RENDERER.connect(fromTerminal,toTerminal,edge,style,label);
if(edge){guide=_RENDERER.drawGuide(edge);
if(edge&&guide){_HANDLER.setClickSelectable(edge,OG.Constants.SELECTABLE);
_HANDLER.setMovable(edge,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
_HANDLER.setResizable(edge,guide,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if($(edge).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.LABEL_EDITABLE){_HANDLER.enableEditLabel(edge)
}}_RENDERER.toFront(guide.group)
}}return edge
};
this.disconnect=function(element){_RENDERER.disconnect(element)
};
this.group=function(elements){var group=_RENDERER.group(elements);
_HANDLER.setClickSelectable(group,OG.Constants.SELECTABLE);
_HANDLER.setMovable(group,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if($(group).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.LABEL_EDITABLE){_HANDLER.enableEditLabel(group)
}}return group
};
this.ungroup=function(groupElements){return _RENDERER.ungroup(groupElements)
};
this.addToGroup=function(groupElement,elements){_RENDERER.addToGroup(groupElement,elements)
};
this.collapse=function(element){_RENDERER.collapse(element)
};
this.expand=function(element){_RENDERER.expand(element)
};
this.clear=function(){_RENDERER.clear()
};
this.removeShape=function(element){_RENDERER.removeShape(element)
};
this.removeChild=function(element){_RENDERER.removeChild(element)
};
this.removeGuide=function(element){_RENDERER.removeGuide(element)
};
this.removeAllGuide=function(){_RENDERER.removeAllGuide()
};
this.getRootElement=function(){return _RENDERER.getRootElement()
};
this.getRootGroup=function(){return _RENDERER.getRootGroup()
};
this.getElementByPoint=function(position){return _RENDERER.getElementByPoint(position)
};
this.getElementsByBBox=function(envelope){return _RENDERER.getElementsByBBox(envelope)
};
this.setAttr=function(element,attribute){_RENDERER.setAttr(element,attribute)
};
this.getAttr=function(element,attrName){return _RENDERER.getAttr(element,attrName)
};
this.toFront=function(element){_RENDERER.toFront(element)
};
this.toBack=function(element){_RENDERER.toBack(element)
};
this.setCanvasSize=function(size){_RENDERER.setCanvasSize(size)
};
this.setViewBox=function(position,size,isFit){_RENDERER.setViewBox(position,size,isFit)
};
this.show=function(element){_RENDERER.show(element)
};
this.hide=function(element){_RENDERER.hide(element)
};
this.appendChild=function(srcElement,targetElement){return _RENDERER.appendChild(srcElement,targetElement)
};
this.insertAfter=function(srcElement,targetElement){return _RENDERER.insertAfter(srcElement,targetElement)
};
this.insertBefore=function(srcElement,targetElement){return _RENDERER.insertBefore(srcElement,targetElement)
};
this.move=function(element,offset,excludeEdgeId){return _RENDERER.move(element,offset,excludeEdgeId)
};
this.moveCentroid=function(element,position,excludeEdgeId){return _RENDERER.moveCentroid(element,position,excludeEdgeId)
};
this.rotate=function(element,angle,excludeEdgeId){return _RENDERER.rotate(element,angle,excludeEdgeId)
};
this.resize=function(element,offset,excludeEdgeId){return _RENDERER.resize(element,offset,excludeEdgeId)
};
this.resizeBox=function(element,size){return _RENDERER.resizeBox(element,size)
};
this.clone=function(element){return _RENDERER.clone(element)
};
this.getElementById=function(id){return _RENDERER.getElementById(id)
};
this.getElementsByType=function(shapeType){var root=this.getRootGroup();
if(shapeType){return $(root).find("[_type=SHAPE][_shape="+shapeType+"]")
}else{return $(root).find("[_type=SHAPE]")
}};
this.getElementsByShapeId=function(shapeId){var root=this.getRootGroup();
return $(root).find("[_type=SHAPE][_shape_id='"+shapeId+"']")
};
this.getBBox=function(element){return _RENDERER.getBBox(element)
};
this.getRootBBox=function(){return _RENDERER.getRootBBox()
};
this.isSVG=function(){return _RENDERER.isSVG()
};
this.isVML=function(){return _RENDERER.isVML()
};
this.setCustomData=function(shapeElement,data){var element=OG.Util.isElement(shapeElement)?shapeElement:document.getElementById(shapeElement);
element.data=data
};
this.getCustomData=function(shapeElement){var element=OG.Util.isElement(shapeElement)?shapeElement:document.getElementById(shapeElement);
return element.data
};
this.toXML=function(){return OG.Util.jsonToXml(this.toJSON())
};
this.toJSON=function(){var CANVAS=this,rootBBox=_RENDERER.getRootBBox(),rootGroup=_RENDERER.getRootGroup(),jsonObj={opengraph:{"@width":rootBBox.width,"@height":rootBBox.height,cell:[]}},childShape;
childShape=function(node,isRoot){$(node).children("[_type=SHAPE]").each(function(idx,item){var shape=item.shape,style=item.shapeStyle,geom=shape.geom,envelope=geom.getBoundary(),cell={},vertices,from,to;
cell["@id"]=$(item).attr("id");
if(!isRoot){cell["@parent"]=$(node).attr("id")
}cell["@shapeType"]=shape.TYPE;
cell["@shapeId"]=shape.SHAPE_ID;
cell["@x"]=envelope.getCentroid().x;
cell["@y"]=envelope.getCentroid().y;
cell["@width"]=envelope.getWidth();
cell["@height"]=envelope.getHeight();
if(style){cell["@style"]=escape(OG.JSON.encode(style))
}if($(item).attr("_from")){cell["@from"]=$(item).attr("_from")
}else{if(shape.TYPE!==OG.Constants.SHAPE_TYPE.EDGE){cell["@from"]=CANVAS.getPrevShapeIds(item).toString()
}}if($(item).attr("_to")){cell["@to"]=$(item).attr("_to")
}else{if(shape.TYPE!==OG.Constants.SHAPE_TYPE.EDGE){cell["@to"]=CANVAS.getNextShapeIds(item).toString()
}}if($(item).attr("_fromedge")){cell["@fromEdge"]=$(item).attr("_fromedge")
}if($(item).attr("_toedge")){cell["@toEdge"]=$(item).attr("_toedge")
}if(shape.label){cell["@label"]=escape(shape.label)
}if(shape.fromLabel){cell["@fromLabel"]=escape(shape.fromLabel)
}if(shape.toLabel){cell["@toLabel"]=escape(shape.toLabel)
}if(shape.angle&&shape.angle!==0){cell["@angle"]=shape.angle
}if(shape instanceof OG.shape.ImageShape){cell["@value"]=shape.image
}else{if(shape instanceof OG.shape.HtmlShape){cell["@value"]=escape(shape.html)
}else{if(shape instanceof OG.shape.TextShape){cell["@value"]=escape(shape.text)
}else{if(shape instanceof OG.shape.EdgeShape){vertices=geom.getVertices();
from=vertices[0];
to=vertices[vertices.length-1];
cell["@value"]=from+","+to
}}}}if(geom){cell["@geom"]=escape(geom.toString())
}if(item.data){cell["@data"]=escape(OG.JSON.encode(item.data))
}jsonObj.opengraph.cell.push(cell);
childShape(item,false)
})
};
if(rootGroup.data){jsonObj.opengraph["@data"]=escape(OG.JSON.encode(rootGroup.data))
}childShape(rootGroup,true);
return jsonObj
};
this.loadXML=function(xml){if(!OG.Util.isElement(xml)){xml=OG.Util.parseXML(xml)
}return this.loadJSON(OG.Util.xmlToJson(xml))
};
this.loadJSON=function(json){var canvasWidth,canvasHeight,rootGroup,minX=Number.MAX_VALUE,minY=Number.MAX_VALUE,maxX=Number.MIN_VALUE,maxY=Number.MIN_VALUE,i,cell,shape,id,parent,shapeType,shapeId,x,y,width,height,style,geom,from,to,fromEdge,toEdge,label,fromLabel,toLabel,angle,value,data,element;
_RENDERER.clear();
if(json&&json.opengraph&&json.opengraph.cell&&OG.Util.isArray(json.opengraph.cell)){canvasWidth=json.opengraph["@width"];
canvasHeight=json.opengraph["@height"];
data=json.opengraph["@data"];
if(data){rootGroup=this.getRootGroup();
rootGroup.data=OG.JSON.decode(unescape(data))
}cell=json.opengraph.cell;
for(i=0;
i<cell.length;
i++){id=cell[i]["@id"];
parent=cell[i]["@parent"];
shapeType=cell[i]["@shapeType"];
shapeId=cell[i]["@shapeId"];
x=parseInt(cell[i]["@x"],10);
y=parseInt(cell[i]["@y"],10);
width=parseInt(cell[i]["@width"],10);
height=parseInt(cell[i]["@height"],10);
style=unescape(cell[i]["@style"]);
geom=unescape(cell[i]["@geom"]);
from=cell[i]["@from"];
to=cell[i]["@to"];
fromEdge=cell[i]["@fromEdge"];
toEdge=cell[i]["@toEdge"];
label=cell[i]["@label"];
fromLabel=cell[i]["@fromLabel"];
toLabel=cell[i]["@toLabel"];
angle=cell[i]["@angle"];
value=cell[i]["@value"];
data=cell[i]["@data"];
label=label?unescape(label):label;
minX=(minX>(x-width/2))?(x-width/2):minX;
minY=(minY>(y-height/2))?(y-height/2):minY;
maxX=(maxX<(x+width/2))?(x+width/2):maxX;
maxY=(maxY<(y+height/2))?(y+height/2):maxY;
switch(shapeType){case OG.Constants.SHAPE_TYPE.GEOM:case OG.Constants.SHAPE_TYPE.GROUP:shape=eval("new "+shapeId+"()");
if(label){shape.label=label
}element=this.drawShape([x,y],shape,[width,height],OG.JSON.decode(style),id,parent,false);
break;
case OG.Constants.SHAPE_TYPE.EDGE:shape=eval("new "+shapeId+"("+value+")");
if(label){shape.label=label
}if(fromLabel){shape.fromLabel=unescape(fromLabel)
}if(toLabel){shape.toLabel=unescape(toLabel)
}if(geom){geom=OG.JSON.decode(geom);
if(geom.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.POLYLINE]){geom=new OG.geometry.PolyLine(geom.vertices);
shape.geom=geom
}else{if(geom.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.CURVE]){geom=new OG.geometry.Curve(geom.controlPoints);
shape.geom=geom
}}}element=this.drawShape(null,shape,null,OG.JSON.decode(style),id,parent,false);
break;
case OG.Constants.SHAPE_TYPE.HTML:shape=eval("new "+shapeId+"()");
if(value){shape.html=unescape(value)
}if(label){shape.label=label
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent,false);
break;
case OG.Constants.SHAPE_TYPE.IMAGE:shape=eval("new "+shapeId+"('"+value+"')");
if(label){shape.label=label
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent,false);
break;
case OG.Constants.SHAPE_TYPE.TEXT:shape=eval("new "+shapeId+"()");
if(value){shape.text=unescape(value)
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent,false);
break
}if(from){$(element).attr("_from",from)
}if(to){$(element).attr("_to",to)
}if(fromEdge){$(element).attr("_fromedge",fromEdge)
}if(toEdge){$(element).attr("_toedge",toEdge)
}if(data){element.data=OG.JSON.decode(unescape(data))
}}this.setCanvasSize([canvasWidth,canvasHeight]);
return{width:maxX-minX,height:maxY-minY,x:minX,y:minY,x2:maxX,y2:maxY}
}return{width:0,height:0,x:0,y:0,x2:0,y2:0}
};
this.getPrevEdges=function(element){var prevEdgeIds=$(element).attr("_fromedge"),edgeArray=[],edgeIds,edge,i;
if(prevEdgeIds){edgeIds=prevEdgeIds.split(",");
for(i=0;
i<edgeIds.length;
i++){edge=this.getElementById(edgeIds[i]);
if(edge){edgeArray.push(edge)
}}}return edgeArray
};
this.getNextEdges=function(element){var nextEdgeIds=$(element).attr("_toedge"),edgeArray=[],edgeIds,edge,i;
if(nextEdgeIds){edgeIds=nextEdgeIds.split(",");
for(i=0;
i<edgeIds.length;
i++){edge=this.getElementById(edgeIds[i]);
if(edge){edgeArray.push(edge)
}}}return edgeArray
};
this.getPrevShapes=function(element){var prevEdges=this.getPrevEdges(element),shapeArray=[],prevShapeId,shape,i;
for(i=0;
i<prevEdges.length;
i++){prevShapeId=$(prevEdges[i]).attr("_from");
if(prevShapeId){prevShapeId=prevShapeId.substring(0,prevShapeId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP));
shape=this.getElementById(prevShapeId);
if(shape){shapeArray.push(shape)
}}}return shapeArray
};
this.getPrevShapeIds=function(element){var prevEdges=this.getPrevEdges(element),shapeArray=[],prevShapeId,i;
for(i=0;
i<prevEdges.length;
i++){prevShapeId=$(prevEdges[i]).attr("_from");
if(prevShapeId){prevShapeId=prevShapeId.substring(0,prevShapeId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP));
shapeArray.push(prevShapeId)
}}return shapeArray
};
this.getNextShapes=function(element){var nextEdges=this.getNextEdges(element),shapeArray=[],nextShapeId,shape,i;
for(i=0;
i<nextEdges.length;
i++){nextShapeId=$(nextEdges[i]).attr("_to");
if(nextShapeId){nextShapeId=nextShapeId.substring(0,nextShapeId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP));
shape=this.getElementById(nextShapeId);
if(shape){shapeArray.push(shape)
}}}return shapeArray
};
this.getNextShapeIds=function(element){var nextEdges=this.getNextEdges(element),shapeArray=[],nextShapeId,i;
for(i=0;
i<nextEdges.length;
i++){nextShapeId=$(nextEdges[i]).attr("_to");
if(nextShapeId){nextShapeId=nextShapeId.substring(0,nextShapeId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP));
shapeArray.push(nextShapeId)
}}return shapeArray
};
this.onDrawShape=function(callbackFunc){$(this.getRootElement()).bind("drawShape",function(event,shapeElement){callbackFunc(event,shapeElement)
})
};
this.onRedrawShape=function(callbackFunc){$(this.getRootElement()).bind("redrawShape",function(event,shapeElement){callbackFunc(event,shapeElement)
})
};
this.onRemoveShape=function(callbackFunc){$(this.getRootElement()).bind("removeShape",function(event,shapeElement){callbackFunc(event,shapeElement)
})
};
this.onMoveShape=function(callbackFunc){$(this.getRootElement()).bind("moveShape",function(event,shapeElement,offset){callbackFunc(event,shapeElement,offset)
})
};
this.onResizeShape=function(callbackFunc){$(this.getRootElement()).bind("resizeShape",function(event,shapeElement,offset){callbackFunc(event,shapeElement,offset)
})
};
this.onBeforeConnectShape=function(callbackFunc){$(this.getRootElement()).bind("beforeConnectShape",function(event){if(callbackFunc(event,event.edge,event.fromShape,event.toShape)===false){event.stopPropagation()
}})
};
this.onConnectShape=function(callbackFunc){$(this.getRootElement()).bind("connectShape",function(event,edgeElement,fromElement,toElement){callbackFunc(event,edgeElement,fromElement,toElement)
})
};
this.onDisconnectShape=function(callbackFunc){$(this.getRootElement()).bind("disconnectShape",function(event,edgeElement,fromElement,toElement){callbackFunc(event,edgeElement,fromElement,toElement)
})
}
};
OG.graph.Canvas.prototype=new OG.graph.Canvas();
OG.graph.Canvas.prototype.constructor=OG.graph.Canvas;
OG.Canvas=OG.graph.Canvas;