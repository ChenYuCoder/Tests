var loginRequest;

var VERIFYCODE_TIP = "验证码输入有误，请重新输入！";
var PASSWORD_TIP = "用户名或密码错误！";
//var encrypt;
function freshCode(){
    $("#verify-code").attr("src","su/verifycode?"+Math.random());
}

$(function() {
    bindEnterKeyEvent();
//	var userId = $.cookie("userId");
//	var password = $.cookie("password");
//	var rememberStr = $.cookie("remember");
//	var remember = rememberStr == "true";
//	encrypt = new JSEncrypt();
//	$("#password").val(password);
//	$("#userId").val(userId);
//	$("#remember-password").attr('checked', remember);
    $("#code-input, #userId, #password").focus(function() {
        $("#code-tip").hide();
    });
//	$.get("/DashBoard/su/publickey",
//		function(key){
//		encrypt.setPublicKey(key);
//	}
//	);

});

function bindEnterKeyEvent() {
    $("#login-form").keydown(function(event){
        if(event.keyCode ==13){
            onLogin();
        }
    });
}

function onLogin() {
//	var remember = $("#remember-password")[0].checked;
//	var userId = encrypt.encrypt($("#userId").val());
//	var password = encrypt.encrypt($("#password").val());
    var userId = $("#userId").val();
    var password = $("#password").val();
    var verifyCode = $("#code-input").val();
    $.removeCookie("access");
    if(loginRequest) {
        loginRequest.abort();
    }
    loginRequest = $.post("/DashBoard/su/login", {
        "username" : userId,
        "password" : password,
        "verifyCode" : verifyCode
    }, function(data) {
        data=$.parseJSON(data);
        if(data.code==0){
//			if(remember) {
//				setCookie("userId", userId);
//				setCookie("password", password);
//				setCookie("remember", remember);
//			} else {
//				setCookie("userId", "");
//				setCookie("password", "");
//				setCookie("remember", remember);
//			}
            var url = getBaseRootPath() + '/index';
            window.location.href=url;
        } else if (data.code == 2) {
            showTip(VERIFYCODE_TIP);
        } else {
            showTip(PASSWORD_TIP);
        }
    });
}

function showTip(text) {
    $("#code-tip").text(text);
    $("#code-tip").show();
    $("#code-input").val("");
    freshCode();
}

function setCookie(name, value) {
    var time = new Date();
    time.setDate(time.getDate() + 1);
    document.cookie = encodeURI(name + "=" + value) + ";expires=" + time.toUTCString();
}
