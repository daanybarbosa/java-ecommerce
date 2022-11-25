package br.com.ecommerce.service;

import br.com.ecommerce.exceptions.EnderecoException;
import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.repository.EnderecoRepository;
import br.com.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Endereco salvar(Endereco endereco) throws EnderecoException {
       validaEndereco(endereco);
       return enderecoRepository.save(endereco);
    }

    public void validaEndereco(Endereco endereco) throws EnderecoException {

        if (endereco.getCep() == null | endereco.getCep().toString().length() <= 7){
            throw new EnderecoException("CEP precisa ter 8 caracteres");
        }

        if (endereco.getEndereco() == null | endereco.getEndereco().length() <= 3) {
            throw new EnderecoException("Endereço precisa ter no minimo 3 caracteres");
        }

        if (endereco.getNumero() == null | endereco.getNumero().length() <= 0) {
            throw new EnderecoException("Numero precisa ser preenchido");
        }

        if (endereco.getComplemento() == null | endereco.getComplemento().length() <= 0) {
            throw new EnderecoException("Complemento precisa ter no minimo 3 caracteres");
        }

        if (endereco.getBairro() == null | endereco.getBairro().length() <= 3) {
            throw new EnderecoException("Bairro precisa ter no minimo 3 caracteres");
        }

        if (endereco.getCidade() == null | endereco.getCidade().length() <= 3) {
            throw new EnderecoException("Cidade precisa ter no minimo 3 caracteres");
        }

        if (endereco.getEstado() == null | endereco.getEstado().length() <= 0) {
            throw new EnderecoException("Numero precisa ser preenchido");
        }
    }

    public Endereco buscarPorCep(Integer cep){
        Endereco endCep = enderecoRepository.findByCep(cep);
        return endCep;
    }

    public List<Endereco> buscarPorEstadoECidade(String estado, String cidade){
        List<Endereco> enderecos = enderecoRepository.findByEstadoAndCidade(estado, cidade);
        return enderecos;
    }

}
