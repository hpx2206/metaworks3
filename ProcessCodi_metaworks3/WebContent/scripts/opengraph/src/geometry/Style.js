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