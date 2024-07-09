package com.samsam.info_final.chatEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MESSAGE {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private CHATROOM chatroom;

    @Column(name = "SELLER_ID")
    private String sellerId;

    @Column(name = "BUYER_ID")
    private String buyerId;

    @Column(name = "MSG_CONTENT")
    private String msgContent;

    // Constructors, getters, and setters

    public MESSAGE() {
    }

    public MESSAGE(CHATROOM chatroom, String sellerId, String buyerId, String msgContent) {
        this.chatroom = chatroom;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.msgContent = msgContent;
    }
    
    

    public CHATROOM getChatroom() {
        return chatroom;
    }

    public void setChatroom(CHATROOM chatroom) {
        this.chatroom = chatroom;
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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}