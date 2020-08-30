"use strict";

//Plaeholder handler
$(function () {

    if (!Modernizr.input.placeholder) {             //placeholder for old brousers and IE

        $('[placeholder]').focus(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
        $('[placeholder]').parents('form').submit(function () {
            $(this).find('[placeholder]').each(function () {
                var input = $(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            })
        });
    }

    //注册
    $('#register-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $name = self.find('[name=nickname]');
        var $email = self.find('[type=email]');
        var $pass = self.find('[name=password]');
        var $phone = self.find('[name=phone]');

        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult('Error! Wrong email!', $email);
            error++;
        }

        if ($name.val().length > 1 && $name.val() != $name.attr('placeholder')) {
            $name.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write your name!', $name);
            error++;
        }

        if ($phone.val().length > 2 && $phone.val() != $phone.attr('placeholder')) {
            $phone.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write phone!', $phone);
            error++;
        }

        if ($pass.val().length > 1 && $pass.val() != $pass.attr('placeholder')) {
            $pass.removeClass('invalid_field');
        } else {
            createErrTult('Error! Wrong password!', $pass);
            error++;
        }

        var formInput = self.serialize();
        if ($email.val().length > 3 && $pass.val().length > 2) {
            $.post("/doRegister", formInput, function (data) {
                if (data.code == 400) {
                    alert(data.extend.error);
                } else {
                    window.location.reload();
                    if (error != 0) return;
                    self.find('[type=submit]').attr('disabled', 'disabled');
                }
            }, "json");
        }
    }); // end register


    //修改
    $('#update-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);
        var $name = self.find('[name=nickname]');
        var $pass = self.find('[name=password]');
        var $repass = self.find('[name=repassword]');
        var $phone = self.find('[name=phone]');
        var $email = self.find('[type=email]');

        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult('Error! Wrong email!', $email);
            error++;
        }

        if ($name.val().length > 1 && $name.val() != $name.attr('placeholder')) {
            $name.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write your name!', $name);
            error++;
        }

        if ($phone.val().length > 2 && $phone.val() != $phone.attr('placeholder')) {
            $phone.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write phone!', $phone);
            error++;
        }

        if ($pass.val().length > 6 && $pass.val() ===$repass.val()) {
            $pass.removeClass('invalid_field');
        } else {
            createErrTult('密码错误！', $pass);
            error++;
        }
        if ($repass.val().length > 6 && $pass.val() ===$repass.val()) {
            $repass.removeClass('invalid_field');
        } else {
            createErrTult('确认密码错误！', $repass);
            error++;
        }

        var formInput = self.serialize();
        if ($email.val().length > 3 && $pass.val() ===$repass.val()) {
         /*
            //出错，ajax修改不了。
            $.ajax({
                type:"post",//请求类型
                url:"/doUpdUser",//请求的url
                data:{
                   name:$name.val(), pass:$pass.val(),repass:$repass.val(),email:$email.val(),phone:$phone.val()
                },//请求参数
                dataType:"json",//ajax接口（请求url）返回的数据类型
                success:function(data){//data：返回数据（json对象）
                        window.parent.location.href = "/login";
                },
                error:function(data){//当访问时候，404，500 等非200的错误状态
                    window.location.reload();
                }
            });*/

    $.post("/doUpdUser", formInput, function (data) {
                if (data.code == 400) {
                    alert(data.extend.error);
                    window.location.reload();
                } else {
                  /!*  window.location.reload();*!/
                    window.parent.location.href = "/login";
                    if (error != 0) return ;
                    self.find('[type=submit]').attr('disabled', 'disabled');
                }
            }, "json");
        }

    }); // end


    //登录
    $('#login-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $email = self.find('[type=email]');
        var $pass = self.find('[type=password]');

        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult("Error! Wrong email!", $email);
            error++;
        }

        if ($pass.val().length > 1 && $pass.val() != $pass.attr('placeholder')) {
            $pass.removeClass('invalid_field');
        } else {
            createErrTult('Error! Wrong password!', $pass);
            error++;
        }

        var formInput = self.serialize();
        $.post("/doLogin", formInput, function (data) {
            if (data.code == 400) {
                alert(data.extend.error);
            } else {
                window.location.href = "/";
                if (error != 0) return;
                self.find('[type=submit]').attr('disabled', 'disabled');
            }
        }, "json");
    }); // end submit


    //提示框
    function createErrTult(text, $elem) {
        $elem.focus();
        $('<p />', {
            'class': 'inv-em alert alert-danger',
            'html': '<span class="icon-warning"></span>' + text + ' <a class="close" data-dismiss="alert" href="#" aria-hidden="true"></a>',
        })
            .appendTo($elem.addClass('invalid_field').parent())
            .insertAfter($elem)
            .delay(4000).animate({'opacity': 0}, 300, function () {
            $(this).slideUp(400, function () {
                $(this).remove()
            })
        });
    }
});
