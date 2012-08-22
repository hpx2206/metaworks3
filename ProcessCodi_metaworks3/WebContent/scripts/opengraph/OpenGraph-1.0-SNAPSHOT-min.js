var OG=window.OG||{};
OG.common={};
OG.geometry={};
OG.graph={};
OG.handler={};
OG.layout={};
OG.renderer={};
OG.shape={};
OG.common.Constants={CANVAS_BACKGROUND:"#f9f9f9",GEOM_TYPE:{NULL:0,POINT:1,LINE:2,POLYLINE:3,POLYGON:4,RECTANGLE:5,CIRCLE:6,ELLIPSE:7,CURVE:8,BEZIER_CURVE:9,COLLECTION:10},GEOM_NAME:["","Point","Line","PolyLine","Polygon","Rectangle","Circle","Ellipse","Curve","BezierCurve","Collection"],NUM_PRECISION:0,NODE_TYPE:{ROOT:"ROOT",SHAPE:"SHAPE"},SHAPE_TYPE:{GEOM:"GEOM",TEXT:"TEXT",IMAGE:"IMAGE",EDGE:"EDGE",GROUP:"GROUP"},EDGE_TYPE:{STRAIGHT:"straight",PLAIN:"plain",BEZIER:"bezier"},EDGE_PADDING:20,LABEL_PADDING:5,LABEL_EDITOR_WIDTH:70,LABEL_EDITOR_HEIGHT:16,LABEL_SUFFIX:"_LABEL",LABEL_EDITOR_SUFFIX:"_LABEL_EDITOR",DEFAULT_STYLE:{SHAPE:{cursor:"default"},GEOM:{stroke:"black",fill:"white","fill-opacity":0,"label-position":"center"},TEXT:{stroke:"none","text-anchor":"middle"},IMAGE:{"label-position":"bottom"},EDGE:{stroke:"black","stroke-width":2,"edge-type":"straight","edge-direction":"c c","arrow-start":"none","arrow-end":"block-wide-long","stroke-dasharray":"","label-position":"center"},EDGE_SHADOW:{stroke:"#00FF00","stroke-width":2,"arrow-start":"none","arrow-end":"none","stroke-dasharray":"- "},GROUP:{stroke:"none",fill:"white","fill-opacity":0,"label-position":"bottom","text-anchor":"middle","vertical-align":"top"},GUIDE_BBOX:{stroke:"#00FF00",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},GUIDE_UL:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_UR:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LL:{stroke:"black",fill:"#00FF00",cursor:"nesw-resize","shape-rendering":"crispEdges"},GUIDE_LR:{stroke:"black",fill:"#00FF00",cursor:"nwse-resize","shape-rendering":"crispEdges"},GUIDE_LC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_UC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_RC:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_LWC:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_FROM:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_TO:{stroke:"black",fill:"#00FF00",cursor:"move","shape-rendering":"crispEdges"},GUIDE_CTL_H:{stroke:"black",fill:"#00FF00",cursor:"ew-resize","shape-rendering":"crispEdges"},GUIDE_CTL_V:{stroke:"black",fill:"#00FF00",cursor:"ns-resize","shape-rendering":"crispEdges"},GUIDE_SHADOW:{stroke:"black",fill:"none","stroke-dasharray":"- ","shape-rendering":"crispEdges"},RUBBER_BAND:{stroke:"#0000FF",opacity:0.2,fill:"#0077FF"},TERMINAL:{stroke:"#808080","stroke-width":1,fill:"r(0.5, 0.5)#FFFFFF-#808080","fill-opacity":0.5,cursor:"pointer"},TERMINAL_OVER:{stroke:"#0077FF","stroke-width":4,fill:"r(0.5, 0.5)#FFFFFF-#0077FF","fill-opacity":1,cursor:"pointer"},TERMINAL_BBOX:{stroke:"none",fill:"white","fill-opacity":0},DROP_OVER_BBOX:{stroke:"#0077FF",fill:"none",opacity:0.6,"shape-rendering":"crispEdges"},LABEL:{"font-size":12,"font-color":"black"},LABEL_EDITOR:{position:"absolute",overflow:"visible",resize:"none","text-align":"center",display:"block",padding:0},COLLAPSE:{stroke:"black",fill:"white","fill-opacity":0,cursor:"pointer","shape-rendering":"crispEdges"},COLLAPSE_BBOX:{stroke:"none",fill:"white","fill-opacity":0}},RUBBER_BAND_ID:"OG_R_BAND",GUIDE_SUFFIX:{GUIDE:"_GUIDE",BBOX:"_GUIDE_BBOX",UL:"_GUIDE_UL",UR:"_GUIDE_UR",LL:"_GUIDE_LL",LR:"_GUIDE_LR",LC:"_GUIDE_LC",UC:"_GUIDE_UC",RC:"_GUIDE_RC",LWC:"_GUIDE_LWC",FROM:"_GUIDE_FROM",TO:"_GUIDE_TO",CTL:"_GUIDE_CTL_",CTL_H:"_GUIDE_CTL_H_",CTL_V:"_GUIDE_CTL_V_"},GUIDE_RECT_SIZE:8,GUIDE_MIN_SIZE:18,COLLAPSE_SUFFIX:"_COLLAPSE",COLLAPSE_BBOX_SUFFIX:"_COLLAPSE_BBOX",COLLAPSE_SIZE:10,MOVE_SNAP_SIZE:5,DROP_OVER_BBOX_SUFFIX:"_DROP_OVER",TERMINAL_SUFFIX:{GROUP:"_TERMINAL",BOX:"_TERMINAL_BOX"},TERMINAL_TYPE:{C:"C",E:"E",W:"W",S:"S",N:"N",IN:"IN",OUT:"OUT",INOUT:"INOUT"},TERMINAL_SIZE:3,COPY_PASTE_PADDING:20};
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
OG.JSON=OG.common.JSON;OG.geometry.Style=function(c){var b={stroke:"black","stroke-width":1},a={};
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
e++){this.geometries[e].move(d,f)
}return this
};
this.resize=function(n,i,g,o){var f=this.getBoundary(),j=g+o,h=n+i,e=f.getWidth()+j,p=f.getHeight()+h,m=f.getWidth()===0?1:e/f.getWidth(),l=f.getHeight()===0?1:p/f.getHeight(),d=f.getUpperLeft(),k;
if(e<0||p<0){throw new OG.ParamError()
}for(b=0;
b<this.geometries.length;
b++){k=this.geometries[b].getVertices();
for(a=0;
a<k.length;
a++){k[a].x=OG.Util.round((d.x-g)+(k[a].x-d.x)*m);
k[a].y=OG.Util.round((d.y-n)+(k[a].y-d.y)*l)
}}f.resize(n,i,g,o)
};
this.resizeBox=function(g,d){var h=this.getBoundary(),f=OG.Util.round((g-h.getWidth())/2),e=OG.Util.round((d-h.getHeight())/2);
this.resize(e,e,f,f);
return this
};
this.rotate=function(e,d){d=d||this.getCentroid();
for(b=0;
b<this.geometries.length;
b++){this.geometries[b].rotate(e,d)
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
return this.geom
};
this.clone=function(){return new OG.shape.GroupShape(this.label)
}
};
OG.shape.GroupShape.prototype=new OG.shape.IShape();
OG.shape.GroupShape.prototype.constructor=OG.shape.GroupShape;
OG.GroupShape=OG.shape.GroupShape;OG.shape.RectangleShape=function(a){this.SHAPE_ID="OG.shape.RectangleShape";
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
OG.RectangleShape=OG.shape.RectangleShape;OG.shape.SwimlaneShape=function(a){this.TYPE=OG.Constants.SHAPE_TYPE.GROUP;
this.SHAPE_ID="OG.shape.SwimlaneShape";
this.label=a;
this.createTerminal=function(){if(!this.geom){return[]
}var b=this.geom.getBoundary();
return[new OG.Terminal(b.getCentroid(),OG.Constants.TERMINAL_TYPE.C,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getRightCenter(),OG.Constants.TERMINAL_TYPE.E,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLeftCenter(),OG.Constants.TERMINAL_TYPE.W,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getLowerCenter(),OG.Constants.TERMINAL_TYPE.S,OG.Constants.TERMINAL_TYPE.INOUT),new OG.Terminal(b.getUpperCenter(),OG.Constants.TERMINAL_TYPE.N,OG.Constants.TERMINAL_TYPE.INOUT)]
};
this.createShape=function(){if(this.geom){return this.geom
}this.geom=new OG.geometry.Rectangle([0,0],100,100);
return this.geom
};
this.clone=function(){return new OG.shape.GroupShape(this.label)
}
};
OG.shape.SwimlaneShape.prototype=new OG.shape.GroupShape();
OG.shape.SwimlaneShape.prototype.constructor=OG.shape.GroupShape;
OG.SwimlaneShape=OG.shape.GroupShape;OG.renderer.IRenderer=function(){this.drawShape=function(a,b,c,d,e){throw new OG.NotImplementedException()
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
this.drawTerminal=function(a){throw new OG.NotImplementedException()
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
d=function(C,E,w){var A=0,F="",D,B,x,y,v,z={};
OG.Util.apply(z,(w instanceof OG.geometry.Style)?w.map:w||{},E.style.map);
E.style.map=z;
switch(E.TYPE){case OG.Constants.GEOM_TYPE.POINT:B=o.circle(E.coordinate.x,E.coordinate.y,0.5);
B.attr(z);
break;
case OG.Constants.GEOM_TYPE.LINE:case OG.Constants.GEOM_TYPE.POLYLINE:case OG.Constants.GEOM_TYPE.POLYGON:case OG.Constants.GEOM_TYPE.RECTANGLE:F="";
D=E.getVertices();
for(A=0;
A<D.length;
A++){if(A===0){F="M"+D[A].x+" "+D[A].y
}else{F+="L"+D[A].x+" "+D[A].y
}}B=o.path(F);
B.attr(z);
break;
case OG.Constants.GEOM_TYPE.CIRCLE:x=OG.JSON.decode(E.toString());
if(x.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.CIRCLE]){B=o.circle(x.center[0],x.center[1],x.radius)
}else{if(x.type===OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE]){if(x.angle===0){B=o.ellipse(x.center[0],x.center[1],x.radiusX,x.radiusY)
}else{F="";
D=E.getControlPoints();
F="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 0 "+D[5].x+" "+D[5].y;
F+="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 1 "+D[5].x+" "+D[5].y;
B=o.path(F)
}}}B.attr(z);
break;
case OG.Constants.GEOM_TYPE.ELLIPSE:x=OG.JSON.decode(E.toString());
if(x.angle===0){B=o.ellipse(x.center[0],x.center[1],x.radiusX,x.radiusY)
}else{F="";
D=E.getControlPoints();
F="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 0 "+D[5].x+" "+D[5].y;
F+="M"+D[1].x+" "+D[1].y+"A"+x.radiusX+" "+x.radiusY+" "+x.angle+" 1 1 "+D[5].x+" "+D[5].y;
B=o.path(F)
}B.attr(z);
break;
case OG.Constants.GEOM_TYPE.CURVE:F="";
D=E.getControlPoints();
for(A=0;
A<D.length;
A++){if(A===0){F="M"+D[A].x+" "+D[A].y
}else{if(A===1){F+="R"+D[A].x+" "+D[A].y
}else{F+=" "+D[A].x+" "+D[A].y
}}}B=o.path(F);
B.attr(z);
break;
case OG.Constants.GEOM_TYPE.COLLECTION:for(A=0;
A<E.geometries.length;
A++){d(C,E.geometries[A],w)
}break
}if(B){n(B);
C.appendChild(B.node)
}return B.node
};
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
j=function(Q,H,J,O,L,B){var A=OG.Constants.LABEL_PADDING,M=J?J[0]-A*2:null,K=J?J[1]-A*2:null,P=J?J[2]||0:0,E,w,v,D={},N,C,z,I,G,F;
OG.Util.apply(D,(O instanceof OG.geometry.Style)?O.map:O||{},OG.Constants.DEFAULT_STYLE.TEXT);
if(L===0||L){E=u(L);
if(E){f(E)
}else{E=o.group();
n(E,L)
}}else{E=o.group();
n(E,L)
}w=o.text(Q[0],Q[1],H);
w.attr(D);
C=w.getBBox();
M=M?(M>C.width?M:C.width):C.width;
K=K?(K>C.height?K:C.height):C.height;
z=OG.Util.round(Q[0]-M/2);
I=OG.Util.round(Q[1]-K/2);
N=new OG.Rectangle([z,I],M,K);
switch(D["text-anchor"]){case"start":G=N.getBoundary().getLeftCenter().x;
break;
case"end":G=N.getBoundary().getRightCenter().x;
break;
case"middle":G=N.getBoundary().getCentroid().x;
break;
default:G=N.getBoundary().getCentroid().x;
break
}switch(D["vertical-align"]){case"top":F=OG.Util.round(N.getBoundary().getUpperCenter().y+C.height/2);
break;
case"bottom":F=OG.Util.round(N.getBoundary().getLowerCenter().y-C.height/2);
break;
case"middle":F=N.getBoundary().getCentroid().y;
break;
default:F=N.getBoundary().getCentroid().y;
break
}w.attr({x:G,y:F});
w.attr({stroke:"none",fill:D["font-color"]||OG.Constants.DEFAULT_STYLE.LABEL["font-color"],"font-size":D["font-size"]||OG.Constants.DEFAULT_STYLE.LABEL["font-size"],"fill-opacity":1});
if(P){w.rotate(P)
}if(B&&H){C=w.getBBox();
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
this.drawShape=function(A,B,H,v,w){var x=H?H[0]:100,G=H?H[1]:100,y,D,F,z,C,E;
if(B instanceof OG.shape.GeomShape){D=B.createShape();
D.moveCentroid(A);
D.resizeBox(x,G);
y=this.drawGeom(D,v,w);
B.geom=y.geom
}else{if(B instanceof OG.shape.TextShape){F=B.createShape();
y=this.drawText(A,F,H,v,w);
B.text=y.text;
B.angle=y.angle;
B.geom=y.geom
}else{if(B instanceof OG.shape.ImageShape){z=B.createShape();
y=this.drawImage(A,z,H,v,w);
B.image=y.image;
B.angle=y.angle;
B.geom=y.geom
}else{if(B instanceof OG.shape.EdgeShape){D=B.createShape();
y=this.drawEdge(D,v,w);
B.geom=y.geom
}else{if(B instanceof OG.shape.GroupShape){D=B.createShape();
D.moveCentroid(A);
D.resizeBox(x,G);
y=this.drawGroup(D,v,w);
B.geom=y.geom
}}}}}y.shape=B;
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
}}return y
};
this.drawGeom=function(y,w,z){var x,v={};
OG.Util.apply(v,(w instanceof OG.geometry.Style)?w.map:w||{},OG.Constants.DEFAULT_STYLE.GEOM);
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
OG.Util.apply(y,(v instanceof OG.geometry.Style)?v.map:v||{},OG.Constants.DEFAULT_STYLE.EDGE);
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
this.drawGroup=function(B,y,C){var z,w,v={},A,x;
OG.Util.apply(v,(y instanceof OG.geometry.Style)?y.map:y||{},OG.Constants.DEFAULT_STYLE.GROUP);
if(C===0||C){z=u(C);
if(z){A=z.node.childNodes;
for(x=A.length-1;
x>=0;
x--){if($(A[x]).attr("_type")!==OG.Constants.NODE_TYPE.SHAPE){t(u(A[x].id))
}}}else{z=o.group();
n(z,C,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GROUP);
i.node.appendChild(z.node)
}}else{z=o.group();
n(z,C,OG.Constants.NODE_TYPE.SHAPE,OG.Constants.SHAPE_TYPE.GROUP);
i.node.appendChild(z.node)
}w=d(z.node,B,v);
z.node.geom=B;
z.attr(OG.Constants.DEFAULT_STYLE.SHAPE);
if(w.id!==z.node.firstChild.id){z.node.insertBefore(w,z.node.firstChild)
}if(z.node.shape){if(!z.node.shape.isCollapsed||z.node.shape.isCollapsed===false){z.node.shape.geom=B
}if(z.node.geom){if(OG.Util.isIE7()){z.node.removeAttribute("geom")
}else{delete z.node.geom
}}}return z.node
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
if(z&&z.shape.geom){switch($(z).attr("_shape")){case OG.Constants.SHAPE_TYPE.GEOM:z=this.drawGeom(z.shape.geom,z.shape.geom.style,z.id);
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
}}return z
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
this.disconnect(y);
if(OG.Util.isElement(I)){$(y).attr("_from",I.id);
x(N,"_toedge",y.id)
}if(OG.Util.isElement(v)){$(y).attr("_to",v.id);
x(F,"_fromedge",y.id)
}this.removeAllTerminal();
return y
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
}}else{w=$(A).attr("_fromedge");
v=$(A).attr("_toedge");
if(w){$.each(w.split(","),function(G,H){C=D.getElementById(H);
E=$(C).attr("_from");
if(E){F=g(E);
x(F,"_toedge",H)
}D.remove(C)
})
}if(v){$.each(v.split(","),function(G,H){z=D.getElementById(H);
B=$(z).attr("_to");
if(B){y=g(B);
x(y,"_fromedge",H)
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
this.drawTerminal=function(v){var w=u(OG.Util.isElement(v)?v.id:v),G=w?w.node.shape.createTerminal():null,A=w?w.node.shape.geom.getBoundary():null,F,D,C,E,B,H=OG.Constants.TERMINAL_SIZE,z=H*2;
if(w&&G&&G.length>0){F=u(w.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
C=u(w.id+OG.Constants.TERMINAL_SUFFIX.BOX);
if(F||C){return{bBox:C.node,terminal:F.node}
}F=o.group();
C=o.rect(A.getUpperLeft().x-z,A.getUpperLeft().y-z,A.getWidth()+z*2,A.getHeight()+z*2);
C.attr(OG.Constants.DEFAULT_STYLE.TERMINAL_BBOX);
n(C,w.id+OG.Constants.TERMINAL_SUFFIX.BOX);
$.each(G,function(x,y){E=y.position.x;
B=y.position.y;
D=o.circle(E,B,H);
D.attr(OG.Constants.DEFAULT_STYLE.TERMINAL);
D.node.terminal=y;
F.appendChild(D);
n(D,w.id+OG.Constants.TERMINAL_SUFFIX.GROUP+"_"+y.direction+"_"+y.inout+"_"+x)
});
n(F,w.id+OG.Constants.TERMINAL_SUFFIX.GROUP);
C.insertBefore(w);
F.insertAfter(w);
return{bBox:C.node,terminal:F.node}
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
this.removeShape=function(x){var v=u(OG.Util.isElement(x)?x.id:x),y=v.node.childNodes,w;
for(w=y.length-1;
w>=0;
w--){if($(y[w]).attr("_type")===OG.Constants.NODE_TYPE.SHAPE){this.removeShape(y[w])
}}this.disconnect(v.node);
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
return this.redrawShape(v.node,z)
}else{if(v){v.transform("...t"+B[0]+","+B[1]);
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
this.getRootBBox=function(){var z=o.canvas.parentNode,A=z.offsetWidth,w=z.offsetHeight,v=OG.Util.isIE7()?z.offsetLeft+z.parentNode.offsetLeft:z.offsetLeft,B=OG.Util.isIE7()?z.offsetTop+z.parentNode.offsetTop:z.offsetTop;
return{width:A,height:w,x:v,y:B,x2:v+A,y2:B+w}
};
this.isSVG=function(){return Raphael.svg
};
this.isVML=function(){return Raphael.vml
}
};
OG.renderer.RaphaelRenderer.prototype=new OG.renderer.IRenderer();
OG.renderer.RaphaelRenderer.prototype.constructor=OG.renderer.RaphaelRenderer;
OG.RaphaelRenderer=OG.renderer.RaphaelRenderer;OG.handler.EventHandler=function(i){var d=this,b=i,g,e,j,h,l,f=function(m){return parseInt(m,10)
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
g=function(p,m,o){var n=b.getRootGroup();
if(!p||!m){return
}if(o){b.setAttr(p,{cursor:"move"});
$(p).draggable({start:function(t){var q=t.pageX,v=t.pageY,u=[],s,r=[];
$(this).data("start",{x:q,y:v});
$(this).data("offset",{x:q-f(b.getAttr(m.bBox,"x")),y:v-f(b.getAttr(m.bBox,"y"))});
$("[id$="+OG.Constants.GUIDE_SUFFIX.BBOX+"]").each(function(w,x){if(x.id){s=b.clone(x);
b.setAttr(s,OG.Constants.DEFAULT_STYLE.GUIDE_SHADOW);
u.push({id:x.id.replace(OG.Constants.GUIDE_SUFFIX.BBOX,""),box:s})
}});
$.each(u,function(x,y){var z=b.getElementById(y.id),w;
if($(z).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){w=c(z,u);
if(w.all||w.none||(w.either&&w.attrEither)){r.push(y)
}else{b.remove(y.box);
b.removeGuide(z)
}}});
$.each(u,function(w,x){var y=b.getElementById(x.id);
if($(y).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){r.push(x)
}});
$(n).data("bBoxArray",r);
b.removeRubberBand(b.getRootElement());
b.removeAllTerminal()
},drag:function(t){var q=t.pageX,w=t.pageY,v=$(this).data("start"),u=$(n).data("bBoxArray"),s=a(q-v.x),r=a(w-v.y);
$(this).css({position:"",left:"",top:""});
$.each(u,function(x,y){b.setAttr(y.box,{transform:"t"+s+","+r})
});
b.removeAllTerminal()
},stop:function(q){var z=q.pageX,v=q.pageY,r=$(this).data("start"),A=$(n).data("bBoxArray"),C=a(z-r.x),B=a(v-r.y),w=[],t=$(n).data("groupTarget"),u=[],s;
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
if($(E).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(c(E,A).none){b.disconnect(E)
}}});
if(t&&OG.Util.isElement(t)){b.addToGroup(t,u);
$.each(u,function(x,y){b.removeGuide(y)
});
s=b.drawGuide(t);
g(t,s,true);
e(t,s,true);
b.toFront(s.group);
b.remove(t.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
$(n).removeData("groupTarget")
}else{b.addToGroup(n,u);
$.each(u,function(x,y){b.removeGuide(y);
s=b.drawGuide(y);
e(y,s,true);
b.toFront(s.group)
})
}$(n).removeData("bBoxArray")
}})
}else{b.setAttr(p,{cursor:OG.Constants.DEFAULT_STYLE.SHAPE.cursor})
}};
e=function(p,m,q){var o=b.getRootGroup(),n=b.getRootBBox();
if(!p||!m){return
}if(q){if($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){$(m.from).draggable({start:function(w){var u=p.shape.geom.getVertices(),r={},s=$(p).attr("_to"),x,t=[u[u.length-1].x,u[u.length-1].y],v=b.drawEdge(new OG.PolyLine(u),OG.Util.apply(r,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,p.shape.geom.style.map));
if(s){x=k(s);
b.drawTerminal(x);
t=b.getElementById(s)
}$(this).data("to_terminal",t);
$(o).data("edge",v);
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(z,y){if(y.id&&$(y).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){b.removeGuide(y)
}})
},drag:function(r){var t=$(o).data("edge"),z=$(o).data("edge_terminal"),E=$(this).data("to_terminal"),A=z?[z.terminal.position.x,z.terminal.position.y]:[r.pageX-n.x,r.pageY-n.y],w=OG.Util.isElement(E)?[E.terminal.position.x,E.terminal.position.y]:E,B=z?z.terminal.direction.toLowerCase():"c",x=OG.Util.isElement(E)?E.terminal.direction.toLowerCase():"c",C=z?k(z):null,v=OG.Util.isElement(E)?k(E):null,s,D,u,y;
$(this).css({position:"",left:"",top:""});
s=A;
D=w;
if(C&&B==="c"){u=b.intersectionEdge(t.geom.style.get("edge-type"),C,[s[0],s[1]],[D[0],D[1]],true);
A=u.position;
B=u.direction
}if(v&&x==="c"){u=b.intersectionEdge(t.geom.style.get("edge-type"),v,[s[0],s[1]],[D[0],D[1]],false);
w=u.position;
x=u.direction
}y=C&&v&&C.id===v.id;
if(y){A=w=C.shape.geom.getBoundary().getRightCenter()
}b.drawEdge(new OG.Line(A,w),OG.Util.apply(t.geom.style.map,{"edge-direction":B+" "+x}),t.id,y)
},stop:function(u){var v=$(o).data("edge_terminal")||[u.pageX-n.x,u.pageY-n.y],r=$(this).data("to_terminal"),s=OG.Util.isElement(v)?k(v):null,t=$(o).data("edge");
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(this).removeData("to_terminal");
$(o).removeData("edge");
$(o).removeData("edge_terminal");
b.remove(t);
b.removeGuide(p);
if(s){b.remove(s.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}p=b.connect(v,r,p,p.shape.geom.style);
m=b.drawGuide(p);
e(p,m,true);
b.toFront(m.group)
}});
$(m.to).draggable({start:function(w){var t=p.shape.geom.getVertices(),s={},r=$(p).attr("_from"),u,x=[t[0].x,t[0].y],v=b.drawEdge(new OG.PolyLine(t),OG.Util.apply(s,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW,p.shape.geom.style.map));
if(r){u=k(r);
b.drawTerminal(u);
x=b.getElementById(r)
}$(this).data("from_terminal",x);
$(o).data("edge",v);
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(z,y){if(y.id&&$(y).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE){b.removeGuide(y)
}})
},drag:function(r){var t=$(o).data("edge"),z=$(this).data("from_terminal"),E=$(o).data("edge_terminal"),A=OG.Util.isElement(z)?[z.terminal.position.x,z.terminal.position.y]:z,w=E?[E.terminal.position.x,E.terminal.position.y]:[r.pageX-n.x,r.pageY-n.y],B=OG.Util.isElement(z)?z.terminal.direction.toLowerCase():"c",x=E?E.terminal.direction.toLowerCase():"c",C=OG.Util.isElement(z)?k(z):null,v=E?k(E):null,s,D,u,y;
$(this).css({position:"",left:"",top:""});
s=A;
D=w;
if(C&&B==="c"){u=b.intersectionEdge(t.geom.style.get("edge-type"),C,[s[0],s[1]],[D[0],D[1]],true);
A=u.position;
B=u.direction
}if(v&&x==="c"){u=b.intersectionEdge(t.geom.style.get("edge-type"),v,[s[0],s[1]],[D[0],D[1]],false);
w=u.position;
x=u.direction
}y=(C!==null)&&(v!==null)&&C.id===v.id;
if(y){A=w=v.shape.geom.getBoundary().getRightCenter()
}b.drawEdge(new OG.Line(A,w),OG.Util.apply(t.geom.style.map,{"edge-direction":B+" "+x}),t.id,y)
},stop:function(t){var v=$(this).data("from_terminal"),r=$(o).data("edge_terminal")||[t.pageX-n.x,t.pageY-n.y],u=OG.Util.isElement(r)?k(r):null,s=$(o).data("edge");
$(this).css({position:"absolute",left:"0px",top:"0px"});
$(this).removeData("from_terminal");
$(o).removeData("edge");
$(o).removeData("edge_terminal");
b.remove(s);
b.removeGuide(p);
if(u){b.remove(u.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}p=b.connect(v,r,p,p.shape.geom.style);
m=b.drawGuide(p);
e(p,m,true);
b.toFront(m.group)
}});
$.each(m.controls,function(r,s){$(s).draggable({start:function(u){var t=u.pageX,v=u.pageY;
$(this).data("start",{x:t,y:v});
$(this).data("offset",{x:t-f(b.getAttr(s,"x")),y:v-f(b.getAttr(s,"y"))});
b.remove(m.bBox);
b.removeRubberBand(b.getRootElement())
},drag:function(u){var D=u.pageX,B=u.pageY,v=$(this).data("start"),w=$(this).data("offset"),t=D-w.x,E=B-w.y,A=p.shape.geom.getVertices(),C=s.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,z=C?parseInt(s.id.replace(p.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(s.id.replace(p.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"",left:"",top:""});
if(C){A[z].x=t;
A[z+1].x=t
}else{A[z].y=E;
A[z+1].y=E
}p=b.drawEdge(new OG.PolyLine(A),p.shape.geom.style,p.id);
b.drawGuide(p);
b.removeAllTerminal();
b.drawLabel(p)
},stop:function(u){var D=u.pageX,B=u.pageY,v=$(this).data("start"),w=$(this).data("offset"),t=D-w.x,E=B-w.y,A=p.shape.geom.getVertices(),C=s.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H)>=0,z=C?parseInt(s.id.replace(p.id+OG.Constants.GUIDE_SUFFIX.CTL_H,""),10):parseInt(s.id.replace(p.id+OG.Constants.GUIDE_SUFFIX.CTL_V,""),10);
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(C){A[z].x=t;
A[z+1].x=t
}else{A[z].y=E;
A[z+1].y=E
}p=b.drawEdge(new OG.PolyLine(A),p.shape.geom.style,p.id);
b.drawGuide(p);
b.drawLabel(p)
}})
})
}else{$(m.rc).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.rc,"x")),y:t-f(b.getAttr(m.rc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(u){var r=u.pageX,A=u.pageY,z=$(this).data("start"),w=$(this).data("offset"),s=r-z.x,v=r-w.x,t=v-f(b.getAttr(m.lc,"x"));
$(this).css({position:"",left:"",top:""});
if(t>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.rc,{x:v});
b.setAttr(m.ur,{x:v});
b.setAttr(m.lr,{x:v});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+v)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+v)/2)});
b.setAttr(m.bBox,{width:t})
}b.removeAllTerminal()
},stop:function(t){var r=t.pageX,v=t.pageY,u=$(this).data("start"),s=r-u.x;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getWidth()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getWidth()
}b.resize(p,[0,0,0,s]);
b.drawGuide(p)
}}});
$(m.lwc).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.lwc,"x")),y:t-f(b.getAttr(m.lwc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var r=t.pageX,z=t.pageY,w=$(this).data("start"),v=$(this).data("offset"),u=z-v.y,s=u-f(b.getAttr(m.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lwc,{y:u});
b.setAttr(m.ll,{y:u});
b.setAttr(m.lr,{y:u});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+u)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+u)/2)});
b.setAttr(m.bBox,{height:s})
}b.removeAllTerminal()
},stop:function(t){var r=t.pageX,v=t.pageY,u=$(this).data("start"),s=v-u.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getHeight()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getHeight()
}b.resize(p,[0,s,0,0]);
b.drawGuide(p)
}}});
$(m.lr).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.lr,"x")),y:t-f(b.getAttr(m.lr,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var A=t.pageX,z=t.pageY,u=$(this).data("start"),v=$(this).data("offset"),s=A-v.x,w=s-f(b.getAttr(m.lc,"x")),B=z-v.y,r=B-f(b.getAttr(m.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(w>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.rc,{x:s});
b.setAttr(m.ur,{x:s});
b.setAttr(m.lr,{x:s});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+s)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+s)/2)});
b.setAttr(m.bBox,{width:w})
}if(r>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lwc,{y:B});
b.setAttr(m.ll,{y:B});
b.setAttr(m.lr,{y:B});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+B)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+B)/2)});
b.setAttr(m.bBox,{height:r})
}b.removeAllTerminal()
},stop:function(u){var r=u.pageX,w=u.pageY,v=$(this).data("start"),t=r-v.x,s=w-v.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getWidth()
}if(p.shape.geom.getBoundary().getHeight()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getHeight()
}b.resize(p,[0,s,0,t]);
b.drawGuide(p)
}b.removeAllTerminal()
}});
$(m.lc).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.lc,"x")),y:t-f(b.getAttr(m.lc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var r=t.pageX,z=t.pageY,w=$(this).data("start"),v=$(this).data("offset"),u=r-v.x,s=f(b.getAttr(m.rc,"x"))-u;
$(this).css({position:"",left:"",top:""});
if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lc,{x:u});
b.setAttr(m.ul,{x:u});
b.setAttr(m.ll,{x:u});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+u)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+u)/2)});
b.setAttr(m.bBox,{x:OG.Util.round(u+f(b.getAttr(m.lc,"width"))/2),width:s})
}b.removeAllTerminal()
},stop:function(t){var r=t.pageX,v=t.pageY,u=$(this).data("start"),s=u.x-r;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getWidth()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getWidth()
}b.resize(p,[0,0,s,0]);
b.drawGuide(p)
}}});
$(m.ll).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.ll,"x")),y:t-f(b.getAttr(m.ll,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var A=t.pageX,z=t.pageY,u=$(this).data("start"),v=$(this).data("offset"),s=A-v.x,B=z-v.y,w=f(b.getAttr(m.rc,"x"))-s,r=B-f(b.getAttr(m.uc,"y"));
$(this).css({position:"",left:"",top:""});
if(w>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lc,{x:s});
b.setAttr(m.ul,{x:s});
b.setAttr(m.ll,{x:s});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+s)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+s)/2)});
b.setAttr(m.bBox,{x:OG.Util.round(s+f(b.getAttr(m.lc,"width"))/2),width:w})
}if(r>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lwc,{y:B});
b.setAttr(m.ll,{y:B});
b.setAttr(m.lr,{y:B});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+B)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.uc,"y"))+B)/2)});
b.setAttr(m.bBox,{height:r})
}b.removeAllTerminal()
},stop:function(u){var r=u.pageX,w=u.pageY,v=$(this).data("start"),t=v.x-r,s=w-v.y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getWidth()
}if(p.shape.geom.getBoundary().getHeight()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getHeight()
}b.resize(p,[0,s,t,0]);
b.drawGuide(p)
}}});
$(m.uc).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.uc,"x")),y:t-f(b.getAttr(m.uc,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var r=t.pageX,z=t.pageY,w=$(this).data("start"),v=$(this).data("offset"),u=z-v.y,s=f(b.getAttr(m.lwc,"y"))-u;
$(this).css({position:"",left:"",top:""});
if(s>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.uc,{y:u});
b.setAttr(m.ul,{y:u});
b.setAttr(m.ur,{y:u});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+u)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+u)/2)});
b.setAttr(m.bBox,{y:OG.Util.round(u+f(b.getAttr(m.uc,"width"))/2),height:s})
}b.removeAllTerminal()
},stop:function(t){var r=t.pageX,v=t.pageY,u=$(this).data("start"),s=u.y-v;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getHeight()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getHeight()
}b.resize(p,[s,0,0,0]);
b.drawGuide(p)
}}});
$(m.ul).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.ul,"x")),y:t-f(b.getAttr(m.ul,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var A=t.pageX,z=t.pageY,u=$(this).data("start"),v=$(this).data("offset"),s=A-v.x,B=z-v.y,w=f(b.getAttr(m.rc,"x"))-s,r=f(b.getAttr(m.lwc,"y"))-B;
$(this).css({position:"",left:"",top:""});
if(w>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.lc,{x:s});
b.setAttr(m.ul,{x:s});
b.setAttr(m.ll,{x:s});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+s)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.rc,"x"))+s)/2)});
b.setAttr(m.bBox,{x:OG.Util.round(s+f(b.getAttr(m.lc,"width"))/2),width:w})
}if(r>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.uc,{y:B});
b.setAttr(m.ul,{y:B});
b.setAttr(m.ur,{y:B});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+B)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+B)/2)});
b.setAttr(m.bBox,{y:OG.Util.round(B+f(b.getAttr(m.uc,"height"))/2),height:r})
}b.removeAllTerminal()
},stop:function(u){var r=u.pageX,w=u.pageY,v=$(this).data("start"),t=v.x-r,s=v.y-w;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getWidth()
}if(p.shape.geom.getBoundary().getHeight()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getHeight()
}b.resize(p,[s,0,t,0]);
b.drawGuide(p)
}}});
$(m.ur).draggable({start:function(s){var r=s.pageX,t=s.pageY;
$(this).data("start",{x:r,y:t});
$(this).data("offset",{x:r-f(b.getAttr(m.ur,"x")),y:t-f(b.getAttr(m.ur,"y"))});
b.removeRubberBand(b.getRootElement())
},drag:function(t){var A=t.pageX,z=t.pageY,u=$(this).data("start"),v=$(this).data("offset"),s=A-v.x,B=z-v.y,w=s-f(b.getAttr(m.lc,"x")),r=f(b.getAttr(m.lwc,"y"))-B;
$(this).css({position:"",left:"",top:""});
if(w>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.rc,{x:s});
b.setAttr(m.ur,{x:s});
b.setAttr(m.lr,{x:s});
b.setAttr(m.uc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+s)/2)});
b.setAttr(m.lwc,{x:OG.Util.round((f(b.getAttr(m.lc,"x"))+s)/2)});
b.setAttr(m.bBox,{width:w})
}if(r>=OG.Constants.GUIDE_MIN_SIZE){b.setAttr(m.uc,{y:B});
b.setAttr(m.ul,{y:B});
b.setAttr(m.ur,{y:B});
b.setAttr(m.lc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+B)/2)});
b.setAttr(m.rc,{y:OG.Util.round((f(b.getAttr(m.lwc,"y"))+B)/2)});
b.setAttr(m.bBox,{y:OG.Util.round(B+f(b.getAttr(m.uc,"width"))/2),height:r})
}b.removeAllTerminal()
},stop:function(u){var r=u.pageX,w=u.pageY,v=$(this).data("start"),t=r-v.x,s=v.y-w;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(p&&p.shape.geom){if(p.shape.geom.getBoundary().getWidth()+t<OG.Constants.GUIDE_MIN_SIZE){t=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getWidth()
}if(p.shape.geom.getBoundary().getHeight()+s<OG.Constants.GUIDE_MIN_SIZE){s=OG.Constants.GUIDE_MIN_SIZE-p.shape.geom.getBoundary().getHeight()
}b.resize(p,[s,0,0,t]);
b.drawGuide(p)
}}})
}}else{if($(p).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){b.setAttr(m.from,{cursor:"default"});
b.setAttr(m.to,{cursor:"default"});
$.each(m.controls,function(r,s){b.setAttr(s,{cursor:"default"})
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
r.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
t=b.drawShape(null,r,null,s.shape.geom.style)
}else{t=b.drawShape([u.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,u.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],r,[u.getWidth(),u.getHeight()],s.shape.geom.style)
}o.appendChild(t);
d.enableClickSelect(t,true,true);
if($(t).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){d.enableEditLabel(t);
d.enableConnect(t)
}if(s.childNodes.length>0){l(s,t)
}}})
};
this.enableEditLabel=function(m){var n=b.getRootElement();
$(m).bind({dblclick:function(){var v=m.shape.geom.getBoundary(),q=v.getUpperLeft(),y=b.getRootBBox(),A,u=q.x,x=q.y,s=v.getWidth(),z=v.getHeight(),t=m.id+OG.Constants.LABEL_EDITOR_SUFFIX,p,r="center",w=function(F){var D,B,G=0,E,C;
D=F.shape.geom.getVertices();
B=F.shape.geom.getLength();
for(E=0;
E<D.length-1;
E++){G+=D[E].distance(D[E+1]);
if(G>B/2){C=F.shape.geom.intersectCircleToLine(D[E+1],G-B/2,D[E+1],D[E]);
break
}}return C[0]
},o;
$(n.parentNode).append("<textarea id='"+m.id+OG.Constants.LABEL_EDITOR_SUFFIX+"'></textarea>");
p=$("#"+t);
switch(m.shape.geom.style.get("text-anchor")){case"start":r="left";
break;
case"middle":r="center";
break;
case"end":r="right";
break;
default:r="center";
break
}if($(m).attr("_shape")===OG.Constants.SHAPE_TYPE.TEXT){$(p).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:u,top:x,width:s,height:z,"text-align":r,overflow:"hidden",resize:"none"}));
$(p).focus();
$(p).val(m.shape.text);
$(p).bind({focusout:function(){m.shape.text=this.value;
if(m.shape.text){b.redrawShape(m);
this.parentNode.removeChild(this)
}else{b.removeShape(m);
this.parentNode.removeChild(this)
}}})
}else{if($(m).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){if(m.shape.label&&b.isSVG()){$(m).find("text").each(function(B,C){A=b.getBBox(C);
u=A.x-10;
x=A.y;
s=A.width+20;
z=A.height
})
}else{o=w(m);
u=o.x-OG.Constants.LABEL_EDITOR_WIDTH/2;
x=o.y-OG.Constants.LABEL_EDITOR_HEIGHT/2;
s=OG.Constants.LABEL_EDITOR_WIDTH;
z=OG.Constants.LABEL_EDITOR_HEIGHT
}$(p).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:u,top:x,width:s,height:z,overflow:"hidden",resize:"none"}));
$(p).focus();
$(p).val(m.shape.label);
$(p).bind({focusout:function(){b.drawLabel(m,this.value);
this.parentNode.removeChild(this)
}})
}else{$(p).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR,{left:u,top:x,width:s,height:z,"text-align":r,overflow:"hidden",resize:"none"}));
$(p).focus();
$(p).val(m.shape.label);
$(p).bind({focusout:function(){b.drawLabel(m,this.value);
this.parentNode.removeChild(this)
}})
}}}})
};
this.enableConnect=function(o){var p,n=b.getRootGroup(),m=b.getRootBBox();
if(o&&$(o).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){$(o).bind({mouseover:function(){p=b.drawTerminal(o);
if(p){if($(n).data("edge")){$.each(p.terminal.childNodes,function(q,r){if(r.terminal&&r.terminal.direction.toLowerCase()==="c"){b.drawDropOverGuide(o);
$(n).data("edge_terminal",r);
return false
}})
}$(p.bBox).bind({mouseout:function(){if(!$(n).data("edge")){b.removeTerminal(o)
}}});
$.each(p.terminal.childNodes,function(q,r){if(r.terminal){$(r).bind({mouseover:function(s){b.setAttr(r,OG.Constants.DEFAULT_STYLE.TERMINAL_OVER);
$(n).data("edge_terminal",r)
},mouseout:function(){b.setAttr(r,OG.Constants.DEFAULT_STYLE.TERMINAL);
$(n).removeData("edge_terminal")
}});
$(r).draggable({start:function(u){var s=r.terminal.position.x,v=r.terminal.position.y,t=b.drawShape(null,new OG.EdgeShape([s,v],[s,v]),null,OG.Constants.DEFAULT_STYLE.EDGE_SHADOW);
$(n).data("edge",t);
$(n).data("from_terminal",r);
b.removeRubberBand(b.getRootElement());
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(x,w){if(w.id){b.removeGuide(w)
}})
},drag:function(s){var u=$(n).data("edge"),A=$(n).data("from_terminal"),E=$(n).data("edge_terminal"),B=[A.terminal.position.x,A.terminal.position.y],x=E?[E.terminal.position.x,E.terminal.position.y]:[s.pageX-m.x,s.pageY-m.y],C=A.terminal.direction.toLowerCase(),y=E?E.terminal.direction.toLowerCase():"c",w=E?k(E):null,t,D,v,z;
$(this).css({position:"",left:"",top:""});
t=B;
D=x;
if(!o.shape.geom.getBoundary().isContains(x)&&C==="c"){v=b.intersectionEdge(u.shape.geom.style.get("edge-type"),o,[t[0],t[1]],[D[0],D[1]],true);
B=v.position;
C=v.direction
}if(w&&y==="c"){v=b.intersectionEdge(u.shape.geom.style.get("edge-type"),w,[t[0],t[1]],[D[0],D[1]],false);
x=v.position;
y=v.direction
}z=o&&w&&o.id===w.id;
if(z){B=x=o.shape.geom.getBoundary().getRightCenter()
}b.drawEdge(new OG.Line(B,x),OG.Util.apply(u.shape.geom.style.map,{"edge-direction":C+" "+y}),u.id,z)
},stop:function(s){var C={x:s.pageX-m.x,y:s.pageY-m.y},v=$(n).data("edge"),A=$(n).data("from_terminal"),D=$(n).data("edge_terminal")||[C.x,C.y],w=OG.Util.isElement(D)?k(D):null,u,x,B,z,t,y;
$(this).css({position:"absolute",left:"0px",top:"0px"});
if(!$(n).data("edge_terminal")){u=o.shape.geom.getBoundary();
x=b.drawShape([C.x,C.y],o.shape.clone(),[u.getWidth(),u.getHeight()],o.shape.geom.style);
d.enableClickSelect(x,true,true);
d.enableConnect(x);
d.enableEditLabel(x);
B=b.drawTerminal(x);
z=B.terminal.childNodes;
D=z[0];
for(y=0;
y<z.length;
y++){if(z[y].terminal&&z[y].terminal.direction.toLowerCase()==="c"){D=z[y];
break
}}}v=b.connect(A,D,v);
t=b.drawGuide(v);
d.enableClickSelect(v,true,true);
d.enableEditLabel(v);
b.toFront(t.group);
$(n).removeData("edge");
$(n).removeData("from_terminal");
$(n).removeData("edge_terminal");
if(w){b.remove(w.id+OG.Constants.DROP_OVER_BBOX_SUFFIX)
}}})
}})
}},mouseout:function(q){if($(o).attr("_shape")!==OG.Constants.SHAPE_TYPE.EDGE&&$(n).data("edge")){b.remove(o.id+OG.Constants.DROP_OVER_BBOX_SUFFIX);
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
this.enableClickSelect=function(o,n,p){var m=b.getRootGroup();
OG.Constants.DEFAULT_STYLE.SHAPE.cursor=n?"move":"pointer";
b.setAttr(o,{cursor:n?"move":"pointer"});
$(o).bind("click",function(r){var q;
if(o.shape){if(!r.shiftKey&&!r.ctrlKey){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(s,t){if(t.id){b.removeGuide(t);
$(t).draggable("destroy")
}})
}if($(o).attr("_selected")==="true"){if(r.shiftKey||r.ctrlKey){b.removeGuide(o);
$(o).draggable("destroy")
}}else{h(o);
if(!j(o)){q=b.drawGuide(o);
if(q){g(o,q,n);
e(o,q,p);
b.removeAllTerminal();
b.toFront(q.group)
}}}return false
}})
};
this.setEnableDragSelect=function(p,n,q){var o=b.getRootElement(),m=b.getRootBBox();
$(o).bind("click",function(s){var r=$(this).data("dragBox");
if(r&&r.width<1&&r.height<1){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(t,u){if(OG.Util.isElement(u)&&u.id){b.removeGuide(u);
$(u).draggable("destroy")
}});
b.removeAllTerminal()
}});
if(p){$(o).bind("mousedown",function(r){$(this).data("dragBox_first",{x:r.pageX-m.x,y:r.pageY-m.y});
b.drawRubberBand([r.pageX-m.x,r.pageY-m.y])
});
$(o).bind("mousemove",function(u){var v=$(this).data("dragBox_first"),t,s,r,w;
if(v){t=u.pageX-m.x-v.x;
s=u.pageY-m.y-v.y;
r=t<=0?v.x+t:v.x;
w=s<=0?v.y+s:v.y;
b.drawRubberBand([r,w],[Math.abs(t),Math.abs(s)])
}});
$(o).bind("mouseup",function(v){var z=$(this).data("dragBox_first"),u,t,s,A,w,r;
b.removeRubberBand(o);
if(z){u=v.pageX-m.x-z.x;
t=v.pageY-m.y-z.y;
s=u<=0?z.x+u:z.x;
A=t<=0?z.y+t:z.y;
w=new OG.Envelope([s,A],Math.abs(u),Math.abs(t));
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(x,y){if(y.shape.geom&&w.isContainsAll(y.shape.geom.getVertices())){h(y);
if(!j(y)){r=b.drawGuide(y);
if(r){g(y,r,n);
e(y,r,q);
b.removeAllTerminal()
}}}});
$(this).data("dragBox",{width:u,height:t,x:s,y:A})
}});
$(o).bind("contextmenu",function(r){b.removeRubberBand(o)
})
}else{$(o).unbind("mousedown");
$(o).unbind("mousemove");
$(o).unbind("mouseup");
$(o).unbind("contextmenu")
}};
this.setEnableHotKey=function(n){var m=b.getRootGroup();
if(n){$(window.document).bind("keydown",function(r){var p,t,q,o,s;
if(r.keyCode===KeyEvent.DOM_VK_DELETE){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){if(v.id){b.removeShape(v)
}})
}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_A){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"]").each(function(v,w){if($(w.parentNode).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){var u=b.drawGuide(w);
if(u){g(w,u,true);
e(w,u,true);
b.removeTerminal(w)
}}})
}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_C){t=[];
$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){t.push(v)
});
$(m).data("copied",t)
}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_V){p=$(m).data("copied");
t=[];
if(p){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){if(v.id){b.removeGuide(v);
$(v).draggable("destroy")
}});
$.each(p,function(u,x){var z=x.shape.geom.getBoundary(),w,y,v;
w=x.shape.clone();
if($(x).attr("_shape")===OG.Constants.SHAPE_TYPE.EDGE){w.geom=new OG.PolyLine(x.shape.geom.getVertices());
w.geom.move(OG.Constants.COPY_PASTE_PADDING,OG.Constants.COPY_PASTE_PADDING);
y=b.drawShape(null,w,null,x.shape.geom.style)
}else{y=b.drawShape([z.getCentroid().x+OG.Constants.COPY_PASTE_PADDING,z.getCentroid().y+OG.Constants.COPY_PASTE_PADDING],w,[z.getWidth(),z.getHeight()],x.shape.geom.style)
}v=b.drawGuide(y);
g(y,v,true);
e(y,v,true);
d.enableClickSelect(y,true,true);
d.enableDragAndDropGroup(y);
d.enableCollapse(y);
if($(y).attr("_shape")!==OG.Constants.SHAPE_TYPE.GROUP){d.enableEditLabel(y);
d.enableConnect(y)
}l(x,y);
t.push(y)
});
$(m).data("copied",t)
}}if(r.ctrlKey&&r.keyCode===KeyEvent.DOM_VK_G){q=b.group($("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]"));
if(q){$("[_type="+OG.Constants.NODE_TYPE.SHAPE+"][_selected=true]").each(function(u,v){b.removeGuide(v);
$(v).draggable("destroy")
});
o=b.drawGuide(q);
if(o){d.enableClickSelect(q,true,true);
g(q,o,true);
e(q,o,true);
d.enableDragAndDropGroup(q);
b.removeAllTerminal();
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
OG.EventHandler=OG.handler.EventHandler;OG.graph.Canvas=function(container){var _RENDERER=container?new OG.RaphaelRenderer(container,[this.width,this.height]):null,_HANDLER=new OG.EventHandler(_RENDERER),_CONTAINER=OG.Util.isElement(container)?container:document.getElementById(container);
this.RENDERER=_RENDERER;
this.CONTAINER=_CONTAINER;
this.initConfig=function(config){this.selectable=config?(config.selectable===undefined?true:config.selectable):true;
this.enableHotKey=config?(config.enableHotKey===undefined?true:config.enableHotKey):true;
this.connectable=config?(config.connectable===undefined?true:config.connectable):true;
this.labelEditable=config?(config.labelEditable===undefined?true:config.labelEditable):true;
this.groupDropable=config?(config.groupDropable===undefined?true:config.groupDropable):true;
this.collapsible=config?(config.collapsible===undefined?true:config.collapsible):true;
if(this.selectable){_HANDLER.setEnableDragSelect(true,true,true)
}if(this.enableHotKey){_HANDLER.setEnableHotKey(true)
}this.CONFIG_INITIALIZED=true
};
this.drawShape=function(position,shape,size,style,id,parentId){var element=_RENDERER.drawShape(position,shape,size,style,id);
if(parentId&&_RENDERER.getElementById(parentId)){_RENDERER.appendChild(element,parentId)
}if(!this.CONFIG_INITIALIZED){this.initConfig()
}if(this.selectable){_HANDLER.enableClickSelect(element,true,true)
}if(this.connectable){_HANDLER.enableConnect(element)
}if(this.labelEditable){_HANDLER.enableEditLabel(element)
}if(this.groupDropable){_HANDLER.enableDragAndDropGroup(element)
}if(this.collapsible){_HANDLER.enableCollapse(element)
}return element
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
childShape=function(node,isRoot){$(node).children("[_type=SHAPE]").each(function(idx,item){var shape=item.shape,geom=shape.geom,envelope=geom.getBoundary(),cell={},vertices,from,to;
cell["@id"]=$(item).attr("id");
if(!isRoot){cell["@parent"]=$(node).attr("id")
}cell["@shapeType"]=shape.TYPE;
cell["@shapeId"]=shape.SHAPE_ID;
cell["@x"]=envelope.getCentroid().x;
cell["@y"]=envelope.getCentroid().y;
cell["@width"]=envelope.getWidth();
cell["@height"]=envelope.getHeight();
cell["@style"]=geom.style.toString();
if($(item).attr("_from")){cell["@from"]=$(item).attr("_from")
}if($(item).attr("_to")){cell["@to"]=$(item).attr("_to")
}if($(item).attr("_fromedge")){cell["@fromEdge"]=$(item).attr("_fromedge")
}if($(item).attr("_toedge")){cell["@toEdge"]=$(item).attr("_toedge")
}if(shape.label){cell["@label"]=shape.label
}if(shape.angle&&shape.angle!==0){cell["@angle"]=shape.angle
}if(shape instanceof OG.shape.ImageShape){cell["@value"]=shape.image
}else{if(shape instanceof OG.shape.TextShape){cell["@value"]=shape.text
}else{if(shape instanceof OG.shape.EdgeShape){vertices=geom.getVertices();
from=vertices[0];
to=vertices[vertices.length-1];
cell["@value"]=from+","+to
}}}if(item.data){cell["@data"]=escape(OG.JSON.encode(item.data))
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
if(json.opengraph&&json.opengraph.cell&&OG.Util.isArray(json.opengraph.cell)){cell=json.opengraph.cell;
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
style=cell[i]["@style"];
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
}}}}
};
OG.graph.Canvas.prototype=new OG.graph.Canvas();
OG.graph.Canvas.prototype.constructor=OG.graph.Canvas;
OG.Canvas=OG.graph.Canvas;