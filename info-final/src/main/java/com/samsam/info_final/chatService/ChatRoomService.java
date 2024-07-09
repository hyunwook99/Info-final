package com.samsam.info_final.chatService;

import com.samsam.info_final.chatEntity.*;
import com.samsam.info_final.chatRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public CHATROOM createChatRoom(String sellerId, String buyerId) {
        CHATROOM chatRoom = new CHATROOM(sellerId, buyerId );
        return chatRoomRepository.save(chatRoom);
    }

    public CHATROOM getChatRoom(UUID chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElse(null);
    }
}
