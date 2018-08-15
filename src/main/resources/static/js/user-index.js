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


    $("#delete").click(function () {
        var selected = select();
        if (selected == "") {
            alert("请先选择你要查看的记录");
            return;
        }
        var ids = selected.split(",");
        if (ids.length > 2) {
            alert("请选择一条记录");
            return;
        }
        $.ajax({
            type: "POST",
            cache: "false",
            url: "/admin/user",
            data: {
                userid: ids[1],
                type: 2
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    alert("删除用户成功")
                    $("#myTable").bootstrapTable('refresh');
                }
            }
        });
    });


    $("#refer").click(function () {
        var selected = select();
        if (selected == "") {
            alert("请先选择你要查看的记录");
            return;
        }
        var ids = selected.split(",");
        if (ids.length > 2) {
            alert("请选择一条记录");
            return;
        }
        $.ajax({
            type: "POST",
            cache: "false",
            url: "/admin/user",
            data: {
                userid: ids[1],
                type: 3
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    alert("推广成功")
                }
            }
        });
    });


    $("#update").click(function () {
        var selected = select();
        if (selected == "") {
            alert("请先选择你要查看的记录");
            return;
        }
        var ids = selected.split(",");
        if (ids.length > 2) {
            alert("请选择一条记录");
            return;
        }
        $.ajax({
            type: "GET",
            cache: "false",
            url: "/admin/user",
            data: {
                userid: ids[1],
                type: 1
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $('#userid').val(result.data.id)
                    $('#email').val(result.data.email)
                    $('#myModal').modal('toggle');
                } else {
                    alert(result.message);
                }
            }
        });

        $('#myModal').modal('toggle');
    });

    $("#refer").click(function () {
        var selected = select();
        if (selected == "") {
            alert("请先选择你要查看的记录");
            return;
        }
        var ids = selected.split(",");
        if (ids.length > 2) {
            alert("请选择一条记录");
            return;
        }
        $.ajax({
            type: "GET",
            cache: "false",
            url: "/admin/user",
            data: {
                userid: ids[1],
                type: 3
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    alert("推广成功");
                } else {
                    alert(result.message);
                }
            }
        });

        $('#myModal').modal('toggle');
    });


    $("#close").click(function () {
        $('#myModal').modal('toggle');
    });


    $("#submit").click(function () {
        $.ajax({
            type: "Post",
            cache: "false",
            url: "/admin/user",
            data: $('#advertForm').serialize() + "&operation=1",
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $('#myModal').modal('toggle');
                    $("#myTable").bootstrapTable('refresh');
                } else {
                    alert(result.message);
                }
            }
        });
    });
});


function select() {
    var ids = "";
    $("input[name=btSelectItem]").each(function () {
        if ($(this).prop('checked')) {
            var index = $("table input:checkbox").index(this);
            val = $("table").find("tr").eq(index).find("td").eq(1).text();
            ids += "," + val;
        }
    });
    return ids;
}

function showAvatar(value, row, index) {
    return "<img style='width: auto;height: 60px' src='" + value + "'><img>";
}

