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