package br.com.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao_sistema")
@Getter
@Setter
public class Sessao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "data_de_acesso", nullable = false)
    private LocalDateTime dataDeAcesso;

    @Column(nullable = false)
    private String token;
}
