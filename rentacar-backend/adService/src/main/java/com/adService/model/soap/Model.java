package com.adService.model.soap;

import com.adService.model.CodeBook;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@XmlType(name = "Model", propOrder = {
        "id",
        "name"
}, namespace = "http://adService.com/comment")
public class Model {

    private Long id;

    private String name;

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