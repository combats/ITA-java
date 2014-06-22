
var group;
function getGroup() {
    $.ajax({
        url: '/appointment-webui/id2',
        dataType: "json",
        type: "GET",
        success: function (data) {
            group = data;
            $("#groupID").text(data.groupID);
            $("#status").text(data.groupStatus);
            $("#course").text(data.course);
            $("#object").text(data);
        }
    });
};

function viewGroups() {
    $.ajax({
        url: '/appointment-webui/groups',
        dataType: "json",
        type: "GET",
        success: function (data) {
            var list;
            for	(index = 0; index < data.length; ++index) {
                list += $("#groupID" + index).text(data[index].groupID);
                list += $("#status" + index).text(data[index].groupStatus);
                list += $("#course" + index).text(data[index].course);
            }
            alert(list);
        }
    });
};




