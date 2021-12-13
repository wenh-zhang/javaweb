
    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#login").validate({
        rules: {
            username: {
                required: true,
            },
            password: {
                required: true,
            },
        },
        messages: {
            username: {
                required: " *",
            },
            password: {
                required: " *",
            },
        }

    })
});