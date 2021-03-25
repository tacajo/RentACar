package com.adService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class AdRatingDTO {

    private Long id;

    private Long adId;

    private double rating;

}