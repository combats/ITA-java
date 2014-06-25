//function viewGroups() {
//    var template = "<li data-id={{groupId}}>" +
//        "<div class='easyBox'>" +
//        " <a href='{{ref}}'>" +
//        "<img src='${pageContext.servletContext.contextPath}/resources/images/{{image}}' alt=''></a>" +
//        " <div class='inner'>" +
//        " <h3 class='light'><a href='{{ref}}'>{{groupName}}</a></h3> " +
//        "<p>{{courseName}}</p> " +
//        "</div>" +
//        " </div> " +
//        "</li>"
//    var selectedValue = $("#drop").val();
//    var statusUrl = "/appointment-webui/groups/" + selectedValue
//    $.ajax({
//        url: statusUrl,
//        dataType: "json",
//        type: "GET",
//        success: function (data) {
//            var output = "";
//            var image;
//            for (index = 0; index < data.length; index++) {
//                var view = {
//                    ref: '/appointment-webui/groups/list/' + data[index].groupID,
//                    image: data[index].course.imageRef,
//                    courseName: "Course : " + data[index].course.name,
//                    groupName: "Group : " + data[index].groupName,
//                    groupId: data[index].groupID
//                }
//                output += Mustache.render(template, view);
//            }
//            var view = {
//                ref: "/appointment-webui/groups/create",
//                image: "grey-plus.jpg",
//                courseName: "Create group",
//                groupName: "Create group",
//                groupId: "id0"
//            }
//            output += Mustache.render(template, view);
//            $("#portfolio1").html(output);
//        }
//    });
//}