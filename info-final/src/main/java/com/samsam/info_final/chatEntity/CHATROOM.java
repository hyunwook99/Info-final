package com.samsam.info_final.chatEntity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;


@Entity
@AllArgsConstructor
public class CHATROOM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID") // Specify the column name
    private UUID roomId; // Unique identifier for the chat room

    @Column(name = "SELLER_ID") // Specify the column name
    private String sellerId; // Seller ID

    @Column(name = "BUYER_ID") // Specify the column name
    private String buyerId; // Buyer ID

    @Column(name = "CREATEDAT") // Specify the column name
    private LocalDateTime createdAt; // Creation timestamp
    
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<MESSAGE> messages;

    // Constructors, getters, and setters

    public CHATROOM() {}

    public CHATROOM(String sellerId, String buyerId) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.createdAt = LocalDateTime.now(); // Initialize createdAt with the current timestamp
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
