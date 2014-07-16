package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.entity.NotificationJSONInfo;
import com.softserveinc.ita.service.QueueManager;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class QueueManagerServiceImpl implements QueueManager {

    static Logger log = Logger.getLogger(
            QueueManagerServiceImpl.class.getName());

    @Produce(uri = "activemq:notification.queue")   //Creates a producer that writes to the JMS queue that is automatically created by ActiveMQ.
            ProducerTemplate producer;  //TODO: think about interface instead of ProducerTemplate

    public void notifyApplicants(List<NotificationJSONInfo> infoList) {
        for (NotificationJSONInfo info : infoList) {
            producer.sendBody("activemq:notification.queue", info);
            log.info("ID: " + info + " was sent to the queue");
        }
    }
}
