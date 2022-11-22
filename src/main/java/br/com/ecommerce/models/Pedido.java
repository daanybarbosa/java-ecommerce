package br.com.ecommerce.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pedido_sistema")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private Double valorProduto;

    @Column(nullable = false)
    private Double qtdeProduto;

    @Column(nullable = false)
    private Double valorTotalPedido;

}
