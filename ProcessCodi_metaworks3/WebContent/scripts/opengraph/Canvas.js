/**
 * OpenGraph 캔버스 클래스
 *
 * @class
 * @requires OG.common.*, OG.geometry.*, OG.shape.*, OG.renderer.*, OG.handler.*, OG.layout.*, raphael-2.1.0
 *
 * @param {HTMLElement,String} container 컨테이너 DOM element or ID
 * @param {Number[]} containerSize 컨테이너 Width, Height
 * @param {String} backgroundColor 캔버스 배경색
 * @author <a href="mailto:hrkenshin@gmail.com">Seungbaek Lee</a>
 */
OG.graph.Canvas = function (container, containerSize, backgroundColor) {
	var _RENDERER = container ? new OG.RaphaelRenderer(container, containerSize, backgroundColor) : null,
		_HANDLER = new OG.EventHandler(_RENDERER),
		_CONTAINER = OG.Util.isElement(container) ? container : document.getElementById(container);

	/**
	 * Canvas 의 설정값을 초기화한다.
	 *
	 * <pre>
	 * - selectable         : 클릭선택 가능여부(디폴트 true)
	 * - dragSelectable     : 마우스드래그선택 가능여부(디폴트 true)
	 * - movable            : 이동 가능여부(디폴트 true)
	 * - resizable          : 리사이즈 가능여부(디폴트 true)
	 * - connectable        : 연결 가능여부(디폴트 true)
	 * - connectCloneable   : 드래그하여 연결시 대상 없을 경우 자동으로 Shape 복사하여 연결 처리 여부(디폴트 true)
	 * - connectRequired    : 드래그하여 연결시 연결대상 있는 경우에만 Edge 드로잉 처리 여부(디폴트 true)
	 * - labelEditable      : 라벨 수정여부(디폴트 true)
	 * - groupDropable      : 그룹핑 가능여부(디폴트 true)
	 * - collapsible        : 최소화 가능여부(디폴트 true)
	 * - enableHotKey       : 핫키 가능여부(디폴트 true)
	 * </pre>
	 *
	 * @param {Object} config JSON 포맷의 configuration
	 */
	this.initConfig = function (config) {
		if (config) {
			OG.Constants.SELECTABLE = config.selectable === undefined ? OG.Constants.SELECTABLE : config.selectable;
			OG.Constants.DRAG_SELECTABLE = config.dragSelectable === undefined ? OG.Constants.DRAG_SELECTABLE : config.dragSelectable;
			OG.Constants.MOVABLE = config.movable === undefined ? OG.Constants.MOVABLE : config.movable;
			OG.Constants.RESIZABLE = config.resizable === undefined ? OG.Constants.RESIZABLE : config.resizable;
			OG.Constants.CONNECTABLE = config.connectable === undefined ? OG.Constants.CONNECTABLE : config.connectable;
			OG.Constants.CONNECT_CLONEABLE = config.connectCloneable === undefined ? OG.Constants.CONNECT_CLONEABLE : config.connectCloneable;
			OG.Constants.CONNECT_REQUIRED = config.connectRequired === undefined ? OG.Constants.CONNECT_REQUIRED : config.connectRequired;
			OG.Constants.LABEL_EDITABLE = config.labelEditable === undefined ? OG.Constants.LABEL_EDITABLE : config.labelEditable;
			OG.Constants.GROUP_DROPABLE = config.groupDropable === undefined ? OG.Constants.GROUP_DROPABLE : config.groupDropable;
			OG.Constants.GROUP_COLLAPSIBLE = config.collapsible === undefined ? OG.Constants.GROUP_COLLAPSIBLE : config.collapsible;
			OG.Constants.ENABLE_HOTKEY = config.enableHotKey === undefined ? OG.Constants.ENABLE_HOTKEY : config.enableHotKey;
		}

		_HANDLER.setDragSelectable(OG.Constants.SELECTABLE && OG.Constants.DRAG_SELECTABLE);
		_HANDLER.setEnableHotKey(OG.Constants.ENABLE_HOTKEY);

		this.CONFIG_INITIALIZED = true;
	};

	/**
	 * 랜더러를 반환한다.
	 *
	 * @return {OG.RaphaelRenderer}
	 */
	this.getRenderer = function () {
		return _RENDERER;
	};

	/**
	 * 컨테이너 DOM element 를 반환한다.
	 *
	 * @return {HTMLElement}
	 */
	this.getContainer = function () {
		return _CONTAINER;
	};

	/**
	 * 이벤트 핸들러를 반환한다.
	 *
	 * @return {OG.EventHandler}
	 */
	this.getEventHandler = function () {
		return _HANDLER;
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

		_HANDLER.setClickSelectable(element, OG.Constants.SELECTABLE);
		_HANDLER.setMovable(element, OG.Constants.SELECTABLE && OG.Constants.MOVABLE);

		if (OG.Constants.CONNECTABLE) {
			_HANDLER.enableConnect(element);
		}

		if (OG.Constants.LABEL_EDITABLE) {
			_HANDLER.enableEditLabel(element);
		}

		if (OG.Constants.GROUP_DROPABLE) {
			_HANDLER.enableDragAndDropGroup(element);
		}

		if (OG.Constants.GROUP_COLLAPSIBLE) {
			_HANDLER.enableCollapse(element);
		}

		return element;
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
		return _RENDERER.drawLabel(shapeElement, text, style);
	};

	/**
	 * 두개의 Shape 을 Edge 로 연결한다.
	 *
	 * @param {Element} fromElement from Shape Element
	 * @param {Element} toElement to Shape Element
	 * @param {OG.geometry.Style,Object} style 스타일
	 * @param {String} label Label
	 * @return {Element} 연결된 Edge 엘리먼트
	 */
	this.connect = function (fromElement, toElement, style, label) {
		var terminalGroup, childTerminals, fromTerminal, toTerminal, i, edge, guide;

		// from Shape 센터 연결 터미널 찾기
		terminalGroup = _RENDERER.drawTerminal(fromElement, OG.Constants.TERMINAL_TYPE.OUT);
		childTerminals = terminalGroup.terminal.childNodes;
		fromTerminal = childTerminals[0];
		for (i = 0; i < childTerminals.length; i++) {
			if (childTerminals[i].terminal && childTerminals[i].terminal.direction.toLowerCase() === "c") {
				fromTerminal = childTerminals[i];
				break;
			}
		}
		_RENDERER.removeTerminal(fromElement);

		// to Shape 센터 연결 터미널 찾기
		terminalGroup = _RENDERER.drawTerminal(toElement, OG.Constants.TERMINAL_TYPE.IN);
		childTerminals = terminalGroup.terminal.childNodes;
		toTerminal = childTerminals[0];
		for (i = 0; i < childTerminals.length; i++) {
			if (childTerminals[i].terminal && childTerminals[i].terminal.direction.toLowerCase() === "c") {
				toTerminal = childTerminals[i];
				break;
			}
		}
		_RENDERER.removeTerminal(toElement);

		// draw edge
		edge = _RENDERER.drawShape(null, new OG.EdgeShape(fromTerminal.terminal.position, toTerminal.terminal.position));

		// connect
		edge = _RENDERER.connect(fromTerminal, toTerminal, edge, style, label);

		guide = _RENDERER.drawGuide(edge);

		// enable event
		_HANDLER.setClickSelectable(edge, OG.Constants.SELECTABLE);
		_HANDLER.setMovable(edge, OG.Constants.SELECTABLE && OG.Constants.MOVABLE);
		_HANDLER.setResizable(edge, guide, OG.Constants.SELECTABLE && OG.Constants.RESIZABLE);
		if ($(edge).attr("_shape") !== OG.Constants.SHAPE_TYPE.GROUP) {
			if (OG.Constants.LABEL_EDITABLE) {
				_HANDLER.enableEditLabel(edge);
			}
		}
		_RENDERER.toFront(guide.group);

		return edge;
	};

	/**
	 * 연결속성정보를 삭제한다. Edge 인 경우는 라인만 삭제하고, 일반 Shape 인 경우는 연결된 모든 Edge 를 삭제한다.
	 *
	 * @param {Element} element
	 */
	this.disconnect = function (element) {
		_RENDERER.disconnect(element);
	};

	/**
	 * 주어진 Shape 들을 그룹핑한다.
	 *
	 * @param {Element[]} elements
	 * @return {Element} Group Shape Element
	 */
	this.group = function (elements) {
		return _RENDERER.group(elements);
	};

	/**
	 * 주어진 그룹들을 그룹해제한다.
	 *
	 * @param {Element[]} groupElements
	 * @return {Element[]} ungrouped Elements
	 */
	this.ungroup = function (groupElements) {
		return _RENDERER.ungroup(groupElements);
	};

	/**
	 * 주어진 Shape 들을 그룹에 추가한다.
	 *
	 * @param {Element} groupElement
	 * @param {Element[]} elements
	 */
	this.addToGroup = function (groupElement, elements) {
		_RENDERER.addToGroup(groupElement, elements);
	};

	/**
	 * 주어진 Shape 이 그룹인 경우 collapse 한다.
	 *
	 * @param {Element} element
	 */
	this.collapse = function (element) {
		_RENDERER.collapse(element);
	};

	/**
	 * 주어진 Shape 이 그룹인 경우 expand 한다.
	 *
	 * @param {Element} element
	 */
	this.expand = function (element) {
		_RENDERER.expand(element);
	};

	/**
	 * 드로잉된 모든 오브젝트를 클리어한다.
	 */
	this.clear = function () {
		_RENDERER.clear();
	};

	/**
	 * Shape 을 캔버스에서 관련된 모두를 삭제한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.removeShape = function (element) {
		_RENDERER.removeShape(element);
	};

	/**
	 * 하위 엘리먼트만 제거한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.removeChild = function (element) {
		_RENDERER.removeChild(element);
	};

	/**
	 * 랜더러 캔버스 Root Element 를 반환한다.
	 *
	 * @return {Element} Element
	 */
	this.getRootElement = function () {
		return _RENDERER.getRootElement();
	};

	/**
	 * 랜더러 캔버스 Root Group Element 를 반환한다.
	 *
	 * @return {Element} Element
	 */
	this.getRootGroup = function () {
		return _RENDERER.getRootGroup();
	};

	/**
	 * 주어진 지점을 포함하는 Top Element 를 반환한다.
	 *
	 * @param {Number[]} position 위치 좌표
	 * @return {Element} Element
	 */
	this.getElementByPoint = function (position) {
		return _RENDERER.getElementByPoint(position);
	};

	/**
	 * 주어진 Boundary Box 영역에 포함되는 Shape(GEOM, TEXT, IMAGE) Element 를 반환한다.
	 *
	 * @param {OG.geometry.Envelope} envelope Boundary Box 영역
	 * @return {Element[]} Element
	 */
	this.getElementsByBBox = function (envelope) {
		return _RENDERER.getElementsByBBox(envelope);
	};

	/**
	 * 엘리먼트에 속성값을 설정한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Object} attribute 속성값
	 */
	this.setAttr = function (element, attribute) {
		_RENDERER.setAttr(element, attribute);
	};

	/**
	 * 엘리먼트 속성값을 반환한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {String} attrName 속성이름
	 * @return {Object} attribute 속성값
	 */
	this.getAttr = function (element, attrName) {
		return _RENDERER.getAttr(element, attrName);
	};

	/**
	 * ID에 해당하는 Element 를 최상단 레이어로 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.toFront = function (element) {
		_RENDERER.toFront(element);
	};

	/**
	 * ID에 해당하는 Element 를 최하단 레이어로 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.toBack = function (element) {
		_RENDERER.toBack(element);
	};

	/**
	 * 랜더러 캔버스의 사이즈(Width, Height)를 변경한다.
	 *
	 * @param {Number[]} size Canvas Width, Height
	 */
	this.setCanvasSize = function (size) {
		_RENDERER.setCanvasSize(size);
	};

	/**
	 * 새로운 View Box 영역을 설정한다. (ZoomIn & ZoomOut 가능)
	 *
	 * @param @param {Number[]} position 위치 좌표(좌상단 기준)
	 * @param {Number[]} size Canvas Width, Height
	 * @param {Boolean} isFit Fit 여부
	 */
	this.setViewBox = function (position, size, isFit) {
		_RENDERER.setViewBox(position, size, isFit);
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 show 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.show = function (element) {
		_RENDERER.show(element);
	};

	/**
	 * ID에 해당하는 Element 를 캔버스에서 hide 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 */
	this.hide = function (element) {
		_RENDERER.hide(element);
	};

	/**
	 * Source Element 를 Target Element 아래에 append 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 */
	this.appendChild = function (srcElement, targetElement) {
		return _RENDERER.appendChild(srcElement, targetElement);
	};

	/**
	 * Source Element 를 Target Element 이후에 insert 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 */
	this.insertAfter = function (srcElement, targetElement) {
		return _RENDERER.insertAfter(srcElement, targetElement);
	};

	/**
	 * Source Element 를 Target Element 이전에 insert 한다.
	 *
	 * @param {Element,String} srcElement Element 또는 ID
	 * @param {Element,String} targetElement Element 또는 ID
	 * @return {Element} Source Element
	 */
	this.insertBefore = function (srcElement, targetElement) {
		return _RENDERER.insertBefore(srcElement, targetElement);
	};

	/**
	 * 해당 Element 를 가로, 세로 Offset 만큼 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} offset [가로, 세로]
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 */
	this.move = function (element, offset, excludeEdgeId) {
		return _RENDERER.move(element, offset, excludeEdgeId);
	};

	/**
	 * 주어진 중심좌표로 해당 Element 를 이동한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} position [x, y]
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 */
	this.moveCentroid = function (element, position, excludeEdgeId) {
		return _RENDERER.moveCentroid(element, position, excludeEdgeId);
	};

	/**
	 * 중심 좌표를 기준으로 주어진 각도 만큼 회전한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number} angle 각도
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 */
	this.rotate = function (element, angle, excludeEdgeId) {
		return _RENDERER.rotate(element, angle, excludeEdgeId);
	};

	/**
	 * 상, 하, 좌, 우 외곽선을 이동한 만큼 리사이즈 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} offset [상, 하, 좌, 우] 각 방향으로 + 값
	 * @param {String[]} excludeEdgeId redraw 제외할 Edge ID
	 * @return {Element} Element
	 */
	this.resize = function (element, offset, excludeEdgeId) {
		return _RENDERER.resize(element, offset, excludeEdgeId);
	};

	/**
	 * 중심좌표는 고정한 채 Bounding Box 의 width, height 를 리사이즈 한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @param {Number[]} size [Width, Height]
	 * @return {Element} Element
	 */
	this.resizeBox = function (element, size) {
		return _RENDERER.resizeBox(element, size);
	};

	/**
	 * 노드 Element 를 복사한다.
	 *
	 * @param {Element,String} element Element 또는 ID
	 * @return {Element} Element
	 */
	this.clone = function (element) {
		return _RENDERER.clone(element);
	};

	/**
	 * ID로 Node Element 를 반환한다.
	 *
	 * @param {String} id
	 * @return {Element} Element
	 */
	this.getElementById = function (id) {
		return _RENDERER.getElementById(id);
	};

	/**
	 * 해당 엘리먼트의 BoundingBox 영역 정보를 반환한다.
	 *
	 * @param {Element,String} element
	 * @return {Object} {width, height, x, y, x2, y2}
	 */
	this.getBBox = function (element) {
		return _RENDERER.getBBox(element);
	};

	/**
	 * 부모노드기준으로 캔버스 루트 엘리먼트의 BoundingBox 영역 정보를 반환한다.
	 *
	 * @return {Object} {width, height, x, y, x2, y2}
	 */
	this.getRootBBox = function () {
		return _RENDERER.getRootBBox();
	};

	/**
	 * SVG 인지 여부를 반환한다.
	 *
	 * @return {Boolean} svg 여부
	 */
	this.isSVG = function () {
		return _RENDERER.isSVG();
	};

	/**
	 * VML 인지 여부를 반환한다.
	 *
	 * @return {Boolean} vml 여부
	 */
	this.isVML = function () {
		return _RENDERER.isVML();
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
					style = item.shapeStyle,
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
				if (style) {
					cell['@style'] = escape(OG.JSON.encode(style));
				}

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
				} else if (shape instanceof OG.shape.HtmlShape) {
					cell['@value'] = escape(shape.htmlString);
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

		if (json && json.opengraph && json.opengraph.cell && OG.Util.isArray(json.opengraph.cell)) {
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
				style = unescape(cell[i]['@style']);

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
				case OG.Constants.SHAPE_TYPE.HTML:
					if (label) {
						shape = eval('new ' + shapeId + '(\'' + unescape(value) + '\', \'' + label + '\')');
					} else {
						shape = eval('new ' + shapeId + '(\'' + unescape(value) + '\')');
					}
					element = this.drawShape([x, y], shape, [width, height, angle], OG.JSON.decode(style), id, parent);
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

	/**
	 * Shape 이 처음 Draw 되었을 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, shapeElement)
	 */
	this.onDrawShape = function (callbackFunc) {
		$(this.getRootElement()).bind('drawShape', function (event, shapeElement) {
			callbackFunc(event, shapeElement);
		});
	};

	/**
	 * Shape 이 Redraw 되었을 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, shapeElement)
	 */
	this.onRedrawShape = function (callbackFunc) {
		$(this.getRootElement()).bind('redrawShape', function (event, shapeElement) {
			callbackFunc(event, shapeElement);
		});
	};

	/**
	 * Shape 이 Remove 될 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, shapeElement)
	 */
	this.onRemoveShape = function (callbackFunc) {
		$(this.getRootElement()).bind('removeShape', function (event, shapeElement) {
			callbackFunc(event, shapeElement);
		});
	};

	/**
	 * Shape 이 Move 되었을 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, shapeElement, offset)
	 */
	this.onMoveShape = function (callbackFunc) {
		$(this.getRootElement()).bind('moveShape', function (event, shapeElement, offset) {
			callbackFunc(event, shapeElement, offset);
		});
	};

	/**
	 * Shape 이 Resize 되었을 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, shapeElement, offset)
	 */
	this.onResizeShape = function (callbackFunc) {
		$(this.getRootElement()).bind('resizeShape', function (event, shapeElement, offset) {
			callbackFunc(event, shapeElement, offset);
		});
	};

	/**
	 * Shape 이 Connect 되었을 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, edgeElement, fromElement, toElement)
	 */
	this.onConnectShape = function (callbackFunc) {
		$(this.getRootElement()).bind('connectShape', function (event, edgeElement, fromElement, toElement) {
			callbackFunc(event, edgeElement, fromElement, toElement);
		});
	};

	/**
	 * Shape 이 Disconnect 되었을 때의 이벤트 리스너
	 *
	 * @param {Function} callbackFunc 콜백함수(event, edgeElement, fromElement, toElement)
	 */
	this.onDisconnectShape = function (callbackFunc) {
		$(this.getRootElement()).bind('disconnectShape', function (event, edgeElement, fromElement, toElement) {
			callbackFunc(event, edgeElement, fromElement, toElement);
		});
	};
};
OG.graph.Canvas.prototype = new OG.graph.Canvas();
OG.graph.Canvas.prototype.constructor = OG.graph.Canvas;
OG.Canvas = OG.graph.Canvas;