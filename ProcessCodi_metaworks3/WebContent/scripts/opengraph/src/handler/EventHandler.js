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