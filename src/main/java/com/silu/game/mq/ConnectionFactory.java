package com.silu.game.mq;


import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.net.SocketFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;


@Service
public class ConnectionFactory {

    private final static boolean DAFAULT_TOPOLOGYRECOVERY = true;
    public final static String DEFAULT_VIRTUAL_HOST = "/";
    private final static boolean DEFAULT_AUTOMATIC_RECOVERY = true;
    private final static SaslConfig DEFAULR_SASL_CONFIG = DefaultSaslConfig.PLAIN;
    private final static int DEFAULT_NETWORK_RECOVERY_INTERVEL = 5000;
    private final static SocketFactory DEFAULT_SOCKET_FACTORY = SocketFactory.getDefault();
    private final static int DEFAULT_CONNECTION_TIMEOUT = 5000;
    private final static int DEFAULT_FRAME_MAX = 0;
    private final static SocketConfigurator DEFAULT_SOCKET_CONFIGURATOR = new DefaultSocketConfigurator();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = Executors.defaultThreadFactory();

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);


    public Connection createConnection(Params params, String addresses) throws IOException {
        logger.info("connect to mq {} for sub", addresses);
        if (null == addresses || "".equals(addresses)) {
            throw new IOException("Addresses must not be empty !");
        }

        Address address = new Address(addresses);
        return createConnection(params, address);
    }


    public Connection createConnection(Params params, Address... addresses) throws IOException {
        com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        if (params != null) {
            factory.setUsername(params.userName);
            factory.setPassword(params.password);
            if (params.executor != null) {
                factory.setSharedExecutor(params.executor);
            } else {
            }
            if (null != params.virtualHost) {
                factory.setVirtualHost(params.virtualHost);
            }
            if (params.clientProperties != null) {
                factory.setClientProperties(params.clientProperties);
            }
            factory.setRequestedChannelMax(params.requestedChannelMax);
            factory.setRequestedHeartbeat(params.requestedHeartbeat);
            factory.setSaslConfig(params.saslConfig);
            factory.setNetworkRecoveryInterval(params.networkRecoveryInterval);
            factory.setTopologyRecoveryEnabled(params.topologyRecovery);
            factory.setAutomaticRecoveryEnabled(params.automaticRecovery);
            factory.setSocketFactory(params.socketFactory);
            factory.setSocketConfigurator(params.socketConfigurator);
            factory.setConnectionTimeout(params.connectionTimeout);
            factory.setThreadFactory(params.threadFactory);
        }
        return factory.newConnection(addresses);
    }


    public static class Params {
        private String userName;
        private String password;
        private ExecutorService executor;
        private String virtualHost;
        private Map<String, Object> clientProperties;
        private int requestedChannelMax = 0;
        private int requestedHeartbeat = 30;
        private SaslConfig saslConfig;
        private int networkRecoveryInterval;
        private boolean topologyRecovery;
        private boolean automaticRecovery;
        private SocketFactory socketFactory;
        private SocketConfigurator socketConfigurator;
        private int connectionTimeout;
        private ThreadFactory threadFactory;

        private Params() {
            this.virtualHost = DEFAULT_VIRTUAL_HOST;
            this.automaticRecovery = DEFAULT_AUTOMATIC_RECOVERY;
            this.topologyRecovery = DAFAULT_TOPOLOGYRECOVERY;
            this.saslConfig = DEFAULR_SASL_CONFIG;
            this.networkRecoveryInterval = DEFAULT_NETWORK_RECOVERY_INTERVEL;
            this.socketFactory = DEFAULT_SOCKET_FACTORY;
            this.socketConfigurator = DEFAULT_SOCKET_CONFIGURATOR;
            this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
            this.threadFactory = DEFAULT_THREAD_FACTORY;
        }

        public static Params build(String userName, String password) {
            Params params = new Params();
            params.userName = userName;
            params.password = password;
            return params;
        }

        public Params buildExecutor(ExecutorService executor) {
            this.executor = executor;
            return this;
        }

        public Params buildVirtualHost(String virtualHost) {
            this.virtualHost = virtualHost;
            return this;
        }

        public Params buildClientProperties(Map<String, Object> clientProperties) {
            this.clientProperties = clientProperties;
            return this;
        }

        public Params buildRequestedChannelMax(int requestedChannelMax) {
            this.requestedChannelMax = requestedChannelMax;
            return this;
        }


        public Params buildRequestedHeartbeat(int requestedHeartbeat) {
            this.requestedHeartbeat = requestedHeartbeat;
            return this;
        }

        public Params buildSaslConfig(SaslConfig saslConfig) {
            this.saslConfig = saslConfig;
            return this;
        }

        public Params buildNetworkRecoveryInterval(int networkRecoveryInterval) {
            this.networkRecoveryInterval = networkRecoveryInterval;
            return this;
        }


        public Params buildTopologyRecovery(boolean topologyRecovery) {
            this.topologyRecovery = topologyRecovery;
            return this;
        }

        public Params buildAutomaticRecovery(boolean automaticRecovery) {
            this.automaticRecovery = automaticRecovery;
            return this;
        }

        public Params buildSocketFactory(SocketFactory socketFactory) {
            this.socketFactory = socketFactory;
            return this;
        }

        public Params buildSocketConfigurator(SocketConfigurator socketConfigurator) {
            this.socketConfigurator = socketConfigurator;
            return this;
        }

        public Params buildConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Params buildThreadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

    }

}
