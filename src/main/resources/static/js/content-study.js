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
    $.ajax({
        type: "GET",
        cache: "false",
        url: "/admin/count/click",
        dataType: "json",
        success: function (result) {
            if (result.status == 1) {
                $("#countClick").text(result.data);
            }
        }
    });
});
