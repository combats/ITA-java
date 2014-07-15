$(function () {
    $("#dialog-form-add-group").dialog({
        modal: true,
        autoOpen: false,
        width: 'auto',
        resizable: false,
        dialogClass: 'dialog',
        show: { effect: "fade", duration: 800 },
        hide: { effect: "fade", duration: 800 },
        open: function () {
            $.ajax({
                url: "/groups/courses",
                dataType: "json",
                type: "GET",
                success: function (data) {
                    var output = "";
                    var template = "<option value={{courseName}}>{{courseName}}</option>";
                    for (var index = 0; index < data.length; index++) {
                        var view = {
                            courseName: data[index].name
                        }
                    output+=Mustache.render(template,view);
                    }
                    $("#gCourse").html(output);
                },
                error: function (data) {
                    console.log("" + data);
                }
            });
        },
        close: function () {

        }
    });
});