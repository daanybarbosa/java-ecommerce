package br.com.ecommerce.repository;

import br.com.ecommerce.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findById(Long id);
    Optional<Produto> findByNomeDoProduto(String nomeDoProduto);
}
