package br.com.ecommerce.service;

import br.com.ecommerce.exceptions.EnderecoException;
import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.repository.EnderecoRepository;
import br.com.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Endereco salvar(Endereco endereco) throws EnderecoException {

        if (endereco.getCep() == null || endereco.getCep().toString().length() < 8) {
            throw new EnderecoException("CEP deve conter de 8 digitos");
        }

        if (endereco.getEndereco() == null || endereco.getEndereco().length() <= 3) {
            throw new EnderecoException("Endereço precisa ter no minimo 3 caracteres");
        }

        if (endereco.getNumero() == null || endereco.getNumero().length() <= 0) {
            throw new EnderecoException("Numero precisa ser preenchido");
        }

        if (endereco.getComplemento() == null || endereco.getComplemento().length() <= 0) {
            throw new EnderecoException("Complemento precisa ter no minimo 3 caracteres");
        }

        if (endereco.getBairro() == null || endereco.getBairro().length() <= 3) {
            throw new EnderecoException("Bairro precisa ter no minimo 3 caracteres");
        }

        if (endereco.getCidade() == null || endereco.getCidade().length() <= 3) {
            throw new EnderecoException("Cidade precisa ter no minimo 3 caracteres");
        }

        if (endereco.getEstado() == null || endereco.getEstado().length() <= 0) {
            throw new EnderecoException("Numero precisa ser preenchido");
        }

        return enderecoRepository.save(endereco);
    }

    public Endereco buscarPorCep(Integer cep){
        Endereco endCep = enderecoRepository.findByCep(cep);
        return endCep;
    }

    /*
    public Endereco buscarPorEstadoECidade(String estado, String cidade){
        Endereco endEstadoECidade = enderecoRepository.findByEstadoECidade(estado, cidade);
        return endEstadoECidade;
    } */

}
