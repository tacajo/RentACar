package com.messageService.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageDTO {

    private Long id;

    private String content;

    private Date date;

    private Long senderID;

    private Long receiverID;

    private Long adID;
}
