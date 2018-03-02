(function ($) {
$.Request = function(m) {
    var sValue = location.search.match(new RegExp("[\?\&]" + m + "=([^\&]*)(\&?)", "i"));
    var keyValue = sValue ? sValue[1] : sValue;
    return decodeURIComponent(keyValue);
};
$.UrlUpdateParams=function (url, name, value) {
    var r = url;
    if (r != null && r != 'undefined' && r != "") {
        value = encodeURIComponent(value);
        var reg = new RegExp("(^|)" + name + "=([^&]*)(|$)");
        var tmp = name + "=" + value;
        if (url.match(reg) != null) {
            r = url.replace(eval(reg), tmp);
        }
        else {
            if (url.match("[\?]")) {
                r = url + "&" + tmp;
            } else {
                r = url + "?" + tmp;
            }
        }
    }
    return r;
}
$.fn.serializeJson=function(){
var serializeObj={};
var array=this.serializeArray();
var str=this.serialize();
$(array).each(function(){
if(serializeObj[this.name]){
if($.isArray(serializeObj[this.name])){
serializeObj[this.name].push(this.value);
}else{
serializeObj[this.name]=[serializeObj[this.name],this.value];
}
}else{
serializeObj[this.name]=this.value;
}
});
return serializeObj;
};
$.fn.setSerializeForm = function loadData(jsonStr) {
    var obj = jsonStr;
    var key, value, tagName, type, arr;
    for (x in obj) {
        key = x;
        value = obj[x];

        $("[name='" + key + "'],[name='" + key + "[]']").each(function () {
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName == 'INPUT') {
                if (type == 'radio') {
                    $(this).attr('checked', $(this).val() == value);
                } else if (type == 'checkbox') {
                    arr = value.split(',');
                    for (var i = 0; i < arr.length; i++) {
                        if ($(this).val() == arr[i]) {
                            $(this).attr('checked', true);
                            break;
                        }
                    }
                } else {
                    $(this).val(value);
                }
            } else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
                $(this).val(value);
            }

        });
    }
}
$.fn.setSerializeFormToLower = function loadData(jsonStr) {
    var obj = jsonStr;
    var key, value, tagName, type, arr;
    for (x in obj) {
        key = x.substring(0, 1).toLowerCase() + x.substring(1);
        value = obj[x];
        var thisform = this;
        var $oinput = thisform.find("[name='" + key + "'],[name='" + key + "[]']");
        $oinput.each(function () {
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName == 'INPUT') {
                if (type == 'radio') {
                    $(this).attr('checked', $(this).val() == value);
                } else if (type == 'checkbox') {
                    arr = value.split(',');
                    for (var i = 0; i < arr.length; i++) {
                        if ($(this).val() == arr[i]) {
                            $(this).attr('checked', true);
                            break;
                        }
                    }
                } else {
                    //if ($(this).val()==0||$(this).val()==""||$(this).val()==undefined) {
                        $(this).val(value);
                    //}
                }
            } else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
                $(this).val(value);
            }

        });
    }
}
})(jQuery);