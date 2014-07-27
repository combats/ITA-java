package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.NotificationJSONInfo;
import com.softserveinc.ita.service.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/applicantNotification")
public class ApplicantsNotificationController {

    @Autowired
    private QueueManager queueManagerService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody List<NotificationJSONInfo> postIDEntityListToNotifyApplicants(@RequestBody List<NotificationJSONInfo> infoList) {
        queueManagerService.notifyApplicants(infoList);
        return infoList;
    }
}
