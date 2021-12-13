$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#orderForm").validate({
        rules: {
            receiverName: {
                required: true,
            },
            receiverPhone: {
                required: true,
            },
            receiverAddress: {
                required: true,
            },
        },
        messages: {
            receiverName: {
                required: " *",
            },
            receiverPhone: {
                required: " *",
            },
            receiverAddress: {
                required: " *",
            },
        }

    })
});