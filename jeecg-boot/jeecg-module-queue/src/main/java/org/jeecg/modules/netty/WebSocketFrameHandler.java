package org.jeecg.modules.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 使用线程安全的 Map 来存储 clientId 和 Channel 的映射关系
    public static Map<String, Channel> clientIdChannelMap = new ConcurrentHashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        WebsocketServer.channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        WebsocketServer.channels.remove(ctx.channel());

        // 从 Map 中移除对应的 clientId 和 Channel
        clientIdChannelMap.values().removeIf(channel -> channel == ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 处理来自客户端的消息
        String text = msg.text();
        System.out.println("收到客户端消息: " + text);

        // 解析 JSON 消息
        JsonNode jsonNode = objectMapper.readTree(text);
        String type = jsonNode.get("type").asText();

        if ("register".equals(type)) {
            String clientId = jsonNode.get("clientId").asText();
            // 将 clientId 和 Channel 存入 Map
            clientIdChannelMap.put(clientId, ctx.channel());
            System.out.println("客户端注册成功，clientId: " + clientId);
        } else {
            // 处理其他类型的消息
            System.out.println("收到未知类型的消息: " + type);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常: " + cause.getMessage());
        ctx.close();
    }
}


