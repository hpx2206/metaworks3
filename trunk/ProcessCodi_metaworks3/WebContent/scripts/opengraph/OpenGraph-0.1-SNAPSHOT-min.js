var OG=window.OG||{};
OG.common={};
OG.geometry={};
OG.graph={};
OG.handler={};
OG.layout={};
OG.renderer={};
OG.shape={};
OG.shape.bpmn={};
OG.common.Constants={CANVAS_BACKGROUND:"#f9f9f9",GEOM_TYPE:{NULL:0,POINT:1,LINE:2,POLYLINE:3,POLYGON:4,RECTANGLE:5,CIRCLE:6,ELLIPSE:7,CURVE:8,BEZIER_CURVE:9,COLLECTION:10},GEOM_NAME:["","Point","Line","PolyLine","Polygon","Rectangle","Circle","Ellipse","Curve","BezierCurve","Collection"],NUM_PRECISION:0,NODE_TYPE:{ROOT:"ROOT",SHAPE:"SHAPE"},SHAPE_TYPE:{GEOM:"GEOM",TEXT:"TEXT",HTML:"HTML",IMAGE:"IMAGE",EDGE:"EDGE",GROUP:"GROUP"},EDGE_TYPE:{STRAIGHT:"straight",PLAIN:"plain",BEZIER:"bezier"},EDGE_PADDING:20,LABEL_PADDING:5,LABEL_EDITOR_WIDTH:70,LABEL_EDITOR_HEIGHT:16,FROMTO_LABEL_OFFSET_TOP:15,LABEL_SUFFIX:"_LABEL",LABEL_EDITOR_SUFFIX:"_LABEL_EDITOR",FROM_LABEL_SUFFIX:"_FROMLABEL",TO_LABEL_SUFFIX:"_TOLABEL",DEFAULT_STYLE:{SHAPE:{cursor:"default"},GEOM:{stroke:"black",fill:"white","fill-opacity":0,"label-position":"center"},TEXT:{stroke:"none","text-anchor":"middle"},HTML:{"label-position":"bottom","text-anchor":"top","vertical-align":"top"},IMAGE:{"label-position":"bottom","text-anchor":"top","vertical-align":"top"},EDGE:{stroke:"black","stroke-width":1,"stroke-opacity":1,"edge-type":"plain","edge-direction":"c c","arrow-start":"none","arrow-end":"classic-wide-long","stroke-dasharray":"","label-position":"center"},EDGE_SHADOW:{stroke:"#00FF00","stroke-width":1,"stroke-opacity":1,"arrow-start":"none","arrow-end":"none","stroke-dasharray":"- "},EDGE_HIDDEN:{stroke:"white","stroke-width":5,"stroke-opacity":0},GROUP:{stroke:"none",fill:"white","fill-opacity":0,"label-position":"bottom","text-anchor":"middle","vertical-align":"top"},GUIDE_BBOX:{stroke:"#00FF00",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},GUIDE_UL:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_UR:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LL:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LR:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_LC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_UC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_RC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_LWC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_FROM:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_TO:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_CTL_H:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_CTL_V:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_SHADOW:{stroke:"black",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},RUBBER_BAND:{stroke:"#0000FF",opacity:0.2,fill:"#0077FF"},TERMINAL:{stroke:"#808080","stroke-width":1,fill:"r(0.5, 0.5)#FFFFFF-#808080","fill-opacity":0.5,cursor:"pointer"},TERMINAL_OVER:{stroke:"#0077FF","stroke-width":4,fill:"r(0.5, 0.5)#FFFFFF-#0077FF","fill-opacity":1,cursor:"pointer"},TERMINAL_BBOX:{stroke:"none",fill:"white","fill-opacity":0},DROP_OVER_BBOX:{stroke:"#0077FF",fill:"none",opacity:0.6,"shape-rendering":"crispEdges"},LABEL:{"font-size":12,"font-color":"black"},LABEL_EDITOR:{position:"absolute",overflow:"visible",resize:"none","text-align":"center",display:"block",padding:0},COLLAPSE:{stroke:"black",fill:"white","fill-opacity":0,cursor:"pointer","shape-rendering":"crispEdges"},COLLAPSE_BBOX:{stroke:"none",fill:"white","fill-opacity":0}},RUBBER_BAND_ID:"OG_R_BAND",GUIDE_SUFFIX:{GUIDE:"_GUIDE",BBOX:"_GUIDE_BBOX",UL:"_GUIDE_UL",UR:"_GUIDE_UR",LL:"_GUIDE_LL",LR:"_GUIDE_LR",LC:"_GUIDE_LC",UC:"_GUIDE_UC",RC:"_GUIDE_RC",LWC:"_GUIDE_LWC",FROM:"_GUIDE_FROM",TO:"_GUIDE_TO",CTL:"_GUIDE_CTL_",CTL_H:"_GUIDE_CTL_H_",CTL_V:"_GUIDE_CTL_V_"},GUIDE_RECT_SIZE:8,GUIDE_MIN_SIZE:18,COLLAPSE_SUFFIX:"_COLLAPSE",COLLAPSE_BBOX_SUFFIX:"_COLLAPSE_BBOX",COLLAPSE_SIZE:10,MOVE_SNAP_SIZE:5,DROP_OVER_BBOX_SUFFIX:"_DROP_OVER",TERMINAL_SUFFIX:{GROUP:"_TERMINAL",BOX:"_TERMINAL_BOX"},TERMINAL_TYPE:{C:"C",E:"E",W:"W",S:"S",N:"N",IN:"IN",OUT:"OUT",INOUT:"INOUT"},TERMINAL_SIZE:3,COPY_PASTE_PADDING:20,AUTO_EXTENSIONAL:true,AUTO_EXTENSION_SIZE:100,SELECTABLE:true,DRAG_SELECTABLE:true,MOVABLE:true,RESIZABLE:true,CONNECTABLE:true,SELF_CONNECTABLE:true,CONNECT_CLONEABLE:true,CONNECT_REQUIRED:true,LABEL_EDITABLE:true,LABEL_EDITABLE_GEOM:true,LABEL_EDITABLE_TEXT:true,LABEL_EDITABLE_HTML:true,LABEL_EDITABLE_EDGE:true,LABEL_EDITABLE_GROUP:true,GROUP_DROPABLE:true,GROUP_COLLAPSIBLE:true,ENABLE_HOTKEY:true,ENABLE_HOTKEY_DELETE:true,ENABLE_HOTKEY_CTRL_A:true,ENABLE_HOTKEY_CTRL_C:true,ENABLE_HOTKEY_CTRL_V:true,ENABLE_HOTKEY_CTRL_G:true,ENABLE_HOTKEY_CTRL_U:true,ENABLE_HOTKEY_ARROW:true};
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
OG.renderer.IRenderer.prototype.constructor=OG.renderer.IRenderer;OG.renderer.RaphaelRenderer=function(l,p,e){var o=new Raphael(l,p?p[0]:null,p?p[1]:null),m=Math.round(Math.random()*10000),h=0,r=new OG.HashMap(),i,s=this,c=e||OG.Constants.CANVAS_BACKGROUND,b,n,t,f,u,d,q,k,a,g,j;
b=function(){var v="OG_"+m+"_"+h;
h++;
return v
};
n=function(w,y,v,x){w.id=y||b();
w.node.id=w.id;
w.node.raphaelid=w.id;
if(v){$(w.node).attr("_type",v)
}if(x){$(w.node).attr("_shape",x)
}r.put(w.id,w);
return w
};
t=function(v){var x,w;
if(v){x=v.node.childNodes;
for(w=x.length-1;
w>=0;
w--){t(u(x[w].id))
}r.remove(v.id);
v.remove()
}};
f=function(v){var x,w;
if(v){x=v.node.childNodes;
for(w=x.length-1;
w>=0;
w--){t(u(x[w].id))
}}};
u=function(v){return r.get(v)
};
d=function(B,D,v,E){var y=0,F="",C,z,w,x={},A=function(O,L){var K,J,H,G,I,M=[],N=function(S,R,Q){var P=Math.PI/180*Q;
return new OG.geometry.Coordinate(OG.Util.round(S.x+R*Math.cos(P)),OG.Util.round(S.y+R*Math.sin(P)))
};
K=OG.JSON.decode(O.toString());
J=O.getVertices();
I=K.angle;
H=N(J[0],L,90+I);
G=N(J[0],L,I);
M=M.concat(["M",H.x,H.y,"Q",J[0].x,J[0].y,G.x,G.y]);
H=N(J[1],L,180+I);
G=N(J[1],L,90+I);
M=M.concat(["L",H.x,H.y,"Q",J[1].x,J[1].y,G.x,G.y]);
H=N(J[2],L,270+I);
G=N(J[2],L,180+I);
M=M.concat(["L",H.x,H.y,"Q",J[2].x,J[2].y,G.x,G.y]);
H=N(J[3],L,I);
G=N(J[3],L,270+I);
M=M.concat(["L",H.x,H.y,"Q",J[3].x,J[3].y,G.x,G.y,"Z"]);
return M.toString()
};
if(E){OG.Util.apply(x,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Util.apply({},D.style.map,OG.Util.apply({},E,OG.Constants.DEFAULT_STYLE.GEOM)))
}else{OG.Util.apply(x,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Util.apply({},D.style.map,OG.Constants.DEFAULT_STYLE.GEOM))
}D.style.map=x;
switch(D.TYPE){case OG.Constants.GEOM_TYPE.POINT:z=o.circle(D.coordinate.x,D.coordinate.y,0.5);
z.attr(x);
break;
case OG.Constants.GEOM_TYPE.LINE:case OG.Constants.GEOM_TYPE.POLYLINE:case OG.Constants.GEOM_TYPE.POLYGON:F="";
C=D.getVertices();
for(y=0;
y<C.length;
y++){if(y===0){F="M"+C[y].x+" "+C[y].y
}else{F+="L"+C[y].x+" "+C[y].y
}}z=o.path(F);
z.attr(x);
break;
case OG.Constants.GEOM_TYPE.RECTANGLE:if((x.r||0)===0){F="";
C=D.getVertices();
for(y=0;
y<C.length;
y++){if(y===0){F="M"+C[y].x+" "+C[y].y
}else{F+="L"+C[y].x+" "+C[y].y
}}}else{F=A(D,x.r||0)
}z=o.path(F);
z.attr(x);
break;
case OG.Constants.GEOM_TYPE.CIRCLE:w=OG.JSON.decode(D.toString());
if(w.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.CIRCLE]){z=o.circle(w.center[0],w.center[1],w.radius)
}else{if(w.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE]){if(w.angle===0){z=o.ellipse(w.center[0],w.center[1],w.radiusX,w.radiusY)
}else{F="";
C=D.getControlPoints();
F="M"+C[1].x+" "+C[1].y+"A"+w.radiusX+" "+w.radiusY+" "+w.angle+" 1 0 "+C[5].x+" "+C[5].y;
F+="M"+C[1].x+" "+C[1].y+"A"+w.radiusX+" "+w.radiusY+" "+w.angle+" 1 1 "+C[5].x+" "+C[5].y;
z=o.path(F)
}}}z.attr(x);
break;
case OG.Constants.GEOM_TYPE.ELLIPSE:w=OG.JSON.decode(D.toString());
if(w.angle===0){z=o.ellipse(w.center[0],w.center[1],w.radiusX,w.radiusY)
}else{F="";
C=D.getControlPoints();
F="M"+C[1].x+" "+C[1].y+"A"+w.radiusX+" "+w.radiusY+" "+w.angle+" 1 0 "+C[5].x+" "+C[5].y;
F+="M"+C[1].x+" "+C[1].y+"A"+w.radiusX+" "+w.radiusY+" "+w.angle+" 1 1 "+C[5].x+" "+C[5].y;
z=o.path(F)
}z.attr(x);
break;
case OG.Constants.GEOM_TYPE.CURVE:F="";
C=D.getControlPoints();
for(y=0;
y<C.length;
y++){if(y===0){F="M"+C[y].x+" "+C[y].y
}else{if(y===1){F+="R"+C[y].x+" "+C[y].y
}else{F+=" "+C[y].x+" "+C[y].y
}}}z=o.path(F);
z.attr(x);
break;
case OG.Constants.GEOM_TYPE.COLLECTION:for(y=0;
y<D.geometries.length;
y++){d(B,D.geometries[y],v,D.style.map)
}break
}if(z){n(z);
B.appendChild(z.node);
return z.node
}else{return B
}};
q=function(x,w,A,z){var v={x:A[0],y:A[1]},y={x:z[0],y:z[1]};
if(x==="c"&&w==="c"){if(v.x<=y.x&&v.y<=y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="e";
w="w"
}else{x="s";
w="n"
}}else{if(v.x<=y.x&&v.y>y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="e";
w="w"
}else{x="n";
w="s"
}}else{if(v.x>y.x&&v.y<=y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="w";
w="e"
}else{x="s";
w="n"
}}else{if(v.x>y.x&&v.y>y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="w";
w="e"
}else{x="n";
w="s"
}}}}}}else{if(x==="c"&&w!=="c"){if(v.x<=y.x&&v.y<=y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="e"
}else{x="s"
}}else{if(v.x<=y.x&&v.y>y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="e"
}else{x="n"
}}else{if(v.x>y.x&&v.y<=y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="w"
}else{x="s"
}}else{if(v.x>y.x&&v.y>y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){x="w"
}else{x="n"
}}}}}}else{if(x!=="c"&&w==="c"){if(v.x<=y.x&&v.y<=y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){w="w"
}else{w="n"
}}else{if(v.x<=y.x&&v.y>y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){w="w"
}else{w="s"
}}else{if(v.x>y.x&&v.y<=y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){w="e"
}else{w="n"
}}else{if(v.x>y.x&&v.y>y.y){if(Math.abs(y.x-v.x)>Math.abs(y.y-v.y)){w="e"
}else{w="s"
}}}}}}}}return x+" "+w
};
k=function(x,C,D){var A={x:C[0],y:C[1]},v={x:D[0],y:D[1]},E=s.drawTerminal(x),y=E.terminal.childNodes,B,z,w;
if(Math.abs(v.x-A.x)>Math.abs(v.y-A.y)){if(v.x>A.x){B="e"
}else{B="w"
}}else{if(v.y>A.y){B="s"
}else{B="n"
}}z=y[0];
for(w=0;
w<y.length;
w++){if(y[w].terminal&&y[w].terminal.direction.toLowerCase()===B){z=y[w];
break
}}return z
};
a=function(x,B,C){var A={x:B[0],y:B[1]},v={x:C[0],y:C[1]},D=s.drawTerminal(x),y=D.terminal.childNodes,z,E,w;
if(Math.abs(v.x-A.x)>Math.abs(v.y-A.y)){if(v.x>A.x){z="w"
}else{z="e"
}}else{if(v.y>A.y){z="n"
}else{z="s"
}}E=y[0];
for(w=0;
w<y.length;
w++){if(y[w].terminal&&y[w].terminal.direction.toLowerCase()===z){E=y[w];
break
}}return E
};
g=function(v){var w=OG.Util.isElement(v)?v.id:v;
if(w){return s.getElementById(w.substring(0,w.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)))
}else{return null
}};
j=function(R,I,K,P,M,B){var A=OG.Constants.LABEL_PADDING,N=K?K[0]-A*2:null,L=K?K[1]-A*2:null,Q=K?K[2]||0:0,E,w,v,D={},F,O,C,z,J,H,G;
OG.Util.apply(D,(P instanceof OG.geometry.Style)?P.map:P||{},OG.Constants.DEFAULT_STYLE.TEXT);
if(M===0||M){E=u(M);
if(E){f(E)
}else{E=o.group();
n(E,M)
}}else{E=o.group();
n(E,M)
}F=D["text-anchor"]||"middle";
D["text-anchor"]="middle";
w=o.text(R[0],R[1],I);
w.attr(D);
C=w.getBBox();
N=N?(N>C.width?N:C.width):C.width;
L=L?(L>C.height?L:C.height):C.height;
z=OG.Util.round(R[0]-N/2);
J=OG.Util.round(R[1]-L/2);
O=new OG.Rectangle([z,J],N,L);
if(D["label-direction"]==="vertical"){switch(F){case"start":G=O.getBoundary().getLowerCenter().y;
break;
case"end":G=O.getBoundary().getUpperCenter().y;
break;
case"middle":G=O.getBoundary().getCentroid().y;
break;
default:G=O.getBoundary().getCentroid().y;
break
}switch(D["vertical-align"]){case"top":H=OG.Util.round(O.getBoundary().getLeftCenter().x+C.height/2);
break;
case"bottom":H=OG.Util.round(O.getBoundary().getRightCenter().x-C.height/2);
break;
case"middle":H=O.getBoundary().getCentroid().x;
break;
default:H=O.getBoundary().getCentroid().x;
break
}Q=-90
}else{switch(F){case"start":H=O.getBoundary().getLeftCenter().x;
break;
case"end":H=O.getBoundary().getRightCenter().x;
break;
case"middle":H=O.getBoundary().getCentroid().x;
break;
default:H=O.getBoundary().getCentroid().x;
break
}switch(D["vertical-align"]){case"top":G=OG.Util.round(O.getBoundary().getUpperCenter().y+C.height/2);
break;
case"bottom":G=OG.Util.round(O.getBoundary().getLowerCenter().y-C.height/2);
break;
case"middle":G=O.getBoundary().getCentroid().y;
break;
default:G=O.getBoundary().getCentroid().y;
break
}}w.attr({x:H,y:G,stroke:"none",fill:D["font-color"]||OG.Constants.DEFAULT_STYLE.LABEL["font-color"],"font-size":D["font-size"]||OG.Constants.DEFAULT_STYLE.LABEL["font-size"],"fill-opacity":1});
if(Q||D["label-angle"]){if(Q===0){Q=parseInt(D["label-angle"],10)
}w.rotate(Q)
}w.attr({"text-anchor":F});
if(B&&I){C=w.getBBox();
v=o.rect(C.x-A/2,C.y-A/2,C.width+A,C.height+A);
v.attr({stroke:"none",fill:c,"fill-opacity":1});
n(v);
E.node.appendChild(v.node)
}n(w);
E.node.appendChild(w.node);
return E.node
};
i=n(o.group(),null,OG.Constants.NODE_TYPE.ROOT);
o.id="OG_"+m;
o.canvas.id="OG_"+m;
$(o.canvas).css({"background-color":c});
if($(o.canvas.parentNode).css("position")==="static"){$(o.canvas.parentNode).css({position:"relative",left:"0",top:"0"})
}this.drawShape=function(B,C,I,v,w){var x=I?I[0]:100,H=I?I[1]:100,y,E,G,z,A,D,F;
if(C instanceof OG.shape.GeomShape){E=C.createShape();
E.moveCentroid(B);
E.resizeBox(x,H);
y=this.drawGeom(E,v,w);
C.geom=y.geom
}else{if(C instanceof OG.shape.TextShape){G=C.createShape();
y=this.drawText(B,G,I,v,w);
C.text=y.text;
C.angle=y.angle;
C.geom=y.geom
}else{if(C instanceof OG.shape.ImageShape){z=C.createShape();
y=this.drawImage(B,z,I,v,w);
C.image=y.image;
C.angle=y.angle;
C.geom=y.geom
}else{if(C instanceof OG.shape.HtmlShape){A=C.createShape();
y=this.drawHtml(B,A,I,v,w);
C.html=y.html;
C.angle=y.angle;
C.geom=y.geom
}else{if(C instanceof OG.shape.EdgeShape){E=C.geom||C.createShape();
y=this.drawEdge(E,v,w);
C.geom=y.geom
}else{if(C instanceof OG.shape.GroupShape){E=C.createShape();
E.moveCentroid(B);
E.resizeBox(x,H);
y=this.drawGroup(E,v,w);
C.geom=y.geom
}}}}}}if(C.geom){y.shape=C
}y.shapeStyle=(v instanceof OG.geometry.Style)?v.map:v;
$(y).attr("_shape_id",C.SHAPE_ID);
if(!(C instanceof OG.shape.TextShape)){this.drawLabel(y);
if(C instanceof OG.shape.EdgeShape){this.drawEdgeLabel(y,null,"FROM");
this.drawEdgeLabel(y,null,"TO")
}}if(y.geom){if(OG.Util.isIE7()){y.removeAttribute("geom")
}else{delete y.geom
}}if(y.text){if(OG.Util.isIE7()){y.removeAttribute("text")
}else{delete y.text
}}if(y.image){if(OG.Util.isIE7()){y.removeAttribute("image")
}else{delete y.image
}}if(y.angle){if(OG.Util.isIE7()){y.removeAttribute("angle")
}else{delete y.angle
}}$(o.canvas).trigger("drawShape",[y]);
return y
};
this.drawGeom=function(y,w,z){var x,v={};
OG.Util.apply(v,(w instanceof OG.geometry.Style)?w.map:w||{});
if(z===0||z){x=u(z);
if(x){f(x)
}else{x=o.group();
n(x,z,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GEOM);
i.node.appendChild(x.node)
}}else{x=o.group();
n(x,z,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GEOM);
i.node.appendChild(x.node)
}d(x.node,y,v);
x.node.geom=y;
x.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(x.node.shape){x.node.shape.geom=y;
if(x.node.geom){if(OG.Util.isIE7()){x.node.removeAttribute("geom")
}else{delete x.node.geom
}}}return x.node
};
this.drawText=function(E,K,N,v,w){var z=N?N[0]:null,L=N?N[1]:null,C=N?N[2]||0:0,J,D,B={},H,M,A,G,I,F;
OG.Util.apply(B,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Constants.DEFAULT_STYLE.TEXT);
if(w===0||w){J=u(w);
if(J){f(J)
}else{J=o.group();
n(J,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.TEXT);
i.node.appendChild(J.node)
}}else{J=o.group();
n(J,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.TEXT);
i.node.appendChild(J.node)
}D=o.text(E[0],E[1],K);
D.attr(B);
M=D.getBBox();
z=z?(z>M.width?z:M.width):M.width;
L=L?(L>M.height?L:M.height):M.height;
A=OG.Util.round(E[0]-z/2);
G=OG.Util.round(E[1]-L/2);
H=new OG.Rectangle([A,G],z,L);
H.style.map=B;
switch(B["text-anchor"]){case"start":I=H.getBoundary().getLeftCenter().x;
break;
case"end":I=H.getBoundary().getRightCenter().x;
break;
case"middle":I=H.getBoundary().getCentroid().x;
break;
default:I=H.getBoundary().getCentroid().x;
break
}switch(B["vertical-align"]){case"top":F=OG.Util.round(H.getBoundary().getUpperCenter().y+M.height/2);
break;
case"bottom":F=OG.Util.round(H.getBoundary().getLowerCenter().y-M.height/2);
break;
case"middle":F=H.getBoundary().getCentroid().y;
break;
default:F=H.getBoundary().getCentroid().y;
break
}D.attr({x:I,y:F});
D.attr({stroke:"none",fill:B["font-color"]||OG.Constants.DEFAULT_STYLE.LABEL["font-color"],"font-size":B["font-size"]||OG.Constants.DEFAULT_STYLE.LABEL["font-size"]});
if(C){D.rotate(C)
}n(D);
J.node.appendChild(D.node);
J.node.text=K;
J.node.angle=C;
J.node.geom=H;
J.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(J.node.shape){J.node.shape.text=K;
J.node.shape.angle=C;
J.node.shape.geom=H;
if(J.node.text){if(OG.Util.isIE7()){J.node.removeAttribute("text")
}else{delete J.node.text
}}if(J.node.angle){if(OG.Util.isIE7()){J.node.removeAttribute("angle")
}else{delete J.node.angle
}}if(J.node.geom){if(OG.Util.isIE7()){J.node.removeAttribute("geom")
}else{delete J.node.geom
}}}return J.node
};
this.drawHtml=function(D,C,J,v,w){var x=J?J[0]:null,H=J?J[1]:null,A=J?J[2]||0:0,G,B,z={},I,F,y,E;
OG.Util.apply(z,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Constants.DEFAULT_STYLE.HTML);
if(w===0||w){G=u(w);
if(G){f(G)
}else{G=o.group();
n(G,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.HTML);
i.node.appendChild(G.node)
}}else{G=o.group();
n(G,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.HTML);
i.node.appendChild(G.node)
}B=o.foreignObject(C,D[0],D[1],x,H);
B.attr(z);
I=B.getBBox();
x=x||I.width;
H=H||I.height;
y=OG.Util.round(D[0]-x/2);
E=OG.Util.round(D[1]-H/2);
B.attr({x:y,y:E});
F=new OG.Rectangle([y,E],x,H);
if(A){B.rotate(A)
}F.style.map=z;
n(B);
G.node.appendChild(B.node);
G.node.html=C;
G.node.angle=A;
G.node.geom=F;
G.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(G.node.shape){G.node.shape.html=C;
G.node.shape.angle=A;
G.node.shape.geom=F;
if(G.node.html){if(OG.Util.isIE7()){G.node.removeAttribute("html")
}else{delete G.node.html
}}if(G.node.angle){if(OG.Util.isIE7()){G.node.removeAttribute("angle")
}else{delete G.node.angle
}}if(G.node.geom){if(OG.Util.isIE7()){G.node.removeAttribute("geom")
}else{delete G.node.geom
}}}return G.node
};
this.drawImage=function(C,I,J,v,w){var x=J?J[0]:null,G=J?J[1]:null,A=J?J[2]||0:0,F,B,z={},H,E,y,D;
OG.Util.apply(z,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Constants.DEFAULT_STYLE.IMAGE);
if(w===0||w){F=u(w);
if(F){f(F)
}else{F=o.group();
n(F,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.IMAGE);
i.node.appendChild(F.node)
}}else{F=o.group();
n(F,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.IMAGE);
i.node.appendChild(F.node)
}B=o.image(I,C[0],C[1],x,G);
B.attr(z);
H=B.getBBox();
x=x||H.width;
G=G||H.height;
y=OG.Util.round(C[0]-x/2);
D=OG.Util.round(C[1]-G/2);
B.attr({x:y,y:D});
E=new OG.Rectangle([y,D],x,G);
if(A){B.rotate(A)
}E.style.map=z;
n(B);
F.node.appendChild(B.node);
F.node.image=I;
F.node.angle=A;
F.node.geom=E;
F.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(F.node.shape){F.node.shape.image=I;
F.node.shape.angle=A;
F.node.shape.geom=E;
if(F.node.image){if(OG.Util.isIE7()){F.node.removeAttribute("image")
}else{delete F.node.image
}}if(F.node.angle){if(OG.Util.isIE7()){F.node.removeAttribute("angle")
}else{delete F.node.angle
}}if(F.node.geom){if(OG.Util.isIE7()){F.node.removeAttribute("geom")
}else{delete F.node.geom
}}}return F.node
};
this.drawEdge=function(I,v,w,z){var G,y={},A=I.getVertices(),D=A[0],C=A[A.length-1],F=[],x,B,H=function(L,K,J){if(J){return[[L[0],L[1]],[K[0],L[1]],[K[0],K[1]]]
}else{return[[L[0],L[1]],[L[0],K[1]],[K[0],K[1]]]
}},E=function(L,K,J){if(J){return[[L[0],L[1]],[OG.Util.round((L[0]+K[0])/2),L[1]],[OG.Util.round((L[0]+K[0])/2),K[1]],[K[0],K[1]]]
}else{return[[L[0],L[1]],[L[0],OG.Util.round((L[1]+K[1])/2)],[K[0],OG.Util.round((L[1]+K[1])/2)],[K[0],K[1]]]
}};
OG.Util.apply(y,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Util.apply({},I.style.map,OG.Constants.DEFAULT_STYLE.EDGE));
if(w===0||w){G=u(w);
if(G){f(G)
}else{G=o.group();
n(G,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.EDGE);
i.node.appendChild(G.node)
}}else{G=o.group();
n(G,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.EDGE);
i.node.appendChild(G.node)
}if(z){F=[[D.x,D.y-OG.Constants.GUIDE_RECT_SIZE/2],[D.x+OG.Constants.GUIDE_RECT_SIZE*2,D.y-OG.Constants.GUIDE_RECT_SIZE],[D.x+OG.Constants.GUIDE_RECT_SIZE*2,D.y+OG.Constants.GUIDE_RECT_SIZE],[D.x,D.y+OG.Constants.GUIDE_RECT_SIZE/2]]
}else{if(I instanceof OG.geometry.Line){switch(y["edge-type"].toLowerCase()){case OG.Constants.EDGE_TYPE.STRAIGHT:F=[D,C];
break;
case OG.Constants.EDGE_TYPE.PLAIN:B=y["edge-direction"].toLowerCase().split(" ");
if(B[0]==="c"||B[1]==="c"){B=q(B[0],B[1],[D.x,D.y],[C.x,C.y]).split(" ")
}if(B[0]==="e"){switch(B[1]){case"e":if(D.x<=C.x){F=H([D.x,D.y],[C.x+OG.Constants.EDGE_PADDING,C.y],true);
F.push([C.x,C.y])
}else{F=[[D.x,D.y]];
F=F.concat(H([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x,C.y],false))
}break;
case"w":if(D.x<=C.x){F=E([D.x,D.y],[C.x,C.y],true)
}else{F=[[D.x,D.y]];
F=F.concat(E([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x-OG.Constants.EDGE_PADDING,C.y],false));
F.push([C.x,C.y])
}break;
case"s":if(D.x<=C.x&&D.y<=C.y){F=E([D.x,D.y],[C.x,C.y+OG.Constants.EDGE_PADDING],true);
F.push([C.x,C.y])
}else{if(D.x<=C.x&&D.y>C.y){F=H([D.x,D.y],[C.x,C.y],true)
}else{if(D.x>C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x,C.y+OG.Constants.EDGE_PADDING],false));
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x,C.y],false))
}}}}break;
case"n":if(D.x<=C.x&&D.y<=C.y){F=H([D.x,D.y],[C.x,C.y],true)
}else{if(D.x<=C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x,C.y-OG.Constants.EDGE_PADDING],false));
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x,C.y],false))
}else{if(D.x>C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x+OG.Constants.EDGE_PADDING,D.y],[C.x,C.y-OG.Constants.EDGE_PADDING],false));
F.push([C.x,C.y])
}}}}break
}}else{if(B[0]==="w"){switch(B[1]){case"e":if(D.x<=C.x){F=[[D.x,D.y]];
F=F.concat(E([D.x-OG.Constants.EDGE_PADDING,D.y],[C.x+OG.Constants.EDGE_PADDING,C.y],false));
F.push([C.x,C.y])
}else{F=E([D.x,D.y],[C.x,C.y],true)
}break;
case"w":if(D.x<=C.x){F=[[D.x,D.y]];
F=F.concat(H([D.x-OG.Constants.EDGE_PADDING,D.y],[C.x,C.y],false))
}else{F=H([D.x,D.y],[C.x-OG.Constants.EDGE_PADDING,C.y],true);
F.push([C.x,C.y])
}break;
case"s":if(D.x<=C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x-OG.Constants.EDGE_PADDING,D.y],[C.x,C.y+OG.Constants.EDGE_PADDING],false));
F.push([C.x,C.y])
}else{if(D.x<=C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x-OG.Constants.EDGE_PADDING,D.y],[C.x,C.y],false))
}else{if(D.x>C.x&&D.y<=C.y){F=E([D.x,D.y],[C.x,C.y+OG.Constants.EDGE_PADDING],true);
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y>C.y){F=H([D.x,D.y],[C.x,C.y],true)
}}}}break;
case"n":if(D.x<=C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x-OG.Constants.EDGE_PADDING,D.y],[C.x,C.y],false))
}else{if(D.x<=C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x-OG.Constants.EDGE_PADDING,D.y],[C.x,C.y-OG.Constants.EDGE_PADDING],false));
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y<=C.y){F=F.concat(H([D.x,D.y],[C.x,C.y],true))
}else{if(D.x>C.x&&D.y>C.y){F=E([D.x,D.y],[C.x,C.y-OG.Constants.EDGE_PADDING],true);
F.push([C.x,C.y])
}}}}break
}}else{if(B[0]==="s"){switch(B[1]){case"e":if(D.x<=C.x&&D.y<=C.y){F=E([D.x,D.y],[C.x+OG.Constants.EDGE_PADDING,C.y],false);
F.push([C.x,C.y])
}else{if(D.x<=C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x,D.y+OG.Constants.EDGE_PADDING],[C.x+OG.Constants.EDGE_PADDING,C.y],true));
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y<=C.y){F=H([D.x,D.y],[C.x,C.y],false)
}else{if(D.x>C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x,D.y+OG.Constants.EDGE_PADDING],[C.x,C.y],true))
}}}}break;
case"w":if(D.x<=C.x&&D.y<=C.y){F=H([D.x,D.y],[C.x,C.y],false)
}else{if(D.x<=C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x,D.y+OG.Constants.EDGE_PADDING],[C.x,C.y],true))
}else{if(D.x>C.x&&D.y<=C.y){F=E([D.x,D.y],[C.x-OG.Constants.EDGE_PADDING,C.y],false);
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y>C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x,D.y+OG.Constants.EDGE_PADDING],[C.x-OG.Constants.EDGE_PADDING,C.y],true));
F.push([C.x,C.y])
}}}}break;
case"s":if(D.y<=C.y){F=H([D.x,D.y],[C.x,C.y+OG.Constants.EDGE_PADDING],false);
F.push([C.x,C.y])
}else{F=[[D.x,D.y]];
F=F.concat(H([D.x,D.y+OG.Constants.EDGE_PADDING],[C.x,C.y],true))
}break;
case"n":if(D.y<=C.y){F=E([D.x,D.y],[C.x,C.y],false)
}else{F=[[D.x,D.y]];
F=F.concat(E([D.x,D.y+OG.Constants.EDGE_PADDING],[C.x,C.y-OG.Constants.EDGE_PADDING],true));
F.push([C.x,C.y])
}break
}}else{if(B[0]==="n"){switch(B[1]){case"e":if(D.x<=C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x,D.y-OG.Constants.EDGE_PADDING],[C.x+OG.Constants.EDGE_PADDING,C.y],true));
F.push([C.x,C.y])
}else{if(D.x<=C.x&&D.y>C.y){F=E([D.x,D.y],[C.x+OG.Constants.EDGE_PADDING,C.y],false);
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x,D.y-OG.Constants.EDGE_PADDING],[C.x,C.y],true))
}else{if(D.x>C.x&&D.y>C.y){F=H([D.x,D.y],[C.x,C.y],false)
}}}}break;
case"w":if(D.x<=C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x,D.y-OG.Constants.EDGE_PADDING],[C.x,C.y],true))
}else{if(D.x<=C.x&&D.y>C.y){F=H([D.x,D.y],[C.x,C.y],false)
}else{if(D.x>C.x&&D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x,D.y-OG.Constants.EDGE_PADDING],[C.x-OG.Constants.EDGE_PADDING,C.y],true));
F.push([C.x,C.y])
}else{if(D.x>C.x&&D.y>C.y){F=E([D.x,D.y],[C.x-OG.Constants.EDGE_PADDING,C.y],false);
F.push([C.x,C.y])
}}}}break;
case"s":if(D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(E([D.x,D.y-OG.Constants.EDGE_PADDING],[C.x,C.y+OG.Constants.EDGE_PADDING],true));
F.push([C.x,C.y])
}else{F=E([D.x,D.y],[C.x,C.y],false)
}break;
case"n":if(D.y<=C.y){F=[[D.x,D.y]];
F=F.concat(H([D.x,D.y-OG.Constants.EDGE_PADDING],[C.x,C.y],true))
}else{F=H([D.x,D.y],[C.x,C.y-OG.Constants.EDGE_PADDING],false);
F.push([C.x,C.y])
}break
}}}}}break;
case OG.Constants.EDGE_TYPE.BEZIER:break
}}else{if(I instanceof OG.geometry.Curve){F=I.getControlPoints()
}else{F=A
}}}if(z){x=new OG.Curve(F)
}else{if(I instanceof OG.geometry.Curve){x=new OG.Curve(F)
}else{x=new OG.PolyLine(F)
}}d(G.node,x,OG.Constants.DEFAULT_STYLE.EDGE_HIDDEN);
d(G.node,x,y);
G.node.geom=x;
G.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(G.node.shape){G.node.shape.geom=x;
if(G.node.geom){if(OG.Util.isIE7()){G.node.removeAttribute("geom")
}else{delete G.node.geom
}}}return G.node
};
this.drawGroup=function(C,w,x){var D,F,z={},E,A,y,v,B={};
OG.Util.apply(z,(w instanceof OG.geometry.Style)?w.map:w||{});
if(x===0||x){D=u(x);
if(D){E=D.node.childNodes;
for(A=E.length-1;
A>=0;
A--){if($(E[A]).attr("_type")!==OG.Constants.NODE_TYPE.SHAPE){t(u(E[A].id))
}}}else{D=o.group();
n(D,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GROUP);
i.node.appendChild(D.node)
}}else{D=o.group();
n(D,x,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GROUP);
i.node.appendChild(D.node)
}F=d(D.node,C,z);
D.node.geom=C;
D.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
OG.Util.apply(B,C.style.map,z);
if(B["label-direction"]&&B["vertical-align"]==="top"){y=C.getBoundary();
if(B["label-direction"]==="vertical"){v=new OG.geometry.Line([y.getUpperLeft().x+20,y.getUpperLeft().y],[y.getLowerLeft().x+20,y.getLowerLeft().y])
}else{v=new OG.geometry.Line([y.getUpperLeft().x,y.getUpperLeft().y+20],[y.getUpperRight().x,y.getUpperRight().y+20])
}d(D.node,v,z)
}if(F.id!==D.node.firstChild.id){D.node.insertBefore(F,D.node.firstChild)
}if(D.node.shape){if(!D.node.shape.isCollapsed||D.node.shape.isCollapsed===false){D.node.shape.geom=C
}if(D.node.geom){if(OG.Util.isIE7()){D.node.removeAttribute("geom")
}else{delete D.node.geom
}}}return D.node
};
this.drawLabel=function(D,F,w){var A=u(OG.Util.isElement(D)?D.id:D),y,B,C,x={},z,G,E=function(L){var J,H,M=0,K,I;
J=L.shape.geom.getVertices();
H=L.shape.geom.getLength();
for(K=0;
K<J.length-1;
K++){M+=J[K].distance(J[K+1]);
if(M>H/2){I=L.shape.geom.intersectCircleToLine(J[K+1],M-H/2,J[K+1],J[K]);
break
}}return I[0]
},v;
OG.Util.apply(x,(w instanceof OG.geometry.Style)?w.map:w||{});
if(A&&A.node.shape){y=A.node;
C=y.shape.geom.getBoundary();
OG.Util.apply(y.shape.geom.style.map,x);
y.shape.label=F===undefined?y.shape.label:F;
if(y.shape.label!==undefined){if(y.shape instanceof OG.shape.EdgeShape){v=E(y);
z=[v.x,v.y];
G=[0,0]
}else{switch(y.shape.geom.style.get("label-position")){case"left":z=[C.getCentroid().x-C.getWidth(),C.getCentroid().y];
break;
case"right":z=[C.getCentroid().x+C.getWidth(),C.getCentroid().y];
break;
case"top":z=[C.getCentroid().x,C.getCentroid().y-C.getHeight()];
break;
case"bottom":z=[C.getCentroid().x,C.getCentroid().y+C.getHeight()];
break;
default:z=[C.getCentroid().x,C.getCentroid().y];
break
}G=[C.getWidth(),C.getHeight()]
}B=j(z,y.shape.label,G,y.shape.geom.style,y.id+OG.Constants.LABEL_SUFFIX,y.shape instanceof OG.shape.EdgeShape);
y.appendChild(B)
}}return B
};
this.drawEdgeLabel=function(C,E,B){var y=u(OG.Util.isElement(C)?C.id:C),w,z,A,x,v,D;
if(y&&y.node.shape){w=y.node;
if(w.shape instanceof OG.shape.EdgeShape){z=w.shape.geom.getVertices();
if(B==="FROM"){x=[z[0].x,z[0].y+OG.Constants.FROMTO_LABEL_OFFSET_TOP];
w.shape.fromLabel=E||w.shape.fromLabel;
v=w.shape.fromLabel;
D=OG.Constants.FROM_LABEL_SUFFIX
}else{x=[z[z.length-1].x,z[z.length-1].y+OG.Constants.FROMTO_LABEL_OFFSET_TOP];
w.shape.toLabel=E||w.shape.toLabel;
v=w.shape.toLabel;
D=OG.Constants.TO_LABEL_SUFFIX
}if(v){A=j(x,v,[0,0],w.shape.geom.style,w.id+D,false);
w.appendChild(A)
}}}return A
};
this.redrawShape=function(z,C){var A=this,B,w,x,D,v,y;
y=function(M,H){var N,L,J,F=H.childNodes,E,K,I,G;
for(K=F.length-1;
K>=0;
K--){if($(F[K]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){y(M,F[K]);
G=false;
N=$(F[K]).attr("_fromedge");
if(N){N=N.split(",");
for(I=0;
I<N.length;
I++){L=A.getElementById(N[I]);
if(L){E=g($(L).attr("_from"));
if($(E).parents("#"+M.id).length===0){G=true
}}}}N=$(F[K]).attr("_toedge");
if(N){N=N.split(",");
for(I=0;
I<N.length;
I++){J=A.getElementById(N[I]);
if(J){E=g($(J).attr("_to"));
if($(E).parents("#"+M.id).length===0){G=true
}}}}if(G===true){A.redrawConnectedEdge(F[K])
}}}};
if(z&&z.shape.geom){switch($(z).attr("_shape")){case OG.Constants.SHAPE_TYPE.GEOM:z=this.drawGeom(z.shape.geom,{},z.id);
this.redrawConnectedEdge(z,C);
this.drawLabel(z);
break;
case OG.Constants.SHAPE_TYPE.TEXT:B=z.shape.geom.getBoundary();
w=B.getCentroid();
x=B.getWidth();
D=B.getHeight();
z=this.drawText([w.x,w.y],z.shape.text,[x,D,z.shape.angle],z.shape.geom.style,z.id);
this.redrawConnectedEdge(z,C);
break;
case OG.Constants.SHAPE_TYPE.IMAGE:B=z.shape.geom.getBoundary();
w=B.getCentroid();
x=B.getWidth();
D=B.getHeight();
z=this.drawImage([w.x,w.y],z.shape.image,[x,D,z.shape.angle],z.shape.geom.style,z.id);
this.redrawConnectedEdge(z,C);
this.drawLabel(z);
break;
case OG.Constants.SHAPE_TYPE.HTML:B=z.shape.geom.getBoundary();
w=B.getCentroid();
x=B.getWidth();
D=B.getHeight();
z=this.drawHtml([w.x,w.y],z.shape.html,[x,D,z.shape.angle],z.shape.geom.style,z.id);
this.redrawConnectedEdge(z,C);
this.drawLabel(z);
break;
case OG.Constants.SHAPE_TYPE.EDGE:z=this.drawEdge(z.shape.geom,z.shape.geom.style,z.id);
this.drawLabel(z);
this.drawEdgeLabel(z,null,"FROM");
this.drawEdgeLabel(z,null,"TO");
break;
case OG.Constants.SHAPE_TYPE.GROUP:if(z.shape.isCollapsed){B=z.shape.geom.getBoundary();
v=B.getUpperLeft();
z=this.drawGroup(new OG.geometry.Rectangle(v,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2),z.shape.geom.style,z.id);
y(z,z)
}else{z=this.drawGroup(z.shape.geom,z.shape.geom.style,z.id);
this.redrawConnectedEdge(z,C);
this.drawLabel(z)
}break
}}$(o.canvas).trigger("redrawShape",[z]);
return z
};
this.redrawConnectedEdge=function(w,y){var z,x=this,v=function(W){var E,V,Q,Z,M,G,L,N,C,F,X,B,R,S,H,J,I,T,K,P,D,Y,A,O,U;
E=x.getElementById(W);
V=$(E).attr("_from");
Q=$(E).attr("_to");
if(V){Z=g(V);
G=parseInt(V.substring(V.lastIndexOf("_")+1),10);
N=Z.shape.createTerminal()[G];
X=N.direction.toLowerCase();
R=N.position
}else{F=E.shape.geom.getVertices();
X="c";
R=F[0]
}if(Q){M=g(Q);
L=parseInt(Q.substring(Q.lastIndexOf("_")+1),10);
C=M.shape.createTerminal()[L];
B=C.direction.toLowerCase();
S=C.position
}else{F=E.shape.geom.getVertices();
B="c";
S=F[F.length-1]
}H=R;
J=S;
I=X;
T=B;
if(Z&&X==="c"){K=x.intersectionEdge(E.shape.geom.style.get("edge-type"),Z,[H.x,H.y],[J.x,J.y],true);
R=K.position;
X=K.direction
}if(M&&B==="c"){K=x.intersectionEdge(E.shape.geom.style.get("edge-type"),M,[H.x,H.y],[J.x,J.y],false);
S=K.position;
B=K.direction
}P=Z&&M&&Z.id===M.id;
if(P){R=S=Z.shape.geom.getBoundary().getRightCenter()
}else{if(Z){D=$(Z).parents("[_collapsed=true]");
if(D.length!==0){Y=D[D.length-1].shape.geom.getBoundary();
A=Y.getUpperLeft();
O=new OG.geometry.Rectangle(A,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2);
switch(X.toUpperCase()){case OG.Constants.TERMINAL_TYPE.E:U=O.getBoundary().getRightCenter();
break;
case OG.Constants.TERMINAL_TYPE.W:U=O.getBoundary().getLeftCenter();
break;
case OG.Constants.TERMINAL_TYPE.S:U=O.getBoundary().getLowerCenter();
break;
case OG.Constants.TERMINAL_TYPE.N:U=O.getBoundary().getUpperCenter();
break
}if(U){R=[U.x,U.y]
}}}if(M){D=$(M).parents("[_collapsed=true]");
if(D.length!==0){Y=D[D.length-1].shape.geom.getBoundary();
A=Y.getUpperLeft();
O=new OG.geometry.Rectangle(A,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2);
switch(B.toUpperCase()){case OG.Constants.TERMINAL_TYPE.E:U=O.getBoundary().getRightCenter();
break;
case OG.Constants.TERMINAL_TYPE.W:U=O.getBoundary().getLeftCenter();
break;
case OG.Constants.TERMINAL_TYPE.S:U=O.getBoundary().getLowerCenter();
break;
case OG.Constants.TERMINAL_TYPE.N:U=O.getBoundary().getUpperCenter();
break
}if(U){S=[U.x,U.y]
}}}}E=x.drawEdge(new OG.Line(R,S),OG.Util.apply(E.shape.geom.style.map,{"edge-direction":X+" "+B}),E.id,P);
x.drawLabel(E);
OG.Util.apply(E.shape.geom.style.map,{"edge-direction":I+" "+T})
};
z=$(w).attr("_fromedge");
if(z){$.each(z.split(","),function(A,B){if(!y||y.toString().indexOf(B)<0){v(B)
}})
}z=$(w).attr("_toedge");
if(z){$.each(z.split(","),function(A,B){if(!y||y.toString().indexOf(B)<0){v(B)
}})
}this.removeAllTerminal()
};
this.connect=function(J,v,z,M,E){var D={},O,G,F,I,K,A,B,N,w,C,L,H,y,x=function(R,Q,S){var T=$(R).attr(Q),U=T?T.split(","):[],P=[];
$.each(U,function(V,W){if(W!==S){P.push(W)
}});
P.push(S);
$(R).attr(Q,P.toString());
return R
};
OG.Util.apply(D,(M instanceof OG.geometry.Style)?M.map:M||{},OG.Constants.DEFAULT_STYLE.EDGE);
if(OG.Util.isElement(J)){O=g(J);
I=[J.terminal.position.x,J.terminal.position.y];
N=J.terminal.direction.toLowerCase()
}else{I=J;
N="c"
}if(OG.Util.isElement(v)){G=g(v);
K=[v.terminal.position.x,v.terminal.position.y];
w=v.terminal.direction.toLowerCase()
}else{K=v;
w="c"
}if(O&&G){y=jQuery.Event("beforeConnectShape",{edge:z,fromShape:O,toShape:G});
$(o.canvas).trigger(y);
if(y.isPropagationStopped()){this.remove(z);
return false
}}A=I;
B=K;
C=N;
L=w;
if(O&&N==="c"){F=this.intersectionEdge(D["edge-type"],O,A,B,true);
I=F.position;
N=F.direction
}if(G&&w==="c"){F=this.intersectionEdge(D["edge-type"],G,A,B,false);
K=F.position;
w=F.direction
}H=O&&G&&O.id===G.id;
if(H){I=K=O.shape.geom.getBoundary().getRightCenter()
}z=this.drawEdge(new OG.Line(I,K),OG.Util.apply(D,{"edge-direction":N+" "+w}),z?z.id:null,H);
this.drawLabel(z,E);
this.drawEdgeLabel(z,null,"FROM");
this.drawEdgeLabel(z,null,"TO");
OG.Util.apply(z.shape.geom.style.map,{"edge-direction":C+" "+L});
z.shapeStyle=z.shape.geom.style.map;
this.disconnect(z);
if(OG.Util.isElement(J)){$(z).attr("_from",J.id);
x(O,"_toedge",z.id)
}if(OG.Util.isElement(v)){$(z).attr("_to",v.id);
x(G,"_fromedge",z.id)
}this.removeAllTerminal();
if(O&&G){$(o.canvas).trigger("connectShape",[z,O,G])
}return z
};
this.disconnect=function(A){var D=this,E,B,F,y,w,v,C,z,x=function(I,H,J){var K=$(I).attr(H),L=K?K.split(","):[],G=[];
$.each(L,function(M,N){if(N!==J){G.push(N)
}});
$(I).attr(H,G.toString());
return I
};
if(A){if($(A).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){E=$(A).attr("_from");
B=$(A).attr("_to");
if(E){F=g(E);
x(F,"_toedge",A.id);
$(A).removeAttr("_from")
}if(B){y=g(B);
x(y,"_fromedge",A.id);
$(A).removeAttr("_to")
}if(F&&y){$(o.canvas).trigger("disconnectShape",[A,F,y])
}}else{w=$(A).attr("_fromedge");
v=$(A).attr("_toedge");
if(w){$.each(w.split(","),function(G,H){C=D.getElementById(H);
E=$(C).attr("_from");
if(E){F=g(E);
x(F,"_toedge",H)
}if(F&&A){$(o.canvas).trigger("disconnectShape",[C,F,A])
}D.remove(C)
})
}if(v){$.each(v.split(","),function(G,H){z=D.getElementById(H);
B=$(z).attr("_to");
if(B){y=g(B);
x(y,"_fromedge",H)
}if(A&&y){$(o.canvas).trigger("disconnectShape",[z,A,y])
}D.remove(z)
})
}}}};
this.drawDropOverGuide=function(z){var w=u(OG.Util.isElement(z)?z.id:z),C=w?w.node.shape.geom:null,B,y,A,v=OG.Constants.GUIDE_RECT_SIZE/2,x=v/2;
if(w&&C&&$(z).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE&&!u(w.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)){B=C.getBoundary();
y=B.getUpperLeft();
A=o.rect(y.x-x,y.y-x,B.getWidth()+v,B.getHeight()+v);
A.attr(OG.Util.apply({"stroke-width":v},OG.Constants.DEFAULT_STYLE.DROP_OVER_BBOX));
n(A,w.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
A.insertAfter(w)
}};
this.drawGuide=function(x){var N=u(OG.Util.isElement(x)?x.id:x),D=N?N.node.shape.geom:null,w,G,R,F,J,z,B,K,I,Q,T,A,O,H,M,E,v,y,P,S,C=OG.Constants.GUIDE_RECT_SIZE,L=OG.Util.round(C/2);
if(N&&D){if($(x).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){return this.drawEdgeGuide(x)
}else{w=D.getBoundary();
J=w.getUpperLeft();
z=w.getUpperRight();
B=w.getLowerLeft();
K=w.getLowerRight();
I=w.getLeftCenter();
Q=w.getUpperCenter();
T=w.getRightCenter();
A=w.getLowerCenter();
if(u(N.id+OG.Constants.GUIDE_SUFFIX.GUIDE)){t(u(N.id+OG.Constants.GUIDE_SUFFIX.BBOX));
F=o.rect(J.x,J.y,w.getWidth(),w.getHeight());
F.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
n(F,N.id+OG.Constants.GUIDE_SUFFIX.BBOX);
F.insertBefore(N);
O=u(N.id+OG.Constants.GUIDE_SUFFIX.UL);
H=u(N.id+OG.Constants.GUIDE_SUFFIX.UR);
M=u(N.id+OG.Constants.GUIDE_SUFFIX.LL);
E=u(N.id+OG.Constants.GUIDE_SUFFIX.LR);
v=u(N.id+OG.Constants.GUIDE_SUFFIX.LC);
y=u(N.id+OG.Constants.GUIDE_SUFFIX.UC);
P=u(N.id+OG.Constants.GUIDE_SUFFIX.RC);
S=u(N.id+OG.Constants.GUIDE_SUFFIX.LWC);
O.attr({x:J.x-L,y:J.y-L});
H.attr({x:z.x-L,y:z.y-L});
M.attr({x:B.x-L,y:B.y-L});
E.attr({x:K.x-L,y:K.y-L});
v.attr({x:I.x-L,y:I.y-L});
y.attr({x:Q.x-L,y:Q.y-L});
P.attr({x:T.x-L,y:T.y-L});
S.attr({x:A.x-L,y:A.y-L});
return null
}G=u(N.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
if(G){t(G);
t(u(N.id+OG.Constants.GUIDE_SUFFIX.BBOX))
}G=o.group();
F=o.rect(J.x,J.y,w.getWidth(),w.getHeight());
O=o.rect(J.x-L,J.y-L,C,C);
H=o.rect(z.x-L,z.y-L,C,C);
M=o.rect(B.x-L,B.y-L,C,C);
E=o.rect(K.x-L,K.y-L,C,C);
v=o.rect(I.x-L,I.y-L,C,C);
y=o.rect(Q.x-L,Q.y-L,C,C);
P=o.rect(T.x-L,T.y-L,C,C);
S=o.rect(A.x-L,A.y-L,C,C);
F.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
O.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UL);
H.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UR);
M.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LL);
E.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LR);
v.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LC);
y.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UC);
P.attr(OG.Constants.DEFAULT_STYLE.GUIDE_RC);
S.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LWC);
G.appendChild(O);
G.appendChild(H);
G.appendChild(M);
G.appendChild(E);
G.appendChild(v);
G.appendChild(y);
G.appendChild(P);
G.appendChild(S);
n(G,N.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
n(F,N.id+OG.Constants.GUIDE_SUFFIX.BBOX);
n(O,N.id+OG.Constants.GUIDE_SUFFIX.UL);
n(H,N.id+OG.Constants.GUIDE_SUFFIX.UR);
n(M,N.id+OG.Constants.GUIDE_SUFFIX.LL);
n(E,N.id+OG.Constants.GUIDE_SUFFIX.LR);
n(v,N.id+OG.Constants.GUIDE_SUFFIX.LC);
n(y,N.id+OG.Constants.GUIDE_SUFFIX.UC);
n(P,N.id+OG.Constants.GUIDE_SUFFIX.RC);
n(S,N.id+OG.Constants.GUIDE_SUFFIX.LWC);
R={bBox:F.node,group:G.node,ul:O.node,ur:H.node,ll:M.node,lr:E.node,lc:v.node,uc:y.node,rc:P.node,lwc:S.node};
F.insertBefore(N);
G.insertAfter(N);
$(N.node).attr("_selected","true");
return R
}}return null
};
this.removeGuide=function(x){var w=u(OG.Util.isElement(x)?x.id:x),v=u(w.id+OG.Constants.GUIDE_SUFFIX.GUIDE),y=u(w.id+OG.Constants.GUIDE_SUFFIX.BBOX);
w.node.removeAttribute("_selected");
t(v);
t(y)
};
this.removeAllGuide=function(){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(v,w){if(OG.Util.isElement(w)&&w.id){s.removeGuide(w)
}})
};
this.drawEdgeGuide=function(A){var E=u(OG.Util.isElement(A)?A.id:A),I=E?E.node.shape.geom:null,F,D,J,w,K,y,x,C,v,G=[],H=OG.Constants.GUIDE_RECT_SIZE,B=OG.Util.round(H/2),z;
if(E&&I){F=I.getVertices();
D=$(A).attr("_from")&&$(A).attr("_to")&&$(A).attr("_from")===$(A).attr("_to");
if(u(E.id+OG.Constants.GUIDE_SUFFIX.GUIDE)){t(u(E.id+OG.Constants.GUIDE_SUFFIX.BBOX));
K="";
for(z=0;
z<F.length;
z++){if(z===0){K="M"+F[z].x+" "+F[z].y
}else{K+="L"+F[z].x+" "+F[z].y
}}y=o.path(K);
y.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
n(y,E.id+OG.Constants.GUIDE_SUFFIX.BBOX);
y.insertBefore(E);
x=u(E.id+OG.Constants.GUIDE_SUFFIX.FROM);
x.attr({x:F[0].x-B,y:F[0].y-B});
C=u(E.id+OG.Constants.GUIDE_SUFFIX.TO);
C.attr({x:F[F.length-1].x-B,y:F[F.length-1].y-B});
if(!D){for(z=1;
z<F.length-2;
z++){if(F[z].x===F[z+1].x){v=u(E.id+OG.Constants.GUIDE_SUFFIX.CTL_H+z);
if(v){v.attr({x:F[z].x-B,y:OG.Util.round((F[z].y+F[z+1].y)/2)-B})
}}else{v=u(E.id+OG.Constants.GUIDE_SUFFIX.CTL_V+z);
if(v){v.attr({x:OG.Util.round((F[z].x+F[z+1].x)/2)-B,y:F[z].y-B})
}}}}return null
}J=u(E.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
if(J){t(J);
t(u(E.id+OG.Constants.GUIDE_SUFFIX.BBOX))
}J=o.group();
K="";
for(z=0;
z<F.length;
z++){if(z===0){K="M"+F[z].x+" "+F[z].y
}else{K+="L"+F[z].x+" "+F[z].y
}}y=o.path(K);
y.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
x=o.rect(F[0].x-B,F[0].y-B,H,H);
x.attr(OG.Constants.DEFAULT_STYLE.GUIDE_FROM);
J.appendChild(x);
n(x,E.id+OG.Constants.GUIDE_SUFFIX.FROM);
C=o.rect(F[F.length-1].x-B,F[F.length-1].y-B,H,H);
C.attr(OG.Constants.DEFAULT_STYLE.GUIDE_TO);
J.appendChild(C);
n(C,E.id+OG.Constants.GUIDE_SUFFIX.TO);
if(!D){for(z=1;
z<F.length-2;
z++){if(F[z].x===F[z+1].x){v=o.rect(F[z].x-B,OG.Util.round((F[z].y+F[z+1].y)/2)-B,H,H);
v.attr(OG.Constants.DEFAULT_STYLE.GUIDE_CTL_H);
n(v,E.id+OG.Constants.GUIDE_SUFFIX.CTL_H+z)
}else{v=o.rect(OG.Util.round((F[z].x+F[z+1].x)/2)-B,F[z].y-B,H,H);
v.attr(OG.Constants.DEFAULT_STYLE.GUIDE_CTL_V);
n(v,E.id+OG.Constants.GUIDE_SUFFIX.CTL_V+z)
}J.appendChild(v);
G.push(v.node)
}}n(y,E.id+OG.Constants.GUIDE_SUFFIX.BBOX);
n(J,E.id+OG.Constants.GUIDE_SUFFIX.GUIDE);
w={bBox:y.node,group:J.node,from:x.node,to:C.node,controls:G};
y.insertBefore(E);
J.insertAfter(E);
$(E.node).attr("_selected","true");
return w
}return null
};
this.drawRubberBand=function(A,F,v){var D=A?A[0]:0,B=A?A[1]:0,w=F?F[0]:0,E=F?F[1]:0,C=u(OG.Constants.RUBBER_BAND_ID),z={};
if(C){C.attr({x:D,y:B,width:Math.abs(w),height:Math.abs(E)});
return C
}OG.Util.apply(z,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Constants.DEFAULT_STYLE.RUBBER_BAND);
C=o.rect(D,B,w,E).attr(z);
n(C,OG.Constants.RUBBER_BAND_ID);
return C.node
};
this.removeRubberBand=function(v){this.setAttr(OG.Constants.RUBBER_BAND_ID,{x:0,y:0,width:0,height:0});
$(v).removeData("dragBox_first");
$(v).removeData("rubberBand")
};
this.drawTerminal=function(v,w){var z=u(OG.Util.isElement(v)?v.id:v),H=z?z.node.shape.createTerminal():null,B=z?z.node.shape.geom.getBoundary():null,G,F,D,E,C,I=OG.Constants.TERMINAL_SIZE,A=I*2;
if(z&&H&&H.length>0){G=u(z.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
D=u(z.id+OG.Constants.TERMINAL_SUFFIX.BOX);
if(G||D){return{bBox:D.node,terminal:G.node}
}G=o.group();
D=o.rect(B.getUpperLeft().x-A,B.getUpperLeft().y-A,B.getWidth()+A*2,B.getHeight()+A*2);
D.attr(OG.Constants.DEFAULT_STYLE.TERMINAL_BBOX);
n(D,z.id+OG.Constants.TERMINAL_SUFFIX.BOX);
$.each(H,function(x,y){if(!w||y.inout.indexOf(w)>=0){E=y.position.x;
C=y.position.y;
F=o.circle(E,C,I);
F.attr(OG.Constants.DEFAULT_STYLE.TERMINAL);
F.node.terminal=y;
G.appendChild(F);
n(F,z.id+OG.Constants.TERMINAL_SUFFIX.GROUP+"_"+y.direction+"_"+y.inout+"_"+x)
}});
n(G,z.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
D.insertBefore(z);
G.insertAfter(z);
return{bBox:D.node,terminal:G.node}
}return null
};
this.removeTerminal=function(w){var v=u(OG.Util.isElement(w)?w.id:w),y,x;
if(v){y=u(v.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
if(y){t(y)
}x=u(v.id+OG.Constants.TERMINAL_SUFFIX.BOX);
if(x){t(x)
}}};
this.removeAllTerminal=function(){var v=this;
$.each(r.keys(),function(w,x){v.removeTerminal(x)
})
};
this.drawCollapseGuide=function(w){var z=u(OG.Util.isElement(w)?w.id:w),B=z?z.node.shape.geom:null,A,D,v,y,C=OG.Constants.COLLAPSE_SIZE,x=C/2;
if(z&&B&&$(w).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){v=u(z.id+OG.Constants.COLLAPSE_BBOX_SUFFIX);
if(v){t(v)
}y=u(z.id+OG.Constants.COLLAPSE_SUFFIX);
if(y){t(y)
}A=B.getBoundary();
D=A.getUpperLeft();
v=o.rect(A.getUpperLeft().x-C,A.getUpperLeft().y-C,A.getWidth()+C*2,A.getHeight()+C*2);
v.attr(OG.Constants.DEFAULT_STYLE.COLLAPSE_BBOX);
n(v,z.id+OG.Constants.COLLAPSE_BBOX_SUFFIX);
if(z.node.shape.isCollapsed===true){y=o.path("M"+(D.x+x)+" "+(D.y+x)+"h"+C+"v"+C+"h"+(-1*C)+"v"+(-1*C)+"m1 "+x+"h"+(C-2)+"M"+(D.x+x)+" "+(D.y+x)+"m"+x+" 1v"+(C-2))
}else{y=o.path("M"+(D.x+x)+" "+(D.y+x)+"h"+C+"v"+C+"h"+(-1*C)+"v"+(-1*C)+"m1 "+x+"h"+(C-2))
}y.attr(OG.Constants.DEFAULT_STYLE.COLLAPSE);
n(y,z.id+OG.Constants.COLLAPSE_SUFFIX);
v.insertBefore(z);
y.insertAfter(z);
return{bBox:v.node,collapse:y.node}
}return null
};
this.removeCollapseGuide=function(x){var v=u(OG.Util.isElement(x)?x.id:x),y,w;
if(v){y=u(v.id+OG.Constants.COLLAPSE_BBOX_SUFFIX);
if(y){t(y)
}w=u(v.id+OG.Constants.COLLAPSE_SUFFIX);
if(w){t(w)
}}};
this.group=function(v){var x,w=[],B,C,z,A,D,y;
if(v&&v.length>1){for(y=0;
y<v.length;
y++){w.push(v[y].shape.geom)
}B=new OG.GeometryCollection(w);
C=B.getBoundary();
z=[C.getCentroid().x,C.getCentroid().y];
A=new OG.GroupShape();
D=[C.getWidth(),C.getHeight()];
x=this.drawShape(z,A,D);
for(y=0;
y<v.length;
y++){x.appendChild(v[y])
}}return x
};
this.ungroup=function(y){var z=[],x,w,v;
if(y&&y.length>0){for(w=0;
w<y.length;
w++){x=$(y[w]).children("[_type='"+OG.Constants.NODE_TYPE.SHAPE+"']");
for(v=0;
v<x.length;
v++){y[w].parentNode.appendChild(x[v]);
z.push(x[v])
}this.removeShape(y[w])
}}return z
};
this.addToGroup=function(w,x){var v;
for(v=0;
v<x.length;
v++){w.appendChild(x[v])
}};
this.collapse=function(x){var y=this,z,w,v;
v=function(I,D){var J,H,F,B=D.childNodes,A,G,E,C;
for(G=B.length-1;
G>=0;
G--){if($(B[G]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){v(I,B[G]);
C=false;
J=$(B[G]).attr("_fromedge");
if(J){J=J.split(",");
for(E=0;
E<J.length;
E++){H=y.getElementById(J[E]);
if(H){A=g($(H).attr("_from"));
if($(A).parents("#"+I.id).length!==0){y.hide(H)
}else{C=true
}}}}J=$(B[G]).attr("_toedge");
if(J){J=J.split(",");
for(E=0;
E<J.length;
E++){F=y.getElementById(J[E]);
if(F){A=g($(F).attr("_to"));
if($(A).parents("#"+I.id).length!==0){y.hide(F)
}else{C=true
}}}}if(C===true){y.redrawConnectedEdge(B[G])
}}}};
if(x.shape){z=x.childNodes;
for(w=z.length-1;
w>=0;
w--){if($(z[w]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.hide(z[w])
}}x.shape.isCollapsed=true;
$(x).attr("_collapsed",true);
v(x,x);
this.redrawShape(x)
}};
this.expand=function(x){var y=this,z,w,v;
v=function(I,D){var J,H,F,B=D.childNodes,A,G,E,C;
for(G=B.length-1;
G>=0;
G--){if($(B[G]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){v(I,B[G]);
C=false;
J=$(B[G]).attr("_fromedge");
if(J){J=J.split(",");
for(E=0;
E<J.length;
E++){H=y.getElementById(J[E]);
if(H){A=g($(H).attr("_from"));
if($(A).parents("#"+I.id).length!==0){y.show(H)
}else{C=true
}}}}J=$(B[G]).attr("_toedge");
if(J){J=J.split(",");
for(E=0;
E<J.length;
E++){F=y.getElementById(J[E]);
if(F){A=g($(F).attr("_to"));
if($(A).parents("#"+I.id).length!==0){y.show(F)
}else{C=true
}}}}if(C===true){y.redrawConnectedEdge(B[G])
}}}};
if(x.shape){z=x.childNodes;
for(w=z.length-1;
w>=0;
w--){if($(z[w]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.show(z[w])
}}x.shape.isCollapsed=false;
$(x).attr("_collapsed",false);
v(x,x);
this.redrawShape(x)
}};
this.clear=function(){o.clear();
r.clear();
i=n(o.group(),null,OG.Constants.NODE_TYPE.ROOT)
};
this.removeShape=function(x){var v=u(OG.Util.isElement(x)?x.id:x),y,w;
y=v.node.childNodes;
for(w=y.length-1;
w>=0;
w--){if($(y[w]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.removeShape(y[w])
}}$(o.canvas).trigger("removeShape",[v.node]);
this.disconnect(v.node);
this.removeTerminal(v.node);
this.removeGuide(v.node);
this.removeCollapseGuide(v.node);
this.remove(v.node)
};
this.remove=function(w){var x=OG.Util.isElement(w)?w.id:w,v=u(x);
t(v)
};
this.removeChild=function(w){var x=OG.Util.isElement(w)?w.id:w,v=u(x);
f(v)
};
this.getRootElement=function(){return o.canvas
};
this.getRootGroup=function(){return i.node
};
this.getElementByPoint=function(v){var w=o.getElementByPoint(v[0],v[1]);
return w?w.node.parentNode:null
};
this.getElementsByBBox=function(w){var v=[];
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(x,y){if(y.shape.geom&&w.isContainsAll(y.shape.geom.getVertices())){v.push(y)
}});
return v
};
this.setAttr=function(w,x){var v=u(OG.Util.isElement(w)?w.id:w);
if(v){v.attr(x)
}};
this.getAttr=function(x,w){var v=u(OG.Util.isElement(x)?x.id:x);
if(v){return v.attr(w)
}return null
};
this.toFront=function(w){var v=u(OG.Util.isElement(w)?w.id:w);
if(v){v.toFront()
}};
this.toBack=function(w){var v=u(OG.Util.isElement(w)?w.id:w);
if(v){v.toBack()
}};
this.setCanvasSize=function(v){o.setSize(v[0],v[1])
};
this.setViewBox=function(v,w,x){o.setViewBox(v[0],v[1],w[0],w[1],x)
};
this.show=function(w){var v=u(OG.Util.isElement(w)?w.id:w);
if(v){v.show()
}};
this.hide=function(w){var v=u(OG.Util.isElement(w)?w.id:w);
if(v){v.hide()
}};
this.appendChild=function(v,w){var x=u(OG.Util.isElement(v)?v.id:v),y=u(OG.Util.isElement(w)?w.id:w);
y.appendChild(x);
return x
};
this.insertAfter=function(v,w){var x=u(OG.Util.isElement(v)?v.id:v),y=u(OG.Util.isElement(w)?w.id:w);
x.insertAfter(y);
return x
};
this.insertBefore=function(v,w){var x=u(OG.Util.isElement(v)?v.id:v),y=u(OG.Util.isElement(w)?w.id:w);
x.insertBefore(y);
return x
};
this.move=function(w,B,z){var v=u(OG.Util.isElement(w)?w.id:w),x=v?v.node.getAttribute("_shape"):null,A=v?v.node.shape.geom:null,y=this;
this.removeCollapseGuide(w);
if(v&&x&&A){$(v.node).children("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(C,D){y.move(D,B,z)
});
A.move(B[0],B[1]);
$(o.canvas).trigger("moveShape",[v.node,B]);
return this.redrawShape(v.node,z)
}else{if(v){v.transform("...t"+B[0]+","+B[1]);
$(o.canvas).trigger("moveShape",[v.node,B]);
return v.node
}}return null
};
this.moveCentroid=function(y,w,A){var x=u(OG.Util.isElement(y)?y.id:y),B=x?x.node.shape.geom:null,z,v={};
if(x&&B){v=B.getCentroid();
return this.move(y,[w[0]-v.x,w[1]-v.y],A)
}else{if(x){z=x.getBBox();
v.x=z.x+OG.Util.round(z.width/2);
v.y=z.y+OG.Util.round(z.height/2);
return this.move(y,[w[0]-v.x,w[1]-v.y])
}}this.removeCollapseGuide(y);
return null
};
this.rotate=function(w,A,y){var v=u(OG.Util.isElement(w)?w.id:w),x=v?v.node.getAttribute("_shape"):null,z=v?v.node.shape.geom:null;
if(v&&x&&z){z.rotate(A);
return this.redrawShape(v.node,y)
}else{if(v){v.rotate(A);
return v.node
}}return null
};
this.resize=function(y,w,E){var A=u(OG.Util.isElement(y)?y.id:y),C=A?A.node.getAttribute("_shape"):null,D=A?A.node.shape.geom:null,H,z,x,v,G,F,B;
this.removeCollapseGuide(y);
if(A&&C&&D){D.resize(w[0],w[1],w[2],w[3]);
$(o.canvas).trigger("resizeShape",[A.node,w]);
return this.redrawShape(A.node,E)
}else{if(A){H=A.getBBox();
z=w[2]+w[3];
x=w[0]+w[1];
v=H.width+z;
G=H.height+x;
F=H.width===0?1:v/H.width;
B=H.height===0?1:G/H.height;
A.transform("...t"+(-1*w[2])+","+(-1*w[0]));
A.transform("...s"+F+","+B+","+H.x+","+H.y);
$(o.canvas).trigger("resizeShape",[A.node,w]);
return A.node
}}return null
};
this.resizeBox=function(y,x){var w=u(OG.Util.isElement(y)?y.id:y),B=w?w.node.shape.geom:null,C,A,z,v;
this.removeCollapseGuide(y);
if(w&&B){C=B.getBoundary();
z=OG.Util.round((x[0]-C.getWidth())/2);
v=OG.Util.round((x[1]-C.getHeight())/2);
return this.resize(y,[v,v,z,z])
}else{if(w){A=w.getBBox();
z=OG.Util.round((x[0]-A.width)/2);
v=OG.Util.round((x[1]-A.height)/2);
return this.resize(y,[v,v,z,z])
}}return null
};
this.intersectionEdge=function(L,A,F,G,y){var C,B,E,I,z,H=Number.MAX_VALUE,v,x,w,D,K,J;
if(A){x=$(A).parents("[_collapsed=true]");
if(x.length!==0){w=x[x.length-1].shape.geom.getBoundary();
D=w.getUpperLeft();
K=new OG.geometry.Rectangle(D,OG.Constants.COLLAPSE_SIZE*3,OG.Constants.COLLAPSE_SIZE*2)
}}switch(L){case OG.Constants.EDGE_TYPE.PLAIN:C=y?k(A,F,G):a(A,F,G);
B=[C.terminal.position.x,C.terminal.position.y];
E=C.terminal.direction.toLowerCase();
if(K){switch(C.terminal.direction){case OG.Constants.TERMINAL_TYPE.E:J=K.getBoundary().getRightCenter();
break;
case OG.Constants.TERMINAL_TYPE.W:J=K.getBoundary().getLeftCenter();
break;
case OG.Constants.TERMINAL_TYPE.S:J=K.getBoundary().getLowerCenter();
break;
case OG.Constants.TERMINAL_TYPE.N:J=K.getBoundary().getUpperCenter();
break
}if(J){B=[J.x,J.y]
}}break;
case OG.Constants.EDGE_TYPE.STRAIGHT:if(K){J=K.getBoundary().getCentroid();
if(y===true){F=[J.x,J.y]
}else{G=[J.x,J.y]
}I=K.intersectToLine([F,G])
}else{I=A.shape.geom.intersectToLine([F,G])
}B=y?F:G;
E="c";
for(z=0;
z<I.length;
z++){v=I[z].distance(y?G:F);
if(v<H){H=v;
B=[I[z].x,I[z].y];
E="c"
}}break;
default:break
}return{position:B,direction:E}
};
this.clone=function(w){var v=u(OG.Util.isElement(w)?w.id:w),x;
x=v.clone();
n(x);
return x.node
};
this.getElementById=function(w){var v=u(w);
return v?v.node:null
};
this.getBBox=function(w){var v=u(OG.Util.isElement(w)?w.id:w);
return v.getBBox()
};
this.getRootBBox=function(){var z=o.canvas.parentNode,A=OG.Util.isFirefox()?o.canvas.width.baseVal.value:o.canvas.scrollWidth,w=OG.Util.isFirefox()?o.canvas.height.baseVal.value:o.canvas.scrollHeight,v=z.offsetLeft,B=z.offsetTop;
return{width:A,height:w,x:v,y:B,x2:v+A,y2:B+w}
};
this.getContainer=function(){return o.canvas.parentNode
};
this.isSVG=function(){return Raphael.svg
};
this.isVML=function(){return Raphael.vml
}
};
OG.renderer.RaphaelRenderer.prototype=new OG.renderer.IRenderer();
OG.renderer.RaphaelRenderer.prototype.constructor=OG.renderer.RaphaelRenderer;
OG.RaphaelRenderer=OG.renderer.RaphaelRenderer;OG.handler.EventHandler=function(k){var d=this,b=k,f,l,j,n,i,h=function(p){return parseInt(p,10)
},a=function(p){return OG.Util.round(p/OG.Constants.MOVE_SNAP_SIZE)*5
},m=function(p){var q=OG.Util.isElement(p)?p.id:p;
return b.getElementById(q.substring(0,q.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)))
},c=function(p,x){var v,t,w,q,u=false,s=false,r;
v=$(p).attr("_from");
t=$(p).attr("_to");
if(v){w=m(v)
}if(t){q=m(t)
}for(r=0;
r<x.length;
r++){if(w&&x[r].id===w.id){u=true
}if(q&&x[r].id===q.id){s=true
}}return{none:!u&&!s,all:u&&s,any:u||s,either:(u&&!s)||(!u&&s),attrEither:(v&&!t)||(!v&&t)}
},o=function(q){var p=b.getContainer();
return{x:q.pageX-$(p).offset().left+p.scrollLeft,y:q.pageY-$(p).offset().top+p.scrollTop}
},e=function(){var r=[],q,p=[];
$("[id$="+OG.Constants.GUIDE_SUFFIX.BBOX+"]").each(function(s,t){if(t.id){q=b.clone(t);
b.setAttr(q,OG.Constants.DEFAULT_STYLE.GUIDE_SHADOW);
r.push({id:t.id.replace(OG.Constants.GUIDE_SUFFIX.BBOX,""),box:q})
}});
$.each(r,function(t,u){var v=b.getElementById(u.id),s;
if($(v).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){s=c(v,r);
if(s.all||s.none||(s.either&&s.attrEither)){p.push(u)
}else{b.remove(u.box);
b.removeGuide(v)
}}});
$.each(r,function(s,t){var u=b.getElementById(t.id);
if($(u).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){p.push(t)
}});
return p
},g=function(t,q,p){var s=[],r=[];
$.each(t,function(u,v){var w=b.getElementById(v.id);
if($(w).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){s.push(v.id)
}});
$.each(t,function(u,v){var w=b.getElementById(v.id);
b.remove(v.box);
b.move(w,[q,p],s);
b.setAttr(w,{cursor:"move"});
b.drawGuide(w);
if($(w).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(c(w,t).none){b.disconnect(w)
}}r.push(w)
});
return r
};
f=function(r,p,s){var q=b.getRootGroup();
if(!r||!p){return
}if(s){if($(r).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){$(p.from).draggable({start:function(y){var w=r.shape.geom.getVertices(),t={},u=$(r).attr("_to"),z,v=[w[w.length-1].x,w[w.length-1].y],x=b.drawEdge(new OG.PolyLine(w),OG.Util.apply(t,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,r.shape.geom.style.map));
if(u){z=m(u);
b.drawTerminal(z);
v=b.getElementById(u)
}$(q).data("to_terminal",v);
$(q).data("edge",x);
$(q).data("dragged_guide","from");
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(B,A){if(A.id&&$(A).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){b.removeGuide(A)
}})
},drag:function(t){var x=o(t),v=$(q).data("edge"),C=$(q).data("edge_terminal"),H=$(q).data("to_terminal"),D=C?[C.terminal.position.x,C.terminal.position.y]:[x.x,x.y],z=OG.Util.isElement(H)?[H.terminal.position.x,H.terminal.position.y]:H,E=C?C.terminal.direction.toLowerCase():"c",A=OG.Util.isElement(H)?H.terminal.direction.toLowerCase():"c",F=C?m(C):null,y=OG.Util.isElement(H)?m(H):null,u,G,w,B;
$(this).css({position:"",left:"",top:""});
u=D;
G=z;
if(F&&E==="c"){w=b.intersectionEdge(v.geom.style.get("edge-type"),F,[u[0],u[1]],[G[0],G[1]],true);
D=w.position;
E=w.direction
}if(y&&A==="c"){w=b.intersectionEdge(v.geom.style.get("edge-type"),y,[u[0],u[1]],[G[0],G[1]],false);
z=w.position;
A=w.direction
}B=F&&y&&F.id===y.id;
if(B){D=z=F.shape.geom.getBoundary().getRightCenter()
}if(!B||OG.Constants.SELF_CONNECTABLE){b.drawEdge(new OG.Line(D,z),OG.Util.apply(v.geom.style.map,{"edge-direction":E+" "+A}),v.id,B)
}},stop:function(x){var A=o(x),z=$(q).data("edge_terminal")||[A.x,A.y],t=$(q).data("to_terminal"),v=OG.Util.isElement(z)?m(z):null,y=OG.Util.isElement(t)?m(t):null,w=$(q).data("edge"),u;
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(q).removeData("to_terminal");
$(q).removeData("edge");
$(q).removeData("edge_terminal");
$(q).removeData("dragged_guide");
b.remove(w);
b.removeGuide(r);
if(v){b.remove(v.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}u=v&&y&&v.id===y.id;
if(!u||OG.Constants.SELF_CONNECTABLE){r=b.connect(z,t,r,r.shape.geom.style);
if(r){p=b.drawGuide(r);
if(r&&p){f(r,p,true);
b.toFront(p.group);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}}}});
$(p.to).draggable({start:function(y){var v=r.shape.geom.getVertices(),u={},t=$(r).attr("_from"),w,z=[v[0].x,v[0].y],x=b.drawEdge(new OG.PolyLine(v),OG.Util.apply(u,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,r.shape.geom.style.map));
if(t){w=m(t);
b.drawTerminal(w);
z=b.getElementById(t)
}$(q).data("from_terminal",z);
$(q).data("edge",x);
$(q).data("dragged_guide","to");
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(B,A){if(A.id&&$(A).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){b.removeGuide(A)
}})
},drag:function(t){var x=o(t),v=$(q).data("edge"),C=$(q).data("from_terminal"),H=$(q).data("edge_terminal"),D=OG.Util.isElement(C)?[C.terminal.position.x,C.terminal.position.y]:C,z=H?[H.terminal.position.x,H.terminal.position.y]:[x.x,x.y],E=OG.Util.isElement(C)?C.terminal.direction.toLowerCase():"c",A=H?H.terminal.direction.toLowerCase():"c",F=OG.Util.isElement(C)?m(C):null,y=H?m(H):null,u,G,w,B;
$(this).css({position:"",left:"",top:""});
u=D;
G=z;
if(F&&E==="c"){w=b.intersectionEdge(v.geom.style.get("edge-type"),F,[u[0],u[1]],[G[0],G[1]],true);
D=w.position;
E=w.direction
}if(y&&A==="c"){w=b.intersectionEdge(v.geom.style.get("edge-type"),y,[u[0],u[1]],[G[0],G[1]],false);
z=w.position;
A=w.direction
}B=(F!==null)&&(y!==null)&&F.id===y.id;
if(B){D=z=y.shape.geom.getBoundary().getRightCenter()
}if(!B||OG.Constants.SELF_CONNECTABLE){b.drawEdge(new OG.Line(D,z),OG.Util.apply(v.geom.style.map,{"edge-direction":E+" "+A}),v.id,B)
}},stop:function(x){var A=o(x),z=$(q).data("from_terminal"),t=$(q).data("edge_terminal")||[A.x,A.y],v=OG.Util.isElement(z)?m(z):null,y=OG.Util.isElement(t)?m(t):null,w=$(q).data("edge"),u;
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(q).removeData("from_terminal");
$(q).removeData("edge");
$(q).removeData("edge_terminal");
$(q).removeData("dragged_guide");
b.remove(w);
b.removeGuide(r);
if(y){b.remove(y.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}u=v&&y&&v.id===y.id;
if(!u||OG.Constants.SELF_CONNECTABLE){r=b.connect(z,t,r,r.shape.geom.style);
if(r){p=b.drawGuide(r);
if(p){f(r,p,true);
b.toFront(p.group);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}}}});
$.each(p.controls,function(t,u){$(u).draggable({start:function(v){var w=o(v);
$(this).data("start",{x:w.x,y:w.y});
$(this).data("offset",{x:w.x-h(b.getAttr(u,"x")),y:w.y-h(b.getAttr(u,"y"))});
b.remove(p.bBox);
b.removeRubberBand(b.getRootElement())
},drag:function(w){var y=o(w),x=$(this).data("start"),z=$(this).data("offset"),v=y.x-z.x,D=y.y-z.y,B=r.shape.geom.getVertices(),C=u.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,A=C?parseInt(u.id.replace(r.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(u.id.replace(r.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"",left:"",top:""});
if(C){B[A].x=v;
B[A+1].x=v
}else{B[A].y=D;
B[A+1].y=D
}r=b.drawEdge(new OG.PolyLine(B),r.shape.geom.style,r.id);
b.drawGuide(r);
b.removeAllTerminal();
b.drawLabel(r);
b.drawEdgeLabel(r,null,"FROM");
b.drawEdgeLabel(r,null,"TO")
},stop:function(w){var y=o(w),x=$(this).data("start"),z=$(this).data("offset"),v=y.x-z.x,D=y.y-z.y,B=r.shape.geom.getVertices(),C=u.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,A=C?parseInt(u.id.replace(r.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(u.id.replace(r.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(C){B[A].x=v;
B[A+1].x=v
}else{B[A].y=D;
B[A+1].y=D
}r=b.drawEdge(new OG.PolyLine(B),r.shape.geom.style,r.id);
b.drawGuide(r);
b.drawLabel(r);
b.drawEdgeLabel(r,null,"FROM");
b.drawEdgeLabel(r,null,"TO");
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}})
})
}else{$(p.rc).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.rc,"x")),y:u.y-h(b.getAttr(p.rc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var v=o(u),y=$(this).data("start"),x=$(this).data("offset"),w=v.x-x.x,t=w-h(b.getAttr(p.lc,"x"));
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.rc,{x:w});
b.setAttr(p.ur,{x:w});
b.setAttr(p.lr,{x:w});
b.setAttr(p.uc,{x:OG.Util.round((h(b.getAttr(p.lc,"x"))+w)/2)});
b.setAttr(p.lwc,{x:OG.Util.round((h(b.getAttr(p.lc,"x"))+w)/2)});
b.setAttr(p.bBox,{width:t})
}b.removeAllTerminal()
},stop:function(u){var v=o(u),w=$(this).data("start"),t=v.x-w.x;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getWidth()
}b.resize(r,[0,0,0,t]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(p.lwc).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.lwc,"x")),y:u.y-h(b.getAttr(p.lwc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var v=o(u),y=$(this).data("start"),x=$(this).data("offset"),w=v.y-x.y,t=w-h(b.getAttr(p.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.lwc,{y:w});
b.setAttr(p.ll,{y:w});
b.setAttr(p.lr,{y:w});
b.setAttr(p.lc,{y:OG.Util.round((h(b.getAttr(p.uc,"y"))+w)/2)});
b.setAttr(p.rc,{y:OG.Util.round((h(b.getAttr(p.uc,"y"))+w)/2)});
b.setAttr(p.bBox,{height:t})
}b.removeAllTerminal()
},stop:function(u){var v=o(u),w=$(this).data("start"),t=v.y-w.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getHeight()
}b.resize(r,[0,t,0,0]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(p.lr).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.lr,"x")),y:u.y-h(b.getAttr(p.lr,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(v){var w=o(v),A=$(this).data("start"),z=$(this).data("offset"),y=w.x-z.x,u=y-h(b.getAttr(p.lc,"x")),x=w.y-z.y,t=x-h(b.getAttr(p.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(u>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.rc,{x:y});
b.setAttr(p.ur,{x:y});
b.setAttr(p.lr,{x:y});
b.setAttr(p.uc,{x:OG.Util.round((h(b.getAttr(p.lc,"x"))+y)/2)});
b.setAttr(p.lwc,{x:OG.Util.round((h(b.getAttr(p.lc,"x"))+y)/2)});
b.setAttr(p.bBox,{width:u})
}if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.lwc,{y:x});
b.setAttr(p.ll,{y:x});
b.setAttr(p.lr,{y:x});
b.setAttr(p.lc,{y:OG.Util.round((h(b.getAttr(p.uc,"y"))+x)/2)});
b.setAttr(p.rc,{y:OG.Util.round((h(b.getAttr(p.uc,"y"))+x)/2)});
b.setAttr(p.bBox,{height:t})
}b.removeAllTerminal()
},stop:function(v){var w=o(v),x=$(this).data("start"),u=w.x-x.x,t=w.y-x.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getWidth()
}if(r.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getHeight()
}b.resize(r,[0,t,0,u]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}b.removeAllTerminal()
}});
$(p.lc).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.lc,"x")),y:u.y-h(b.getAttr(p.lc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var v=o(u),y=$(this).data("start"),x=$(this).data("offset"),w=v.x-x.x,t=h(b.getAttr(p.rc,"x"))-w;
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.lc,{x:w});
b.setAttr(p.ul,{x:w});
b.setAttr(p.ll,{x:w});
b.setAttr(p.uc,{x:OG.Util.round((h(b.getAttr(p.rc,"x"))+w)/2)});
b.setAttr(p.lwc,{x:OG.Util.round((h(b.getAttr(p.rc,"x"))+w)/2)});
b.setAttr(p.bBox,{x:OG.Util.round(w+h(b.getAttr(p.lc,"width"))/2),width:t})
}b.removeAllTerminal()
},stop:function(u){var v=o(u),w=$(this).data("start"),t=w.x-v.x;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getWidth()
}b.resize(r,[0,0,t,0]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(p.ll).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.ll,"x")),y:u.y-h(b.getAttr(p.ll,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(v){var w=o(v),A=$(this).data("start"),z=$(this).data("offset"),y=w.x-z.x,x=w.y-z.y,u=h(b.getAttr(p.rc,"x"))-y,t=x-h(b.getAttr(p.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(u>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.lc,{x:y});
b.setAttr(p.ul,{x:y});
b.setAttr(p.ll,{x:y});
b.setAttr(p.uc,{x:OG.Util.round((h(b.getAttr(p.rc,"x"))+y)/2)});
b.setAttr(p.lwc,{x:OG.Util.round((h(b.getAttr(p.rc,"x"))+y)/2)});
b.setAttr(p.bBox,{x:OG.Util.round(y+h(b.getAttr(p.lc,"width"))/2),width:u})
}if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.lwc,{y:x});
b.setAttr(p.ll,{y:x});
b.setAttr(p.lr,{y:x});
b.setAttr(p.lc,{y:OG.Util.round((h(b.getAttr(p.uc,"y"))+x)/2)});
b.setAttr(p.rc,{y:OG.Util.round((h(b.getAttr(p.uc,"y"))+x)/2)});
b.setAttr(p.bBox,{height:t})
}b.removeAllTerminal()
},stop:function(v){var w=o(v),x=$(this).data("start"),u=x.x-w.x,t=w.y-x.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getWidth()
}if(r.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getHeight()
}b.resize(r,[0,t,u,0]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(p.uc).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.uc,"x")),y:u.y-h(b.getAttr(p.uc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var v=o(u),y=$(this).data("start"),x=$(this).data("offset"),w=v.y-x.y,t=h(b.getAttr(p.lwc,"y"))-w;
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.uc,{y:w});
b.setAttr(p.ul,{y:w});
b.setAttr(p.ur,{y:w});
b.setAttr(p.lc,{y:OG.Util.round((h(b.getAttr(p.lwc,"y"))+w)/2)});
b.setAttr(p.rc,{y:OG.Util.round((h(b.getAttr(p.lwc,"y"))+w)/2)});
b.setAttr(p.bBox,{y:OG.Util.round(w+h(b.getAttr(p.uc,"width"))/2),height:t})
}b.removeAllTerminal()
},stop:function(u){var v=o(u),w=$(this).data("start"),t=w.y-v.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getHeight()
}b.resize(r,[t,0,0,0]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(p.ul).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.ul,"x")),y:u.y-h(b.getAttr(p.ul,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(v){var w=o(v),A=$(this).data("start"),z=$(this).data("offset"),y=w.x-z.x,x=w.y-z.y,u=h(b.getAttr(p.rc,"x"))-y,t=h(b.getAttr(p.lwc,"y"))-x;
$(this).css({position:"",left:"",top:""});
if(u>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.lc,{x:y});
b.setAttr(p.ul,{x:y});
b.setAttr(p.ll,{x:y});
b.setAttr(p.uc,{x:OG.Util.round((h(b.getAttr(p.rc,"x"))+y)/2)});
b.setAttr(p.lwc,{x:OG.Util.round((h(b.getAttr(p.rc,"x"))+y)/2)});
b.setAttr(p.bBox,{x:OG.Util.round(y+h(b.getAttr(p.lc,"width"))/2),width:u})
}if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.uc,{y:x});
b.setAttr(p.ul,{y:x});
b.setAttr(p.ur,{y:x});
b.setAttr(p.lc,{y:OG.Util.round((h(b.getAttr(p.lwc,"y"))+x)/2)});
b.setAttr(p.rc,{y:OG.Util.round((h(b.getAttr(p.lwc,"y"))+x)/2)});
b.setAttr(p.bBox,{y:OG.Util.round(x+h(b.getAttr(p.uc,"height"))/2),height:t})
}b.removeAllTerminal()
},stop:function(v){var w=o(v),x=$(this).data("start"),u=x.x-w.x,t=x.y-w.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getWidth()
}if(r.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getHeight()
}b.resize(r,[t,0,u,0]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(p.ur).draggable({start:function(t){var u=o(t);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(p.ur,"x")),y:u.y-h(b.getAttr(p.ur,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(v){var w=o(v),A=$(this).data("start"),z=$(this).data("offset"),y=w.x-z.x,x=w.y-z.y,u=y-h(b.getAttr(p.lc,"x")),t=h(b.getAttr(p.lwc,"y"))-x;
$(this).css({position:"",left:"",top:""});
if(u>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.rc,{x:y});
b.setAttr(p.ur,{x:y});
b.setAttr(p.lr,{x:y});
b.setAttr(p.uc,{x:OG.Util.round((h(b.getAttr(p.lc,"x"))+y)/2)});
b.setAttr(p.lwc,{x:OG.Util.round((h(b.getAttr(p.lc,"x"))+y)/2)});
b.setAttr(p.bBox,{width:u})
}if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(p.uc,{y:x});
b.setAttr(p.ul,{y:x});
b.setAttr(p.ur,{y:x});
b.setAttr(p.lc,{y:OG.Util.round((h(b.getAttr(p.lwc,"y"))+x)/2)});
b.setAttr(p.rc,{y:OG.Util.round((h(b.getAttr(p.lwc,"y"))+x)/2)});
b.setAttr(p.bBox,{y:OG.Util.round(x+h(b.getAttr(p.uc,"width"))/2),height:t})
}b.removeAllTerminal()
},stop:function(v){var w=o(v),x=$(this).data("start"),u=w.x-x.x,t=x.y-w.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(r&&r.shape.geom){if(r.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getWidth()
}if(r.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-r.shape.geom.getBoundary().getHeight()
}b.resize(r,[t,0,0,u]);
b.drawGuide(r);
b.setAttr(r,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}})
}}else{if($(r).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){b.setAttr(p.from,{cursor:"default"});
b.setAttr(p.to,{cursor:"default"});
$.each(p.controls,function(t,u){b.setAttr(u,{cursor:"default"})
})
}else{b.setAttr(p.ul,{cursor:"default"});
b.setAttr(p.ur,{cursor:"default"});
b.setAttr(p.ll,{cursor:"default"});
b.setAttr(p.lr,{cursor:"default"});
b.setAttr(p.lc,{cursor:"default"});
b.setAttr(p.uc,{cursor:"default"});
b.setAttr(p.rc,{cursor:"default"});
b.setAttr(p.lwc,{cursor:"default"})
}}};
l=function(q){var p=q.parentNode;
if(p){if(l(p)){return true
}if($(p).attr("_type")===OG.Constants.NODE_TYPE.SHAPE&&$(p).attr("_selected")==="true"){return true
}}return false
};
j=function(q){var p=q.childNodes;
$.each(p,function(r,s){if($(s).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){if(s.childNodes.length>0){j(s)
}if($(s).attr("_selected")==="true"){b.removeGuide(s);
$(s).draggable("destroy")
}}})
};
n=function(q,r){var p=q.childNodes;
$.each(p,function(s,v){if($(v).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){var x=v.shape.geom.getBoundary(),u,w,t;
u=v.shape.clone();
if($(v).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){u.geom=new OG.PolyLine(v.shape.geom.getVertices());
u.geom.style=v.shape.geom.style;
u.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
w=b.drawShape(null,u,null,v.shapeStyle)
}else{w=b.drawShape([x.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,x.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],u,[x.getWidth(),x.getHeight()],v.shapeStyle)
}r.appendChild(w);
d.setClickSelectable(w,OG.Constants.SELECTABLE);
d.setMovable(w,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(w)
}if(OG.Constants.GROUP_COLLAPSIBLE){d.enableCollapse(w)
}if($(w).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){d.enableConnect(w)
}if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(w)
}}if(v.childNodes.length>0){n(v,w)
}}})
};
i=function(r,q){var p=b.getRootBBox();
if(OG.Constants.AUTO_EXTENSIONAL&&p.width<r){b.setCanvasSize([p.width+OG.Constants.AUTO_EXTENSION_SIZE,p.height])
}if(OG.Constants.AUTO_EXTENSIONAL&&p.height<q){b.setCanvasSize([p.width,p.height+OG.Constants.AUTO_EXTENSION_SIZE])
}};
this.setResizable=f;
this.enableEditLabel=function(p){if(($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.GEOM&&OG.Constants.LABEL_EDITABLE_GEOM)||($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT&&OG.Constants.LABEL_EDITABLE_TEXT)||($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.HTML&&OG.Constants.LABEL_EDITABLE_HTML)||($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE&&OG.Constants.LABEL_EDITABLE_EDGE)||($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP&&OG.Constants.LABEL_EDITABLE_GROUP)){$(p).bind({dblclick:function(t){var u=b.getContainer(),A=p.shape.geom.getBoundary(),s=A.getUpperLeft(),E,y=s.x-1,C=s.y-1,w=A.getWidth(),D=A.getHeight(),x=p.id+OG.Constants.LABEL_EDITOR_SUFFIX,r,v="center",F,z,B=function(K){var I,G,L=0,J,H;
I=K.shape.geom.getVertices();
G=K.shape.geom.getLength();
for(J=0;
J<I.length-1;
J++){L+=I[J].distance(I[J+1]);
if(L>G/2){H=K.shape.geom.intersectCircleToLine(I[J+1],L-G/2,I[J+1],I[J]);
break
}}return H[0]
},q;
$(u).append("<textarea id='"+p.id+OG.Constants.LABEL_EDITOR_SUFFIX+"'></textarea>");
r=$("#"+x);
switch(p.shape.geom.style.get("text-anchor")){case"start":v="left";
break;
case"middle":v="center";
break;
case"end":v="right";
break;
default:v="center";
break
}if($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.HTML){$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:y,top:C,width:w,height:D,"text-align":"left",overflow:"hidden",resize:"none"}));
$(r).focus();
$(r).val(p.shape.html);
$(r).bind({focusout:function(){p.shape.html=this.value;
if(p.shape.html){b.redrawShape(p);
this.parentNode.removeChild(this)
}else{b.removeShape(p);
this.parentNode.removeChild(this)
}}})
}else{if($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT){$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:y,top:C,width:w,height:D,"text-align":v,overflow:"hidden",resize:"none"}));
$(r).focus();
$(r).val(p.shape.text);
$(r).bind({focusout:function(){p.shape.text=this.value;
if(p.shape.text){b.redrawShape(p);
this.parentNode.removeChild(this)
}else{b.removeShape(p);
this.parentNode.removeChild(this)
}}})
}else{if($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(p.shape.label&&b.isSVG()){$(p).children("[id$=_LABEL]").each(function(G,H){$(H).find("text").each(function(J,I){E=b.getBBox(I);
y=E.x-10;
C=E.y;
w=E.width+20;
D=E.height
})
})
}else{q=B(p);
y=q.x-OG.Constants.LABEL_EDITOR_WIDTH/2;
C=q.y-OG.Constants.LABEL_EDITOR_HEIGHT/2;
w=OG.Constants.LABEL_EDITOR_WIDTH;
D=OG.Constants.LABEL_EDITOR_HEIGHT
}$(t.srcElement).parents("[id$=_FROMLABEL]").each(function(G,H){$(H).find("text").each(function(J,I){E=b.getBBox(I);
y=E.x-10;
C=E.y;
w=E.width+20;
D=E.height;
F=p.shape.fromLabel
})
});
$(t.srcElement).parents("[id$=_TOLABEL]").each(function(G,H){$(H).find("text").each(function(J,I){E=b.getBBox(I);
y=E.x-10;
C=E.y;
w=E.width+20;
D=E.height;
z=p.shape.toLabel
})
});
$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:y,top:C,width:w,height:D,overflow:"hidden",resize:"none"}));
$(r).focus();
if(F||z){$(r).val(F?p.shape.fromLabel:p.shape.toLabel)
}else{$(r).val(p.shape.label)
}$(r).bind({focusout:function(){if(F){b.drawEdgeLabel(p,this.value,"FROM")
}else{if(z){b.drawEdgeLabel(p,this.value,"TO")
}else{b.drawLabel(p,this.value)
}}this.parentNode.removeChild(this)
}})
}else{$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:y,top:C,width:w,height:D,"text-align":v,overflow:"hidden",resize:"none"}));
$(r).focus();
$(r).val(p.shape.label);
$(r).bind({focusout:function(){b.drawLabel(p,this.value);
this.parentNode.removeChild(this)
}})
}}}}})
}};
this.enableConnect=function(q){var r,p=b.getRootGroup();
if(q&&$(q).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){$(q).bind({mouseover:function(){r=b.drawTerminal(q,$(p).data("dragged_guide")==="to"?OG.Constants.TERMINAL_TYPE.IN:OG.Constants.TERMINAL_TYPE.OUT);
if(r&&r.terminal&&r.terminal.childNodes.length>0){if($(p).data("edge")){$.each(r.terminal.childNodes,function(s,v){var w=$(p).data("from_terminal"),u=w&&OG.Util.isElement(w)?m(w):null,t=q&&u&&q.id===u.id;
if(v.terminal&&v.terminal.direction.toLowerCase()==="c"&&(($(p).data("dragged_guide")==="to"&&v.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.IN)>=0)||($(p).data("dragged_guide")==="from"&&v.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.OUT)>=0))&&(!t||OG.Constants.SELF_CONNECTABLE)){b.drawDropOverGuide(q);
$(p).data("edge_terminal",v);
return false
}})
}$(r.bBox).bind({mouseout:function(){if(!$(p).data("edge")){b.removeTerminal(q)
}}});
$.each(r.terminal.childNodes,function(s,t){if(t.terminal){$(t).bind({mouseover:function(w){var x=$(p).data("from_terminal"),v=x&&OG.Util.isElement(x)?m(x):null,u=q&&v&&q.id===v.id;
if((($(p).data("dragged_guide")==="to"&&t.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.IN)>=0)||($(p).data("dragged_guide")==="from"&&t.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.OUT)>=0)||(!$(p).data("dragged_guide")&&t.terminal.inout.indexOf(OG.Constants.TERMINAL_TYPE.OUT)>=0))&&(!u||OG.Constants.SELF_CONNECTABLE)){b.setAttr(t,OG.Constants.DEFAULT_STYLE.TERMINAL_OVER);
$(p).data("edge_terminal",t)
}},mouseout:function(){b.setAttr(t,OG.Constants.DEFAULT_STYLE.TERMINAL);
$(p).removeData("edge_terminal")
}});
$(t).draggable({start:function(w){var u=t.terminal.position.x,z=t.terminal.position.y,v=b.drawShape(null,new OG.EdgeShape([u,z],[u,z]),null,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW);
$(p).data("edge",v);
$(p).data("from_terminal",t);
$(p).data("dragged_guide","to");
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(y,x){if(x.id){b.removeGuide(x)
}})
},drag:function(u){var y=o(u),w=$(p).data("edge"),D=$(p).data("from_terminal"),H=$(p).data("edge_terminal"),E=[D.terminal.position.x,D.terminal.position.y],A=H?[H.terminal.position.x,H.terminal.position.y]:[y.x,y.y],F=D.terminal.direction.toLowerCase(),B=H?H.terminal.direction.toLowerCase():"c",z=H?m(H):null,v,G,x,C;
$(this).css({position:"",left:"",top:""});
v=E;
G=A;
if(!q.shape.geom.getBoundary().isContains(A)&&F==="c"){x=b.intersectionEdge(w.shape.geom.style.get("edge-type"),q,[v[0],v[1]],[G[0],G[1]],true);
E=x.position;
F=x.direction
}if(z&&B==="c"){x=b.intersectionEdge(w.shape.geom.style.get("edge-type"),z,[v[0],v[1]],[G[0],G[1]],false);
A=x.position;
B=x.direction
}C=q&&z&&q.id===z.id;
if(C){E=A=q.shape.geom.getBoundary().getRightCenter()
}if(!C||OG.Constants.SELF_CONNECTABLE){b.drawEdge(new OG.Line(E,A),OG.Util.apply(w.shape.geom.style.map,{"edge-direction":F+" "+B}),w.id,C)
}},stop:function(u){var F=o(u),x=$(p).data("edge"),D=$(p).data("from_terminal"),G=$(p).data("edge_terminal")||[F.x,F.y],y=OG.Util.isElement(G)?m(G):null,w,z,E,B,v,A,C;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(!$(p).data("edge_terminal")&&OG.Constants.CONNECT_CLONEABLE){w=q.shape.geom.getBoundary();
z=b.drawShape([F.x,F.y],q.shape.clone(),[w.getWidth(),w.getHeight()],q.shapeStyle);
d.setClickSelectable(z,OG.Constants.SELECTABLE);
d.setMovable(z,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(z)
}if(OG.Constants.GROUP_COLLAPSIBLE){d.enableCollapse(z)
}if($(z).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){d.enableConnect(z)
}if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(z)
}}E=b.drawTerminal(z,OG.Constants.TERMINAL_TYPE.IN);
B=E.terminal.childNodes;
G=B[0];
for(A=0;
A<B.length;
A++){if(B[A].terminal&&B[A].terminal.direction.toLowerCase()==="c"){G=B[A];
break
}}}C=q&&y&&q.id===y.id;
if(G&&(OG.Util.isElement(G)||!OG.Constants.CONNECT_REQUIRED)&&(!C||OG.Constants.SELF_CONNECTABLE)){x=b.connect(D,G,x);
if(x){v=b.drawGuide(x);
if(x&&v){d.setClickSelectable(x,OG.Constants.SELECTABLE);
d.setMovable(x,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
f(x,v,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if($(x).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(x)
}}b.toFront(v.group)
}}}else{b.removeShape(x)
}$(p).removeData("edge");
$(p).removeData("from_terminal");
$(p).removeData("edge_terminal");
$(p).removeData("dragged_guide");
if(y){b.remove(y.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}}})
}})
}else{b.removeTerminal(q)
}},mouseout:function(s){if($(q).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE&&$(p).data("edge")){b.remove(q.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(p).removeData("edge_terminal")
}}})
}};
this.enableDragAndDropGroup=function(r){var p=b.getRootGroup(),q;
if(r&&$(r).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){$(r).bind({mouseover:function(){if($(p).data("bBoxArray")){q=false;
$.each($(p).data("bBoxArray"),function(s,t){if(r.id===t.id){q=true
}});
if(!q){$(p).data("groupTarget",r);
b.drawDropOverGuide(r)
}}},mouseout:function(s){b.remove(r.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(p).removeData("groupTarget")
}})
}};
this.enableCollapse=function(r){var q,p;
p=function(s,t){if(t&&t.bBox&&t.collapse){$(t.collapse).bind("click",function(u){if(s.shape.isCollapsed===true){b.expand(s);
t=b.drawCollapseGuide(s);
p(s,t)
}else{b.collapse(s);
t=b.drawCollapseGuide(s);
p(s,t)
}});
$(t.bBox).bind("mouseout",function(u){b.remove(s.id+OG.Constants.COLLAPSE_BBOX);
b.remove(s.id+OG.Constants.COLLAPSE_SUFFIX)
})
}};
if(r&&$(r).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){$(r).bind({mouseover:function(){q=b.drawCollapseGuide(this);
if(q&&q.bBox&&q.collapse){p(r,q)
}}})
}};
this.setMovable=function(r,q){var p=b.getRootGroup();
if(!r){return
}if(q){$(r).draggable({start:function(t){var u=o(t),s;
if(b.getElementById(r.id+OG.Constants.GUIDE_SUFFIX.GUIDE)===null){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(v,w){if(OG.Util.isElement(w)&&w.id){b.removeGuide(w)
}});
b.removeAllTerminal()
}b.removeGuide(r);
s=b.drawGuide(r);
$(this).data("start",{x:u.x,y:u.y});
$(this).data("offset",{x:u.x-h(b.getAttr(s.bBox,"x")),y:u.y-h(b.getAttr(s.bBox,"y"))});
$(p).data("bBoxArray",e());
b.removeRubberBand(b.getRootElement());
b.removeAllTerminal()
},drag:function(u){var v=o(u),x=$(this).data("start"),w=$(p).data("bBoxArray"),t=a(v.x-x.x),s=a(v.y-x.y);
i(v.x,v.y);
$(this).css({position:"",left:"",top:""});
$.each(w,function(y,z){b.setAttr(z.box,{transform:"t"+t+","+s})
});
b.removeAllTerminal()
},stop:function(s){var v=o(s),t=$(this).data("start"),y=$(p).data("bBoxArray"),A=a(v.x-t.x),z=a(v.y-t.y),w=$(p).data("groupTarget"),x,u;
$(this).css({position:"",left:"",top:""});
x=g(y,A,z);
if(w&&OG.Util.isElement(w)){b.addToGroup(w,x);
$.each(x,function(B,C){b.removeGuide(C)
});
u=b.drawGuide(w);
f(w,u,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.toFront(u.group);
b.remove(w.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(p).removeData("groupTarget")
}else{b.addToGroup(p,x);
$.each(x,function(B,C){b.removeGuide(C);
u=b.drawGuide(C);
f(C,u,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.toFront(u.group)
})
}$(p).removeData("bBoxArray")
}});
b.setAttr(r,{cursor:"move"})
}else{$(r).draggable("destroy");
b.setAttr(r,{cursor:OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
this.setClickSelectable=function(q,p){if(p){$(q).bind("click",function(s){var r;
if(q.shape){if(!s.shiftKey&&!s.ctrlKey){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(t,u){if(u.id){b.removeGuide(u)
}})
}if($(q).attr("_selected")==="true"){if(s.shiftKey||s.ctrlKey){b.removeGuide(q)
}}else{j(q);
if(!l(q)){r=b.drawGuide(q);
if(r){f(q,r,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.removeAllTerminal();
b.toFront(r.group)
}}}return false
}});
if(p&&OG.Constants.MOVABLE){b.setAttr(q,{cursor:"move"})
}else{b.setAttr(q,{cursor:"pointer"})
}}else{$(q).click("destroy");
b.setAttr(q,{cursor:OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
this.setDragSelectable=function(p){var q=b.getRootElement();
$(q).bind("click",function(s){var r=$(this).data("dragBox");
if(!r||(r&&r.width<1&&r.height<1)){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(t,u){if(OG.Util.isElement(u)&&u.id){b.removeGuide(u)
}});
b.removeAllTerminal()
}});
if(p){$(q).bind("mousedown",function(r){var s=o(r);
$(this).data("dragBox_first",{x:s.x,y:s.y});
b.drawRubberBand([s.x,s.y])
});
$(q).bind("mousemove",function(u){var w=$(this).data("dragBox_first"),v,t,s,r,z;
if(w){v=o(u);
t=v.x-w.x;
s=v.y-w.y;
r=t<=0?w.x+t:w.x;
z=s<=0?w.y+s:w.y;
b.drawRubberBand([r,z],[Math.abs(t),Math.abs(s)])
}});
$(q).bind("mouseup",function(r){var v=$(this).data("dragBox_first"),u,s,B,A,z,w,t;
b.removeRubberBand(q);
if(v){u=o(r);
s=u.x-v.x;
B=u.y-v.y;
A=s<=0?v.x+s:v.x;
z=B<=0?v.y+B:v.y;
w=new OG.Envelope([A,z],Math.abs(s),Math.abs(B));
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(x,y){if(y.shape.geom&&w.isContainsAll(y.shape.geom.getVertices())){j(y);
if(!l(y)){t=b.drawGuide(y);
if(t){f(y,t,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.removeAllTerminal()
}}}});
$(this).data("dragBox",{width:s,height:B,x:A,y:z})
}});
$(q).bind("contextmenu",function(r){b.removeRubberBand(q)
})
}else{$(q).unbind("mousedown");
$(q).unbind("mousemove");
$(q).unbind("mouseup");
$(q).unbind("contextmenu")
}};
this.setEnableHotKey=function(q){var p=b.getRootGroup();
if(q){$(window.document).bind("keydown",function(u){var s,w,t,r,v;
if(OG.Constants.ENABLE_HOTKEY_DELETE&&u.keyCode===KeyEvent.DOM_VK_DELETE){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape=EDGE][_selected=true]").each(function(x,y){if(y.id){b.removeShape(y)
}});
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(x,y){if(y.id){b.removeShape(y)
}})
}if(OG.Constants.ENABLE_HOTKEY_CTRL_A&&OG.Constants.SELECTABLE&&u.ctrlKey&&u.keyCode===KeyEvent.DOM_VK_A){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(y,z){if($(z.parentNode).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){var x=b.drawGuide(z);
if(x){f(z,x,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.removeTerminal(z)
}}})
}if(OG.Constants.ENABLE_HOTKEY_CTRL_C&&u.ctrlKey&&u.keyCode===KeyEvent.DOM_VK_C){w=[];
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(x,y){w.push(y)
});
$(p).data("copied",w)
}if(OG.Constants.ENABLE_HOTKEY_CTRL_V&&u.ctrlKey&&u.keyCode===KeyEvent.DOM_VK_V){s=$(p).data("copied");
w=[];
if(s){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(x,y){if(y.id){b.removeGuide(y)
}});
$.each(s,function(x,A){var C=A.shape.geom.getBoundary(),z,B,y;
z=A.shape.clone();
if($(A).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){z.geom=new OG.PolyLine(A.shape.geom.getVertices());
z.geom.style=A.shape.geom.style;
z.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
B=b.drawShape(null,z,null,A.shapeStyle)
}else{B=b.drawShape([C.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,C.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],z,[C.getWidth(),C.getHeight()],A.shapeStyle)
}y=b.drawGuide(B);
d.setClickSelectable(B,OG.Constants.SELECTABLE);
d.setMovable(B,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
f(B,y,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(B)
}if(OG.Constants.GROUP_COLLAPSIBLE){d.enableCollapse(B)
}if($(B).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){d.enableConnect(B)
}if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(B)
}}n(A,B);
w.push(B)
});
$(p).data("copied",w)
}}if(OG.Constants.ENABLE_HOTKEY_CTRL_G&&u.ctrlKey&&u.keyCode===KeyEvent.DOM_VK_G){t=b.group($("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]"));
if(t){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(x,y){b.removeGuide(y)
});
r=b.drawGuide(t);
if(r){d.setClickSelectable(t,OG.Constants.SELECTABLE);
d.setMovable(t,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
f(t,r,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(t)
}b.removeAllTerminal();
b.toFront(r.group)
}}}if(OG.Constants.ENABLE_HOTKEY_CTRL_U&&u.ctrlKey&&u.keyCode===KeyEvent.DOM_VK_U){v=b.ungroup($("[_shape="+OG.Constants.SHAPE_TYPE.GROUP+"][_selected=true]"));
$.each(v,function(x,y){r=b.drawGuide(y);
if(r){b.removeAllTerminal();
b.toFront(r.group)
}})
}if(OG.Constants.ENABLE_HOTKEY_ARROW){if(u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_LEFT){g(e(),-1,0)
}if(u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_RIGHT){g(e(),1,0)
}if(u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_UP){g(e(),0,-1)
}if(u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_DOWN){g(e(),0,1)
}if(!u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_LEFT){g(e(),-5,0)
}if(!u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_RIGHT){g(e(),5,0)
}if(!u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_UP){g(e(),0,-5)
}if(!u.shiftKey&&u.keyCode===KeyEvent.DOM_VK_DOWN){g(e(),0,5)
}}})
}else{$(window.document).unbind("keydown")
}}
};
OG.handler.EventHandler.prototype=new OG.handler.EventHandler();
OG.handler.EventHandler.prototype.constructor=OG.handler.EventHandler;
OG.EventHandler=OG.handler.EventHandler;OG.graph.Canvas=function(container,containerSize,backgroundColor){var _RENDERER=container?new OG.RaphaelRenderer(container,containerSize,backgroundColor):null,_HANDLER=new OG.EventHandler(_RENDERER),_CONTAINER=OG.Util.isElement(container)?container:document.getElementById(container);
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
this.CONFIG_INITIALIZED=true
};
this.getRenderer=function(){return _RENDERER
};
this.getContainer=function(){return _CONTAINER
};
this.getEventHandler=function(){return _HANDLER
};
this.drawShape=function(position,shape,size,style,id,parentId){var element=_RENDERER.drawShape(position,shape,size,style,id);
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
this.setStyle=function(shapeElement,style){if(OG.Util.isElement(shapeElement)&&shapeElement.shape&&shapeElement.shape.geom){OG.Util.apply(shapeElement.shape.geom.style.map,style||{});
_RENDERER.redrawShape(shapeElement)
}};
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
this.toJSON=function(){var rootBBox=_RENDERER.getRootBBox(),rootGroup=_RENDERER.getRootGroup(),jsonObj={opengraph:{"@width":rootBBox.width,"@height":rootBBox.height,cell:[]}},childShape;
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
}if($(item).attr("_to")){cell["@to"]=$(item).attr("_to")
}if($(item).attr("_fromedge")){cell["@fromEdge"]=$(item).attr("_fromedge")
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
}element=this.drawShape([x,y],shape,[width,height],OG.JSON.decode(style),id,parent);
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
}}}element=this.drawShape(null,shape,null,OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.HTML:shape=eval("new "+shapeId+"()");
if(value){shape.html=unescape(value)
}if(label){shape.label=label
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.IMAGE:shape=eval("new "+shapeId+"('"+value+"')");
if(label){shape.label=label
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.TEXT:shape=eval("new "+shapeId+"()");
if(value){shape.text=unescape(value)
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent);
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
this.getNextShapes=function(element){var nextEdges=this.getNextEdges(element),shapeArray=[],nextShapeId,shape,i;
for(i=0;
i<nextEdges.length;
i++){nextShapeId=$(nextEdges[i]).attr("_to");
if(nextShapeId){nextShapeId=nextShapeId.substring(0,nextShapeId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP));
shape=this.getElementById(nextShapeId);
if(shape){shapeArray.push(shape)
}}}return shapeArray
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