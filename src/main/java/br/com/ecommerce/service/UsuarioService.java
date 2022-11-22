package br.com.ecommerce.service;

import br.com.ecommerce.exceptions.UsuarioException;
import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.models.Usuario;
import br.com.ecommerce.repository.EnderecoRepository;
import br.com.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Usuario salvar(Usuario usuario) throws UsuarioException {

        if (usuario.getNome() == null || usuario.getNome().length() <= 3){
            throw new UsuarioException("Nome precisa ter no minimo 3 caracteres");
        }

        if (usuario.getSobrenome() == null || usuario.getSobrenome().length() <= 3){
            throw new UsuarioException("Sobrenome precisa ter no minimo 3 caracteres");
        }

        if (usuario.getCpf() == null || usuario.getCpf().length() <= 3){
            throw new UsuarioException("CPF precisa ter no minimo 3 caracteres");
        }

        if (usuario.getTelefone() == null || usuario.getTelefone() <= 3){
            throw new UsuarioException("Telefone precisa ter no minimo 3 caracteres");
        }

        if (usuario.getDataNascimento() == null){
            throw new UsuarioException("Data de nascimento não pode ser nulo");
        }

        if (usuario.getSexo() == null || usuario.getSexo().length() <= 3){
            throw new UsuarioException("Sexo precisa ter no minimo 3 caracteres");
        }

        if (usuario.getEmail() == null || usuario.getEmail().length() <= 3){
            throw new UsuarioException("Email precisa ter no minimo 3 caracteres");
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() <= 3){
            throw new UsuarioException("Senha precisa ter no minimo 3 caracteres");
        }

        Endereco e = enderecoRepository.save(usuario.getEndereco());
        usuario.setEndereco(e);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        Optional<Usuario> usuarioDB = usuarioRepository.findById(id);
        return usuarioDB;
    }

    public Usuario buscarUsuarioPorEmail(String email){
        Usuario usuarioEmail = usuarioRepository.findByEmail(email);
        return  usuarioEmail;
    }

}
