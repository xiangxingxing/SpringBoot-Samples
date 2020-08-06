package me.levi.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message){
        LOGGER.info("Received <" + message + ">");
        latch.countDown();
    }

    /**
    * signal that the message has been received
    * */
    public CountDownLatch getLatch() {
        return latch;
    }
}
