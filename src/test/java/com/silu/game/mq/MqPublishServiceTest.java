package com.silu.game.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.charset.Charset;

/**
 * Created by piguangtao on 15/1/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/servlet_entrance.xml")
public class MqPublishServiceTest {

    @Autowired
    private MqPublishService mqPublishService;

    @Autowired
    private MessageQueueService service;

    @Test
    public void publishTest(){
        String test = "testtest";
        NotifyMessage message = new NotifyMessage();
        message.setAckFlag(false);

        for (int i =0;i< 1000000;i++){
            mqPublishService.publishMsg(test.getBytes(Charset.forName("utf8")));
        }
        try {
            Thread.sleep(50 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
