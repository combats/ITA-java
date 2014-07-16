package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.NotificationJSONInfo;

import java.util.List;

public interface QueueManager {
    public void notifyApplicants(List<NotificationJSONInfo> appointmentIDs);
}
