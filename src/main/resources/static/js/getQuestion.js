$(document).ready(function () {
    $("#button").click(function () {
        var param1 = $("#param").val();

        $.ajax({
            type: "GET",
            url: "/getData",
            data: {param1: param1},
            success: function (result) {
                $("#result").text(result);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});