$(function () {
    $("#gStartDate").datepicker();
    $("#gEndDate").datepicker();
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
                    var output = "<option value=''>Select group course</option>";
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
            $("#gName").val("");
            $("#gCapacity").val("");
            $("#gStartDate").val("");
            $("#gEndDate").val("");
            $("#gAddress").val("");
            $("#gCourse").append("<option value=''>Select group course</option>");
        }
    });

    $("#cancelUButton").click(function (e) {
        e.preventDefault();
        $("#dialog-form-add-group").dialog("close");
    });

    $("#saveUButton").click( function(e){
        e.preventDefault();
        e.stopPropagation();
        if($("#userForm").valid()){
            alert("group is valid");
        }
    });

    $("#userForm").validate({
        rules: {
            groupName: {
                required: true,
                minlength: 3,
                maxlength: 40
            },
            groupAddress: {
                required: true,
                minlength: 8,
                maxlength: 40
            },
            groupCourse: {
                required: true
            },
            groupCapacity: {
                required: true,
                min : 1,
                number : true
            },
            groupStartDate: {
                required: true,
                date: true
            },
            groupEndDate: {
                required: true,
                date: true
            }
        },
        messages: {
            groupName: {
                required: "Group name is required",
                minlength: jQuery.validator.format("At least {0} characters required in \"Group name\" field."),
                maxlength: jQuery.validator.format("No more than {0} characters is allowed in \"Group name\" field.")
            },
            groupAddress: {
                required: "Group address is required.",
                minlength: jQuery.validator.format("At least {0} characters required in \"Group address\" field."),
                maxlength: jQuery.validator.format("No more than {0} characters is allowed in \"Group address\" field.")
            },
            groupCourse: {
                required: "Group course is required."
            },
            groupCapacity: {
                required: "Group capacity is required.",
                min: jQuery.validator.format("At least 1 student required in \"Group capacity\" field."),
                number : "Should be a correct number."
            },
            groupStartDate: {
                required: "Group start date is required.",
                date: "Should be a correct date."
            },
            groupEndDate: {
                required: "Group end date is required.",
                date: "Should be a correct date."
            }
        }
    });

});