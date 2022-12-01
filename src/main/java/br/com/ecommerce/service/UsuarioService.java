package br.com.ecommerce.service;

import br.com.ecommerce.exceptions.EnderecoException;
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

    @Autowired
    private EnderecoService enderecoService;

    public Usuario salvar(Usuario usuario) throws UsuarioException, EnderecoException {
        validaUsuario(usuario);
        Endereco enderecoDB = enderecoService.salvar(usuario.getEndereco());
        usuario.setEndereco(enderecoDB);

        return usuarioRepository.save(usuario);
    }

    public void validaUsuario(Usuario usuario) throws UsuarioException {

        if (usuario.getNome() == null | usuario.getNome().length() <= 0){
            throw new UsuarioException("Nome precisa ser preenchido");
        }

        if (usuario.getSobrenome() == null | usuario.getSobrenome().length() < 3){
            throw new UsuarioException("Sobrenome precisa ter no minimo 3 caracteres");
        }

        if (usuario.getCpf() == null | usuario.getCpf().length() < 8){
            throw new UsuarioException("CPF precisa ter no minimo 8 caracteres");
        }

        if (usuario.getTelefone() == null | usuario.getTelefone().toString().length() < 9){
            throw new UsuarioException("Telefone precisa ter no minimo 9 caracteres");
        }

        if (usuario.getDataNascimento() == null){
            throw new UsuarioException("Data de nascimento não pode ser nulo");
        }

        if (usuario.getSexo() == null){
            throw new UsuarioException("Sexo precisa ser preenchido");
        }

        if (usuario.getEmail() == null | usuario.getEmail().length() < 3){
            throw new UsuarioException("Email precisa ter no minimo 3 caracteres");
        }

        if (usuario.getSenha() == null | usuario.getSenha().length() < 3) {
            throw new UsuarioException("Senha precisa ter no minimo 3 caracteres");
        }
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        Optional<Usuario> usuarioDB = usuarioRepository.findById(id);
        return usuarioDB;
    }

    public Usuario buscarUsuarioPorEmailESenha(String email, String senha){
        Usuario usuarioEmail = usuarioRepository.findByEmailAndSenha(email, senha);
        return  usuarioEmail;
    }

    public Usuario buscarEndereco(Integer cep){
        Usuario enderecoCep = usuarioRepository.findByEndereco(cep);
        return enderecoCep;
    }
}
