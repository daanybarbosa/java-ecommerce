package br.com.ecommerce.models;

import br.com.ecommerce.enumeration.Categoria;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto_sistema")
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_do_produto", length = 200, nullable = false)
    private String nomeDoProduto;

    @Column(length = 200, nullable = false)
    private String modelo;

    @Column(length = 200, nullable = false)
    private String fabricante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(name = "valor_unitario", length = 200, nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "quantidade_do_produto", nullable = false)
    private Integer qtdeProduto;

    @Column(name = "descricao_do_produto", length = 200, nullable = false)
    private String descricaoDoProduto;

    @Column(name = "informacoes_tecnicas", length = 200, nullable = false)
    private String informacoesTecnicas;
}
