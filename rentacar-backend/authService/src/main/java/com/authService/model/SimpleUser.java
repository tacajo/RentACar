package com.authService.model;

import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("simpleuser")
public class SimpleUser  extends User {


}
