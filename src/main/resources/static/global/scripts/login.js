var Login = function() {
	var loginForm = $('#loginForm');
	var handleLogin = function() {
		loginForm.validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                ename: {required: true},
	                pwd: {required: true},
	                rememberme: {required: false}
	            },
	            messages: {
	            	ename: { required: "请输入登录名" },
	            	pwd: { required: "请输入密码" }
	            },
	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                loginForm.find('div.alert').removeClass('display-hide').find('span').html('请输入用户名和密码');
	            },
	            highlight: function (element) { // hightlight error inputs
	                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	            },
	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },
	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            },
	            submitHandler: function (form) {
	                //form.submit();
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                	login();
	                }
	                return false;
	            }
	        });
	};

	var login = function() {
        if(!loginForm.valid()) return false;
        loginForm.find('div.alert').addClass('display-hide');
        var mask = layer.load(0, {shade: [0.2,'#000']});
        var pwd = loginForm.find('input[name=pwd]');
        var oldpwd = pwd;
        pwd.val($.md5(pwd.val()));
        $.post('web_login', loginForm.serialize(), function(data) {
            if(data.res == 'SUCCESS') {
                window.location = data.msg;
            } else {
                loginForm.find('div.alert').removeClass('display-hide').find('span').html(data.msg);
            }
            pwd.val(oldpwd);
            layer.close(mask);
        }, 'JSON');
    };
	
	var close = function() {
		loginForm.find('div.alert').addClass('display-hide');
	};
	
	return {
		handleLogin : handleLogin,		// 校验登录用户名密码
		login : login,					// 登录提交验证用户名密码
		close : close					// 关闭验证提示
	};
}();

jQuery(document).ready(function() {
	$(document).ajaxError(function(event, request, settings) {
		if(request.status == 500) {
			alert("服务器异常，请重新登录或刷新页面重试，如果仍存在问题请联系管理员！");
		}
		else if (request.status == 403) {
			alert("请求URL"+settings.url+"系统检测您没有权限！");
		} 
		else if (request.status == 302) {
			alert("没有权限，请重试！");
		}
		else if (request.status == 200) {
			alert("用户请求错误，可能是登录超时，请刷新页面重新登录后重试！");
		} 
		else {
			alert("异常状态："+request.status+"，请重新登录或刷新页面重试！");
		}
	});
	
	// validate form
	Login.handleLogin();
	// init background slide images
	$.backstretch([ "global/layout/img/bg/1.jpg",
			"global/layout/img/bg/2.jpg",
			"global/layout/img/bg/3.jpg",
			"global/layout/img/bg/4.jpg" ], {
		fade : 1000,
		duration : 8000
	});
});

var login_test = function() {
    var ll = $("#testForm");
    var pwd = ll.find('input[name=pwd]');
    pwd.val($.md5(pwd.val()));
    $.post('web_login', ll.serialize(), function(data) {
        if(data.res == 'SUCCESS') {
            window.location = data.msg;
        } else {
            ll.find('div.alert').removeClass('display-hide').find('span').html(data.msg);
        }
        pwd.val(1);
    }, 'JSON');
    pwd.val(1);
};