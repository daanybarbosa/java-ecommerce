package br.com.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarrinhoDto {

    List<Long> produtosId;
    Integer cep;

}
