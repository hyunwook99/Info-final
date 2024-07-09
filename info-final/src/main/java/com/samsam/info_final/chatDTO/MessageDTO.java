package com.samsam.info_final.chatDTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String buyerId;
    private String sellerId;
    private String content;
    private UUID roomId;
}
