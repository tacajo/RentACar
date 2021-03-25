package com.authService.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("admin")
@Entity
public class Admin extends User {

}
