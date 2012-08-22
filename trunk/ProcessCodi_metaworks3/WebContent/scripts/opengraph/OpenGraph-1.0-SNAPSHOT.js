/** @namespace */
var OG = window.OG || {};

/** @namespace */
OG.common = {};

/** @namespace */
OG.geometry = {};

/** @namespace */
OG.graph = {};

/** @namespace */
OG.handler = {};

/** @namespace */
OG.layout = {};

/** @namespace */
OG.renderer = {};

/** @namespace */
OG.shape = {};

/**
 * 공통 상수 정의 Javascript 클래스
 *
 * @class
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.Constants = {

	/**
	 * 캔버스 배경색
	 */
	CANVAS_BACKGROUND: "#f9f9f9",

	/**
	 * 공간 기하 객체 타입 정의
	 */
	GEOM_TYPE: {
		NULL        : 0,
		POINT       : 1,
		LINE        : 2,
		POLYLINE    : 3,
		POLYGON     : 4,
		RECTANGLE   : 5,
		CIRCLE      : 6,
		ELLIPSE     : 7,
		CURVE       : 8,
		BEZIER_CURVE: 9,
		COLLECTION  : 10
	},

	/**
	 * 공간 기하 객체 타입-이름 매핑
	 */
	GEOM_NAME: ["", "Point", "Line", "PolyLine", "Polygon", "Rectangle", "Circle", "Ellipse", "Curve", "BezierCurve", "Collection"],

	/**
	 * 숫자 반올림 소숫점 자리수
	 */
	NUM_PRECISION: 0,

	/**
	 * 캔버스 노드 타입 정의
	 */
	NODE_TYPE: {
		ROOT : "ROOT",
		SHAPE: "SHAPE"
	},

	/**
	 * Shape 타입 정의
	 */
	SHAPE_TYPE: {
		GEOM : "GEOM",
		TEXT : "TEXT",
		IMAGE: "IMAGE",
		EDGE : "EDGE",
		GROUP: "GROUP"
	},

	/**
	 * Edge 타입 정의
	 */
	EDGE_TYPE: {
		STRAIGHT: "straight",
		PLAIN   : "plain",
		BEZIER  : "bezier"
	},

	/**
	 * Edge 꺽은선 패딩 사이즈
	 */
	EDGE_PADDING: 20,

	/**
	 * 라벨의 패딩 사이즈
	 */
	LABEL_PADDING: 5,

	/**
	 * 라벨 에디터(textarea)의 디폴트 width
	 */
	LABEL_EDITOR_WIDTH: 70,

	/**
	 * 라벨 에디터(textarea)의 디폴트 height
	 */
	LABEL_EDITOR_HEIGHT: 16,

	/**
	 * 라벨 ID의 suffix 정의
	 */
	LABEL_SUFFIX: "_LABEL",

	/**
	 * 라벨 에디터 ID의 suffix 정의
	 */
	LABEL_EDITOR_SUFFIX: "_LABEL_EDITOR",

	/**
	 * 디폴트 스타일 정의
	 */
	DEFAULT_STYLE: {
		SHAPE         : { cursor: "default" },
		GEOM          : { stroke: "black", fill: "white", "fill-opacity": 0, "label-position": "center" },
		TEXT          : { stroke: "none", "text-anchor": "middle" },
		IMAGE         : { "label-position": "bottom" },
		EDGE          : { stroke: "black", "stroke-width": 2, "edge-type": "straight", "edge-direction": "c c", "arrow-start": "none", "arrow-end": "block-wide-long", "stroke-dasharray": "", "label-position": "center" },
		EDGE_SHADOW   : { stroke: "#00FF00", "stroke-width": 2, "arrow-start": "none", "arrow-end": "none", "stroke-dasharray": "- " },
		GROUP         : { stroke: "none", fill: "white", "fill-opacity": 0, "label-position": "bottom", "text-anchor": "middle", "vertical-align": "top" },
		GUIDE_BBOX    : { stroke: "#00FF00", fill: "none", "stroke-dasharray": "- ", "shape-rendering": "crispEdges" },
		GUIDE_UL      : { stroke: "black", fill: "#00FF00", cursor: "nwse-resize", "shape-rendering": "crispEdges" },
		GUIDE_UR      : { stroke: "black", fill: "#00FF00", cursor: "nesw-resize", "shape-rendering": "crispEdges" },
		GUIDE_LL      : { stroke: "black", fill: "#00FF00", cursor: "nesw-resize", "shape-rendering": "crispEdges" },
		GUIDE_LR      : { stroke: "black", fill: "#00FF00", cursor: "nwse-resize", "shape-rendering": "crispEdges" },
		GUIDE_LC      : { stroke: "black", fill: "#00FF00", cursor: "ew-resize", "shape-rendering": "crispEdges" },
		GUIDE_UC      : { stroke: "black", fill: "#00FF00", cursor: "ns-resize", "shape-rendering": "crispEdges" },
		GUIDE_RC      : { stroke: "black", fill: "#00FF00", cursor: "ew-resize", "shape-rendering": "crispEdges" },
		GUIDE_LWC     : { stroke: "black", fill: "#00FF00", cursor: "ns-resize", "shape-rendering": "crispEdges" },
		GUIDE_FROM    : { stroke: "black", fill: "#00FF00", cursor: "move", "shape-rendering": "crispEdges" },
		GUIDE_TO      : { stroke: "black", fill: "#00FF00", cursor: "move", "shape-rendering": "crispEdges" },
		GUIDE_CTL_H   : { stroke: "black", fill: "#00FF00", cursor: "ew-resize", "shape-rendering": "crispEdges" },
		GUIDE_CTL_V   : { stroke: "black", fill: "#00FF00", cursor: "ns-resize", "shape-rendering": "crispEdges" },
		GUIDE_SHADOW  : { stroke: "black", fill: "none", "stroke-dasharray": "- ", "shape-rendering": "crispEdges" },
		RUBBER_BAND   : { stroke: "#0000FF", opacity: 0.2, fill: "#0077FF" },
		TERMINAL      : { stroke: "#808080", "stroke-width": 1, fill: "r(0.5, 0.5)#FFFFFF-#808080", "fill-opacity": 0.5, cursor: "pointer" },
		TERMINAL_OVER : { stroke: "#0077FF", "stroke-width": 4, fill: "r(0.5, 0.5)#FFFFFF-#0077FF", "fill-opacity": 1, cursor: "pointer" },
		TERMINAL_BBOX : { stroke: "none", fill: "white", "fill-opacity": 0 },
		DROP_OVER_BBOX: { stroke: "#0077FF", fill: "none", opacity: 0.6, "shape-rendering": "crispEdges" },
		LABEL         : { "font-size": 12, "font-color": "black" },
		LABEL_EDITOR  : { position: "absolute", overflow: "visible", resize: "none", "text-align": "center", display: "block", padding: 0 },
		COLLAPSE      : { stroke: "black", fill: "white", "fill-opacity": 0, cursor: "pointer", "shape-rendering": "crispEdges" },
		COLLAPSE_BBOX : { stroke: "none", fill: "white", "fill-opacity": 0 }
	},

	/**
	 * Rectangle 모양의 마우스 드래그 선택 박스 영역
	 */
	RUBBER_BAND_ID: "OG_R_BAND",

	/**
	 * Move & Resize 용 가이드 ID 의 suffix 정의
	 */
	GUIDE_SUFFIX: {
		GUIDE: "_GUIDE",
		BBOX : "_GUIDE_BBOX",
		UL   : "_GUIDE_UL",
		UR   : "_GUIDE_UR",
		LL   : "_GUIDE_LL",
		LR   : "_GUIDE_LR",
		LC   : "_GUIDE_LC",
		UC   : "_GUIDE_UC",
		RC   : "_GUIDE_RC",
		LWC  : "_GUIDE_LWC",
		FROM : "_GUIDE_FROM",
		TO   : "_GUIDE_TO",
		CTL  : "_GUIDE_CTL_",
		CTL_H: "_GUIDE_CTL_H_",
		CTL_V: "_GUIDE_CTL_V_"
	},

	/**
	 * Move & Resize 용 가이드 콘트롤 Rect 사이즈
	 */
	GUIDE_RECT_SIZE: 8,

	/**
	 * Move & Resize 용 가이드 가로, 세로 최소 사이즈
	 */
	GUIDE_MIN_SIZE: 18,

	/**
	 * Collapse & Expand 용 가이드 ID의 suffix 정의
	 */
	COLLAPSE_SUFFIX     : "_COLLAPSE",
	COLLAPSE_BBOX_SUFFIX: "_COLLAPSE_BBOX",

	/**
	 * Collapse & Expand 용 가이드 Rect 사이즈
	 */
	COLLAPSE_SIZE: 10,

	/**
	 * Shape Move & Resize 시 이동 간격
	 */
	MOVE_SNAP_SIZE: 5,

	/**
	 * Edge 연결할때 Drop Over 가이드 ID의 suffix 정의
	 */
	DROP_OVER_BBOX_SUFFIX: "_DROP_OVER",

	/**
	 * Shape - Edge 와의 연결 포인트 터미널 ID의 suffix 정의
	 */
	TERMINAL_SUFFIX: {
		GROUP: "_TERMINAL",
		BOX  : "_TERMINAL_BOX"
	},

	/**
	 * Shape - Edge 와의 연결 포인트 터미널 유형 정의
	 */
	TERMINAL_TYPE: {
		C    : "C",
		E    : "E",
		W    : "W",
		S    : "S",
		N    : "N",
		IN   : "IN",
		OUT  : "OUT",
		INOUT: "INOUT"
	},

	/**
	 * 터미널 cross 사이즈
	 */
	TERMINAL_SIZE: 3,

	/**
	 * Shape 복사시 패딩 사이즈
	 */
	COPY_PASTE_PADDING: 20
};
OG.Constants = OG.common.Constants;

// keyCode Definition
if (typeof KeyEvent === "undefined") {
	var KeyEvent = {
		DOM_VK_CANCEL       : 3,
		DOM_VK_HELP         : 6,
		DOM_VK_BACK_SPACE   : 8,
		DOM_VK_TAB          : 9,
		DOM_VK_CLEAR        : 12,
		DOM_VK_RETURN       : 13,
		DOM_VK_ENTER        : 14,
		DOM_VK_SHIFT        : 16,
		DOM_VK_CONTROL      : 17,
		DOM_VK_ALT          : 18,
		DOM_VK_PAUSE        : 19,
		DOM_VK_CAPS_LOCK    : 20,
		DOM_VK_ESCAPE       : 27,
		DOM_VK_SPACE        : 32,
		DOM_VK_PAGE_UP      : 33,
		DOM_VK_PAGE_DOWN    : 34,
		DOM_VK_END          : 35,
		DOM_VK_HOME         : 36,
		DOM_VK_LEFT         : 37,
		DOM_VK_UP           : 38,
		DOM_VK_RIGHT        : 39,
		DOM_VK_DOWN         : 40,
		DOM_VK_PRINTSCREEN  : 44,
		DOM_VK_INSERT       : 45,
		DOM_VK_DELETE       : 46,
		DOM_VK_0            : 48,
		DOM_VK_1            : 49,
		DOM_VK_2            : 50,
		DOM_VK_3            : 51,
		DOM_VK_4            : 52,
		DOM_VK_5            : 53,
		DOM_VK_6            : 54,
		DOM_VK_7            : 55,
		DOM_VK_8            : 56,
		DOM_VK_9            : 57,
		DOM_VK_SEMICOLON    : 59,
		DOM_VK_EQUALS       : 61,
		DOM_VK_A            : 65,
		DOM_VK_B            : 66,
		DOM_VK_C            : 67,
		DOM_VK_D            : 68,
		DOM_VK_E            : 69,
		DOM_VK_F            : 70,
		DOM_VK_G            : 71,
		DOM_VK_H            : 72,
		DOM_VK_I            : 73,
		DOM_VK_J            : 74,
		DOM_VK_K            : 75,
		DOM_VK_L            : 76,
		DOM_VK_M            : 77,
		DOM_VK_N            : 78,
		DOM_VK_O            : 79,
		DOM_VK_P            : 80,
		DOM_VK_Q            : 81,
		DOM_VK_R            : 82,
		DOM_VK_S            : 83,
		DOM_VK_T            : 84,
		DOM_VK_U            : 85,
		DOM_VK_V            : 86,
		DOM_VK_W            : 87,
		DOM_VK_X            : 88,
		DOM_VK_Y            : 89,
		DOM_VK_Z            : 90,
		DOM_VK_COMMAND      : 91,
		DOM_VK_CONTEXT_MENU : 93,
		DOM_VK_NUMPAD0      : 96,
		DOM_VK_NUMPAD1      : 97,
		DOM_VK_NUMPAD2      : 98,
		DOM_VK_NUMPAD3      : 99,
		DOM_VK_NUMPAD4      : 100,
		DOM_VK_NUMPAD5      : 101,
		DOM_VK_NUMPAD6      : 102,
		DOM_VK_NUMPAD7      : 103,
		DOM_VK_NUMPAD8      : 104,
		DOM_VK_NUMPAD9      : 105,
		DOM_VK_MULTIPLY     : 106,
		DOM_VK_ADD          : 107,
		DOM_VK_SEPARATOR    : 108,
		DOM_VK_SUBTRACT     : 109,
		DOM_VK_DECIMAL      : 110,
		DOM_VK_DIVIDE       : 111,
		DOM_VK_F1           : 112,
		DOM_VK_F2           : 113,
		DOM_VK_F3           : 114,
		DOM_VK_F4           : 115,
		DOM_VK_F5           : 116,
		DOM_VK_F6           : 117,
		DOM_VK_F7           : 118,
		DOM_VK_F8           : 119,
		DOM_VK_F9           : 120,
		DOM_VK_F10          : 121,
		DOM_VK_F11          : 122,
		DOM_VK_F12          : 123,
		DOM_VK_F13          : 124,
		DOM_VK_F14          : 125,
		DOM_VK_F15          : 126,
		DOM_VK_F16          : 127,
		DOM_VK_F17          : 128,
		DOM_VK_F18          : 129,
		DOM_VK_F19          : 130,
		DOM_VK_F20          : 131,
		DOM_VK_F21          : 132,
		DOM_VK_F22          : 133,
		DOM_VK_F23          : 134,
		DOM_VK_F24          : 135,
		DOM_VK_NUM_LOCK     : 144,
		DOM_VK_SCROLL_LOCK  : 145,
		DOM_VK_COMMA        : 188,
		DOM_VK_PERIOD       : 190,
		DOM_VK_SLASH        : 191,
		DOM_VK_BACK_QUOTE   : 192,
		DOM_VK_OPEN_BRACKET : 219,
		DOM_VK_BACK_SLASH   : 220,
		DOM_VK_CLOSE_BRACKET: 221,
		DOM_VK_QUOTE        : 222,
		DOM_VK_META         : 224
	};
}
/**
 * 공통 유틸리티 Javascript 클래스
 *
 * @class
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.Util = {

	isEmpty    : function (v, allowBlank) {
		return v === null || v === undefined || ((OG.Util.isArray(v) && !v.length)) || (!allowBlank ? v === '' : false);
	},
	isArray    : function (v) {
		return Object.prototype.toString.apply(v) === '[object Array]';
	},
	isDate     : function (v) {
		return Object.prototype.toString.apply(v) === '[object Date]';
	},
	isObject   : function (v) {
		return !!v && Object.prototype.toString.call(v) === '[object Object]';
	},
	isPrimitive: function (v) {
		return OG.Util.isString(v) || OG.Util.isNumber(v) || OG.Util.isBoolean(v);
	},
	isFunction : function (v) {
		return Object.prototype.toString.apply(v) === '[object Function]';
	},
	isNumber   : function (v) {
		return typeof v === 'number' && isFinite(v);
	},
	isString   : function (v) {
		return typeof v === 'string';
	},
	isBoolean  : function (v) {
		return typeof v === 'boolean';
	},
	isElement  : function (v) {
		return !!v && v.tagName ? true : false;
	},
	isDefined  : function (v) {
		return typeof v !== 'undefined';
	},

	isWebKit : function () {
		return (/webkit/).test(navigator.userAgent.toLowerCase());
	},
	isGecko  : function () {
		return !OG.Util.isWebKit() && (/gecko/).test(navigator.userAgent.toLowerCase());
	},
	isOpera  : function () {
		return (/opera/).test(navigator.userAgent.toLowerCase());
	},
	isChrome : function () {
		return (/\bchrome\b/).test(navigator.userAgent.toLowerCase());
	},
	isSafari : function () {
		return !OG.Util.isChrome() && (/safari/).test(navigator.userAgent.toLowerCase());
	},
	isFirefox: function () {
		return (/firefox/).test(navigator.userAgent.toLowerCase());
	},
	isIE     : function () {
		return !OG.Util.isOpera() && (/msie/).test(navigator.userAgent.toLowerCase());
	},
	isIE6    : function () {
		return OG.Util.isIE() && (/msie 6/).test(navigator.userAgent.toLowerCase());
	},
	isIE7    : function () {
		return OG.Util.isIE() && ((/msie 7/).test(navigator.userAgent.toLowerCase()) || document.documentMode === 7);
	},
	isIE8    : function () {
		return OG.Util.isIE() && ((/msie 8/).test(navigator.userAgent.toLowerCase()) || document.documentMode === 8);
	},
	isIE9    : function () {
		return OG.Util.isIE() && ((/msie 9/).test(navigator.userAgent.toLowerCase()) || document.documentMode === 9);
	},
	isWindows: function () {
		return (/windows|win32/).test(navigator.userAgent.toLowerCase());
	},
	isMac    : function () {
		return (/macintosh|mac os x/).test(navigator.userAgent.toLowerCase());
	},
	isLinux  : function () {
		return (/linux/).test(navigator.userAgent.toLowerCase());
	},

	/**
	 * Object 를 복사한다.
	 *
	 * @param {Object} obj 복사할 Object
	 * @return {Object} 복사된 Object
	 * @static
	 */
	clone: function (obj) {
		if (obj === null || obj === undefined) {
			return obj;
		}

		// DOM nodes
		if (obj.nodeType && obj.cloneNode) {
			return obj.cloneNode(true);
		}

		var i, j, k, clone, key,
			type = Object.prototype.toString.call(obj),
			enumerables = ["hasOwnProperty", "valueOf", "isPrototypeOf", "propertyIsEnumerable",
				"toLocaleString", "toString", "constructor"];

		// Date
		if (type === "[object Date]") {
			return new Date(obj.getTime());
		}

		// Array, Object
		if (type === "[object Array]") {
			i = obj.length;

			clone = [];

			while (i--) {
				clone[i] = this.clone(obj[i]);
			}
		} else if (type === "[object Object]" && obj.constructor === Object) {
			// TODO : 보완필요
			clone = {};

			for (key in obj) {
				clone[key] = this.clone(obj[key]);
			}

			if (enumerables) {
				for (j = enumerables.length; j--;) {
					k = enumerables[j];
					clone[k] = obj[k];
				}
			}
		}

		return clone || obj;
	},

	/**
	 * 디폴트로 지정된 소숫점 자리수로 Round 한 값을 반환한다.
	 *
	 * @param {Number} val 반올림할 값
	 * @return {Number} 지정한 소숫점 자리수에 따른 반올림 값
	 */
	round: function (val) {
		return this.roundPrecision(val, OG.Constants.NUM_PRECISION);
	},

	/**
	 * 입력된 숫자값을 지정된 소숫점 자릿수로 Round해서 값을 리턴한다.
	 * @example
	 * OG.Util.roundPrecision(300.12345678, 3);
	 * Result ) 300.123
	 *
	 * @param {Number} val 반올림할 값
	 * @param {Number} precision 소숫점 자리수
	 * @return {Number} 지정한 소숫점 자리수에 따른 반올림 값
	 */
	roundPrecision: function (val, precision) {
		var p = Math.pow(10, precision);
		return Math.round(val * p) / p;
	},

	/**
	 * Copies all the properties of config to obj.
	 *
	 * @param {Object} obj The receiver of the properties
	 * @param {Object} config The source of the properties
	 * @param {Object} defaults A different object that will also be applied for default values
	 * @return {Object} returns obj
	 */
	apply: function (obj, config, defaults) {
		// no "this" reference for friendly out of scope calls
		var p;
		if (defaults) {
			this.apply(obj, defaults);
		}
		if (obj && config && typeof config === 'object') {
			for (p in config) {
				obj[p] = config[p];
			}
		}
		return obj;
	},

	/**
	 * <p>Extends one class to create a subclass and optionally overrides members with the passed literal. This method
	 * also adds the function "override()" to the subclass that can be used to override members of the class.</p>
	 * For example, to create a subclass of Ext GridPanel:
	 * <pre><code>
	 MyGridPanel = Ext.extend(Ext.grid.GridPanel, {
	 constructor: function(config) {

	 //      Create configuration for this Grid.
	 var store = new Ext.data.Store({...});
	 var colModel = new Ext.grid.ColumnModel({...});

	 //      Create a new config object containing our computed properties
	 //      *plus* whatever was in the config parameter.
	 config = Ext.apply({
	 store: store,
	 colModel: colModel
	 }, config);

	 MyGridPanel.superclass.constructor.call(this, config);

	 //      Your postprocessing here
	 },

	 yourMethod: function() {
	 // etc.
	 }
	 });
	 </code></pre>
	 *
	 * <p>This function also supports a 3-argument call in which the subclass's constructor is
	 * passed as an argument. In this form, the parameters are as follows:</p>
	 * <div class="mdetail-params"><ul>
	 * <li><code>subclass</code> : Function <div class="sub-desc">The subclass constructor.</div></li>
	 * <li><code>superclass</code> : Function <div class="sub-desc">The constructor of class being extended</div></li>
	 * <li><code>overrides</code> : Object <div class="sub-desc">A literal with members which are copied into the subclass's
	 * prototype, and are therefore shared among all instances of the new class.</div></li>
	 * </ul></div>
	 *
	 * @param {Function} superclass The constructor of class being extended.
	 * @param {Object} overrides <p>A literal with members which are copied into the subclass's
	 * prototype, and are therefore shared between all instances of the new class.</p>
	 * <p>This may contain a special member named <tt><b>constructor</b></tt>. This is used
	 * to define the constructor of the new class, and is returned. If this property is
	 * <i>not</i> specified, a constructor is generated and returned which just calls the
	 * superclass's constructor passing on its parameters.</p>
	 * <p><b>It is essential that you call the superclass constructor in any provided constructor. See example code.</b></p>
	 * @return {Function} The subclass constructor from the <code>overrides</code> parameter, or a generated one if not provided.
	 */
	extend: (function () {
		// inline overrides
		var io = function (o) {
				var m;
				for (m in o) {
					this[m] = o[m];
				}
			},
			oc = Object.prototype.constructor;

		return function (sb, sp, overrides) {
			if (OG.Util.isObject(sp)) {
				overrides = sp;
				sp = sb;
				sb = overrides.constructor !== oc ? overrides.constructor : function () {
					sp.apply(this, arguments);
				};
			}
			var F = function () {
				},
				sbp,
				spp = sp.prototype;

			F.prototype = spp;
			sbp = sb.prototype = new F();
			sbp.constructor = sb;
			sb.superclass = spp;
			if (spp.constructor === oc) {
				spp.constructor = sp;
			}
			sb.override = function (o) {
				OG.Util.override(sb, o);
			};
			sbp.superclass = sbp.supr = (function () {
				return spp;
			}());
			sbp.override = io;
			OG.Util.override(sb, overrides);
			sb.extend = function (o) {
				return OG.Util.extend(sb, o);
			};
			return sb;
		};
	}()),

	/**
	 * Adds a list of functions to the prototype of an existing class, overwriting any existing methods with the same name.
	 * Usage:<pre><code>
	 Ext.override(MyClass, {
	 newMethod1: function(){
	 // etc.
	 },
	 newMethod2: function(foo){
	 // etc.
	 }
	 });
	 </code></pre>
	 * @param {Object} origclass The class to override
	 * @param {Object} overrides The list of functions to add to origClass.  This should be specified as an object literal
	 * containing one or more methods.
	 * @method override
	 */
	override: function (origclass, overrides) {
		if (overrides) {
			var p = origclass.prototype;
			OG.Util.apply(p, overrides);
			if ((/msie/).test(navigator.userAgent.toLowerCase()) && overrides.hasOwnProperty('toString')) {
				p.toString = overrides.toString;
			}
		}
	},

	xmlToJson: function (node) {
		var json = {},
			cloneNS = function (ns) {
				var nns = {};
				for (var n in ns) {
					if (ns.hasOwnProperty(n)) {
						nns[n] = ns[n];
					}
				}
				return nns;
			},
			process = function (node, obj, ns) {
				if (node.nodeType === 3) {
					if (!node.nodeValue.match(/[\S]+/)) return;
					if (obj["$"] instanceof Array) {
						obj["$"].push(node.nodeValue);
					} else if (obj["$"] instanceof Object) {
						obj["$"] = [obj["$"], node.nodeValue];
					} else {
						obj["$"] = node.nodeValue;
					}
				} else if (node.nodeType === 1) {
					var p = {};
					var nodeName = node.nodeName;
					for (var i = 0; node.attributes && i < node.attributes.length; i++) {
						var attr = node.attributes[i];
						var name = attr.nodeName;
						var value = attr.nodeValue;
						if (name === "xmlns") {
							ns["$"] = value;
						} else if (name.indexOf("xmlns:") === 0) {
							ns[name.substr(name.indexOf(":") + 1)] = value;
						} else {
							p["@" + name] = value;
						}
					}
					for (var prefix in ns) {
						if (ns.hasOwnProperty(prefix)) {
							p["@xmlns"] = p["@xmlns"] || {};
							p["@xmlns"][prefix] = ns[prefix];
						}
					}
					if (obj[nodeName] instanceof Array) {
						obj[nodeName].push(p);
					} else if (obj[nodeName] instanceof Object) {
						obj[nodeName] = [obj[nodeName], p];
					} else {
						obj[nodeName] = p;
					}
					for (var j = 0; j < node.childNodes.length; j++) {
						process(node.childNodes[j], p, cloneNS(ns));
					}
				} else if (node.nodeType === 9) {
					for (var k = 0; k < node.childNodes.length; k++) {
						process(node.childNodes[k], obj, cloneNS(ns));
					}
				}
			};
		process(node, json, {});
		return json;
	},

	jsonToXml: function (json) {
		if (typeof json !== "object") return null;
		var cloneNS = function (ns) {
			var nns = {};
			for (var n in ns) {
				if (ns.hasOwnProperty(n)) {
					nns[n] = ns[n];
				}
			}
			return nns;
		};

		var processLeaf = function (lname, child, ns) {
			var body = "";
			if (child instanceof Array) {
				for (var i = 0; i < child.length; i++) {
					body += processLeaf(lname, child[i], cloneNS(ns));
				}
				return body;
			} else if (typeof child === "object") {
				var el = "<" + lname;
				var attributes = "";
				var text = "";
				if (child["@xmlns"]) {
					var xmlns = child["@xmlns"];
					for (var prefix in xmlns) {
						if (xmlns.hasOwnProperty(prefix)) {
							if (prefix === "$") {
								if (ns[prefix] !== xmlns[prefix]) {
									attributes += " " + "xmlns=\"" + xmlns[prefix] + "\"";
									ns[prefix] = xmlns[prefix];
								}
							} else if (!ns[prefix] || (ns[prefix] !== xmlns[prefix])) {
								attributes += " xmlns:" + prefix + "=\"" + xmlns[prefix] + "\"";
								ns[prefix] = xmlns[prefix];
							}
						}
					}
				}
				for (var key in child) {
					if (child.hasOwnProperty(key) && key !== "@xmlns") {
						var obj = child[key];
						if (key === "$") {
							text += obj;
						} else if (key.indexOf("@") === 0) {
							attributes += " " + key.substring(1) + "=\"" + obj + "\"";
						} else {
							body += processLeaf(key, obj, cloneNS(ns));
						}
					}
				}
				body = text + body;
				return (body !== "") ? el + attributes + ">" + body + "</" + lname + ">" : el + attributes + "/>"
			}
		};
		for (var lname in json) {
			if (json.hasOwnProperty(lname) && lname.indexOf("@") == -1) {
				return processLeaf(lname, json[lname], {});
			}
		}
		return null;
	}
};
OG.Util = OG.common.Util;
/**
 * 곡선(Curve) 알고리즘을 구현한 Javascript 클래스
 *
 * @class
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.CurveUtil = {
	/**
	 * 주어진 좌표 Array 에 대해 Cubic Catmull-Rom spline Curve 좌표를 계산하는 함수를 반환한다.
	 * 모든 좌표를 지나는 커브를 계산한다.
	 *
	 * @example
	 * var points = [[2, 2], [2, -2], [-2, 2], [-2, -2]],
	 *     cmRomSpline = OG.CurveUtil.CatmullRomSpline(points), t, curve = [];
	 *
	 * // t 는 0 ~ maxT 의 값으로, t 값의 증분값이 작을수록 세밀한 Curve 를 그린다.
	 * for(t = 0; t <= cmRomSpline.maxT; t += 0.1) {
	 *     curve.push([cmRomSpline.getX(t), cmRomSpline.getY(t)]);
	 * }
	 *
	 * @param {Array} points 좌표 Array (예, [[x1,y1], [x2,y2], [x3,y3], [x4,y4]])
	 * @return {Object} t 값에 의해 X, Y 좌표를 구하는 함수와 maxT 값을 반환
	 * @static
	 */
	CatmullRomSpline: function (points) {
		var coeffs = [], p,
			first = {},
			last = {}, // control point at the beginning and at the end

			makeFct = function (which) {

				return function (t, suspendedUpdate) {

					var len = points.length, s, c;

					if (len < 2) {
						return NaN;
					}

					t = t - 1;

					if (!suspendedUpdate && coeffs[which]) {
						suspendedUpdate = true;
					}

					if (!suspendedUpdate) {
						first[which] = 2 * points[0][which] - points[1][which];
						last[which] = 2 * points[len - 1][which] - points[len - 2][which];
						p = [first].concat(points, [last]);

						coeffs[which] = [];
						for (s = 0; s < len - 1; s++) {
							coeffs[which][s] = [
								2 * p[s + 1][which],
								-p[s][which] + p[s + 2][which],
								2 * p[s][which] - 5 * p[s + 1][which] + 4 * p[s + 2][which] - p[s + 3][which],
								-p[s][which] + 3 * p[s + 1][which] - 3 * p[s + 2][which] + p[s + 3][which]
							];
						}
					}
					len += 2;  // add the two control points
					if (isNaN(t)) {
						return NaN;
					}
					// This is necessay for our advanced plotting algorithm:
					if (t < 0) {
						return p[1][which];
					} else if (t >= len - 3) {
						return p[len - 2][which];
					}

					s = Math.floor(t);
					if (s === t) {
						return p[s][which];
					}
					t -= s;
					c = coeffs[which][s];
					return 0.5 * (((c[3] * t + c[2]) * t + c[1]) * t + c[0]);
				};
			};

		return {
			getX: makeFct(0),
			getY: makeFct(1),
			maxT: points.length + 1
		};
	},

	/**
	 * 주어진 좌표 Array (좌표1, 콘트롤포인트1, 콘트롤포인트2, 좌표2 ...) 에 대해 Cubic Bezier Curve 좌표를 계산하는 함수를 반환한다.
	 * Array 갯수는 3 * K + 1 이어야 한다.
	 * 예) 좌표1, 콘트롤포인트1, 콘트롤포인트2, 좌표2, 콘트롤포인트1, 콘트롤포인트2, 좌표3 ...
	 *
	 * @example
	 * var points = [[2, 1], [1, 3], [-1, -1], [-2, 1]],
	 *     bezier = OG.CurveUtil.Bezier(points), t, curve = [];
	 *
	 * // t 는 0 ~ maxT 의 값으로, t 값의 증분값이 작을수록 세밀한 Curve 를 그린다.
	 * for(t = 0; t <= bezier.maxT; t += 0.1) {
	 *     curve.push([bezier.getX(t), bezier.getY(t)]);
	 * }
	 *
	 * @param {Array} points 좌표 Array (예, [[x1,y1], [cp_x1,cp_y1], [cp_x2,cp_y2], [x2,y4]])
	 * @return {Object} t 값에 의해 X, Y 좌표를 구하는 함수와 maxT 값을 반환
	 * @static
	 */
	Bezier: function (points) {
		var len,
			makeFct = function (which) {
				return function (t, suspendedUpdate) {
					var z = Math.floor(t) * 3,
						t0 = t,
						t1 = 1 - t0;

					if (!suspendedUpdate && len) {
						suspendedUpdate = true;
					}

					if (!suspendedUpdate) {
						len = Math.floor(points.length / 3);
					}

					if (t < 0) {
						return points[0][which];
					}
					if (t >= len) {
						return points[points.length - 1][which];
					}
					if (isNaN(t)) {
						return NaN;
					}
					return t1 * t1 * (t1 * points[z][which] + 3 * t0 * points[z + 1][which]) +
						(3 * t1 * points[z + 2][which] + t0 * points[z + 3][which]) * t0 * t0;
				};
			};

		return {
			getX: makeFct(0),
			getY: makeFct(1),
			maxT: Math.floor(points.length / 3) + 1
		};
	},

	/**
	 * 주어진 좌표 Array (시작좌표, 콘트롤포인트1, 콘트롤포인트2, ..., 끝좌표) 에 대해 B-Spline Curve 좌표를 계산하는 함수를 반환한다.
	 *
	 * @example
	 * var points = [[2, 1], [1, 3], [-1, -1], [-2, 1]],
	 *     bspline = OG.CurveUtil.BSpline(points), t, curve = [];
	 *
	 * // t 는 0 ~ maxT 의 값으로, t 값의 증분값이 작을수록 세밀한 Curve 를 그린다.
	 * for(t = 0; t <= bspline.maxT; t += 0.1) {
	 *     curve.push([bspline.getX(t), bspline.getY(t)]);
	 * }
	 *
	 * @param {Array} points 좌표 Array (예, [[x1,y1], [x2,y2], [x3,y3], [x4,y4]])
	 * @param {Number} order Order of the B-spline curve.
	 * @return {Object} t 값에 의해 X, Y 좌표를 구하는 함수와 maxT 값을 반환
	 * @static
	 */
	BSpline: function (points, order) {
		var knots, N = [],
			_knotVector = function (n, k) {
				var j, kn = [];
				for (j = 0; j < n + k + 1; j++) {
					if (j < k) {
						kn[j] = 0.0;
					} else if (j <= n) {
						kn[j] = j - k + 1;
					} else {
						kn[j] = n - k + 2;
					}
				}
				return kn;
			},

			_evalBasisFuncs = function (t, kn, n, k, s) {
				var i, j, a, b, den,
					N = [];

				if (kn[s] <= t && t < kn[s + 1]) {
					N[s] = 1;
				} else {
					N[s] = 0;
				}
				for (i = 2; i <= k; i++) {
					for (j = s - i + 1; j <= s; j++) {
						if (j <= s - i + 1 || j < 0) {
							a = 0.0;
						} else {
							a = N[j];
						}
						if (j >= s) {
							b = 0.0;
						} else {
							b = N[j + 1];
						}
						den = kn[j + i - 1] - kn[j];
						if (den === 0) {
							N[j] = 0;
						} else {
							N[j] = (t - kn[j]) / den * a;
						}
						den = kn[j + i] - kn[j + 1];
						if (den !== 0) {
							N[j] += (kn[j + i] - t) / den * b;
						}
					}
				}
				return N;
			},
			makeFct = function (which) {
				return function (t, suspendedUpdate) {
					var len = points.length, y, j, s,
						n = len - 1,
						k = order;

					if (n <= 0) {
						return NaN;
					}
					if (n + 2 <= k) {
						k = n + 1;
					}
					if (t <= 0) {
						return points[0][which];
					}
					if (t >= n - k + 2) {
						return points[n][which];
					}

					knots = _knotVector(n, k);
					s = Math.floor(t) + k - 1;
					N = _evalBasisFuncs(t, knots, n, k, s);

					y = 0.0;
					for (j = s - k + 1; j <= s; j++) {
						if (j < len && j >= 0) {
							y += points[j][which] * N[j];
						}
					}
					return y;
				};
			};

		return {
			getX: makeFct(0),
			getY: makeFct(1),
			maxT: points.length - 2
		};
	}
};
OG.CurveUtil = OG.common.CurveUtil;
/**
 * 사용자 정의 예외 클래스 NotSupportedException
 *
 * @class
 *
 * @param {String} message 메시지
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.NotSupportedException = function (message) {
	/**
	 * {String} 예외명
	 */
	this.name = "OG.NotSupportedException";

	/**
	 * {String} 메시지
	 */
	this.message = message || "Not Supported!";
};
OG.NotSupportedException = OG.common.NotSupportedException;

