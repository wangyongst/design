$(function () {
    $.ajax({
        type: "GET",
        cache: "false",
        url: "/admin/count/study",
        dataType: "json",
        success: function (result) {
            if (result.status == 1) {
                $("#countStudy").text(result.data);
            }
        }
    });
});

