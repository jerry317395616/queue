package org.jeecg.modules.netty;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyServer {

    @PostConstruct
    public void start() throws Exception {
        new Thread(() -> {
            try {
                new WebsocketServer(8081).run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
