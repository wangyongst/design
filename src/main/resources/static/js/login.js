$(function () {
    $("#loginButton").click(function () {
        if (!checkIsNotNull()) {
            return;
        }
        var jobType = isChecked($("#channelAddForm"));
        if ($("#id").val() == null || $("#id").val() == "") {
            $("#id").val("0");
        }
        $("#source").val("手动");
        $.ajax({
            type: "POST",
            cache: "false",
            url: "channel/add.do",
            data: $('#channelAddForm').serialize() + "&jobType=" + jobType,
            dataType: "json",
            error: function () {//请求失败时调用函数。
                alert("出现错误");
            },
            success: function (result) {
                $("#channelTable").bootstrapTable('refresh');
                $("#channelInfo").modal('hide');
            }
        });
    });
});

