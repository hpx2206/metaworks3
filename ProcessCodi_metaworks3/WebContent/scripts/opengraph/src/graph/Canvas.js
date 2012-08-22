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