function getGroup() {
    $.ajax({
        url: 'http://localhost:8080/appointment-webui/id2',
        dataType: "json",
        type: "GET",
        success: function (data) {
            $("#groupID").text(data.groupID);
            $("#status").text(data.groupStatus);
            $("#course").text(data.course);
            $("#object").text(data);
        }
    });
};

function readyFunc() {
    alert('Im ready!');
}

(function ($) {
    readyFunc = function () {
        alert('Im ready!');
    };
})(jQuery);


(function ($) {
    hide = function () {
        $("#hide").click(function () {
            $("p").hide();
        });
        $("#show").click(function () {
            $("p").show();
        });
        s
    };
})(jQuery);






