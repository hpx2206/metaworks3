/* 
 * 날짜 형식
 */
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

Date.prototype.ISODateString = function() {
	var d = this;
	
    function pad(n){
        return n>10 ? '0'+n : n
    }
    return d.getUTCFullYear()+'-'
    + pad(d.getUTCMonth()+1)+'-'
    + pad(d.getUTCDate())+'T'
    + pad(d.getUTCHours())+':'
    + pad(d.getUTCMinutes())+':'
    + pad(d.getUTCSeconds())+'Z'
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

jQuery.fn.selectRange = function(start, end) {
    return this.each(function() {
        if (this.setSelectionRange) {
            this.focus();
            this.setSelectionRange(start, end);
        } else if (this.createTextRange) {
            var range = this.createTextRange();
            range.collapse(true);
            range.moveEnd('character', end);
            range.moveStart('character', start);
            range.select();
        }
    });
}; 
	 		
var setCookie = function (cKey, cValue){
    var date = new Date();
    var validity = 10;
    date.setDate(date.getDate() + validity);
    document.cookie = cKey + '=' + escape(cValue) + ';expires=' + date.toGMTString();
};

var delCookie = function (cKey) {
    var date = new Date(); // ���� ��¥ 
    var validity = -1;
    date.setDate(date.getDate() + validity);
    document.cookie = cKey + "=;expires=" + date.toGMTString();
};

var getCookie = function (cKey) {
    var allcookies = document.cookie;
    var cookies = allcookies.split("; ");
    
    for (var i = 0; i < cookies.length; i++) {
        var keyValues = cookies[i].split("=");
        if (keyValues[0] == cKey) {
            return unescape(keyValues[1]);
        }
    }
    return "";
};

var Request = function() {
	this.getParameter = function(name) {
		var rtnval = "";
		var nowAddress = unescape(location.href);
		var parameters = (nowAddress.slice(nowAddress.indexOf("?") + 1,
				nowAddress.length)).split("&");

		for ( var i = 0; i < parameters.length; i++) {
			var varName = parameters[i].split("=")[0];
			if (varName.toUpperCase() == name.toUpperCase()) {
				rtnval = parameters[i].split("=")[1];
				break;
			}
		}
		return rtnval;
	}
}