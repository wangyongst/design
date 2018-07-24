$(function () {
    $("#look").click(function () {
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
            url: "/admin/advert",
            data: {
                advertid: ids[1]
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $('#lookImg').attr("src", result.data.image);
                }
            }
        });
        $('#myModal').modal('toggle');
    });

    $("#create").click(function () {
        $('#myModal2').modal('toggle');
    });

    $("#upload").change(function () {
        var formData = new FormData();
        formData.append('image', this.files[0]);
        $.ajax({
            type: "POST",
            cache: "false",
            contentType: false,
            processData: false,
            url: "/upload/image",
            data: formData,
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $('#image').val("http://pas99p7vd.bkt.clouddn.com/" + result.data);
                }
            }
        });
    });

    $("#refer").click(function () {
        var selected = select();
        if (selected == "") {
            alert("请先选择你要推荐的记录");
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
            url: "/admin/advert/refer",
            data: {
                advertid: ids[1]
            },
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $("#myTable").bootstrapTable('refresh');
                }
            }
        });
    });

    $("#submit2").click(function () {
        $.ajax({
            type: "Post",
            cache: "false",
            url: "/admin/advert",
            data: $('#advertForm').serialize() + "&operation=1",
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $('#myModal2').modal('toggle');
                    $("#myTable").bootstrapTable('refresh');
                }
            }
        });
    });

    $("#close").click(function () {
        $('#myModal').modal('toggle');
    });

    $("#close2").click(function () {
        $('#myModal2').modal('toggle');
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

