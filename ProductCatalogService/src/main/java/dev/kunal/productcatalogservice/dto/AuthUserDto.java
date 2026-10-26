package dev.kunal.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDto {

    private String email;
    private String role;
}
