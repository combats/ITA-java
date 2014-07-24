//load applicants by id
$(function () {
    loadUsersList();
    loadApplicantsIDListByStatus('SCHEDULED');
    loadApplicantsIDListByStatus('NOT_SCHEDULED');
    loadApplicantsByStatus('SCHEDULED');
    loadApplicantsByStatus('NOT_SCHEDULED');
    //load appointments by id
    applicants.forEach(function (element) {
            if (element['applicant']['status'] == 'SCHEDULED') {
                $.ajax({
                    async: false,
                    url: 'http://176.36.11.25:8080' + '/appointments/' + element.appointmentID,
                    dataType: 'json',
                    type: "GET",
                    success: function (appointment) {
                        element['appointment'] = parseAppointment(appointment);
                    },
                    error: function () {
                        console.log('content', "Failed to load appointment from server by id:" + element.appointmentID);
                    }
                })
            }
        }
    );
    //render
    var emptyApplicant = {name: "", surname: "", city: "", cv: "", birthday: "", photo: ""};
    //new applicant
    var newAppRendered = Mustache.render(pageTemplate, {'data': [emptyApplicant], 'newApp': true});
    $('.newapp').html(newAppRendered);
    //scheduled
    var scheduledRendered = Mustache.render(pageTemplate, {data: applicants.filter(function (element) {
        return element['applicant']['status'] == 'SCHEDULED';
    }), 'edit': true});
    $('.scheduled').html(scheduledRendered);
    //not scheduled
    var notScheduledRendered = Mustache.render(pageTemplate, {'data': applicants.filter(function (element) {
        return element['applicant']['status'] == 'NOT_SCHEDULED';
    }), 'schedule': true });
    $('.not_scheduled').html(notScheduledRendered);
    postRender();
    setInterval(function () {
        $('div.schedule').each(function (index, element) {
            var date = $(element).find('.date')[0];
            var time = $(element).find('.time')[0];
            if (date && time) {
                if (new Date($(date).val()).getTime() + parseTime($(time).val()) - new Date().getTime() < 30 * 60 * 1000) {
                    $(element).find('.interview').button({disabled: false});
                }
            }
        });
    }, 1000 * 60 * 2);
});
beginInterview = function (target) {
    var appointmentID = $(target).closest('div.schedule').attr('appointmentID');
    $.ajax({
        async: false,
        url: location.origin + '/ui/interview/?appointmentId=' + appointmentID,
        type: "GET"
    });
};
postAppointment = function (event) {
    var requestType = "POST";
    var id = "";
    if ($(event.target).parent('button').hasClass("schedulable")) {
        requestType = "PUT";
        id = $(event.target).closest('div.schedule').attr('appointmentid');
    }
    var target = $(event.target);
    var parentdiv = $(target).parent().closest("div.schedule");
    var parent = parentdiv.find("select.scheduled");
    var names = [];
    $(parent).children("option").each(function () {
        names.push($(this).text())
    });
    var userIDs = userIDByName(names);
    var dateTarget = parentdiv.find(".date");
    var timeTarget = parentdiv.find(".time");
    var durationTarget = parentdiv.find(".duration");
    if (validateDate(dateTarget) && validateInput(parentdiv.find('input.validatable'))) {
        if (userIDs.length == 0) {
            $("#dialog").data('content', 'Select at least one user!');
            $('#dialog').dialog('open');
        } else {
            var appointment = {
                id: id,
                applicantId: $(event.target).closest('div.applicant').attr('applicantid'),
                userIdList: userIDs,
                duration: +$(durationTarget).val() * 60 * 1000,
                startTime: new Date($(dateTarget).val()).getTime() + parseTime($(timeTarget).val())};
            $.ajax({
                    async: false,
                    url: 'http://176.36.11.25:8080' + '/appointments/',
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(appointment),
                    type: requestType,
                    success: function (newAppointment) {
                        if (requestType == 'PUT') {
                            disableElements(event.target);
                            $("#dialog").data('content', 'Appointment successfuly scheduled/edited');
                            $('#dialog').dialog('open');
                        } else {
                            createAppointment(newAppointment);
                        }
                    },
                    error: function () {
                        $("#dialog").data('content', 'Failed to schedule/edit appointment!');
                        $('#dialog').dialog('open');
                    }
                }
            );
        }
    }
};
disableElements = function (item) {
    var parentdiv = $(item).closest('div.schedule');
    $(parentdiv).find("input.schedulable").prop('disabled', true);
    $(parentdiv).find('button.schedulable').button({disabled: true});
    $(parentdiv).find('.editAppointment').button({disabled: false});
};
createAppointment = function (newApointment) {
    var tosend = {};
    var applicantID = newApointment['applicantId'];
    tosend[applicantID] = {};
    tosend[applicantID]['status'] = 'SCHEDULED';
    tosend[applicantID]['rank'] = -1;
    $.ajax({
        async: false,
        url: location.origin + '/groups/' + groupID + '/applicants',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(tosend),
        type: 'PUT',
        success: function () {
            var elementToChange = elementByApplicantID(applicantID);
            elementToChange['applicant']['status'] = 'SCHEDULED';
            elementToChange['appointment'] = parseAppointment(newApointment);
            var scheduledRendered = Mustache.render(pageTemplate, {'data': applicants.filter(function (element) {
                return element['applicant']['status'] == 'SCHEDULED';
            }), 'edit': true});
            $('.scheduled').html(scheduledRendered);
            var notScheduledRendered = Mustache.render(pageTemplate, {'data': applicants.filter(function (element) {
                return element['applicant']['status'] == 'NOT_SCHEDULED';
            }), 'schedule': true});
            $('.not_scheduled').html(notScheduledRendered);
            $(".accordion").accordion("destroy");
            postRender();
            $("#dialog").data('content', 'Information updated!');
            $('#dialog').dialog('open');
        },
        error: function () {
            $("#dialog").data('content', 'Failed to assign status to the applicant within a group!');
            $('#dialog').dialog('open');
        }
    });
};
submitApplicant = function (event) {
    var requestType = 'POST';
    var parentdiv = $(event.target).closest('div.info');
    var inputData = parentdiv.find('input.validatable');
    var inputDate = parentdiv.find('input.date');
    if (validateInput(inputData) && validateDate(inputDate)) {
        var applicant = buildApplicant(inputData);
        applicant['birthday'] = new Date($(inputDate).val()).getTime();
        applicant['id'] = "";
        var applicantID = $(event.target).closest('div.applicant').attr('applicantID');
        if (applicantID) {
            requestType = 'PUT';
            applicant['id'] = applicantID;
        }
        if (requestType == 'POST') {
            var formData = new FormData($('form')[0]);
            $.ajax({
                async: false,
                url: location.origin + '/repo/doc/' + applicantID,
                data: formData,
                type: requestType,
                cache: false,
                contentType: false,
                processData: false,
                error: function () {
                    $("#dialog").data('content', 'Failed to upload CV!');
                    $('#dialog').dialog('open');
                }
            });
        }
        $.ajax({
            async: false,
            url: location.origin + '/applicants/',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(applicant),
            type: requestType,
            success: function (newApp) {
                if (requestType == 'PUT') {
                    updateApplicant(newApp);
                    $(event.target).closest('div').find('button').button({'disabled': false});
                    $(event.target).parent().button({'disabled': true});
                    $("#dialog").data('content', 'Information updated');
                    $('#dialog').dialog('open');
                } else {
                    notify({
                        applicantId: applicant.id,
                        groupId: groupID,
                        responsibleHrId: getHRID()
                    });
                    $(event.target).closest('div.applicant').find('input').val('');
                    createApplicant(newApp);
                }
            },
            error: function () {
                $("#dialog").data('content', 'Failed to create/change applicant!');
                $('#dialog').dialog('open');
            }
        });
    }
};
validateInput = function (target) {
    var result = true;
    $(target).each(function (index, element) {
        var tmpresult = true;
        if ($(element).hasClass('cv')) {
            tmpresult = $(element).val().length != 0;
        } else {
            var regex = $(element).attr('pattern');
            regex = new RegExp(regex);
            var inputVal = $(element).val();
            tmpresult = regex.test(inputVal);
        }
        changeStyle(tmpresult, element);
        if (!tmpresult) {
            result = tmpresult;
        }
    });
    return result;
};
validateDate = function (target) {
    var d = new Date();
    var before = ($(target).attr('before') === "true");
    var selectedDateStr = $(target).val();
    var selectedDate = new Date(selectedDateStr);
    if (!selectedDate) {
        return false;
    }
    var result;
    if (before) {
        result = selectedDate > d;
    } else {
        result = selectedDate < d;
    }
    changeStyle(result, target);
    return result;
};
changeStyle = function (condition, target) {
    if ($(target).attr('type') == 'file') {
        target = $(target).closest('div.info').find('input.NFI-filename');
    }
    if (condition) {
        $(target).removeClass("ui-state-error").addClass("ui-state-highlight");
    } else {
        $(target).removeClass("ui-state-highlight").addClass("ui-state-error");
    }
};
buildApplicant = function (input) {
    var applicant = {};
    $(input).each(function (index, element) {
        var name = $(element).attr('name');
        if (name) {
            applicant[name] = $(element).val();
        }
    });
    return applicant;
};
createApplicant = function (input) {
    input['status'] = 'NOT_SCHEDULED';
    var tosend = {};
    tosend[input.id] = {};
    tosend[input.id]['status'] = input['status'];
    tosend[input.id]['rank'] = -1;
    var date = new Date(input.birthday);
    input.birthday = date.getMonth() + '/' + date.getDate() + '/' + date.getFullYear();
    $.ajax({
        async: false,
        url: location.origin + '/groups/' + groupID + '/applicants',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(tosend),
        type: 'PUT',
        success: function () {
            applicants.push({applicant: input, appointment: {availableUsers: userList, scheduledUsers: []}});
            var notScheduledRendered = Mustache.render(pageTemplate, {'data': applicants.filter(function (element) {
                return element['applicant']['status'] == 'NOT_SCHEDULED';
            }), 'schedule': true});
            $('.not_scheduled').html(notScheduledRendered);
            $(".accordion").accordion("destroy");
            postRender();
            $("#dialog").data('content', 'Information updated!');
            $('#dialog').dialog('open');
        },
        error: function () {
            $("#dialog").data('content', 'Failed to assign status to the applicant within a group!');
            $('#dialog').dialog('open');
        }
    });
};
parseTime = function (input) {
    var split = input.split(/[,\-\.:]/);
    var result = split[0] * 60 * 60 * 1000;//hours to millis
    if (split.length > 1) {
        result += split[1] * 60 * 1000;//+minutes to millis
    }
    return result;
};
userIDByName = function (names) {
    var result = [];
    $(names).each(function (index, name) {
        $(userList).each(function (index, user) {
            if (name == user.name + ' ' + user.surname) {
                result.push(user.id);
            }
        });
    });
    return result;
};
parseAppointment = function (appointment) {
    //appointment contains list of IDs of scheduled users
    var scheduledUsers = getUsersByIDList(appointment['userIdList']);
    //rest of users
    var availableUsers = [];
    userList.forEach(function (element) {
        var clone = JSON.parse(JSON.stringify(element));
        if ($.inArray(element, scheduledUsers) != -1) {
            clone['disabled'] = true;
        }
        availableUsers.push(clone);
    });
    var date = new Date(appointment.startTime);
    var startDate = ('0' + date.getMonth() + 1).slice(-2) + '/' + ('0' + date.getDate()).slice(-2) + '/' + date.getFullYear();
    var startTime = ('0' + date.getHours()).slice(-2) + ':' + ('0' + date.getMinutes()).slice(-2);
    return {'availableUsers': availableUsers,
        'scheduledUsers': scheduledUsers, 'duration': appointment.duration / 60 / 1000,
        'startDate': startDate,
        'startTime': startTime}
};
addUser = function (event) {
    var parentdiv = $(event.target).parent().closest("div");
    var select = $(parentdiv).find("select.users");
    var selectedOption = $(select).children(":selected");
    var scheduled = $(parentdiv).find("select.scheduled");
    $(selectedOption).prop("disabled", true);
    $(selectedOption).prop("selected", false);
    $('<option value=' + $(selectedOption).val() + '>'
        + $(selectedOption).text() + '</option>').appendTo(scheduled);
    var scheduledNumber = $(scheduled).children("option").length;
    var usersNumber = $(select).children("option").length;
    if (scheduledNumber == usersNumber) {
        $(event.target).parent().button("disable");
    }
    if (scheduledNumber != 0) {
        $(parentdiv).find(".removeUser").button({disabled: false});
    }
    $(select).selectmenu("refresh");
    $(scheduled).selectmenu("refresh");
};
removeUser = function (event) {
    var parentdiv = $(event.target).parent().closest("div");
    var scheduled = $(parentdiv).find("select.scheduled");
    var selectedOption = $(scheduled).children(":selected");
    var users = $(parentdiv).find("select.users");
    var selectedUser = $(users).children("option[value='" + $(selectedOption).val() + "']");
    $(selectedOption).remove();
    $(selectedUser).prop("disabled", false);
    $(selectedUser).prop("selected", true);
    var scheduledNumber = $(scheduled).children("option").length;
    var usersNumber = $(users).children("option:enabled").length;
    if (scheduledNumber == 0) {
        $(event.target).parent().button("disable");
    }
    if (usersNumber != 0) {
        $(parentdiv).find(".addUser").button({disabled: false});
    }
    $(scheduled).selectmenu("refresh");
    $(users).selectmenu("refresh");
};
updateApplicant = function (applicant) {
    $(applicants).each(function (index, element) {
        if (element.applicant.id == applicant.id) {
            element.applicant = applicant;
        }
    });
};
getUsersByIDList = function (IDList) {
    return userList.filter(function (element) {
        return $.inArray(element.id, IDList) > -1;
    });
};