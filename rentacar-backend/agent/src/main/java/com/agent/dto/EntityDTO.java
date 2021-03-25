package com.agent.dto;

import lombok.Getter;
import lombok.Setter;

public class EntityDTO {

    private Long id;

    private String name;

    public EntityDTO() {};

    public EntityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EntityDTO(String name){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
