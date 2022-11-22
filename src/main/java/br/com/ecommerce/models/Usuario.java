package br.com.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuario_sistema")
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column(length = 200, nullable = false)
    private String sobrenome;

    @Column(length = 200, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private Integer telefone;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 200, nullable = false)
    private String sexo;

    @Column(length = 200, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "endereco", referencedColumnName = "cep")
    private Endereco endereco;

    @OneToMany(targetEntity=Pedido.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "pedido", referencedColumnName = "id")
    private List<Pedido> pedidos;

}
