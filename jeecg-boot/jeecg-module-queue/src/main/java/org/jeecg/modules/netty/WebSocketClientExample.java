package org.jeecg.modules.netty;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketClientExample {

    public static void main(String[] args) {
        try {
            // 连接到服务器的 URI
            URI uri = new URI("ws://localhost:8081/ws");

            // 创建 WebSocket 客户端
            WebSocketClient client = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("已连接到服务器");
                    // 发送客户端标识符
                    String clientId = "client123"; // 替换为实际的客户端 ID
                    String message = "{\"type\":\"register\", \"clientId\":\"" + clientId + "\"}";
                    send(message);
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("收到消息: " + message);
                    // 在这里更新客户端显示的数据
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("连接已关闭，原因：" + reason);
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("发生错误: " + ex.getMessage());
                }
            };

            // 连接到服务器
            client.connect();

            // 等待连接成功
            while (!client.isOpen()) {
                Thread.sleep(100);
            }

            // 保持程序运行，等待接收消息
            System.out.println("按下回车键退出程序...");
            System.in.read();

            // 关闭连接
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
