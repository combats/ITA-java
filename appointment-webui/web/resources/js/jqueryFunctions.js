function viewGroups() {
    var template;
    jQuery.get('/appointment-webui/resources/template/iconTemplate', function (data) {
        template = data;
    });
    var selectedValue = $("#drop").val();
    var statusUrl = "/appointment-webui/groups/" + selectedValue
    $.ajax({
        url: statusUrl,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var output = "";
            var image;
            for (index = 0; index < data.length; index++) {
                var view = {
                    ref: '/appointment-webui/groups/list/' + data[index].groupID,
                    image: data[index].course.imageRef,
                    courseName: "Course : " + data[index].course.name,
                    groupName: "Group : " + data[index].groupName,
                    groupId: data[index].groupID
                }
                output += Mustache.render(template, view);
            }
            var view = {
                ref: "/appointment-webui/groups/create",
                image: "grey-plus.jpg",
                courseName: "Create group",
                groupName: "Create group",
                groupId: "id0"
            }
            output += Mustache.render(template, view);
            $("#portfolio1").html(output);
        }
    });
}