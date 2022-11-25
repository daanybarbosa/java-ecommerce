package br.com.ecommerce.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String email;
    private String senha;
    private String token;

}
