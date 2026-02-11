package com.rabbit.SpringRabbit.User;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UserPublisher {

    private final StreamBridge streamBridge;

    public UserPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publish(String userId){
        streamBridge.send("userCreated-out-0", new UserCreatedEvent(userId));
    }
}
