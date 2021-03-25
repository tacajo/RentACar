package com.adService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityDTO {

    private Long id;

    private String name;

    public EntityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EntityDTO(String name) {
        this.name = name;
    }
}
