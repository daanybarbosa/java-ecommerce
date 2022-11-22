package br.com.ecommerce.repository;

import br.com.ecommerce.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Endereco findByCep(Integer cep);
    //Endereco findByEstadoECidade(String estado, String cidade);
}
