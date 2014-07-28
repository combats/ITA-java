package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.entity.NotificationJSONInfo;
import com.softserveinc.ita.service.QueueManager;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import java.util.List;


@Service
public class QueueManagerServiceImpl implements QueueManager {

    static Logger log = Logger.getLogger(
            QueueManagerServiceImpl.class.getName());

    @Produce(uri = "activemq:notification.queue")   //Creates a producer that writes to the JMS queue that is automatically created by ActiveMQ.
            ProducerTemplate producer;

    public void notifyApplicants(List<NotificationJSONInfo> infoList) {
        for (NotificationJSONInfo info : infoList) {
            producer.sendBody("activemq:notification.queue", info);
            log.info("ID: " + info + " was sent to the queue");
        }
    }
}
