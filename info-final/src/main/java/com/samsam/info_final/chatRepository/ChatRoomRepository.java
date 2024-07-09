package com.samsam.info_final.chatRepository;

import com.samsam.info_final.chatEntity.*;	
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<CHATROOM, UUID> {
}