/**
 * 사용자 정의 예외 클래스 NotImplementedException
 *
 * @class
 *
 * @param {String} message 메시지
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.NotImplementedException = function (message) {
	/**
	 * {String} 예외명
	 */
	this.name = "OG.NotImplementedException";

	/**
	 * {String} 메시지
	 */
	this.message = message || "Not Implemented!";
};
OG.NotImplementedException = OG.common.NotImplementedException;

/**
 * 사용자 정의 예외 클래스 ParamError
 *
 * @class
 *
 * @param {String} message 메시지
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.ParamError = function (message) {
	/**
	 * {String} 예외명
	 */
	this.name = "OG.ParamError";

	/**
	 * {String} 메시지
	 */
	this.message = message || "Invalid Parameter Error!";
};
OG.ParamError = OG.common.ParamError;
/**
 * HashMap 구현 Javascript 클래스
 *
 * @class
 *
 * @param {Object} jsonObject key:value 매핑 JSON 오브젝트
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.HashMap = function (jsonObject) {

	this.map = jsonObject || {};

	/**
	 * key : value 를 매핑한다.
	 *
	 * @param {String} key 키
	 * @param {Object} value 값
	 */
	this.put = function (key, value) {
		this.map[key] = value;
	};

	/**
	 * key 에 대한 value 를 반환한다.
	 *
	 * @param {String} key 키
	 * @return {Object} 값
	 */
	this.get = function (key) {
		return this.map[key];
	};

	/**
	 * 주어진 key 를 포함하는지 여부를 반환한다.
	 *
	 * @param {String} key 키
	 * @return {Boolean}
	 */
	this.containsKey = function (key) {
		return this.map.hasOwnProperty(key);
	};

	/**
	 * 주어진 value 를 포함하는지 여부를 반환한다.
	 *
	 * @param {Object} value 값
	 * @return {Boolean}
	 */
	this.containsValue = function (value) {
		var prop;
		for (prop in this.map) {
			if (this.map[prop] === value) {
				return true;
			}
		}
		return false;
	};

	/**
	 * Empty 여부를 반환한다.
	 *
	 * @return {Boolean}
	 */
	this.isEmpty = function () {
		return this.size() === 0;
	};

	/**
	 * 매핑정보를 클리어한다.
	 */
	this.clear = function () {
		var prop;
		for (prop in this.map) {
			delete this.map[prop];
		}
	};

	/**
	 * 주어진 key 의 매핑정보를 삭제한다.
	 *
	 * @param {String} key 키
	 */
	this.remove = function (key) {
		if (this.map[key]) {
			delete this.map[key];
		}
	};

	/**
	 * key 목록을 반환한다.
	 *
	 * @return {String[]} 키목록
	 */
	this.keys = function () {
		var keys = [], prop;
		for (prop in this.map) {
			keys.push(prop);
		}
		return keys;
	};

	/**
	 * value 목록을 반환한다.
	 *
	 * @return {Object[]} 값목록
	 */
	this.values = function () {
		var values = [], prop;
		for (prop in this.map) {
			values.push(this.map[prop]);
		}
		return values;
	};

	/**
	 * 매핑된 key:value 갯수를 반환한다.
	 *
	 * @return {Number}
	 */
	this.size = function () {
		var count = 0, prop;
		for (prop in this.map) {
			count++;
		}
		return count;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [], prop;
		for (prop in this.map) {
			s.push("'" + prop + "':'" + this.map[prop] + "'");
		}

		return "{" + s.join() + "}";
	};
};
OG.common.HashMap.prototype = new OG.common.HashMap();
OG.common.HashMap.prototype.constructor = OG.common.HashMap;
OG.HashMap = OG.common.HashMap;
/**
 * Modified version of Douglas Crockford"s json.js that doesn"t
 * mess with the Object prototype
 * http://www.json.org/js.html
 *
 * @class
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.common.JSON = new (function () {
	var useHasOwn = !!{}.hasOwnProperty,
		USE_NATIVE_JSON = false,
		isNative = (function () {
			var useNative = null;

			return function () {
				if (useNative === null) {
					useNative = USE_NATIVE_JSON && window.JSON && JSON.toString() === '[object JSON]';
				}

				return useNative;
			};
		}()),
		m = {
			"\b": '\\b',
			"\t": '\\t',
			"\n": '\\n',
			"\f": '\\f',
			"\r": '\\r',
			'"' : '\\"',
			"\\": '\\\\'
		},
		pad = function (n) {
			return n < 10 ? "0" + n : n;
		},
		doDecode = function (json) {
			return eval("(" + json + ')');
		},
		encodeString = function (s) {
			if (/["\\\x00-\x1f]/.test(s)) {
				return '"' + s.replace(/([\x00-\x1f\\"])/g, function (a, b) {
					var c = m[b];
					if (c) {
						return c;
					}
					c = b.charCodeAt();
					return "\\u00" +
						Math.floor(c / 16).toString(16) +
						(c % 16).toString(16);
				}) + '"';
			}
			return '"' + s + '"';
		},
		encodeArray = function (o) {
			var a = ["["], b, i, l = o.length, v;
			for (i = 0; i < l; i += 1) {
				v = o[i];
				switch (typeof v) {
				case "undefined":
				case "function":
				case "unknown":
					break;
				default:
					if (b) {
						a.push(',');
					}
					a.push(v === null ? "null" : OG.common.JSON.encode(v));
					b = true;
				}
			}
			a.push("]");
			return a.join("");
		},
		doEncode = function (o) {
			if (!OG.Util.isDefined(o) || o === null) {
				return "null";
			} else if (OG.Util.isArray(o)) {
				return encodeArray(o);
			} else if (OG.Util.isDate(o)) {
				return OG.common.JSON.encodeDate(o);
			} else if (OG.Util.isString(o)) {
				return encodeString(o);
			} else if (typeof o === "number") {
				//don't use isNumber here, since finite checks happen inside isNumber
				return isFinite(o) ? String(o) : "null";
			} else if (OG.Util.isBoolean(o)) {
				return String(o);
			} else {
				var a = ["{"], b, i, v;
				for (i in o) {
					// don't encode DOM objects
					if (!o.getElementsByTagName) {
						if (!useHasOwn || o.hasOwnProperty(i)) {
							v = o[i];
							switch (typeof v) {
							case "undefined":
							case "function":
							case "unknown":
								break;
							default:
								if (b) {
									a.push(',');
								}
								a.push(doEncode(i), ":",
									v === null ? "null" : doEncode(v));
								b = true;
							}
						}
					}
				}
				a.push("}");
				return a.join("");
			}
		};

	/**
	 * <p>Encodes a Date. This returns the actual string which is inserted into the JSON string as the literal expression.
	 * <b>The returned value includes enclosing double quotation marks.</b></p>
	 * <p>The default return format is "yyyy-mm-ddThh:mm:ss".</p>
	 * <p>To override this:</p><pre><code>
	 OG.common.JSON.encodeDate = function(d) {
	 return d.format('"Y-m-d"');
	 };
	 </code></pre>
	 * @param {Date} d The Date to encode
	 * @return {String} The string literal to use in a JSON string.
	 */
	this.encodeDate = function (o) {
		return '"' + o.getFullYear() + "-" +
			pad(o.getMonth() + 1) + "-" +
			pad(o.getDate()) + "T" +
			pad(o.getHours()) + ":" +
			pad(o.getMinutes()) + ":" +
			pad(o.getSeconds()) + '"';
	};

	/**
	 * Encodes an Object, Array or other value
	 * @param {Mixed} o The variable to encode
	 * @return {String} The JSON string
	 */
	this.encode = (function () {
		var ec;
		return function (o) {
			if (!ec) {
				// setup encoding function on first access
				ec = isNative() ? JSON.stringify : doEncode;
			}
			return ec(o);
		};
	}());


	/**
	 * Decodes (parses) a JSON string to an object. If the JSON is invalid, this function throws a SyntaxError unless the safe option is set.
	 * @param {String} json The JSON string
	 * @return {Object} The resulting object
	 */
	this.decode = (function () {
		var dc;
		return function (json) {
			if (!dc) {
				// setup decoding function on first access
				dc = isNative() ? JSON.parse : doDecode;
			}
			return dc(json);
		};
	}());

})();
OG.JSON = OG.common.JSON;

/**
 * 스타일(StyleSheet) Property 정보 클래스
 *
 * @class
 * @extends OG.common.HashMap
 *
 * @param {OG.common.HashMap} style 키:값 매핑된 스타일 프라퍼티 정보
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Style = function (style) {
	var DEFAULT_STYLE = {
			"stroke"      : "black",
			"stroke-width": 1
		},
		_style = {};

	OG.Util.apply(_style, style, DEFAULT_STYLE);

	OG.geometry.Style.superclass.call(this, _style);
};
OG.geometry.Style.prototype = new OG.common.HashMap();
OG.geometry.Style.superclass = OG.common.HashMap;
OG.geometry.Style.prototype.constructor = OG.geometry.Style;
OG.Style = OG.geometry.Style;
/**
 * 2차원 좌표계에서의 좌표값
 *
 * @example
 * var coordinate1 = new OG.Coordinate(10, 10);
 * or
 * var coordinate2 = new OG.Coordinate([20, 20]);
 *
 * @class
 *
 * @param {Number} x x좌표
 * @param {Number} y y좌표
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Coordinate = function (x, y) {

	/**
	 * {Number} x좌표
	 */
	this.x = undefined;

	/**
	 * {Number} y좌표
	 */
	this.y = undefined;

	// Array 좌표를 OG.geometry.Coordinate 로 변환
	if (arguments.length === 1 && x.constructor === Array) {
		this.x = x[0];
		this.y = x[1];
	} else if (arguments.length === 2 && typeof x === "number" && typeof y === "number") {
		this.x = x;
		this.y = y;
	} else if (arguments.length !== 0) {
		throw new OG.ParamError();
	}

	/**
	 * 주어진 좌표와의 거리를 계산한다.
	 *
	 * @example
	 * coordinate.distance([10, 10]);
	 * or
	 * coordinate.distance(new OG.Coordinate(10, 10));
	 *
	 *
	 * @param {OG.geometry.Coordinate,Number[]} coordinate 좌표값
	 * @return {Number} 좌표간의 거리값
	 */
	this.distance = function (coordinate) {
		if (coordinate.constructor === Array) {
			coordinate = new OG.geometry.Coordinate(coordinate[0], coordinate[1]);
		}

		var dx = this.x - coordinate.x, dy = this.y - coordinate.y;
		return OG.Util.round(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
	};

	/**
	 * 가로, 세로 Offset 만큼 좌표를 이동한다.
	 *
	 * @param {Number} offsetX 가로 Offset
	 * @param {Number} offsetY 세로 Offset
	 * @return {OG.geometry.Coordinate} 이동된 좌표
	 */
	this.move = function (offsetX, offsetY) {
		this.x += offsetX;
		this.y += offsetY;

		return this;
	};

	/**
	 * 기준 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @example
	 * coordinate.rotate(90, [10,10]);
	 * or
	 * coordinate.rotate(90, new OG.Coordinate(10, 10));
	 *
	 * @param {Number} angle 회전 각도
	 * @param {OG.geometry.Coordinate,Number[]} origin 기준 좌표
	 * @return {OG.geometry.Coordinate} 회전된 좌표
	 */
	this.rotate = function (angle, origin) {
		if (origin.constructor === Array) {
			origin = new OG.geometry.Coordinate(origin[0], origin[1]);
		}

		angle *= Math.PI / 180;
		var radius = this.distance(origin),
			theta = angle + Math.atan2(this.y - origin.y, this.x - origin.x);
		this.x = OG.Util.round(origin.x + (radius * Math.cos(theta)));
		this.y = OG.Util.round(origin.y + (radius * Math.sin(theta)));

		return this;
	};

	/**
	 * 주어진 좌표값과 같은지 비교한다.
	 *
	 * @example
	 * coordinate.isEquals([10, 10]);
	 * or
	 * coordinate.isEquals(new OG.Coordinate(10, 10));
	 *
	 * @param {OG.geometry.Coordinate,Number[]} coordinate 좌표값
	 * @return {Boolean} true:같음, false:다름
	 */
	this.isEquals = function (coordinate) {
		if (coordinate.constructor === Array) {
			coordinate = new OG.geometry.Coordinate(coordinate[0], coordinate[1]);
		}

		if (coordinate && coordinate instanceof OG.geometry.Coordinate) {
			if (this.x === coordinate.x && this.y === coordinate.y) {
				return true;
			}
		}

		return false;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push(this.x);
		s.push(this.y);

		return "[" + s.join() + "]";
	};
};
OG.geometry.Coordinate.prototype = new OG.geometry.Coordinate();
OG.geometry.Coordinate.prototype.constructor = OG.geometry.Coordinate;
OG.Coordinate = OG.geometry.Coordinate;
/**
 * 2차원 좌표계에서 Envelope 영역을 정의
 *
 * @example
 * var boundingBox = new OG.Envelope([50, 50], 200, 100);
 *
 * @class
 * @requires OG.geometry.Coordinate
 *
 * @param {OG.geometry.Coordinate,Number[]} upperLeft 기준 좌상단 좌표
 * @param {Number} width 너비
 * @param {Number} height 높이
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Envelope = function (upperLeft, width, height) {
	var _upperLeft, _width = width, _height = height,
		_upperRight, _lowerLeft, _lowerRight, _leftCenter, _upperCenter, _rightCenter, _lowerCenter, _centroid,
		reset;

	// Array 좌표를 OG.geometry.Coordinate 로 변환
	if (upperLeft) {
		if (upperLeft.constructor === Array) {
			_upperLeft = new OG.geometry.Coordinate(upperLeft);
		} else {
			_upperLeft = new OG.geometry.Coordinate(upperLeft.x, upperLeft.y);
		}
	}

	/**
	 * 기준 좌상단 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 좌상단 좌표
	 */
	this.getUpperLeft = function () {
		return _upperLeft;
	};

	/**
	 * 주어진 좌표로 기준 좌상단 좌표를 설정한다. 새로 설정된 값으로 이동된다.
	 *
	 * @param {OG.geometry.Coordinate,Number[]} upperLeft 좌상단 좌표
	 */
	this.setUpperLeft = function (upperLeft) {
		if (upperLeft.constructor === Array) {
			upperLeft = new OG.geometry.Coordinate(upperLeft[0], upperLeft[1]);
		}

		_upperLeft = upperLeft;
		reset();
	};

	/**
	 * 우상단 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 우상단 좌표
	 */
	this.getUpperRight = function () {
		if (!_upperRight) {
			_upperRight = new OG.geometry.Coordinate(_upperLeft.x + _width, _upperLeft.y);
		}
		return _upperRight;
	};

	/**
	 * 우하단 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 우하단 좌표
	 */
	this.getLowerRight = function () {
		if (!_lowerRight) {
			_lowerRight = new OG.geometry.Coordinate(_upperLeft.x + _width, _upperLeft.y + _height);
		}
		return _lowerRight;
	};

	/**
	 * 좌하단 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 좌하단 좌표
	 */
	this.getLowerLeft = function () {
		if (!_lowerLeft) {
			_lowerLeft = new OG.geometry.Coordinate(_upperLeft.x, _upperLeft.y + _height);
		}
		return _lowerLeft;
	};

	/**
	 * 좌중간 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 좌중간 좌표
	 */
	this.getLeftCenter = function () {
		if (!_leftCenter) {
			_leftCenter = new OG.geometry.Coordinate(_upperLeft.x, OG.Util.round(_upperLeft.y + _height / 2));
		}
		return _leftCenter;
	};

	/**
	 * 상단중간 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 상단중간 좌표
	 */
	this.getUpperCenter = function () {
		if (!_upperCenter) {
			_upperCenter = new OG.geometry.Coordinate(OG.Util.round(_upperLeft.x + _width / 2), _upperLeft.y);
		}
		return _upperCenter;
	};

	/**
	 * 우중간 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 우중간 좌표
	 */
	this.getRightCenter = function () {
		if (!_rightCenter) {
			_rightCenter = new OG.geometry.Coordinate(_upperLeft.x + _width, OG.Util.round(_upperLeft.y + _height / 2));
		}
		return _rightCenter;
	};

	/**
	 * 하단중간 좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 하단중간 좌표
	 */
	this.getLowerCenter = function () {
		if (!_lowerCenter) {
			_lowerCenter = new OG.geometry.Coordinate(OG.Util.round(_upperLeft.x + _width / 2), _upperLeft.y + _height);
		}
		return _lowerCenter;
	};

	/**
	 * Envelope 의 중심좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 중심좌표
	 */
	this.getCentroid = function () {
		if (!_centroid) {
			_centroid = new OG.geometry.Coordinate(OG.Util.round(_upperLeft.x + _width / 2),
				OG.Util.round(_upperLeft.y + _height / 2));
		}

		return _centroid;
	};

	/**
	 * 주어진 좌표로 중심 좌표를 설정한다. 새로 설정된 값으로 이동된다.
	 *
	 * @param {OG.geometry.Coordinate,Number[]} centroid 중심좌표
	 */
	this.setCentroid = function (centroid) {
		if (centroid.constructor === Array) {
			centroid = new OG.geometry.Coordinate(centroid[0], centroid[1]);
		}

		this.move(centroid.x - this.getCentroid().x, centroid.y - this.getCentroid().y);
	};

	/**
	 * Envelope 의 가로 사이즈를 반환한다.
	 *
	 * @return {Number} 너비
	 */
	this.getWidth = function () {
		return _width;
	};

	/**
	 * 주어진 값으로 Envelope 의 가로 사이즈를 설정한다.
	 *
	 * @param {Number} width 너비
	 */
	this.setWidth = function (width) {
		_width = width;
		reset();
	};

	/**
	 * Envelope 의 세로 사이즈를 반환한다.
	 *
	 * @return {Number} 높이
	 */
	this.getHeight = function () {
		return _height;
	};

	/**
	 * 주어진 값으로 Envelope 의 세로 사이즈를 설정한다.
	 *
	 * @param {Number} height 높이
	 */
	this.setHeight = function (height) {
		_height = height;
		reset();
	};

	/**
	 * Envelope 모든 꼭지점을 반환한다.
	 * 좌상단좌표부터 시계방향으로 꼭지점 Array 를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array : [좌상단, 상단중간, 우상단, 우중간, 우하단, 하단중간, 좌하단, 좌중간, 좌상단]
	 */
	this.getVertices = function () {
		var vertices = [];

		vertices.push(this.getUpperLeft());
		vertices.push(this.getUpperCenter());
		vertices.push(this.getUpperRight());
		vertices.push(this.getRightCenter());
		vertices.push(this.getLowerRight());
		vertices.push(this.getLowerCenter());
		vertices.push(this.getLowerLeft());
		vertices.push(this.getLeftCenter());
		vertices.push(this.getUpperLeft());

		return vertices;
	};

	/**
	 * 주어진 좌표값이 Envelope 영역에 포함되는지 비교한다.
	 *
	 * @param {OG.geometry.Coordinate,Number[]} coordinate 좌표값
	 * @return {Boolean} true:포함, false:비포함
	 */
	this.isContains = function (coordinate) {
		if (coordinate.constructor === Array) {
			return coordinate[0] >= _upperLeft.x && coordinate[0] <= this.getLowerRight().x &&
				coordinate[1] >= _upperLeft.y && coordinate[1] <= this.getLowerRight().y;
		} else {
			return coordinate.x >= _upperLeft.x && coordinate.x <= this.getLowerRight().x &&
				coordinate.y >= _upperLeft.y && coordinate.y <= this.getLowerRight().y;
		}
	};

	/**
	 * 주어진 모든 좌표값이 Envelope 영역에 포함되는지 비교한다.
	 *
	 * @param {OG.geometry.Coordinate[]} coordinateArray 좌표값 Array
	 * @return {Boolean} true:포함, false:비포함
	 */
	this.isContainsAll = function (coordinateArray) {
		var i;
		for (i = 0; i < coordinateArray.length; i++) {
			if (!this.isContains(coordinateArray[i])) {
				return false;
			}
		}

		return true;
	};

	/**
	 * 크기는 고정한 채 가로, 세로 Offset 만큼 Envelope 을 이동한다.
	 *
	 * @param {Number} offsetX 가로 Offset
	 * @param {Number} offsetY 세로 Offset
	 * @return {OG.geometry.Envelope} 이동된 Envelope
	 */
	this.move = function (offsetX, offsetY) {
		_upperLeft.move(offsetX, offsetY);
		reset();

		return this;
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동하여 Envelope 을 리사이즈 한다.
	 *
	 * @param {Number} upper 상단 라인 이동 Offset(위 방향으로 +)
	 * @param {Number} lower 하단 라인 이동 Offset(아래 방향으로 +)
	 * @param {Number} left 좌측 라인 이동 Offset(좌측 방향으로 +)
	 * @param {Number} right 우측 라인 이동 Offset(우측 방향으로 +)
	 * @return {OG.geometry.Envelope} 리사이즈된 Envelope
	 */
	this.resize = function (upper, lower, left, right) {
		upper = upper || 0;
		lower = lower || 0;
		left = left || 0;
		right = right || 0;

		if (_width + (left + right) < 0 || _height + (upper + lower) < 0) {
			throw new OG.ParamError();
		}

		_upperLeft.move(-1 * left, -1 * upper);
		_width += (left + right);
		_height += (upper + lower);
		reset();

		return this;
	};

	/**
	 * 주어진 Envelope 영역과 같은지 비교한다.
	 *
	 * @param {OG.geometry.Envelope} Envelope 영역
	 * @return {Boolean} true:같음, false:다름
	 */
	this.isEquals = function (envelope) {
		if (envelope && envelope instanceof OG.geometry.Envelope) {
			if (this.getUpperLeft().isEquals(envelope.getUpperLeft()) &&
				this.getWidth() === envelope.getWidth() &&
				this.getHeight() === envelope.getHeight()) {
				return true;
			}
		}

		return false;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push("upperLeft:" + this.getUpperLeft());
		s.push("width:" + this.getWidth());
		s.push("height:" + this.getHeight());
		s.push("upperRight:" + this.getUpperRight());
		s.push("lowerLeft:" + this.getLowerLeft());
		s.push("lowerRight:" + this.getLowerRight());
		s.push("leftCenter:" + this.getLeftCenter());
		s.push("upperCenter:" + this.getUpperCenter());
		s.push("rightCenter:" + this.getRightCenter());
		s.push("lowerCenter:" + this.getLowerCenter());
		s.push("centroid:" + this.getCentroid());

		return "{" + s.join() + "}";
	};

	/**
	 * _upperLeft, _width, _height 를 제외한 로컬 멤버 변수의 값을 리셋한다.
	 *
	 * @private
	 */
	reset = function () {
		_upperRight = null;
		_lowerLeft = null;
		_lowerRight = null;
		_leftCenter = null;
		_upperCenter = null;
		_rightCenter = null;
		_lowerCenter = null;
		_centroid = null;
	};
};
OG.geometry.Envelope.prototype = new OG.geometry.Envelope();
OG.geometry.Envelope.prototype.constructor = OG.geometry.Envelope;
OG.Envelope = OG.geometry.Envelope;
/**
 * 공간 기하 객체(Spatial Geometry Object)의 최상위 추상 클래스
 *
 * @class
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Geometry = function () {

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.NULL;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = false;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * {OG.geometry.Envelope} 공간기하객체를 포함하는 사각형의 Boundary 영역
	 */
	this.boundary = null;

	// 다른 Geometry 객체와의 Spatial Relation 을 테스트하는 함수들

	/**
	 * 주어진 Geometry 객체와 같은지 비교한다.
	 *
	 * @param {OG.geometry.Geometry} _geometry Geometry 객체
	 * @return {Boolean} true:같음, false:다름
	 */
	this.isEquals = function (_geometry) {
		return _geometry && _geometry.toString() === this.toString();
	};

	/**
	 * 주어진 공간기하객체를 포함하는지 비교한다.
	 *
	 * @param {OG.geometry.Geometry} _geometry Geometry 객체
	 * @return {Boolean} 포함하면 true
	 */
	this.isContains = function (_geometry) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 공간기하객체에 포함되는지 비교한다.
	 *
	 * @param {OG.geometry.Geometry} _geometry Geometry 객체
	 * @return {Boolean} 포함되면 true
	 */
	this.isWithin = function (_geometry) {
		throw new OG.NotImplementedException();
	};

//	this.isDisjoint = function (_geometry) {
//		throw new OG.NotImplementedException();
//	};
//
//	this.isIntersects = function (_geometry) {
//		throw new OG.NotImplementedException();
//	};
//
//	this.isOverlaps = function (_geometry) {
//		throw new OG.NotImplementedException();
//	};
//
//	this.isTouches = function (_geometry) {
//		throw new OG.NotImplementedException();
//	};

	// 현 Geometry 객체의 Spatial Analysis 를 지원하는 함수들

	/**
	 * 공간기하객체를 포함하는 사각형의 Boundary 영역을 반환한다.
	 *
	 * @return {OG.geometry.Envelope} Envelope 영역
	 */
	this.getBoundary = function () {
		if (this.boundary === null) {
			var minX, minY, maxX, maxY, upperLeft, width, height,
				vertices = this.getVertices(), i;
			for (i = 0; i < vertices.length; i++) {
				if (i === 0) {
					minX = maxX = vertices[i].x;
					minY = maxY = vertices[i].y;
				}
				minX = vertices[i].x < minX ? vertices[i].x : minX;
				minY = vertices[i].y < minY ? vertices[i].y : minY;
				maxX = vertices[i].x > maxX ? vertices[i].x : maxX;
				maxY = vertices[i].y > maxY ? vertices[i].y : maxY;
			}
			upperLeft = new OG.geometry.Coordinate(minX, minY);
			width = maxX - minX;
			height = maxY - minY;

			this.boundary = new OG.geometry.Envelope(upperLeft, width, height);
		}

		return this.boundary;
	};

	/**
	 * 공간기하객체의 중심좌표를 반환한다.
	 *
	 * @return {OG.geometry.Coordinate} 중심좌표
	 */
	this.getCentroid = function () {
		return this.getBoundary().getCentroid();
	};

	/**
	 * 공간기하객체의 모든 꼭지점을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array
	 * @abstract
	 */
	this.getVertices = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 좌표와의 최단거리를 반환한다.
	 *
	 * @param {OG.geometry.Coordinate} _coordinate 좌표
	 * @return {Number} 최단거리
	 */
	this.minDistance = function (_coordinate) {
		var minDistance = Number.MAX_VALUE,
			distance = 0,
			vertices = this.getVertices(),
			i;

		_coordinate = this.convertCoordinate(_coordinate);

		if (vertices.length === 1) {
			return _coordinate.distance(vertices[0]);
		}

		for (i = 0; i < vertices.length - 1; i++) {
			distance = this.distanceToLine(_coordinate, [vertices[i], vertices[i + 1]]);
			if (distance < minDistance) {
				minDistance = distance;
			}
		}

		return minDistance;
	};

	/**
	 * 주어진 공간기하객체와의 중심점 간의 거리를 반환한다.
	 *
	 * @param {OG.geometry.Geometry} _geometry 공간 기하 객체
	 * @return {Number} 거리
	 */
	this.distance = function (_geometry) {
		return this.getCentroid().distance(_geometry.getCentroid());
	};

	/**
	 * 공간기하객체의 길이를 반환한다.
	 *
	 * @return {Number} 길이
	 */
	this.getLength = function () {
		var length = 0,
			vertices = this.getVertices(),
			i;
		for (i = 0; i < vertices.length - 1; i++) {
			length += vertices[i].distance(vertices[i + 1]);
		}

		return length;
	};

