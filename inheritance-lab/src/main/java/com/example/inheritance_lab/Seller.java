package com.example.inheritance_lab;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seller extends User{
    private String gstNo;
}
