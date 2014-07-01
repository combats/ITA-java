function viewGroups() {
    var template;
    jQuery.get('/group_webui/resources/template/iconTemplate', function (data) {
        template = data;
    });
    var selectedValue = $("#drop").val();
    var statusUrl = "/group_webui/groups/" + selectedValue
    $.ajax({
        url: statusUrl,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var output = "";
            var image;
            for (index = 0; index < data.length; index++) {
                var view = {
                    ref: '/group_webui/groups/list/' + data[index].groupID,
                    image: data[index].course.imageRef,
                    courseName: "Course : " + data[index].course.name,
                    groupName: "Group : " + data[index].groupName,
                    groupId: data[index].groupID
                }
                output += Mustache.render(template, view);
            }
            var view = {
                ref: "/group_webui/groups/create",
                image: "grey-plus.jpg",
                courseName: "Create group",
                groupName: "Create group",
                groupId: "id0"
            }
            output += Mustache.render(template, view);

            $("#portfolio1").html(output);
        },
        error: function(){
            alert("error");
        }
    });
}