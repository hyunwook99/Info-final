package com.samsam.info_final.chatcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsam.info_final.chatDTO.MessageDTO;
import com.samsam.info_final.chatDTO.ChatRoomDTO;
import com.samsam.info_final.chatEntity.CHATROOM;
import com.samsam.info_final.chatService.ChatRoomService;
import com.samsam.info_final.chatService.MessagesService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;

    private final MessagesService messagesService;
     
    
    @Autowired
    public MessageController(SimpMessagingTemplate template, ChatRoomService chatRoomService , MessagesService messagesService) {
        this.template = template;
        this.chatRoomService = chatRoomService;
        this.messagesService = messagesService;
    }

    @PostMapping("/enter")
    public ResponseEntity<ChatRoomDTO> startChat(@RequestBody ChatRoomDTO chatRoomDTO) {
        // 고정된 sellerId를 사용하거나 필요한 sellerId를 여기서 설정합니다.
        String sellerId = "고정된 sellerId";

        // 채팅방 생성
        CHATROOM createdChatRoom = chatRoomService.createChatRoom(sellerId, chatRoomDTO.getBuyerId());

        // ChatRoom을 ChatRoomDTO로 변환하여 반환
        ChatRoomDTO createdChatRoomDTO = new ChatRoomDTO(
            createdChatRoom.getRoomId(),
            createdChatRoom.getSellerId(),
            createdChatRoom.getBuyerId(),
            createdChatRoom.getCreatedAt()
        );

        // 생성된 ChatRoomDTO를 응답으로 반환
        return new ResponseEntity<>(createdChatRoomDTO, HttpStatus.CREATED);
    }
    
    
    
    @GetMapping("/enter/{roomId}")
    public ResponseEntity<ChatRoomDTO> enterChatRoom(@PathVariable("roomId") UUID roomId) {
        // roomId를 기반으로 채팅방 정보를 DB에서 가져옵니다.
        CHATROOM chatRoom = chatRoomService.getChatRoom(roomId);
        
        if (chatRoom != null) {
            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
            chatRoomDTO.setRoomId(chatRoom.getRoomId());
            chatRoomDTO.setSellerId(chatRoom.getSellerId());
            chatRoomDTO.setBuyerId(chatRoom.getBuyerId());
            chatRoomDTO.setCreatedAt(chatRoom.getCreatedAt());
            
            return new ResponseEntity<>(chatRoomDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    @MessageMapping("/chat/enter")
//    public void enter(@Payload CHATROOM chatRoom) {
//        logger.info("입장 메시지 받음: {}", chatRoom);
//        try {
//            UUID chatRoomId = chatRoom.getRoomId();
//
//            // 해당 채팅방에 입장 메시지 전송
//            ChatMessageDto enterMessage = new ChatMessageDto();
//            enterMessage.setWriter(chatRoom.getSellerId()); // 입장한 사람을 작성자로 설정
//            enterMessage.setMessage(chatRoom.getSellerId() + " 님이 채팅방에 입장하셨습니다.");
//            enterMessage.setRoomId(chatRoomId.toString());
//
//            template.convertAndSend("/sub/chat/" + chatRoomId, enterMessage);
//            logger.info("/sub/chat/{}에 입장 메시지 전송", chatRoomId);
//        } catch (IllegalArgumentException e) {
//            logger.error("유효하지 않은 채팅방 ID: {}", chatRoom.getRoomId(), e);
//        }
//    }
   
    @MessageMapping("/pub/chat/message")
    public void sendMessage(@Payload MessageDTO message) {
        logger.info("Received message: {}", message);  // 수신된 메시지 로그

        // 메시지를 저장하거나 필요한 로직 수행
        messagesService.saveMessage(message);

        // 채널 구독자에게 메시지 브로드캐스트
        String destination = String.format("/sub/chat-room/%s", message.getRoomId());
        logger.info("Broadcasting message to: {}", destination);  // 브로드캐스트 전송 로그
        template.convertAndSend(destination, message);
    }
}



