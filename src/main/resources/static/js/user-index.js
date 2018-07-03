$(function () {
    $.ajax({
        type: "GET",
        cache: "false",
        url: "/admin/count/token",
        dataType: "json",
        success: function (result) {
            if (result.status == 1) {
                $("#countToken").text(result.data);
            }
        }
    });
    $.ajax({
        type: "GET",
        cache: "false",
        url: "/admin/count/user",
        dataType: "json",
        success: function (result) {
            if (result.status == 1) {
                $("#countUser").text(result.data);
            }
        }
    });
});

