package br.com.ecommerce.repository;

import br.com.ecommerce.models.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Sessao findByUsuarioId(Long id);
}
