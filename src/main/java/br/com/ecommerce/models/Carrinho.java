package br.com.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho_sistema")
@Getter
@Setter
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_sistema", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "produto_sistema", referencedColumnName = "id")
    private List<Produto> produtos = new ArrayList<>();

    @Column(name = "quantidade_produto", nullable = false)
    private Integer qtdeProduto = 0;

    @Column(name = "valor_total_produto", nullable = false)
    private BigDecimal valorTotalProduto = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "endereco_sistema", referencedColumnName = "cep")
    private Endereco endereco;

    @Column(nullable = false)
    private BigDecimal frete = BigDecimal.ZERO;

    @Column(name = "valor_total_carrinho", nullable = false)
    private BigDecimal valorTotalCarrinho = BigDecimal.ZERO;

    public BigDecimal getValorTotalProduto() {
        this.valorTotalProduto = BigDecimal.ZERO;

        for (Produto produto: produtos) {
            this.valorTotalProduto = this.valorTotalProduto.add(produto.getValorUnitario()); //BigDecimal
        }
        return valorTotalProduto;
    }

    public BigDecimal getValorTotalCarrinho(){
        this.valorTotalCarrinho = getValorTotalProduto();
        this.valorTotalCarrinho = this.valorTotalCarrinho.add(frete);
        return valorTotalCarrinho;
    }

    public Integer getQtdeProduto(){
        return this.produtos.size();
    }
}
