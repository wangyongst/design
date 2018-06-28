$(function () {


});


function logout() {
    $.ajax({
        type: "POST",
        cache: "false",
        url: "admin/user/logout",
        dataType: "json",
        success: function (result) {
            if (result.status == 1) {
                window.location.href = "page-login.html";
            } else {
                alert(result.message);
            }
        }
    });
}

