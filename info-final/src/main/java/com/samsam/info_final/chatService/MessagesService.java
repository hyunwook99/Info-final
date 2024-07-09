package com.samsam.info_final.chatService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.samsam.info_final.chatDTO.MessageDTO;
import com.samsam.info_final.chatEntity.CHATROOM;
import com.samsam.info_final.chatEntity.MESSAGE;
import com.samsam.info_final.chatRepository.MessageRepository;



@Service
public class MessagesService {
	
	private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessagesService(MessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void saveMessage(MessageDTO messageDTO) {
        // Convert DTO to Entity
        CHATROOM chatRoom = new CHATROOM();
        chatRoom.setRoomId(messageDTO.getRoomId());

        MESSAGE message = new MESSAGE(chatRoom, messageDTO.getSellerId(), messageDTO.getBuyerId(), messageDTO.getContent());
        messageRepository.save(message);

        // Broadcast message to subscribers
        broadcastMessage(messageDTO);
    }

    private void broadcastMessage(MessageDTO messageDTO) {
        // Assuming you have a WebSocket topic "/topic/chat-room/{roomId}" configured
        String destination = "/topic/chat-room/" + messageDTO.getRoomId();
        messagingTemplate.convertAndSend(destination, messageDTO);
    }

    public List<MessageDTO> getMessagesByRoomId(UUID roomId) {
        List<MESSAGE> messages = messageRepository.findByChatRoomRoomId(roomId);
        return messages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MessageDTO convertToDTO(MESSAGE message) {
        return new MessageDTO(
                message.getBuyerId(),
                message.getSellerId(),
                message.getMsgContent(),
                message.getChatroom().getRoomId()
        );
    }
}
