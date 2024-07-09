package com.samsam.info_final.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // CONNECT 요청 처리 (예: 인증)
            log.info("Websocket 연결 요청: {}", accessor.getSessionId());
        } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            String roomId = extractRoomId(accessor.getDestination());
            log.info("채팅방 구독 요청: roomId={}", roomId);
        }

        return message;
    }
    
    
    private String extractRoomId(String destination) {
        // destination 형식: /sub/chat-room/{roomId}
        String[] parts = destination.split("/");
        if (parts.length >= 4) {
            return parts[3]; // roomId 추출
        }
        return null;
    }
}
