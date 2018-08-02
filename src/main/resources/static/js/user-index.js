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
                type:2
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $("#myTable").bootstrapTable('refresh');
                }
            }
        });
        $('#myModal').modal('toggle');
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

