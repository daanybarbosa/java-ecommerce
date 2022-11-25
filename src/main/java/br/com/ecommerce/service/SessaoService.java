package br.com.ecommerce.service;

import br.com.ecommerce.dto.LoginDto;
import br.com.ecommerce.exceptions.SessaoException;
import br.com.ecommerce.models.Sessao;
import br.com.ecommerce.models.Usuario;
import br.com.ecommerce.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessaoService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SessaoRepository sessaoRepository;

    public Sessao ativarSessao(LoginDto loginDto) throws SessaoException {

        // buscar usuario
        Usuario usuario = usuarioService.buscarUsuarioPorEmailESenha(loginDto.getEmail(), loginDto.getSenha());
        if (usuario == null) throw new SessaoException("Usuário não existe!");

        // se usuario existir consultar sessao
        Sessao sessao = consultarSessao(usuario);

        // se sessao exitir, deletar sessao e recriar
        if (sessao != null){
            sessaoRepository.deleteById(sessao.getId());
        }

        sessao = new Sessao();
        // gerar o token da sessao com UUID.random().toString() que retorna lgo como uadhduihasdiuhaiuhh2o2po
        sessao.setToken(UUID.randomUUID().toString());
        sessao.setDataDeAcesso(LocalDateTime.now());
        sessao.setUsuario(usuario);

        // retornar login com token da sessao/
        return sessaoRepository.save(sessao);
    }

    public Sessao consultarSessao(Usuario usuario){
        return sessaoRepository.findByUsuarioId(usuario.getId());
    }

    //consultar sessao por token
    
}
