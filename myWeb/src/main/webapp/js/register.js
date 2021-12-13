$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#register").validate({
        rules: {
            username: {
                required: true,
                checkUsername:true,
            },
            password: {
                required: true,
                checkPassword:true,
            },
            password_confirm:{
                required:true,
                checkPwdSame:true,
            },
            email:{
                required:true,
                checkEmail:true,
            }
        },
        messages: {
            username: {
                required: " *不能为空",
            },
            password: {
                required: " *不能为空",
            },
            password_confirm: {
                required: " *不能为空",
            },
            email: {
                required: " *不能为空",
            }
        }
    })
    $.validator.addMethod("checkUsername",function(value,element,params){
        var checkUsername = /^[-_a-zA-Z0-9]{3,16}$/;
        return this.optional(element)||(checkUsername.test(value));
    }," *格式不符");
    $.validator.addMethod("checkPassword",function(value,element,params){
        var checkUsername = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;
        return this.optional(element)||(checkUsername.test(value));
    }," *格式不符");
    $.validator.addMethod("checkPwdSame",function(value,element,params){
        var pwd = document.getElementsByName("password_confirm");
        return this.optional(element)||(pwd!=value);
    }," *两次输入不同");
    $.validator.addMethod("checkEmail",function(value,element,params){
        var checkUsername = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        return this.optional(element)||(checkUsername.test(value));
    }," *格式不符");

});