package com.silu.game.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by piguangtao on 15/1/21.
 */
@Service
public class MqPublishService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqPublishService.class);

    @Value("#{configproperties['mq.user.name']}")
    private String mqUserName;

    @Value("#{configproperties['mq.user.password']}")
    private String mqUserPassword;

    @Value("#{configproperties['mq.address']}")
    private String mqAddress;


    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("#{configproperties['mq.publish.queue']}")
    private String publishQueue;

    private Channel pubChannel;

    @Override
    public void afterPropertiesSet() throws Exception {
        initMq();
    }

    private void initMq() {
        initChannel(initConnection());
    }


    private void initChannel(Connection connection) {
        if (null == connection) {
            LOGGER.error("fails to create channel. ");
            return;
        }
        //每个连接均建立发布通道
        try {
            pubChannel = connection.createChannel();
        } catch (IOException e) {
            LOGGER.error("fails to create channel. ");
        }
    }

    private Connection initConnection() {
        ConnectionFactory.Params params = ConnectionFactory.Params.build(mqUserName, mqUserPassword).buildVirtualHost(ConnectionFactory.DEFAULT_VIRTUAL_HOST);
        Connection connection = createConnection(params, mqAddress);
        return connection;
    }


    private Connection createConnection(ConnectionFactory.Params params, String address) {
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection(params, address);
        } catch (IOException e) {
            LOGGER.error("=====fails to init mq.=====", e);
        }
        return connection;
    }

    public void publishMsg(byte[] msg) {
        if (null == pubChannel) {
            LOGGER.error("pubChannel should not be null.");
            return;
        }
        try {
            pubChannel.basicPublish("", publishQueue, null, msg);
        } catch (IOException e) {
            LOGGER.error("fails to pub channel");
        }
    }
}
