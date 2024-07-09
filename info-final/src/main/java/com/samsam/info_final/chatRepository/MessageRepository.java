package com.samsam.info_final.chatRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsam.info_final.chatEntity.MESSAGE;

public interface MessageRepository extends JpaRepository<MESSAGE, Long> {
	
	 List<MESSAGE> findByChatRoomRoomId(UUID roomId);

}
