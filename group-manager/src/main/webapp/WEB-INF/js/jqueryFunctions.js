function viewGroups() {
    var template;
    jQuery.get("template/iconTemplate", function (data) {
        template = data;
    });
    var createGroupHtml;
    jQuery.get("template/createGroupHtml", function (data) {
        createGroupHtml = data;
    });
    var selectedValue = $("#drop").val();
    var statusUrl = "/groups/" + selectedValue;
    $.ajax({
        url: statusUrl,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var output = "";
            var image;
            for (var index = 0; index < data.length; index++) {
                var view = {
                    ref: 'groups/list/' + data[index].groupID,
                    image: data[index].course.imageRef,
                    courseName: "Course : " + data[index].course.name,
                    groupName: "Group : " + data[index].groupName,
                    groupId: data[index].groupID
                }
                output += Mustache.render(template, view);
            }
            output += createGroupHtml;
            $("#portfolio1").html(output);
        },
        error: function(data){
            console.log("" + data);
        }
    });
}

function viewDialog() {
    $("#dialog-form-add-group").data('content', 'Information updated!');
    $('#dialog-form-add-group').dialog('open');
}