package com.samsam.info_final.chatDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoomDTO {
	
 private UUID roomId;
 private	String sellerId;
 private	String buyerId;
 private	LocalDateTime createdAt;

 public ChatRoomDTO() {
     // 기본 생성자 추가
 }
 
 
 public ChatRoomDTO(UUID roomId, String sellerId, String buyerId, LocalDateTime createdAt) {
     this.roomId = roomId;
     this.sellerId = sellerId;
     this.buyerId = buyerId;
     this.createdAt = createdAt;
 }
}
