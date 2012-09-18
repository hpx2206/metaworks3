var OG=window.OG||{};
OG.common={};
OG.geometry={};
OG.graph={};
OG.handler={};
OG.layout={};
OG.renderer={};
OG.shape={};
OG.shape.bpmn={};
OG.common.Constants={CANVAS_BACKGROUND:"#f9f9f9",GEOM_TYPE:{NULL:0,POINT:1,LINE:2,POLYLINE:3,POLYGON:4,RECTANGLE:5,CIRCLE:6,ELLIPSE:7,CURVE:8,BEZIER_CURVE:9,COLLECTION:10},GEOM_NAME:["","Point","Line","PolyLine","Polygon","Rectangle","Circle","Ellipse","Curve","BezierCurve","Collection"],NUM_PRECISION:0,NODE_TYPE:{ROOT:"ROOT",SHAPE:"SHAPE"},SHAPE_TYPE:{GEOM:"GEOM",TEXT:"TEXT",HTML:"HTML",IMAGE:"IMAGE",EDGE:"EDGE",GROUP:"GROUP"},EDGE_TYPE:{STRAIGHT:"straight",PLAIN:"plain",BEZIER:"bezier"},EDGE_PADDING:20,LABEL_PADDING:5,LABEL_EDITOR_WIDTH:70,LABEL_EDITOR_HEIGHT:16,LABEL_SUFFIX:"_LABEL",LABEL_EDITOR_SUFFIX:"_LABEL_EDITOR",DEFAULT_STYLE:{SHAPE:{cursor:"default"},GEOM:{stroke:"black",fill:"white","fill-opacity":0,"label-position":"center"},TEXT:{stroke:"none","text-anchor":"middle"},HTML:{"label-position":"bottom","text-anchor":"top","vertical-align":"top"},IMAGE:{"label-position":"bottom","text-anchor":"top","vertical-align":"top"},EDGE:{stroke:"black","stroke-width":2,"edge-type":"plain","edge-direction":"c c","arrow-start":"none","arrow-end":"classic-wide-long","stroke-dasharray":"","label-position":"center"},EDGE_SHADOW:{stroke:"#00FF00","stroke-width":2,"arrow-start":"none","arrow-end":"none","stroke-dasharray":"- "},GROUP:{stroke:"none",fill:"white","fill-opacity":0,"label-position":"bottom","text-anchor":"middle","vertical-align":"top"},GUIDE_BBOX:{stroke:"#00FF00",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},GUIDE_UL:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_UR:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LL:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LR:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_LC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_UC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_RC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_LWC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_FROM:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_TO:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_CTL_H:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_CTL_V:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_SHADOW:{stroke:"black",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},RUBBER_BAND:{stroke:"#0000FF",opacity:0.2,fill:"#0077FF"},TERMINAL:{stroke:"#808080","stroke-width":1,fill:"r(0.5, 0.5)#FFFFFF-#808080","fill-opacity":0.5,cursor:"pointer"},TERMINAL_OVER:{stroke:"#0077FF","stroke-width":4,fill:"r(0.5, 0.5)#FFFFFF-#0077FF","fill-opacity":1,cursor:"pointer"},TERMINAL_BBOX:{stroke:"none",fill:"white","fill-opacity":0},DROP_OVER_BBOX:{stroke:"#0077FF",fill:"none",opacity:0.6,"shape-rendering":"crispEdges"},LABEL:{"font-size":12,"font-color":"black"},LABEL_EDITOR:{position:"absolute",overflow:"visible",resize:"none","text-align":"center",display:"block",padding:0},COLLAPSE:{stroke:"black",fill:"white","fill-opacity":0,cursor:"pointer","shape-rendering":"crispEdges"},COLLAPSE_BBOX:{stroke:"none",fill:"white","fill-opacity":0}},RUBBER_BAND_ID:"OG_R_BAND",GUIDE_SUFFIX:{GUIDE:"_GUIDE",BBOX:"_GUIDE_BBOX",UL:"_GUIDE_UL",UR:"_GUIDE_UR",LL:"_GUIDE_LL",LR:"_GUIDE_LR",LC:"_GUIDE_LC",UC:"_GUIDE_UC",RC:"_GUIDE_RC",LWC:"_GUIDE_LWC",FROM:"_GUIDE_FROM",TO:"_GUIDE_TO",CTL:"_GUIDE_CTL_",CTL_H:"_GUIDE_CTL_H_",CTL_V:"_GUIDE_CTL_V_"},GUIDE_RECT_SIZE:8,GUIDE_MIN_SIZE:18,COLLAPSE_SUFFIX:"_COLLAPSE",COLLAPSE_BBOX_SUFFIX:"_COLLAPSE_BBOX",COLLAPSE_SIZE:10,MOVE_SNAP_SIZE:5,DROP_OVER_BBOX_SUFFIX:"_DROP_OVER",TERMINAL_SUFFIX:{GROUP:"_TERMINAL",BOX:"_TERMINAL_BOX"},TERMINAL_TYPE:{C:"C",E:"E",W:"W",S:"S",N:"N",IN:"IN",OUT:"OUT",INOUT:"INOUT"},TERMINAL_SIZE:3,COPY_PASTE_PADDING:20,AUTO_EXTENSIONAL:true,AUTO_EXTENSION_SIZE:100,SELECTABLE:true,DRAG_SELECTABLE:true,MOVABLE:true,RESIZABLE:true,CONNECTABLE:true,CONNECT_CLONEABLE:true,CONNECT_REQUIRED:true,LABEL_EDITABLE:true,GROUP_DROPABLE:true,GROUP_COLLAPSIBLE:true,ENABLE_HOTKEY:true};
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
for(var a in d){if(d.hasOwnProperty(a)&&a.indexOf("@")==-1){return c(a,d[a],{})
}}return null
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
this.toString=function(){var f=[];
f.push("type:'"+OG.Constants.GEOM_NAME[this.TYPE]+"'");
f.push("upperLeft:"+this.vertices[0]);
f.push("width:"+(this.vertices[2].x-this.vertices[0].x));
f.push("height:"+(this.vertices[2].y-this.vertices[0].y));
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
OG.ImageShape=OG.shape.ImageShape;OG.shape.EdgeShape=function(c,b,a){this.TYPE=OG.Constants.SHAPE_TYPE.EDGE;
this.SHAPE_ID="OG.shape.EdgeShape";
this.from=c;
this.to=b;
this.label=a;
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.Line(c,b);
return this.geom
};
this.clone=function(){return new OG.shape.EdgeShape(this.from,this.to,this.label)
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
this.htmlString=b||"";
this.label=a;
this.angle=0;
this.createTerminal=function(){if(!this.geom){return[]
}var c=this.geom.getBoundary();
return[new OG.Terminal(c.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(c.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){return this.htmlString
};
this.clone=function(){return new OG.shape.HtmlShape(this.htmlString,this.label)
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
this.geom.style=new OG.geometry.Style({stroke:"black"});
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
this.geom.style=new OG.geometry.Style({"stroke-dasharray":"-"});
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
d=function(C,E,w,F){var A=0,G="",D,B,x,y,v,z={};
if(F){OG.Util.apply(z,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Util.apply({},E.style.map,OG.Util.apply({},F,OG.Constants.DEFAULT_STYLE.GEOM)))
}else{OG.Util.apply(z,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Util.apply({},E.style.map,OG.Constants.DEFAULT_STYLE.GEOM))
}E.style.map=z;
switch(E.TYPE){case OG.Constants.GEOM_TYPE.POINT:B=o.circle(E.coordinate.x,E.coordinate.y,0.5);
B.attr(z);
break;
case OG.Constants.GEOM_TYPE.LINE:case OG.Constants.GEOM_TYPE.POLYLINE:case OG.Constants.GEOM_TYPE.POLYGON:case OG.Constants.GEOM_TYPE.RECTANGLE:G="";
D=E.getVertices();
for(A=0;
A<D.length;
A++){if(A===0){G="M"+D[A].x+" "+D[A].y
}else{G+="L"+D[A].x+" "+D[A].y
}}B=o.path(G);
B.attr(z);
break;
case OG.Constants.GEOM_TYPE.CIRCLE:x=OG.JSON.decode(E.toString());
if(x.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.CIRCLE]){B=o.circle(x.center[0],x.center[1],x.radius)
}else{if(x.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE]){if(x.angle===0){B=o.ellipse(x.center[0],x.center[1],x.radiusX,x.radiusY)
}else{G="";
D=E.getControlPoints();
G="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 0 "+D[5].x+" "+D[5].y;
G+="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 1 "+D[5].x+" "+D[5].y;
B=o.path(G)
}}}B.attr(z);
break;
case OG.Constants.GEOM_TYPE.ELLIPSE:x=OG.JSON.decode(E.toString());
if(x.angle===0){B=o.ellipse(x.center[0],x.center[1],x.radiusX,x.radiusY)
}else{G="";
D=E.getControlPoints();
G="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 0 "+D[5].x+" "+D[5].y;
G+="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 1 "+D[5].x+" "+D[5].y;
B=o.path(G)
}B.attr(z);
break;
case OG.Constants.GEOM_TYPE.CURVE:G="";
D=E.getControlPoints();
for(A=0;
A<D.length;
A++){if(A===0){G="M"+D[A].x+" "+D[A].y
}else{if(A===1){G+="R"+D[A].x+" "+D[A].y
}else{G+=" "+D[A].x+" "+D[A].y
}}}B=o.path(G);
B.attr(z);
break;
case OG.Constants.GEOM_TYPE.COLLECTION:for(A=0;
A<E.geometries.length;
A++){d(C,E.geometries[A],w,E.style.map)
}break
}if(B){n(B);
C.appendChild(B.node);
return B.node
}else{return C
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
this.drawShape=function(A,B,I,v,w){var x=I?I[0]:100,H=I?I[1]:100,y,E,G,z,D,C,F;
if(B instanceof OG.shape.GeomShape){E=B.createShape();
E.moveCentroid(A);
E.resizeBox(x,H);
y=this.drawGeom(E,v,w);
B.geom=y.geom
}else{if(B instanceof OG.shape.TextShape){G=B.createShape();
y=this.drawText(A,G,I,v,w);
B.text=y.text;
B.angle=y.angle;
B.geom=y.geom
}else{if(B instanceof OG.shape.ImageShape){z=B.createShape();
y=this.drawImage(A,z,I,v,w);
B.image=y.image;
B.angle=y.angle;
B.geom=y.geom
}else{if(B instanceof OG.shape.HtmlShape){D=B.createShape();
y=this.drawHtml(A,D,I,v,w);
B.htmlString=y.htmlString;
B.angle=y.angle;
B.geom=y.geom
}else{if(B instanceof OG.shape.EdgeShape){E=B.createShape();
y=this.drawEdge(E,v,w);
B.geom=y.geom
}else{if(B instanceof OG.shape.GroupShape){E=B.createShape();
E.moveCentroid(A);
E.resizeBox(x,H);
y=this.drawGroup(E,v,w);
B.geom=y.geom
}}}}}}y.shape=B;
y.shapeStyle=(v instanceof OG.geometry.Style)?v.map:v;
$(y).attr("_shape_id",B.SHAPE_ID);
if(!(B instanceof OG.shape.TextShape)){this.drawLabel(y)
}if(y.geom){if(OG.Util.isIE7()){y.removeAttribute("geom")
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
this.drawHtml=function(C,F,J,v,w){var x=J?J[0]:null,H=J?J[1]:null,A=J?J[2]||0:0,G,B,z={},I,E,y,D;
OG.Util.apply(z,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Constants.DEFAULT_STYLE.HTML);
if(w===0||w){G=u(w);
if(G){f(G)
}else{G=o.group();
n(G,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.HTML);
i.node.appendChild(G.node)
}}else{G=o.group();
n(G,w,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.HTML);
i.node.appendChild(G.node)
}B=o.foreignObject(F,C[0],C[1],x,H);
B.attr(z);
I=B.getBBox();
x=x||I.width;
H=H||I.height;
y=OG.Util.round(C[0]-x/2);
D=OG.Util.round(C[1]-H/2);
B.attr({x:y,y:D});
E=new OG.Rectangle([y,D],x,H);
if(A){B.rotate(A)
}E.style.map=z;
n(B);
G.node.appendChild(B.node);
G.node.htmlString=F;
G.node.angle=A;
G.node.geom=E;
G.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(G.node.shape){G.node.shape.htmlString=F;
G.node.shape.angle=A;
G.node.shape.geom=E;
if(G.node.htmlString){if(OG.Util.isIE7()){G.node.removeAttribute("htmlString")
}else{delete G.node.htmlString
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
}}else{F=A
}}if(z){x=new OG.Curve(F)
}else{x=new OG.PolyLine(F)
}d(G.node,x,y);
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
z=this.drawHtml([w.x,w.y],z.shape.htmlString,[x,D,z.shape.angle],z.shape.geom.style,z.id);
this.redrawConnectedEdge(z,C);
this.drawLabel(z);
break;
case OG.Constants.SHAPE_TYPE.EDGE:z=this.drawEdge(z.shape.geom,z.shape.geom.style,z.id);
this.drawLabel(z);
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
this.connect=function(I,v,y,L,D){var C={},N,F,E,H,J,z,A,M,w,B,K,G,x=function(Q,P,R){var S=$(Q).attr(P),T=S?S.split(","):[],O=[];
$.each(T,function(U,V){if(V!==R){O.push(V)
}});
O.push(R);
$(Q).attr(P,O.toString());
return Q
};
OG.Util.apply(C,(L instanceof OG.geometry.Style)?L.map:L||{},OG.Constants.DEFAULT_STYLE.EDGE);
if(OG.Util.isElement(I)){N=g(I);
H=[I.terminal.position.x,I.terminal.position.y];
M=I.terminal.direction.toLowerCase()
}else{H=I;
M="c"
}if(OG.Util.isElement(v)){F=g(v);
J=[v.terminal.position.x,v.terminal.position.y];
w=v.terminal.direction.toLowerCase()
}else{J=v;
w="c"
}z=H;
A=J;
B=M;
K=w;
if(N&&M==="c"){E=this.intersectionEdge(C["edge-type"],N,z,A,true);
H=E.position;
M=E.direction
}if(F&&w==="c"){E=this.intersectionEdge(C["edge-type"],F,z,A,false);
J=E.position;
w=E.direction
}G=N&&F&&N.id===F.id;
if(G){H=J=N.shape.geom.getBoundary().getRightCenter()
}y=this.drawEdge(new OG.Line(H,J),OG.Util.apply(C,{"edge-direction":M+" "+w}),y?y.id:null,G);
this.drawLabel(y,D);
OG.Util.apply(y.shape.geom.style.map,{"edge-direction":B+" "+K});
y.shapeStyle=y.shape.geom.style.map;
this.disconnect(y);
if(OG.Util.isElement(I)){$(y).attr("_from",I.id);
x(N,"_toedge",y.id)
}if(OG.Util.isElement(v)){$(y).attr("_to",v.id);
x(F,"_fromedge",y.id)
}this.removeAllTerminal();
if(N&&F){$(o.canvas).trigger("connectShape",[y,N,F])
}return y
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
}$(o.canvas).trigger("disconnectShape",[A,F,y])
}else{w=$(A).attr("_fromedge");
v=$(A).attr("_toedge");
if(w){$.each(w.split(","),function(G,H){C=D.getElementById(H);
E=$(C).attr("_from");
if(E){F=g(E);
x(F,"_toedge",H)
}$(o.canvas).trigger("disconnectShape",[C,F,A]);
D.remove(C)
})
}if(v){$.each(v.split(","),function(G,H){z=D.getElementById(H);
B=$(z).attr("_to");
if(B){y=g(B);
x(y,"_fromedge",H)
}$(o.canvas).trigger("disconnectShape",[z,A,y]);
D.remove(z)
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
this.getRootBBox=function(){var z=o.canvas.parentNode,A=o.canvas.clientWidth,w=o.canvas.clientHeight,v=OG.Util.isIE7()?z.offsetLeft+z.parentNode.offsetLeft:z.offsetLeft,B=OG.Util.isIE7()?z.offsetTop+z.parentNode.offsetTop:z.offsetTop;
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
OG.RaphaelRenderer=OG.renderer.RaphaelRenderer;OG.handler.EventHandler=function(i){var d=this,b=i,e,j,h,l,g,f=function(m){return parseInt(m,10)
},a=function(m){return OG.Util.round(m/OG.Constants.MOVE_SNAP_SIZE)*5
},k=function(m){var n=OG.Util.isElement(m)?m.id:m;
return b.getElementById(n.substring(0,n.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)))
},c=function(m,u){var s,q,t,n,r=false,p=false,o;
s=$(m).attr("_from");
q=$(m).attr("_to");
if(s){t=k(s)
}if(q){n=k(q)
}for(o=0;
o<u.length;
o++){if(t&&u[o].id===t.id){r=true
}if(n&&u[o].id===n.id){p=true
}}return{none:!r&&!p,all:r&&p,any:r||p,either:(r&&!p)||(!r&&p),attrEither:(s&&!q)||(!s&&q)}
};
e=function(q,m,r){var p=b.getContainer(),o=b.getRootGroup(),n=b.getRootBBox();
if(!q||!m){return
}if(r){if($(q).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){$(m.from).draggable({start:function(x){var v=q.shape.geom.getVertices(),s={},t=$(q).attr("_to"),y,u=[v[v.length-1].x,v[v.length-1].y],w=b.drawEdge(new OG.PolyLine(v),OG.Util.apply(s,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,q.shape.geom.style.map));
if(t){y=k(t);
b.drawTerminal(y);
u=b.getElementById(t)
}$(o).data("to_terminal",u);
$(o).data("edge",w);
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(A,z){if(z.id&&$(z).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){b.removeGuide(z)
}})
},drag:function(s){var u=$(o).data("edge"),A=$(o).data("edge_terminal"),F=$(o).data("to_terminal"),B=A?[A.terminal.position.x,A.terminal.position.y]:[p.scrollLeft+s.pageX-n.x,p.scrollTop+s.pageY-n.y],x=OG.Util.isElement(F)?[F.terminal.position.x,F.terminal.position.y]:F,C=A?A.terminal.direction.toLowerCase():"c",y=OG.Util.isElement(F)?F.terminal.direction.toLowerCase():"c",D=A?k(A):null,w=OG.Util.isElement(F)?k(F):null,t,E,v,z;
$(this).css({position:"",left:"",top:""});
t=B;
E=x;
if(D&&C==="c"){v=b.intersectionEdge(u.geom.style.get("edge-type"),D,[t[0],t[1]],[E[0],E[1]],true);
B=v.position;
C=v.direction
}if(w&&y==="c"){v=b.intersectionEdge(u.geom.style.get("edge-type"),w,[t[0],t[1]],[E[0],E[1]],false);
x=v.position;
y=v.direction
}z=D&&w&&D.id===w.id;
if(z){B=x=D.shape.geom.getBoundary().getRightCenter()
}b.drawEdge(new OG.Line(B,x),OG.Util.apply(u.geom.style.map,{"edge-direction":C+" "+y}),u.id,z)
},stop:function(v){var w=$(o).data("edge_terminal")||[p.scrollLeft+v.pageX-n.x,p.scrollTop+v.pageY-n.y],s=$(o).data("to_terminal"),t=OG.Util.isElement(w)?k(w):null,u=$(o).data("edge");
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(o).removeData("to_terminal");
$(o).removeData("edge");
$(o).removeData("edge_terminal");
b.remove(u);
b.removeGuide(q);
if(t){b.remove(t.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}q=b.connect(w,s,q,q.shape.geom.style);
m=b.drawGuide(q);
e(q,m,true);
b.toFront(m.group);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}});
$(m.to).draggable({start:function(x){var u=q.shape.geom.getVertices(),t={},s=$(q).attr("_from"),v,y=[u[0].x,u[0].y],w=b.drawEdge(new OG.PolyLine(u),OG.Util.apply(t,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,q.shape.geom.style.map));
if(s){v=k(s);
b.drawTerminal(v);
y=b.getElementById(s)
}$(o).data("from_terminal",y);
$(o).data("edge",w);
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(A,z){if(z.id&&$(z).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){b.removeGuide(z)
}})
},drag:function(s){var u=$(o).data("edge"),A=$(o).data("from_terminal"),F=$(o).data("edge_terminal"),B=OG.Util.isElement(A)?[A.terminal.position.x,A.terminal.position.y]:A,x=F?[F.terminal.position.x,F.terminal.position.y]:[p.scrollLeft+s.pageX-n.x,p.scrollTop+s.pageY-n.y],C=OG.Util.isElement(A)?A.terminal.direction.toLowerCase():"c",y=F?F.terminal.direction.toLowerCase():"c",D=OG.Util.isElement(A)?k(A):null,w=F?k(F):null,t,E,v,z;
$(this).css({position:"",left:"",top:""});
t=B;
E=x;
if(D&&C==="c"){v=b.intersectionEdge(u.geom.style.get("edge-type"),D,[t[0],t[1]],[E[0],E[1]],true);
B=v.position;
C=v.direction
}if(w&&y==="c"){v=b.intersectionEdge(u.geom.style.get("edge-type"),w,[t[0],t[1]],[E[0],E[1]],false);
x=v.position;
y=v.direction
}z=(D!==null)&&(w!==null)&&D.id===w.id;
if(z){B=x=w.shape.geom.getBoundary().getRightCenter()
}b.drawEdge(new OG.Line(B,x),OG.Util.apply(u.geom.style.map,{"edge-direction":C+" "+y}),u.id,z)
},stop:function(u){var w=$(o).data("from_terminal"),s=$(o).data("edge_terminal")||[p.scrollLeft+u.pageX-n.x,p.scrollTop+u.pageY-n.y],v=OG.Util.isElement(s)?k(s):null,t=$(o).data("edge");
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(o).removeData("from_terminal");
$(o).removeData("edge");
$(o).removeData("edge_terminal");
b.remove(t);
b.removeGuide(q);
if(v){b.remove(v.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}q=b.connect(w,s,q,q.shape.geom.style);
m=b.drawGuide(q);
e(q,m,true);
b.toFront(m.group);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}});
$.each(m.controls,function(s,t){$(t).draggable({start:function(v){var u=p.scrollLeft+v.pageX,w=p.scrollTop+v.pageY;
$(this).data("start",{x:u,y:w});
$(this).data("offset",{x:u-f(b.getAttr(t,"x")),y:w-f(b.getAttr(t,"y"))});
b.remove(m.bBox);
b.removeRubberBand(b.getRootElement())
},drag:function(v){var E=p.scrollLeft+v.pageX,C=p.scrollTop+v.pageY,w=$(this).data("start"),z=$(this).data("offset"),u=E-z.x,F=C-z.y,B=q.shape.geom.getVertices(),D=t.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,A=D?parseInt(t.id.replace(q.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(t.id.replace(q.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"",left:"",top:""});
if(D){B[A].x=u;
B[A+1].x=u
}else{B[A].y=F;
B[A+1].y=F
}q=b.drawEdge(new OG.PolyLine(B),q.shape.geom.style,q.id);
b.drawGuide(q);
b.removeAllTerminal();
b.drawLabel(q)
},stop:function(v){var E=p.scrollLeft+v.pageX,C=p.scrollTop+v.pageY,w=$(this).data("start"),z=$(this).data("offset"),u=E-z.x,F=C-z.y,B=q.shape.geom.getVertices(),D=t.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,A=D?parseInt(t.id.replace(q.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(t.id.replace(q.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(D){B[A].x=u;
B[A+1].x=u
}else{B[A].y=F;
B[A+1].y=F
}q=b.drawEdge(new OG.PolyLine(B),q.shape.geom.style,q.id);
b.drawGuide(q);
b.drawLabel(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}})
})
}else{$(m.rc).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.rc,"x")),y:u-f(b.getAttr(m.rc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(v){var s=p.scrollLeft+v.pageX,B=p.scrollTop+v.pageY,A=$(this).data("start"),z=$(this).data("offset"),t=s-A.x,w=s-z.x,u=w-f(b.getAttr(m.lc,"x"));
$(this).css({position:"",left:"",top:""});
if(u>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.rc,{x:w});
b.setAttr(m.ur,{x:w});
b.setAttr(m.lr,{x:w});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+w)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+w)/2)});
b.setAttr(m.bBox,{width:u})
}b.removeAllTerminal()
},stop:function(u){var s=p.scrollLeft+u.pageX,w=p.scrollTop+u.pageY,v=$(this).data("start"),t=s-v.x;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getWidth()
}b.resize(q,[0,0,0,t]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(m.lwc).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.lwc,"x")),y:u-f(b.getAttr(m.lwc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var s=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,z=$(this).data("start"),w=$(this).data("offset"),v=A-w.y,t=v-f(b.getAttr(m.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lwc,{y:v});
b.setAttr(m.ll,{y:v});
b.setAttr(m.lr,{y:v});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+v)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+v)/2)});
b.setAttr(m.bBox,{height:t})
}b.removeAllTerminal()
},stop:function(u){var s=p.scrollLeft+u.pageX,w=p.scrollTop+u.pageY,v=$(this).data("start"),t=w-v.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getHeight()
}b.resize(q,[0,t,0,0]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(m.lr).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.lr,"x")),y:u-f(b.getAttr(m.lr,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var B=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,v=$(this).data("start"),w=$(this).data("offset"),t=B-w.x,z=t-f(b.getAttr(m.lc,"x")),C=A-w.y,s=C-f(b.getAttr(m.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(z>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.rc,{x:t});
b.setAttr(m.ur,{x:t});
b.setAttr(m.lr,{x:t});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+t)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+t)/2)});
b.setAttr(m.bBox,{width:z})
}if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lwc,{y:C});
b.setAttr(m.ll,{y:C});
b.setAttr(m.lr,{y:C});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+C)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+C)/2)});
b.setAttr(m.bBox,{height:s})
}b.removeAllTerminal()
},stop:function(v){var s=p.scrollLeft+v.pageX,z=p.scrollTop+v.pageY,w=$(this).data("start"),u=s-w.x,t=z-w.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getWidth()
}if(q.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getHeight()
}b.resize(q,[0,t,0,u]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}b.removeAllTerminal()
}});
$(m.lc).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.lc,"x")),y:u-f(b.getAttr(m.lc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var s=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,z=$(this).data("start"),w=$(this).data("offset"),v=s-w.x,t=f(b.getAttr(m.rc,"x"))-v;
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lc,{x:v});
b.setAttr(m.ul,{x:v});
b.setAttr(m.ll,{x:v});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+v)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+v)/2)});
b.setAttr(m.bBox,{x:OG.Util.round(v+f(b.getAttr(m.lc,"width"))/2),width:t})
}b.removeAllTerminal()
},stop:function(u){var s=p.scrollLeft+u.pageX,w=p.scrollTop+u.pageY,v=$(this).data("start"),t=v.x-s;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getWidth()
}b.resize(q,[0,0,t,0]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(m.ll).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.ll,"x")),y:u-f(b.getAttr(m.ll,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var B=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,v=$(this).data("start"),w=$(this).data("offset"),t=B-w.x,C=A-w.y,z=f(b.getAttr(m.rc,"x"))-t,s=C-f(b.getAttr(m.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(z>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lc,{x:t});
b.setAttr(m.ul,{x:t});
b.setAttr(m.ll,{x:t});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+t)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+t)/2)});
b.setAttr(m.bBox,{x:OG.Util.round(t+f(b.getAttr(m.lc,"width"))/2),width:z})
}if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lwc,{y:C});
b.setAttr(m.ll,{y:C});
b.setAttr(m.lr,{y:C});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+C)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+C)/2)});
b.setAttr(m.bBox,{height:s})
}b.removeAllTerminal()
},stop:function(v){var s=p.scrollLeft+v.pageX,z=p.scrollTop+v.pageY,w=$(this).data("start"),u=w.x-s,t=z-w.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getWidth()
}if(q.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getHeight()
}b.resize(q,[0,t,u,0]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(m.uc).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.uc,"x")),y:u-f(b.getAttr(m.uc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var s=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,z=$(this).data("start"),w=$(this).data("offset"),v=A-w.y,t=f(b.getAttr(m.lwc,"y"))-v;
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.uc,{y:v});
b.setAttr(m.ul,{y:v});
b.setAttr(m.ur,{y:v});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+v)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+v)/2)});
b.setAttr(m.bBox,{y:OG.Util.round(v+f(b.getAttr(m.uc,"width"))/2),height:t})
}b.removeAllTerminal()
},stop:function(u){var s=p.scrollLeft+u.pageX,w=p.scrollTop+u.pageY,v=$(this).data("start"),t=v.y-w;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getHeight()
}b.resize(q,[t,0,0,0]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(m.ul).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.ul,"x")),y:u-f(b.getAttr(m.ul,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var B=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,v=$(this).data("start"),w=$(this).data("offset"),t=B-w.x,C=A-w.y,z=f(b.getAttr(m.rc,"x"))-t,s=f(b.getAttr(m.lwc,"y"))-C;
$(this).css({position:"",left:"",top:""});
if(z>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lc,{x:t});
b.setAttr(m.ul,{x:t});
b.setAttr(m.ll,{x:t});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+t)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+t)/2)});
b.setAttr(m.bBox,{x:OG.Util.round(t+f(b.getAttr(m.lc,"width"))/2),width:z})
}if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.uc,{y:C});
b.setAttr(m.ul,{y:C});
b.setAttr(m.ur,{y:C});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+C)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+C)/2)});
b.setAttr(m.bBox,{y:OG.Util.round(C+f(b.getAttr(m.uc,"height"))/2),height:s})
}b.removeAllTerminal()
},stop:function(v){var s=p.scrollLeft+v.pageX,z=p.scrollTop+v.pageY,w=$(this).data("start"),u=w.x-s,t=w.y-z;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getWidth()
}if(q.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getHeight()
}b.resize(q,[t,0,u,0]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}});
$(m.ur).draggable({start:function(t){var s=p.scrollLeft+t.pageX,u=p.scrollTop+t.pageY;
$(this).data("start",{x:s,y:u});
$(this).data("offset",{x:s-f(b.getAttr(m.ur,"x")),y:u-f(b.getAttr(m.ur,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var B=p.scrollLeft+u.pageX,A=p.scrollTop+u.pageY,v=$(this).data("start"),w=$(this).data("offset"),t=B-w.x,C=A-w.y,z=t-f(b.getAttr(m.lc,"x")),s=f(b.getAttr(m.lwc,"y"))-C;
$(this).css({position:"",left:"",top:""});
if(z>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.rc,{x:t});
b.setAttr(m.ur,{x:t});
b.setAttr(m.lr,{x:t});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+t)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+t)/2)});
b.setAttr(m.bBox,{width:z})
}if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.uc,{y:C});
b.setAttr(m.ul,{y:C});
b.setAttr(m.ur,{y:C});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+C)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+C)/2)});
b.setAttr(m.bBox,{y:OG.Util.round(C+f(b.getAttr(m.uc,"width"))/2),height:s})
}b.removeAllTerminal()
},stop:function(v){var s=p.scrollLeft+v.pageX,z=p.scrollTop+v.pageY,w=$(this).data("start"),u=s-w.x,t=w.y-z;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(q&&q.shape.geom){if(q.shape.geom.getBoundary().getWidth()+u<OG.Constants.GUIDE_MIN_SIZE){u=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getWidth()
}if(q.shape.geom.getBoundary().getHeight()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-q.shape.geom.getBoundary().getHeight()
}b.resize(q,[t,0,0,u]);
b.drawGuide(q);
b.setAttr(q,{cursor:OG.Constants.SELECTABLE&&OG.Constants.MOVABLE?"move":(OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor)})
}}})
}}else{if($(q).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){b.setAttr(m.from,{cursor:"default"});
b.setAttr(m.to,{cursor:"default"});
$.each(m.controls,function(s,t){b.setAttr(t,{cursor:"default"})
})
}else{b.setAttr(m.ul,{cursor:"default"});
b.setAttr(m.ur,{cursor:"default"});
b.setAttr(m.ll,{cursor:"default"});
b.setAttr(m.lr,{cursor:"default"});
b.setAttr(m.lc,{cursor:"default"});
b.setAttr(m.uc,{cursor:"default"});
b.setAttr(m.rc,{cursor:"default"});
b.setAttr(m.lwc,{cursor:"default"})
}}};
j=function(n){var m=n.parentNode;
if(m){if(j(m)){return true
}if($(m).attr("_type")===OG.Constants.NODE_TYPE.SHAPE&&$(m).attr("_selected")==="true"){return true
}}return false
};
h=function(n){var m=n.childNodes;
$.each(m,function(o,p){if($(p).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){if(p.childNodes.length>0){h(p)
}if($(p).attr("_selected")==="true"){b.removeGuide(p);
$(p).draggable("destroy")
}}})
};
l=function(n,o){var m=n.childNodes;
$.each(m,function(p,s){if($(s).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){var u=s.shape.geom.getBoundary(),r,t,q;
r=s.shape.clone();
if($(s).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){r.geom=new OG.PolyLine(s.shape.geom.getVertices());
r.geom.style=s.shape.geom.style;
r.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
t=b.drawShape(null,r,null,s.shapeStyle)
}else{t=b.drawShape([u.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,u.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],r,[u.getWidth(),u.getHeight()],s.shapeStyle)
}o.appendChild(t);
d.setClickSelectable(t,OG.Constants.SELECTABLE);
d.setMovable(t,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(t)
}if(OG.Constants.GROUP_COLLAPSIBLE){d.enableCollapse(t)
}if($(t).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){d.enableConnect(t)
}if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(t)
}}if(s.childNodes.length>0){l(s,t)
}}})
};
g=function(o,n){var m=b.getRootBBox();
if(OG.Constants.AUTO_EXTENSIONAL&&m.x2<o){b.setCanvasSize([m.width+OG.Constants.AUTO_EXTENSION_SIZE,m.height])
}if(OG.Constants.AUTO_EXTENSIONAL&&m.y2<n){b.setCanvasSize([m.width,m.height+OG.Constants.AUTO_EXTENSION_SIZE])
}};
this.setResizable=e;
this.enableEditLabel=function(o){var n=b.getContainer(),p=b.getRootElement(),m=(n.style&&n.style.position)?(n.style.position.toLowerCase()==="absolute"?true:false):false;
$(o).bind({dblclick:function(){var x=o.shape.geom.getBoundary(),s=x.getUpperLeft(),A=b.getRootBBox(),C,w=s.x+(m?0:A.x)-1,z=s.y+(m?0:A.y)-1,u=x.getWidth(),B=x.getHeight(),v=o.id+OG.Constants.LABEL_EDITOR_SUFFIX,r,t="center",y=function(H){var F,D,I=0,G,E;
F=H.shape.geom.getVertices();
D=H.shape.geom.getLength();
for(G=0;
G<F.length-1;
G++){I+=F[G].distance(F[G+1]);
if(I>D/2){E=H.shape.geom.intersectCircleToLine(F[G+1],I-D/2,F[G+1],F[G]);
break
}}return E[0]
},q;
$(p.parentNode).append("<textarea id='"+o.id+OG.Constants.LABEL_EDITOR_SUFFIX+"'></textarea>");
r=$("#"+v);
switch(o.shape.geom.style.get("text-anchor")){case"start":t="left";
break;
case"middle":t="center";
break;
case"end":t="right";
break;
default:t="center";
break
}if($(o).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT){$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:w,top:z,width:u,height:B,"text-align":t,overflow:"hidden",resize:"none"}));
$(r).focus();
$(r).val(o.shape.text);
$(r).bind({focusout:function(){o.shape.text=this.value;
if(o.shape.text){b.redrawShape(o);
this.parentNode.removeChild(this)
}else{b.removeShape(o);
this.parentNode.removeChild(this)
}}})
}else{if($(o).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(o.shape.label&&b.isSVG()){$(o).find("text").each(function(D,E){C=b.getBBox(E);
w=C.x+(m?0:A.x)-10;
z=C.y+(m?0:A.y);
u=C.width+20;
B=C.height
})
}else{q=y(o);
w=q.x-OG.Constants.LABEL_EDITOR_WIDTH/2+(m?0:A.x);
z=q.y-OG.Constants.LABEL_EDITOR_HEIGHT/2+(m?0:A.y);
u=OG.Constants.LABEL_EDITOR_WIDTH;
B=OG.Constants.LABEL_EDITOR_HEIGHT
}$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:w,top:z,width:u,height:B,overflow:"hidden",resize:"none"}));
$(r).focus();
$(r).val(o.shape.label);
$(r).bind({focusout:function(){b.drawLabel(o,this.value);
this.parentNode.removeChild(this)
}})
}else{$(r).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:w,top:z,width:u,height:B,"text-align":t,overflow:"hidden",resize:"none"}));
$(r).focus();
$(r).val(o.shape.label);
$(r).bind({focusout:function(){b.drawLabel(o,this.value);
this.parentNode.removeChild(this)
}})
}}}})
};
this.enableConnect=function(p){var q,o=b.getContainer(),n=b.getRootGroup(),m=b.getRootBBox();
if(p&&$(p).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){$(p).bind({mouseover:function(){q=b.drawTerminal(p,$(n).data("from_terminal")?OG.Constants.TERMINAL_TYPE.IN:OG.Constants.TERMINAL_TYPE.OUT);
if(q){if($(n).data("edge")){$.each(q.terminal.childNodes,function(r,s){if(s.terminal&&s.terminal.direction.toLowerCase()==="c"){b.drawDropOverGuide(p);
$(n).data("edge_terminal",s);
return false
}})
}$(q.bBox).bind({mouseout:function(){if(!$(n).data("edge")){b.removeTerminal(p)
}}});
$.each(q.terminal.childNodes,function(r,s){if(s.terminal){$(s).bind({mouseover:function(t){b.setAttr(s,OG.Constants.DEFAULT_STYLE.TERMINAL_OVER);
$(n).data("edge_terminal",s)
},mouseout:function(){b.setAttr(s,OG.Constants.DEFAULT_STYLE.TERMINAL);
$(n).removeData("edge_terminal")
}});
$(s).draggable({start:function(v){var t=s.terminal.position.x,w=s.terminal.position.y,u=b.drawShape(null,new OG.EdgeShape([t,w],[t,w]),null,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW);
$(n).data("edge",u);
$(n).data("from_terminal",s);
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(y,x){if(x.id){b.removeGuide(x)
}})
},drag:function(t){var v=$(n).data("edge"),B=$(n).data("from_terminal"),F=$(n).data("edge_terminal"),C=[B.terminal.position.x,B.terminal.position.y],y=F?[F.terminal.position.x,F.terminal.position.y]:[o.scrollLeft+t.pageX-m.x,o.scrollTop+t.pageY-m.y],D=B.terminal.direction.toLowerCase(),z=F?F.terminal.direction.toLowerCase():"c",x=F?k(F):null,u,E,w,A;
$(this).css({position:"",left:"",top:""});
u=C;
E=y;
if(!p.shape.geom.getBoundary().isContains(y)&&D==="c"){w=b.intersectionEdge(v.shape.geom.style.get("edge-type"),p,[u[0],u[1]],[E[0],E[1]],true);
C=w.position;
D=w.direction
}if(x&&z==="c"){w=b.intersectionEdge(v.shape.geom.style.get("edge-type"),x,[u[0],u[1]],[E[0],E[1]],false);
y=w.position;
z=w.direction
}A=p&&x&&p.id===x.id;
if(A){C=y=p.shape.geom.getBoundary().getRightCenter()
}b.drawEdge(new OG.Line(C,y),OG.Util.apply(v.shape.geom.style.map,{"edge-direction":D+" "+z}),v.id,A)
},stop:function(t){var D={x:o.scrollLeft+t.pageX-m.x,y:o.scrollTop+t.pageY-m.y},w=$(n).data("edge"),B=$(n).data("from_terminal"),E=$(n).data("edge_terminal")||[D.x,D.y],x=OG.Util.isElement(E)?k(E):null,v,y,C,A,u,z;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(!$(n).data("edge_terminal")&&OG.Constants.CONNECT_CLONEABLE){v=p.shape.geom.getBoundary();
y=b.drawShape([D.x,D.y],p.shape.clone(),[v.getWidth(),v.getHeight()],p.shapeStyle);
d.setClickSelectable(y,OG.Constants.SELECTABLE);
d.setMovable(y,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(y)
}if(OG.Constants.GROUP_COLLAPSIBLE){d.enableCollapse(y)
}if($(y).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){d.enableConnect(y)
}if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(y)
}}C=b.drawTerminal(y,OG.Constants.TERMINAL_TYPE.IN);
A=C.terminal.childNodes;
E=A[0];
for(z=0;
z<A.length;
z++){if(A[z].terminal&&A[z].terminal.direction.toLowerCase()==="c"){E=A[z];
break
}}}if(E&&(OG.Util.isElement(E)||!OG.Constants.CONNECT_REQUIRED)){w=b.connect(B,E,w);
u=b.drawGuide(w);
d.setClickSelectable(w,OG.Constants.SELECTABLE);
d.setMovable(w,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
e(w,u,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if($(w).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(w)
}}b.toFront(u.group)
}else{b.removeShape(w)
}$(n).removeData("edge");
$(n).removeData("from_terminal");
$(n).removeData("edge_terminal");
if(x){b.remove(x.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}}})
}})
}},mouseout:function(r){if($(p).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE&&$(n).data("edge")){b.remove(p.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(n).removeData("edge_terminal")
}}})
}};
this.enableDragAndDropGroup=function(o){var m=b.getRootGroup(),n;
if(o&&$(o).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){$(o).bind({mouseover:function(){if($(m).data("bBoxArray")){n=false;
$.each($(m).data("bBoxArray"),function(p,q){if(o.id===q.id){n=true
}});
if(!n){$(m).data("groupTarget",o);
b.drawDropOverGuide(o)
}}},mouseout:function(p){b.remove(o.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(m).removeData("groupTarget")
}})
}};
this.enableCollapse=function(o){var n,m;
m=function(p,q){if(q&&q.bBox&&q.collapse){$(q.collapse).bind("click",function(r){if(p.shape.isCollapsed===true){b.expand(p);
q=b.drawCollapseGuide(p);
m(p,q)
}else{b.collapse(p);
q=b.drawCollapseGuide(p);
m(p,q)
}});
$(q.bBox).bind("mouseout",function(r){b.remove(p.id+OG.Constants.COLLAPSE_BBOX);
b.remove(p.id+OG.Constants.COLLAPSE_SUFFIX)
})
}};
if(o&&$(o).attr("_shape")===OG.Constants.SHAPE_TYPE.GROUP){$(o).bind({mouseover:function(){n=b.drawCollapseGuide(this);
if(n&&n.bBox&&n.collapse){m(o,n)
}}})
}};
this.setMovable=function(p,o){var n=b.getContainer(),m=b.getRootGroup();
if(!p){return
}if(o){$(p).draggable({start:function(u){var r=n.scrollLeft+u.pageX,w=n.scrollTop+u.pageY,v=[],t,s=[],q;
if(b.getElementById(p.id+OG.Constants.GUIDE_SUFFIX.GUIDE)===null){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(x,y){if(OG.Util.isElement(y)&&y.id){b.removeGuide(y)
}});
b.removeAllTerminal()
}b.removeGuide(p);
q=b.drawGuide(p);
$(this).data("start",{x:r,y:w});
$(this).data("offset",{x:r-f(b.getAttr(q.bBox,"x")),y:w-f(b.getAttr(q.bBox,"y"))});
$("[id$="+OG.Constants.GUIDE_SUFFIX.BBOX+"]").each(function(x,y){if(y.id){t=b.clone(y);
b.setAttr(t,OG.Constants.DEFAULT_STYLE.GUIDE_SHADOW);
v.push({id:y.id.replace(OG.Constants.GUIDE_SUFFIX.BBOX,""),box:t})
}});
$.each(v,function(y,z){var A=b.getElementById(z.id),x;
if($(A).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){x=c(A,v);
if(x.all||x.none||(x.either&&x.attrEither)){s.push(z)
}else{b.remove(z.box);
b.removeGuide(A)
}}});
$.each(v,function(x,y){var z=b.getElementById(y.id);
if($(z).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){s.push(y)
}});
$(m).data("bBoxArray",s);
b.removeRubberBand(b.getRootElement());
b.removeAllTerminal()
},drag:function(t){var q=n.scrollLeft+t.pageX,w=n.scrollTop+t.pageY,v=$(this).data("start"),u=$(m).data("bBoxArray"),s=a(q-v.x),r=a(w-v.y);
g(q,w);
$(this).css({position:"",left:"",top:""});
$.each(u,function(x,y){b.setAttr(y.box,{transform:"t"+s+","+r})
});
b.removeAllTerminal()
},stop:function(q){var z=n.scrollLeft+q.pageX,v=n.scrollTop+q.pageY,r=$(this).data("start"),A=$(m).data("bBoxArray"),C=a(z-r.x),B=a(v-r.y),w=[],t=$(m).data("groupTarget"),u=[],s;
$(this).css({position:"",left:"",top:""});
$.each(A,function(x,y){var D=b.getElementById(y.id);
if($(D).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){w.push(y.id)
}});
$.each(A,function(y,D){var E,x;
b.remove(D.box);
E=b.getElementById(D.id);
u.push(E);
$("[id^='"+D.id+OG.Constants.GUIDE_SUFFIX.GUIDE+"_']").each(function(F,G){x=b.getElementById(G.id);
b.setAttr(x,{x:f(b.getAttr(x,"x"))+C,y:f(b.getAttr(x,"y"))+B})
});
b.move(E,[C,B],w);
b.setAttr(E,{cursor:"move"});
if($(E).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(c(E,A).none){b.disconnect(E)
}}});
if(t&&OG.Util.isElement(t)){b.addToGroup(t,u);
$.each(u,function(x,y){b.removeGuide(y)
});
s=b.drawGuide(t);
e(t,s,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.toFront(s.group);
b.remove(t.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(m).removeData("groupTarget")
}else{b.addToGroup(m,u);
$.each(u,function(x,y){b.removeGuide(y);
s=b.drawGuide(y);
e(y,s,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.toFront(s.group)
})
}$(m).removeData("bBoxArray")
}});
b.setAttr(p,{cursor:"move"})
}else{$(p).draggable("destroy");
b.setAttr(p,{cursor:OG.Constants.SELECTABLE?"pointer":OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
this.setClickSelectable=function(n,m){if(m){$(n).bind("click",function(p){var o;
if(n.shape){if(!p.shiftKey&&!p.ctrlKey){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(q,r){if(r.id){b.removeGuide(r)
}})
}if($(n).attr("_selected")==="true"){if(p.shiftKey||p.ctrlKey){b.removeGuide(n)
}}else{h(n);
if(!j(n)){o=b.drawGuide(n);
if(o){e(n,o,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.removeAllTerminal();
b.toFront(o.group)
}}}return false
}});
if(m&&OG.Constants.MOVABLE){b.setAttr(n,{cursor:"move"})
}else{b.setAttr(n,{cursor:"pointer"})
}}else{$(n).click("destroy");
b.setAttr(n,{cursor:OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
this.setDragSelectable=function(m){var n=b.getContainer(),o=b.getRootElement();
$(o).bind("click",function(q){var p=$(this).data("dragBox");
if(!p||(p&&p.width<1&&p.height<1)){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(r,s){if(OG.Util.isElement(s)&&s.id){b.removeGuide(s)
}});
b.removeAllTerminal()
}});
if(m){$(o).bind("mousedown",function(q){var p=b.getRootBBox();
$(this).data("dragBox_first",{x:n.scrollLeft+q.pageX-p.x,y:n.scrollTop+q.pageY-p.y});
b.drawRubberBand([n.scrollLeft+q.pageX-p.x,n.scrollTop+q.pageY-p.y])
});
$(o).bind("mousemove",function(t){var u=$(this).data("dragBox_first"),r=b.getRootBBox(),s,q,p,v;
if(u){s=n.scrollLeft+t.pageX-r.x-u.x;
q=n.scrollTop+t.pageY-r.y-u.y;
p=s<=0?u.x+s:u.x;
v=q<=0?u.y+q:u.y;
b.drawRubberBand([p,v],[Math.abs(s),Math.abs(q)])
}});
$(o).bind("mouseup",function(p){var s=$(this).data("dragBox_first"),w=b.getRootBBox(),q,z,v,u,t,r;
b.removeRubberBand(o);
if(s){q=n.scrollLeft+p.pageX-w.x-s.x;
z=n.scrollTop+p.pageY-w.y-s.y;
v=q<=0?s.x+q:s.x;
u=z<=0?s.y+z:s.y;
t=new OG.Envelope([v,u],Math.abs(q),Math.abs(z));
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(x,y){if(y.shape.geom&&t.isContainsAll(y.shape.geom.getVertices())){h(y);
if(!j(y)){r=b.drawGuide(y);
if(r){e(y,r,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.removeAllTerminal()
}}}});
$(this).data("dragBox",{width:q,height:z,x:v,y:u})
}});
$(o).bind("contextmenu",function(p){b.removeRubberBand(o)
})
}else{$(o).unbind("mousedown");
$(o).unbind("mousemove");
$(o).unbind("mouseup");
$(o).unbind("contextmenu")
}};
this.setEnableHotKey=function(n){var m=b.getRootGroup();
if(n){$(window.document).bind("keydown",function(r){var p,t,q,o,s;
if(r.keyCode===KeyEvent.DOM_VK_DELETE){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_shape=EDGE][_selected=true]").each(function(u,v){if(v.id){b.removeShape(v)
}});
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){if(v.id){b.removeShape(v)
}})
}if(OG.Constants.SELECTABLE&&r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_A){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(v,w){if($(w.parentNode).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){var u=b.drawGuide(w);
if(u){e(w,u,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
b.removeTerminal(w)
}}})
}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_C){t=[];
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){t.push(v)
});
$(m).data("copied",t)
}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_V){p=$(m).data("copied");
t=[];
if(p){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){if(v.id){b.removeGuide(v)
}});
$.each(p,function(u,x){var z=x.shape.geom.getBoundary(),w,y,v;
w=x.shape.clone();
if($(x).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){w.geom=new OG.PolyLine(x.shape.geom.getVertices());
w.geom.style=x.shape.geom.style;
w.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
y=b.drawShape(null,w,null,x.shapeStyle)
}else{y=b.drawShape([z.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,z.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],w,[z.getWidth(),z.getHeight()],x.shapeStyle)
}v=b.drawGuide(y);
d.setClickSelectable(y,OG.Constants.SELECTABLE);
d.setMovable(y,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
e(y,v,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(y)
}if(OG.Constants.GROUP_COLLAPSIBLE){d.enableCollapse(y)
}if($(y).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.CONNECTABLE){d.enableConnect(y)
}if(OG.Constants.LABEL_EDITABLE){d.enableEditLabel(y)
}}l(x,y);
t.push(y)
});
$(m).data("copied",t)
}}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_G){q=b.group($("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]"));
if(q){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){b.removeGuide(v)
});
o=b.drawGuide(q);
if(o){d.setClickSelectable(q,OG.Constants.SELECTABLE);
d.setMovable(q,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
e(q,o,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if(OG.Constants.GROUP_DROPABLE){d.enableDragAndDropGroup(q)
}b.removeAllTerminal();
b.toFront(o.group)
}}}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_U){s=b.ungroup($("[_shape="+OG.Constants.SHAPE_TYPE.GROUP+"][_selected=true]"));
$.each(s,function(u,v){o=b.drawGuide(v);
if(o){b.removeAllTerminal();
b.toFront(o.group)
}})
}if(r.shiftKey&&r.keyCode===KeyEvent.DOM_VK_LEFT){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){})
}if(r.shiftKey&&r.keyCode===KeyEvent.DOM_VK_RIGHT){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){})
}if(r.shiftKey&&r.keyCode===KeyEvent.DOM_VK_UP){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){})
}if(r.shiftKey&&r.keyCode===KeyEvent.DOM_VK_DOWN){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){})
}})
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
if(parentId&&_RENDERER.getElementById(parentId)){_RENDERER.appendChild(element,parentId)
}if(!this.CONFIG_INITIALIZED){this.initConfig()
}_HANDLER.setClickSelectable(element,OG.Constants.SELECTABLE);
_HANDLER.setMovable(element,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
if(OG.Constants.CONNECTABLE){_HANDLER.enableConnect(element)
}if(OG.Constants.LABEL_EDITABLE){_HANDLER.enableEditLabel(element)
}if(OG.Constants.GROUP_DROPABLE){_HANDLER.enableDragAndDropGroup(element)
}if(OG.Constants.GROUP_COLLAPSIBLE){_HANDLER.enableCollapse(element)
}return element
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
guide=_RENDERER.drawGuide(edge);
_HANDLER.setClickSelectable(edge,OG.Constants.SELECTABLE);
_HANDLER.setMovable(edge,OG.Constants.SELECTABLE&&OG.Constants.MOVABLE);
_HANDLER.setResizable(edge,guide,OG.Constants.SELECTABLE&&OG.Constants.RESIZABLE);
if($(edge).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){if(OG.Constants.LABEL_EDITABLE){_HANDLER.enableEditLabel(edge)
}}_RENDERER.toFront(guide.group);
return edge
};
this.disconnect=function(element){_RENDERER.disconnect(element)
};
this.group=function(elements){return _RENDERER.group(elements)
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
this.toJSON=function(){var jsonObj={opengraph:{cell:[]}},childShape;
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
}if(shape.label){cell["@label"]=shape.label
}if(shape.angle&&shape.angle!==0){cell["@angle"]=shape.angle
}if(shape instanceof OG.shape.ImageShape){cell["@value"]=shape.image
}else{if(shape instanceof OG.shape.HtmlShape){cell["@value"]=escape(shape.htmlString)
}else{if(shape instanceof OG.shape.TextShape){cell["@value"]=shape.text
}else{if(shape instanceof OG.shape.EdgeShape){vertices=geom.getVertices();
from=vertices[0];
to=vertices[vertices.length-1];
cell["@value"]=from+","+to
}}}}if(item.data){cell["@data"]=escape(OG.JSON.encode(item.data))
}jsonObj.opengraph.cell.push(cell);
childShape(item,false)
})
};
childShape(_RENDERER.getRootGroup(),true);
return jsonObj
};
this.loadXML=function(xml){this.loadJSON(OG.Util.xmlToJson(xml))
};
this.loadJSON=function(json){var i,cell,shape,id,parent,shapeType,shapeId,x,y,width,height,style,from,to,fromEdge,toEdge,label,angle,value,data,element;
_RENDERER.clear();
if(json&&json.opengraph&&json.opengraph.cell&&OG.Util.isArray(json.opengraph.cell)){cell=json.opengraph.cell;
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
from=cell[i]["@from"];
to=cell[i]["@to"];
fromEdge=cell[i]["@fromEdge"];
toEdge=cell[i]["@toEdge"];
label=cell[i]["@label"];
angle=cell[i]["@angle"];
value=cell[i]["@value"];
data=cell[i]["@data"];
switch(shapeType){case OG.Constants.SHAPE_TYPE.GEOM:case OG.Constants.SHAPE_TYPE.GROUP:if(label){shape=eval("new "+shapeId+"('"+label+"')")
}else{shape=eval("new "+shapeId+"()")
}element=this.drawShape([x,y],shape,[width,height],OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.EDGE:if(label){shape=eval("new "+shapeId+"("+value+", '"+label+"')")
}else{shape=eval("new "+shapeId+"("+value+")")
}element=this.drawShape(null,shape,null,OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.HTML:if(label){shape=eval("new "+shapeId+"('"+unescape(value)+"', '"+label+"')")
}else{shape=eval("new "+shapeId+"('"+unescape(value)+"')")
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.IMAGE:if(label){shape=eval("new "+shapeId+"('"+value+"', '"+label+"')")
}else{shape=eval("new "+shapeId+"('"+value+"')")
}element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent);
break;
case OG.Constants.SHAPE_TYPE.TEXT:shape=eval("new "+shapeId+"('"+value+"')");
element=this.drawShape([x,y],shape,[width,height,angle],OG.JSON.decode(style),id,parent);
break
}if(from){$(element).attr("_from",from)
}if(to){$(element).attr("_to",to)
}if(fromEdge){$(element).attr("_fromedge",fromEdge)
}if(toEdge){$(element).attr("_toedge",toEdge)
}if(data){element.data=OG.JSON.decode(unescape(data))
}}}};
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