//	this.intersect = function (_geometry) {
//		throw new OG.NotImplementedException();
//	};
//
//	this.union = function (_geometry) {
//		throw new OG.NotImplementedException();
//	};

	// 현 Geometry 객체의 Spatial Transform 를 지원하는 함수들

	/**
	 * 가로, 세로 Offset 만큼 좌표를 이동한다.
	 *
	 * @param {Number} offsetX 가로 Offset
	 * @param {Number} offsetY 세로 Offset
	 * @return {OG.geometry.Geometry} 이동된 공간 기하 객체
	 * @abstract
	 */
	this.move = function (offsetX, offsetY) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 중심좌표로 공간기하객체를 이동한다.
	 *
	 * @param {OG.geometry.Coordinate} 중심 좌표
	 */
	this.moveCentroid = function (target) {
		var origin = this.getCentroid();
		target = new OG.geometry.Coordinate(target);

		this.move(target.x - origin.x, target.y - origin.y);
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동하여 Envelope 을 리사이즈 한다.
	 *
	 * @param {Number} upper 상단 라인 이동 Offset(위 방향으로 +)
	 * @param {Number} lower 하단 라인 이동 Offset(아래 방향으로 +)
	 * @param {Number} left 좌측 라인 이동 Offset(좌측 방향으로 +)
	 * @param {Number} right 우측 라인 이동 Offset(우측 방향으로 +)
	 * @return {OG.geometry.Geometry} 리사이즈된 공간 기하 객체
	 * @abstract
	 */
	this.resize = function (upper, lower, left, right) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 중심좌표는 고정한 채 Bounding Box 의 width, height 를 리사이즈 한다.
	 *
	 * @param {Number} width 너비
	 * @param {Number} height 높이
	 * @return {OG.geometry.Geometry} 리사이즈된 공간 기하 객체
	 */
	this.resizeBox = function (width, height) {
		var boundary = this.getBoundary(),
			offsetWidth = OG.Util.round((width - boundary.getWidth()) / 2),
			offsetHeight = OG.Util.round((height - boundary.getHeight()) / 2);

		this.resize(offsetHeight, offsetHeight, offsetWidth, offsetWidth);

		return this;
	};

	/**
	 * 기준 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Number} angle 회전 각도
	 * @param {OG.geometry.Coordinate} origin 기준 좌표(default:중심좌표)
	 * @return {OG.geometry.Geometry} 회전된 공간 기하 객체
	 * @abstract
	 */
	this.rotate = function (angle, origin) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 Boundary 영역 안으로 공간 기하 객체를 적용한다.(이동 & 리사이즈)
	 *
	 * @param {OG.geometry.Envelope} envelope Envelope 영역
	 * @return {OG.geometry.Geometry} 적용된 공간 기하 객체
	 */
	this.fitToBoundary = function (envelope) {
		var boundary = this.getBoundary(),
			upper = boundary.getUpperCenter().y - envelope.getUpperCenter().y,
			lower = envelope.getLowerCenter().y - boundary.getLowerCenter().y,
			left = boundary.getLeftCenter().x - envelope.getLeftCenter().x,
			right = envelope.getRightCenter().x - boundary.getRightCenter().x;

		this.resize(upper, lower, left, right);

		return this;
	};

	// 유틸리티 함수들

	/**
	 * 파라미터가 [x, y] 형식의 좌표 Array 이면 OG.geometry.Coordinate 인스턴스를 new 하여 반환한다.
	 *
	 * @param {OG.geometry.Coordinate,Number[]} coordinate [x, y] 형식의 좌표 Array 또는 OG.geometry.Coordinate 인스턴스
	 * @return {OG.geometry.Coordinate}
	 */
	this.convertCoordinate = function (coordinate) {
		// Array 좌표를 OG.geometry.Coordinate 로 변환
		if (coordinate) {
			if (coordinate.constructor === Array) {
				return new OG.geometry.Coordinate(coordinate);
			} else if (coordinate instanceof OG.geometry.Coordinate) {
				return new OG.geometry.Coordinate(coordinate.x, coordinate.y);
			} else {
				throw new OG.ParamError();
			}
		} else {
			return undefined;
		}
	};

	/**
	 * 포인트 P 로부터 라인 AB의 거리를 계산한다.
	 * Note: NON-ROBUST!
	 *
	 * @param {OG.geometry.Coordinate,Number[]} p 기준좌표
	 * @param {OG.geometry.Coordinate[]} line 라인 시작좌표, 끝좌표 Array
	 * @return {Number} 거리
	 */
	this.distanceToLine = function (p, line) {
		var A = this.convertCoordinate(line[0]),
			B = this.convertCoordinate(line[1]),
			r, s;
		p = this.convertCoordinate(p);

		// if start==end, then use pt distance
		if (A.isEquals(B)) {
			return p.distance(A);
		}

		// otherwise use comp.graphics.algorithms Frequently Asked Questions method
		//	(1)				AC dot AB
		//			   r = -----------
		//					||AB||^2
		//	r has the following meaning:
		//	r=0 P = A
		//	r=1 P = B
		//	r<0 P is on the backward extension of AB
		//	r>1 P is on the forward extension of AB
		//	0<r<1 P is interior to AB

		r = ((p.x - A.x) * (B.x - A.x) + (p.y - A.y) * (B.y - A.y)) /
			((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));

		if (r <= 0.0) {
			return p.distance(A);
		}
		if (r >= 1.0) {
			return p.distance(B);
		}

		// (2)
		//		 (Ay-Cy)(Bx-Ax)-(Ax-Cx)(By-Ay)
		//	s = -----------------------------
		//					L^2
		//
		//	Then the distance from C to P = |s|*L.

		s = ((A.y - p.y) * (B.x - A.x) - (A.x - p.x) * (B.y - A.y)) /
			((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));

		return OG.Util.round(Math.abs(s) *
			Math.sqrt(((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y))));
	};

	/**
	 * 라인1 로부터 라인2 의 거리를 계산한다.
	 * Note: NON-ROBUST!
	 *
	 * @param {OG.geometry.Coordinate[]} line1 line1 라인 시작좌표, 끝좌표 Array
	 * @param {OG.geometry.Coordinate[]} line2 line2 라인 시작좌표, 끝좌표 Array
	 * @return {Number} 거리
	 */
	this.distanceLineToLine = function (line1, line2) {
		var A = this.convertCoordinate(line1[0]),
			B = this.convertCoordinate(line1[1]),
			C = this.convertCoordinate(line2[0]),
			D = this.convertCoordinate(line2[1]),
			r_top, r_bot, s_top, s_bot, s, r;

		// check for zero-length segments
		if (A.isEquals(B)) {
			return this.distanceToLine(A, [C, D]);
		}
		if (C.isEquals(D)) {
			return this.distanceToLine(D, [A, B]);
		}

		// AB and CD are line segments
		//   from comp.graphics.algo
		//
		//  Solving the above for r and s yields
		//				(Ay-Cy)(Dx-Cx)-(Ax-Cx)(Dy-Cy)
		//			r = ----------------------------- (eqn 1)
		//				(Bx-Ax)(Dy-Cy)-(By-Ay)(Dx-Cx)
		//
		//			(Ay-Cy)(Bx-Ax)-(Ax-Cx)(By-Ay)
		//		s = ----------------------------- (eqn 2)
		//			(Bx-Ax)(Dy-Cy)-(By-Ay)(Dx-Cx)
		//	Let P be the position vector of the intersection point, then
		//		P=A+r(B-A) or
		//		Px=Ax+r(Bx-Ax)
		//		Py=Ay+r(By-Ay)
		//	By examining the values of r & s, you can also determine some other
		//	limiting conditions:
		//		If 0<=r<=1 & 0<=s<=1, intersection exists
		//		r<0 or r>1 or s<0 or s>1 line segments do not intersect
		//		If the denominator in eqn 1 is zero, AB & CD are parallel
		//		If the numerator in eqn 1 is also zero, AB & CD are collinear.
		r_top = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
		r_bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);
		s_top = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);
		s_bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);

		if ((r_bot === 0) || (s_bot === 0)) {
			return Math.min(this.distanceToLine(A, [C, D]),
				Math.min(this.distanceToLine(B, [C, D]),
					Math.min(this.distanceToLine(C, [A, B]), this.distanceToLine(D, [A, B]))));

		}
		s = s_top / s_bot;
		r = r_top / r_bot;

		if ((r < 0) || (r > 1) || (s < 0) || (s > 1)) {
			//no intersection
			return Math.min(this.distanceToLine(A, [C, D]),
				Math.min(this.distanceToLine(B, [C, D]),
					Math.min(this.distanceToLine(C, [A, B]), this.distanceToLine(D, [A, B]))));
		}

		//intersection exists
		return 0;
	};

	/**
	 * 주어진 라인과 교차하는 좌표를 반환한다.
	 *
	 * @param {OG.geometry.Coordinate[]} line 라인 시작좌표, 끝좌표 Array
	 * @return {OG.geometry.Coordinate[]}
	 */
	this.intersectToLine = function (line) {
		var vertices = this.getVertices(), result = [], point, i,
			contains = function (coordinateArray, coordinate) {
				var k;
				for (k = 0; k < coordinateArray.length; k++) {
					if (coordinateArray[k].isEquals(coordinate)) {
						return true;
					}
				}
				return false;
			};

		for (i = 0; i < vertices.length - 1; i++) {
			point = this.intersectLineToLine(line, [vertices[i], vertices[i + 1]]);
			if (point && !contains(result, point)) {
				result.push(point);
			}
		}

		return result;
	};

	/**
	 * 라인1 로부터 라인2 의 교차점을 계산한다.
	 *
	 * @param {OG.geometry.Coordinate[]} line1 line1 라인 시작좌표, 끝좌표 Array
	 * @param {OG.geometry.Coordinate[]} line2 line2 라인 시작좌표, 끝좌표 Array
	 * @return {OG.geometry.Coordinate} 교차점
	 */
	this.intersectLineToLine = function (line1, line2) {
		var A = this.convertCoordinate(line1[0]),
			B = this.convertCoordinate(line1[1]),
			C = this.convertCoordinate(line2[0]),
			D = this.convertCoordinate(line2[1]),
			result,
			resultText,
			r_top, r_bot, s_top, s_bot, r, s;

		// check for zero-length segments
		if (A.isEquals(B)) {
			return this.distanceToLine(A, [C, D]) === 0 ? A : undefined;
		}
		if (C.isEquals(D)) {
			return this.distanceToLine(C, [A, B]) === 0 ? C : undefined;
		}

		// AB and CD are line segments
		//   from comp.graphics.algo
		//
		//  Solving the above for r and s yields
		//				(Ay-Cy)(Dx-Cx)-(Ax-Cx)(Dy-Cy)
		//			r = ----------------------------- (eqn 1)
		//				(Bx-Ax)(Dy-Cy)-(By-Ay)(Dx-Cx)
		//
		//			(Ay-Cy)(Bx-Ax)-(Ax-Cx)(By-Ay)
		//		s = ----------------------------- (eqn 2)
		//			(Bx-Ax)(Dy-Cy)-(By-Ay)(Dx-Cx)
		//	Let P be the position vector of the intersection point, then
		//		P=A+r(B-A) or
		//		Px=Ax+r(Bx-Ax)
		//		Py=Ay+r(By-Ay)
		//	By examining the values of r & s, you can also determine some other
		//	limiting conditions:
		//		If 0<=r<=1 & 0<=s<=1, intersection exists
		//		r<0 or r>1 or s<0 or s>1 line segments do not intersect
		//		If the denominator in eqn 1 is zero, AB & CD are parallel
		//		If the numerator in eqn 1 is also zero, AB & CD are collinear.
		r_top = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
		r_bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);
		s_top = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);
		s_bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);

		if (r_bot !== 0 && s_bot !== 0) {
			r = r_top / r_bot;
			s = s_top / s_bot;
			if (0 <= r && r <= 1 && 0 <= s && s <= 1) {
				resultText = "Intersection";
				result = new OG.Coordinate(OG.Util.round(A.x + r * (B.x - A.x)), OG.Util.round(A.y + r * (B.y - A.y)));
			} else {
				resultText = "No Intersection";
			}
		} else {
			if (r_top === 0 || s_top === 0) {
				resultText = "Coincident";
			} else {
				resultText = "Parallel";
			}
		}

		return result;
	};

	/**
	 * 라인1 로부터 라인2 의 교차점을 계산한다.
	 *
	 * @param {OG.geometry.Coordinate} center 중심점
	 * @param {Number} radius 반경
	 * @param {OG.geometry.Coordinate} from line 라인 시작좌표
	 * @param {OG.geometry.Coordinate} to line 라인 끝좌표
	 * @return {OG.geometry.Coordinate[]} 교차점
	 */
	this.intersectCircleToLine = function (center, radius, from, to) {
		var result = [],
			a = (to.x - from.x) * (to.x - from.x) +
				(to.y - from.y) * (to.y - from.y),
			b = 2 * ( (to.x - from.x) * (from.x - center.x) +
				(to.y - from.y) * (from.y - center.y)   ),
			cc = center.x * center.x + center.y * center.y + from.x * from.x + from.y * from.y -
				2 * (center.x * from.x + center.y * from.y) - radius * radius,
			deter = b * b - 4 * a * cc,
			resultText,
			lerp = function (from, to, t) {
				return new OG.Coordinate(
					OG.Util.round(from.x + (to.x - from.x) * t),
					OG.Util.round(from.y + (to.y - from.y) * t)
				);
			},
			e, u1, u2;

		if (deter < 0) {
			resultText = "Outside";
		} else if (deter === 0) {
			resultText = "Tangent";
			// NOTE: should calculate this point
		} else {
			e = Math.sqrt(deter);
			u1 = (-b + e) / (2 * a);
			u2 = (-b - e) / (2 * a);

			if ((u1 < 0 || u1 > 1) && (u2 < 0 || u2 > 1)) {
				if ((u1 < 0 && u2 < 0) || (u1 > 1 && u2 > 1)) {
					resultText = "Outside";
				} else {
					resultText = "Inside";
				}
			} else {
				resultText = "Intersection";

				if (0 <= u1 && u1 <= 1) {
					result.push(lerp(from, to, u1));
				}

				if (0 <= u2 && u2 <= 1) {
					result.push(lerp(from, to, u2));
				}
			}
		}

		return result;
	};

	/**
	 * 저장된 boundary 를 클리어하여 새로 계산하도록 한다.
	 */
	this.reset = function () {
		this.boundary = null;
	};
};
OG.geometry.Geometry.prototype = new OG.geometry.Geometry();
OG.geometry.Geometry.prototype.constructor = OG.geometry.Geometry;
/**
 * PolyLine 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Geometry
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate[]} vertices Line Vertex 좌표 Array
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.PolyLine = function (vertices) {

	var i;

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.POLYLINE;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = false;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * {OG.geometry.Coordinate[]} Line Vertex 좌표 Array
	 */
	this.vertices = [];

	// Array 좌표를 OG.geometry.Coordinate 로 변환
	if (vertices && vertices.length > 0) {
		for (i = 0; i < vertices.length; i++) {
			this.vertices.push(this.convertCoordinate(vertices[i]));
		}
	}

	/**
	 * 공간기하객체의 모든 꼭지점을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array
	 * @override
	 */
	this.getVertices = function () {
		return this.vertices;
	};

	/**
	 * 가로, 세로 Offset 만큼 좌표를 이동한다.
	 *
	 * @param {Number} offsetX 가로 Offset
	 * @param {Number} offsetY 세로 Offset
	 * @return {OG.geometry.Geometry} 이동된 공간 기하 객체
	 * @override
	 */
	this.move = function (offsetX, offsetY) {
		this.getBoundary().move(offsetX, offsetY);
		for (i = 0; i < this.vertices.length; i++) {
			this.vertices[i].move(offsetX, offsetY);
		}

		return this;
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동하여 Envelope 을 리사이즈 한다.
	 *
	 * @param {Number} upper 상단 라인 이동 Offset(위 방향으로 +)
	 * @param {Number} lower 하단 라인 이동 Offset(아래 방향으로 +)
	 * @param {Number} left 좌측 라인 이동 Offset(좌측 방향으로 +)
	 * @param {Number} right 우측 라인 이동 Offset(우측 방향으로 +)
	 * @return {OG.geometry.Geometry} 리사이즈된 공간 기하 객체
	 * @override
	 */
	this.resize = function (upper, lower, left, right) {
		var boundary = this.getBoundary(),
			offsetX = left + right,
			offsetY = upper + lower,
			width = boundary.getWidth() + offsetX,
			height = boundary.getHeight() + offsetY,
			rateWidth = boundary.getWidth() === 0 ? 1 : width / boundary.getWidth(),
			rateHeight = boundary.getHeight() === 0 ? 1 : height / boundary.getHeight(),
			upperLeft = boundary.getUpperLeft();

		if (width < 0 || height < 0) {
			throw new OG.ParamError();
		}

		for (i = 0; i < this.vertices.length; i++) {
			this.vertices[i].x = OG.Util.round((upperLeft.x - left) + (this.vertices[i].x - upperLeft.x) * rateWidth);
			this.vertices[i].y = OG.Util.round((upperLeft.y - upper) + (this.vertices[i].y - upperLeft.y) * rateHeight);
		}
		boundary.resize(upper, lower, left, right);

		return this;
	};

	/**
	 * 기준 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Number} angle 회전 각도
	 * @param {OG.geometry.Coordinate} origin 기준 좌표
	 * @return {OG.geometry.Geometry} 회전된 공간 기하 객체
	 * @override
	 */
	this.rotate = function (angle, origin) {
		origin = origin || this.getCentroid();
		for (i = 0; i < this.vertices.length; i++) {
			this.vertices[i].rotate(angle, origin);
		}
		this.reset();

		return this;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
		s.push("vertices:[" + this.vertices + "]");

		return "{" + s.join() + "}";
	};
};
OG.geometry.PolyLine.prototype = new OG.geometry.Geometry();
OG.geometry.PolyLine.prototype.constructor = OG.geometry.PolyLine;
OG.PolyLine = OG.geometry.PolyLine;
/**
 * Catmull-Rom Spline Curve 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Geometry
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry, OG.common.CurveUtil
 *
 * @param {OG.geometry.Coordinate[]} controlPoints Curve Vertex 좌표 Array
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Curve = function (controlPoints) {

	OG.geometry.Curve.superclass.call(this, controlPoints);

	var t, cmRomSpline = OG.CurveUtil.CatmullRomSpline(eval("[" + this.vertices.toString() + "]"));

	// t 는 0 ~ maxT 의 값으로, t 값의 증분값이 작을수록 세밀한 Curve 를 그린다.
	this.vertices = [];
	for (t = 0; t <= cmRomSpline.maxT; t += 0.1) {
		this.vertices.push(new OG.geometry.Coordinate(
			OG.Util.round(cmRomSpline.getX(t)),
			OG.Util.round(cmRomSpline.getY(t))
		));
	}

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.CURVE;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = false;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * 공간기하객체의 모든 꼭지점을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array
	 * @override
	 */
	this.getVertices = function () {
		var vertices = [], i;
		for (i = 10; i <= this.vertices.length - 10; i++) {
			vertices.push(this.vertices[i]);
		}

		return vertices;
	};

	/**
	 * 콘트롤 포인트 목록을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} controlPoints Array
	 */
	this.getControlPoints = function () {
		var controlPoints = [], i;
		for (i = 10; i <= this.vertices.length - 10; i += 10) {
			controlPoints.push(this.vertices[i]);
		}

		return controlPoints;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
		s.push("vertices:[" + this.getVertices() + "]");
		s.push("controlPoints:[" + this.getControlPoints() + "]");

		return "{" + s.join() + "}";
	};
};
OG.geometry.Curve.prototype = new OG.geometry.PolyLine();
OG.geometry.Curve.superclass = OG.geometry.PolyLine;
OG.geometry.Curve.prototype.constructor = OG.geometry.Curve;
OG.Curve = OG.geometry.Curve;
/**
 * Ellipse 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Curve
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate} center Ellipse 중심 좌표
 * @param {Number} radiusX X축 반경
 * @param {Number} radiusY Y축 반경
 * @param {Number} angle X축 기울기
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Ellipse = function (center, radiusX, radiusY, angle) {

	var _center = this.convertCoordinate(center),
		_angle = angle || 0,
		theta,
		i,
		controlPoints = [];

	if (_center) {
		for (i = -45; i <= 405; i += 45) {
			theta = Math.PI / 180 * i;
			controlPoints.push((new OG.geometry.Coordinate(
				OG.Util.round(_center.x + radiusX * Math.cos(theta)),
				OG.Util.round(_center.y + radiusY * Math.sin(theta))
			)).rotate(_angle, _center));
		}
	}

	OG.geometry.Ellipse.superclass.call(this, controlPoints);

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.ELLIPSE;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = true;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * 공간기하객체의 모든 꼭지점을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array
	 * @override
	 */
	this.getVertices = function () {
		var vertices = [];
		for (i = 20; i < this.vertices.length - 20; i++) {
			vertices.push(this.vertices[i]);
		}

		return vertices;
	};

	/**
	 * 콘트롤 포인트 목록을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} controlPoints Array
	 */
	this.getControlPoints = function () {
		var controlPoints = [];
		for (i = 10; i <= this.vertices.length - 10; i += 10) {
			controlPoints.push(this.vertices[i]);
		}

		return controlPoints;
	};

	/**
	 * 공간기하객체의 길이를 반환한다.
	 *
	 * @return {Number} 길이
	 * @override
	 */
	this.getLength = function () {
		// π{5(a+b)/4 - ab/(a+b)}
		var controlPoints = this.getControlPoints(),
			radiusX = center.distance(controlPoints[1]),
			radiusY = center.distance(controlPoints[3]);
		return Math.PI * (5 * (radiusX + radiusY) / 4 - radiusX * radiusY / (radiusX + radiusY));
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [],
			controlPoints = this.getControlPoints(),
			center = this.getCentroid(),
			radiusX = center.distance(controlPoints[1]),
			radiusY = center.distance(controlPoints[3]),
			angle = OG.Util.round(Math.atan2(controlPoints[1].y - center.y, controlPoints[1].x - center.x) * 180 / Math.PI);

		s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
		s.push("center:" + center);
		s.push("radiusX:" + radiusX);
		s.push("radiusY:" + radiusY);
		s.push("angle:" + angle);

		return "{" + s.join() + "}";
	};
};
OG.geometry.Ellipse.prototype = new OG.geometry.Curve();
OG.geometry.Ellipse.superclass = OG.geometry.Curve;
OG.geometry.Ellipse.prototype.constructor = OG.geometry.Ellipse;
OG.Ellipse = OG.geometry.Ellipse;
/**
 * Circle 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Ellipse
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate} center Circle 중심 좌표
 * @param {Number} radius radius 반경
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Circle = function (center, radius) {

	OG.geometry.Circle.superclass.call(this, center, radius, radius, 0);

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.CIRCLE;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = true;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * 공간기하객체의 길이를 반환한다.
	 *
	 * @return {Number} 길이
	 * @override
	 */
	this.getLength = function () {
		var controlPoints = this.getControlPoints(),
			radiusX = center.distance(controlPoints[1]);
		return 2 * Math.PI * radiusX;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [],
			controlPoints = this.getControlPoints(),
			center = this.getCentroid(),
			radiusX = center.distance(controlPoints[1]),
			radiusY = center.distance(controlPoints[3]),
			angle = OG.Util.round(Math.atan2(controlPoints[1].y - center.y, controlPoints[1].x - center.x) * 180 / Math.PI);

		if (radiusX === radiusY) {
			s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
			s.push("center:" + center);
			s.push("radius:" + radiusX);
		} else {
			s.push("type:'" + OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE] + "'");
			s.push("center:" + center);
			s.push("radiusX:" + radiusX);
			s.push("radiusY:" + radiusY);
			s.push("angle:" + angle);
		}

		return "{" + s.join() + "}";
	};
};
OG.geometry.Circle.prototype = new OG.geometry.Ellipse();
OG.geometry.Circle.superclass = OG.geometry.Ellipse;
OG.geometry.Circle.prototype.constructor = OG.geometry.Circle;
OG.Circle = OG.geometry.Circle;
/**
 * 공간 기하 객체(Spatial Geometry Object) Collection
 *
 * @class
 * @extends OG.geometry.Geometry
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param geometries {OG.geometry.Geometry[]} 공간 기하 객체 Array
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.GeometryCollection = function (geometries) {

	var i, j;

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.COLLECTION;

	/**
	 * {OG.geometry.Geometry[]} 공간 기하 객체 Array
	 */
	this.geometries = geometries;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = false;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * 공간기하객체의 모든 꼭지점을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array
	 * @override
	 */
	this.getVertices = function () {
		var vertices = [], _vertices;
		for (i = 0; i < this.geometries.length; i++) {
			_vertices = this.geometries[i].getVertices();
			for (j = 0; j < _vertices.length; j++) {
				vertices.push(_vertices[j]);
			}
		}

		return vertices;
	};

	/**
	 * 가로, 세로 Offset 만큼 좌표를 이동한다.
	 *
	 * @param {Number} offsetX 가로 Offset
	 * @param {Number} offsetY 세로 Offset
	 * @return {OG.geometry.Geometry} 이동된 공간 기하 객체
	 * @override
	 */
	this.move = function (offsetX, offsetY) {
		var i;
		this.getBoundary().move(offsetX, offsetY);
		for (i = 0; i < this.geometries.length; i++) {
			this.geometries[i].move(offsetX, offsetY);
		}

		return this;
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동하여 Envelope 을 리사이즈 한다.
	 *
	 * @param {Number} upper 상단 라인 이동 Offset(위 방향으로 +)
	 * @param {Number} lower 하단 라인 이동 Offset(아래 방향으로 +)
	 * @param {Number} left 좌측 라인 이동 Offset(좌측 방향으로 +)
	 * @param {Number} right 우측 라인 이동 Offset(우측 방향으로 +)
	 * @return {OG.geometry.Geometry} 리사이즈된 공간 기하 객체
	 * @override
	 */
	this.resize = function (upper, lower, left, right) {
		var boundary = this.getBoundary(),
			offsetX = left + right,
			offsetY = upper + lower,
			width = boundary.getWidth() + offsetX,
			height = boundary.getHeight() + offsetY,
			rateWidth = boundary.getWidth() === 0 ? 1 : width / boundary.getWidth(),
			rateHeight = boundary.getHeight() === 0 ? 1 : height / boundary.getHeight(),
			upperLeft = boundary.getUpperLeft(),
			vertices;

		if (width < 0 || height < 0) {
			throw new OG.ParamError();
		}

		for (i = 0; i < this.geometries.length; i++) {
			vertices = this.geometries[i].getVertices();
			for (j = 0; j < vertices.length; j++) {
				vertices[j].x = OG.Util.round((upperLeft.x - left) + (vertices[j].x - upperLeft.x) * rateWidth);
				vertices[j].y = OG.Util.round((upperLeft.y - upper) + (vertices[j].y - upperLeft.y) * rateHeight);
			}
		}
		boundary.resize(upper, lower, left, right);
	};

	/**
	 * 중심좌표는 고정한 채 Bounding Box 의 width, height 를 리사이즈 한다.
	 *
	 * @param {Number} width 너비
	 * @param {Number} height 높이
	 */
	this.resizeBox = function (width, height) {
		var boundary = this.getBoundary(),
			offsetWidth = OG.Util.round((width - boundary.getWidth()) / 2),
			offsetHeight = OG.Util.round((height - boundary.getHeight()) / 2);

		this.resize(offsetHeight, offsetHeight, offsetWidth, offsetWidth);

		return this;
	};

	/**
	 * 기준 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Number} angle 회전 각도
	 * @param {OG.geometry.Coordinate} origin 기준 좌표(default:중심좌표)
	 * @return {OG.geometry.Geometry} 회전된 공간 기하 객체
	 * @override
	 */
	this.rotate = function (angle, origin) {
		origin = origin || this.getCentroid();
		for (i = 0; i < this.geometries.length; i++) {
			this.geometries[i].rotate(angle, origin);
		}
		this.reset();

		return this;
	};

	/**
	 * 주어진 Boundary 영역 안으로 공간 기하 객체를 적용한다.(이동 & 리사이즈)
	 *
	 * @param {OG.geometry.Envelope} envelope Envelope 영역
	 * @return {OG.geometry.Geometry} 적용된 공간 기하 객체
	 * @override
	 */
	this.fitToBoundary = function (envelope) {
		var boundary = this.getBoundary(),
			upper = boundary.getUpperCenter().y - envelope.getUpperCenter().y,
			lower = envelope.getLowerCenter().y - boundary.getLowerCenter().y,
			left = boundary.getLeftCenter().x - envelope.getLeftCenter().x,
			right = envelope.getRightCenter().x - boundary.getRightCenter().x;

		this.resize(upper, lower, left, right);

		return this;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];

		for (i = 0; i < this.geometries.length; i++) {
			s.push(this.geometries[i].toString());
		}

		return "{type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "',geometries:[" + s.join() + "]}";
	};
};
OG.geometry.GeometryCollection.prototype = new OG.geometry.Geometry();
OG.geometry.GeometryCollection.prototype.constructor = OG.geometry.GeometryCollection;
OG.GeometryCollection = OG.geometry.GeometryCollection;
/**
 * Line 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Geometry
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate} from 라인 시작 좌표값
 * @param {OG.geometry.Coordinate} to 라인 끝 좌표값
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Line = function (from, to) {

	var _from = this.convertCoordinate(from),
		_to = this.convertCoordinate(to);

	OG.geometry.Line.superclass.call(this, [
		[_from.x, _from.y],
		[_to.x, _to.y]
	]);

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.LINE;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = false;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
		s.push("from:" + this.vertices[0]);
		s.push("to:" + this.vertices[1]);

		return "{" + s.join() + "}";
	};
};
OG.geometry.Line.prototype = new OG.geometry.PolyLine();
OG.geometry.Line.superclass = OG.geometry.PolyLine;
OG.geometry.Line.prototype.constructor = OG.geometry.Line;
OG.Line = OG.geometry.Line;
/**
 * Point 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Geometry
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate} coordinate 좌표값
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Point = function (coordinate) {

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.POINT;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = false;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * {OG.geometry.Coordinate} 좌표값
	 */
	this.coordinate = this.convertCoordinate(coordinate);

	/**
	 * {OG.geometry.Coordinate[]} Line Vertex 좌표 Array
	 */
	this.vertices = [this.coordinate];

	/**
	 * 공간기하객체의 모든 꼭지점을 반환한다.
	 *
	 * @return {OG.geometry.Coordinate[]} 꼭지점 좌표 Array
	 * @override
	 */
	this.getVertices = function () {
		return this.vertices;
	};

	/**
	 * 가로, 세로 Offset 만큼 좌표를 이동한다.
	 *
	 * @param {Number} offsetX 가로 Offset
	 * @param {Number} offsetY 세로 Offset
	 * @return {OG.geometry.Geometry} 이동된 공간 기하 객체
	 * @override
	 */
	this.move = function (offsetX, offsetY) {
		this.getBoundary().move(offsetX, offsetY);
		this.coordinate.move(offsetX, offsetY);
		this.vertices = [this.coordinate];

		return this;
	};

	/**
	 * 주어진 중심좌표로 공간기하객체를 이동한다.
	 *
	 * @param {OG.geometry.Coordinate} 중심 좌표
	 */
	this.moveCentroid = function (target) {
		this.getBoundary().setUpperLeft(target);
		this.coordinate = new OG.geometry.Coordinate(target);
		this.vertices = [this.coordinate];
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동하여 Envelope 을 리사이즈 한다.
	 *
	 * @param {Number} upper 상단 라인 이동 Offset(위 방향으로 +)
	 * @param {Number} lower 하단 라인 이동 Offset(아래 방향으로 +)
	 * @param {Number} left 좌측 라인 이동 Offset(좌측 방향으로 +)
	 * @param {Number} right 우측 라인 이동 Offset(우측 방향으로 +)
	 * @return {OG.geometry.Geometry} 리사이즈된 공간 기하 객체
	 * @override
	 */
	this.resize = function (upper, lower, left, right) {
		var boundary = this.getBoundary();
		boundary.resize(upper, lower, left, right);

		this.coordinate = boundary.getCentroid();
		this.vertices = [this.coordinate];
		this.boundary = new OG.Envelope(this.coordinate, 0, 0);

		return this;
	};

	/**
	 * 중심좌표는 고정한 채 Bounding Box 의 width, height 를 리사이즈 한다.
	 *
	 * @param {Number} width 너비
	 * @param {Number} height 높이
	 * @return {OG.geometry.Geometry} 리사이즈된 공간 기하 객체
	 * @override
	 */
	this.resizeBox = function (width, height) {
		return this;
	};

	/**
	 * 기준 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Number} angle 회전 각도
	 * @param {OG.geometry.Coordinate} origin 기준 좌표
	 * @return {OG.geometry.Geometry} 회전된 공간 기하 객체
	 * @override
	 */
	this.rotate = function (angle, origin) {
		origin = origin || this.getCentroid();

		this.coordinate.rotate(angle, origin);
		this.vertices = [this.coordinate];
		this.reset();

		return this;
	};

	/**
	 * 주어진 Boundary 영역 안으로 공간 기하 객체를 적용한다.(이동 & 리사이즈)
	 *
	 * @param {OG.geometry.Envelope} envelope Envelope 영역
	 * @return {OG.geometry.Geometry} 적용된 공간 기하 객체
	 * @override
	 */
	this.fitToBoundary = function (envelope) {
		this.coordinate = envelope.getCentroid();
		this.vertices = [this.coordinate];
		this.boundary = new OG.Envelope(this.coordinate, 0, 0);

		return this;
	};

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
		s.push("coordinate:" + this.coordinate);

		return "{" + s.join() + "}";
	};
};
OG.geometry.Point.prototype = new OG.geometry.Geometry();
OG.geometry.Point.prototype.constructor = OG.geometry.Point;
OG.Point = OG.geometry.Point;
/**
 * Polygon 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.PolyLine
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate[]} vertices Line Vertex 좌표 Array
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Polygon = function (vertices) {

	OG.geometry.Polygon.superclass.call(this, vertices);

	// Polygon 은 첫번째 좌표와 마지막 좌표가 같음
	if (this.vertices.length > 0 && !this.vertices[0].isEquals(this.vertices[this.vertices.length - 1])) {
		this.vertices.push(new OG.geometry.Coordinate(this.vertices[0].x, this.vertices[0].y));
	}

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.POLYGON;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = true;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();
};
OG.geometry.Polygon.prototype = new OG.geometry.PolyLine();
OG.geometry.Polygon.superclass = OG.geometry.PolyLine;
OG.geometry.Polygon.prototype.constructor = OG.geometry.Polygon;
OG.Polygon = OG.geometry.Polygon;
/**
 * Rectangle 공간 기하 객체(Spatial Geometry Object)
 *
 * @class
 * @extends OG.geometry.Geometry
 * @requires OG.geometry.Coordinate, OG.geometry.Envelope, OG.geometry.Geometry
 *
 * @param {OG.geometry.Coordinate} upperLeft 좌상단좌표
 * @param {Number} width 너비
 * @param {Number} height 높이
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.geometry.Rectangle = function (upperLeft, width, height) {

	var _upperLeft = this.convertCoordinate(upperLeft),
		_lowerRight = this.convertCoordinate([_upperLeft.x + width, _upperLeft.y + height]);

	// 파라미터 유효성 체크
	if (_upperLeft.x > _lowerRight.x || _upperLeft.y > _lowerRight.y) {
		throw new OG.ParamError();
	}

	OG.geometry.Rectangle.superclass.call(this, [
		[_upperLeft.x, _upperLeft.y],
		[_upperLeft.x + (_lowerRight.x - _upperLeft.x), _upperLeft.y],
		[_lowerRight.x, _lowerRight.y],
		[_upperLeft.x, _upperLeft.y + (_lowerRight.y - _upperLeft.y)],
		[_upperLeft.x, _upperLeft.y]
	]);

	/**
	 * {Number} 공간 기하 객체 타입
	 */
	this.TYPE = OG.Constants.GEOM_TYPE.RECTANGLE;

	/**
	 * {Boolean} 닫힌 기하 객체 인지 여부
	 */
	this.IS_CLOSED = true;

	/**
	 * {OG.geometry.Style} 스타일 속성
	 */
	this.style = new OG.geometry.Style();

	/**
	 * 객체 프라퍼티 정보를 JSON 스트링으로 반환한다.
	 *
	 * @return {String} 프라퍼티 정보
	 * @override
	 */
	this.toString = function () {
		var s = [];
		s.push("type:'" + OG.Constants.GEOM_NAME[this.TYPE] + "'");
		s.push("upperLeft:" + this.vertices[0]);
		s.push("width:" + (this.vertices[2].x - this.vertices[0].x));
		s.push("height:" + (this.vertices[2].y - this.vertices[0].y));

		return "{" + s.join() + "}";
	};
};
OG.geometry.Rectangle.prototype = new OG.geometry.Polygon();
OG.geometry.Rectangle.superclass = OG.geometry.Polygon;
OG.geometry.Rectangle.prototype.constructor = OG.geometry.Rectangle;
OG.Rectangle = OG.geometry.Rectangle;
/**
 * 도형, 텍스트, 이미지 등의 드로잉 될 Object 의 정보를 저장하는 Shape 정보 최상위 인터페이스
 *
 * @class
 * @requires OG.common.*, OG.geometry.*
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.IShape = function () {
	this.TYPE = OG.Constants.NODE_TYPE.SHAPE;

	this.SHAPE_ID = 'OG.shape.IShape';

	this.geom = null;

	this.label = null;

	this.isCollapsed = false;

	this.createTerminal = function () {
		return [];
	};

	/**
	 * 드로잉할 Shape 를 생성하여 반환한다.
	 *
	 * @return {*} Shape 정보
	 * @abstract
	 */
	this.createShape = function () {
		throw new OG.NotImplementedException("OG.shape.IShape.createShape");
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @abstract
	 */
	this.clone = function () {
		throw new OG.NotImplementedException("OG.shape.IShape.clone");
	};
};
OG.shape.IShape.prototype = new OG.shape.IShape();
OG.shape.IShape.prototype.constructor = OG.shape.IShape;
OG.IShape = OG.shape.IShape;
/**
 * Shape 의 Edge 연결 포인트 정보
 *
 * @class
 * @requires OG.common.*, OG.geometry.*
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.Terminal = function (position, direction, inout) {
	this.position = position;
	this.direction = direction || OG.Constants.TERMINAL_TYPE.E;
	this.inout = inout || OG.Constants.TERMINAL_TYPE.INOUT;
};
OG.shape.Terminal.prototype = new OG.shape.Terminal();
OG.shape.Terminal.prototype.constructor = OG.shape.Terminal;
OG.Terminal = OG.shape.Terminal;
/**
 * Geometry Shape
 *
 * @class
 * @extends OG.shape.IShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.GeomShape = function () {
	this.TYPE = OG.Constants.SHAPE_TYPE.GEOM;

	this.SHAPE_ID = 'OG.shape.GeomShape';

	this.createTerminal = function () {
		if (!this.geom) {
			return [];
		}

		var envelope = this.geom.getBoundary();

		return [
			new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getRightCenter(), OG.Constants.TERMINAL_TYPE.E, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLeftCenter(), OG.Constants.TERMINAL_TYPE.W, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLowerCenter(), OG.Constants.TERMINAL_TYPE.S, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getUpperCenter(), OG.Constants.TERMINAL_TYPE.N, OG.Constants.TERMINAL_TYPE.INOUT)
		];
	};

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @abstract
	 */
	this.createShape = function () {
		throw new OG.NotImplementedException("OG.shape.IShape.createShape");
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @abstract
	 */
	this.clone = function () {
		throw new OG.NotImplementedException("OG.shape.IShape.clone");
	};
};
OG.shape.GeomShape.prototype = new OG.shape.IShape();
OG.shape.GeomShape.prototype.constructor = OG.shape.GeomShape;
OG.GeomShape = OG.shape.GeomShape;
/**
 * Text Shape
 *
 * @class
 * @extends OG.shape.IShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} text 텍스트
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.TextShape = function (text) {

	this.TYPE = OG.Constants.SHAPE_TYPE.TEXT;

	this.SHAPE_ID = 'OG.shape.TextShape';

	this.text = text || "Text Here";

	this.angle = 0;

	/**
	 * 드로잉할 텍스트를 반환한다.
	 *
	 * @return {String} 텍스트
	 * @override
	 */
	this.createShape = function () {
		return this.text;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.TextShape(this.text);
	};
};
OG.shape.TextShape.prototype = new OG.shape.IShape();
OG.shape.TextShape.prototype.constructor = OG.shape.TextShape;
OG.TextShape = OG.shape.TextShape;
/**
 * Image Shape
 *
 * @class
 * @extends OG.shape.IShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} image 이미지 URL
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.ImageShape = function (image, label) {

	this.TYPE = OG.Constants.SHAPE_TYPE.IMAGE;

	this.SHAPE_ID = 'OG.shape.ImageShape';

	this.image = image;

	this.angle = 0;

	this.label = label;

	this.createTerminal = function () {
		if (!this.geom) {
			return [];
		}

		var envelope = this.geom.getBoundary();

		return [
			new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getRightCenter(), OG.Constants.TERMINAL_TYPE.E, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLeftCenter(), OG.Constants.TERMINAL_TYPE.W, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLowerCenter(), OG.Constants.TERMINAL_TYPE.S, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getUpperCenter(), OG.Constants.TERMINAL_TYPE.N, OG.Constants.TERMINAL_TYPE.INOUT)
		];
	};

	/**
	 * 드로잉할 이미지 URL을 반환한다.
	 *
	 * @return {String} 이미지 URL
	 * @override
	 */
	this.createShape = function () {
		return this.image;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.ImageShape(this.image, this.label);
	};
};
OG.shape.ImageShape.prototype = new OG.shape.IShape();
OG.shape.ImageShape.prototype.constructor = OG.shape.ImageShape;
OG.ImageShape = OG.shape.ImageShape;
/**
 * Line Shape
 *
 * @class
 * @extends OG.shape.GeomShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {Number[]} from 와이어 시작 좌표
 * @param {Number[]} to 와이어 끝 좌표
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.EdgeShape = function (from, to, label) {
	this.TYPE = OG.Constants.SHAPE_TYPE.EDGE;

	this.SHAPE_ID = 'OG.shape.EdgeShape';

	this.from = from;
	this.to = to;

	this.label = label;

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @override
	 */
	this.createShape = function () {
		if (this.geom) {
			return this.geom;
		}

		this.geom = new OG.Line(from, to);
		return this.geom;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.EdgeShape(this.from, this.to, this.label);
	};
};
OG.shape.EdgeShape.prototype = new OG.shape.IShape();
OG.shape.EdgeShape.prototype.constructor = OG.shape.EdgeShape;
OG.EdgeShape = OG.shape.EdgeShape;
/**
 * Circle Shape
 *
 * @class
 * @extends OG.shape.GeomShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.CircleShape = function (label) {

	this.SHAPE_ID = 'OG.shape.CircleShape';

	this.label = label;

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @override
	 */
	this.createShape = function () {
		if (this.geom) {
			return this.geom;
		}

		this.geom = new OG.geometry.Circle([50, 50], 50);
		return this.geom;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.CircleShape(this.label);
	};
};
OG.shape.CircleShape.prototype = new OG.shape.GeomShape();
OG.shape.CircleShape.prototype.constructor = OG.shape.CircleShape;
OG.CircleShape = OG.shape.CircleShape;
/**
 * Ellipse Shape
 *
 * @class
 * @extends OG.shape.GeomShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.EllipseShape = function (label) {

	this.SHAPE_ID = 'OG.shape.EllipseShape';

	this.label = label;

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @override
	 */
	this.createShape = function () {
		if (this.geom) {
			return this.geom;
		}

		this.geom = new OG.geometry.Ellipse([50, 50], 50, 30);
		return this.geom;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.EllipseShape(this.label);
	};
};
OG.shape.EllipseShape.prototype = new OG.shape.GeomShape();
OG.shape.EllipseShape.prototype.constructor = OG.shape.EllipseShape;
OG.EllipseShape = OG.shape.EllipseShape;
/**
 * Group Shape
 *
 * @class
 * @extends OG.shape.IShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.GroupShape = function (label) {

	this.TYPE = OG.Constants.SHAPE_TYPE.GROUP;

	this.SHAPE_ID = 'OG.shape.GroupShape';

	this.label = label;

	this.createTerminal = function () {
		if (!this.geom) {
			return [];
		}

		var envelope = this.geom.getBoundary();

		return [
			new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getRightCenter(), OG.Constants.TERMINAL_TYPE.E, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLeftCenter(), OG.Constants.TERMINAL_TYPE.W, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLowerCenter(), OG.Constants.TERMINAL_TYPE.S, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getUpperCenter(), OG.Constants.TERMINAL_TYPE.N, OG.Constants.TERMINAL_TYPE.INOUT)
		];
	};

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @override
	 */
	this.createShape = function () {
		if (this.geom) {
			return this.geom;
		}

		this.geom = new OG.geometry.Rectangle([0, 0], 100, 100);
		return this.geom;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.GroupShape(this.label);
	};
};
OG.shape.GroupShape.prototype = new OG.shape.IShape();
OG.shape.GroupShape.prototype.constructor = OG.shape.GroupShape;
OG.GroupShape = OG.shape.GroupShape;
/**
 * Rectangle Shape
 *
 * @class
 * @extends OG.shape.GeomShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.RectangleShape = function (label) {

	this.SHAPE_ID = 'OG.shape.RectangleShape';

	this.label = label;

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @override
	 */
	this.createShape = function () {
		if (this.geom) {
			return this.geom;
		}

		this.geom = new OG.geometry.Rectangle([0, 0], 100, 100);
		return this.geom;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.RectangleShape(this.label);
	};
};
OG.shape.RectangleShape.prototype = new OG.shape.GeomShape();
OG.shape.RectangleShape.prototype.constructor = OG.shape.RectangleShape;
OG.RectangleShape = OG.shape.RectangleShape;
/**
 * Swimlane Shape
 *
 * @class
 * @extends OG.shape.GroupShape
 * @requires OG.common.*, OG.geometry.*
 *
 * @param {String} label 라벨
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.shape.SwimlaneShape = function (label) {

	this.TYPE = OG.Constants.SHAPE_TYPE.GROUP;

	this.SHAPE_ID = 'OG.shape.SwimlaneShape';

	this.label = label;

	this.createTerminal = function () {
		if (!this.geom) {
			return [];
		}

		var envelope = this.geom.getBoundary();

		return [
			new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getRightCenter(), OG.Constants.TERMINAL_TYPE.E, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLeftCenter(), OG.Constants.TERMINAL_TYPE.W, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getLowerCenter(), OG.Constants.TERMINAL_TYPE.S, OG.Constants.TERMINAL_TYPE.INOUT),
			new OG.Terminal(envelope.getUpperCenter(), OG.Constants.TERMINAL_TYPE.N, OG.Constants.TERMINAL_TYPE.INOUT)
		];
	};

	/**
	 * 드로잉할 Shape 을 생성하여 반환한다.
	 *
	 * @return {OG.geometry.Geometry} Shape 정보
	 * @override
	 */
	this.createShape = function () {
		if (this.geom) {
			return this.geom;
		}

		this.geom = new OG.geometry.Rectangle([0, 0], 100, 100);
		return this.geom;
	};

	/**
	 * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	 *
	 * @return {OG.shape.IShape} 복사된 인스턴스
	 * @override
	 */
	this.clone = function () {
		return new OG.shape.GroupShape(this.label);
	};
};
OG.shape.SwimlaneShape.prototype = new OG.shape.GroupShape();
OG.shape.SwimlaneShape.prototype.constructor = OG.shape.GroupShape;
OG.SwimlaneShape = OG.shape.GroupShape;
/**
 * 도형의 Style 과 Shape 정보를 통해 캔버스에 렌더링 기능을 정의한 인터페이스
 *
 * @class
 * @requires OG.common.*, OG.geometry.*, OG.shape.*
 *
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.renderer.IRenderer = function () {
	/**
	 * Shape 을 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @example
	 * renderer.drawShape([100, 100], new OG.CircleShape(), [50, 50], {stroke:'red'});
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(중앙 기준)
	 * @param {OG.shape.IShape} shape Shape
	 * @param {Number[]} size Shape Width, Height
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @return {Element} Group DOM Element with geometry
	 */
	this.drawShape = function (position, shape, size, style, id) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Geometry 를 캔버스에 드로잉한다.
	 *
	 * @param {OG.geometry.Geometry} geometry 기하 객체
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element} Group DOM Element with geometry
	 */
	this.drawGeom = function (geometry, style, id) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Text 를 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 * (스타일 'text-anchor': 'start' or 'middle' or 'end' 에 따라 위치 기준이 다름)
	 *
	 * @example
	 * renderer.drawText([100, 100], 'Hello', null, {'text-anchor':'start'});
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(스타일 'text-anchor': 'start' or 'middle' or 'end' 에 따라 기준이 다름)
	 * @param {String} text 텍스트
	 * @param {Number[]} size Text Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @return {Element} DOM Element
	 */
	this.drawText = function (position, text, size, style, id) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Image 를 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @example
	 * renderer.drawImage([100, 100], 'img.jpg', [50, 50]);
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(좌상단 기준)
	 * @param {String} imgSrc 이미지경로
	 * @param {Number[]} size Image Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @return {Element} DOM Element
	 */
	this.drawImage = function (position, imgSrc, size, style, id) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 라인을 캔버스에 드로잉한다.
	 *
	 * @param {OG.geometry.Line} line 라인
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @param {Boolean} isSelf 셀프 연결 여부
	 * @return {Element} Group DOM Element with geometry
	 */
	this.drawEdge = function (line, style, id, isSelf) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Shape 의 Label 을 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @param {Element,String} shapeElement Shape DOM element or ID
	 * @param {String} text 텍스트
	 * @param {Object} style 스타일
	 * @return {Element} DOM Element
	 * @override
	 */
	this.drawLabel = function (shapeElement, text, style) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Element 에 저장된 geom, angle, image, text 정보로 shape 을 redraw 한다.
	 *
	 * @param {Element} element Shape 엘리먼트
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 */
	this.redrawShape = function (element, excludeEdgeId) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Shape 의 연결된 Edge 를 redraw 한다.(이동 또는 리사이즈시)
	 *
	 * @param {Element} element
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 */
	this.redrawConnectedEdge = function (element, excludeEdgeId) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 두개의 터미널을 연결하고, 속성정보에 추가한다.
	 *
	 * @param {Element,Number[]} from 시작점
	 * @param {Element,Number[]} to 끝점
	 * @param {Element} edge Edge Shape
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} label Label
	 * @return {Element}
	 */
	this.connect = function (from, to, edge, style, label) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 연결속성정보를 삭제한다. Edge 인 경우는 라인만 삭제하고, 일반 Shape 인 경우는 연결된 모든 Edge 를 삭제한다.
	 *
	 * @param {Element} element
	 */
	this.disconnect = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 의 Edge 연결시 Drop Over 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.drawDropOverGuide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 의 Move & Resize 용 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Object}
	 */
	this.drawGuide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 의 Move & Resize 용 가이드를 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.removeGuide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Edge Element 의 Move & Resize 용 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Object}
	 */
	this.drawEdgeGuide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Rectangle 모양의 마우스 드래그 선택 박스 영역을 드로잉한다.
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(좌상단)
	 * @param {Number[]} size Text Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element} DOM Element
	 */
	this.drawRubberBand = function (position, size, style) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Rectangle 모양의 마우스 드래그 선택 박스 영역을 제거한다.
	 *
	 * @param {Element} root first, rubberBand 정보를 저장한 엘리먼트
	 */
	this.removeRubberBand = function (root) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Edge 연결용 터미널을 드로잉한다.
	 *
	 * @param {Element} element DOM Element
	 * @return {Element} terminal group element
	 */
	this.drawTerminal = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 *  Edge 연결용 터미널을 remove 한다.
	 *
	 * @param {Element} element DOM Element
	 */
	this.removeTerminal = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 *  모든 Edge 연결용 터미널을 remove 한다.
	 */
	this.removeAllTerminal = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 의 Collapse 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Element}
	 */
	this.drawCollapseGuide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 의 Collapse 가이드를 제거한다.
	 *
	 * @param {Element} element
	 */
	this.removeCollapseGuide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 Shape 들을 그룹핑한다.
	 *
	 * @param {Element[]} elements
	 * @return {Element} Group Shape Element
	 */
	this.group = function (elements) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 그룹들을 그룹해제한다.
	 *
	 * @param {Element[]} groupElements
	 * @return {Element[]} ungrouped Elements
	 */
	this.ungroup = function (groupElements) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 Shape 들을 그룹에 추가한다.
	 *
	 * @param {Element} groupElement
	 * @param {Element[]} elements
	 */
	this.addToGroup = function (groupElement, elements) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 Shape 이 그룹인 경우 collapse 한다.
	 *
	 * @param {Element} element
	 */
	this.collapse = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 Shape 이 그룹인 경우 expand 한다.
	 *
	 * @param {Element} element
	 */
	this.expand = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 드로잉된 모든 오브젝트를 클리어한다.
	 */
	this.clear = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * Shape 을 캔버스에서 관련된 모두를 삭제한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.removeShape = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.remove = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 하위 엘리먼트만 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.removeChild = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 랜더러 캔버스 Root Element 를 반환한다.
	 *
	 * @return {Element} Element
	 */
	this.getRootElement = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * 랜더러 캔버스 Root Group Element 를 반환한다.
	 *
	 * @return {Element} Element
	 */
	this.getRootGroup = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 지점을 포함하는 Top Element 를 반환한다.
	 *
	 * @param {Number[]} position 위치 좌표
	 * @return {Element} Element
	 */
	this.getElementByPoint = function (position) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 Boundary Box 영역에 포함되는 Shape(GEOM, TEXT, IMAGE) Element 를 반환한다.
	 *
	 * @param envelope
	 * @return {Element[]} Element
	 */
	this.getElementsByBBox = function (envelope) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 엘리먼트에 속성값을 설정한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Object} attribute 속성값
	 */
	this.setAttr = function (element, attribute) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 엘리먼트 속성값을 반환한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {String} attrName 속성이름
	 */
	this.getAttr = function (element, attrName) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 를 최상단 레이어로 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.toFront = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 를 최하단 레이어로 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.toBack = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 랜더러 캔버스의 사이즈(Width, Height)를 변경한다.
	 *
	 * @param {Number[]} size Canvas Width, Height
	 */
	this.setCanvasSize = function (size) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 새로운 View Box 영역을 설정한다. (ZoomIn & ZoomOut 가능)
	 *
	 * @param @param {Number[]} position 위치 좌표(좌상단 기준)
	 * @param {Number[]} size Canvas Width, Height
	 * @param {Boolean} isFit Fit 여부
	 */
	this.setViewBox = function (position, size, isFit) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 show 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.show = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 hide 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.hide = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Source Element 를 Target Element 아래에 append 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 */
	this.appendChild = function (srcElement, targetElement) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Source Element 를 Target Element 이후에 insert 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 */
	this.insertAfter = function (srcElement, targetElement) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Source Element 를 Target Element 이전에 insert 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 */
	this.insertBefore = function (srcElement, targetElement) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 해당 Element 를 가로, 세로 Offset 만큼 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} offset [가로, 세로]
	 * @return {Element} Element
	 */
	this.move = function (element, offset) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 주어진 중심좌표로 해당 Element 를 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} position [x, y]
	 * @return {Element} Element
	 */
	this.moveCentroid = function (element, position) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 중심 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number} angle 각도
	 * @return {Element} Element
	 */
	this.rotate = function (element, angle) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동한 만큼 리사이즈 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} offset [상, 하, 좌, 우] 각 방향으로 + 값
	 * @return {Element} Element
	 */
	this.resize = function (element, offset) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 중심좌표는 고정한 채 Bounding Box 의 width, height 를 리사이즈 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} size [Width, Height]
	 * @return {Element} Element
	 */
	this.resizeBox = function (element, size) {
		throw new OG.NotImplementedException();
	};

	/**
	 * Edge 유형에 따라 Shape 과의 연결 지점을 찾아 반환한다.
	 *
	 * @param {String} edgeType Edge 유형(straight, plain..)
	 * @param {Element} element 연결할 Shape 엘리먼트
	 * @param {Number[]} from 시작좌표
	 * @param {Number[]} to 끝좌표
	 * @param {Boolean} 시작 연결지점 여부
	 * @return {Object} {position, direction}
	 */
	this.intersectionEdge = function (edgeType, element, from, to, isFrom) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 노드 Element 를 복사한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Element} Element
	 */
	this.clone = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * ID로 Node Element 를 반환한다.
	 *
	 * @param {String} id
	 * @return {Element} Element
	 */
	this.getElementById = function (id) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 해당 엘리먼트의 BoundingBox 영역 정보를 반환한다.
	 *
	 * @param {Element,String} element
	 * @return {Object} {width, height, x, y, x2, y2}
	 */
	this.getBBox = function (element) {
		throw new OG.NotImplementedException();
	};

	/**
	 * 캔버스 루트 엘리먼트의 BoundingBox 영역 정보를 반환한다.
	 *
	 * @return {Object} {width, height, x, y, x2, y2}
	 * @override
	 */
	this.getRootBBox = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * SVG 인지 여부를 반환한다.
	 *
	 * @return {Boolean} svg 여부
	 */
	this.isSVG = function () {
		throw new OG.NotImplementedException();
	};

	/**
	 * VML 인지 여부를 반환한다.
	 *
	 * @return {Boolean} vml 여부
	 */
	this.isVML = function () {
		throw new OG.NotImplementedException();
	};
};
OG.renderer.IRenderer.prototype = new OG.renderer.IRenderer();
OG.renderer.IRenderer.prototype.constructor = OG.renderer.IRenderer;
/**
 * Raphael 라이브러리를 이용하여 구현한 랜더러 캔버스 클래스
 * - 노드에 추가되는 속성 : _type, _shape, _selected, _from, _to, _fromedge, _toedge
 * - 노드에 저장되는 값 : shape : { geom, angle, image, text }
 *
 * @class
 * @extends OG.renderer.IRenderer
 * @requires OG.common.*, OG.geometry.*, OG.shape.*, raphael-2.1.0
 *
 * @param {HTMLElement,String} container 컨테이너 DOM element or ID
 * @param {Number[]} containerSize 컨테이너 Width, Height
 * @param {String} backgroundColor 캔버스 배경색
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.renderer.RaphaelRenderer = function (container, containerSize, backgroundColor) {
	var _PAPER = new Raphael(container, containerSize ? containerSize[0] : null, containerSize ? containerSize[1] : null),
		_ID_PREFIX = Math.round(Math.random() * 10000),
		_LAST_ID = 0,
		_ELE_MAP = new OG.HashMap(),
		_ROOT_GROUP,
		_RENDERER = this,
		_CANVAS_COLOR = backgroundColor || OG.Constants.CANVAS_BACKGROUND,
		genId,
		add,
		remove,
		removeChild,
		getREleById,
		drawGeometry,
		adjustEdgeDirection,
		findFromTerminal,
		findToTerminal,
		getShapeFromTerminal,
		drawLabel;

	/**
	 * ID를 generate 한다.
	 *
	 * @return {String} ID
	 * @private
	 */
	genId = function () {
		var id = "OG_" + _ID_PREFIX + "_" + _LAST_ID;
		_LAST_ID++;
		return id;
	};

	/**
	 * ID를 발급하고 ID:rElement 해쉬맵에 추가한다.
	 *
	 * @param {Raphael.Element} rElement 라파엘 엘리먼트
	 * @param {String} id 지정ID
	 * @param {String} nodeType Node 유형(ROOT, SHAPE ...)
	 * @param {String} shapeType Shape 유형(GEOM, TEXT, IMAGE, EDGE, GROUP ...)
	 * @return {Raphael.Element} rElement 라파엘 엘리먼트
	 * @private
	 */
	add = function (rElement, id, nodeType, shapeType) {
		rElement.id = id || genId();
		rElement.node.id = rElement.id;
		rElement.node.raphaelid = rElement.id;
		if (nodeType) {
			$(rElement.node).attr("_type", nodeType);
		}
		if (shapeType) {
			$(rElement.node).attr("_shape", shapeType);
		}
		_ELE_MAP.put(rElement.id, rElement);

		return rElement;
	};

	/**
	 * 라파엘 엘리먼트를 하위 엘리먼트 포함하여 제거한다.
	 *
	 * @param {Raphael.Element} rElement 라파엘 엘리먼트
	 * @private
	 */
	remove = function (rElement) {
		var childNodes, i;
		if (rElement) {
			childNodes = rElement.node.childNodes;
			for (i = childNodes.length - 1; i >= 0; i--) {
				remove(getREleById(childNodes[i].id));
			}
			_ELE_MAP.remove(rElement.id);
			rElement.remove();
		}
	};

	/**
	 * 하위 엘리먼트만 제거한다.
	 *
	 * @param {Raphael.Element} rElement 라파엘 엘리먼트
	 * @private
	 */
	removeChild = function (rElement) {
		var childNodes, i;
		if (rElement) {
			childNodes = rElement.node.childNodes;
			for (i = childNodes.length - 1; i >= 0; i--) {
				remove(getREleById(childNodes[i].id));
			}
		}
	};

	/**
	 * ID에 해당하는 RaphaelElement 를 반환한다.
	 *
	 * @param {String} id ID
	 * @return {Raphael.Element} RaphaelElement
	 * @private
	 */
	getREleById = function (id) {
		return _ELE_MAP.get(id);
	};

	/**
	 * Geometry 를 캔버스에 드로잉한다.(Recursive)
	 *
	 * @param {Element} groupElement Group DOM Element
	 * @param {OG.geometry.Geometry} geometry 기하 객체
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element}
	 * @private
	 */
	drawGeometry = function (groupElement, geometry, style) {
		var i = 0, pathStr = "", vertices, element, geomObj, boundary, upperLeft, _style = {};
		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, geometry.style.map);
		geometry.style.map = _style;

		// 타입에 따라 드로잉
		switch (geometry.TYPE) {
		case OG.Constants.GEOM_TYPE.POINT:
			element = _PAPER.circle(geometry.coordinate.x, geometry.coordinate.y, 0.5);
			element.attr(_style);
			break;

		case OG.Constants.GEOM_TYPE.LINE:
		case OG.Constants.GEOM_TYPE.POLYLINE:
		case OG.Constants.GEOM_TYPE.POLYGON:
		case OG.Constants.GEOM_TYPE.RECTANGLE:
			pathStr = "";
			vertices = geometry.getVertices();
			for (i = 0; i < vertices.length; i++) {
				if (i === 0) {
					pathStr = "M" + vertices[i].x + " " + vertices[i].y;
				} else {
					pathStr += "L" + vertices[i].x + " " + vertices[i].y;
				}
			}
			element = _PAPER.path(pathStr);
			element.attr(_style);
			break;

		case OG.Constants.GEOM_TYPE.CIRCLE:
			geomObj = OG.JSON.decode(geometry.toString());
			if (geomObj.type === OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.CIRCLE]) {
				element = _PAPER.circle(geomObj.center[0], geomObj.center[1], geomObj.radius);
			} else if (geomObj.type === OG.Constants.GEOM_NAME[OG.Constants.GEOM_TYPE.ELLIPSE]) {
				if (geomObj.angle === 0) {
					element = _PAPER.ellipse(geomObj.center[0], geomObj.center[1], geomObj.radiusX, geomObj.radiusY);
				} else {
					pathStr = "";
					vertices = geometry.getControlPoints();
					pathStr = "M" + vertices[1].x + " " + vertices[1].y + "A" + geomObj.radiusX + " " + geomObj.radiusY
						+ " " + geomObj.angle + " 1 0 " + vertices[5].x + " " + vertices[5].y;
					pathStr += "M" + vertices[1].x + " " + vertices[1].y + "A" + geomObj.radiusX + " " + geomObj.radiusY
						+ " " + geomObj.angle + " 1 1 " + vertices[5].x + " " + vertices[5].y;
					element = _PAPER.path(pathStr);
				}
			}
			element.attr(_style);
			break;

		case OG.Constants.GEOM_TYPE.ELLIPSE:
			geomObj = OG.JSON.decode(geometry.toString());
			if (geomObj.angle === 0) {
				element = _PAPER.ellipse(geomObj.center[0], geomObj.center[1], geomObj.radiusX, geomObj.radiusY);
			} else {
				pathStr = "";
				vertices = geometry.getControlPoints();
				pathStr = "M" + vertices[1].x + " " + vertices[1].y + "A" + geomObj.radiusX + " " + geomObj.radiusY
					+ " " + geomObj.angle + " 1 0 " + vertices[5].x + " " + vertices[5].y;
				pathStr += "M" + vertices[1].x + " " + vertices[1].y + "A" + geomObj.radiusX + " " + geomObj.radiusY
					+ " " + geomObj.angle + " 1 1 " + vertices[5].x + " " + vertices[5].y;
				element = _PAPER.path(pathStr);
			}
			element.attr(_style);
			break;

		case OG.Constants.GEOM_TYPE.CURVE:
			pathStr = "";
			vertices = geometry.getControlPoints();
			for (i = 0; i < vertices.length; i++) {
				if (i === 0) {
					pathStr = "M" + vertices[i].x + " " + vertices[i].y;
				} else if (i === 1) {
					pathStr += "R" + vertices[i].x + " " + vertices[i].y;
				} else {
					pathStr += " " + vertices[i].x + " " + vertices[i].y;
				}
			}
			element = _PAPER.path(pathStr);
			element.attr(_style);
			break;

		case OG.Constants.GEOM_TYPE.COLLECTION:
			for (i = 0; i < geometry.geometries.length; i++) {
				// recursive call
				drawGeometry(groupElement, geometry.geometries[i], style);
			}
			break;
		}

		if (element) {
			add(element);
			groupElement.appendChild(element.node);
		}

		return element.node;
	};


	/**
	 * 한쪽이상 끊긴 경우 Edge Direction 을 보정한다.
	 *
	 * @param {String} fromDrct 시작방향
	 * @param {String} toDrct 끝방향
	 * @param {Number[]} from 시작위치
	 * @param {Number[]} to 끝위치
	 * @return {String} edge-direction 보정된 edge-direction
	 */
	adjustEdgeDirection = function (fromDrct, toDrct, from, to) {
		var fromXY = {x: from[0], y: from[1]}, toXY = {x: to[0], y: to[1]};
		// 한쪽이 끊긴 경우 방향 보정
		if (fromDrct === "c" && toDrct === "c") {
			if (fromXY.x <= toXY.x && fromXY.y <= toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "e";
					toDrct = "w";
				} else {
					fromDrct = "s";
					toDrct = "n";
				}
			} else if (fromXY.x <= toXY.x && fromXY.y > toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "e";
					toDrct = "w";
				} else {
					fromDrct = "n";
					toDrct = "s";
				}
			} else if (fromXY.x > toXY.x && fromXY.y <= toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "w";
					toDrct = "e";
				} else {
					fromDrct = "s";
					toDrct = "n";
				}
			} else if (fromXY.x > toXY.x && fromXY.y > toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "w";
					toDrct = "e";
				} else {
					fromDrct = "n";
					toDrct = "s";
				}
			}
		} else if (fromDrct === "c" && toDrct !== "c") {
			if (fromXY.x <= toXY.x && fromXY.y <= toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "e";
				} else {
					fromDrct = "s";
				}
			} else if (fromXY.x <= toXY.x && fromXY.y > toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "e";
				} else {
					fromDrct = "n";
				}
			} else if (fromXY.x > toXY.x && fromXY.y <= toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "w";
				} else {
					fromDrct = "s";
				}
			} else if (fromXY.x > toXY.x && fromXY.y > toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					fromDrct = "w";
				} else {
					fromDrct = "n";
				}
			}
		} else if (fromDrct !== "c" && toDrct === "c") {
			if (fromXY.x <= toXY.x && fromXY.y <= toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					toDrct = "w";
				} else {
					toDrct = "n";
				}
			} else if (fromXY.x <= toXY.x && fromXY.y > toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					toDrct = "w";
				} else {
					toDrct = "s";
				}
			} else if (fromXY.x > toXY.x && fromXY.y <= toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					toDrct = "e";
				} else {
					toDrct = "n";
				}
			} else if (fromXY.x > toXY.x && fromXY.y > toXY.y) {
				if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
					toDrct = "e";
				} else {
					toDrct = "s";
				}
			}
		}

		return fromDrct + " " + toDrct;
	};

	/**
	 * 시작, 끝 좌표에 따라 적절한 시작 터미널을 찾아 반환한다.
	 *
	 * @param {Element} element Shape 엘리먼트
	 * @param {Number[]} from 시작자표
	 * @param {Number[]} to 끝자표
	 * @return {Element} 터미널 엘리먼트
	 */
	findFromTerminal = function (element, from, to) {
		// 적절한 연결 터미널 찾기
		var fromXY = {x: from[0], y: from[1]}, toXY = {x: to[0], y: to[1]},
			terminalGroup = _RENDERER.drawTerminal(element),
			childTerminals = terminalGroup.terminal.childNodes, fromDrct, fromTerminal, i;
		if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
			if (toXY.x > fromXY.x) {
				fromDrct = "e";
			} else {
				fromDrct = "w";
			}
		} else {
			if (toXY.y > fromXY.y) {
				fromDrct = "s";
			} else {
				fromDrct = "n";
			}
		}

		fromTerminal = childTerminals[0];
		for (i = 0; i < childTerminals.length; i++) {
			if (childTerminals[i].terminal && childTerminals[i].terminal.direction.toLowerCase() === fromDrct) {
				fromTerminal = childTerminals[i];
				break;
			}
		}

		return fromTerminal;
	};

	/**
	 * 시작, 끝 좌표에 따라 적절한 끝 터미널을 찾아 반환한다.
	 *
	 * @param {Element} element Shape 엘리먼트
	 * @param {Number[]} from 시작자표
	 * @param {Number[]} to 끝자표
	 * @return {Element} 터미널 엘리먼트
	 */
	findToTerminal = function (element, from, to) {
		// 적절한 연결 터미널 찾기
		var fromXY = {x: from[0], y: from[1]}, toXY = {x: to[0], y: to[1]},
			terminalGroup = _RENDERER.drawTerminal(element),
			childTerminals = terminalGroup.terminal.childNodes, toDrct, toTerminal, i;
		if (Math.abs(toXY.x - fromXY.x) > Math.abs(toXY.y - fromXY.y)) {
			if (toXY.x > fromXY.x) {
				toDrct = "w";
			} else {
				toDrct = "e";
			}
		} else {
			if (toXY.y > fromXY.y) {
				toDrct = "n";
			} else {
				toDrct = "s";
			}
		}

		toTerminal = childTerminals[0];
		for (i = 0; i < childTerminals.length; i++) {
			if (childTerminals[i].terminal && childTerminals[i].terminal.direction.toLowerCase() === toDrct) {
				toTerminal = childTerminals[i];
				break;
			}
		}

		return toTerminal;
	};

	/**
	 * 터미널로부터 부모 Shape element 를 찾아 반환한다.
	 *
	 * @param {Element,String} terminal 터미널 Element or ID
	 * @return {Element} Shape element
	 */
	getShapeFromTerminal = function (terminal) {
		var terminalId = OG.Util.isElement(terminal) ? terminal.id : terminal;
		if (terminalId) {
			return _RENDERER.getElementById(terminalId.substring(0, terminalId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)));
		} else {
			return null;
		}
	};

	/**
	 * Shape 의 Label 을 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(중앙 기준)
	 * @param {String} text 텍스트
	 * @param {Number[]} size Text Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @param {Boolean} isEdge 라인여부(라인인 경우 라벨이 가려지지 않도록)
	 * @return {Element} DOM Element
	 */
	drawLabel = function (position, text, size, style, id, isEdge) {
		var LABEL_PADDING = OG.Constants.LABEL_PADDING,
			width = size ? size[0] - LABEL_PADDING * 2 : null,
			height = size ? size[1] - LABEL_PADDING * 2 : null,
			angle = size ? size[2] || 0 : 0,
			group, element, rect, _style = {}, geom,
			bBox, left, top, x, y;
		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.TEXT);

		// ID 지정된 경우 존재하면 하위 노드 제거
		if (id === 0 || id) {
			group = getREleById(id);
			if (group) {
				removeChild(group);
			} else {
				group = _PAPER.group();
				add(group, id);
			}
		} else {
			group = _PAPER.group();
			add(group, id);
		}

		// Draw text
		element = _PAPER.text(position[0], position[1], text);
		element.attr(_style);

		// real size
		bBox = element.getBBox();

		// calculate width, height, left, top
		width = width ? (width > bBox.width ? width : bBox.width) : bBox.width;
		height = height ? (height > bBox.height ? height : bBox.height) : bBox.height;
		left = OG.Util.round(position[0] - width / 2);
		top = OG.Util.round(position[1] - height / 2);

		// Boundary Box
		geom = new OG.Rectangle([left, top], width, height);

		// Text Horizontal Align
		switch (_style["text-anchor"]) {
		case "start":
			x = geom.getBoundary().getLeftCenter().x;
			break;
		case "end":
			x = geom.getBoundary().getRightCenter().x;
			break;
		case "middle":
			x = geom.getBoundary().getCentroid().x;
			break;
		default:
			x = geom.getBoundary().getCentroid().x;
			break;
		}

		// Text Vertical Align
		switch (_style["vertical-align"]) {
		case "top":
			y = OG.Util.round(geom.getBoundary().getUpperCenter().y + bBox.height / 2);
			break;
		case "bottom":
			y = OG.Util.round(geom.getBoundary().getLowerCenter().y - bBox.height / 2);
			break;
		case "middle":
			y = geom.getBoundary().getCentroid().y;
			break;
		default:
			y = geom.getBoundary().getCentroid().y;
			break;
		}

		// text align 적용
		element.attr({x: x, y: y});

		// font-color, font-size 적용
		element.attr({
			stroke        : "none",
			fill          : _style["font-color"] || OG.Constants.DEFAULT_STYLE.LABEL["font-color"],
			"font-size"   : _style["font-size"] || OG.Constants.DEFAULT_STYLE.LABEL["font-size"],
			"fill-opacity": 1
		});

		// angle 적용
		if (angle) {
			element.rotate(angle);
		}

		// 라인인 경우 overwrap 용 rectangle
		if (isEdge && text) {
			// real size
			bBox = element.getBBox();

			rect = _PAPER.rect(bBox.x - LABEL_PADDING / 2, bBox.y - LABEL_PADDING / 2,
				bBox.width + LABEL_PADDING, bBox.height + LABEL_PADDING);
			rect.attr({stroke: "none", fill: _CANVAS_COLOR, 'fill-opacity': 1});
			add(rect);
			group.node.appendChild(rect.node);
		}

		// Add to group
		add(element);
		group.node.appendChild(element.node);

		return group.node;
	};

	// 최상위 그룹 엘리먼트 초기화
	_ROOT_GROUP = add(_PAPER.group(), null, OG.Constants.NODE_TYPE.ROOT);
	_PAPER.id = "OG_" + _ID_PREFIX;
	_PAPER.canvas.id = "OG_" + _ID_PREFIX;
	$(_PAPER.canvas).css({"background-color": _CANVAS_COLOR});

	/**
	 * Shape 을 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @example
	 * renderer.drawShape([100, 100], new OG.CircleShape(), [50, 50], {stroke:'red'});
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(중앙 기준)
	 * @param {OG.shape.IShape} shape Shape
	 * @param {Number[]} size Shape Width, Height
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @return {Element} Group DOM Element with geometry
	 * @override
	 */
	this.drawShape = function (position, shape, size, style, id) {
		var width = size ? size[0] : 100,
			height = size ? size[1] : 100,
			groupNode, geometry, text, image, envelope, label;

		if (shape instanceof OG.shape.GeomShape) {
			geometry = shape.createShape();

			// 좌상단으로 이동 및 크기 조정
			geometry.moveCentroid(position);
			geometry.resizeBox(width, height);

			groupNode = this.drawGeom(geometry, style, id);
			shape.geom = groupNode.geom;
		} else if (shape instanceof OG.shape.TextShape) {
			text = shape.createShape();

			groupNode = this.drawText(position, text, size, style, id);
			shape.text = groupNode.text;
			shape.angle = groupNode.angle;
			shape.geom = groupNode.geom;
		} else if (shape instanceof OG.shape.ImageShape) {
			image = shape.createShape();

			groupNode = this.drawImage(position, image, size, style, id);
			shape.image = groupNode.image;
			shape.angle = groupNode.angle;
			shape.geom = groupNode.geom;
		} else if (shape instanceof OG.shape.EdgeShape) {
			geometry = shape.createShape();

			groupNode = this.drawEdge(geometry, style, id);
			shape.geom = groupNode.geom;
		} else if (shape instanceof OG.shape.GroupShape) {
			geometry = shape.createShape();

			// 좌상단으로 이동 및 크기 조정
			geometry.moveCentroid(position);
			geometry.resizeBox(width, height);

			groupNode = this.drawGroup(geometry, style, id);

			shape.geom = groupNode.geom;
		}

		groupNode.shape = shape;

		$(groupNode).attr("_shape_id", shape.SHAPE_ID);

		// Draw Label
		if (!(shape instanceof OG.shape.TextShape)) {
			this.drawLabel(groupNode);
		}
		if (groupNode.geom) {
			if (OG.Util.isIE7()) {
				groupNode.removeAttribute("geom");
			} else {
				delete groupNode.geom;
			}
		}
		if (groupNode.text) {
			if (OG.Util.isIE7()) {
				groupNode.removeAttribute("text");
			} else {
				delete groupNode.text;
			}
		}
		if (groupNode.image) {
			if (OG.Util.isIE7()) {
				groupNode.removeAttribute("image");
			} else {
				delete groupNode.image;
			}
		}
		if (groupNode.angle) {
			if (OG.Util.isIE7()) {
				groupNode.removeAttribute("angle");
			} else {
				delete groupNode.angle;
			}
		}

		return groupNode;
	};

	/**
	 * Geometry 를 캔버스에 드로잉한다.
	 *
	 * @param {OG.geometry.Geometry} geometry 기하 객체
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element} Group DOM Element with geometry
	 * @override
	 */
	this.drawGeom = function (geometry, style, id) {
		var group, _style = {};

		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.GEOM);

		// ID 지정된 경우 존재하면 하위 노드 제거
		if (id === 0 || id) {
			group = getREleById(id);
			if (group) {
				removeChild(group);
			} else {
				group = _PAPER.group();
				add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.GEOM);
				_ROOT_GROUP.node.appendChild(group.node);
			}
		} else {
			group = _PAPER.group();
			add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.GEOM);
			_ROOT_GROUP.node.appendChild(group.node);
		}

		// Draw geometry
		drawGeometry(group.node, geometry, _style);
		group.node.geom = geometry;
		group.attr(OG.Constants.DEFAULT_STYLE.SHAPE);

		if (group.node.shape) {
			group.node.shape.geom = geometry;

			if (group.node.geom) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("geom");
				} else {
					delete group.node.geom;
				}
			}
		}

		return group.node;
	};

	/**
	 * Text 를 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(중앙 기준)
	 * @param {String} text 텍스트
	 * @param {Number[]} size Text Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @return {Element} DOM Element
	 * @override
	 */
	this.drawText = function (position, text, size, style, id) {
		var width = size ? size[0] : null,
			height = size ? size[1] : null,
			angle = size ? size[2] || 0 : 0,
			group, element, _style = {}, geom,
			bBox, left, top, x, y;
		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.TEXT);

		// ID 지정된 경우 존재하면 하위 노드 제거
		if (id === 0 || id) {
			group = getREleById(id);
			if (group) {
				removeChild(group);
			} else {
				group = _PAPER.group();
				add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.TEXT);
				_ROOT_GROUP.node.appendChild(group.node);
			}
		} else {
			group = _PAPER.group();
			add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.TEXT);
			_ROOT_GROUP.node.appendChild(group.node);
		}

		// Draw text
		element = _PAPER.text(position[0], position[1], text);
		element.attr(_style);

		// real size
		bBox = element.getBBox();

		// calculate width, height, left, top
		width = width ? (width > bBox.width ? width : bBox.width) : bBox.width;
		height = height ? (height > bBox.height ? height : bBox.height) : bBox.height;
		left = OG.Util.round(position[0] - width / 2);
		top = OG.Util.round(position[1] - height / 2);

		// Boundary Box
		geom = new OG.Rectangle([left, top], width, height);
		geom.style.map = _style;

		// Text Horizontal Align
		switch (_style["text-anchor"]) {
		case "start":
			x = geom.getBoundary().getLeftCenter().x;
			break;
		case "end":
			x = geom.getBoundary().getRightCenter().x;
			break;
		case "middle":
			x = geom.getBoundary().getCentroid().x;
			break;
		default:
			x = geom.getBoundary().getCentroid().x;
			break;
		}

		// Text Vertical Align
		switch (_style["vertical-align"]) {
		case "top":
			y = OG.Util.round(geom.getBoundary().getUpperCenter().y + bBox.height / 2);
			break;
		case "bottom":
			y = OG.Util.round(geom.getBoundary().getLowerCenter().y - bBox.height / 2);
			break;
		case "middle":
			y = geom.getBoundary().getCentroid().y;
			break;
		default:
			y = geom.getBoundary().getCentroid().y;
			break;
		}

		// text align 적용
		element.attr({x: x, y: y});

		// font-color, font-size 적용
		element.attr({
			stroke     : "none",
			fill       : _style["font-color"] || OG.Constants.DEFAULT_STYLE.LABEL["font-color"],
			"font-size": _style["font-size"] || OG.Constants.DEFAULT_STYLE.LABEL["font-size"]
		});

		// angle 적용
		if (angle) {
			element.rotate(angle);
		}

		// Add to group
		add(element);
		group.node.appendChild(element.node);
		group.node.text = text;
		group.node.angle = angle;
		group.node.geom = geom;
		group.attr(OG.Constants.DEFAULT_STYLE.SHAPE);

		if (group.node.shape) {
			group.node.shape.text = text;
			group.node.shape.angle = angle;
			group.node.shape.geom = geom;

			if (group.node.text) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("text");
				} else {
					delete group.node.text;
				}
			}
			if (group.node.angle) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("angle");
				} else {
					delete group.node.angle;
				}
			}
			if (group.node.geom) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("geom");
				} else {
					delete group.node.geom;
				}
			}
		}

		return group.node;
	};

	/**
	 * Image 를 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(중앙 기준)
	 * @param {String} imgSrc 이미지경로
	 * @param {Number[]} size Image Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @return {Element} DOM Element
	 * @override
	 */
	this.drawImage = function (position, imgSrc, size, style, id) {
		var width = size ? size[0] : null,
			height = size ? size[1] : null,
			angle = size ? size[2] || 0 : 0,
			group, element, _style = {}, bBox, geom, left, top;
		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.IMAGE);

		// ID 지정된 경우 존재하면 하위 노드 제거
		if (id === 0 || id) {
			group = getREleById(id);
			if (group) {
				removeChild(group);
			} else {
				group = _PAPER.group();
				add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.IMAGE);
				_ROOT_GROUP.node.appendChild(group.node);
			}
		} else {
			group = _PAPER.group();
			add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.IMAGE);
			_ROOT_GROUP.node.appendChild(group.node);
		}

		// Draw image
		element = _PAPER.image(imgSrc, position[0], position[1], width, height);
		element.attr(_style);

		// real size
		bBox = element.getBBox();

		// calculate width, height, left, top
		width = width || bBox.width;
		height = height || bBox.height;
		left = OG.Util.round(position[0] - width / 2);
		top = OG.Util.round(position[1] - height / 2);

		// text align 적용
		element.attr({x: left, y: top});

		geom = new OG.Rectangle([left, top], width, height);
		if (angle) {
			element.rotate(angle);
		}
		geom.style.map = _style;

		// Add to group
		add(element);
		group.node.appendChild(element.node);
		group.node.image = imgSrc;
		group.node.angle = angle;
		group.node.geom = geom;
		group.attr(OG.Constants.DEFAULT_STYLE.SHAPE);

		if (group.node.shape) {
			group.node.shape.image = imgSrc;
			group.node.shape.angle = angle;
			group.node.shape.geom = geom;

			if (group.node.image) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("image");
				} else {
					delete group.node.image;
				}
			}
			if (group.node.angle) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("angle");
				} else {
					delete group.node.angle;
				}
			}
			if (group.node.geom) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("geom");
				} else {
					delete group.node.geom;
				}
			}
		}

		return group.node;
	};

	/**
	 * 라인을 캔버스에 드로잉한다.
	 * OG.geometry.Line 타입인 경우 EdgeType 에 따라 Path 를 자동으로 계산하며,
	 * OG.geometry.PolyLine 인 경우는 주어진 Path 그대로 drawing 한다.
	 *
	 * @param {OG.geometry.Line,OG.geometry.PolyLine} line 또는 polyLine
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} id Element ID 지정
	 * @param {Boolean} isSelf 셀프 연결 여부
	 * @return {Element} Group DOM Element with geometry
	 * @override
	 */
	this.drawEdge = function (line, style, id, isSelf) {
		var group, _style = {},
			vertices = line.getVertices(),
			from = vertices[0], to = vertices[vertices.length - 1],
			points = [], edge, edge_direction,
			getArrayOfOrthogonal_1 = function (from, to, isHorizontal) {
				if (isHorizontal) {
					return [
						[from[0], from[1]],
						[to[0], from[1]],
						[to[0], to[1]]
					];
				} else {
					return [
						[from[0], from[1]],
						[from[0], to[1]],
						[to[0], to[1]]
					];
				}
			},
			getArrayOfOrthogonal_2 = function (from, to, isHorizontal) {
				if (isHorizontal) {
					return [
						[from[0], from[1]],
						[OG.Util.round((from[0] + to[0]) / 2), from[1]],
						[OG.Util.round((from[0] + to[0]) / 2), to[1]],
						[to[0], to[1]]
					];
				} else {
					return [
						[from[0], from[1]],
						[from[0], OG.Util.round((from[1] + to[1]) / 2)],
						[to[0], OG.Util.round((from[1] + to[1]) / 2)],
						[to[0], to[1]]
					];
				}
			};

		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.EDGE);

		// ID 지정된 경우 존재하면 하위 노드 제거
		if (id === 0 || id) {
			group = getREleById(id);
			if (group) {
				removeChild(group);
			} else {
				group = _PAPER.group();
				add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.EDGE);
				_ROOT_GROUP.node.appendChild(group.node);
			}
		} else {
			group = _PAPER.group();
			add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.EDGE);
			_ROOT_GROUP.node.appendChild(group.node);
		}

		if (isSelf) {
			points = [
				[from.x, from.y - OG.Constants.GUIDE_RECT_SIZE / 2],
				[from.x + OG.Constants.GUIDE_RECT_SIZE * 2, from.y - OG.Constants.GUIDE_RECT_SIZE],
				[from.x + OG.Constants.GUIDE_RECT_SIZE * 2, from.y + OG.Constants.GUIDE_RECT_SIZE],
				[from.x, from.y + OG.Constants.GUIDE_RECT_SIZE / 2]
			];
		} else if (line instanceof OG.geometry.Line) {
			// edgeType
			switch (_style["edge-type"].toLowerCase()) {
			case OG.Constants.EDGE_TYPE.STRAIGHT:
				points = [from, to];
				break;
			case OG.Constants.EDGE_TYPE.PLAIN:
				edge_direction = _style["edge-direction"].toLowerCase().split(" ");

				// 'c' 인 경우 위치 보정
				if (edge_direction[0] === "c" || edge_direction[1] === "c") {
					edge_direction = adjustEdgeDirection(edge_direction[0], edge_direction[1], [from.x, from.y], [to.x, to.y]).split(" ");
				}

				if (edge_direction[0] === "e") {
					switch (edge_direction[1]) {
					case "e":
						if (from.x <= to.x) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x + OG.Constants.EDGE_PADDING, to.y],
								true
							);
							points.push([to.x, to.y]);
						} else {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y],
								false
							));
						}
						break;
					case "w":
						if (from.x <= to.x) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y],
								true
							);
						} else {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x - OG.Constants.EDGE_PADDING, to.y],
								false
							));
							points.push([to.x, to.y]);
						}
						break;
					case "s":
						if (from.x <= to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y + OG.Constants.EDGE_PADDING],
								true
							);
							points.push([to.x, to.y]);
						} else if (from.x <= to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								true
							);
						} else if (from.x > to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y + OG.Constants.EDGE_PADDING],
								false
							));
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y],
								false
							));
						}
						break;
					case "n":
						if (from.x <= to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								true
							);
						} else if (from.x <= to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y - OG.Constants.EDGE_PADDING],
								false
							));
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y],
								false
							));
						} else if (from.x > to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x + OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y - OG.Constants.EDGE_PADDING],
								false
							));
							points.push([to.x, to.y]);
						}
						break;
					}
				} else if (edge_direction[0] === "w") {
					switch (edge_direction[1]) {
					case "e":
						if (from.x <= to.x) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x - OG.Constants.EDGE_PADDING, from.y],
								[to.x + OG.Constants.EDGE_PADDING, to.y],
								false
							));
							points.push([to.x, to.y]);
						} else {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y],
								true
							);
						}
						break;
					case "w":
						if (from.x <= to.x) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x - OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y],
								false
							));

						} else {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x - OG.Constants.EDGE_PADDING, to.y],
								true
							);
							points.push([to.x, to.y]);
						}
						break;
					case "s":
						if (from.x <= to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x - OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y + OG.Constants.EDGE_PADDING],
								false
							));
							points.push([to.x, to.y]);
						} else if (from.x <= to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x - OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y],
								false
							));
						} else if (from.x > to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y + OG.Constants.EDGE_PADDING],
								true
							);
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								true
							);
						}
						break;
					case "n":
						if (from.x <= to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x - OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y],
								false
							));
						} else if (from.x <= to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x - OG.Constants.EDGE_PADDING, from.y],
								[to.x, to.y - OG.Constants.EDGE_PADDING],
								false
							));
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y <= to.y) {
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								true
							));
						} else if (from.x > to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y - OG.Constants.EDGE_PADDING],
								true
							);
							points.push([to.x, to.y]);
						}
						break;
					}
				} else if (edge_direction[0] === "s") {
					switch (edge_direction[1]) {
					case "e":
						if (from.x <= to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x + OG.Constants.EDGE_PADDING, to.y],
								false
							);
							points.push([to.x, to.y]);
						} else if (from.x <= to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y + OG.Constants.EDGE_PADDING],
								[to.x + OG.Constants.EDGE_PADDING, to.y],
								true
							));
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								false
							);
						} else if (from.x > to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x, from.y + OG.Constants.EDGE_PADDING],
								[to.x, to.y],
								true
							));
						}
						break;
					case "w":
						if (from.x <= to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								false
							);
						} else if (from.x <= to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x, from.y + OG.Constants.EDGE_PADDING],
								[to.x, to.y],
								true
							));
						} else if (from.x > to.x && from.y <= to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x - OG.Constants.EDGE_PADDING, to.y],
								false
							);
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y > to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y + OG.Constants.EDGE_PADDING],
								[to.x - OG.Constants.EDGE_PADDING, to.y],
								true
							));
							points.push([to.x, to.y]);
						}
						break;
					case "s":
						if (from.y <= to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y + OG.Constants.EDGE_PADDING],
								false
							);
							points.push([to.x, to.y]);
						} else {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y + OG.Constants.EDGE_PADDING],
								[to.x, to.y],
								true
							));
						}
						break;
					case "n":
						if (from.y <= to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y],
								false
							);
						} else {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x, from.y + OG.Constants.EDGE_PADDING],
								[to.x, to.y - OG.Constants.EDGE_PADDING],
								true
							));
							points.push([to.x, to.y]);
						}
						break;
					}
				} else if (edge_direction[0] === "n") {
					switch (edge_direction[1]) {
					case "e":
						if (from.x <= to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y - OG.Constants.EDGE_PADDING],
								[to.x + OG.Constants.EDGE_PADDING, to.y],
								true
							));
							points.push([to.x, to.y]);
						} else if (from.x <= to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x + OG.Constants.EDGE_PADDING, to.y],
								false
							);
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x, from.y - OG.Constants.EDGE_PADDING],
								[to.x, to.y],
								true
							));
						} else if (from.x > to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								false
							);
						}
						break;
					case "w":
						if (from.x <= to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x, from.y - OG.Constants.EDGE_PADDING],
								[to.x, to.y],
								true
							));
						} else if (from.x <= to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y],
								false
							);
						} else if (from.x > to.x && from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y - OG.Constants.EDGE_PADDING],
								[to.x - OG.Constants.EDGE_PADDING, to.y],
								true
							));
							points.push([to.x, to.y]);
						} else if (from.x > to.x && from.y > to.y) {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x - OG.Constants.EDGE_PADDING, to.y],
								false
							);
							points.push([to.x, to.y]);
						}
						break;
					case "s":
						if (from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_2(
								[from.x, from.y - OG.Constants.EDGE_PADDING],
								[to.x, to.y + OG.Constants.EDGE_PADDING],
								true
							));
							points.push([to.x, to.y]);
						} else {
							points = getArrayOfOrthogonal_2(
								[from.x, from.y],
								[to.x, to.y],
								false
							);
						}
						break;
					case "n":
						if (from.y <= to.y) {
							points = [
								[from.x, from.y]
							];
							points = points.concat(getArrayOfOrthogonal_1(
								[from.x, from.y - OG.Constants.EDGE_PADDING],
								[to.x, to.y],
								true
							));
						} else {
							points = getArrayOfOrthogonal_1(
								[from.x, from.y],
								[to.x, to.y - OG.Constants.EDGE_PADDING],
								false
							);
							points.push([to.x, to.y]);
						}
						break;
					}
				}
				break;
			case OG.Constants.EDGE_TYPE.BEZIER:
				// TODO : 베지어곡선
				break;
			}
		} else {
			points = vertices;
		}

		// Draw geometry
		if (isSelf) {
			edge = new OG.Curve(points);
		} else {
			edge = new OG.PolyLine(points);
		}
		drawGeometry(group.node, edge, _style);
		group.node.geom = edge;
		group.attr(OG.Constants.DEFAULT_STYLE.SHAPE);

		if (group.node.shape) {
			group.node.shape.geom = edge;

			if (group.node.geom) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("geom");
				} else {
					delete group.node.geom;
				}
			}
		}

		return group.node;
	};

	/**
	 * 그룹 Geometry 를 캔버스에 드로잉한다.
	 *
	 * @param {OG.geometry.Geometry} geometry 기하 객체
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element} Group DOM Element with geometry
	 * @override
	 */
	this.drawGroup = function (geometry, style, id) {
		var group, geomElement, _style = {}, childNodes, i;

		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.GROUP);

		// ID 지정된 경우 존재하면 하위 노드 제거, 하위에 Shape 은 삭제하지 않도록
		if (id === 0 || id) {
			group = getREleById(id);
			if (group) {
				childNodes = group.node.childNodes;
				for (i = childNodes.length - 1; i >= 0; i--) {
					if ($(childNodes[i]).attr("_type") !== OG.Constants.NODE_TYPE.SHAPE) {
						remove(getREleById(childNodes[i].id));
					}
				}
			} else {
				group = _PAPER.group();
				add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.GROUP);
				_ROOT_GROUP.node.appendChild(group.node);
			}
		} else {
			group = _PAPER.group();
			add(group, id, OG.Constants.NODE_TYPE.SHAPE, OG.Constants.SHAPE_TYPE.GROUP);
			_ROOT_GROUP.node.appendChild(group.node);
		}

		// Draw geometry
		geomElement = drawGeometry(group.node, geometry, _style);
		group.node.geom = geometry;
		group.attr(OG.Constants.DEFAULT_STYLE.SHAPE);

		// 위치조정
		if (geomElement.id !== group.node.firstChild.id) {
			group.node.insertBefore(geomElement, group.node.firstChild);
		}

		if (group.node.shape) {
			if (!group.node.shape.isCollapsed || group.node.shape.isCollapsed === false) {
				group.node.shape.geom = geometry;
			}

			if (group.node.geom) {
				if (OG.Util.isIE7()) {
					group.node.removeAttribute("geom");
				} else {
					delete group.node.geom;
				}
			}
		}

		return group.node;
	};

	/**
	 * Shape 의 Label 을 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @param {Element,String} shapeElement Shape DOM element or ID
	 * @param {String} text 텍스트
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element} DOM Element
	 * @override
	 */
	this.drawLabel = function (shapeElement, text, style) {
		var rElement = getREleById(OG.Util.isElement(shapeElement) ? shapeElement.id : shapeElement),
			element, labelElement, envelope, _style = {}, position, size,
			/**
			 * 라인(꺽은선)의 중심위치를 반환한다.
			 *
			 * @param {Element} element Edge 엘리먼트
			 * @return {OG.Coordinate}
			 */
				getCenterOfEdge = function (element) {
				var vertices, lineLength, distance = 0, i, intersectArray;

				// Edge Shape 인 경우 라인의 중간 지점 찾기
				vertices = element.shape.geom.getVertices();
				lineLength = element.shape.geom.getLength();

				for (i = 0; i < vertices.length - 1; i++) {
					distance += vertices[i].distance(vertices[i + 1]);
					if (distance > lineLength / 2) {
						intersectArray = element.shape.geom.intersectCircleToLine(
							vertices[i + 1], distance - lineLength / 2, vertices[i + 1], vertices[i]
						);

						break;
					}
				}

				return intersectArray[0];
			},
			centerOfEdge;

		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {});

		if (rElement && rElement.node.shape) {
			element = rElement.node;
			envelope = element.shape.geom.getBoundary();

			OG.Util.apply(element.shape.geom.style.map, _style);
			element.shape.label = text === undefined ? element.shape.label : text;

			if (element.shape.label !== undefined) {
				if (element.shape instanceof OG.shape.EdgeShape) {
					centerOfEdge = getCenterOfEdge(element);
					position = [centerOfEdge.x, centerOfEdge.y];
					size = [0, 0];
				} else {
					// label-position 에 따라 위치 조정
					switch (element.shape.geom.style.get("label-position")) {
					case "left":
						position = [envelope.getCentroid().x - envelope.getWidth(), envelope.getCentroid().y];
						break;
					case "right":
						position = [envelope.getCentroid().x + envelope.getWidth(), envelope.getCentroid().y];
						break;
					case "top":
						position = [envelope.getCentroid().x, envelope.getCentroid().y - envelope.getHeight()];
						break;
					case "bottom":
						position = [envelope.getCentroid().x, envelope.getCentroid().y + envelope.getHeight()];
						break;
					default:
						position = [envelope.getCentroid().x, envelope.getCentroid().y];
						break;
					}
					size = [envelope.getWidth(), envelope.getHeight()];
				}

				labelElement = drawLabel(
					position,
					element.shape.label,
					size,
					element.shape.geom.style,
					element.id + OG.Constants.LABEL_SUFFIX,
					element.shape instanceof OG.shape.EdgeShape
				);
				element.appendChild(labelElement);
			}
		}

		return labelElement;
	};

	/**
	 * Element 에 저장된 geom, angle, image, text 정보로 shape 을 redraw 한다.
	 *
	 * @param {Element} element Shape 엘리먼트
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @override
	 */
	this.redrawShape = function (element, excludeEdgeId) {
		var renderer = this, envelope, center, width, height, upperLeft,
			redrawChildConnectedEdge;

		redrawChildConnectedEdge = function (_collapseRootElement, _element) {
			var edgeIdArray, fromEdge, toEdge, _childNodes = _element.childNodes, otherShape, i, j, isNeedToRedraw;
			for (i = _childNodes.length - 1; i >= 0; i--) {
				if ($(_childNodes[i]).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
					redrawChildConnectedEdge(_collapseRootElement, _childNodes[i]);

					isNeedToRedraw = false;
					edgeIdArray = $(_childNodes[i]).attr("_fromedge");
					if (edgeIdArray) {
						edgeIdArray = edgeIdArray.split(",");
						for (j = 0; j < edgeIdArray.length; j++) {
							fromEdge = renderer.getElementById(edgeIdArray[j]);
							if (fromEdge) {
								otherShape = getShapeFromTerminal($(fromEdge).attr("_from"));

								// otherShape 이 같은 collapse 범위내에 있는지 체크
								if ($(otherShape).parents("#" + _collapseRootElement.id).length === 0) {
									isNeedToRedraw = true;
								}
							}
						}
					}

					edgeIdArray = $(_childNodes[i]).attr("_toedge");
					if (edgeIdArray) {
						edgeIdArray = edgeIdArray.split(",");
						for (j = 0; j < edgeIdArray.length; j++) {
							toEdge = renderer.getElementById(edgeIdArray[j]);
							if (toEdge) {
								otherShape = getShapeFromTerminal($(toEdge).attr("_to"));

								// otherShape 이 같은 collapse 범위내에 있는지 체크
								if ($(otherShape).parents("#" + _collapseRootElement.id).length === 0) {
									isNeedToRedraw = true;
								}
							}
						}
					}

					// group 영역 밖의 연결된 otherShape 이 있는 경우 redrawConnectedEdge
					if (isNeedToRedraw === true) {
						renderer.redrawConnectedEdge(_childNodes[i]);
					}
				}
			}
		};

		if (element && element.shape.geom) {
			switch ($(element).attr("_shape")) {
			case OG.Constants.SHAPE_TYPE.GEOM:
				element = this.drawGeom(element.shape.geom, element.shape.geom.style, element.id);
				this.redrawConnectedEdge(element, excludeEdgeId);
				this.drawLabel(element);
				break;
			case OG.Constants.SHAPE_TYPE.TEXT:
				envelope = element.shape.geom.getBoundary();
				center = envelope.getCentroid();
				width = envelope.getWidth();
				height = envelope.getHeight();
				element = this.drawText([center.x, center.y], element.shape.text,
					[width, height, element.shape.angle], element.shape.geom.style, element.id);
				this.redrawConnectedEdge(element, excludeEdgeId);
				break;
			case OG.Constants.SHAPE_TYPE.IMAGE:
				envelope = element.shape.geom.getBoundary();
				center = envelope.getCentroid();
				width = envelope.getWidth();
				height = envelope.getHeight();
				element = this.drawImage([center.x, center.y], element.shape.image,
					[width, height, element.shape.angle], element.shape.geom.style, element.id);
				this.redrawConnectedEdge(element, excludeEdgeId);
				this.drawLabel(element);
				break;
			case OG.Constants.SHAPE_TYPE.EDGE:
				element = this.drawEdge(element.shape.geom, element.shape.geom.style, element.id);
				this.drawLabel(element);
				break;
			case OG.Constants.SHAPE_TYPE.GROUP:
				if (element.shape.isCollapsed) {
					envelope = element.shape.geom.getBoundary();
					upperLeft = envelope.getUpperLeft();
					element = this.drawGroup(new OG.geometry.Rectangle(
						upperLeft, OG.Constants.COLLAPSE_SIZE * 3, OG.Constants.COLLAPSE_SIZE * 2),
						element.shape.geom.style, element.id);
					redrawChildConnectedEdge(element, element);
				} else {
					element = this.drawGroup(element.shape.geom, element.shape.geom.style, element.id);
					this.redrawConnectedEdge(element, excludeEdgeId);
					this.drawLabel(element);
				}
				break;
			}
		}

		return element;
	};

	/**
	 * Shape 의 연결된 Edge 를 redraw 한다.(이동 또는 리사이즈시)
	 *
	 * @param {Element} element
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @override
	 */
	this.redrawConnectedEdge = function (element, excludeEdgeId) {
		var edgeId, renderer = this,
			draw = function (item) {
				var edge, fromTerminalId, toTerminalId, fromShape, toShape, fromTerminalNum, toTerminalNum,
					fromTerminal, toTerminal, vertices, fromDrct, toDrct, fromXY, toXY,
					orgFromXY, orgToXY, orgFromDrct, orgToDrct, intersectionInfo, isSelf,
					collapsedParents, collapsedEnvelope, collapsedUpperLeft, collapsedGeom, collapsedPosition;

				edge = renderer.getElementById(item);

				// ex) OG_3312_1_TERMINAL_E_INOUT_0
				fromTerminalId = $(edge).attr("_from");
				toTerminalId = $(edge).attr("_to");

				if (fromTerminalId) {
					fromShape = getShapeFromTerminal(fromTerminalId);
					fromTerminalNum = parseInt(fromTerminalId.substring(fromTerminalId.lastIndexOf("_") + 1), 10);
					fromTerminal = fromShape.shape.createTerminal()[fromTerminalNum];
					fromDrct = fromTerminal.direction.toLowerCase();
					fromXY = fromTerminal.position;
				} else {
					vertices = edge.shape.geom.getVertices();
					fromDrct = "c";
					fromXY = vertices[0];
				}

				if (toTerminalId) {
					toShape = getShapeFromTerminal(toTerminalId);
					toTerminalNum = parseInt(toTerminalId.substring(toTerminalId.lastIndexOf("_") + 1), 10);
					toTerminal = toShape.shape.createTerminal()[toTerminalNum];
					toDrct = toTerminal.direction.toLowerCase();
					toXY = toTerminal.position;
				} else {
					vertices = edge.shape.geom.getVertices();
					toDrct = "c";
					toXY = vertices[vertices.length - 1];
				}

				// backup edge-direction
				orgFromXY = fromXY;
				orgToXY = toXY;
				orgFromDrct = fromDrct;
				orgToDrct = toDrct;

				// direction 이 c 인 경우에 대한 처리(센터 연결)
				if (fromShape && fromDrct === "c") {
					intersectionInfo = renderer.intersectionEdge(
						edge.shape.geom.style.get("edge-type"), fromShape, [orgFromXY.x, orgFromXY.y], [orgToXY.x, orgToXY.y], true
					);
					fromXY = intersectionInfo.position;
					fromDrct = intersectionInfo.direction;
				}
				if (toShape && toDrct === "c") {
					intersectionInfo = renderer.intersectionEdge(
						edge.shape.geom.style.get("edge-type"), toShape, [orgFromXY.x, orgFromXY.y], [orgToXY.x, orgToXY.y], false
					);
					toXY = intersectionInfo.position;
					toDrct = intersectionInfo.direction;
				}

				isSelf = fromShape && toShape && fromShape.id === toShape.id;
				if (isSelf) {
					fromXY = toXY = fromShape.shape.geom.getBoundary().getRightCenter();
				} else {
					// fromShape 이 collapsed 인지 체크
					if (fromShape) {
						collapsedParents = $(fromShape).parents("[_collapsed=true]");
						if (collapsedParents.length !== 0) {
							// collapsed 인 경우
							collapsedEnvelope = collapsedParents[collapsedParents.length - 1].shape.geom.getBoundary();
							collapsedUpperLeft = collapsedEnvelope.getUpperLeft();
							collapsedGeom = new OG.geometry.Rectangle(
								collapsedUpperLeft, OG.Constants.COLLAPSE_SIZE * 3, OG.Constants.COLLAPSE_SIZE * 2);

							switch (fromDrct.toUpperCase()) {
							case OG.Constants.TERMINAL_TYPE.E:
								collapsedPosition = collapsedGeom.getBoundary().getRightCenter();
								break;
							case OG.Constants.TERMINAL_TYPE.W:
								collapsedPosition = collapsedGeom.getBoundary().getLeftCenter();
								break;
							case OG.Constants.TERMINAL_TYPE.S:
								collapsedPosition = collapsedGeom.getBoundary().getLowerCenter();
								break;
							case OG.Constants.TERMINAL_TYPE.N:
								collapsedPosition = collapsedGeom.getBoundary().getUpperCenter();
								break;
							}
							if (collapsedPosition) {
								fromXY = [collapsedPosition.x, collapsedPosition.y];
							}
						}
					}

					// toShape 이 collapsed 인지 체크
					if (toShape) {
						collapsedParents = $(toShape).parents("[_collapsed=true]");
						if (collapsedParents.length !== 0) {
							// collapsed 인 경우
							collapsedEnvelope = collapsedParents[collapsedParents.length - 1].shape.geom.getBoundary();
							collapsedUpperLeft = collapsedEnvelope.getUpperLeft();
							collapsedGeom = new OG.geometry.Rectangle(
								collapsedUpperLeft, OG.Constants.COLLAPSE_SIZE * 3, OG.Constants.COLLAPSE_SIZE * 2);

							switch (toDrct.toUpperCase()) {
							case OG.Constants.TERMINAL_TYPE.E:
								collapsedPosition = collapsedGeom.getBoundary().getRightCenter();
								break;
							case OG.Constants.TERMINAL_TYPE.W:
								collapsedPosition = collapsedGeom.getBoundary().getLeftCenter();
								break;
							case OG.Constants.TERMINAL_TYPE.S:
								collapsedPosition = collapsedGeom.getBoundary().getLowerCenter();
								break;
							case OG.Constants.TERMINAL_TYPE.N:
								collapsedPosition = collapsedGeom.getBoundary().getUpperCenter();
								break;
							}
							if (collapsedPosition) {
								toXY = [collapsedPosition.x, collapsedPosition.y];
							}
						}
					}
				}

				// redraw edge
				edge = renderer.drawEdge(new OG.Line(fromXY, toXY),
					OG.Util.apply(edge.shape.geom.style.map, {"edge-direction": fromDrct + " " + toDrct}), edge.id, isSelf);

				// Draw Label
				renderer.drawLabel(edge);

				// restore edge-direction
				OG.Util.apply(edge.shape.geom.style.map, {"edge-direction": orgFromDrct + " " + orgToDrct});
			};

		edgeId = $(element).attr("_fromedge");
		if (edgeId) {
			$.each(edgeId.split(","), function (idx, item) {
				if (!excludeEdgeId || excludeEdgeId.toString().indexOf(item) < 0) {
					draw(item);
				}
			});
		}

		edgeId = $(element).attr("_toedge");
		if (edgeId) {
			$.each(edgeId.split(","), function (idx, item) {
				if (!excludeEdgeId || excludeEdgeId.toString().indexOf(item) < 0) {
					draw(item);
				}
			});
		}

		this.removeAllTerminal();
	};

	/**
	 * 두개의 터미널을 연결하고, 속성정보에 추가한다.
	 *
	 * @param {Element,Number[]} from 시작점
	 * @param {Element,Number[]} to 끝점
	 * @param {Element} edge Edge Shape
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} label Label
	 * @return {Element}
	 * @override
	 */
	this.connect = function (from, to, edge, style, label) {
		var _style = {}, fromShape, toShape, intersectionInfo, fromXY, toXY,
			orgFromXY, orgToXY, fromDrct, toDrct, orgFromDrct, orgToDrct, isSelf,
			addAttrValues = function (element, name, value) {
				var attrValue = $(element).attr(name),
					array = attrValue ? attrValue.split(",") : [],
					newArray = [];
				$.each(array, function (idx, item) {
					if (item !== value) {
						newArray.push(item);
					}
				});
				newArray.push(value);

				$(element).attr(name, newArray.toString());
				return element;
			};

		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.EDGE);

		// 연결 시작, 끝 Shape
		if (OG.Util.isElement(from)) {
			fromShape = getShapeFromTerminal(from);
			fromXY = [from.terminal.position.x, from.terminal.position.y];
			fromDrct = from.terminal.direction.toLowerCase();
		} else {
			fromXY = from;
			fromDrct = "c";
		}
		if (OG.Util.isElement(to)) {
			toShape = getShapeFromTerminal(to);
			toXY = [to.terminal.position.x, to.terminal.position.y];
			toDrct = to.terminal.direction.toLowerCase();
		} else {
			toXY = to;
			toDrct = "c";
		}

		// backup edge-direction
		orgFromXY = fromXY;
		orgToXY = toXY;
		orgFromDrct = fromDrct;
		orgToDrct = toDrct;

		// direction 이 c 인 경우에 대한 처리(센터 연결)
		if (fromShape && fromDrct === "c") {
			intersectionInfo = this.intersectionEdge(_style["edge-type"], fromShape, orgFromXY, orgToXY, true);
			fromXY = intersectionInfo.position;
			fromDrct = intersectionInfo.direction;
		}
		if (toShape && toDrct === "c") {
			intersectionInfo = this.intersectionEdge(_style["edge-type"], toShape, orgFromXY, orgToXY, false);
			toXY = intersectionInfo.position;
			toDrct = intersectionInfo.direction;
		}

		isSelf = fromShape && toShape && fromShape.id === toShape.id;
		if (isSelf) {
			fromXY = toXY = fromShape.shape.geom.getBoundary().getRightCenter();
		}

		// 라인 드로잉
		edge = this.drawEdge(new OG.Line(fromXY, toXY),
			OG.Util.apply(_style, {"edge-direction": fromDrct + " " + toDrct}), edge ? edge.id : null, isSelf);

		// Draw Label
		this.drawLabel(edge, label);

		// restore edge-direction
		OG.Util.apply(edge.shape.geom.style.map, {"edge-direction": orgFromDrct + " " + orgToDrct});

		// 이전 연결속성정보 삭제
		this.disconnect(edge);

		// 연결 노드 정보 설정
		if (OG.Util.isElement(from)) {
			$(edge).attr("_from", from.id);
			addAttrValues(fromShape, "_toedge", edge.id);
		}

		if (OG.Util.isElement(to)) {
			$(edge).attr("_to", to.id);
			addAttrValues(toShape, "_fromedge", edge.id);
		}

		this.removeAllTerminal();

		return edge;
	};

	/**
	 * 연결속성정보를 삭제한다. Edge 인 경우는 연결 속성정보만 삭제하고, 일반 Shape 인 경우는 연결된 모든 Edge 를 삭제한다.
	 *
	 * @param {Element} element
	 * @override
	 */
	this.disconnect = function (element) {
		var renderer = this, fromTerminalId, toTerminalId, fromShape, toShape, fromEdgeId, toEdgeId, fromEdge, toEdge,
			removeAttrValue = function (element, name, value) {
				var attrValue = $(element).attr(name),
					array = attrValue ? attrValue.split(",") : [],
					newArray = [];
				$.each(array, function (idx, item) {
					if (item !== value) {
						newArray.push(item);
					}
				});

				$(element).attr(name, newArray.toString());
				return element;
			};

		if (element) {
			if ($(element).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
				// Edge 인 경우 연결된 Shape 의 연결 속성 정보를 삭제
				fromTerminalId = $(element).attr("_from");
				toTerminalId = $(element).attr("_to");

				if (fromTerminalId) {
					fromShape = getShapeFromTerminal(fromTerminalId);
					removeAttrValue(fromShape, "_toedge", element.id);
					$(element).removeAttr("_from");
				}

				if (toTerminalId) {
					toShape = getShapeFromTerminal(toTerminalId);
					removeAttrValue(toShape, "_fromedge", element.id);
					$(element).removeAttr("_to");
				}
			} else {
				// 일반 Shape 인 경우 연결된 모든 Edge 와 속성 정보를 삭제
				fromEdgeId = $(element).attr("_fromedge");
				toEdgeId = $(element).attr("_toedge");

				if (fromEdgeId) {
					$.each(fromEdgeId.split(","), function (idx, item) {
						fromEdge = renderer.getElementById(item);

						fromTerminalId = $(fromEdge).attr("_from");
						if (fromTerminalId) {
							fromShape = getShapeFromTerminal(fromTerminalId);
							removeAttrValue(fromShape, "_toedge", item);
						}
						renderer.remove(fromEdge);
					});
				}

				if (toEdgeId) {
					$.each(toEdgeId.split(","), function (idx, item) {
						toEdge = renderer.getElementById(item);

						toTerminalId = $(toEdge).attr("_to");
						if (toTerminalId) {
							toShape = getShapeFromTerminal(toTerminalId);
							removeAttrValue(toShape, "_fromedge", item);
						}
						renderer.remove(toEdge);
					});
				}
			}
		}
	};

	/**
	 * ID에 해당하는 Element 의 Edge 연결시 Drop Over 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.drawDropOverGuide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			geometry = rElement ? rElement.node.shape.geom : null,
			envelope, _upperLeft, _bBoxRect,
			_size = OG.Constants.GUIDE_RECT_SIZE / 2,
			_hSize = _size / 2;

		if (rElement && geometry && $(element).attr("_shape") !== OG.Constants.SHAPE_TYPE.EDGE &&
			!getREleById(rElement.id + OG.Constants.DROP_OVER_BBOX_SUFFIX)) {
			envelope = geometry.getBoundary();
			_upperLeft = envelope.getUpperLeft();

			// guide line 랜더링
			_bBoxRect = _PAPER.rect(_upperLeft.x - _hSize, _upperLeft.y - _hSize, envelope.getWidth() + _size, envelope.getHeight() + _size);
			_bBoxRect.attr(OG.Util.apply({'stroke-width': _size}, OG.Constants.DEFAULT_STYLE.DROP_OVER_BBOX));
			add(_bBoxRect, rElement.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);

			// layer 위치 조정
			_bBoxRect.insertAfter(rElement);
		}
	};

	/**
	 * ID에 해당하는 Element 의 Move & Resize 용 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Object}
	 * @override
	 */
	this.drawGuide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			geometry = rElement ? rElement.node.shape.geom : null,
			envelope,
			group, guide,
			_bBoxRect,
			_upperLeft, _upperRight, _lowerLeft, _lowerRight, _leftCenter, _upperCenter, _rightCenter, _lowerCenter,
			_ulRect, _urRect, _llRect, _lrRect, _lcRect, _ucRect, _rcRect, _lwcRect,
			_size = OG.Constants.GUIDE_RECT_SIZE, _hSize = OG.Util.round(_size / 2);

		if (rElement && geometry) {
			// Edge 인 경우 따로 처리
			if ($(element).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
				return this.drawEdgeGuide(element);
			} else {
				envelope = geometry.getBoundary();
				_upperLeft = envelope.getUpperLeft();
				_upperRight = envelope.getUpperRight();
				_lowerLeft = envelope.getLowerLeft();
				_lowerRight = envelope.getLowerRight();
				_leftCenter = envelope.getLeftCenter();
				_upperCenter = envelope.getUpperCenter();
				_rightCenter = envelope.getRightCenter();
				_lowerCenter = envelope.getLowerCenter();

				if (getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE)) {
					// 가이드가 이미 존재하는 경우에는 bBox 만 삭제후 새로 draw
					// bBox remove -> redraw
					remove(getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX));
					_bBoxRect = _PAPER.rect(_upperLeft.x, _upperLeft.y, envelope.getWidth(), envelope.getHeight());
					_bBoxRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
					add(_bBoxRect, rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX);
					_bBoxRect.insertBefore(rElement);

					_ulRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.UL);
					_urRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.UR);
					_llRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.LL);
					_lrRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.LR);
					_lcRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.LC);
					_ucRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.UC);
					_rcRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.RC);
					_lwcRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.LWC);

					_ulRect.attr({x: _upperLeft.x - _hSize, y: _upperLeft.y - _hSize});
					_urRect.attr({x: _upperRight.x - _hSize, y: _upperRight.y - _hSize});
					_llRect.attr({x: _lowerLeft.x - _hSize, y: _lowerLeft.y - _hSize});
					_lrRect.attr({x: _lowerRight.x - _hSize, y: _lowerRight.y - _hSize});
					_lcRect.attr({x: _leftCenter.x - _hSize, y: _leftCenter.y - _hSize});
					_ucRect.attr({x: _upperCenter.x - _hSize, y: _upperCenter.y - _hSize});
					_rcRect.attr({x: _rightCenter.x - _hSize, y: _rightCenter.y - _hSize});
					_lwcRect.attr({x: _lowerCenter.x - _hSize, y: _lowerCenter.y - _hSize});

					return null;
				}

				// group
				group = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE);
				if (group) {
					remove(group);
					remove(getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX));
				}
				group = _PAPER.group();

				// guide line 랜더링
				_bBoxRect = _PAPER.rect(_upperLeft.x, _upperLeft.y, envelope.getWidth(), envelope.getHeight());
				_ulRect = _PAPER.rect(_upperLeft.x - _hSize, _upperLeft.y - _hSize, _size, _size);
				_urRect = _PAPER.rect(_upperRight.x - _hSize, _upperRight.y - _hSize, _size, _size);
				_llRect = _PAPER.rect(_lowerLeft.x - _hSize, _lowerLeft.y - _hSize, _size, _size);
				_lrRect = _PAPER.rect(_lowerRight.x - _hSize, _lowerRight.y - _hSize, _size, _size);
				_lcRect = _PAPER.rect(_leftCenter.x - _hSize, _leftCenter.y - _hSize, _size, _size);
				_ucRect = _PAPER.rect(_upperCenter.x - _hSize, _upperCenter.y - _hSize, _size, _size);
				_rcRect = _PAPER.rect(_rightCenter.x - _hSize, _rightCenter.y - _hSize, _size, _size);
				_lwcRect = _PAPER.rect(_lowerCenter.x - _hSize, _lowerCenter.y - _hSize, _size, _size);

				_bBoxRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
				_ulRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UL);
				_urRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UR);
				_llRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LL);
				_lrRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LR);
				_lcRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LC);
				_ucRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_UC);
				_rcRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_RC);
				_lwcRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_LWC);

				// add to Group
				group.appendChild(_ulRect);
				group.appendChild(_urRect);
				group.appendChild(_llRect);
				group.appendChild(_lrRect);
				group.appendChild(_lcRect);
				group.appendChild(_ucRect);
				group.appendChild(_rcRect);
				group.appendChild(_lwcRect);

				add(group, rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE);
				add(_bBoxRect, rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX);
				add(_ulRect, rElement.id + OG.Constants.GUIDE_SUFFIX.UL);
				add(_urRect, rElement.id + OG.Constants.GUIDE_SUFFIX.UR);
				add(_llRect, rElement.id + OG.Constants.GUIDE_SUFFIX.LL);
				add(_lrRect, rElement.id + OG.Constants.GUIDE_SUFFIX.LR);
				add(_lcRect, rElement.id + OG.Constants.GUIDE_SUFFIX.LC);
				add(_ucRect, rElement.id + OG.Constants.GUIDE_SUFFIX.UC);
				add(_rcRect, rElement.id + OG.Constants.GUIDE_SUFFIX.RC);
				add(_lwcRect, rElement.id + OG.Constants.GUIDE_SUFFIX.LWC);

				// guide 정의
				guide = {
					bBox : _bBoxRect.node,
					group: group.node,
					ul   : _ulRect.node,
					ur   : _urRect.node,
					ll   : _llRect.node,
					lr   : _lrRect.node,
					lc   : _lcRect.node,
					uc   : _ucRect.node,
					rc   : _rcRect.node,
					lwc  : _lwcRect.node
				};

				// layer 위치 조정
				_bBoxRect.insertBefore(rElement);
				group.insertAfter(rElement);

				// selected 속성값 설정
				$(rElement.node).attr("_selected", "true");

				return guide;
			}
		}

		return null;
	};

	/**
	 * ID에 해당하는 Element 의 Move & Resize 용 가이드를 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.removeGuide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			guide = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE),
			bBox = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX);

		rElement.node.removeAttribute("_selected");
		remove(guide);
		remove(bBox);
	};

	/**
	 * ID에 해당하는 Edge Element 의 Move & Resize 용 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Object}
	 * @override
	 */
	this.drawEdgeGuide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			geometry = rElement ? rElement.node.shape.geom : null,
			vertices, isSelf,
			group, guide, pathStr,
			_bBoxLine, _fromRect, _toRect, _controlRect, controlNode = [],
			_size = OG.Constants.GUIDE_RECT_SIZE, _hSize = OG.Util.round(_size / 2),
			i;

		if (rElement && geometry) {
			vertices = geometry.getVertices();

			isSelf = $(element).attr("_from") && $(element).attr("_to") && $(element).attr("_from") === $(element).attr("_to");

			if (getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE)) {
				// 가이드가 이미 존재하는 경우에는 bBoxLine 만 삭제후 새로 draw 하고 나머지 guide 는 Update 한다.
				// bBoxLine remove -> redraw
				remove(getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX));
				pathStr = "";
				for (i = 0; i < vertices.length; i++) {
					if (i === 0) {
						pathStr = "M" + vertices[i].x + " " + vertices[i].y;
					} else {
						pathStr += "L" + vertices[i].x + " " + vertices[i].y;
					}
				}
				_bBoxLine = _PAPER.path(pathStr);
				_bBoxLine.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);
				add(_bBoxLine, rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX);
				_bBoxLine.insertBefore(rElement);

				// 시작지점 가이드 Update
				_fromRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.FROM);
				_fromRect.attr({x: vertices[0].x - _hSize, y: vertices[0].y - _hSize});

				// 종료지점 가이드 Update
				_toRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.TO);
				_toRect.attr({x: vertices[vertices.length - 1].x - _hSize, y: vertices[vertices.length - 1].y - _hSize});

				// 콘트롤 가이드 Update
				if (!isSelf) {
					for (i = 1; i < vertices.length - 2; i++) {
						if (vertices[i].x === vertices[i + 1].x) {
							_controlRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.CTL_H + i);
							if (_controlRect) {
								_controlRect.attr({
									x: vertices[i].x - _hSize,
									y: OG.Util.round((vertices[i].y + vertices[i + 1].y) / 2) - _hSize
								});
							}
						} else {
							_controlRect = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.CTL_V + i);
							if (_controlRect) {
								_controlRect.attr({
									x: OG.Util.round((vertices[i].x + vertices[i + 1].x) / 2) - _hSize,
									y: vertices[i].y - _hSize
								});
							}
						}
					}
				}

				return null;
			}

			// group
			group = getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE);
			if (group) {
				remove(group);
				remove(getREleById(rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX));
			}
			group = _PAPER.group();

			// 쉐도우 가이드
			pathStr = "";
			for (i = 0; i < vertices.length; i++) {
				if (i === 0) {
					pathStr = "M" + vertices[i].x + " " + vertices[i].y;
				} else {
					pathStr += "L" + vertices[i].x + " " + vertices[i].y;
				}
			}
			_bBoxLine = _PAPER.path(pathStr);
			_bBoxLine.attr(OG.Constants.DEFAULT_STYLE.GUIDE_BBOX);

			// 시작지점 가이드
			_fromRect = _PAPER.rect(vertices[0].x - _hSize, vertices[0].y - _hSize, _size, _size);
			_fromRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_FROM);
			group.appendChild(_fromRect);
			add(_fromRect, rElement.id + OG.Constants.GUIDE_SUFFIX.FROM);

			// 종료지점 가이드
			_toRect = _PAPER.rect(vertices[vertices.length - 1].x - _hSize, vertices[vertices.length - 1].y - _hSize, _size, _size);
			_toRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_TO);
			group.appendChild(_toRect);
			add(_toRect, rElement.id + OG.Constants.GUIDE_SUFFIX.TO);

			// 콘트롤 가이드
			if (!isSelf) {
				for (i = 1; i < vertices.length - 2; i++) {
					if (vertices[i].x === vertices[i + 1].x) {
						_controlRect = _PAPER.rect(vertices[i].x - _hSize,
							OG.Util.round((vertices[i].y + vertices[i + 1].y) / 2) - _hSize, _size, _size);
						_controlRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_CTL_H);
						add(_controlRect, rElement.id + OG.Constants.GUIDE_SUFFIX.CTL_H + i);
					} else {
						_controlRect = _PAPER.rect(OG.Util.round((vertices[i].x + vertices[i + 1].x) / 2) - _hSize,
							vertices[i].y - _hSize, _size, _size);
						_controlRect.attr(OG.Constants.DEFAULT_STYLE.GUIDE_CTL_V);
						add(_controlRect, rElement.id + OG.Constants.GUIDE_SUFFIX.CTL_V + i);
					}
					group.appendChild(_controlRect);
					controlNode.push(_controlRect.node);
				}
			}
			add(_bBoxLine, rElement.id + OG.Constants.GUIDE_SUFFIX.BBOX);
			add(group, rElement.id + OG.Constants.GUIDE_SUFFIX.GUIDE);

			// guide 정의
			guide = {
				bBox    : _bBoxLine.node,
				group   : group.node,
				from    : _fromRect.node,
				to      : _toRect.node,
				controls: controlNode
			};

			// layer 위치 조정
			_bBoxLine.insertBefore(rElement);
			group.insertAfter(rElement);

			// selected 속성값 설정
			$(rElement.node).attr("_selected", "true");

			return guide;
		}

		return null;
	};

	/**
	 * Rectangle 모양의 마우스 드래그 선택 박스 영역을 드로잉한다.
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(좌상단)
	 * @param {Number[]} size Text Width, Height, Angle
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @return {Element} DOM Element
	 * @override
	 */
	this.drawRubberBand = function (position, size, style) {
		var x = position ? position[0] : 0,
			y = position ? position[1] : 0,
			width = size ? size[0] : 0,
			height = size ? size[1] : 0,
			rect = getREleById(OG.Constants.RUBBER_BAND_ID),
			_style = {};
		if (rect) {
			rect.attr({
				x     : x,
				y     : y,
				width : Math.abs(width),
				height: Math.abs(height)
			});
			return rect;
		}
		OG.Util.apply(_style, (style instanceof OG.geometry.Style) ? style.map : style || {}, OG.Constants.DEFAULT_STYLE.RUBBER_BAND);
		rect = _PAPER.rect(x, y, width, height).attr(_style);
		add(rect, OG.Constants.RUBBER_BAND_ID);

		return rect.node;
	};

	/**
	 * Rectangle 모양의 마우스 드래그 선택 박스 영역을 제거한다.
	 *
	 * @param {Element} root first, rubberBand 정보를 저장한 엘리먼트
	 * @override
	 */
	this.removeRubberBand = function (root) {
		this.setAttr(OG.Constants.RUBBER_BAND_ID, {x: 0, y: 0, width: 0, height: 0});
		$(root).removeData("dragBox_first");
		$(root).removeData("rubberBand");
	};

	/**
	 * Edge 연결용 터미널을 드로잉한다.
	 *
	 * @param {Element} element DOM Element
	 * @return {Element} terminal group element
	 * @override
	 */
	this.drawTerminal = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			terminals = rElement ? rElement.node.shape.createTerminal() : null,
			envelope = rElement ? rElement.node.shape.geom.getBoundary() : null,
			group, cross, rect, x, y, size = OG.Constants.TERMINAL_SIZE, rect_gap = size * 2;

		if (rElement && terminals && terminals.length > 0) {
			group = getREleById(rElement.id + OG.Constants.TERMINAL_SUFFIX.GROUP);
			rect = getREleById(rElement.id + OG.Constants.TERMINAL_SUFFIX.BOX);
			if (group || rect) {
				return {
					bBox    : rect.node,
					terminal: group.node
				};
			}

			// group
			group = _PAPER.group();

			// hidden box
			rect = _PAPER.rect(envelope.getUpperLeft().x - rect_gap, envelope.getUpperLeft().y - rect_gap,
				envelope.getWidth() + rect_gap * 2, envelope.getHeight() + rect_gap * 2);
			rect.attr(OG.Constants.DEFAULT_STYLE.TERMINAL_BBOX);
			add(rect, rElement.id + OG.Constants.TERMINAL_SUFFIX.BOX);

			// terminal
			$.each(terminals, function (idx, item) {
				x = item.position.x;
				y = item.position.y;

				cross = _PAPER.circle(x, y, size);
				cross.attr(OG.Constants.DEFAULT_STYLE.TERMINAL);
				cross.node.terminal = item;

				group.appendChild(cross);
				add(cross, rElement.id + OG.Constants.TERMINAL_SUFFIX.GROUP + "_" + item.direction + "_" + item.inout + "_" + idx);
			});

			add(group, rElement.id + OG.Constants.TERMINAL_SUFFIX.GROUP);

			// layer 위치 조정
			rect.insertBefore(rElement);
			group.insertAfter(rElement);

			return {
				bBox    : rect.node,
				terminal: group.node
			};
		}

		return null;
	};

	/**
	 *  Edge 연결용 터미널을 remove 한다.
	 *
	 * @param {Element} element DOM Element
	 * @override
	 */
	this.removeTerminal = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			group, rect;

		if (rElement) {
			// group
			group = getREleById(rElement.id + OG.Constants.TERMINAL_SUFFIX.GROUP);
			if (group) {
				remove(group);
			}
			rect = getREleById(rElement.id + OG.Constants.TERMINAL_SUFFIX.BOX);
			if (rect) {
				remove(rect);
			}
		}
	};

	/**
	 *  모든 Edge 연결용 터미널을 remove 한다.
	 *
	 * @override
	 */
	this.removeAllTerminal = function () {
		var renderer = this;
		$.each(_ELE_MAP.keys(), function (idx, item) {
			renderer.removeTerminal(item);
		});
	};

	/**
	 * ID에 해당하는 Element 의 Collapse 가이드를 드로잉한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Element}
	 * @override
	 */
	this.drawCollapseGuide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			geometry = rElement ? rElement.node.shape.geom : null,
			envelope, _upperLeft, _bBoxRect, _rect,
			_size = OG.Constants.COLLAPSE_SIZE,
			_hSize = _size / 2;

		if (rElement && geometry && $(element).attr("_shape") === OG.Constants.SHAPE_TYPE.GROUP) {
			_bBoxRect = getREleById(rElement.id + OG.Constants.COLLAPSE_BBOX_SUFFIX);
			if (_bBoxRect) {
				remove(_bBoxRect);
			}
			_rect = getREleById(rElement.id + OG.Constants.COLLAPSE_SUFFIX);
			if (_rect) {
				remove(_rect);
			}

			envelope = geometry.getBoundary();
			_upperLeft = envelope.getUpperLeft();

			// hidden box
			_bBoxRect = _PAPER.rect(envelope.getUpperLeft().x - _size, envelope.getUpperLeft().y - _size,
				envelope.getWidth() + _size * 2, envelope.getHeight() + _size * 2);
			_bBoxRect.attr(OG.Constants.DEFAULT_STYLE.COLLAPSE_BBOX);
			add(_bBoxRect, rElement.id + OG.Constants.COLLAPSE_BBOX_SUFFIX);

			if (rElement.node.shape.isCollapsed === true) {
				// expand 랜더링
				_rect = _PAPER.path(
					"M" + (_upperLeft.x + _hSize) + " " + (_upperLeft.y + _hSize) +
						"h" + _size + "v" + _size + "h" + (-1 * _size) + "v" + (-1 * _size) +
						"m1 " + _hSize + "h" + (_size - 2) + "M" +
						(_upperLeft.x + _hSize) + " " + (_upperLeft.y + _hSize) +
						"m" + _hSize + " 1v" + (_size - 2)
				);
			} else {
				// collapse 랜더링
				_rect = _PAPER.path("M" + (_upperLeft.x + _hSize) + " " +
					(_upperLeft.y + _hSize) + "h" + _size + "v" + _size + "h" + (-1 * _size) + "v" + (-1 * _size) +
					"m1 " + _hSize + "h" + (_size - 2));
			}

			_rect.attr(OG.Constants.DEFAULT_STYLE.COLLAPSE);
			add(_rect, rElement.id + OG.Constants.COLLAPSE_SUFFIX);

			// layer 위치 조정
			_bBoxRect.insertBefore(rElement);
			_rect.insertAfter(rElement);

			return {
				bBox    : _bBoxRect.node,
				collapse: _rect.node
			};
		}

		return null;
	};

	/**
	 * ID에 해당하는 Element 의 Collapse 가이드를 제거한다.
	 *
	 * @param {Element} element
	 * @override
	 */
	this.removeCollapseGuide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			_bBoxRect, _rect;

		if (rElement) {
			_bBoxRect = getREleById(rElement.id + OG.Constants.COLLAPSE_BBOX_SUFFIX);
			if (_bBoxRect) {
				remove(_bBoxRect);
			}
			_rect = getREleById(rElement.id + OG.Constants.COLLAPSE_SUFFIX);
			if (_rect) {
				remove(_rect);
			}
		}
	};

	/**
	 * 주어진 Shape 들을 그룹핑한다.
	 *
	 * @param {Element[]} elements
	 * @return {Element} Group Shape Element
	 * @override
	 */
	this.group = function (elements) {
		var groupShapeEle, geometryArray = [], geometryCollection, envelope, position, shape, size, i;

		if (elements && elements.length > 1) {
			// 그룹핑할 Shape 의 전체 영역 계산
			for (i = 0; i < elements.length; i++) {
				geometryArray.push(elements[i].shape.geom);
			}
			geometryCollection = new OG.GeometryCollection(geometryArray);
			envelope = geometryCollection.getBoundary();

			// 위치 및 사이즈 설정
			position = [envelope.getCentroid().x, envelope.getCentroid().y];
			shape = new OG.GroupShape();
			size = [envelope.getWidth(), envelope.getHeight()];

			// draw group
			groupShapeEle = this.drawShape(position, shape, size);

			// append child
			for (i = 0; i < elements.length; i++) {
				groupShapeEle.appendChild(elements[i]);
			}
		}

		return groupShapeEle;
	};

	/**
	 * 주어진 그룹들을 그룹해제한다.
	 *
	 * @param {Element[]} groupElements
	 * @return {Element[]} ungrouped Elements
	 * @override
	 */
	this.ungroup = function (groupElements) {
		var ungroupElements = [], children, i, j;
		if (groupElements && groupElements.length > 0) {
			for (i = 0; i < groupElements.length; i++) {
				children = $(groupElements[i]).children("[_type='" + OG.Constants.NODE_TYPE.SHAPE + "']");
				for (j = 0; j < children.length; j++) {
					groupElements[i].parentNode.appendChild(children[j]);
					ungroupElements.push(children[j]);
				}
				this.removeShape(groupElements[i]);
			}
		}

		return ungroupElements;
	};

	/**
	 * 주어진 Shape 들을 그룹에 추가한다.
	 *
	 * @param {Element} groupElement
	 * @param {Element[]} elements
	 * @override
	 */
	this.addToGroup = function (groupElement, elements) {
		var i;
		for (i = 0; i < elements.length; i++) {
			groupElement.appendChild(elements[i]);
		}
	};

	/**
	 * 주어진 Shape 이 그룹인 경우 collapse 한다.
	 *
	 * @param {Element} element
	 * @override
	 */
	this.collapse = function (element) {
		var renderer = this, childNodes, i, hideChildEdge;

		hideChildEdge = function (_collapseRootElement, _element) {
			var edgeIdArray, fromEdge, toEdge, _childNodes = _element.childNodes, otherShape, i, j, isNeedToRedraw;
			for (i = _childNodes.length - 1; i >= 0; i--) {
				if ($(_childNodes[i]).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
					hideChildEdge(_collapseRootElement, _childNodes[i]);

					isNeedToRedraw = false;
					edgeIdArray = $(_childNodes[i]).attr("_fromedge");
					if (edgeIdArray) {
						edgeIdArray = edgeIdArray.split(",");
						for (j = 0; j < edgeIdArray.length; j++) {
							fromEdge = renderer.getElementById(edgeIdArray[j]);
							if (fromEdge) {
								otherShape = getShapeFromTerminal($(fromEdge).attr("_from"));

								// otherShape 이 같은 collapse 범위내에 있는지 체크
								if ($(otherShape).parents("#" + _collapseRootElement.id).length !== 0) {
									renderer.hide(fromEdge);
								} else {
									isNeedToRedraw = true;
								}
							}
						}
					}

					edgeIdArray = $(_childNodes[i]).attr("_toedge");
					if (edgeIdArray) {
						edgeIdArray = edgeIdArray.split(",");
						for (j = 0; j < edgeIdArray.length; j++) {
							toEdge = renderer.getElementById(edgeIdArray[j]);
							if (toEdge) {
								otherShape = getShapeFromTerminal($(toEdge).attr("_to"));

								// otherShape 이 같은 collapse 범위내에 있는지 체크
								if ($(otherShape).parents("#" + _collapseRootElement.id).length !== 0) {
									renderer.hide(toEdge);
								} else {
									isNeedToRedraw = true;
								}
							}
						}
					}

					// group 영역 밖의 연결된 otherShape 이 있는 경우 redrawConnectedEdge
					if (isNeedToRedraw === true) {
						renderer.redrawConnectedEdge(_childNodes[i]);
					}
				}
			}
		};

		if (element.shape) {
			childNodes = element.childNodes;
			for (i = childNodes.length - 1; i >= 0; i--) {
				if ($(childNodes[i]).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
					this.hide(childNodes[i]);
				}
			}
			element.shape.isCollapsed = true;
			$(element).attr("_collapsed", true);

			hideChildEdge(element, element);
			this.redrawShape(element);
		}
	};

	/**
	 * 주어진 Shape 이 그룹인 경우 expand 한다.
	 *
	 * @param {Element} element
	 * @override
	 */
	this.expand = function (element) {
		var renderer = this, childNodes, i, showChildEdge;

		showChildEdge = function (_collapseRootElement, _element) {
			var edgeIdArray, fromEdge, toEdge, _childNodes = _element.childNodes, otherShape, i, j, isNeedToRedraw;
			for (i = _childNodes.length - 1; i >= 0; i--) {
				if ($(_childNodes[i]).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
					showChildEdge(_collapseRootElement, _childNodes[i]);

					isNeedToRedraw = false;
					edgeIdArray = $(_childNodes[i]).attr("_fromedge");
					if (edgeIdArray) {
						edgeIdArray = edgeIdArray.split(",");
						for (j = 0; j < edgeIdArray.length; j++) {
							fromEdge = renderer.getElementById(edgeIdArray[j]);
							if (fromEdge) {
								otherShape = getShapeFromTerminal($(fromEdge).attr("_from"));

								// otherShape 이 같은 collapse 범위내에 있는지 체크
								if ($(otherShape).parents("#" + _collapseRootElement.id).length !== 0) {
									renderer.show(fromEdge);
								} else {
									isNeedToRedraw = true;
								}
							}
						}
					}

					edgeIdArray = $(_childNodes[i]).attr("_toedge");
					if (edgeIdArray) {
						edgeIdArray = edgeIdArray.split(",");
						for (j = 0; j < edgeIdArray.length; j++) {
							toEdge = renderer.getElementById(edgeIdArray[j]);
							if (toEdge) {
								otherShape = getShapeFromTerminal($(toEdge).attr("_to"));

								// otherShape 이 같은 collapse 범위내에 있는지 체크
								if ($(otherShape).parents("#" + _collapseRootElement.id).length !== 0) {
									renderer.show(toEdge);
								} else {
									isNeedToRedraw = true;
								}
							}
						}
					}

					// group 영역 밖의 연결된 otherShape 이 있는 경우 redrawConnectedEdge
					if (isNeedToRedraw === true) {
						renderer.redrawConnectedEdge(_childNodes[i]);
					}
				}
			}
		};

		if (element.shape) {
			childNodes = element.childNodes;
			for (i = childNodes.length - 1; i >= 0; i--) {
				if ($(childNodes[i]).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
					this.show(childNodes[i]);
				}
			}
			element.shape.isCollapsed = false;
			$(element).attr("_collapsed", false);

			showChildEdge(element, element);
			this.redrawShape(element);
		}
	};

	/**
	 * 드로잉된 모든 오브젝트를 클리어한다.
	 *
	 * @override
	 */
	this.clear = function () {
		_PAPER.clear();
		_ELE_MAP.clear();
		_ROOT_GROUP = add(_PAPER.group(), null, OG.Constants.NODE_TYPE.ROOT);
	};

	/**
	 * Shape 을 캔버스에서 관련된 모두를 삭제한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.removeShape = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			childNodes = rElement.node.childNodes, i;

		for (i = childNodes.length - 1; i >= 0; i--) {
			if ($(childNodes[i]).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
				this.removeShape(childNodes[i]);
			}
		}

		this.disconnect(rElement.node);
		this.removeTerminal(rElement.node);
		this.removeGuide(rElement.node);
		this.removeCollapseGuide(rElement.node);
		this.remove(rElement.node);
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.remove = function (element) {
		var id = OG.Util.isElement(element) ? element.id : element,
			rElement = getREleById(id);
		remove(rElement);
	};

	/**
	 * 하위 엘리먼트만 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.removeChild = function (element) {
		var id = OG.Util.isElement(element) ? element.id : element,
			rElement = getREleById(id);
		removeChild(rElement);
	};

	/**
	 * 랜더러 캔버스 Root Element 를 반환한다.
	 *
	 * @return {Element} Element
	 * @override
	 */
	this.getRootElement = function () {
		return _PAPER.canvas;
	};

	/**
	 * 랜더러 캔버스 Root Group Element 를 반환한다.
	 *
	 * @return {Element} Element
	 * @override
	 */
	this.getRootGroup = function () {
		return _ROOT_GROUP.node;
	};

	/**
	 * 주어진 지점을 포함하는 Top Element 를 반환한다.
	 *
	 * @param {Number[]} position 위치 좌표
	 * @return {Element} Element
	 * @override
	 */
	this.getElementByPoint = function (position) {
		var element = _PAPER.getElementByPoint(position[0], position[1]);
		return element ? element.node.parentNode : null;
	};

	/**
	 * 주어진 Boundary Box 영역에 포함되는 Shape(GEOM, TEXT, IMAGE) Element 를 반환한다.
	 *
	 * @param envelope
	 * @return {Element[]} Element
	 * @override
	 */
	this.getElementsByBBox = function (envelope) {
		var elements = [];
		$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "]").each(function (index, element) {
			if (element.shape.geom && envelope.isContainsAll(element.shape.geom.getVertices())) {
				elements.push(element);
			}
		});

		return elements;
	};

	/**
	 * 엘리먼트에 속성값을 설정한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Object} attribute 속성값
	 * @override
	 */
	this.setAttr = function (element, attribute) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		if (rElement) {
			rElement.attr(attribute);
		}
	};

	/**
	 * 엘리먼트 속성값을 반환한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {String} attrName 속성이름
	 * @override
	 */
	this.getAttr = function (element, attrName) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		if (rElement) {
			return rElement.attr(attrName);
		}
		return null;
	};

	/**
	 * ID에 해당하는 Element 를 최상단 레이어로 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.toFront = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		if (rElement) {
			rElement.toFront();
		}
	};

	/**
	 * ID에 해당하는 Element 를 최하단 레이어로 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.toBack = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		if (rElement) {
			rElement.toBack();
		}
	};

	/**
	 * 랜더러 캔버스의 사이즈(Width, Height)를 변경한다.
	 *
	 * @param {Number[]} size Canvas Width, Height
	 * @override
	 */
	this.setCanvasSize = function (size) {
		_PAPER.setSize(size[0], size[1]);
	};

	/**
	 * 새로운 View Box 영역을 설정한다. (ZoomIn & ZoomOut 가능)
	 *
	 * @param @param {Number[]} position 위치 좌표(좌상단 기준)
	 * @param {Number[]} size Canvas Width, Height
	 * @param {Boolean} isFit Fit 여부
	 * @override
	 */
	this.setViewBox = function (position, size, isFit) {
		_PAPER.setViewBox(position[0], position[1], size[0], size[1], isFit);
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 show 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.show = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		if (rElement) {
			rElement.show();
		}
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 hide 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @override
	 */
	this.hide = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		if (rElement) {
			rElement.hide();
		}
	};

	/**
	 * Source Element 를 Target Element 아래에 append 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 * @override
	 */
	this.appendChild = function (srcElement, targetElement) {
		var srcRElement = getREleById(OG.Util.isElement(srcElement) ? srcElement.id : srcElement),
			targetRElement = getREleById(OG.Util.isElement(targetElement) ? targetElement.id : targetElement);

		targetRElement.appendChild(srcRElement);

		return srcRElement;
	};

	/**
	 * Source Element 를 Target Element 이후에 insert 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 * @override
	 */
	this.insertAfter = function (srcElement, targetElement) {
		var srcRElement = getREleById(OG.Util.isElement(srcElement) ? srcElement.id : srcElement),
			targetRElement = getREleById(OG.Util.isElement(targetElement) ? targetElement.id : targetElement);

		srcRElement.insertAfter(targetRElement);

		return srcRElement;
	};

	/**
	 * Source Element 를 Target Element 이전에 insert 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 * @override
	 */
	this.insertBefore = function (srcElement, targetElement) {
		var srcRElement = getREleById(OG.Util.isElement(srcElement) ? srcElement.id : srcElement),
			targetRElement = getREleById(OG.Util.isElement(targetElement) ? targetElement.id : targetElement);

		srcRElement.insertBefore(targetRElement);

		return srcRElement;
	};

	/**
	 * 해당 Element 를 가로, 세로 Offset 만큼 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} offset [가로, 세로]
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 * @override
	 */
	this.move = function (element, offset, excludeEdgeId) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			type = rElement ? rElement.node.getAttribute("_shape") : null,
			geometry = rElement ? rElement.node.shape.geom : null,
			renderer = this;

		this.removeCollapseGuide(element);
		if (rElement && type && geometry) {
			$(rElement.node).children("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "]").each(function (idx, item) {
				// recursive
				renderer.move(item, offset, excludeEdgeId);
			});

			geometry.move(offset[0], offset[1]);
			return this.redrawShape(rElement.node, excludeEdgeId);
		} else if (rElement) {
			rElement.transform("...t" + offset[0] + "," + offset[1]);

			return rElement.node;
		}

		return null;
	};

	/**
	 * 주어진 중심좌표로 해당 Element 를 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} position [x, y]
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 * @override
	 */
	this.moveCentroid = function (element, position, excludeEdgeId) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			geometry = rElement ? rElement.node.shape.geom : null,
			bBox, center = {};

		if (rElement && geometry) {
			center = geometry.getCentroid();

			return this.move(element, [position[0] - center.x, position[1] - center.y], excludeEdgeId);
		} else if (rElement) {
			bBox = rElement.getBBox();
			center.x = bBox.x + OG.Util.round(bBox.width / 2);
			center.y = bBox.y + OG.Util.round(bBox.height / 2);

			return this.move(element, [position[0] - center.x, position[1] - center.y]);
		}
		this.removeCollapseGuide(element);

		return null;
	};

	/**
	 * 중심 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number} angle 각도
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 * @override
	 */
	this.rotate = function (element, angle, excludeEdgeId) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			type = rElement ? rElement.node.getAttribute("_shape") : null,
			geometry = rElement ? rElement.node.shape.geom : null;

		if (rElement && type && geometry) {
			geometry.rotate(angle);

			return this.redrawShape(rElement.node, excludeEdgeId);
		} else if (rElement) {
			rElement.rotate(angle);

			return rElement.node;
		}

		return null;
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동한 만큼 리사이즈 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} offset [상, 하, 좌, 우] 각 방향으로 + 값
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 * @override
	 */
	this.resize = function (element, offset, excludeEdgeId) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			type = rElement ? rElement.node.getAttribute("_shape") : null,
			geometry = rElement ? rElement.node.shape.geom : null,
			bBox, offsetX, offsetY, width, height, hRate, vRate;

		this.removeCollapseGuide(element);
		if (rElement && type && geometry) {
			geometry.resize(offset[0], offset[1], offset[2], offset[3]);

			return this.redrawShape(rElement.node, excludeEdgeId);
		} else if (rElement) {
			bBox = rElement.getBBox();

			offsetX = offset[2] + offset[3];
			offsetY = offset[0] + offset[1];
			width = bBox.width + offsetX;
			height = bBox.height + offsetY;
			hRate = bBox.width === 0 ? 1 : width / bBox.width;
			vRate = bBox.height === 0 ? 1 : height / bBox.height;

			rElement.transform("...t" + (-1 * offset[2]) + "," + (-1 * offset[0]));
			rElement.transform("...s" + hRate + "," + vRate + "," + bBox.x + "," + bBox.y);

			return rElement.node;
		}

		return null;
	};

	/**
	 * 중심좌표는 고정한 채 Bounding Box 의 width, height 를 리사이즈 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} size [Width, Height]
	 * @return {Element} Element
	 * @override
	 */
	this.resizeBox = function (element, size) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element),
			geometry = rElement ? rElement.node.shape.geom : null,
			boundary, bBox, offsetWidth, offsetHeight;

		this.removeCollapseGuide(element);
		if (rElement && geometry) {
			boundary = geometry.getBoundary();
			offsetWidth = OG.Util.round((size[0] - boundary.getWidth()) / 2);
			offsetHeight = OG.Util.round((size[1] - boundary.getHeight()) / 2);

			return this.resize(element, [offsetHeight, offsetHeight, offsetWidth, offsetWidth]);
		} else if (rElement) {
			bBox = rElement.getBBox();
			offsetWidth = OG.Util.round((size[0] - bBox.width) / 2);
			offsetHeight = OG.Util.round((size[1] - bBox.height) / 2);

			return this.resize(element, [offsetHeight, offsetHeight, offsetWidth, offsetWidth]);
		}

		return null;
	};

	/**
	 * Edge 유형에 따라 Shape 과의 연결 지점을 찾아 반환한다.
	 *
	 * @param {String} edgeType Edge 유형(straight, plain..)
	 * @param {Element} element 연결할 Shape 엘리먼트
	 * @param {Number[]} from 시작좌표
	 * @param {Number[]} to 끝좌표
	 * @param {Boolean} 시작 연결지점 여부
	 * @return {Object} {position, direction}
	 * @override
	 */
	this.intersectionEdge = function (edgeType, element, from, to, isFrom) {
		var terminal, position, direction, intersectPoints, i, minDistance = Number.MAX_VALUE, distance,
			collapsedParents, collapsedEnvelope, collapsedUpperLeft, collapsedGeom, collapsedPosition;

		// element 가 collapsed 인지 체크
		if (element) {
			collapsedParents = $(element).parents("[_collapsed=true]");
			if (collapsedParents.length !== 0) {
				// collapsed 인 경우
				collapsedEnvelope = collapsedParents[collapsedParents.length - 1].shape.geom.getBoundary();
				collapsedUpperLeft = collapsedEnvelope.getUpperLeft();
				collapsedGeom = new OG.geometry.Rectangle(
					collapsedUpperLeft, OG.Constants.COLLAPSE_SIZE * 3, OG.Constants.COLLAPSE_SIZE * 2);
			}
		}

		switch (edgeType) {
		case OG.Constants.EDGE_TYPE.PLAIN:
			terminal = isFrom ? findFromTerminal(element, from, to) : findToTerminal(element, from, to);
			position = [terminal.terminal.position.x, terminal.terminal.position.y];
			direction = terminal.terminal.direction.toLowerCase();

			if (collapsedGeom) {
				switch (terminal.terminal.direction) {
				case OG.Constants.TERMINAL_TYPE.E:
					collapsedPosition = collapsedGeom.getBoundary().getRightCenter();
					break;
				case OG.Constants.TERMINAL_TYPE.W:
					collapsedPosition = collapsedGeom.getBoundary().getLeftCenter();
					break;
				case OG.Constants.TERMINAL_TYPE.S:
					collapsedPosition = collapsedGeom.getBoundary().getLowerCenter();
					break;
				case OG.Constants.TERMINAL_TYPE.N:
					collapsedPosition = collapsedGeom.getBoundary().getUpperCenter();
					break;
				}
				if (collapsedPosition) {
					position = [collapsedPosition.x, collapsedPosition.y];
				}
			}

			break;
		case OG.Constants.EDGE_TYPE.STRAIGHT:
			if (collapsedGeom) {
				collapsedPosition = collapsedGeom.getBoundary().getCentroid();
				if (isFrom === true) {
					from = [collapsedPosition.x, collapsedPosition.y];
				} else {
					to = [collapsedPosition.x, collapsedPosition.y];
				}
				intersectPoints = collapsedGeom.intersectToLine([from, to]);
			} else {
				intersectPoints = element.shape.geom.intersectToLine([from, to]);
			}
			position = isFrom ? from : to;
			direction = "c";
			for (i = 0; i < intersectPoints.length; i++) {
				distance = intersectPoints[i].distance(isFrom ? to : from);
				if (distance < minDistance) {
					minDistance = distance;
					position = [intersectPoints[i].x, intersectPoints[i].y];
					direction = "c";
				}
			}
			break;
		default:
			break;
		}

		return {
			position : position,
			direction: direction
		};
	};

	/**
	 * 노드 Element 를 복사한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Element} Element
	 * @override
	 */
	this.clone = function (element) {
		// TODO : 오류 - group 인 경우 clone 처리 필요
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element), newElement;
		newElement = rElement.clone();
		add(newElement);

		return newElement.node;
	};

	/**
	 * ID로 Node Element 를 반환한다.
	 *
	 * @param {String} id
	 * @return {Element} Element
	 * @override
	 */
	this.getElementById = function (id) {
		var rElement = getREleById(id);
		return rElement ? rElement.node : null;
	};

	/**
	 * 해당 엘리먼트의 BoundingBox 영역 정보를 반환한다.
	 *
	 * @param {Element,String} element
	 * @return {Object} {width, height, x, y, x2, y2}
	 * @override
	 */
	this.getBBox = function (element) {
		var rElement = getREleById(OG.Util.isElement(element) ? element.id : element);
		return rElement.getBBox();
	};

	/**
	 * 캔버스 루트 엘리먼트의 BoundingBox 영역 정보를 반환한다.
	 *
	 * @return {Object} {width, height, x, y, x2, y2}
	 * @override
	 */
	this.getRootBBox = function () {
		var container = _PAPER.canvas.parentNode,
			width = container.offsetWidth,
			height = container.offsetHeight,
			x = OG.Util.isIE7() ? container.offsetLeft + container.parentNode.offsetLeft : container.offsetLeft,
			y = OG.Util.isIE7() ? container.offsetTop + container.parentNode.offsetTop : container.offsetTop;

		return {
			width : width,
			height: height,
			x     : x,
			y     : y,
			x2    : x + width,
			y2    : y + height
		};
	};

	/**
	 * SVG 인지 여부를 반환한다.
	 *
	 * @return {Boolean} svg 여부
	 * @override
	 */
	this.isSVG = function () {
		return Raphael.svg;
	};

	/**
	 * VML 인지 여부를 반환한다.
	 *
	 * @return {Boolean} vml 여부
	 * @override
	 */
	this.isVML = function () {
		return Raphael.vml;
	};
};
OG.renderer.RaphaelRenderer.prototype = new OG.renderer.IRenderer();
OG.renderer.RaphaelRenderer.prototype.constructor = OG.renderer.RaphaelRenderer;
OG.RaphaelRenderer = OG.renderer.RaphaelRenderer;

