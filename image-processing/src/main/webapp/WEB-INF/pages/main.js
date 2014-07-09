/**
 * Created by pjatochkin on 04-Jul-2014.
 */
$.ajax({
        url: location.origin + '/appointments',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(appointment),
        type: "POST",
        success: function () {
            $("#dialog").data('content', 'Appointment successfuly scheduled');
            $('#dialog').dialog('open');
        },
        error: function () {
            $("#dialog").data('content', 'Failed to schedule new appointment!');
            $('#dialog').dialog('open');
        }
    }
);