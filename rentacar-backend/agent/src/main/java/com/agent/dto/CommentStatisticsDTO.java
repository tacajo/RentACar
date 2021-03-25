package com.agent.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentStatisticsDTO {
    private Long adID;
    private int numOfComments;
}