/**
 * Event Handler
 *
 * @class
 * @requires OG.renderer.*
 *
 * @param {OG.renderer.IRenderer} renderer 렌더러
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.handler.EventHandler = function (renderer) {
	var _HANDLER = this, _RENDERER = renderer, setMovable, setResizable, isParentSelected, deselectChildren, copyChildren,
		num = function (str) {
			return parseInt(str, 10);
		},
		grid = function (number) {
			return OG.Util.round(number / OG.Constants.MOVE_SNAP_SIZE) * 5;
		},
		getShapeFromTerminal = function (terminal) {
			var terminalId = OG.Util.isElement(terminal) ? terminal.id : terminal;
			return _RENDERER.getElementById(terminalId.substring(0, terminalId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)));
		},
		isContainsConnectedShape = function (edgeEle, bBoxArray) {
			var fromTerminalId, toTerminalId, fromShape, toShape, isContainsFrom = false, isContainsTo = false, i;

			fromTerminalId = $(edgeEle).attr("_from");
			toTerminalId = $(edgeEle).attr("_to");
			if (fromTerminalId) {
				fromShape = getShapeFromTerminal(fromTerminalId);
			}
			if (toTerminalId) {
				toShape = getShapeFromTerminal(toTerminalId);
			}

			for (i = 0; i < bBoxArray.length; i++) {
				if (fromShape && bBoxArray[i].id === fromShape.id) {
					isContainsFrom = true;
				}
				if (toShape && bBoxArray[i].id === toShape.id) {
					isContainsTo = true;
				}
			}

			return {
				none      : !isContainsFrom && !isContainsTo,
				all       : isContainsFrom && isContainsTo,
				any       : isContainsFrom || isContainsTo,
				either    : (isContainsFrom && !isContainsTo) || (!isContainsFrom && isContainsTo),
				attrEither: (fromTerminalId && !toTerminalId) || (!fromTerminalId && toTerminalId)
			};
		};

	/**
	 * Shape 엘리먼트의 이동 가능여부를 설정한다.
	 *
	 * @param {Element} element Shape 엘리먼트
	 * @param {Object} guide JSON 포맷 가이드 정보
	 * @param {Boolean} isMovable 가능여부
	 */
	setMovable = function (element, guide, isMovable) {
		var root = _RENDERER.getRootGroup();

		if (!element || !guide) {
			return;
		}

		if (isMovable) {
			_RENDERER.setAttr(element, {cursor: 'move'});

			// move handle
			$(element).draggable({
				start: function (event) {
					var x = event.pageX, y = event.pageY, bBoxArray = [], box, newBBoxArray = [];
					$(this).data("start", {x: x, y: y});
					$(this).data("offset", {
						x: x - num(_RENDERER.getAttr(guide.bBox, "x")),
						y: y - num(_RENDERER.getAttr(guide.bBox, "y"))
					});

					$("[id$=" + OG.Constants.GUIDE_SUFFIX.BBOX + "]").each(function (index, item) {
						if (item.id) {
							box = _RENDERER.clone(item);
							_RENDERER.setAttr(box, OG.Constants.DEFAULT_STYLE.GUIDE_SHADOW);

							bBoxArray.push({
								id : item.id.replace(OG.Constants.GUIDE_SUFFIX.BBOX, ""),
								box: box
							});
						}
					});

					// Edge 인 경우 먼저 등록, 연결된 Shape 이 있는 경우 목록에서 제거
					$.each(bBoxArray, function (k, item) {
						var ele = _RENDERER.getElementById(item.id), isContainsResult;
						if ($(ele).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
							isContainsResult = isContainsConnectedShape(ele, bBoxArray);
							if (isContainsResult.all || isContainsResult.none || (isContainsResult.either && isContainsResult.attrEither)) {
								newBBoxArray.push(item);
							} else {
								_RENDERER.remove(item.box);
								_RENDERER.removeGuide(ele);
							}
						}
					});

					// Edge 이외 목록에 등록
					$.each(bBoxArray, function (k, item) {
						var ele = _RENDERER.getElementById(item.id);
						if ($(ele).attr("_shape") !== OG.Constants.SHAPE_TYPE.EDGE) {
							newBBoxArray.push(item);
						}
					});

					$(root).data("bBoxArray", newBBoxArray);
					_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					_RENDERER.removeAllTerminal();
				},
				drag : function (event) {
					var x = event.pageX, y = event.pageY,
						start = $(this).data("start"),
						bBoxArray = $(root).data("bBoxArray"),
						dx = grid(x - start.x),
						dy = grid(y - start.y);

					$(this).css({"position": "", "left": "", "top": ""});
					$.each(bBoxArray, function (k, item) {
						_RENDERER.setAttr(item.box, {transform: "t" + dx + "," + dy});
					});
					_RENDERER.removeAllTerminal();
				},
				stop : function (event) {
					var x = event.pageX, y = event.pageY,
						start = $(this).data("start"),
						bBoxArray = $(root).data("bBoxArray"),
						dx = grid(x - start.x),
						dy = grid(y - start.y),
						excludeEdgeId = [],
						groupTarget = $(root).data("groupTarget"),
						eleArray = [],
						guide;

					$(this).css({"position": "", "left": "", "top": ""});
					$.each(bBoxArray, function (k, item) {
						var ele = _RENDERER.getElementById(item.id);
						if ($(ele).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
							excludeEdgeId.push(item.id);
						}
					});

					$.each(bBoxArray, function (k, item) {
						var ele, guideRect;

						_RENDERER.remove(item.box);
						ele = _RENDERER.getElementById(item.id);
						eleArray.push(ele);

						$("[id^='" + item.id + OG.Constants.GUIDE_SUFFIX.GUIDE + "_']").each(function (idx, guideItem) {
							guideRect = _RENDERER.getElementById(guideItem.id);
							// moving
							_RENDERER.setAttr(guideRect, {
								x: num(_RENDERER.getAttr(guideRect, "x")) + dx,
								y: num(_RENDERER.getAttr(guideRect, "y")) + dy
							});
						});

						_RENDERER.move(ele, [dx, dy], excludeEdgeId);

						// Edge 인 경우 disconnect 처리(연결된 Shape 이 없는 경우)
						if ($(ele).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
							if (isContainsConnectedShape(ele, bBoxArray).none) {
								_RENDERER.disconnect(ele);
							}
						}
					});

					// group target 이 있는 경우 grouping 처리
					if (groupTarget && OG.Util.isElement(groupTarget)) {
						// grouping
						_RENDERER.addToGroup(groupTarget, eleArray);

						// guide
						$.each(eleArray, function (k, item) {
							_RENDERER.removeGuide(item);
						});
						guide = _RENDERER.drawGuide(groupTarget);
						setMovable(groupTarget, guide, true);
						setResizable(groupTarget, guide, true);
						_RENDERER.toFront(guide.group);

						_RENDERER.remove(groupTarget.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);
						$(root).removeData("groupTarget");
					} else {
						// ungrouping
						_RENDERER.addToGroup(root, eleArray);

						// guide
						$.each(eleArray, function (k, item) {
							_RENDERER.removeGuide(item);
							guide = _RENDERER.drawGuide(item);
							setResizable(item, guide, true);
							_RENDERER.toFront(guide.group);
						});
					}

					$(root).removeData("bBoxArray");
				}
			});
		} else {
			_RENDERER.setAttr(element, {cursor: OG.Constants.DEFAULT_STYLE.SHAPE.cursor});
		}
	};

	/**
	 * Shape 엘리먼트의 리사이즈 가능여부를 설정한다.
	 *
	 * @param {Element} element Shape 엘리먼트
	 * @param {Object} guide JSON 포맷 가이드 정보
	 * @param {Boolean} isResizable 가능여부
	 */
	setResizable = function (element, guide, isResizable) {
		var root = _RENDERER.getRootGroup(), rootBBox = _RENDERER.getRootBBox();

		if (!element || !guide) {
			return;
		}

		if (isResizable) {
			if ($(element).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
				// resize handle
				$(guide.from).draggable({
					start: function (event) {
						var vertices = element.shape.geom.getVertices(), _style = {},
							toTerminalId = $(element).attr("_to"), toShape,
							toTerminal = [vertices[vertices.length - 1].x, vertices[vertices.length - 1].y],
							edge = _RENDERER.drawEdge(new OG.PolyLine(vertices),
								OG.Util.apply(_style, OG.Constants.DEFAULT_STYLE.EDGE_SHADOW, element.shape.geom.style.map));

						if (toTerminalId) {
							toShape = getShapeFromTerminal(toTerminalId);
							_RENDERER.drawTerminal(toShape);
							toTerminal = _RENDERER.getElementById(toTerminalId);
						}

						$(this).data("to_terminal", toTerminal);
						$(root).data("edge", edge);

						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
						$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (n, selectedItem) {
							if (selectedItem.id && $(selectedItem).attr("_shape") !== OG.Constants.SHAPE_TYPE.EDGE) {
								_RENDERER.removeGuide(selectedItem);
							}
						});
					},
					drag : function (event) {
						var edge = $(root).data("edge"),
							fromTerminal = $(root).data("edge_terminal"),
							toTerminal = $(this).data("to_terminal"),
							fromXY = fromTerminal ? [fromTerminal.terminal.position.x, fromTerminal.terminal.position.y] : [event.pageX - rootBBox.x, event.pageY - rootBBox.y],
							toXY = OG.Util.isElement(toTerminal) ? [toTerminal.terminal.position.x, toTerminal.terminal.position.y] : toTerminal,
							fromDrct = fromTerminal ? fromTerminal.terminal.direction.toLowerCase() : "c",
							toDrct = OG.Util.isElement(toTerminal) ? toTerminal.terminal.direction.toLowerCase() : "c",
							fromShape = fromTerminal ? getShapeFromTerminal(fromTerminal) : null,
							toShape = OG.Util.isElement(toTerminal) ? getShapeFromTerminal(toTerminal) : null,
							orgFromXY, orgToXY, intersectionInfo, isSelf;

						$(this).css({"position": "", "left": "", "top": ""});

						// backup edge-direction
						orgFromXY = fromXY;
						orgToXY = toXY;

						// direction 이 c 인 경우에 대한 처리(센터 연결)
						if (fromShape && fromDrct === "c") {
							intersectionInfo = _RENDERER.intersectionEdge(
								edge.geom.style.get("edge-type"), fromShape, [orgFromXY[0], orgFromXY[1]], [orgToXY[0], orgToXY[1]], true
							);
							fromXY = intersectionInfo.position;
							fromDrct = intersectionInfo.direction;
						}
						if (toShape && toDrct === "c") {
							intersectionInfo = _RENDERER.intersectionEdge(
								edge.geom.style.get("edge-type"), toShape, [orgFromXY[0], orgFromXY[1]], [orgToXY[0], orgToXY[1]], false
							);
							toXY = intersectionInfo.position;
							toDrct = intersectionInfo.direction;
						}

						isSelf = fromShape && toShape && fromShape.id === toShape.id;
						if (isSelf) {
							fromXY = toXY = fromShape.shape.geom.getBoundary().getRightCenter();
						}

						_RENDERER.drawEdge(new OG.Line(fromXY, toXY),
							OG.Util.apply(edge.geom.style.map, {"edge-direction": fromDrct + " " + toDrct}), edge.id, isSelf);
					},
					stop : function (event) {
						var fromTerminal = $(root).data("edge_terminal") || [event.pageX - rootBBox.x, event.pageY - rootBBox.y],
							toTerminal = $(this).data("to_terminal"),
							fromShape = OG.Util.isElement(fromTerminal) ? getShapeFromTerminal(fromTerminal) : null,
							edge = $(root).data("edge");

						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});

						// clear
						$(this).removeData("to_terminal");
						$(root).removeData("edge");
						$(root).removeData("edge_terminal");
						_RENDERER.remove(edge);
						_RENDERER.removeGuide(element);
						if (fromShape) {
							_RENDERER.remove(fromShape.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);
						}

						// draw
						element = _RENDERER.connect(fromTerminal, toTerminal, element, element.shape.geom.style);
						guide = _RENDERER.drawGuide(element);
						setResizable(element, guide, true);
						_RENDERER.toFront(guide.group);
					}
				});

				$(guide.to).draggable({
					start: function (event) {
						var vertices = element.shape.geom.getVertices(), _style = {},
							fromTerminalId = $(element).attr("_from"), fromShape,
							fromTerminal = [vertices[0].x, vertices[0].y],
							edge = _RENDERER.drawEdge(new OG.PolyLine(vertices),
								OG.Util.apply(_style, OG.Constants.DEFAULT_STYLE.EDGE_SHADOW, element.shape.geom.style.map));

						if (fromTerminalId) {
							fromShape = getShapeFromTerminal(fromTerminalId);
							_RENDERER.drawTerminal(fromShape);
							fromTerminal = _RENDERER.getElementById(fromTerminalId);
						}

						$(this).data("from_terminal", fromTerminal);
						$(root).data("edge", edge);

						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
						$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (n, selectedItem) {
							if (selectedItem.id && $(selectedItem).attr("_shape") !== OG.Constants.SHAPE_TYPE.EDGE) {
								_RENDERER.removeGuide(selectedItem);
							}
						});
					},
					drag : function (event) {
						var edge = $(root).data("edge"),
							fromTerminal = $(this).data("from_terminal"),
							toTerminal = $(root).data("edge_terminal"),
							fromXY = OG.Util.isElement(fromTerminal) ? [fromTerminal.terminal.position.x, fromTerminal.terminal.position.y] : fromTerminal,
							toXY = toTerminal ? [toTerminal.terminal.position.x, toTerminal.terminal.position.y] : [event.pageX - rootBBox.x, event.pageY - rootBBox.y],
							fromDrct = OG.Util.isElement(fromTerminal) ? fromTerminal.terminal.direction.toLowerCase() : "c",
							toDrct = toTerminal ? toTerminal.terminal.direction.toLowerCase() : "c",
							fromShape = OG.Util.isElement(fromTerminal) ? getShapeFromTerminal(fromTerminal) : null,
							toShape = toTerminal ? getShapeFromTerminal(toTerminal) : null,
							orgFromXY, orgToXY, intersectionInfo, isSelf;

						$(this).css({"position": "", "left": "", "top": ""});

						// backup edge-direction
						orgFromXY = fromXY;
						orgToXY = toXY;

						// direction 이 c 인 경우에 대한 처리(센터 연결)
						if (fromShape && fromDrct === "c") {
							intersectionInfo = _RENDERER.intersectionEdge(
								edge.geom.style.get("edge-type"), fromShape, [orgFromXY[0], orgFromXY[1]], [orgToXY[0], orgToXY[1]], true
							);
							fromXY = intersectionInfo.position;
							fromDrct = intersectionInfo.direction;
						}
						if (toShape && toDrct === "c") {
							intersectionInfo = _RENDERER.intersectionEdge(
								edge.geom.style.get("edge-type"), toShape, [orgFromXY[0], orgFromXY[1]], [orgToXY[0], orgToXY[1]], false
							);
							toXY = intersectionInfo.position;
							toDrct = intersectionInfo.direction;
						}

						isSelf = (fromShape !== null) && (toShape !== null) && fromShape.id === toShape.id;
						if (isSelf) {
							fromXY = toXY = toShape.shape.geom.getBoundary().getRightCenter();
						}

						_RENDERER.drawEdge(new OG.Line(fromXY, toXY),
							OG.Util.apply(edge.geom.style.map, {"edge-direction": fromDrct + " " + toDrct}), edge.id, isSelf);
					},
					stop : function (event) {
						var fromTerminal = $(this).data("from_terminal"),
							toTerminal = $(root).data("edge_terminal") || [event.pageX - rootBBox.x, event.pageY - rootBBox.y],
							toShape = OG.Util.isElement(toTerminal) ? getShapeFromTerminal(toTerminal) : null,
							edge = $(root).data("edge");

						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});

						// clear
						$(this).removeData("from_terminal");
						$(root).removeData("edge");
						$(root).removeData("edge_terminal");
						_RENDERER.remove(edge);
						_RENDERER.removeGuide(element);
						if (toShape) {
							_RENDERER.remove(toShape.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);
						}

						// draw
						element = _RENDERER.connect(fromTerminal, toTerminal, element, element.shape.geom.style);
						guide = _RENDERER.drawGuide(element);
						setResizable(element, guide, true);
						_RENDERER.toFront(guide.group);
					}
				});

				$.each(guide.controls, function (idx, item) {
					$(item).draggable({
						start: function (event) {
							var x = event.pageX, y = event.pageY;
							$(this).data("start", {x: x, y: y});
							$(this).data("offset", {
								x: x - num(_RENDERER.getAttr(item, "x")),
								y: y - num(_RENDERER.getAttr(item, "y"))
							});
							_RENDERER.remove(guide.bBox);
							_RENDERER.removeRubberBand(_RENDERER.getRootElement());
						},
						drag : function (event) {
							var x = event.pageX, y = event.pageY,
								start = $(this).data("start"),
								offset = $(this).data("offset"),
								newX = x - offset.x,
								newY = y - offset.y,
								vertices = element.shape.geom.getVertices(),
								isHorizontal = item.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H) >= 0,
								num = isHorizontal ?
									parseInt(item.id.replace(element.id + OG.Constants.GUIDE_SUFFIX.CTL_H, ""), 10) :
									parseInt(item.id.replace(element.id + OG.Constants.GUIDE_SUFFIX.CTL_V, ""), 10);

							$(this).css({"position": "", "left": "", "top": ""});

							if (isHorizontal) {
								vertices[num].x = newX;
								vertices[num + 1].x = newX;
							} else {
								vertices[num].y = newY;
								vertices[num + 1].y = newY;
							}

							element = _RENDERER.drawEdge(new OG.PolyLine(vertices), element.shape.geom.style, element.id);
							_RENDERER.drawGuide(element);

							_RENDERER.removeAllTerminal();

							// Draw Label
							_RENDERER.drawLabel(element);
						},
						stop : function (event) {
							var x = event.pageX, y = event.pageY,
								start = $(this).data("start"),
								offset = $(this).data("offset"),
								newX = x - offset.x,
								newY = y - offset.y,
								vertices = element.shape.geom.getVertices(),
								isHorizontal = item.id.indexOf(OG.Constants.GUIDE_SUFFIX.CTL_H) >= 0,
								num = isHorizontal ?
									parseInt(item.id.replace(element.id + OG.Constants.GUIDE_SUFFIX.CTL_H, ""), 10) :
									parseInt(item.id.replace(element.id + OG.Constants.GUIDE_SUFFIX.CTL_V, ""), 10);

							$(this).css({"position": "absolute", "left": "0px", "top": "0px"});

							if (isHorizontal) {
								vertices[num].x = newX;
								vertices[num + 1].x = newX;
							} else {
								vertices[num].y = newY;
								vertices[num + 1].y = newY;
							}

							element = _RENDERER.drawEdge(new OG.PolyLine(vertices), element.shape.geom.style, element.id);
							_RENDERER.drawGuide(element);

							// Draw Label
							_RENDERER.drawLabel(element);
						}
					});
				});
			} else {
				// resize handle
				$(guide.rc).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.rc, "x")),
							y: y - num(_RENDERER.getAttr(guide.rc, "y"))
						});
						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							dx = x - start.x,
							newX = x - offset.x,
							newWidth = newX - num(_RENDERER.getAttr(guide.lc, "x"));
						$(this).css({"position": "", "left": "", "top": ""});
						if (newWidth >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.rc, {x: newX});
							_RENDERER.setAttr(guide.ur, {x: newX});
							_RENDERER.setAttr(guide.lr, {x: newX});
							_RENDERER.setAttr(guide.uc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.lc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.lwc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.lc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.bBox, {width: newWidth});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dx = x - start.x;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getWidth() + dx < OG.Constants.GUIDE_MIN_SIZE) {
								dx = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getWidth();
							}
							_RENDERER.resize(element, [0, 0, 0, dx]);
							_RENDERER.drawGuide(element);
						}
					}
				});

				$(guide.lwc).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.lwc, "x")),
							y: y - num(_RENDERER.getAttr(guide.lwc, "y"))
						});
						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newY = y - offset.y,
							newHeight = newY - num(_RENDERER.getAttr(guide.uc, "y"));
						$(this).css({"position": "", "left": "", "top": ""});
						if (newHeight >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.lwc, {y: newY});
							_RENDERER.setAttr(guide.ll, {y: newY});
							_RENDERER.setAttr(guide.lr, {y: newY});
							_RENDERER.setAttr(guide.lc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.uc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.rc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.uc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.bBox, {height: newHeight});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dy = y - start.y;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getHeight() + dy < OG.Constants.GUIDE_MIN_SIZE) {
								dy = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getHeight();
							}
							_RENDERER.resize(element, [0, dy, 0, 0]);
							_RENDERER.drawGuide(element);
						}
					}
				});

				$(guide.lr).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.lr, "x")),
							y: y - num(_RENDERER.getAttr(guide.lr, "y"))
						});
						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newX = x - offset.x,
							newWidth = newX - num(_RENDERER.getAttr(guide.lc, "x")),
							newY = y - offset.y,
							newHeight = newY - num(_RENDERER.getAttr(guide.uc, "y"));
						$(this).css({"position": "", "left": "", "top": ""});
						if (newWidth >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.rc, {x: newX});
							_RENDERER.setAttr(guide.ur, {x: newX});
							_RENDERER.setAttr(guide.lr, {x: newX});
							_RENDERER.setAttr(guide.uc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.lc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.lwc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.lc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.bBox, {width: newWidth});
						}
						if (newHeight >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.lwc, {y: newY});
							_RENDERER.setAttr(guide.ll, {y: newY});
							_RENDERER.setAttr(guide.lr, {y: newY});
							_RENDERER.setAttr(guide.lc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.uc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.rc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.uc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.bBox, {height: newHeight});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dx = x - start.x,
							dy = y - start.y;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getWidth() + dx < OG.Constants.GUIDE_MIN_SIZE) {
								dx = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getWidth();
							}
							if (element.shape.geom.getBoundary().getHeight() + dy < OG.Constants.GUIDE_MIN_SIZE) {
								dy = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getHeight();
							}
							_RENDERER.resize(element, [0, dy, 0, dx]);
							_RENDERER.drawGuide(element);
						}
						_RENDERER.removeAllTerminal();
					}
				});

				$(guide.lc).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.lc, "x")),
							y: y - num(_RENDERER.getAttr(guide.lc, "y"))
						});
						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newX = x - offset.x,
							newWidth = num(_RENDERER.getAttr(guide.rc, "x")) - newX;
						$(this).css({"position": "", "left": "", "top": ""});
						if (newWidth >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.lc, {x: newX});
							_RENDERER.setAttr(guide.ul, {x: newX});
							_RENDERER.setAttr(guide.ll, {x: newX});
							_RENDERER.setAttr(guide.uc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.rc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.lwc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.rc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.bBox, {x: OG.Util.round(newX + num(_RENDERER.getAttr(guide.lc, "width")) / 2), width: newWidth});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dx = start.x - x;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getWidth() + dx < OG.Constants.GUIDE_MIN_SIZE) {
								dx = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getWidth();
							}
							_RENDERER.resize(element, [0, 0, dx, 0]);
							_RENDERER.drawGuide(element);
						}
					}
				});

				$(guide.ll).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.ll, "x")),
							y: y - num(_RENDERER.getAttr(guide.ll, "y"))
						});

						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newX = x - offset.x,
							newY = y - offset.y,
							newWidth = num(_RENDERER.getAttr(guide.rc, "x")) - newX,
							newHeight = newY - num(_RENDERER.getAttr(guide.uc, "y"));
						$(this).css({"position": "", "left": "", "top": ""});
						if (newWidth >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.lc, {x: newX});
							_RENDERER.setAttr(guide.ul, {x: newX});
							_RENDERER.setAttr(guide.ll, {x: newX});
							_RENDERER.setAttr(guide.uc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.rc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.lwc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.rc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.bBox, {x: OG.Util.round(newX + num(_RENDERER.getAttr(guide.lc, "width")) / 2), width: newWidth});
						}
						if (newHeight >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.lwc, {y: newY});
							_RENDERER.setAttr(guide.ll, {y: newY});
							_RENDERER.setAttr(guide.lr, {y: newY});
							_RENDERER.setAttr(guide.lc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.uc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.rc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.uc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.bBox, {height: newHeight});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dx = start.x - x,
							dy = y - start.y;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getWidth() + dx < OG.Constants.GUIDE_MIN_SIZE) {
								dx = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getWidth();
							}
							if (element.shape.geom.getBoundary().getHeight() + dy < OG.Constants.GUIDE_MIN_SIZE) {
								dy = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getHeight();
							}
							_RENDERER.resize(element, [0, dy, dx, 0]);
							_RENDERER.drawGuide(element);
						}
					}
				});

				$(guide.uc).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.uc, "x")),
							y: y - num(_RENDERER.getAttr(guide.uc, "y"))
						});

						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newY = y - offset.y,
							newHeight = num(_RENDERER.getAttr(guide.lwc, "y")) - newY;
						$(this).css({"position": "", "left": "", "top": ""});
						if (newHeight >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.uc, {y: newY});
							_RENDERER.setAttr(guide.ul, {y: newY});
							_RENDERER.setAttr(guide.ur, {y: newY});
							_RENDERER.setAttr(guide.lc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.lwc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.rc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.lwc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.bBox, {y: OG.Util.round(newY + num(_RENDERER.getAttr(guide.uc, "width")) / 2), height: newHeight});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dy = start.y - y;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getHeight() + dy < OG.Constants.GUIDE_MIN_SIZE) {
								dy = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getHeight();
							}
							_RENDERER.resize(element, [dy, 0, 0, 0]);
							_RENDERER.drawGuide(element);
						}
					}
				});

				$(guide.ul).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.ul, "x")),
							y: y - num(_RENDERER.getAttr(guide.ul, "y"))
						});

						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newX = x - offset.x,
							newY = y - offset.y,
							newWidth = num(_RENDERER.getAttr(guide.rc, "x")) - newX,
							newHeight = num(_RENDERER.getAttr(guide.lwc, "y")) - newY;
						$(this).css({"position": "", "left": "", "top": ""});
						if (newWidth >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.lc, {x: newX});
							_RENDERER.setAttr(guide.ul, {x: newX});
							_RENDERER.setAttr(guide.ll, {x: newX});
							_RENDERER.setAttr(guide.uc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.rc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.lwc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.rc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.bBox, {x: OG.Util.round(newX + num(_RENDERER.getAttr(guide.lc, "width")) / 2), width: newWidth});
						}
						if (newHeight >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.uc, {y: newY});
							_RENDERER.setAttr(guide.ul, {y: newY});
							_RENDERER.setAttr(guide.ur, {y: newY});
							_RENDERER.setAttr(guide.lc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.lwc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.rc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.lwc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.bBox, {y: OG.Util.round(newY + num(_RENDERER.getAttr(guide.uc, "height")) / 2), height: newHeight});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dx = start.x - x,
							dy = start.y - y;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getWidth() + dx < OG.Constants.GUIDE_MIN_SIZE) {
								dx = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getWidth();
							}
							if (element.shape.geom.getBoundary().getHeight() + dy < OG.Constants.GUIDE_MIN_SIZE) {
								dy = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getHeight();
							}
							_RENDERER.resize(element, [dy, 0, dx, 0]);
							_RENDERER.drawGuide(element);
						}
					}
				});

				$(guide.ur).draggable({
					start: function (event) {
						var x = event.pageX, y = event.pageY;
						$(this).data("start", {x: x, y: y});
						$(this).data("offset", {
							x: x - num(_RENDERER.getAttr(guide.ur, "x")),
							y: y - num(_RENDERER.getAttr(guide.ur, "y"))
						});

						_RENDERER.removeRubberBand(_RENDERER.getRootElement());
					},
					drag : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							offset = $(this).data("offset"),
							newX = x - offset.x,
							newY = y - offset.y,
							newWidth = newX - num(_RENDERER.getAttr(guide.lc, "x")),
							newHeight = num(_RENDERER.getAttr(guide.lwc, "y")) - newY;
						$(this).css({"position": "", "left": "", "top": ""});
						if (newWidth >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.rc, {x: newX});
							_RENDERER.setAttr(guide.ur, {x: newX});
							_RENDERER.setAttr(guide.lr, {x: newX});
							_RENDERER.setAttr(guide.uc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.lc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.lwc, {x: OG.Util.round((num(_RENDERER.getAttr(guide.lc, "x")) + newX) / 2)});
							_RENDERER.setAttr(guide.bBox, {width: newWidth});
						}
						if (newHeight >= OG.Constants.GUIDE_MIN_SIZE) {
							_RENDERER.setAttr(guide.uc, {y: newY});
							_RENDERER.setAttr(guide.ul, {y: newY});
							_RENDERER.setAttr(guide.ur, {y: newY});
							_RENDERER.setAttr(guide.lc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.lwc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.rc, {y: OG.Util.round((num(_RENDERER.getAttr(guide.lwc, "y")) + newY) / 2)});
							_RENDERER.setAttr(guide.bBox, {y: OG.Util.round(newY + num(_RENDERER.getAttr(guide.uc, "width")) / 2), height: newHeight});
						}
						_RENDERER.removeAllTerminal();
					},
					stop : function (event) {
						var x = event.pageX, y = event.pageY,
							start = $(this).data("start"),
							dx = x - start.x,
							dy = start.y - y;
						$(this).css({"position": "absolute", "left": "0px", "top": "0px"});
						if (element && element.shape.geom) {
							// resizing
							if (element.shape.geom.getBoundary().getWidth() + dx < OG.Constants.GUIDE_MIN_SIZE) {
								dx = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getWidth();
							}
							if (element.shape.geom.getBoundary().getHeight() + dy < OG.Constants.GUIDE_MIN_SIZE) {
								dy = OG.Constants.GUIDE_MIN_SIZE - element.shape.geom.getBoundary().getHeight();
							}
							_RENDERER.resize(element, [dy, 0, 0, dx]);
							_RENDERER.drawGuide(element);
						}
					}
				});
			}
		} else {
			if ($(element).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
				_RENDERER.setAttr(guide.from, {cursor: 'default'});
				_RENDERER.setAttr(guide.to, {cursor: 'default'});
				$.each(guide.controls, function (idx, item) {
					_RENDERER.setAttr(item, {cursor: 'default'});
				});
			} else {
				_RENDERER.setAttr(guide.ul, {cursor: 'default'});
				_RENDERER.setAttr(guide.ur, {cursor: 'default'});
				_RENDERER.setAttr(guide.ll, {cursor: 'default'});
				_RENDERER.setAttr(guide.lr, {cursor: 'default'});
				_RENDERER.setAttr(guide.lc, {cursor: 'default'});
				_RENDERER.setAttr(guide.uc, {cursor: 'default'});
				_RENDERER.setAttr(guide.rc, {cursor: 'default'});
				_RENDERER.setAttr(guide.lwc, {cursor: 'default'});
			}
		}
	};

	/**
	 * 선택되어진 Shape 부모노드가 하나라도 있다면 true 를 반환한다.
	 *
	 * @param {Element} element
	 * @return {Boolean}
	 */
	isParentSelected = function (element) {
		var parentNode = element.parentNode;
		if (parentNode) {
			if (isParentSelected(parentNode)) {
				return true;
			}

			if ($(parentNode).attr("_type") === OG.Constants.NODE_TYPE.SHAPE &&
				$(parentNode).attr("_selected") === "true") {
				return true;
			}
		}

		return false;
	};

	/**
	 * 하위 Shape 자식노드를 모두 deselect 처리한다.
	 *
	 * @param {Element} element
	 */
	deselectChildren = function (element) {
		var children = element.childNodes;
		$.each(children, function (idx, item) {
			if ($(item).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
				if (item.childNodes.length > 0) {
					deselectChildren(item);
				}

				if ($(item).attr("_selected") === "true") {
					_RENDERER.removeGuide(item);
					$(item).draggable("destroy");
				}
			}
		});
	};

	/**
	 * 그룹 Shape 인 경우 포함된 하위 Shape 들을 복사한다.
	 *
	 * @param {Element} element 원본 부모 Shape 엘리먼트
	 * @param {Element} newCopiedElement 복사된 부모 Shape 엘리먼트
	 */
	copyChildren = function (element, newCopiedElement) {
		var children = element.childNodes;
		$.each(children, function (idx, item) {
			if ($(item).attr("_type") === OG.Constants.NODE_TYPE.SHAPE) {
				// copy
				var boundary = item.shape.geom.getBoundary(), newShape, newElement, newGuide;
				newShape = item.shape.clone();

				if ($(item).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
					newShape.geom = new OG.PolyLine(item.shape.geom.getVertices());
					newShape.geom.move(OG.Constants.COPY_PASTE_PADDING, OG.Constants.COPY_PASTE_PADDING);
					newElement = _RENDERER.drawShape(
						null, newShape,
						null, item.shape.geom.style
					);

				} else {
					newElement = _RENDERER.drawShape(
						[ boundary.getCentroid().x + OG.Constants.COPY_PASTE_PADDING, boundary.getCentroid().y + OG.Constants.COPY_PASTE_PADDING ],
						newShape, [boundary.getWidth(), boundary.getHeight()], item.shape.geom.style
					);
				}

				// append child
				newCopiedElement.appendChild(newElement);

				// enable event
				_HANDLER.enableClickSelect(newElement, true, true);
				if ($(newElement).attr("_shape") !== OG.Constants.SHAPE_TYPE.GROUP) {
					_HANDLER.enableEditLabel(newElement);
					_HANDLER.enableConnect(newElement);
				}

				// recursive call
				if (item.childNodes.length > 0) {
					copyChildren(item, newElement);
				}
			}
		});
	};

	/**
	 * 주어진 Shape Element 의 라벨을 수정 가능하도록 한다.
	 * TODO : jquery layout 을 사용한 경우 offset 위치 조정 필요
	 *
	 * @param {Element} element Shape Element
	 */
	this.enableEditLabel = function (element) {
		var rootEle = _RENDERER.getRootElement();
		$(element).bind({
			dblclick: function () {
				var envelope = element.shape.geom.getBoundary(),
					upperLeft = envelope.getUpperLeft(),
					rootBBox = _RENDERER.getRootBBox(),
					bBox,
//					left = upperLeft.x + rootBBox.x,
					left = upperLeft.x,
//					top = upperLeft.y + rootBBox.y,
					top = upperLeft.y,
					width = envelope.getWidth(),
					height = envelope.getHeight(),
					editorId = element.id + OG.Constants.LABEL_EDITOR_SUFFIX,
					labelEditor,
					textAlign = "center",
					/**
					 * 라인(꺽은선)의 중심위치를 반환한다.
					 *
					 * @param {Element} element Edge 엘리먼트
					 * @return {OG.Coordinate}
					 */
						getCenterOfEdge = function (element) {
						var vertices, lineLength, distance = 0, i, intersectArray;

						// Edge Shape 인 경우 라인의 중간 지점 찾기
						vertices = element.shape.geom.getVertices();
						lineLength = element.shape.geom.getLength();

						for (i = 0; i < vertices.length - 1; i++) {
							distance += vertices[i].distance(vertices[i + 1]);
							if (distance > lineLength / 2) {
								intersectArray = element.shape.geom.intersectCircleToLine(
									vertices[i + 1], distance - lineLength / 2, vertices[i + 1], vertices[i]
								);

								break;
							}
						}

						return intersectArray[0];
					},
					centerOfEdge;

				// textarea
				$(rootEle.parentNode).append("<textarea id='" + element.id + OG.Constants.LABEL_EDITOR_SUFFIX + "'></textarea>");
				labelEditor = $("#" + editorId);

				// text-align 스타일 적용
				switch (element.shape.geom.style.get("text-anchor")) {
				case "start":
					textAlign = "left";
					break;
				case "middle":
					textAlign = "center";
					break;
				case "end":
					textAlign = "right";
					break;
				default:
					textAlign = "center";
					break;
				}

				if ($(element).attr("_shape") === OG.Constants.SHAPE_TYPE.TEXT) {
					// Text Shape
					$(labelEditor).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR, {
						left: left, top: top, width: width, height: height, "text-align": textAlign, overflow: "hidden", resize: "none"
					}));
					$(labelEditor).focus();
					$(labelEditor).val(element.shape.text);

					$(labelEditor).bind({
						focusout: function () {
							element.shape.text = this.value;
							if (element.shape.text) {
								_RENDERER.redrawShape(element);
								this.parentNode.removeChild(this);
							} else {
								_RENDERER.removeShape(element);
								this.parentNode.removeChild(this);
							}
						}
					});
				} else if ($(element).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
					// Edge Shape
					if (element.shape.label && _RENDERER.isSVG()) {
						$(element).find("text").each(function (idx, item) {
							bBox = _RENDERER.getBBox(item);
//							left = bBox.x + rootBBox.x - 10;
							left = bBox.x - 10;
//							top = bBox.y + rootBBox.y;
							top = bBox.y;
							width = bBox.width + 20;
							height = bBox.height;
						});
					} else {
						centerOfEdge = getCenterOfEdge(element);
//						left = centerOfEdge.x - OG.Constants.LABEL_EDITOR_WIDTH / 2 + rootBBox.x;
						left = centerOfEdge.x - OG.Constants.LABEL_EDITOR_WIDTH / 2;
//						top = centerOfEdge.y - OG.Constants.LABEL_EDITOR_HEIGHT / 2 + rootBBox.y;
						top = centerOfEdge.y - OG.Constants.LABEL_EDITOR_HEIGHT / 2;
						width = OG.Constants.LABEL_EDITOR_WIDTH;
						height = OG.Constants.LABEL_EDITOR_HEIGHT;
					}

					$(labelEditor).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR, {
						left: left, top: top, width: width, height: height, overflow: "hidden", resize: "none"
					}));
					$(labelEditor).focus();
					$(labelEditor).val(element.shape.label);

					$(labelEditor).bind({
						focusout: function () {
							_RENDERER.drawLabel(element, this.value);
							this.parentNode.removeChild(this);
						}
					});
				} else {
					$(labelEditor).css(OG.Util.apply(OG.Constants.DEFAULT_STYLE.LABEL_EDITOR, {
						left: left, top: top, width: width, height: height, "text-align": textAlign, overflow: "hidden", resize: "none"
					}));
					$(labelEditor).focus();
					$(labelEditor).val(element.shape.label);

					$(labelEditor).bind({
						focusout: function () {
							_RENDERER.drawLabel(element, this.value);
							this.parentNode.removeChild(this);
						}
					});
				}
			}
		});
	};

	/**
	 * 주어진 Shape Element 를 연결가능하도록 한다.
	 *
	 * @param {Element} element Shape Element
	 */
	this.enableConnect = function (element) {
		var terminalGroup, root = _RENDERER.getRootGroup(), rootBBox = _RENDERER.getRootBBox();
		if (element && $(element).attr("_shape") !== OG.Constants.SHAPE_TYPE.GROUP) {
			$(element).bind({
				mouseover: function () {
					terminalGroup = _RENDERER.drawTerminal(element);
					if (terminalGroup) {
						// 센터 연결 터미널 찾기
						if ($(root).data("edge")) {
							$.each(terminalGroup.terminal.childNodes, function (idx, item) {
								if (item.terminal && item.terminal.direction.toLowerCase() === "c") {
									_RENDERER.drawDropOverGuide(element);
									$(root).data("edge_terminal", item);
									return false;
								}
							});
						}

						$(terminalGroup.bBox).bind({
							mouseout: function () {
								if (!$(root).data("edge")) {
									_RENDERER.removeTerminal(element);
								}
							}
						});

						$.each(terminalGroup.terminal.childNodes, function (idx, item) {
							if (item.terminal) {
								$(item).bind({
									mouseover: function (event) {
										_RENDERER.setAttr(item, OG.Constants.DEFAULT_STYLE.TERMINAL_OVER);
										$(root).data("edge_terminal", item);
									},
									mouseout : function () {
										_RENDERER.setAttr(item, OG.Constants.DEFAULT_STYLE.TERMINAL);
										$(root).removeData("edge_terminal");
									}
								});

								$(item).draggable({
									start: function (event) {
										var x = item.terminal.position.x, y = item.terminal.position.y,
											edge = _RENDERER.drawShape(null, new OG.EdgeShape([x, y], [x, y]), null,
												OG.Constants.DEFAULT_STYLE.EDGE_SHADOW);

										$(root).data("edge", edge);
										$(root).data("from_terminal", item);

										_RENDERER.removeRubberBand(_RENDERER.getRootElement());
										$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (n, selectedItem) {
											if (selectedItem.id) {
												_RENDERER.removeGuide(selectedItem);
											}
										});
									},
									drag : function (event) {
										var edge = $(root).data("edge"),
											fromTerminal = $(root).data("from_terminal"),
											toTerminal = $(root).data("edge_terminal"),
											fromXY = [fromTerminal.terminal.position.x, fromTerminal.terminal.position.y],
											toXY = toTerminal ? [toTerminal.terminal.position.x, toTerminal.terminal.position.y] : [event.pageX - rootBBox.x, event.pageY - rootBBox.y],
											fromDrct = fromTerminal.terminal.direction.toLowerCase(),
											toDrct = toTerminal ? toTerminal.terminal.direction.toLowerCase() : "c",
											toShape = toTerminal ? getShapeFromTerminal(toTerminal) : null,
											orgFromXY, orgToXY, intersectionInfo, isSelf;

										$(this).css({"position": "", "left": "", "top": ""});

										// backup edge-direction
										orgFromXY = fromXY;
										orgToXY = toXY;

										// direction 이 c 인 경우에 대한 처리(센터 연결)
										if (!element.shape.geom.getBoundary().isContains(toXY) && fromDrct === "c") {
											intersectionInfo = _RENDERER.intersectionEdge(
												edge.shape.geom.style.get("edge-type"), element, [orgFromXY[0], orgFromXY[1]], [orgToXY[0], orgToXY[1]], true
											);
											fromXY = intersectionInfo.position;
											fromDrct = intersectionInfo.direction;
										}
										if (toShape && toDrct === "c") {
											intersectionInfo = _RENDERER.intersectionEdge(
												edge.shape.geom.style.get("edge-type"), toShape, [orgFromXY[0], orgFromXY[1]], [orgToXY[0], orgToXY[1]], false
											);
											toXY = intersectionInfo.position;
											toDrct = intersectionInfo.direction;
										}

										isSelf = element && toShape && element.id === toShape.id;
										if (isSelf) {
											fromXY = toXY = element.shape.geom.getBoundary().getRightCenter();
										}

										_RENDERER.drawEdge(new OG.Line(fromXY, toXY),
											OG.Util.apply(edge.shape.geom.style.map, {"edge-direction": fromDrct + " " + toDrct}), edge.id, isSelf);
									},
									stop : function (event) {
										var to = {x: event.pageX - rootBBox.x, y: event.pageY - rootBBox.y},
											edge = $(root).data("edge"),
											fromTerminal = $(root).data("from_terminal"),
											toTerminal = $(root).data("edge_terminal") || [to.x, to.y],
											toShape = OG.Util.isElement(toTerminal) ? getShapeFromTerminal(toTerminal) : null,
											boundary, clonedElement, terminalGroup, childTerminals, guide, i;

										$(this).css({"position": "absolute", "left": "0px", "top": "0px"});

										// 연결대상이 없으면 복사후 연결
										if (!$(root).data("edge_terminal")) {
											boundary = element.shape.geom.getBoundary();
											clonedElement = _RENDERER.drawShape([to.x, to.y], element.shape.clone(),
												[boundary.getWidth(), boundary.getHeight()], element.shape.geom.style);

											_HANDLER.enableClickSelect(clonedElement, true, true);
											_HANDLER.enableConnect(clonedElement);
											_HANDLER.enableEditLabel(clonedElement);

											// 센터 연결 터미널 찾기
											terminalGroup = _RENDERER.drawTerminal(clonedElement);
											childTerminals = terminalGroup.terminal.childNodes;
											toTerminal = childTerminals[0];
											for (i = 0; i < childTerminals.length; i++) {
												if (childTerminals[i].terminal && childTerminals[i].terminal.direction.toLowerCase() === "c") {
													toTerminal = childTerminals[i];
													break;
												}
											}
										}

										// connect
										edge = _RENDERER.connect(fromTerminal, toTerminal, edge);
										guide = _RENDERER.drawGuide(edge);
										_HANDLER.enableClickSelect(edge, true, true);
										_HANDLER.enableEditLabel(edge);
										_RENDERER.toFront(guide.group);

										// clear
										$(root).removeData("edge");
										$(root).removeData("from_terminal");
										$(root).removeData("edge_terminal");
										if (toShape) {
											_RENDERER.remove(toShape.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);
										}
									}
								});
							}
						});
					}
				},
				mouseout : function (event) {
					if ($(element).attr("_shape") !== OG.Constants.SHAPE_TYPE.EDGE && $(root).data("edge")) {
						_RENDERER.remove(element.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);
						$(root).removeData("edge_terminal");
					}
				}
			});
		}
	};

	/**
	 * 주어진 Shape Element 를 Drag & Drop 으로 그룹핑 가능하도록 한다.
	 *
	 * @param {Element} element Shape Element
	 */
	this.enableDragAndDropGroup = function (element) {
		var root = _RENDERER.getRootGroup(), isSelf;
		if (element && $(element).attr("_shape") === OG.Constants.SHAPE_TYPE.GROUP) {
			$(element).bind({
				mouseover: function () {
					// Drag & Drop 하여 그룹핑하는 경우 가이드 표시
					if ($(root).data("bBoxArray")) {
						isSelf = false;
						$.each($(root).data("bBoxArray"), function (idx, item) {
							if (element.id === item.id) {
								isSelf = true;
							}
						});

						if (!isSelf) {
							$(root).data("groupTarget", element);
							_RENDERER.drawDropOverGuide(element);
						}
					}
				},
				mouseout : function (event) {
					// Drag & Drop 하여 그룹핑하는 경우 가이드 제거
					_RENDERER.remove(element.id + OG.Constants.DROP_OVER_BBOX_SUFFIX);
					$(root).removeData("groupTarget");
				}
			});
		}
	};

	/**
	 * 주어진 Shape Element 를 Collapse/Expand 가능하도록 한다.
	 *
	 * @param {Element} element Shape Element
	 */
	this.enableCollapse = function (element) {
		var collapseObj, clickHandle;

		clickHandle = function (_element, _collapsedOjb) {
			if (_collapsedOjb && _collapsedOjb.bBox && _collapsedOjb.collapse) {
				$(_collapsedOjb.collapse).bind("click", function (event) {
					if (_element.shape.isCollapsed === true) {
						_RENDERER.expand(_element);
						_collapsedOjb = _RENDERER.drawCollapseGuide(_element);
						clickHandle(_element, _collapsedOjb);
					} else {
						_RENDERER.collapse(_element);
						_collapsedOjb = _RENDERER.drawCollapseGuide(_element);
						clickHandle(_element, _collapsedOjb);
					}
				});

				$(_collapsedOjb.bBox).bind("mouseout", function (event) {
					_RENDERER.remove(_element.id + OG.Constants.COLLAPSE_BBOX);
					_RENDERER.remove(_element.id + OG.Constants.COLLAPSE_SUFFIX);
				});
			}
		};

		if (element && $(element).attr("_shape") === OG.Constants.SHAPE_TYPE.GROUP) {
			$(element).bind({
				mouseover: function () {
					collapseObj = _RENDERER.drawCollapseGuide(this);
					if (collapseObj && collapseObj.bBox && collapseObj.collapse) {
						clickHandle(element, collapseObj);
					}
				}
			});
		}
	};

	/**
	 * 주어진 Shape Element 를 마우스 클릭하여 선택가능하도록 한다.
	 *
	 * @param {Element} element Shape Element
	 * @param {Boolean} isMovable
	 * @param {Boolean} isResizable
	 */
	this.enableClickSelect = function (element, isMovable, isResizable) {
		var root = _RENDERER.getRootGroup();

		OG.Constants.DEFAULT_STYLE.SHAPE.cursor = isMovable ? 'move' : 'pointer';
		_RENDERER.setAttr(element, {cursor: isMovable ? 'move' : 'pointer'});

		// 마우스 클릭하여 선택 처리
		$(element).bind("click", function (event) {
			var guide;
			if (element.shape) {
				if (!event.shiftKey && !event.ctrlKey) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, item) {
						if (item.id) {
							_RENDERER.removeGuide(item);
							$(item).draggable("destroy");
						}
					});
				}

				if ($(element).attr("_selected") === "true") {
					if (event.shiftKey || event.ctrlKey) {
						_RENDERER.removeGuide(element);
						$(element).draggable("destroy");
					}
				} else {
					deselectChildren(element);
					if (!isParentSelected(element)) {
						guide = _RENDERER.drawGuide(element);
						if (guide) {
							setMovable(element, guide, isMovable);
							setResizable(element, guide, isResizable);
							_RENDERER.removeAllTerminal();
							_RENDERER.toFront(guide.group);
						}
					}
				}

				return false;
			}
		});
	};

	/**
	 * 마우스 드래그 영역지정 선택가능여부를 설정한다.
	 *
	 * @param isEnableDragSelect
	 * @param isMovable 이동 가능여부(isEnableDragSelect 이 true 인 경우 해당)
	 * @param isResizable 리사이즈 가능여부(isEnableDragSelect 이 true 인 경우 해당)
	 */
	this.setEnableDragSelect = function (isEnableDragSelect, isMovable, isResizable) {
		var rootEle = _RENDERER.getRootElement(), rootBBox = _RENDERER.getRootBBox();

		// 배경클릭한 경우 deselect 하도록
		$(rootEle).bind("click", function (event) {
			var dragBox = $(this).data("dragBox");
			if (dragBox && dragBox.width < 1 && dragBox.height < 1) {
				$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, item) {
					if (OG.Util.isElement(item) && item.id) {
						_RENDERER.removeGuide(item);
						$(item).draggable("destroy");
					}
				});
				_RENDERER.removeAllTerminal();
			}
		});

		if (isEnableDragSelect) {
			// 마우스 영역 드래그하여 선택 처리
			$(rootEle).bind("mousedown", function (event) {
				$(this).data("dragBox_first", {x: event.pageX - rootBBox.x, y: event.pageY - rootBBox.y});
				_RENDERER.drawRubberBand([event.pageX - rootBBox.x, event.pageY - rootBBox.y]);
			});
			$(rootEle).bind("mousemove", function (event) {
				var first = $(this).data("dragBox_first"),
					width, height, x, y;
				if (first) {
					width = event.pageX - rootBBox.x - first.x;
					height = event.pageY - rootBBox.y - first.y;
					x = width <= 0 ? first.x + width : first.x;
					y = height <= 0 ? first.y + height : first.y;
					_RENDERER.drawRubberBand([x, y], [Math.abs(width), Math.abs(height)]);
				}
			});
			$(rootEle).bind("mouseup", function (event) {
				var first = $(this).data("dragBox_first"),
					width, height, x, y, envelope, guide;
				_RENDERER.removeRubberBand(rootEle);
				if (first) {
					width = event.pageX - rootBBox.x - first.x;
					height = event.pageY - rootBBox.y - first.y;
					x = width <= 0 ? first.x + width : first.x;
					y = height <= 0 ? first.y + height : first.y;
					envelope = new OG.Envelope([x, y], Math.abs(width), Math.abs(height));
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "]").each(function (index, element) {
						if (element.shape.geom && envelope.isContainsAll(element.shape.geom.getVertices())) {
							deselectChildren(element);
							if (!isParentSelected(element)) {
								guide = _RENDERER.drawGuide(element);
								if (guide) {
									setMovable(element, guide, isMovable);
									setResizable(element, guide, isResizable);
									_RENDERER.removeAllTerminal();
								}
							}
						}
					});

					$(this).data("dragBox", {width: width, height: height, x: x, y: y});
				}
			});

			$(rootEle).bind("contextmenu", function (event) {
				_RENDERER.removeRubberBand(rootEle);
			});
		} else {
			$(rootEle).unbind("mousedown");
			$(rootEle).unbind("mousemove");
			$(rootEle).unbind("mouseup");
			$(rootEle).unbind("contextmenu");
		}
	};

	/**
	 * HotKey 사용 가능여부를 설정한다. (Delete, Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+G, Ctrl+U)
	 *
	 * @param {Boolean} isEnableHotKey 핫키가능여부
	 */
	this.setEnableHotKey = function (isEnableHotKey) {
		var root = _RENDERER.getRootGroup();

		if (isEnableHotKey) {
			// delete, ctrl+A
			$(window.document).bind("keydown", function (event) {
				var copiedElement, selectedElement, groupElement, guide, ungroupedElements;

				// Delete : 삭제
				if (event.keyCode === KeyEvent.DOM_VK_DELETE) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, item) {
						if (item.id) {
							_RENDERER.removeShape(item);
						}
					});
				}

				// Ctrl+A : 전체선택
				if (event.ctrlKey && event.keyCode === KeyEvent.DOM_VK_A) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "]").each(function (index, element) {
						if ($(element.parentNode).attr("_shape") !== OG.Constants.SHAPE_TYPE.GROUP) {
							var guide = _RENDERER.drawGuide(element);
							if (guide) {
								setMovable(element, guide, true);
								setResizable(element, guide, true);
								_RENDERER.removeTerminal(element);
							}
						}
					});
				}

				// Ctrl+C : 복사
				if (event.ctrlKey && event.keyCode === KeyEvent.DOM_VK_C) {
					selectedElement = [];
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, element) {
						selectedElement.push(element);
					});
					$(root).data("copied", selectedElement);
				}

				// Ctrl+V: 붙여넣기
				if (event.ctrlKey && event.keyCode === KeyEvent.DOM_VK_V) {
					copiedElement = $(root).data("copied");
					selectedElement = [];
					if (copiedElement) {
						$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, item) {
							if (item.id) {
								_RENDERER.removeGuide(item);
								$(item).draggable("destroy");
							}
						});

						// TODO : 연결된 Shape 인 경우 연결성 유지토록
						$.each(copiedElement, function (idx, item) {
							// copy
							var boundary = item.shape.geom.getBoundary(), newShape, newElement, newGuide;
							newShape = item.shape.clone();

							if ($(item).attr("_shape") === OG.Constants.SHAPE_TYPE.EDGE) {
								newShape.geom = new OG.PolyLine(item.shape.geom.getVertices());
								newShape.geom.move(OG.Constants.COPY_PASTE_PADDING, OG.Constants.COPY_PASTE_PADDING);
								newElement = _RENDERER.drawShape(
									null, newShape,
									null, item.shape.geom.style
								);

							} else {
								newElement = _RENDERER.drawShape(
									[ boundary.getCentroid().x + OG.Constants.COPY_PASTE_PADDING, boundary.getCentroid().y + OG.Constants.COPY_PASTE_PADDING ],
									newShape, [boundary.getWidth(), boundary.getHeight()], item.shape.geom.style
								);
							}

							// enable event
							newGuide = _RENDERER.drawGuide(newElement);
							setMovable(newElement, newGuide, true);
							setResizable(newElement, newGuide, true);
							_HANDLER.enableClickSelect(newElement, true, true);
							_HANDLER.enableDragAndDropGroup(newElement);
							_HANDLER.enableCollapse(newElement);
							if ($(newElement).attr("_shape") !== OG.Constants.SHAPE_TYPE.GROUP) {
								_HANDLER.enableEditLabel(newElement);
								_HANDLER.enableConnect(newElement);
							}

							// copy children
							copyChildren(item, newElement);

							selectedElement.push(newElement);
						});
						$(root).data("copied", selectedElement);
					}
				}

				// Ctrl+G : 그룹
				if (event.ctrlKey && event.keyCode === KeyEvent.DOM_VK_G) {
					groupElement = _RENDERER.group($("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]"));

					if (groupElement) {
						$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (idx, item) {
							_RENDERER.removeGuide(item);
							$(item).draggable("destroy");
						});

						guide = _RENDERER.drawGuide(groupElement);
						if (guide) {
							_HANDLER.enableClickSelect(groupElement, true, true);
							setMovable(groupElement, guide, true);
							setResizable(groupElement, guide, true);
							_HANDLER.enableDragAndDropGroup(groupElement);
							_RENDERER.removeAllTerminal();
							_RENDERER.toFront(guide.group);
						}
					}
				}

				// Ctrl+U : 언그룹
				if (event.ctrlKey && event.keyCode === KeyEvent.DOM_VK_U) {
					ungroupedElements = _RENDERER.ungroup($("[_shape=" + OG.Constants.SHAPE_TYPE.GROUP + "][_selected=true]"));
					$.each(ungroupedElements, function (idx, item) {
						guide = _RENDERER.drawGuide(item);
						if (guide) {
							_RENDERER.removeAllTerminal();
							_RENDERER.toFront(guide.group);
						}
					});
				}

				// Shift+화살표 : 이동
				if (event.shiftKey && event.keyCode === KeyEvent.DOM_VK_LEFT) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, element) {
						// TODO : 화살표 클릭시 이동 처리
					});
				}
				if (event.shiftKey && event.keyCode === KeyEvent.DOM_VK_RIGHT) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, element) {
					});
				}
				if (event.shiftKey && event.keyCode === KeyEvent.DOM_VK_UP) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, element) {
					});
				}
				if (event.shiftKey && event.keyCode === KeyEvent.DOM_VK_DOWN) {
					$("[_type=" + OG.Constants.NODE_TYPE.SHAPE + "][_selected=true]").each(function (index, element) {
					});
				}
			});
		} else {
			$(window.document).unbind("keydown");
		}
	};
};
OG.handler.EventHandler.prototype = new OG.handler.EventHandler();
OG.handler.EventHandler.prototype.constructor = OG.handler.EventHandler;
OG.EventHandler = OG.handler.EventHandler;
/**
 * OpenGraph 캔버스 클래스
 *
 * @class
 * @requires OG.common.*, OG.geometry.*, OG.shape.*, OG.renderer.*, OG.handler.*, OG.layout.*, raphael-2.1.0
 *
 * @param {HTMLElement,String} container 컨테이너 DOM element or ID
 * @param {Object} config JSON 포맷의 configuration
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.graph.Canvas = function (container) {
	var _RENDERER = container ? new OG.RaphaelRenderer(container, [this.width, this.height]) : null,
		_HANDLER = new OG.EventHandler(_RENDERER),
		_CONTAINER = OG.Util.isElement(container) ? container : document.getElementById(container);

	this.RENDERER = _RENDERER;
	this.CONTAINER = _CONTAINER;

	/**
	 * Canvas 의 설정값을 초기화한다.
	 *
	 * <pre>
	 * - selectable		: 선택가능여부(디폴트 true)
	 * - enableHotKey   : 핫키가능여부(디폴트 true)
	 * - connectable    : 연결가능여부(디폴트 true)
	 * - labelEditable  : 라벨수정여부(디폴트 true)
	 * - groupDropable  : 그룹핑가능여부(디폴트 true)
	 * - collapsible    : 최소화가능여부(디폴트 true)
	 * </pre>
	 *
	 * @param {Object} config JSON 포맷의 configuration
	 */
	this.initConfig = function (config) {
		this.selectable = config ? (config.selectable === undefined ? true : config.selectable) : true;
		this.enableHotKey = config ? (config.enableHotKey === undefined ? true : config.enableHotKey) : true;
		this.connectable = config ? (config.connectable === undefined ? true : config.connectable) : true;
		this.labelEditable = config ? (config.labelEditable === undefined ? true : config.labelEditable) : true;
		this.groupDropable = config ? (config.groupDropable === undefined ? true : config.groupDropable) : true;
		this.collapsible = config ? (config.collapsible === undefined ? true : config.collapsible) : true;

		if (this.selectable) {
			_HANDLER.setEnableDragSelect(true, true, true);
		}

		if (this.enableHotKey) {
			_HANDLER.setEnableHotKey(true);
		}

		this.CONFIG_INITIALIZED = true;
	};

	/**
	 * Shape 을 캔버스에 위치 및 사이즈 지정하여 드로잉한다.
	 *
	 * @example
	 * canvas.drawShape([100, 100], new OG.CircleShape(), [50, 50], {stroke:'red'});
	 *
	 * @param {Number[]} position 드로잉할 위치 좌표(중앙 기준)
	 * @param {OG.shape.IShape} shape Shape
	 * @param {Number[]} size Shape Width, Height
	 * @param {OG.geometry.Style,Object} style 스타일 (Optional)
	 * @param {String} id Element ID 지정 (Optional)
	 * @param {String} parentId 부모 Element ID 지정 (Optional)
	 * @return {Element} Group DOM Element with geometry
	 */
	this.drawShape = function (position, shape, size, style, id, parentId) {
		var element = _RENDERER.drawShape(position, shape, size, style, id);
		if (parentId && _RENDERER.getElementById(parentId)) {
			_RENDERER.appendChild(element, parentId);
		}

		if (!this.CONFIG_INITIALIZED) {
			this.initConfig();
		}

		if (this.selectable) {
			_HANDLER.enableClickSelect(element, true, true);
		}

		if (this.connectable) {
			_HANDLER.enableConnect(element);
		}

		if (this.labelEditable) {
			_HANDLER.enableEditLabel(element);
		}

		if (this.groupDropable) {
			_HANDLER.enableDragAndDropGroup(element);
		}

		if (this.collapsible) {
			_HANDLER.enableCollapse(element);
		}

		return element;
	};

	/**
	 * 주어진 Shape 엘리먼트에 커스텀 데이타를 저장한다.
	 *
	 * @param {Element,String} shapeElement Shape DOM Element or ID
	 * @param {Object} data JSON 포맷의 Object
	 */
	this.setCustomData = function (shapeElement, data) {
		var element = OG.Util.isElement(shapeElement) ? shapeElement : document.getElementById(shapeElement);
		element.data = data;
	};

	/**
	 * 주어진 Shape 엘리먼트에 저장된 커스텀 데이터를 반환한다.
	 *
	 * @param {Element,String} shapeElement Shape DOM Element or ID
	 * @return {Object} JSON 포맷의 Object
	 */
	this.getCustomData = function (shapeElement) {
		var element = OG.Util.isElement(shapeElement) ? shapeElement : document.getElementById(shapeElement);
		return element.data;
	};

	/**
	 *    Canvas 에 그려진 Shape 들을 OpenGraph XML 문자열로 export 한다.
	 *
	 * @return {String} XML 문자열
	 */
	this.toXML = function () {
		return OG.Util.jsonToXml(this.toJSON());
	};

	/**
	 * Canvas 에 그려진 Shape 들을 OpenGraph JSON 객체로 export 한다.
	 *
	 * @return {Object} JSON 포맷의 Object
	 */
	this.toJSON = function () {
		var jsonObj = { opengraph: {
				cell: []
			}},
			childShape;

		childShape = function (node, isRoot) {
			$(node).children("[_type=SHAPE]").each(function (idx, item) {
				var shape = item.shape,
					geom = shape.geom,
					envelope = geom.getBoundary(),
					cell = {},
					vertices,
					from,
					to;

				cell['@id'] = $(item).attr('id');
				if (!isRoot) {
					cell['@parent'] = $(node).attr('id');
				}
				cell['@shapeType'] = shape.TYPE;
				cell['@shapeId'] = shape.SHAPE_ID;
				cell['@x'] = envelope.getCentroid().x;
				cell['@y'] = envelope.getCentroid().y;
				cell['@width'] = envelope.getWidth();
				cell['@height'] = envelope.getHeight();
				cell['@style'] = geom.style.toString();

				if ($(item).attr('_from')) {
					cell['@from'] = $(item).attr('_from');
				}
				if ($(item).attr('_to')) {
					cell['@to'] = $(item).attr('_to');
				}
				if ($(item).attr('_fromedge')) {
					cell['@fromEdge'] = $(item).attr('_fromedge');
				}
				if ($(item).attr('_toedge')) {
					cell['@toEdge'] = $(item).attr('_toedge');
				}
				if (shape.label) {
					cell['@label'] = shape.label;
				}
				if (shape.angle && shape.angle !== 0) {
					cell['@angle'] = shape.angle;
				}
				if (shape instanceof OG.shape.ImageShape) {
					cell['@value'] = shape.image;
				} else if (shape instanceof OG.shape.TextShape) {
					cell['@value'] = shape.text;
				} else if (shape instanceof OG.shape.EdgeShape) {
					vertices = geom.getVertices();
					from = vertices[0];
					to = vertices[vertices.length - 1];
					cell['@value'] = from + ',' + to;
				}
				if (item.data) {
					cell['@data'] = escape(OG.JSON.encode(item.data));
				}

				jsonObj.opengraph.cell.push(cell);

				childShape(item, false);
			});
		};

		childShape(_RENDERER.getRootGroup(), true);

		return jsonObj;
	};

	/**
	 * OpenGraph XML 문자열로 부터 Shape 을 드로잉한다.
	 *
	 * @param {String} xml XML 문자열
	 */
	this.loadXML = function (xml) {
		this.loadJSON(OG.Util.xmlToJson(xml));
	};

	/**
	 * JSON 객체로 부터 Shape 을 드로잉한다.
	 *
	 * @param {Object} json JSON 포맷의 Object
	 */
	this.loadJSON = function (json) {
		var i, cell, shape, id, parent, shapeType, shapeId, x, y, width, height, style, from, to,
			fromEdge, toEdge, label, angle, value, data, element;

		_RENDERER.clear();

		if (json.opengraph && json.opengraph.cell && OG.Util.isArray(json.opengraph.cell)) {
			cell = json.opengraph.cell;
			for (i = 0; i < cell.length; i++) {
				id = cell[i]['@id'];
				parent = cell[i]['@parent'];
				shapeType = cell[i]['@shapeType'];
				shapeId = cell[i]['@shapeId'];
				x = parseInt(cell[i]['@x'], 10);
				y = parseInt(cell[i]['@y'], 10);
				width = parseInt(cell[i]['@width'], 10);
				height = parseInt(cell[i]['@height'], 10);
				style = cell[i]['@style'];

				from = cell[i]['@from'];
				to = cell[i]['@to'];
				fromEdge = cell[i]['@fromEdge'];
				toEdge = cell[i]['@toEdge'];
				label = cell[i]['@label'];
				angle = cell[i]['@angle'];
				value = cell[i]['@value'];
				data = cell[i]['@data'];

				switch (shapeType) {
				case OG.Constants.SHAPE_TYPE.GEOM:
				case OG.Constants.SHAPE_TYPE.GROUP:
					if (label) {
						shape = eval('new ' + shapeId + '(\'' + label + '\')');
					} else {
						shape = eval('new ' + shapeId + '()');
					}
					element = this.drawShape([x, y], shape, [width, height], OG.JSON.decode(style), id, parent);
					break;
				case OG.Constants.SHAPE_TYPE.EDGE:
					if (label) {
						shape = eval('new ' + shapeId + '(' + value + ', \'' + label + '\')');
					} else {
						shape = eval('new ' + shapeId + '(' + value + ')');
					}
					element = this.drawShape(null, shape, null, OG.JSON.decode(style), id, parent);
					break;
				case OG.Constants.SHAPE_TYPE.IMAGE:
					if (label) {
						shape = eval('new ' + shapeId + '(\'' + value + '\', \'' + label + '\')');
					} else {
						shape = eval('new ' + shapeId + '(\'' + value + '\')');
					}
					element = this.drawShape([x, y], shape, [width, height, angle], OG.JSON.decode(style), id, parent);
					break;
				case OG.Constants.SHAPE_TYPE.TEXT:
					shape = eval('new ' + shapeId + '(\'' + value + '\')');
					element = this.drawShape([x, y], shape, [width, height, angle], OG.JSON.decode(style), id, parent);
					break;
				}

				if (from) {
					$(element).attr('_from', from);
				}
				if (to) {
					$(element).attr('_to', to);
				}
				if (fromEdge) {
					$(element).attr('_fromedge', fromEdge);
				}
				if (toEdge) {
					$(element).attr('_toedge', toEdge);
				}
				if (data) {
					element.data = OG.JSON.decode(unescape(data));
				}
			}
		}
	};
};
OG.graph.Canvas.prototype = new OG.graph.Canvas();
OG.graph.Canvas.prototype.constructor = OG.graph.Canvas;
OG.Canvas = OG.graph.Canvas;
