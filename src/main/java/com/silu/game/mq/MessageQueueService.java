package com.silu.game.mq;

import com.chatgame.protobuf.TcpBiz;
import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class MessageQueueService implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MessageQueueService.class);

    private ObjectMapper mapper;

    public byte[] writePacket(NotifyMessage message) {
        ByteBuf body = Unpooled.buffer();
        body.writeByte(1); //system type
        byte flag = 0;
        if (message.getAckFlag()) {
            flag |= 1;
        }
        if (message.getPushFlag()) {
            flag |= 4;
        }

        if (message.isIncrOfflineCount()) { /*推送是否需要更新显示数字*/
            flag |= 8;
        }
        body.writeByte(flag);
        body.writeBytes(message.getFrom().getBytes());
        body.writeBytes(message.getTo().getBytes());
        body.writeLong(0);

        byte[] push_content_bytes = new byte[0];
        try {
            push_content_bytes = message.getPushContent().getBytes("UTF-8");
        } catch (Exception e) {

        }
        body.writeShort(push_content_bytes.length);
        body.writeBytes(push_content_bytes);

        byte[] dataJson = new byte[0];
        try {
            dataJson = mapper.writeValueAsBytes(message.getData());
        } catch (Exception e) {

        }
        body.writeShort(dataJson.length);
        body.writeBytes(dataJson);
        body.writeLong(0);
        //log.debug("body size is {}", body.writableBytes());
        //log.debug("body capacity is {}", body.capacity());

        ByteBuf header = Unpooled.buffer();
        header.writeByte(0xb7);
        header.writeByte(0);
        header.writeByte(8);
        header.writeByte(0);
        header.writeShort((int) (System.currentTimeMillis() >> 32));
        header.writeInt((int) System.currentTimeMillis());
        header.writeShort(0);
        header.writeShort(body.readableBytes());
        header.writeShort(0);

        ByteBuf packet = Unpooled.wrappedBuffer(header, body);
        byte[] data = new byte[packet.readableBytes()];
        packet.readBytes(data);


        packet.release();
        if (log.isDebugEnabled()) {
            log.debug("toUser is {}, body is {}", message.getTo(), new String(data));
        }

        TcpBiz.Tcp2BizReq.Builder builder = TcpBiz.Tcp2BizReq.newBuilder();
        builder.setSource(TcpBiz.SourceType.CORE_SERVER).setAppId(0).setUserId(message.getFrom()).setTraceId(UUID.randomUUID().toString()).setMsgBody(ByteString.copyFrom(data));
        return builder.build().toByteArray();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);

        mapper.setAnnotationIntrospector(pair);
    }

}
